package com.carbon.refactor.infrastructure.dto.request;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for receiving proposal commission data from clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalCommissionRequestDTO {
    
    @NotNull(message = "Commissioned type classifier ID is required")
    @Positive(message = "Commissioned type classifier ID must be positive")
    private Integer commissionedTypeClassifierId;
    
    @NotNull(message = "Person ID is required")
    @Positive(message = "Person ID must be positive")
    private Integer personId;
    
    private Integer personTypeClassifierId;
    
    private LocalDateTime dueDate;
    
    @NotNull(message = "Value is required")
    private BigDecimal value;
    
    private String notes;
    
    private Integer commissionTypeId;
    
    @NotNull(message = "Proposal detail ID is required")
    @Positive(message = "Proposal detail ID must be positive")
    private Integer proposalDetailId;
    
    private Integer accountId;
    
    @NotNull(message = "Payment classifier ID is required")
    @Positive(message = "Payment classifier ID must be positive")
    private Integer paymentClassifierId;
    
    @Size(max = 255, message = "Bank data must be less than 255 characters")
    private String bankData;
    
    @Size(max = 100, message = "Number NF must be less than 100 characters")
    private String numberNf;
}
