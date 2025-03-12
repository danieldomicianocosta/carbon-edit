package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.ProductModel;
import java.util.List;

/**
 * Repository interface for read-only operations on ProductModel entities.
 */
public interface ProductModelRepository extends ReadOnlyRepository<ProductModel, Integer> {
    
    /**
     * Find product models by product ID.
     * 
     * @param productId The product ID
     * @return A list of product models for the given product
     */
    List<ProductModel> findByProductId(Integer productId);
    
    /**
     * Find product models by model ID.
     * 
     * @param modelId The model ID
     * @return A list of product models for the given model
     */
    List<ProductModel> findByModelId(Integer modelId);
    
    /**
     * Find product models by model year range.
     * 
     * @param year The year to check
     * @return A list of product models valid for the given year
     */
    List<ProductModel> findByModelYearStartLessThanEqualAndModelYearEndGreaterThanEqual(
            Integer year, Integer sameYear);
    
    /**
     * Find product models by product ID and model year range.
     * 
     * @param productId The product ID
     * @param year The year to check
     * @return A list of product models for the given product and valid for the given year
     */
    List<ProductModel> findByProductIdAndModelYearStartLessThanEqualAndModelYearEndGreaterThanEqual(
            Integer productId, Integer year, Integer sameYear);
    
    /**
     * Find product models by model ID and model year range.
     * 
     * @param modelId The model ID
     * @param year The year to check
     * @return A list of product models for the given model and valid for the given year
     */
    List<ProductModel> findByModelIdAndModelYearStartLessThanEqualAndModelYearEndGreaterThanEqual(
            Integer modelId, Integer year, Integer sameYear);
    
    /**
     * Find product models that have projects.
     * 
     * @return A list of product models that have projects
     */
    List<ProductModel> findByHasProjectTrue();
}
