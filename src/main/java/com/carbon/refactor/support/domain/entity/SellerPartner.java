package com.carbon.refactor.support.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the seller_partner table.
 */
@Entity
@Table(name = "seller_partner")
@IdClass(SellerPartnerId.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerPartner {
    @Id
    @Column(name = "seller_id")
    private Integer sellerId;
    
    @Id
    @Column(name = "partner_id")
    private Integer partnerId;
    
    // Relationships
    @ManyToOne
    @JoinColumn(name = "seller_id", insertable = false, updatable = false)
    private Seller seller;
    
    @Transient // Assuming Partner entity is not yet defined
    private Object partner; // Replace with actual Partner entity when available
}
