package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.Brand;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on Brand entities.
 */
public interface BrandService extends ReadOnlyService<Brand, Integer> {
    
    /**
     * Find all active brands.
     * 
     * @return A list of active brands
     */
    List<Brand> findAllActive();
    
    /**
     * Find a brand by its name.
     * 
     * @param name The name to search for
     * @return An Optional containing the brand if found, or empty if not found
     */
    Optional<Brand> findByName(String name);
    
    /**
     * Find brands by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of brands with names containing the given text
     */
    List<Brand> findByNameContaining(String name);
    
    /**
     * Find brands by partner ID.
     * 
     * @param partnerId The partner ID
     * @return A list of brands for the given partner
     */
    List<Brand> findByPartnerId(Integer partnerId);
}
