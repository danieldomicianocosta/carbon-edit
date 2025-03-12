package com.carbon.refactor.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.domain.entity.ProposalCommission;
import com.carbon.refactor.domain.exception.EntityNotFoundException;
import com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException;
import com.carbon.refactor.domain.repository.ProposalCommissionRepository;
import com.carbon.refactor.domain.service.ProposalCommissionService;
import com.carbon.refactor.domain.service.ProposalDetailService;

/**
 * Implementation of the ProposalCommissionService interface.
 */
@Service
@RequiredArgsConstructor
public class ProposalCommissionServiceImpl implements ProposalCommissionService {
    
    private final ProposalCommissionRepository proposalCommissionRepository;
    private final ProposalDetailService proposalDetailService;
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalCommission> findAll() {
        return proposalCommissionRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProposalCommission findById(Integer id) {
        return proposalCommissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProposalCommission", id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalCommission> findByProposalDetailId(Integer proposalDetailId) {
        return proposalCommissionRepository.findByProposalDetailId(proposalDetailId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalCommission> findByPersonId(Integer personId) {
        return proposalCommissionRepository.findByPersonId(personId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ProposalCommission> findByProposalDetailIdAndPersonId(Integer proposalDetailId, Integer personId) {
        return proposalCommissionRepository.findByProposalDetailIdAndPersonId(proposalDetailId, personId);
    }
    
    @Override
    @Transactional
    public ProposalCommission create(ProposalCommission proposalCommission) {
        try {
            validateForeignKeys(proposalCommission);
            return proposalCommissionRepository.save(proposalCommission);
        } catch (DataIntegrityViolationException e) {
            handleDataIntegrityViolation(e);
            // This line will never be reached, but is needed to satisfy the compiler
            return null;
        }
    }
    
    @Override
    @Transactional
    public ProposalCommission update(Integer id, ProposalCommission proposalCommission) {
        if (!proposalCommissionRepository.existsById(id)) {
            throw new EntityNotFoundException("ProposalCommission", id);
        }
        
        try {
            validateForeignKeys(proposalCommission);
            proposalCommission.setId(id);
            return proposalCommissionRepository.save(proposalCommission);
        } catch (DataIntegrityViolationException e) {
            handleDataIntegrityViolation(e);
            // This line will never be reached, but is needed to satisfy the compiler
            return null;
        }
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        if (!proposalCommissionRepository.existsById(id)) {
            throw new EntityNotFoundException("ProposalCommission", id);
        }
        
        proposalCommissionRepository.deleteById(id);
    }
    
    @Override
    @Transactional
    public void deleteByProposalDetailId(Integer proposalDetailId) {
        proposalCommissionRepository.deleteByProposalDetailId(proposalDetailId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return proposalCommissionRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByProposalDetailId(Integer proposalDetailId) {
        return proposalCommissionRepository.existsByProposalDetailId(proposalDetailId);
    }
    
    /**
     * Validates that all foreign keys in the proposal commission exist.
     * 
     * @param proposalCommission The proposal commission to validate
     * @throws ForeignKeyConstraintViolationException if any foreign key constraints are violated
     */
    private void validateForeignKeys(ProposalCommission proposalCommission) {
        // Validate proposal detail exists
        if (proposalCommission.getProposalDetail() != null) {
            Integer proposalDetailId = proposalCommission.getProposalDetail().getId();
            if (!proposalDetailService.existsById(proposalDetailId)) {
                throw new ForeignKeyConstraintViolationException("ProposalDetail", "ppd_id", proposalDetailId);
            }
        } else {
            throw new IllegalArgumentException("Proposal detail is required");
        }
        
        // Validate commissioned type classifier exists
        if (proposalCommission.getCommissionedTypeClassifierId() == null) {
            throw new IllegalArgumentException("Commissioned type classifier is required");
        }
        
        // Validate person exists
        if (proposalCommission.getPersonId() == null) {
            throw new IllegalArgumentException("Person is required");
        }
        
        // Validate payment classifier exists
        if (proposalCommission.getPaymentClassifierId() == null) {
            throw new IllegalArgumentException("Payment classifier is required");
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
        
        if (message.contains("fk_comission_person")) {
            throw new ForeignKeyConstraintViolationException("Person", "per_id", "Unknown");
        } else if (message.contains("fk_comission_proposal_bank_account")) {
            throw new ForeignKeyConstraintViolationException("BankAccount", "act_id", "Unknown");
        } else if (message.contains("fk_comission_proposal_detail")) {
            throw new ForeignKeyConstraintViolationException("ProposalDetail", "ppd_id", "Unknown");
        } else if (message.contains("fk_commission_payment_classifier")) {
            throw new ForeignKeyConstraintViolationException("Classifier", "payment_cla_id", "Unknown");
        } else if (message.contains("fk_proposal_commission_commission_type")) {
            throw new ForeignKeyConstraintViolationException("CommissionType", "cmt_id", "Unknown");
        } else {
            throw new ForeignKeyConstraintViolationException("Unknown", "Unknown", "Unknown");
        }
    }
}
