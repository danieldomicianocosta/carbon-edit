package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.ProductModelBonus;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on ProductModelBonus entities.
 */
public interface ProductModelBonusService extends ReadOnlyService<ProductModelBonus, Integer> {
    
    /**
     * Find product model bonuses by brand ID.
     * 
     * @param brandId The brand ID
     * @return A list of product model bonuses for the given brand
     */
    List<ProductModelBonus> findByBrandId(Integer brandId);
    
    /**
     * Find product model bonuses by product model ID.
     * 
     * @param productModelId The product model ID
     * @return A list of product model bonuses for the given product model
     */
    List<ProductModelBonus> findByProductModelId(Integer productModelId);
    
    /**
     * Find product model bonuses by payment type classifier.
     * 
     * @param claPaymentType The payment type classifier
     * @return A list of product model bonuses with the given payment type classifier
     */
    List<ProductModelBonus> findByClaPaymentType(Integer claPaymentType);
    
    /**
     * Find product model bonuses that are percentage type.
     * 
     * @return A list of product model bonuses that are percentage type
     */
    List<ProductModelBonus> findByIsBonusTypePercentageTrue();
    
    /**
     * Find product model bonuses that are not percentage type.
     * 
     * @return A list of product model bonuses that are not percentage type
     */
    List<ProductModelBonus> findByIsBonusTypePercentageFalse();
    
    /**
     * Find a product model bonus by brand ID and product model ID.
     * 
     * @param brandId The brand ID
     * @param productModelId The product model ID
     * @return An Optional containing the product model bonus if found, or empty if not found
     */
    Optional<ProductModelBonus> findByBrandIdAndProductModelId(Integer brandId, Integer productModelId);
    
    /**
     * Find a product model bonus by brand ID, product model ID, and payment type classifier.
     * 
     * @param brandId The brand ID
     * @param productModelId The product model ID
     * @param claPaymentType The payment type classifier
     * @return An Optional containing the product model bonus if found, or empty if not found
     */
    Optional<ProductModelBonus> findByBrandIdAndProductModelIdAndClaPaymentType(
            Integer brandId, Integer productModelId, Integer claPaymentType);
}
