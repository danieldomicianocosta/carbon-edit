package com.carbon.refactor.support.domain.service;

import java.util.List;
import java.util.Optional;

/**
 * Base service interface for read-only operations.
 * 
 * @param <T> The entity type
 * @param <ID> The entity ID type
 */
public interface ReadOnlyService<T, ID> {
    
    /**
     * Find an entity by its ID.
     * 
     * @param id The entity ID
     * @return An Optional containing the entity if found, or empty if not found
     */
    Optional<T> findById(ID id);
    
    /**
     * Find all entities.
     * 
     * @return A list of all entities
     */
    List<T> findAll();
    
    /**
     * Check if an entity exists by its ID.
     * 
     * @param id The entity ID
     * @return true if the entity exists, false otherwise
     */
    boolean existsById(ID id);
    
    /**
     * Count the number of entities.
     * 
     * @return The number of entities
     */
    long count();
}
