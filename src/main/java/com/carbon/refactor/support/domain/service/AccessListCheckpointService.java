package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.AccessListCheckpoint;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on AccessListCheckpoint entities.
 */
public interface AccessListCheckpointService extends ReadOnlyService<AccessListCheckpoint, Integer> {
    
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
