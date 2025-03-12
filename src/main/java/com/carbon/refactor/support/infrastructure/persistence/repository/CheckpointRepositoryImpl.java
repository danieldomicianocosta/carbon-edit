package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.Checkpoint;
import com.carbon.refactor.support.domain.repository.CheckpointRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the CheckpointRepository interface using JPA.
 */
@Repository
@RequiredArgsConstructor
public class CheckpointRepositoryImpl implements CheckpointRepository {
    
    private final JpaCheckpointRepository jpaCheckpointRepository;
    
    @Override
    public Optional<Checkpoint> findById(Integer id) {
        return jpaCheckpointRepository.findById(id);
    }
    
    @Override
    public List<Checkpoint> findAll() {
        return jpaCheckpointRepository.findAll();
    }
    
    @Override
    public boolean existsById(Integer id) {
        return jpaCheckpointRepository.existsById(id);
    }
    
    @Override
    public long count() {
        return jpaCheckpointRepository.count();
    }
    
    @Override
    public Optional<Checkpoint> findByName(String name) {
        return jpaCheckpointRepository.findByName(name);
    }
    
    @Override
    public List<Checkpoint> findByNameContaining(String name) {
        return jpaCheckpointRepository.findByNameContainingIgnoreCase(name);
    }
    
    @Override
    public List<Checkpoint> findByDescriptionContaining(String description) {
        return jpaCheckpointRepository.findByDescriptionContainingIgnoreCase(description);
    }
}
