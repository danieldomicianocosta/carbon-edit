package com.carbon.refactor.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.carbon.refactor.support.domain.entity.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a proposal document in the system.
 * This is a join table between Proposal and Document with a composite primary key.
 */
@Entity
@Table(name = "proposal_document")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProposalDocumentId.class)
public class ProposalDocument {
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pps_id", nullable = false)
    private Proposal proposal;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id", nullable = false)
    private Document document;
}
