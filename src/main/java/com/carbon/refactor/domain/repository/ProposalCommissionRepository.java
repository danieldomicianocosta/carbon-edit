package com.carbon.refactor.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.domain.entity.ProposalCommission;

/**
 * Repository interface for ProposalCommission entities.
 */
@Repository
public interface ProposalCommissionRepository extends JpaRepository<ProposalCommission, Integer> {
    
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
     * Delete all proposal commissions by proposal detail ID.
     * 
     * @param proposalDetailId The proposal detail ID to delete commissions for
     */
    void deleteByProposalDetailId(Integer proposalDetailId);
    
    /**
     * Check if any proposal commissions exist for the given proposal detail ID.
     * 
     * @param proposalDetailId The proposal detail ID to check
     * @return true if any proposal commissions exist, false otherwise
     */
    boolean existsByProposalDetailId(Integer proposalDetailId);
}
