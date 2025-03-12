package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.PaymentRuleInstallment;
import java.util.List;

/**
 * Service interface for read-only operations on PaymentRuleInstallment entities.
 */
public interface PaymentRuleInstallmentService extends ReadOnlyService<PaymentRuleInstallment, Integer> {
    
    /**
     * Find payment rule installments by number of installments.
     * 
     * @param installments The number of installments
     * @return A list of payment rule installments with the given number of installments
     */
    List<PaymentRuleInstallment> findByInstallments(Integer installments);
    
    /**
     * Find payment rule installments by payment.
     * 
     * @param payment The payment
     * @return A list of payment rule installments with the given payment
     */
    List<PaymentRuleInstallment> findByPayment(Integer payment);
    
    /**
     * Find payment rule installments by installments greater than the given value.
     * 
     * @param installments The minimum number of installments
     * @return A list of payment rule installments with installments greater than the given value
     */
    List<PaymentRuleInstallment> findByInstallmentsGreaterThan(Integer installments);
    
    /**
     * Find payment rule installments by installments less than the given value.
     * 
     * @param installments The maximum number of installments
     * @return A list of payment rule installments with installments less than the given value
     */
    List<PaymentRuleInstallment> findByInstallmentsLessThan(Integer installments);
}
