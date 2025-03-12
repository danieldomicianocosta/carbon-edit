                  package com.carbon.refactor.support.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.support.domain.entity.AccessList;
import com.carbon.refactor.support.domain.repository.AccessListRepository;
import com.carbon.refactor.support.domain.service.AccessListService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the AccessListService interface.
 */
@Service
@RequiredArgsConstructor
public class AccessListServiceImpl implements AccessListService {
    
    private final AccessListRepository accessListRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<AccessList> findById(Integer id) {
        return accessListRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<AccessList> findAll() {
        return accessListRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return accessListRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long count() {
        return accessListRepository.count();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<AccessList> findByName(String name) {
        return accessListRepository.findByName(name);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<AccessList> findByNameContaining(String name) {
        return accessListRepository.findByNameContaining(name);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<AccessList> findByMenuId(Integer menuId) {
        return accessListRepository.findByMenuId(menuId);
    }
}
