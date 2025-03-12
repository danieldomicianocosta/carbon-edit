package com.carbon.refactor.support.domain.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the commission_type table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommissionType {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal defaultMaximumValue;
    private boolean manufacturer;
    private boolean overprice;
}
