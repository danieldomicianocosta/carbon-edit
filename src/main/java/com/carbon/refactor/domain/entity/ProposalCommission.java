package com.carbon.refactor.domain.entity;

import java.time.LocalDateTime;
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
 * Entity representing a proposal commission in the system.
 */
@Entity
@Table(name = "proposal_commission")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalCommission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pcm_id")
    private Integer id;
    
    @Column(name = "cmn_type_cla_id", nullable = false)
    private Integer commissionedTypeClassifierId;
    
    @Column(name = "per_id", nullable = false)
    private Integer personId;
    
    @Column(name = "person_type_cla_id")
    private Integer personTypeClassifierId;
    
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    
    @Column(name = "value", nullable = false, precision = 13, scale = 2)
    private BigDecimal value;
    
    @Column(name = "notes", columnDefinition = "text")
    private String notes;
    
    @Column(name = "cmt_id")
    private Integer commissionTypeId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ppd_id", nullable = false)
    private ProposalDetail proposalDetail;
    
    @Column(name = "act_id")
    private Integer accountId;
    
    @Column(name = "payment_cla_id", nullable = false)
    private Integer paymentClassifierId;
    
    @Column(name = "bank_data", length = 255)
    private String bankData;
    
    @Column(name = "number_nf", length = 100)
    private String numberNf;
}
