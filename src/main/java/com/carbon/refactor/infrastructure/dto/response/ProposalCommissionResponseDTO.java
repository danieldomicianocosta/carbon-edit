package com.carbon.refactor.infrastructure.dto.response;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning proposal commission data to clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalCommissionResponseDTO {
    
    private Integer id;
    private Integer commissionedTypeClassifierId;
    private Integer personId;
    private Integer personTypeClassifierId;
    private LocalDateTime dueDate;
    private BigDecimal value;
    private String notes;
    private Integer commissionTypeId;
    private Integer proposalDetailId;
    private Integer accountId;
    private Integer paymentClassifierId;
    private String bankData;
    private String numberNf;
}
