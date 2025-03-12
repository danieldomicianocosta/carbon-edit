package com.carbon.refactor.support.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the specific_payment_method table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecificPaymentMethod {
    private Integer id;
    private Integer paymentMethodId;
    private Integer specificPaymentConditionId;
    private Integer meioPagamentoEtapaClaId;
    
    // Relationships
    private PaymentMethod paymentMethod;
    private SpecificPaymentCondition specificPaymentCondition;
}
