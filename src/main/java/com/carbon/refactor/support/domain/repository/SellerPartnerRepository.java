package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.SellerPartner;
import com.carbon.refactor.support.domain.entity.SellerPartnerId;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on SellerPartner entities.
 */
public interface SellerPartnerRepository extends ReadOnlyRepository<SellerPartner, SellerPartnerId> {
    
    /**
     * Find seller partners by seller ID.
     * 
     * @param sellerId The seller ID
     * @return A list of seller partners for the given seller
     */
    List<SellerPartner> findBySellerId(Integer sellerId);
    
    /**
     * Find seller partners by partner ID.
     * 
     * @param partnerId The partner ID
     * @return A list of seller partners for the given partner
     */
    List<SellerPartner> findByPartnerId(Integer partnerId);
    
    /**
     * Find a seller partner by seller ID and partner ID.
     * 
     * @param sellerId The seller ID
     * @param partnerId The partner ID
     * @return An Optional containing the seller partner if found, or empty if not found
     */
    Optional<SellerPartner> findBySellerIdAndPartnerId(Integer sellerId, Integer partnerId);
}
