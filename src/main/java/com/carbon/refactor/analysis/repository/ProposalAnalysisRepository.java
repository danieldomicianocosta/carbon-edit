package com.carbon.refactor.analysis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.domain.entity.Proposal;

@Repository
public interface ProposalAnalysisRepository extends JpaRepository<Proposal, Integer> {
    
    /**
     * Find all proposals by status.
     *
     * @param statusId The status ID to filter by
     * @return A list of proposals with the given status
     */
    List<Proposal> findByStatusClaId(Integer statusId);
    
    /**
     * Find all proposals by status with pagination.
     *
     * @param statusId The status ID to filter by
     * @param pageable The pagination information
     * @return A page of proposals with the given status
     */
    Page<Proposal> findByStatusClaId(Integer statusId, Pageable pageable);
    
    /**
     * Find all proposals by status with related data.
     *
     * @param statusId The status ID to filter by
     * @return A list of proposals with the given status and related data
     */
    @Query("SELECT DISTINCT p FROM Proposal p " +
           "LEFT JOIN FETCH com.carbon.refactor.domain.entity.ProposalDetail pd ON pd.proposal.id = p.id " +
           "LEFT JOIN FETCH com.carbon.refactor.domain.entity.ProposalDetailVehicle pdv ON pdv.proposalDetail.id = pd.id " +
           "WHERE p.statusClaId = :statusId")
    List<Proposal> findByStatusClaIdWithDetails(@Param("statusId") Integer statusId);
    
    /**
     * Count proposals by status.
     *
     * @param statusId The status ID to filter by
     * @return The count of proposals with the given status
     */
    @Query("SELECT COUNT(p) FROM Proposal p WHERE p.statusClaId = :statusId")
    long countByStatusClaId(@Param("statusId") Integer statusId);
    
    /**
     * Count proposals grouped by status.
     *
     * @return A list of Object[] arrays containing the status ID and count
     */
    @Query("SELECT p.statusClaId, COUNT(p) FROM Proposal p GROUP BY p.statusClaId ORDER BY p.statusClaId")
    List<Object[]> countGroupByStatus();
    
    /**
     * Find all proposals by status with pagination and related data.
     * This is a workaround for the "HHH000104: firstResult/maxResults specified with collection fetch; applying in memory!" warning.
     *
     * @param statusId The status ID to filter by
     * @param pageable The pagination information
     * @return A list of proposals with the given status and related data
     */
    @Query("SELECT p FROM Proposal p WHERE p.statusClaId = :statusId")
    Page<Proposal> findPageByStatusClaId(@Param("statusId") Integer statusId, Pageable pageable);
}
