package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.PriceListPartner;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on PriceListPartner entities.
 */
public interface PriceListPartnerRepository extends ReadOnlyRepository<PriceListPartner, Integer> {
    
    /**
     * Find price list partners by partner ID.
     * 
     * @param partnerId The partner ID
     * @return A list of price list partners for the given partner
     */
    List<PriceListPartner> findByPartnerId(Integer partnerId);
    
    /**
     * Find price list partners by price list ID.
     * 
     * @param priceListId The price list ID
     * @return A list of price list partners for the given price list
     */
    List<PriceListPartner> findByPriceListId(Integer priceListId);
    
    /**
     * Find a price list partner by partner ID and price list ID.
     * 
     * @param partnerId The partner ID
     * @param priceListId The price list ID
     * @return An Optional containing the price list partner if found, or empty if not found
     */
    Optional<PriceListPartner> findByPartnerIdAndPriceListId(Integer partnerId, Integer priceListId);
}
