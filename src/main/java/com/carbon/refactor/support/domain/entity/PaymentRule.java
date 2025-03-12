package com.carbon.refactor.support.domain.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the payment_rule table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRule {
    private Integer id;
    private String name;
    private Integer installments;
    private BigDecimal tax;
    private boolean active;
    private boolean preApproved;
    private Integer paymentMethodId;
    private boolean applyTaxProposal;
    private boolean simpleInterest;
    private boolean crmBlindagem;
    private boolean technicalAssistance;
    private String totvsPaymentCode;
    
    // Relationship
    private PaymentMethod paymentMethod;
}
