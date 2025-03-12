package com.carbon.refactor.domain.service;

import java.util.List;
import java.util.Optional;

import com.carbon.refactor.domain.entity.ProposalCommission;

/**
 * Service interface for managing ProposalCommission entities.
 */
public interface ProposalCommissionService {
    
    /**
     * Find all proposal commissions.
     * 
     * @return A list of all proposal commissions
     */
    List<ProposalCommission> findAll();
    
    /**
     * Find a proposal commission by ID.
     * 
     * @param id The ID to search for
     * @return The proposal commission with the given ID
     * @throws com.carbon.refactor.domain.exception.EntityNotFoundException if no proposal commission is found with the given ID
     */
    ProposalCommission findById(Integer id);
    
    /**
     * Find all proposal commissions by proposal detail ID.
     * 
     * @param proposalDetailId The proposal detail ID to search for
     * @return A list of proposal commissions for the given proposal detail
     */
    List<ProposalCommission> findByProposalDetailId(Integer proposalDetailId);
    
    /**
     * Find all proposal commissions by person ID.
     * 
     * @param personId The person ID to search for
     * @return A list of proposal commissions for the given person
     */
    List<ProposalCommission> findByPersonId(Integer personId);
    
    /**
     * Find a proposal commission by proposal detail ID and person ID.
     * 
     * @param proposalDetailId The proposal detail ID to search for
     * @param personId The person ID to search for
     * @return An Optional containing the proposal commission if found, or empty if not found
     */
    Optional<ProposalCommission> findByProposalDetailIdAndPersonId(Integer proposalDetailId, Integer personId);
    
    /**
     * Create a new proposal commission.
     * 
     * @param proposalCommission The proposal commission to create
     * @return The created proposal commission
     * @throws com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException if any foreign key constraints are violated
     */
    ProposalCommission create(ProposalCommission proposalCommission);
    
    /**
     * Update an existing proposal commission.
     * 
     * @param id The ID of the proposal commission to update
     * @param proposalCommission The updated proposal commission data
     * @return The updated proposal commission
     * @throws com.carbon.refactor.domain.exception.EntityNotFoundException if no proposal commission is found with the given ID
     * @throws com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException if any foreign key constraints are violated
     */
    ProposalCommission update(Integer id, ProposalCommission proposalCommission);
    
    /**
     * Delete a proposal commission by ID.
     * 
     * @param id The ID of the proposal commission to delete
     * @throws com.carbon.refactor.domain.exception.EntityNotFoundException if no proposal commission is found with the given ID
     */
    void delete(Integer id);
    
    /**
     * Delete all proposal commissions by proposal detail ID.
     * 
     * @param proposalDetailId The proposal detail ID to delete commissions for
     */
    void deleteByProposalDetailId(Integer proposalDetailId);
    
    /**
     * Check if a proposal commission exists with the given ID.
     * 
     * @param id The ID to check
     * @return true if a proposal commission exists with the given ID, false otherwise
     */
    boolean existsById(Integer id);
    
    /**
     * Check if any proposal commissions exist for the given proposal detail ID.
     * 
     * @param proposalDetailId The proposal detail ID to check
     * @return true if any proposal commissions exist, false otherwise
     */
    boolean existsByProposalDetailId(Integer proposalDetailId);
}
