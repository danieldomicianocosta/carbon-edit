package com.carbon.refactor.infrastructure.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for receiving proposal detail vehicle item data from clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDetailVehicleItemRequestDTO {
    
    @NotNull(message = "Amount discount is required")
    @PositiveOrZero(message = "Amount discount must be positive or zero")
    private BigDecimal amountDiscount;
    
    @NotNull(message = "Percent discount is required")
    @PositiveOrZero(message = "Percent discount must be positive or zero")
    private BigDecimal percentDiscount;
    
    @NotNull(message = "Final price is required")
    @PositiveOrZero(message = "Final price must be positive or zero")
    private BigDecimal finalPrice;
    
    @NotNull(message = "Table price tax is required")
    @PositiveOrZero(message = "Table price tax must be positive")
    private BigDecimal tablePriceTax;
    
    @NotNull(message = "For free flag is required")
    private Boolean forFree;
    
    @NotNull(message = "Proposal detail vehicle ID is required")
    @Positive(message = "Proposal detail vehicle ID must be positive")
    private Integer proposalDetailVehicleId;
    
    @NotNull(message = "Seller ID is required")
    @Positive(message = "Seller ID must be positive")
    private Integer sellerId;
    
    private Integer priceItemId;
    
    private Integer priceItemModelId;
    
    @NotNull(message = "Amendment is required")
    @PositiveOrZero(message = "Amendment must be positive or zero")
    private Integer amendment;
    
    @NotNull(message = "Immediate delivery flag is required")
    private Boolean immediateDelivery;
}
