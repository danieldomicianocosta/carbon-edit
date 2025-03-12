package com.carbon.refactor.support.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the price_item table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceItem {
    private Integer id;
    private BigDecimal price;
    private Integer itemId;
    private Integer priceListId;
    private boolean forFree;
    private Integer userIdCreate;
    private Integer userIdDelete;
    private LocalDateTime createDate;
    private LocalDateTime deleteDate;
    
    // Relationships
    private Item item;
    private PriceList priceList;
}
