package com.carbon.refactor.support.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the payment_rule_installment table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRuleInstallment {
    private Integer id;
    private Integer installments;
    private Integer payment;
}
