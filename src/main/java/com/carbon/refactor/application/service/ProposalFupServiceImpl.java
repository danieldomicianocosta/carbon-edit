package com.carbon.refactor.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.domain.entity.ProposalFup;
import com.carbon.refactor.domain.exception.EntityNotFoundException;
import com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException;
import com.carbon.refactor.domain.repository.ProposalFupRepository;
import com.carbon.refactor.domain.service.ProposalFupService;
import com.carbon.refactor.domain.service.ProposalService;

/**
 * Implementation of the ProposalFupService interface.
 */
@Service
@RequiredArgsConstructor
public class ProposalFupServiceImpl implements ProposalFupService {
    
    private final ProposalFupRepository proposalFupRepository;
    private final ProposalService proposalService;
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalFup> findAll() {
        return proposalFupRepository.findAllWithProposal();
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProposalFup findById(Integer id) {
        return proposalFupRepository.findByIdWithProposal(id)
                .orElseThrow(() -> new EntityNotFoundException("ProposalFup", id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalFup> findByProposalId(Integer proposalId) {
        return proposalFupRepository.findByProposalIdWithProposal(proposalId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalFup> findByProposalIdOrderByDateDesc(Integer proposalId) {
        return proposalFupRepository.findByProposalIdOrderByDateDescWithProposal(proposalId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalFup> findByUserId(Integer userId) {
        return proposalFupRepository.findByUserIdWithProposal(userId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalFup> findByMediaClassifierId(Integer mediaClassifierId) {
        return proposalFupRepository.findByMediaClassifierIdWithProposal(mediaClassifierId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalFup> findByFollowUpTypeClassifierId(Integer followUpTypeClassifierId) {
        return proposalFupRepository.findByFollowUpTypeClassifierIdWithProposal(followUpTypeClassifierId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalFup> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return proposalFupRepository.findByDateBetweenWithProposal(startDate, endDate);
    }
    
    @Override
    @Transactional
    public ProposalFup create(ProposalFup proposalFup) {
        try {
            validateForeignKeys(proposalFup);
            
            // Set the date to now if not provided
            if (proposalFup.getDate() == null) {
                proposalFup.setDate(LocalDateTime.now());
            }
            
            return proposalFupRepository.save(proposalFup);
        } catch (DataIntegrityViolationException e) {
            handleDataIntegrityViolation(e);
            // This line will never be reached, but is needed to satisfy the compiler
            return null;
        }
    }
    
    @Override
    @Transactional
    public ProposalFup update(Integer id, ProposalFup proposalFup) {
        if (!proposalFupRepository.existsById(id)) {
            throw new EntityNotFoundException("ProposalFup", id);
        }
        
        try {
            validateForeignKeys(proposalFup);
            proposalFup.setId(id);
            return proposalFupRepository.save(proposalFup);
        } catch (DataIntegrityViolationException e) {
            handleDataIntegrityViolation(e);
            // This line will never be reached, but is needed to satisfy the compiler
            return null;
        }
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        if (!proposalFupRepository.existsById(id)) {
            throw new EntityNotFoundException("ProposalFup", id);
        }
        
        proposalFupRepository.deleteById(id);
    }
    
    @Override
    @Transactional
    public void deleteByProposalId(Integer proposalId) {
        proposalFupRepository.deleteByProposalId(proposalId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return proposalFupRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByProposalId(Integer proposalId) {
        return proposalFupRepository.existsByProposalId(proposalId);
    }
    
    /**
     * Validates that all foreign keys in the proposal follow-up exist.
     * 
     * @param proposalFup The proposal follow-up to validate
     * @throws ForeignKeyConstraintViolationException if any foreign key constraints are violated
     */
    private void validateForeignKeys(ProposalFup proposalFup) {
        // Validate proposal exists
        if (proposalFup.getProposal() != null) {
            Integer proposalId = proposalFup.getProposal().getId();
            if (!proposalService.existsById(proposalId)) {
                throw new ForeignKeyConstraintViolationException("Proposal", "pps_id", proposalId);
            }
        } else {
            throw new IllegalArgumentException("Proposal is required");
        }
        
        // Validate media classifier exists
        if (proposalFup.getMediaClassifierId() == null) {
            throw new IllegalArgumentException("Media classifier is required");
        }
        
        // Validate person is not null or empty
        if (proposalFup.getPerson() == null || proposalFup.getPerson().trim().isEmpty()) {
            throw new IllegalArgumentException("Person is required");
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
        
        if (message.contains("fk_proposal_fup_proposal1")) {
            throw new ForeignKeyConstraintViolationException("Proposal", "pps_id", "Unknown");
        } else if (message.contains("fk_proposal_fup_media")) {
            throw new ForeignKeyConstraintViolationException("Classifier", "media_cla_id", "Unknown");
        } else if (message.contains("fk_fup_type_cla_id")) {
            throw new ForeignKeyConstraintViolationException("Classifier", "fup_type_cla_id", "Unknown");
        } else if (message.contains("fk_proposal_fup_usr")) {
            throw new ForeignKeyConstraintViolationException("User", "usr_id", "Unknown");
        } else {
            throw new ForeignKeyConstraintViolationException("Unknown", "Unknown", "Unknown");
        }
    }
}
