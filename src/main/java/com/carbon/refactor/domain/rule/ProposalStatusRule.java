package com.carbon.refactor.domain.rule;

import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;

/**
 * Interface for defining rules to be executed during proposal status transitions.
 * Implementations of this interface can define custom logic to be executed
 * when a proposal transitions from one status to another.
 */
public interface ProposalStatusRule {
    
    /**
     * Execute the rule for a proposal status transition.
     *
     * @param proposal The complete proposal data
     * @param currentStatus The current status of the proposal
     * @param newStatus The new status of the proposal
     * @return true if the rule execution was successful, false otherwise
     */
    boolean executeRule(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus);
    
    /**
     * Get the source status for which this rule applies.
     * 
     * @return The source status ID
     */
    Integer getSourceStatus();
    
    /**
     * Get the target status for which this rule applies.
     * 
     * @return The target status ID
     */
    Integer getTargetStatus();
    
    /**
     * Get a custom error message for when the rule execution fails.
     * This message will be used in the validation response to provide
     * more context about why the status transition was rejected.
     * 
     * @return The custom error message, or null to use a default message
     */
    default String getErrorMessage() {
        return null;
    }
}
