package com.carbon.refactor.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for requesting a proposal status update.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalStatusUpdateRequestDTO {
    
    /**
     * The current status of the proposal.
     */
    private Integer currentStatus;
    
    /**
     * The new status to update to.
     */
    private Integer newStatus;
}
