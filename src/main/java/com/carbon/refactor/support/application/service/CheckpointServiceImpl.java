package com.carbon.refactor.support.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.support.domain.entity.Checkpoint;
import com.carbon.refactor.support.domain.repository.CheckpointRepository;
import com.carbon.refactor.support.domain.service.CheckpointService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the CheckpointService interface.
 */
@Service
@RequiredArgsConstructor
public class CheckpointServiceImpl implements CheckpointService {
    
    private final CheckpointRepository checkpointRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Checkpoint> findById(Integer id) {
        return checkpointRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Checkpoint> findAll() {
        return checkpointRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return checkpointRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long count() {
        return checkpointRepository.count();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Checkpoint> findByName(String name) {
        return checkpointRepository.findByName(name);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Checkpoint> findByNameContaining(String name) {
        return checkpointRepository.findByNameContaining(name);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Checkpoint> findByDescriptionContaining(String description) {
        return checkpointRepository.findByDescriptionContaining(description);
    }
}
