package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.Checkpoint;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on Checkpoint entities.
 */
public interface CheckpointRepository extends ReadOnlyRepository<Checkpoint, Integer> {
    
    /**
     * Find a checkpoint by its name.
     * 
     * @param name The name to search for
     * @return An Optional containing the checkpoint if found, or empty if not found
     */
    Optional<Checkpoint> findByName(String name);
    
    /**
     * Find checkpoints by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of checkpoints with names containing the given text
     */
    List<Checkpoint> findByNameContaining(String name);
    
    /**
     * Find checkpoints by description containing the given text.
     * 
     * @param description The description to search for
     * @return A list of checkpoints with descriptions containing the given text
     */
    List<Checkpoint> findByDescriptionContaining(String description);
}
