package com.carbon.refactor.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.domain.entity.ProposalDetailVehicle;

@Repository
public interface JpaProposalDetailVehicleRepository extends JpaRepository<ProposalDetailVehicle, Integer> {
    
    @Query("SELECT pdv FROM ProposalDetailVehicle pdv WHERE pdv.proposalDetail.id = :proposalDetailId")
    List<ProposalDetailVehicle> findByProposalDetailId(@Param("proposalDetailId") Integer proposalDetailId);
    
    @Modifying
    @Query("DELETE FROM ProposalDetailVehicle pdv WHERE pdv.proposalDetail.id = :proposalDetailId")
    void deleteByProposalDetailId(@Param("proposalDetailId") Integer proposalDetailId);
}
