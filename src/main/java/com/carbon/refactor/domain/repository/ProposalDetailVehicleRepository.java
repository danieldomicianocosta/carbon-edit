package com.carbon.refactor.domain.repository;

import java.util.List;
import java.util.Optional;

import com.carbon.refactor.domain.entity.ProposalDetailVehicle;

public interface ProposalDetailVehicleRepository {
    
    List<ProposalDetailVehicle> findAll();
    
    Optional<ProposalDetailVehicle> findById(Integer id);
    
    List<ProposalDetailVehicle> findByProposalDetailId(Integer proposalDetailId);
    
    ProposalDetailVehicle save(ProposalDetailVehicle proposalDetailVehicle);
    
    void deleteById(Integer id);
    
    boolean existsById(Integer id);
    
    void deleteByProposalDetailId(Integer proposalDetailId);
}
