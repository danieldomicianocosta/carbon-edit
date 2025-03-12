package com.carbon.refactor.application.dto.request;

import java.util.List;

import com.carbon.refactor.application.dto.ProposalDetailRequestDTO;
import com.carbon.refactor.application.dto.ProposalDetailVehicleRequestDTO;
import com.carbon.refactor.application.dto.ProposalRequestDTO;
import com.carbon.refactor.infrastructure.dto.request.ProposalCommissionRequestDTO;
import com.carbon.refactor.infrastructure.dto.request.ProposalDetailVehicleItemRequestDTO;
import com.carbon.refactor.infrastructure.dto.request.ProposalDocumentRequestDTO;
import com.carbon.refactor.infrastructure.dto.request.ProposalFupRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for receiving complete proposal data including all related entities for update.
 * This DTO aggregates all information related to a proposal in a single object.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompleteProposalRequestDTO {
    
    @NotNull(message = "Proposal is required")
    @Valid
    private ProposalRequestDTO proposal;
    
    @Valid
    private ProposalDetailRequestDTO proposalDetail;
    
    @Valid
    private List<ProposalDetailVehicleRequestDTO> proposalDetailVehicles;
    
    @Valid
    private List<ProposalDetailVehicleItemRequestDTO> proposalDetailVehicleItems;
    
    @Valid
    private List<ProposalCommissionRequestDTO> proposalCommissions;
    
    @Valid
    private List<ProposalDocumentRequestDTO> proposalDocuments;
    
    @Valid
    private List<ProposalFupRequestDTO> proposalFups;
}
