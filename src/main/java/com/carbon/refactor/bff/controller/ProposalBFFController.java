package com.carbon.refactor.bff.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbon.refactor.application.dto.ProposalResponseDTO;
import com.carbon.refactor.application.dto.request.CompleteProposalRequestDTO;
import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.bff.dto.PaginatedProposalResponseDTO;
import com.carbon.refactor.bff.dto.ProposalFilterRequestDTO;
import com.carbon.refactor.bff.dto.ProposalStatusUpdateRequestDTO;
import com.carbon.refactor.bff.dto.ProposalSummaryDTO;
import com.carbon.refactor.bff.facade.ProposalFacade;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * BFF Controller for proposal operations.
 * This controller provides endpoints specifically tailored for frontend needs,
 * delegating to the ProposalFacade for business logic.
 */
@RestController
@RequestMapping("/api/bff/proposals")
@RequiredArgsConstructor
@Slf4j
public class ProposalBFFController {
    
    private final ProposalFacade proposalFacade;
    
    /**
     * Get a complete proposal by ID.
     * This endpoint aggregates data from multiple services to provide a complete view of a proposal.
     *
     * @param id The ID of the proposal
     * @return The complete proposal data
     */
    @GetMapping("/{id}/complete")
    public ResponseEntity<CompleteProposalResponseDTO> getCompleteProposal(@PathVariable Integer id) {
        log.info("Recebendo requisição para buscar proposta completa com ID: {}", id);
        CompleteProposalResponseDTO completeProposal = proposalFacade.findCompleteProposalById(id);
        return ResponseEntity.ok(completeProposal);
    }
    
    /**
     * Get a proposal summary by ID.
     * This endpoint provides a simplified view of a proposal, tailored for specific frontend needs.
     *
     * @param id The ID of the proposal
     * @return The proposal summary
     */
    @GetMapping("/{id}/summary")
    public ResponseEntity<ProposalSummaryDTO> getProposalSummary(@PathVariable Integer id) {
        log.info("Recebendo requisição para buscar resumo da proposta com ID: {}", id);
        CompleteProposalResponseDTO completeProposal = proposalFacade.findCompleteProposalById(id);
        ProposalSummaryDTO summary = ProposalSummaryDTO.fromCompleteProposal(completeProposal);
        return ResponseEntity.ok(summary);
    }
    
    /**
     * Update a complete proposal.
     * This endpoint allows updating all aspects of a proposal in a single request.
     *
     * @param id The ID of the proposal to update
     * @param completeProposalRequest The complete proposal data
     * @return The updated complete proposal
     */
    @PutMapping("/{id}/complete")
    public ResponseEntity<CompleteProposalResponseDTO> updateCompleteProposal(
            @PathVariable Integer id,
            @Valid @RequestBody CompleteProposalRequestDTO completeProposalRequest) {
        
        log.info("Recebendo requisição para atualizar proposta completa com ID: {}", id);
        CompleteProposalResponseDTO updatedProposal = proposalFacade.updateCompleteProposal(id, completeProposalRequest);
        return ResponseEntity.ok(updatedProposal);
    }
    
    /**
     * Update just the status of a proposal.
     * This endpoint provides a simplified interface for the common operation of updating a proposal's status.
     *
     * @param id The ID of the proposal to update
     * @param statusUpdateRequest The status update request
     * @return The updated proposal
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<ProposalResponseDTO> updateProposalStatus(
            @PathVariable Integer id,
            @Valid @RequestBody ProposalStatusUpdateRequestDTO statusUpdateRequest) {
        
        log.info("Recebendo requisição para atualizar status da proposta com ID: {}", id);
        ProposalResponseDTO updatedProposal = proposalFacade.updateProposalStatus(id, statusUpdateRequest);
        return ResponseEntity.ok(updatedProposal);
    }
    
    /**
     * Search for proposals with filtering and pagination.
     * This endpoint provides a specialized search functionality optimized for frontend needs.
     *
     * @param filter The filter criteria and pagination parameters
     * @return A paginated response containing proposal summaries
     */
    @PostMapping("/search")
    public ResponseEntity<PaginatedProposalResponseDTO> searchProposals(
            @Valid @RequestBody ProposalFilterRequestDTO filter) {
        
        log.info("Recebendo requisição para busca paginada de propostas com filtros");
        PaginatedProposalResponseDTO result = proposalFacade.searchProposals(filter);
        return ResponseEntity.ok(result);
    }
}
