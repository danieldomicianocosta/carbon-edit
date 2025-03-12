package com.carbon.refactor.domain.repository;

import java.util.List;
import java.util.Optional;

import com.carbon.refactor.domain.entity.ProposalDetail;

public interface ProposalDetailRepository {
    
    List<ProposalDetail> findAll();
    
    Optional<ProposalDetail> findById(Integer id);
    
    Optional<ProposalDetail> findByProposalId(Integer proposalId);
    
    ProposalDetail save(ProposalDetail proposalDetail);
    
    void deleteById(Integer id);
    
    boolean existsById(Integer id);
    
    boolean existsByProposalId(Integer proposalId);
}
