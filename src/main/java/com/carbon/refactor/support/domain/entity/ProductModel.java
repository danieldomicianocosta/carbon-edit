package com.carbon.refactor.support.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the product_model table.
 */
@Entity
@Table(name = "product_model")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prm_id")
    private Integer id;
    
    @Column(name = "has_project", nullable = false)
    private boolean hasProject;
    
    @Column(name = "model_year_start", nullable = false)
    private Integer modelYearStart;
    
    @Column(name = "model_year_end", nullable = false)
    private Integer modelYearEnd;
    
    @Column(name = "manufacture_days", nullable = false)
    private Integer manufactureDays;
    
    @Column(name = "prd_id", nullable = false)
    private Integer productId;
    
    @Column(name = "mdl_id", nullable = false)
    private Integer modelId;
    
    @Column(name = "customer_days", nullable = false)
    private Integer customerDays;
    
    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prd_id", insertable = false, updatable = false)
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mdl_id", insertable = false, updatable = false)
    private Model model;
}
