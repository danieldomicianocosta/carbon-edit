package com.carbon.refactor.bff.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for updating just the status of a proposal.
 * This is an example of a specialized DTO for the BFF layer that simplifies
 * a common operation (status update) by requiring only the necessary fields.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalStatusUpdateRequestDTO {
    
    @NotNull(message = "Status ID is required")
    private Integer statusClaId;
    
    private String comment;
}
