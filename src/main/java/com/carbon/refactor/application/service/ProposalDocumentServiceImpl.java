package com.carbon.refactor.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.domain.entity.ProposalDocument;
import com.carbon.refactor.domain.entity.ProposalDocumentId;
import com.carbon.refactor.domain.exception.EntityNotFoundException;
import com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException;
import com.carbon.refactor.domain.repository.ProposalDocumentRepository;
import com.carbon.refactor.domain.service.ProposalDocumentService;
import com.carbon.refactor.domain.service.ProposalService;
import com.carbon.refactor.support.domain.service.DocumentService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the ProposalDocumentService interface.
 */
@Service
@RequiredArgsConstructor
public class ProposalDocumentServiceImpl implements ProposalDocumentService {
    
    private final ProposalDocumentRepository proposalDocumentRepository;
    private final ProposalService proposalService;
    private final DocumentService documentService;
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalDocument> findAll() {
        return proposalDocumentRepository.findAllWithProposal();
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProposalDocument findById(ProposalDocumentId id) {
        return proposalDocumentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProposalDocument with id " + id.toString() + " not found"));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ProposalDocument> findByProposalIdAndDocumentId(Integer proposalId, Integer documentId) {
        return proposalDocumentRepository.findByProposalIdAndDocumentIdWithProposal(proposalId, documentId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalDocument> findByProposalId(Integer proposalId) {
        return proposalDocumentRepository.findByProposalIdWithProposal(proposalId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalDocument> findByDocumentId(Integer documentId) {
        return proposalDocumentRepository.findByDocumentIdWithProposal(documentId);
    }
    
    @Override
    @Transactional
    public ProposalDocument create(ProposalDocument proposalDocument) {
        try {
            validateForeignKeys(proposalDocument);
            return proposalDocumentRepository.save(proposalDocument);
        } catch (DataIntegrityViolationException e) {
            handleDataIntegrityViolation(e);
            // This line will never be reached, but is needed to satisfy the compiler
            return null;
        }
    }
    
    @Override
    @Transactional
    public void delete(ProposalDocumentId id) {
        if (!proposalDocumentRepository.existsById(id)) {
            throw new EntityNotFoundException("ProposalDocument with id " + id.toString() + " not found");
        }
        
        proposalDocumentRepository.deleteById(id);
    }
    
    @Override
    @Transactional
    public void deleteByProposalIdAndDocumentId(Integer proposalId, Integer documentId) {
        ProposalDocumentId id = new ProposalDocumentId(proposalId, documentId);
        delete(id);
    }
    
    @Override
    @Transactional
    public void deleteByProposalId(Integer proposalId) {
        proposalDocumentRepository.deleteByProposalId(proposalId);
    }
    
    @Override
    @Transactional
    public void deleteByDocumentId(Integer documentId) {
        proposalDocumentRepository.deleteByDocumentId(documentId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(ProposalDocumentId id) {
        return proposalDocumentRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByProposalIdAndDocumentId(Integer proposalId, Integer documentId) {
        return proposalDocumentRepository.existsByProposalIdAndDocumentId(proposalId, documentId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByProposalId(Integer proposalId) {
        return proposalDocumentRepository.existsByProposalId(proposalId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByDocumentId(Integer documentId) {
        return proposalDocumentRepository.existsByDocumentId(documentId);
    }
    
    /**
     * Validates that all foreign keys in the proposal document exist.
     * 
     * @param proposalDocument The proposal document to validate
     * @throws ForeignKeyConstraintViolationException if any foreign key constraints are violated
     */
    private void validateForeignKeys(ProposalDocument proposalDocument) {
        // Validate proposal exists
        if (proposalDocument.getProposal() != null) {
            Integer proposalId = proposalDocument.getProposal().getId();
            if (!proposalService.existsById(proposalId)) {
                throw new ForeignKeyConstraintViolationException("Proposal", "pps_id", proposalId);
            }
        } else {
            throw new IllegalArgumentException("Proposal is required");
        }
        
        // Validate document exists
        if (proposalDocument.getDocument() != null) {
            Integer documentId = proposalDocument.getDocument().getId();
            if (!documentService.existsById(documentId)) {
                throw new ForeignKeyConstraintViolationException("Document", "doc_id", documentId);
            }
        } else {
            throw new IllegalArgumentException("Document is required");
        }
    }
    
    /**
     * Handles data integrity violations by extracting the constraint name and throwing an appropriate exception.
     * 
     * @param e The DataIntegrityViolationException to handle
     * @throws ForeignKeyConstraintViolationException with details about the violated constraint
     */
    private void handleDataIntegrityViolation(DataIntegrityViolationException e) {
        String message = e.getMessage() != null ? e.getMessage() : "";
        
        if (message.contains("fk_proposal_document_proposal1")) {
            throw new ForeignKeyConstraintViolationException("Proposal", "pps_id", "Unknown");
        } else if (message.contains("fk_proposal_document_document1")) {
            throw new ForeignKeyConstraintViolationException("Document", "doc_id", "Unknown");
        } else {
            throw new ForeignKeyConstraintViolationException("Unknown", "Unknown", "Unknown");
        }
    }
}
