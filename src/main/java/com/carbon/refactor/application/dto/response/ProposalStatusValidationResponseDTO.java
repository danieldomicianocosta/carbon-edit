package com.carbon.refactor.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for the response of a proposal status validation.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalStatusValidationResponseDTO {
    
    /**
     * Whether the status transition is valid.
     */
    private boolean valid;
    
    /**
     * The current status description.
     */
    private String currentStatusDescription;
    
    /**
     * The new status description.
     */
    private String newStatusDescription;
    
    /**
     * A message explaining the validation result.
     */
    private String message;
}
