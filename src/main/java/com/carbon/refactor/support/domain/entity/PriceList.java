package com.carbon.refactor.support.domain.entity;

import java.time.LocalDate;

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
 * Domain entity representing the price_list table.
 */
@Entity
@Table(name = "price_list")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prl_id")
    private Integer id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "chn_id")
    private Integer channelId;
    
    @Column(name = "all_partners", columnDefinition = "tinyint(1)")
    private boolean allPartners;
    
    // Relationship is commented out until the related entity is properly defined with JPA annotations
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "chn_id", insertable = false, updatable = false)
    // private Channel channel;
}
