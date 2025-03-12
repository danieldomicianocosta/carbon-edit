package com.carbon.refactor.support.domain.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the product_model_bonus table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModelBonus {
    private Integer id;
    private Integer brandId;
    private Integer productModelId;
    private BigDecimal bonusValue;
    private boolean isBonusTypePercentage;
    private Integer claPaymentType;
    
    // Relationships
    private Brand brand;
    private ProductModel productModel;
}
