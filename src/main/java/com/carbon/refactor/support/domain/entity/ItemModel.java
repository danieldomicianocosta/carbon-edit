package com.carbon.refactor.support.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the item_model table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {
    private Integer id;
    private Integer modelYearStart;
    private Integer modelYearEnd;
    private Integer itemId;
    private Integer modelId;
    private Integer additionalTerm;
    private Boolean factorySettings;
    private Integer mandatoryClaId;
    
    // Relationships
    private Item item;
    private Model model;
}
