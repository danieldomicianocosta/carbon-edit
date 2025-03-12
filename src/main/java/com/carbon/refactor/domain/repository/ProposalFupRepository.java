package com.carbon.refactor.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.domain.entity.ProposalFup;

/**
 * Repository interface for ProposalFup entities.
 */
@Repository
public interface ProposalFupRepository extends JpaRepository<ProposalFup, Integer> {
    
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
     * Delete all proposal follow-ups by proposal ID.
     * 
     * @param proposalId The proposal ID to delete follow-ups for
     */
    void deleteByProposalId(Integer proposalId);
    
    /**
     * Check if any proposal follow-ups exist for the given proposal ID.
     * 
     * @param proposalId The proposal ID to check
     * @return true if any proposal follow-ups exist, false otherwise
     */
    boolean existsByProposalId(Integer proposalId);
    
    // Eager loading methods
    
    /**
     * Find a proposal follow-up by ID with its proposal eagerly loaded.
     * 
     * @param id The ID to search for
     * @return An Optional containing the proposal follow-up if found, or empty if not found
     */
    @Query("SELECT pf FROM ProposalFup pf JOIN FETCH pf.proposal WHERE pf.id = :id")
    Optional<ProposalFup> findByIdWithProposal(@Param("id") Integer id);
    
    /**
     * Find all proposal follow-ups by proposal ID with their proposals eagerly loaded.
     * 
     * @param proposalId The proposal ID to search for
     * @return A list of proposal follow-ups for the given proposal with their proposals eagerly loaded
     */
    @Query("SELECT pf FROM ProposalFup pf JOIN FETCH pf.proposal WHERE pf.proposal.id = :proposalId")
    List<ProposalFup> findByProposalIdWithProposal(@Param("proposalId") Integer proposalId);
    
    /**
     * Find all proposal follow-ups by proposal ID, ordered by date descending, with their proposals eagerly loaded.
     * 
     * @param proposalId The proposal ID to search for
     * @return A list of proposal follow-ups for the given proposal, ordered by date descending, with their proposals eagerly loaded
     */
    @Query("SELECT pf FROM ProposalFup pf JOIN FETCH pf.proposal WHERE pf.proposal.id = :proposalId ORDER BY pf.date DESC")
    List<ProposalFup> findByProposalIdOrderByDateDescWithProposal(@Param("proposalId") Integer proposalId);
    
    /**
     * Find all proposal follow-ups by user ID with their proposals eagerly loaded.
     * 
     * @param userId The user ID to search for
     * @return A list of proposal follow-ups for the given user with their proposals eagerly loaded
     */
    @Query("SELECT pf FROM ProposalFup pf JOIN FETCH pf.proposal WHERE pf.userId = :userId")
    List<ProposalFup> findByUserIdWithProposal(@Param("userId") Integer userId);
    
    /**
     * Find all proposal follow-ups by media classifier ID with their proposals eagerly loaded.
     * 
     * @param mediaClassifierId The media classifier ID to search for
     * @return A list of proposal follow-ups for the given media classifier with their proposals eagerly loaded
     */
    @Query("SELECT pf FROM ProposalFup pf JOIN FETCH pf.proposal WHERE pf.mediaClassifierId = :mediaClassifierId")
    List<ProposalFup> findByMediaClassifierIdWithProposal(@Param("mediaClassifierId") Integer mediaClassifierId);
    
    /**
     * Find all proposal follow-ups by follow-up type classifier ID with their proposals eagerly loaded.
     * 
     * @param followUpTypeClassifierId The follow-up type classifier ID to search for
     * @return A list of proposal follow-ups for the given follow-up type classifier with their proposals eagerly loaded
     */
    @Query("SELECT pf FROM ProposalFup pf JOIN FETCH pf.proposal WHERE pf.followUpTypeClassifierId = :followUpTypeClassifierId")
    List<ProposalFup> findByFollowUpTypeClassifierIdWithProposal(@Param("followUpTypeClassifierId") Integer followUpTypeClassifierId);
    
    /**
     * Find all proposal follow-ups by date between start date and end date with their proposals eagerly loaded.
     * 
     * @param startDate The start date to search for
     * @param endDate The end date to search for
     * @return A list of proposal follow-ups between the given dates with their proposals eagerly loaded
     */
    @Query("SELECT pf FROM ProposalFup pf JOIN FETCH pf.proposal WHERE pf.date BETWEEN :startDate AND :endDate")
    List<ProposalFup> findByDateBetweenWithProposal(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    /**
     * Find all proposal follow-ups with their proposals eagerly loaded.
     * 
     * @return A list of all proposal follow-ups with their proposals eagerly loaded
     */
    @Query("SELECT pf FROM ProposalFup pf JOIN FETCH pf.proposal")
    List<ProposalFup> findAllWithProposal();
}
