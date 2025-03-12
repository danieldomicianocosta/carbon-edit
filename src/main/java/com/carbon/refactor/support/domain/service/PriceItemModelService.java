package com.carbon.refactor.support.domain.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.carbon.refactor.support.domain.entity.PriceItemModel;

/**
 * Service interface for read-only operations on PriceItemModel entities.
 */
public interface PriceItemModelService extends ReadOnlyService<PriceItemModel, Integer> {
    
    /**
     * Find price item models by price list ID.
     * 
     * @param priceListId The price list ID
     * @return A list of price item models for the given price list
     */
    List<PriceItemModel> findByPriceListId(Integer priceListId);
    
    /**
     * Find price item models by item model ID.
     * 
     * @param itemModelId The item model ID
     * @return A list of price item models for the given item model
     */
    List<PriceItemModel> findByItemModelId(Integer itemModelId);
    
    /**
     * Find price item models by brand ID.
     * 
     * @param brandId The brand ID
     * @return A list of price item models for the given brand
     */
    List<PriceItemModel> findByBrandId(Integer brandId);
    
    /**
     * Find price item models by item ID.
     * 
     * @param itemId The item ID
     * @return A list of price item models for the given item
     */
    List<PriceItemModel> findByItemId(Integer itemId);
    
    /**
     * Find price item models that are for all models.
     * 
     * @return A list of price item models that are for all models
     */
    List<PriceItemModel> findByAllModelsTrue();
    
    /**
     * Find price item models that are for all brands.
     * 
     * @return A list of price item models that are for all brands
     */
    List<PriceItemModel> findByAllBrandsTrue();
    
    /**
     * Find price item models that are for free.
     * 
     * @return A list of price item models that are for free
     */
    List<PriceItemModel> findByForFreeTrue();
    
    /**
     * Find price item models by price.
     * 
     * @param price The price to search for
     * @return A list of price item models with the given price
     */
    List<PriceItemModel> findByPrice(BigDecimal price);
    
    /**
     * Find price item models by price less than or equal to the given value.
     * 
     * @param price The maximum price
     * @return A list of price item models with prices less than or equal to the given value
     */
    List<PriceItemModel> findByPriceLessThanEqual(BigDecimal price);
    
    /**
     * Find price item models by price greater than or equal to the given value.
     * 
     * @param price The minimum price
     * @return A list of price item models with prices greater than or equal to the given value
     */
    List<PriceItemModel> findByPriceGreaterThanEqual(BigDecimal price);
    
    /**
     * Find price item models by user ID who created them.
     * 
     * @param userIdCreate The user ID who created the price item models
     * @return A list of price item models created by the given user
     */
    List<PriceItemModel> findByUserIdCreate(Integer userIdCreate);
    
    /**
     * Find price item models by user ID who deleted them.
     * 
     * @param userIdDelete The user ID who deleted the price item models
     * @return A list of price item models deleted by the given user
     */
    List<PriceItemModel> findByUserIdDelete(Integer userIdDelete);
    
    /**
     * Find price item models created after the given date.
     * 
     * @param date The date to search from
     * @return A list of price item models created after the given date
     */
    List<PriceItemModel> findByCreateDateAfter(LocalDateTime date);
    
    /**
     * Find price item models created before the given date.
     * 
     * @param date The date to search to
     * @return A list of price item models created before the given date
     */
    List<PriceItemModel> findByCreateDateBefore(LocalDateTime date);
    
    /**
     * Find active price item models.
     * 
     * @return A list of active price item models
     */
    List<PriceItemModel> findByDeleteDateIsNull();
}
