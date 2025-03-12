package com.carbon.refactor.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import com.carbon.refactor.domain.entity.ProposalFup;

/**
 * Service interface for managing ProposalFup entities.
 */
public interface ProposalFupService {
    
    /**
     * Find all proposal follow-ups.
     * 
     * @return A list of all proposal follow-ups
     */
    List<ProposalFup> findAll();
    
    /**
     * Find a proposal follow-up by ID.
     * 
     * @param id The ID to search for
     * @return The proposal follow-up with the given ID
     * @throws com.carbon.refactor.domain.exception.EntityNotFoundException if no proposal follow-up is found with the given ID
     */
    ProposalFup findById(Integer id);
    
    /**
     * Find all proposal follow-ups by proposal ID.
     * 
     * @param proposalId The proposal ID to search for
     * @return A list of proposal follow-ups for the given proposal
     */
    List<ProposalFup> findByProposalId(Integer proposalId);
    
    /**
     * Find all proposal follow-ups by proposal ID, ordered by date descending.
     * 
     * @param proposalId The proposal ID to search for
     * @return A list of proposal follow-ups for the given proposal, ordered by date descending
     */
    List<ProposalFup> findByProposalIdOrderByDateDesc(Integer proposalId);
    
    /**
     * Find all proposal follow-ups by user ID.
     * 
     * @param userId The user ID to search for
     * @return A list of proposal follow-ups for the given user
     */
    List<ProposalFup> findByUserId(Integer userId);
    
    /**
     * Find all proposal follow-ups by media classifier ID.
     * 
     * @param mediaClassifierId The media classifier ID to search for
     * @return A list of proposal follow-ups for the given media classifier
     */
    List<ProposalFup> findByMediaClassifierId(Integer mediaClassifierId);
    
    /**
     * Find all proposal follow-ups by follow-up type classifier ID.
     * 
     * @param followUpTypeClassifierId The follow-up type classifier ID to search for
     * @return A list of proposal follow-ups for the given follow-up type classifier
     */
    List<ProposalFup> findByFollowUpTypeClassifierId(Integer followUpTypeClassifierId);
    
    /**
     * Find all proposal follow-ups by date between start date and end date.
     * 
     * @param startDate The start date to search for
     * @param endDate The end date to search for
     * @return A list of proposal follow-ups between the given dates
     */
    List<ProposalFup> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Create a new proposal follow-up.
     * 
     * @param proposalFup The proposal follow-up to create
     * @return The created proposal follow-up
     * @throws com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException if any foreign key constraints are violated
     */
    ProposalFup create(ProposalFup proposalFup);
    
    /**
     * Update an existing proposal follow-up.
     * 
     * @param id The ID of the proposal follow-up to update
     * @param proposalFup The updated proposal follow-up data
     * @return The updated proposal follow-up
     * @throws com.carbon.refactor.domain.exception.EntityNotFoundException if no proposal follow-up is found with the given ID
     * @throws com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException if any foreign key constraints are violated
     */
    ProposalFup update(Integer id, ProposalFup proposalFup);
    
    /**
     * Delete a proposal follow-up by ID.
     * 
     * @param id The ID of the proposal follow-up to delete
     * @throws com.carbon.refactor.domain.exception.EntityNotFoundException if no proposal follow-up is found with the given ID
     */
    void delete(Integer id);
    
    /**
     * Delete all proposal follow-ups by proposal ID.
     * 
     * @param proposalId The proposal ID to delete follow-ups for
     */
    void deleteByProposalId(Integer proposalId);
    
    /**
     * Check if a proposal follow-up exists with the given ID.
     * 
     * @param id The ID to check
     * @return true if a proposal follow-up exists with the given ID, false otherwise
     */
    boolean existsById(Integer id);
    
    /**
     * Check if any proposal follow-ups exist for the given proposal ID.
     * 
     * @param proposalId The proposal ID to check
     * @return true if any proposal follow-ups exist, false otherwise
     */
    boolean existsByProposalId(Integer proposalId);
}
