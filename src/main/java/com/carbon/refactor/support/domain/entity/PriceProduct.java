package com.carbon.refactor.support.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
 * Domain entity representing the price_product table.
 */
@Entity
@Table(name = "price_product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceProduct {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ppr_id")
    private Integer id;
    
    @Column(name = "price", nullable = false, precision = 13, scale = 2)
    private BigDecimal price;
    
    @Column(name = "prl_id", nullable = false)
    private Integer priceListId;
    
    @Column(name = "prm_id", nullable = false)
    private Integer productModelId;
    
    @Column(name = "usr_id_create")
    private Integer userIdCreate;
    
    @Column(name = "usr_id_delete")
    private Integer userIdDelete;
    
    @Column(name = "create_date")
    private LocalDateTime createDate;
    
    @Column(name = "delete_date")
    private LocalDateTime deleteDate;
    
     //Relationships are commented out until the related entities are properly defined
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "prl_id", insertable = false, updatable = false)
     private PriceList priceList;
    
     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prm_id", insertable = false, updatable = false)
    private ProductModel productModel;
}
