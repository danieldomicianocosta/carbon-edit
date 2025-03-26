package com.carbon.refactor.support.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.support.domain.entity.AccessListCheckpoint;
import com.carbon.refactor.support.domain.entity.AccessListCheckpointId;
import com.carbon.refactor.support.domain.repository.AccessListCheckpointRepository;
import com.carbon.refactor.support.domain.service.AccessListCheckpointService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the AccessListCheckpointService interface.
 */
@Service
@RequiredArgsConstructor
public class AccessListCheckpointServiceImpl implements AccessListCheckpointService {
    
    private final AccessListCheckpointRepository accessListCheckpointRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<AccessListCheckpoint> findById(AccessListCheckpointId id) {
        return accessListCheckpointRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<AccessListCheckpoint> findAll() {
        return accessListCheckpointRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(AccessListCheckpointId id) {
        return accessListCheckpointRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long count() {
        return accessListCheckpointRepository.count();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<AccessListCheckpoint> findByCheckpointId(Integer checkpointId) {
        return accessListCheckpointRepository.findByCheckpointId(checkpointId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<AccessListCheckpoint> findByAccessListId(Integer accessListId) {
        return accessListCheckpointRepository.findByAccessListId(accessListId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<AccessListCheckpoint> findByCheckpointIdAndAccessListId(Integer checkpointId, Integer accessListId) {
        return accessListCheckpointRepository.findByCheckpointIdAndAccessListId(checkpointId, accessListId);
    }
}
