package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.CommissionType;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on CommissionType entities.
 */
public interface CommissionTypeRepository extends ReadOnlyRepository<CommissionType, Integer> {
    
    /**
     * Find a commission type by its name.
     * 
     * @param name The name to search for
     * @return An Optional containing the commission type if found, or empty if not found
     */
    Optional<CommissionType> findByName(String name);
    
    /**
     * Find commission types by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of commission types with names containing the given text
     */
    List<CommissionType> findByNameContaining(String name);
    
    /**
     * Find commission types that are for manufacturers.
     * 
     * @return A list of commission types for manufacturers
     */
    List<CommissionType> findByManufacturerTrue();
    
    /**
     * Find commission types that are for overprices.
     * 
     * @return A list of commission types for overprices
     */
    List<CommissionType> findByOverpriceTrue();
}
