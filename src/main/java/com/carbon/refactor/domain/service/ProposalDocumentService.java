package com.carbon.refactor.domain.service;

import java.util.List;
import java.util.Optional;

import com.carbon.refactor.domain.entity.ProposalDocument;
import com.carbon.refactor.domain.entity.ProposalDocumentId;

/**
 * Service interface for managing ProposalDocument entities.
 */
public interface ProposalDocumentService {
    
    /**
     * Find all proposal documents.
     * 
     * @return A list of all proposal documents
     */
    List<ProposalDocument> findAll();
    
    /**
     * Find a proposal document by ID.
     * 
     * @param id The ID to search for
     * @return The proposal document with the given ID
     * @throws com.carbon.refactor.domain.exception.EntityNotFoundException if no proposal document is found with the given ID
     */
    ProposalDocument findById(ProposalDocumentId id);
    
    /**
     * Find a proposal document by proposal ID and document ID.
     * 
     * @param proposalId The proposal ID to search for
     * @param documentId The document ID to search for
     * @return An Optional containing the proposal document if found, or empty if not found
     */
    Optional<ProposalDocument> findByProposalIdAndDocumentId(Integer proposalId, Integer documentId);
    
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
     * Create a new proposal document.
     * 
     * @param proposalDocument The proposal document to create
     * @return The created proposal document
     * @throws com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException if any foreign key constraints are violated
     */
    ProposalDocument create(ProposalDocument proposalDocument);
    
    /**
     * Delete a proposal document by ID.
     * 
     * @param id The ID of the proposal document to delete
     * @throws com.carbon.refactor.domain.exception.EntityNotFoundException if no proposal document is found with the given ID
     */
    void delete(ProposalDocumentId id);
    
    /**
     * Delete a proposal document by proposal ID and document ID.
     * 
     * @param proposalId The proposal ID of the proposal document to delete
     * @param documentId The document ID of the proposal document to delete
     * @throws com.carbon.refactor.domain.exception.EntityNotFoundException if no proposal document is found with the given proposal ID and document ID
     */
    void deleteByProposalIdAndDocumentId(Integer proposalId, Integer documentId);
    
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
     * Check if a proposal document exists with the given ID.
     * 
     * @param id The ID to check
     * @return true if a proposal document exists with the given ID, false otherwise
     */
    boolean existsById(ProposalDocumentId id);
    
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
}
