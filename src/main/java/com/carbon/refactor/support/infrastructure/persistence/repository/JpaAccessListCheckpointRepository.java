package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.AccessListCheckpoint;
import com.carbon.refactor.support.domain.entity.AccessListCheckpointId;

/**
 * JPA repository interface for AccessListCheckpoint entities.
 */
@Repository
public interface JpaAccessListCheckpointRepository extends JpaRepository<AccessListCheckpoint, AccessListCheckpointId> {
    
    /**
     * Find access list checkpoints by checkpoint ID.
     * 
     * @param checkpointId The checkpoint ID
     * @return A list of access list checkpoints for the given checkpoint
     */
    List<AccessListCheckpoint> findByCheckpointId(Integer checkpointId);
    
    /**
     * Find access list checkpoints by access list ID.
     * 
     * @param accessListId The access list ID
     * @return A list of access list checkpoints for the given access list
     */
    List<AccessListCheckpoint> findByAccessListId(Integer accessListId);
    
    /**
     * Find an access list checkpoint by checkpoint ID and access list ID.
     * 
     * @param checkpointId The checkpoint ID
     * @param accessListId The access list ID
     * @return An Optional containing the access list checkpoint if found, or empty if not found
     */
    Optional<AccessListCheckpoint> findByCheckpointIdAndAccessListId(Integer checkpointId, Integer accessListId);
}
