package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.PaymentRule;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on PaymentRule entities.
 */
public interface PaymentRuleRepository extends ReadOnlyRepository<PaymentRule, Integer> {
    
    /**
     * Find all active payment rules.
     * 
     * @return A list of active payment rules
     */
    List<PaymentRule> findAllActive();
    
    /**
     * Find a payment rule by its name.
     * 
     * @param name The name to search for
     * @return An Optional containing the payment rule if found, or empty if not found
     */
    Optional<PaymentRule> findByName(String name);
    
    /**
     * Find payment rules by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of payment rules with names containing the given text
     */
    List<PaymentRule> findByNameContaining(String name);
    
    /**
     * Find payment rules by payment method ID.
     * 
     * @param paymentMethodId The payment method ID
     * @return A list of payment rules for the given payment method
     */
    List<PaymentRule> findByPaymentMethodId(Integer paymentMethodId);
    
    /**
     * Find payment rules that are pre-approved.
     * 
     * @return A list of pre-approved payment rules
     */
    List<PaymentRule> findByPreApprovedTrue();
    
    /**
     * Find payment rules by number of installments.
     * 
     * @param installments The number of installments
     * @return A list of payment rules with the given number of installments
     */
    List<PaymentRule> findByInstallments(Integer installments);
}
