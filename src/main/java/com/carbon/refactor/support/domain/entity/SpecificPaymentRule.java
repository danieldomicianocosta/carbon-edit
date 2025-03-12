package com.carbon.refactor.support.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the specific_payment_rule table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecificPaymentRule {
    private Integer id;
    private Integer specificPaymentMethodId;
    private Integer paymentRuleId;
    
    // Relationships
    private SpecificPaymentMethod specificPaymentMethod;
    private PaymentRule paymentRule;
}
