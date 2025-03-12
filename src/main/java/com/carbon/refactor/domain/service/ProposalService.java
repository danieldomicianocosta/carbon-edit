package com.carbon.refactor.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carbon.refactor.application.dto.response.PaginatedProposalResponseDTO;
import com.carbon.refactor.domain.entity.Proposal;

public interface ProposalService {
    
    List<Proposal> findAll();
    
    Page<Proposal> findAll(Pageable pageable);
    
    Page<PaginatedProposalResponseDTO> findAllPaginated(Pageable pageable);
    
    Proposal findById(Integer id);
    
    Proposal findByProposalNumber(String proposalNumber);
    
    Proposal findByNumAndCod(Long num, String cod);
    
    Proposal create(Proposal proposal);
    
    Proposal update(Integer id, Proposal proposal);
    
    void delete(Integer id);
    
    void activate(Integer id);
    
    void deactivate(Integer id);
    
    boolean existsById(Integer id);
    
    boolean existsByProposalNumber(String proposalNumber);
    
    boolean existsByNumAndCod(Long num, String cod);
}
