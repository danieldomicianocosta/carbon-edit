package com.carbon.refactor.domain.service;

import java.util.List;

import com.carbon.refactor.domain.entity.ProposalDetailVehicle;

public interface ProposalDetailVehicleService {
    
    List<ProposalDetailVehicle> findAll();
    
    ProposalDetailVehicle findById(Integer id);
    
    List<ProposalDetailVehicle> findByProposalDetailId(Integer proposalDetailId);
    
    ProposalDetailVehicle create(ProposalDetailVehicle proposalDetailVehicle);
    
    ProposalDetailVehicle update(Integer id, ProposalDetailVehicle proposalDetailVehicle);
    
    void delete(Integer id);
    
    void deleteByProposalDetailId(Integer proposalDetailId);
    
    boolean existsById(Integer id);
}
