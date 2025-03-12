package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.SpecificPaymentMethod;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on SpecificPaymentMethod entities.
 */
public interface SpecificPaymentMethodService extends ReadOnlyService<SpecificPaymentMethod, Integer> {
    
    /**
     * Find specific payment methods by payment method ID.
     * 
     * @param paymentMethodId The payment method ID
     * @return A list of specific payment methods for the given payment method
     */
    List<SpecificPaymentMethod> findByPaymentMethodId(Integer paymentMethodId);
    
    /**
     * Find specific payment methods by specific payment condition ID.
     * 
     * @param specificPaymentConditionId The specific payment condition ID
     * @return A list of specific payment methods for the given specific payment condition
     */
    List<SpecificPaymentMethod> findBySpecificPaymentConditionId(Integer specificPaymentConditionId);
    
    /**
     * Find specific payment methods by payment method stage classifier ID.
     * 
     * @param paymentMethodStageClassifierId The payment method stage classifier ID
     * @return A list of specific payment methods with the given payment method stage classifier
     */
    List<SpecificPaymentMethod> findByPaymentMethodStageClassifierId(Integer paymentMethodStageClassifierId);
    
    /**
     * Find a specific payment method by payment method ID and specific payment condition ID.
     * 
     * @param paymentMethodId The payment method ID
     * @param specificPaymentConditionId The specific payment condition ID
     * @return An Optional containing the specific payment method if found, or empty if not found
     */
    Optional<SpecificPaymentMethod> findByPaymentMethodIdAndSpecificPaymentConditionId(
            Integer paymentMethodId, Integer specificPaymentConditionId);
    
    /**
     * Find a specific payment method by payment method ID, specific payment condition ID, and payment method stage classifier ID.
     * 
     * @param paymentMethodId The payment method ID
     * @param specificPaymentConditionId The specific payment condition ID
     * @param paymentMethodStageClassifierId The payment method stage classifier ID
     * @return An Optional containing the specific payment method if found, or empty if not found
     */
    Optional<SpecificPaymentMethod> findByPaymentMethodIdAndSpecificPaymentConditionIdAndPaymentMethodStageClassifierId(
            Integer paymentMethodId, Integer specificPaymentConditionId, Integer paymentMethodStageClassifierId);
}
