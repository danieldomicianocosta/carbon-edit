package com.carbon.refactor.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.domain.entity.Proposal;

/**
 * JPA repository for Proposal entities that supports specification-based queries.
 * This interface extends JpaRepository and JpaSpecificationExecutor to provide
 * both basic CRUD operations and specification-based querying capabilities.
 */
@Repository
public interface JpaProposalRepository extends JpaRepository<Proposal, Integer>, JpaSpecificationExecutor<Proposal> {
    
    /**
     * Find a proposal by its proposal number.
     *
     * @param proposalNumber The proposal number to search for
     * @return An Optional containing the found proposal, or empty if not found
     */
    Optional<Proposal> findByProposalNumber(String proposalNumber);
    
    /**
     * Find a proposal by its num and cod.
     *
     * @param num The num value to search for
     * @param cod The cod value to search for
     * @return An Optional containing the found proposal, or empty if not found
     */
    Optional<Proposal> findByNumAndCod(Long num, String cod);
    
    /**
     * Check if a proposal exists with the given proposal number.
     *
     * @param proposalNumber The proposal number to check
     * @return true if a proposal exists with the given number, false otherwise
     */
    boolean existsByProposalNumber(String proposalNumber);
    
    /**
     * Check if a proposal exists with the given num and cod.
     *
     * @param num The num value to check
     * @param cod The cod value to check
     * @return true if a proposal exists with the given num and cod, false otherwise
     */
    boolean existsByNumAndCod(Long num, String cod);
}
