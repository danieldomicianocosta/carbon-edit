package com.carbon.refactor.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.domain.entity.ProposalDocument;
import com.carbon.refactor.domain.entity.ProposalDocumentId;

/**
 * Repository interface for ProposalDocument entities.
 */
@Repository
public interface ProposalDocumentRepository extends JpaRepository<ProposalDocument, ProposalDocumentId> {
    
    /**
     * Find all proposal documents by proposal ID.
     * 
     * @param proposalId The proposal ID to search for
     * @return A list of proposal documents for the given proposal
     */
    List<ProposalDocument> findByProposalId(Integer proposalId);
    
    /**
     * Find all proposal documents by document ID.
     * 
     * @param documentId The document ID to search for
     * @return A list of proposal documents for the given document
     */
    List<ProposalDocument> findByDocumentId(Integer documentId);
    
    /**
     * Delete all proposal documents by proposal ID.
     * 
     * @param proposalId The proposal ID to delete documents for
     */
    void deleteByProposalId(Integer proposalId);
    
    /**
     * Delete all proposal documents by document ID.
     * 
     * @param documentId The document ID to delete proposal documents for
     */
    void deleteByDocumentId(Integer documentId);
    
    /**
     * Check if a proposal document exists with the given proposal ID and document ID.
     * 
     * @param proposalId The proposal ID to check
     * @param documentId The document ID to check
     * @return true if a proposal document exists with the given proposal ID and document ID, false otherwise
     */
    boolean existsByProposalIdAndDocumentId(Integer proposalId, Integer documentId);
    
    /**
     * Check if any proposal documents exist for the given proposal ID.
     * 
     * @param proposalId The proposal ID to check
     * @return true if any proposal documents exist, false otherwise
     */
    boolean existsByProposalId(Integer proposalId);
    
    /**
     * Check if any proposal documents exist for the given document ID.
     * 
     * @param documentId The document ID to check
     * @return true if any proposal documents exist, false otherwise
     */
    boolean existsByDocumentId(Integer documentId);
    
    // Eager loading methods
    
    /**
     * Find all proposal documents with their proposals and documents eagerly loaded.
     * 
     * @return A list of all proposal documents with their proposals and documents eagerly loaded
     */
    @Query("SELECT pd FROM ProposalDocument pd JOIN FETCH pd.proposal JOIN FETCH pd.document")
    List<ProposalDocument> findAllWithProposal();
    
    /**
     * Find a proposal document by proposal ID and document ID with its proposal and document eagerly loaded.
     * 
     * @param proposalId The proposal ID to search for
     * @param documentId The document ID to search for
     * @return An Optional containing the proposal document if found, or empty if not found
     */
    @Query("SELECT pd FROM ProposalDocument pd JOIN FETCH pd.proposal JOIN FETCH pd.document WHERE pd.proposal.id = :proposalId AND pd.document.id = :documentId")
    Optional<ProposalDocument> findByProposalIdAndDocumentIdWithProposal(@Param("proposalId") Integer proposalId, @Param("documentId") Integer documentId);
    
    /**
     * Find all proposal documents by proposal ID with their proposals and documents eagerly loaded.
     * 
     * @param proposalId The proposal ID to search for
     * @return A list of proposal documents for the given proposal with their proposals and documents eagerly loaded
     */
    @Query("SELECT pd FROM ProposalDocument pd JOIN FETCH pd.proposal JOIN FETCH pd.document WHERE pd.proposal.id = :proposalId")
    List<ProposalDocument> findByProposalIdWithProposal(@Param("proposalId") Integer proposalId);
    
    /**
     * Find all proposal documents by document ID with their proposals and documents eagerly loaded.
     * 
     * @param documentId The document ID to search for
     * @return A list of proposal documents for the given document with their proposals and documents eagerly loaded
     */
    @Query("SELECT pd FROM ProposalDocument pd JOIN FETCH pd.proposal JOIN FETCH pd.document WHERE pd.document.id = :documentId")
    List<ProposalDocument> findByDocumentIdWithProposal(@Param("documentId") Integer documentId);
}
