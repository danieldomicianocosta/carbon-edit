package com.carbon.refactor.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.domain.entity.ProposalDetailVehicle;
import com.carbon.refactor.domain.repository.ProposalDetailVehicleRepository;

@Component
@RequiredArgsConstructor
public class ProposalDetailVehicleRepositoryImpl implements ProposalDetailVehicleRepository {
    
    private final JpaProposalDetailVehicleRepository jpaProposalDetailVehicleRepository;
    
    @Override
    public List<ProposalDetailVehicle> findAll() {
        return jpaProposalDetailVehicleRepository.findAll();
    }
    
    @Override
    public Optional<ProposalDetailVehicle> findById(Integer id) {
        return jpaProposalDetailVehicleRepository.findById(id);
    }
    
    @Override
    public List<ProposalDetailVehicle> findByProposalDetailId(Integer proposalDetailId) {
        return jpaProposalDetailVehicleRepository.findByProposalDetailId(proposalDetailId);
    }
    
    @Override
    public ProposalDetailVehicle save(ProposalDetailVehicle proposalDetailVehicle) {
        return jpaProposalDetailVehicleRepository.save(proposalDetailVehicle);
    }
    
    @Override
    public void deleteById(Integer id) {
        jpaProposalDetailVehicleRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Integer id) {
        return jpaProposalDetailVehicleRepository.existsById(id);
    }
    
    @Override
    public void deleteByProposalDetailId(Integer proposalDetailId) {
        jpaProposalDetailVehicleRepository.deleteByProposalDetailId(proposalDetailId);
    }
}
