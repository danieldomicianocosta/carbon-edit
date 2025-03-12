package com.carbon.refactor.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.domain.entity.ProposalDetail;

@Repository
public interface JpaProposalDetailRepository extends JpaRepository<ProposalDetail, Integer> {
    
    @Query("SELECT pd FROM ProposalDetail pd WHERE pd.proposal.id = :proposalId")
    Optional<ProposalDetail> findByProposalId(@Param("proposalId") Integer proposalId);
    
    @Query("SELECT CASE WHEN COUNT(pd) > 0 THEN true ELSE false END FROM ProposalDetail pd WHERE pd.proposal.id = :proposalId")
    boolean existsByProposalId(@Param("proposalId") Integer proposalId);
    
    @Query("SELECT pd FROM ProposalDetail pd JOIN FETCH pd.proposal WHERE pd.id = :id")
    Optional<ProposalDetail> findByIdWithProposal(@Param("id") Integer id);
    
    @Query("SELECT pd FROM ProposalDetail pd JOIN FETCH pd.proposal WHERE pd.proposal.id = :proposalId")
    Optional<ProposalDetail> findByProposalIdWithProposal(@Param("proposalId") Integer proposalId);
    
    @Query("SELECT pd FROM ProposalDetail pd JOIN FETCH pd.proposal")
    List<ProposalDetail> findAllWithProposal();
}
