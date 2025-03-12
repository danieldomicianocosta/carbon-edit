package com.carbon.refactor.domain.service;

import com.carbon.refactor.application.dto.request.CompleteProposalRequestDTO;
import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;

/**
 * Service for handling complete proposal operations.
 */
public interface CompleteProposalService {
    
    /**
     * Find a complete proposal by ID.
     *
     * @param id The ID of the proposal
     * @return The complete proposal data
     */
    CompleteProposalResponseDTO findCompleteProposalById(Integer id);
    
    /**
     * Update an existing complete proposal.
     *
     * @param id The ID of the proposal to update
     * @param completeProposal The complete proposal data
     * @return The updated complete proposal
     */
    CompleteProposalResponseDTO updateCompleteProposal(Integer id, CompleteProposalRequestDTO completeProposal);
}
