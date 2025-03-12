package com.carbon.refactor.support.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the price_list_partner table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceListPartner {
    private Integer partnerId;
    private Integer priceListId;
    
    // Relationships
    private Partner partner;
    private PriceList priceList;
}
