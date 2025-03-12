package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.ItemModel;
import java.util.List;

/**
 * Repository interface for read-only operations on ItemModel entities.
 */
public interface ItemModelRepository extends ReadOnlyRepository<ItemModel, Integer> {
    
    /**
     * Find item models by item ID.
     * 
     * @param itemId The item ID
     * @return A list of item models for the given item
     */
    List<ItemModel> findByItemId(Integer itemId);
    
    /**
     * Find item models by model ID.
     * 
     * @param modelId The model ID
     * @return A list of item models for the given model
     */
    List<ItemModel> findByModelId(Integer modelId);
    
    /**
     * Find item models by model year range.
     * 
     * @param year The year to check
     * @return A list of item models valid for the given year
     */
    List<ItemModel> findByModelYearStartLessThanEqualAndModelYearEndGreaterThanEqual(
            Integer year, Integer sameYear);
    
    /**
     * Find item models by item ID and model year range.
     * 
     * @param itemId The item ID
     * @param year The year to check
     * @return A list of item models for the given item and valid for the given year
     */
    List<ItemModel> findByItemIdAndModelYearStartLessThanEqualAndModelYearEndGreaterThanEqual(
            Integer itemId, Integer year, Integer sameYear);
    
    /**
     * Find item models by model ID and model year range.
     * 
     * @param modelId The model ID
     * @param year The year to check
     * @return A list of item models for the given model and valid for the given year
     */
    List<ItemModel> findByModelIdAndModelYearStartLessThanEqualAndModelYearEndGreaterThanEqual(
            Integer modelId, Integer year, Integer sameYear);
    
    /**
     * Find item models by mandatory classifier ID.
     * 
     * @param mandatoryClaId The mandatory classifier ID
     * @return A list of item models with the given mandatory classifier
     */
    List<ItemModel> findByMandatoryClaId(Integer mandatoryClaId);
    
    /**
     * Find item models that have factory settings.
     * 
     * @return A list of item models that have factory settings
     */
    List<ItemModel> findByFactorySettingsTrue();
}
