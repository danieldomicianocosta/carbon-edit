package com.carbon.refactor.support.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the partner_brand table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerBrand {
    private Integer partnerId;
    private Integer brandId;
    
    // Relationships
    private Partner partner;
    private Brand brand;
}
