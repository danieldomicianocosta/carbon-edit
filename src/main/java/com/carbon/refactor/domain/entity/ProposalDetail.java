package com.carbon.refactor.domain.entity;

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
import jakarta.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proposal_detail", uniqueConstraints = {
    @UniqueConstraint(name = "pps_id_UNIQUE", columnNames = {"pps_id"})
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ppd_id")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pps_id", nullable = false)
    private Proposal proposal;
    
    @Column(name = "sel_id", nullable = false)
    private Integer sellerId;
    
    @Column(name = "intern_sale_sel_id")
    private Integer internSaleSellerId;
    
    @Column(name = "chn_id", nullable = false)
    private Integer channelId;
    
    @Column(name = "ptn_id")
    private Integer partnerId;
    
    @Column(name = "usr_id")
    private Integer userId;
    
    @Column(name = "purchase_order_service", length = 50)
    private String purchaseOrderService;
    
    @Column(name = "purchase_order_product", length = 50)
    private String purchaseOrderProduct;
    
    @Column(name = "purchase_order_documentation", length = 50)
    private String purchaseOrderDocumentation;
    
    @Column(name = "internal_comission", nullable = false, columnDefinition = "decimal(13,2) default 0.00")
    private Double internalComission;
    
    @Column(name = "intern_sale_additive")
    private Integer internSaleAdditive;
    
    @Column(name = "seller_additive")
    private Integer sellerAdditive;
    
    @Column(name = "sale_date_additive")
    private LocalDateTime saleDateAdditive;
}
