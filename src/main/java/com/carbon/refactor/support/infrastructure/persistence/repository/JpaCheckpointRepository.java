package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.Checkpoint;

/**
 * JPA repository interface for Checkpoint entities.
 */
@Repository
public interface JpaCheckpointRepository extends JpaRepository<Checkpoint, Integer> {
    
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
    List<Checkpoint> findByNameContainingIgnoreCase(String name);
    
    /**
     * Find checkpoints by description containing the given text.
     * 
     * @param description The description to search for
     * @return A list of checkpoints with descriptions containing the given text
     */
    List<Checkpoint> findByDescriptionContainingIgnoreCase(String description);
}
