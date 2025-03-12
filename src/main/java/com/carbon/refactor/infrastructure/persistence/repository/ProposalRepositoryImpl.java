package com.carbon.refactor.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.repository.ProposalRepository;

@Component
@RequiredArgsConstructor
public class ProposalRepositoryImpl implements ProposalRepository {
    
    private final JpaProposalRepository jpaProposalRepository;
    
    @Override
    public List<Proposal> findAll() {
        return jpaProposalRepository.findAll();
    }
    
    @Override
    public Page<Proposal> findAll(Pageable pageable) {
        return jpaProposalRepository.findAll(pageable);
    }
    
    @Override
    public Optional<Proposal> findById(Integer id) {
        return jpaProposalRepository.findById(id);
    }
    
    @Override
    public Optional<Proposal> findByProposalNumber(String proposalNumber) {
        return jpaProposalRepository.findByProposalNumber(proposalNumber);
    }
    
    @Override
    public Optional<Proposal> findByNumAndCod(Long num, String cod) {
        return jpaProposalRepository.findByNumAndCod(num, cod);
    }
    
    @Override
    public Proposal save(Proposal proposal) {
        return jpaProposalRepository.save(proposal);
    }
    
    @Override
    public void deleteById(Integer id) {
        jpaProposalRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Integer id) {
        return jpaProposalRepository.existsById(id);
    }
    
    @Override
    public boolean existsByProposalNumber(String proposalNumber) {
        return jpaProposalRepository.existsByProposalNumber(proposalNumber);
    }
    
    @Override
    public boolean existsByNumAndCod(Long num, String cod) {
        return jpaProposalRepository.existsByNumAndCod(num, cod);
    }
}
