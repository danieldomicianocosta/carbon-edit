package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.AccessListCheckpoint;
import com.carbon.refactor.support.domain.entity.AccessListCheckpointId;
import com.carbon.refactor.support.domain.repository.AccessListCheckpointRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the AccessListCheckpointRepository interface using JPA.
 */
@Repository
@RequiredArgsConstructor
public class AccessListCheckpointRepositoryImpl implements AccessListCheckpointRepository {
    
    private final JpaAccessListCheckpointRepository jpaAccessListCheckpointRepository;
    
    @Override
    public Optional<AccessListCheckpoint> findById(AccessListCheckpointId id) {
        return jpaAccessListCheckpointRepository.findById(id);
    }
    
    @Override
    public List<AccessListCheckpoint> findAll() {
        return jpaAccessListCheckpointRepository.findAll();
    }
    
    @Override
    public boolean existsById(AccessListCheckpointId id) {
        return jpaAccessListCheckpointRepository.existsById(id);
    }
    
    @Override
    public long count() {
        return jpaAccessListCheckpointRepository.count();
    }
    
    @Override
    public List<AccessListCheckpoint> findByCheckpointId(Integer checkpointId) {
        return jpaAccessListCheckpointRepository.findByCheckpointId(checkpointId);
    }
    
    @Override
    public List<AccessListCheckpoint> findByAccessListId(Integer accessListId) {
        return jpaAccessListCheckpointRepository.findByAccessListId(accessListId);
    }
    
    @Override
    public Optional<AccessListCheckpoint> findByCheckpointIdAndAccessListId(Integer checkpointId, Integer accessListId) {
        return jpaAccessListCheckpointRepository.findByCheckpointIdAndAccessListId(checkpointId, accessListId);
    }
}
