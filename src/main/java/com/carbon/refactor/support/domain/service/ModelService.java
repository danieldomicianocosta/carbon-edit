package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.Model;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on Model entities.
 */
public interface ModelService extends ReadOnlyService<Model, Integer> {
    
    /**
     * Find all active models.
     * 
     * @return A list of active models
     */
    List<Model> findAllActive();
    
    /**
     * Find a model by its name.
     * 
     * @param name The name to search for
     * @return An Optional containing the model if found, or empty if not found
     */
    Optional<Model> findByName(String name);
    
    /**
     * Find models by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of models with names containing the given text
     */
    List<Model> findByNameContaining(String name);
}
