package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.ProposalApprovalRule;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on ProposalApprovalRule entities.
 */
public interface ProposalApprovalRuleRepository extends ReadOnlyRepository<ProposalApprovalRule, Integer> {
    
    /**
     * Find proposal approval rules by job ID.
     * 
     * @param jobId The job ID
     * @return A list of proposal approval rules for the given job
     */
    List<ProposalApprovalRule> findByJobId(Integer jobId);
    
    /**
     * Find proposal approval rules by value less than or equal to the given value.
     * 
     * @param value The maximum value
     * @return A list of proposal approval rules with values less than or equal to the given value
     */
    List<ProposalApprovalRule> findByValueLessThanEqual(BigDecimal value);
    
    /**
     * Find proposal approval rules by value greater than or equal to the given value.
     * 
     * @param value The minimum value
     * @return A list of proposal approval rules with values greater than or equal to the given value
     */
    List<ProposalApprovalRule> findByValueGreaterThanEqual(BigDecimal value);
    
    /**
     * Find proposal approval rules that allow immediate delivery.
     * 
     * @return A list of proposal approval rules that allow immediate delivery
     */
    List<ProposalApprovalRule> findByImmediateDeliveryTrue();
    
    /**
     * Find proposal approval rules that do not allow immediate delivery.
     * 
     * @return A list of proposal approval rules that do not allow immediate delivery
     */
    List<ProposalApprovalRule> findByImmediateDeliveryFalse();
    
    /**
     * Find a proposal approval rule by job ID and value.
     * 
     * @param jobId The job ID
     * @param value The value
     * @return An Optional containing the proposal approval rule if found, or empty if not found
     */
    Optional<ProposalApprovalRule> findByJobIdAndValue(Integer jobId, BigDecimal value);
}
