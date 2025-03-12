package com.carbon.refactor.infrastructure.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbon.refactor.application.dto.request.ProposalStatusUpdateRequestDTO;
import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.application.dto.response.PossibleNextStatusesResponseDTO;
import com.carbon.refactor.application.dto.response.ProposalStatusValidationResponseDTO;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.domain.service.CompleteProposalService;
import com.carbon.refactor.domain.service.ProposalStatusService;

/**
 * Controller for proposal status operations.
 */
@RestController
@RequestMapping("/api/proposal-status")
public class ProposalStatusController {
    
    private final ProposalStatusService proposalStatusService;
    private final CompleteProposalService completeProposalService;
    
    public ProposalStatusController(
            @Qualifier("proposalStatusServiceImpl") ProposalStatusService proposalStatusService,
            CompleteProposalService completeProposalService) {
        this.proposalStatusService = proposalStatusService;
        this.completeProposalService = completeProposalService;
    }
    
    /**
     * Validate if a status transition is valid according to the state machine.
     *
     * @param request The status update request containing the current and new statuses
     * @return A response entity with the validation result
     */
    @PostMapping("/validate")
    public ResponseEntity<ProposalStatusValidationResponseDTO> validateStatusTransition(
            @RequestBody ProposalStatusUpdateRequestDTO request) {
        
        boolean isValid = proposalStatusService.isValidStatusTransition(
                request.getCurrentStatus(), request.getNewStatus());
        
        ProposalStatus currentStatus = ProposalStatus.findById(request.getCurrentStatus());
        ProposalStatus newStatus = ProposalStatus.findById(request.getNewStatus());
        
        String currentDesc = currentStatus != null ? currentStatus.getDescription() : "Unknown";
        String newDesc = newStatus != null ? newStatus.getDescription() : "Unknown";
        
        String message = isValid
                ? String.format("Status transition from '%s' to '%s' is valid", currentDesc, newDesc)
                : String.format("Status transition from '%s' to '%s' is not valid", currentDesc, newDesc);
        
        ProposalStatusValidationResponseDTO response = ProposalStatusValidationResponseDTO.builder()
                .valid(isValid)
                .currentStatusDescription(currentDesc)
                .newStatusDescription(newDesc)
                .message(message)
                .build();
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Validate if a status transition is valid according to both the state machine and the rules.
     *
     * @param proposalId The ID of the proposal
     * @param request The status update request containing the current and new statuses
     * @return A response entity with the validation result
     */
    @PostMapping("/{proposalId}/validate-with-rules")
    public ResponseEntity<ProposalStatusValidationResponseDTO> validateStatusTransitionWithRules(
            @PathVariable Integer proposalId,
            @RequestBody ProposalStatusUpdateRequestDTO request) {
        
        // First validate according to the state machine
        boolean isValidTransition = proposalStatusService.isValidStatusTransition(
                request.getCurrentStatus(), request.getNewStatus());
        
        if (!isValidTransition) {
            ProposalStatus currentStatus = ProposalStatus.findById(request.getCurrentStatus());
            ProposalStatus newStatus = ProposalStatus.findById(request.getNewStatus());
            
            String currentDesc = currentStatus != null ? currentStatus.getDescription() : "Unknown";
            String newDesc = newStatus != null ? newStatus.getDescription() : "Unknown";
            
            String message = String.format("Status transition from '%s' to '%s' is not valid", currentDesc, newDesc);
            
            ProposalStatusValidationResponseDTO response = ProposalStatusValidationResponseDTO.builder()
                    .valid(false)
                    .currentStatusDescription(currentDesc)
                    .newStatusDescription(newDesc)
                    .message(message)
                    .build();
            
            return ResponseEntity.ok(response);
        }
        
        // Then validate according to the rules
        CompleteProposalResponseDTO completeProposal = completeProposalService.findCompleteProposalById(proposalId);
        boolean rulesValid = proposalStatusService.executeRules(
                completeProposal, request.getCurrentStatus(), request.getNewStatus());
        
        ProposalStatus currentStatus = ProposalStatus.findById(request.getCurrentStatus());
        ProposalStatus newStatus = ProposalStatus.findById(request.getNewStatus());
        
        String currentDesc = currentStatus != null ? currentStatus.getDescription() : "Unknown";
        String newDesc = newStatus != null ? newStatus.getDescription() : "Unknown";
        
        String message;
        if (rulesValid) {
            message = String.format("Status transition from '%s' to '%s' is valid", currentDesc, newDesc);
        } else {
            // Get custom error message from the rules if available
            String errorMessage = proposalStatusService.getErrorMessage(
                    completeProposal, request.getCurrentStatus(), request.getNewStatus());
            
            if (errorMessage != null && !errorMessage.isEmpty()) {
                message = errorMessage;
            } else {
                message = String.format("Status transition from '%s' to '%s' is not valid: rules failed", currentDesc, newDesc);
            }
        }
        
        ProposalStatusValidationResponseDTO response = ProposalStatusValidationResponseDTO.builder()
                .valid(rulesValid)
                .currentStatusDescription(currentDesc)
                .newStatusDescription(newDesc)
                .message(message)
                .build();
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Get the possible next statuses for a given current status.
     *
     * @param currentStatus The current status ID
     * @return A response entity with the list of possible next statuses
     */
    @GetMapping("/possible-next-statuses/{currentStatus}")
    public ResponseEntity<PossibleNextStatusesResponseDTO> getPossibleNextStatuses(
            @PathVariable Integer currentStatus) {
        
        // Get the current status description
        ProposalStatus status = ProposalStatus.findById(currentStatus);
        String currentStatusDescription = status != null ? status.getDescription() : "Unknown";
        
        // Get the list of possible next statuses
        List<Integer> nextStatusIds = proposalStatusService.getPossibleNextStatuses(currentStatus);
        
        // Map the status IDs to DTOs with descriptions
        List<PossibleNextStatusesResponseDTO.StatusDTO> nextStatuses = nextStatusIds.stream()
                .map(id -> {
                    ProposalStatus nextStatus = ProposalStatus.findById(id);
                    String description = nextStatus != null ? nextStatus.getDescription() : "Unknown";
                    return PossibleNextStatusesResponseDTO.StatusDTO.builder()
                            .id(id)
                            .description(description)
                            .build();
                })
                .collect(Collectors.toList());
        
        // Build the response DTO
        PossibleNextStatusesResponseDTO response = PossibleNextStatusesResponseDTO.builder()
                .currentStatusId(currentStatus)
                .currentStatusDescription(currentStatusDescription)
                .possibleNextStatuses(nextStatuses)
                .build();
        
        return ResponseEntity.ok(response);
    }
}
