package com.carbon.refactor.infrastructure.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for receiving proposal document data from clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDocumentRequestDTO {
    
    @NotNull(message = "Proposal ID is required")
    @Positive(message = "Proposal ID must be positive")
    private Integer proposalId;
    
    @NotNull(message = "Document ID is required")
    @Positive(message = "Document ID must be positive")
    private Integer documentId;
}
