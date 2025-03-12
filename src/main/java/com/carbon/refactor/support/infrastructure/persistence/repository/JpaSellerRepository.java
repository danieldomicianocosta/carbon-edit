package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.Seller;

/**
 * JPA repository interface for Seller entities.
 */
@Repository
public interface JpaSellerRepository extends JpaRepository<Seller, Integer> {
    
    /**
     * Find all active sellers.
     * 
     * @return A list of active sellers
     */
    List<Seller> findByActiveTrue();
    
    /**
     * Find sellers by person ID.
     * 
     * @param personId The person ID
     * @return A list of sellers for the given person
     */
    List<Seller> findByPersonId(Integer personId);
    
    /**
     * Find sellers by job ID.
     * 
     * @param jobId The job ID
     * @return A list of sellers for the given job
     */
    List<Seller> findByJobId(Integer jobId);
    
    /**
     * Find sellers that provide technical assistance.
     * 
     * @return A list of sellers that provide technical assistance
     */
    List<Seller> findByTechnicalAssistanceTrue();
    
    /**
     * Find a seller by person ID and job ID.
     * 
     * @param personId The person ID
     * @param jobId The job ID
     * @return An Optional containing the seller if found, or empty if not found
     */
    Optional<Seller> findByPersonIdAndJobId(Integer personId, Integer jobId);
}
