package com.carbon.refactor.domain.entity;

import java.math.BigDecimal;

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
 * Entity representing a proposal detail vehicle item in the system.
 */
@Entity
@Table(name = "proposal_detail_vehicle_item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDetailVehicleItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pdvi_id")
    private Integer id;
    
    @Column(name = "amount_discount", nullable = false, precision = 13, scale = 2, columnDefinition = "decimal(13,2) default 0.00")
    private BigDecimal amountDiscount;
    
    @Column(name = "percent_discount", nullable = false, precision = 3, scale = 2, columnDefinition = "decimal(3,2) default 0.00")
    private BigDecimal percentDiscount;
    
    @Column(name = "final_price", nullable = false, precision = 13, scale = 2, columnDefinition = "decimal(13,2) default 0.00")
    private BigDecimal finalPrice;
    
    @Column(name = "table_price_tax", nullable = false, precision = 13, scale = 2)
    private BigDecimal tablePriceTax;
    
    @Column(name = "for_free", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean forFree;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pdv_id", nullable = false)
    private ProposalDetailVehicle proposalDetailVehicle;
    
    @Column(name = "seller_id", nullable = false)
    private Integer sellerId;
    
    @Column(name = "pci_id")
    private Integer priceItemId;
    
    @Column(name = "pim_id")
    private Integer priceItemModelId;
    
    @Column(name = "amendment", nullable = false, columnDefinition = "int default 0")
    private Integer amendment;
    
    @Column(name = "immediate_delivery", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean immediateDelivery;
}
