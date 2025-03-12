package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.PriceItem;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on PriceItem entities.
 */
public interface PriceItemRepository extends ReadOnlyRepository<PriceItem, Integer> {
    
    /**
     * Find price items by item ID.
     * 
     * @param itemId The item ID
     * @return A list of price items for the given item
     */
    List<PriceItem> findByItemId(Integer itemId);
    
    /**
     * Find price items by price list ID.
     * 
     * @param priceListId The price list ID
     * @return A list of price items for the given price list
     */
    List<PriceItem> findByPriceListId(Integer priceListId);
    
    /**
     * Find price items that are for free.
     * 
     * @return A list of price items that are for free
     */
    List<PriceItem> findByForFreeTrue();
    
    /**
     * Find price items by item ID and price list ID.
     * 
     * @param itemId The item ID
     * @param priceListId The price list ID
     * @return A list of price items for the given item and price list
     */
    List<PriceItem> findByItemIdAndPriceListId(Integer itemId, Integer priceListId);
    
    /**
     * Find active price items by item ID and price list ID.
     * 
     * @param itemId The item ID
     * @param priceListId The price list ID
     * @return An Optional containing the active price item if found, or empty if not found
     */
    Optional<PriceItem> findByItemIdAndPriceListIdAndDeleteDateIsNull(Integer itemId, Integer priceListId);
    
    /**
     * Find price items by user ID who created them.
     * 
     * @param userIdCreate The user ID who created the price items
     * @return A list of price items created by the given user
     */
    List<PriceItem> findByUserIdCreate(Integer userIdCreate);
    
    /**
     * Find price items by user ID who deleted them.
     * 
     * @param userIdDelete The user ID who deleted the price items
     * @return A list of price items deleted by the given user
     */
    List<PriceItem> findByUserIdDelete(Integer userIdDelete);
    
    /**
     * Find price items created after the given date.
     * 
     * @param date The date to search from
     * @return A list of price items created after the given date
     */
    List<PriceItem> findByCreateDateAfter(LocalDateTime date);
    
    /**
     * Find price items created before the given date.
     * 
     * @param date The date to search to
     * @return A list of price items created before the given date
     */
    List<PriceItem> findByCreateDateBefore(LocalDateTime date);
    
    /**
     * Find active price items.
     * 
     * @return A list of active price items
     */
    List<PriceItem> findByDeleteDateIsNull();
}
