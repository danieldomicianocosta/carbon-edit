package com.carbon.refactor.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.domain.entity.Proposal;

@Repository
public interface JpaProposalRepository extends JpaRepository<Proposal, Integer> {
    
    Optional<Proposal> findByProposalNumber(String proposalNumber);
    
    Optional<Proposal> findByNumAndCod(Long num, String cod);
    
    boolean existsByProposalNumber(String proposalNumber);
    
    boolean existsByNumAndCod(Long num, String cod);
}
