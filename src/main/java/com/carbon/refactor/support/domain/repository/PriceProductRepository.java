package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.PriceProduct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on PriceProduct entities.
 */
public interface PriceProductRepository extends ReadOnlyRepository<PriceProduct, Integer> {
    
    /**
     * Find price products by price list ID.
     * 
     * @param priceListId The price list ID
     * @return A list of price products for the given price list
     */
    List<PriceProduct> findByPriceListId(Integer priceListId);
    
    /**
     * Find price products by product model ID.
     * 
     * @param productModelId The product model ID
     * @return A list of price products for the given product model
     */
    List<PriceProduct> findByProductModelId(Integer productModelId);
    
    /**
     * Find price products by price list ID and product model ID.
     * 
     * @param priceListId The price list ID
     * @param productModelId The product model ID
     * @return A list of price products for the given price list and product model
     */
    List<PriceProduct> findByPriceListIdAndProductModelId(Integer priceListId, Integer productModelId);
    
    /**
     * Find active price products by price list ID and product model ID.
     * 
     * @param priceListId The price list ID
     * @param productModelId The product model ID
     * @return An Optional containing the active price product if found, or empty if not found
     */
    Optional<PriceProduct> findByPriceListIdAndProductModelIdAndDeleteDateIsNull(
            Integer priceListId, Integer productModelId);
    
    /**
     * Find price products by user ID who created them.
     * 
     * @param userIdCreate The user ID who created the price products
     * @return A list of price products created by the given user
     */
    List<PriceProduct> findByUserIdCreate(Integer userIdCreate);
    
    /**
     * Find price products by user ID who deleted them.
     * 
     * @param userIdDelete The user ID who deleted the price products
     * @return A list of price products deleted by the given user
     */
    List<PriceProduct> findByUserIdDelete(Integer userIdDelete);
    
    /**
     * Find price products created after the given date.
     * 
     * @param date The date to search from
     * @return A list of price products created after the given date
     */
    List<PriceProduct> findByCreateDateAfter(LocalDateTime date);
    
    /**
     * Find price products created before the given date.
     * 
     * @param date The date to search to
     * @return A list of price products created before the given date
     */
    List<PriceProduct> findByCreateDateBefore(LocalDateTime date);
    
    /**
     * Find active price products.
     * 
     * @return A list of active price products
     */
    List<PriceProduct> findByDeleteDateIsNull();
}
