package com.carbon.refactor.support.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the specific_payment_event table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecificPaymentEvent {
    private Integer id;
    private Integer specificPaymentConditionId;
    private Integer classifierId;
    
    // Relationships
    private SpecificPaymentCondition specificPaymentCondition;
}
