package com.carbon.refactor.bff.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.carbon.refactor.application.dto.ProposalDetailVehicleResponseDTO;
import com.carbon.refactor.application.dto.response.PossibleNextStatusesResponseDTO.StatusDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning a summary of a proposal.
 * This is an example of a specialized DTO for the BFF layer that includes
 * only the data needed for a specific frontend use case.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalSummaryDTO {
    
    // Basic proposal information
    private Integer id;
    private String proposalNumber;
    private Integer statusClaId;
    private String statusDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Customer information
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    
    // Vehicle summary
    private List<ProposalDetailVehicleResponseDTO> vehicles;
    
    // Status information
    private List<StatusDTO> possibleNextStatuses;
    
    // Summary metrics
    private Integer documentCount;
    private Integer followUpCount;
    private Double totalValue;
    
    /**
     * Static factory method to create a summary from a complete proposal response.
     * This demonstrates how the BFF layer can transform complex DTOs into simpler ones
     * tailored for specific frontend needs.
     * 
     * @param completeProposal The complete proposal response
     * @return A simplified proposal summary
     */
    public static ProposalSummaryDTO fromCompleteProposal(
            com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO completeProposal) {
        
        // Calculate total value from vehicles if available
        Double totalValue = 0.0;
        if (completeProposal.getProposalDetailVehicles() != null) {
            totalValue = completeProposal.getProposalDetailVehicles().stream()
                    .mapToDouble(v -> v.getProductFinalPrice() != null ? v.getProductFinalPrice() : 0.0)
                    .sum();
        }
        
        // Build the summary DTO
        return ProposalSummaryDTO.builder()
                .id(completeProposal.getProposal().getId())
                .proposalNumber(completeProposal.getProposal().getProposalNumber())
                .statusClaId(completeProposal.getProposal().getStatusClaId())
                .statusDescription("Status " + completeProposal.getProposal().getStatusClaId()) // Status description not available in DTO
                .createdAt(completeProposal.getProposal().getCreateDate())
                .updatedAt(completeProposal.getProposal().getLastUpdateDate())
                .customerName(completeProposal.getProposal().getCustomerName())
                .customerEmail(completeProposal.getProposal().getCustomerEmail())
                .customerPhone(completeProposal.getProposal().getCustomerPhone())
                .vehicles(completeProposal.getProposalDetailVehicles())
                .possibleNextStatuses(completeProposal.getPossibleNextStatuses())
                .documentCount(completeProposal.getProposalDocuments() != null ? 
                        completeProposal.getProposalDocuments().size() : 0)
                .followUpCount(completeProposal.getProposalFups() != null ? 
                        completeProposal.getProposalFups().size() : 0)
                .totalValue(totalValue)
                .build();
    }
}
