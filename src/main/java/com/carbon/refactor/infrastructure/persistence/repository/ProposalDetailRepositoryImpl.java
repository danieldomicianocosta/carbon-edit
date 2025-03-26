package com.carbon.refactor.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.domain.entity.ProposalDetail;
import com.carbon.refactor.domain.repository.ProposalDetailRepository;

@Component("proposalDetailRepository")
@RequiredArgsConstructor
public class ProposalDetailRepositoryImpl implements ProposalDetailRepository {
    
    private final JpaProposalDetailRepository jpaProposalDetailRepository;
    
    @Override
    public List<ProposalDetail> findAll() {
        return jpaProposalDetailRepository.findAllWithProposal();
    }
    
    @Override
    public Optional<ProposalDetail> findById(Integer id) {
        return jpaProposalDetailRepository.findByIdWithProposal(id);
    }
    
    @Override
    public Optional<ProposalDetail> findByProposalId(Integer proposalId) {
        return jpaProposalDetailRepository.findByProposalIdWithProposal(proposalId);
    }
    
    @Override
    public ProposalDetail save(ProposalDetail proposalDetail) {
        return jpaProposalDetailRepository.save(proposalDetail);
    }
    
    @Override
    public void deleteById(Integer id) {
        jpaProposalDetailRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Integer id) {
        return jpaProposalDetailRepository.existsById(id);
    }
    
    @Override
    public boolean existsByProposalId(Integer proposalId) {
        return jpaProposalDetailRepository.existsByProposalId(proposalId);
    }
}
