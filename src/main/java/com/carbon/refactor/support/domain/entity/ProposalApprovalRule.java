package com.carbon.refactor.support.domain.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the proposal_approval_rule table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalApprovalRule {
    private Integer id;
    private BigDecimal value;
    private Integer jobId;
    private Boolean immediateDelivery;
    
    // Relationships
    private Job job;
}
