package com.carbon.refactor.support.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the brand table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    private Integer id;
    private String name;
    private boolean active;
    private Integer partnerId;
    
    // Relationship
    private Partner partner;
}
