package com.carbon.refactor.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carbon.refactor.domain.entity.Proposal;

public interface ProposalRepository {
    
    List<Proposal> findAll();
    
    Page<Proposal> findAll(Pageable pageable);
    
    Optional<Proposal> findById(Integer id);
    
    Optional<Proposal> findByProposalNumber(String proposalNumber);
    
    Optional<Proposal> findByNumAndCod(Long num, String cod);
    
    Proposal save(Proposal proposal);
    
    void deleteById(Integer id);
    
    boolean existsById(Integer id);
    
    boolean existsByProposalNumber(String proposalNumber);
    
    boolean existsByNumAndCod(Long num, String cod);
}
