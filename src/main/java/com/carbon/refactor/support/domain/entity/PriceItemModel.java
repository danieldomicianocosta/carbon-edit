package com.carbon.refactor.support.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the price_item_model table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceItemModel {
    private Integer id;
    private BigDecimal price;
    private boolean allModels;
    private boolean allBrands;
    private Integer priceListId;
    private Integer itemModelId;
    private Integer brandId;
    private Integer itemId;
    private boolean forFree;
    private Integer userIdCreate;
    private Integer userIdDelete;
    private LocalDateTime createDate;
    private LocalDateTime deleteDate;
    
    // Relationships
    private PriceList priceList;
    private ItemModel itemModel;
    private Brand brand;
    private Item item;
}
