package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.PartnerBrand;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on PartnerBrand entities.
 */
public interface PartnerBrandService extends ReadOnlyService<PartnerBrand, Integer> {
    
    /**
     * Find partner brands by partner ID.
     * 
     * @param partnerId The partner ID
     * @return A list of partner brands for the given partner
     */
    List<PartnerBrand> findByPartnerId(Integer partnerId);
    
    /**
     * Find partner brands by brand ID.
     * 
     * @param brandId The brand ID
     * @return A list of partner brands for the given brand
     */
    List<PartnerBrand> findByBrandId(Integer brandId);
    
    /**
     * Find a partner brand by partner ID and brand ID.
     * 
     * @param partnerId The partner ID
     * @param brandId The brand ID
     * @return An Optional containing the partner brand if found, or empty if not found
     */
    Optional<PartnerBrand> findByPartnerIdAndBrandId(Integer partnerId, Integer brandId);
}
