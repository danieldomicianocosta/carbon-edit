package com.carbon.refactor.support.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the seller table.
 */
@Entity
@Table(name = "seller")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
    @Id
    private Integer id;
    
    @Column(name = "person_id")
    private Integer personId;
    
    @Column(name = "job_id")
    private Integer jobId;
    
    private Boolean active;
    
    @Column(name = "technical_assistance")
    private Boolean technicalAssistance;
    
    @Lob
    @Column(name = "img_seller")
    private byte[] imgSeller;
    
    // Relationships
    @Transient // Replace with actual Person entity when available
    private Object person;
    
    @Transient // Assuming Job entity is not yet properly defined
    private Object job;
}
