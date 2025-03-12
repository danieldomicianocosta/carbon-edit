package com.carbon.refactor.support.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the access_list table.
 */
@Entity
@Table(name = "access_list")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessList {
    @Id
    private Integer id;
    private String name;
    private Integer menuId;
    
    // Relationship - assuming there's a Menu entity
    @Transient // Mark as transient until the actual Menu entity is available
    private Object menu; // Replace with actual Menu entity when available
}
