package com.carbon.refactor.domain.service;

import java.util.List;

import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.domain.rule.ProposalStatusRule;

/**
 * Service for handling proposal status transitions.
 */
public interface ProposalStatusService {
    
    /**
     * Validates if a status transition is valid according to the proposal state machine.
     *
     * @param currentStatus The current status of the proposal
     * @param newStatus The new status to transition to
     * @return true if the transition is valid, false otherwise
     */
    boolean isValidStatusTransition(Integer currentStatus, Integer newStatus);
    
    /**
     * Registers a rule to be executed during a status transition.
     *
     * @param rule The rule to register
     */
    void registerRule(ProposalStatusRule rule);
    
    /**
     * Executes all applicable rules for a status transition.
     *
     * @param proposal The complete proposal data
     * @param currentStatus The current status of the proposal
     * @param newStatus The new status to transition to
     * @return true if all rules executed successfully, false otherwise
     */
    boolean executeRules(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus);
    
    /**
     * Gets the error message from the first failed rule for a status transition.
     * If no rule provides a custom error message, a default message is returned.
     *
     * @param proposal The complete proposal data
     * @param currentStatus The current status of the proposal
     * @param newStatus The new status to transition to
     * @return The error message, or null if all rules passed
     */
    String getErrorMessage(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus);
    
    /**
     * Returns a list of possible next statuses for a given current status.
     *
     * @param currentStatus The current status of the proposal
     * @return A list of possible next statuses
     */
    List<Integer> getPossibleNextStatuses(Integer currentStatus);
}
