package com.carbon.refactor.domain.service;

import java.util.List;

import com.carbon.refactor.domain.entity.ProposalDetail;

public interface ProposalDetailService {
    
    List<ProposalDetail> findAll();
    
    ProposalDetail findById(Integer id);
    
    ProposalDetail findByProposalId(Integer proposalId);
    
    ProposalDetail create(ProposalDetail proposalDetail);
    
    ProposalDetail update(Integer id, ProposalDetail proposalDetail);
    
    void delete(Integer id);
    
    boolean existsById(Integer id);
    
    boolean existsByProposalId(Integer proposalId);
}
