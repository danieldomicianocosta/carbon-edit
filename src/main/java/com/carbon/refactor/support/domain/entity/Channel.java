package com.carbon.refactor.support.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the channel table.
 */
@Entity
@Table(name = "channel")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chn_id")
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "active", columnDefinition = "tinyint(1)")
    private boolean active;
    
    @Column(name = "has_partner", columnDefinition = "tinyint(1)")
    private boolean hasPartner;
    
    @Column(name = "has_internal_sale", columnDefinition = "tinyint(1)")
    private boolean hasInternalSale;
    
    @Column(name = "has_bonus_bucket", columnDefinition = "tinyint(1)")
    private boolean hasBonusBucket;
    
    @Column(name = "totvs_nature_code")
    private String totvsNatureCode;
}
