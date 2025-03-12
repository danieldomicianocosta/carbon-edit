package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.ItemModel;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on ItemModel entities.
 */
public interface ItemModelService extends ReadOnlyService<ItemModel, Integer> {
    
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
     * Find item models by item ID and model ID.
     * 
     * @param itemId The item ID
     * @param modelId The model ID
     * @return An Optional containing the item model if found, or empty if not found
     */
    Optional<ItemModel> findByItemIdAndModelId(Integer itemId, Integer modelId);
    
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
     * Find item models with additional term.
     * 
     * @param additionalTerm The additional term
     * @return A list of item models with the given additional term
     */
    List<ItemModel> findByAdditionalTerm(Integer additionalTerm);
    
    /**
     * Find item models with factory settings.
     * 
     * @return A list of item models with factory settings
     */
    List<ItemModel> findByFactorySettingsTrue();
    
    /**
     * Find item models by mandatory classifier ID.
     * 
     * @param mandatoryClassifierId The mandatory classifier ID
     * @return A list of item models with the given mandatory classifier
     */
    List<ItemModel> findByMandatoryClassifierId(Integer mandatoryClassifierId);
}
