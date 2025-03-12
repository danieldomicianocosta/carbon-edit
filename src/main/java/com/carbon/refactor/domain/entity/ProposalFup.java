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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a proposal follow-up in the system.
 */
@Entity
@Table(name = "proposal_fup")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalFup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pfp_id")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pps_id", nullable = false)
    private Proposal proposal;
    
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    
    @Column(name = "media_cla_id", nullable = false)
    private Integer mediaClassifierId;
    
    @Column(name = "person", nullable = false, length = 150)
    private String person;
    
    @Column(name = "comment", length = 1000)
    private String comment;
    
    @Column(name = "fup_type_cla_id")
    private Integer followUpTypeClassifierId;
    
    @Column(name = "usr_id")
    private Integer userId;
}
