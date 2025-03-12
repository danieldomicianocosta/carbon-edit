package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.SpecificPaymentRule;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on SpecificPaymentRule entities.
 */
public interface SpecificPaymentRuleService extends ReadOnlyService<SpecificPaymentRule, Integer> {
    
    /**
     * Find specific payment rules by specific payment method ID.
     * 
     * @param specificPaymentMethodId The specific payment method ID
     * @return A list of specific payment rules for the given specific payment method
     */
    List<SpecificPaymentRule> findBySpecificPaymentMethodId(Integer specificPaymentMethodId);
    
    /**
     * Find specific payment rules by payment rule ID.
     * 
     * @param paymentRuleId The payment rule ID
     * @return A list of specific payment rules for the given payment rule
     */
    List<SpecificPaymentRule> findByPaymentRuleId(Integer paymentRuleId);
    
    /**
     * Find a specific payment rule by specific payment method ID and payment rule ID.
     * 
     * @param specificPaymentMethodId The specific payment method ID
     * @param paymentRuleId The payment rule ID
     * @return An Optional containing the specific payment rule if found, or empty if not found
     */
    Optional<SpecificPaymentRule> findBySpecificPaymentMethodIdAndPaymentRuleId(
            Integer specificPaymentMethodId, Integer paymentRuleId);
}
