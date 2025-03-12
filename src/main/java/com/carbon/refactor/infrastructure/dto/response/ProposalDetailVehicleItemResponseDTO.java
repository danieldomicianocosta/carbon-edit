package com.carbon.refactor.infrastructure.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning proposal detail vehicle item data to clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDetailVehicleItemResponseDTO {
    
    private Integer id;
    private BigDecimal amountDiscount;
    private BigDecimal percentDiscount;
    private BigDecimal finalPrice;
    private BigDecimal tablePriceTax;
    private Boolean forFree;
    private Integer proposalDetailVehicleId;
    private Integer sellerId;
    private Integer priceItemId;
    private Integer priceItemModelId;
    private Integer amendment;
    private Boolean immediateDelivery;
}
