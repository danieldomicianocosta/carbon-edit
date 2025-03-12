package com.carbon.refactor.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Composite ID class for ProposalDocument entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDocumentId implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer proposal;
    private Integer document;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProposalDocumentId that = (ProposalDocumentId) o;
        return Objects.equals(proposal, that.proposal) &&
               Objects.equals(document, that.document);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(proposal, document);
    }
}
