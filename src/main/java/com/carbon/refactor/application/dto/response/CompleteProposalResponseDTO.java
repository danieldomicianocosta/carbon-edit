package com.carbon.refactor.application.dto.response;

import java.util.List;

import com.carbon.refactor.application.dto.ProposalDetailResponseDTO;
import com.carbon.refactor.application.dto.ProposalDetailVehicleResponseDTO;
import com.carbon.refactor.application.dto.ProposalResponseDTO;
import com.carbon.refactor.application.dto.response.PossibleNextStatusesResponseDTO.StatusDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalCommissionResponseDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalDetailVehicleItemResponseDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalDocumentResponseDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalFupResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning complete proposal data including all related entities.
 * This DTO aggregates all information related to a proposal in a single object.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompleteProposalResponseDTO {
    
    private ProposalResponseDTO proposal;
    private ProposalDetailResponseDTO proposalDetail;
    private List<ProposalDetailVehicleResponseDTO> proposalDetailVehicles;
    private List<ProposalDetailVehicleItemResponseDTO> proposalDetailVehicleItems;
    private List<ProposalCommissionResponseDTO> proposalCommissions;
    private List<ProposalDocumentResponseDTO> proposalDocuments;
    private List<ProposalFupResponseDTO> proposalFups;
    
    /**
     * List of possible next statuses for the proposal.
     */
    private List<StatusDTO> possibleNextStatuses;
}
