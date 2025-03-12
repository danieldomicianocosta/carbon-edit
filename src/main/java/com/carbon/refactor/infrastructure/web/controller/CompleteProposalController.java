package com.carbon.refactor.infrastructure.web.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.application.dto.request.CompleteProposalRequestDTO;
import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.domain.service.CompleteProposalService;

/**
 * REST controller for managing complete proposal data.
 * This controller provides endpoints to retrieve and update all data related to a proposal in a single request.
 */
@RestController
@RequestMapping("/api/complete-proposals")
@RequiredArgsConstructor
public class CompleteProposalController {
    
    private final CompleteProposalService completeProposalService;
    
    /**
     * GET /api/complete-proposals/:id : Get a complete proposal by ID.
     * 
     * @param id The ID of the proposal to retrieve
     * @return The ResponseEntity with status 200 (OK) and the complete proposal in the body,
     *         or with status 404 (Not Found) if the proposal is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CompleteProposalResponseDTO> findById(@PathVariable Integer id) {
        CompleteProposalResponseDTO completeProposal = completeProposalService.findCompleteProposalById(id);
        return ResponseEntity.ok(completeProposal);
    }
    
    /**
     * PUT /api/complete-proposals/:id : Update a complete proposal by ID.
     * 
     * @param id The ID of the proposal to update
     * @param completeProposalRequest The complete proposal data to update
     * @return The ResponseEntity with status 200 (OK) and the updated complete proposal in the body,
     *         or with status 404 (Not Found) if the proposal is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<CompleteProposalResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody CompleteProposalRequestDTO completeProposalRequest) {
        CompleteProposalResponseDTO updatedCompleteProposal = 
                completeProposalService.updateCompleteProposal(id, completeProposalRequest);
        return ResponseEntity.ok(updatedCompleteProposal);
    }
}
