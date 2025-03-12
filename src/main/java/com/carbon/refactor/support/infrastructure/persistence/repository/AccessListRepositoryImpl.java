package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.AccessList;
import com.carbon.refactor.support.domain.repository.AccessListRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the AccessListRepository interface using JPA.
 */
@Repository
@RequiredArgsConstructor
public class AccessListRepositoryImpl implements AccessListRepository {
    
    private final JpaAccessListRepository jpaAccessListRepository;
    
    @Override
    public Optional<AccessList> findById(Integer id) {
        return jpaAccessListRepository.findById(id);
    }
    
    @Override
    public List<AccessList> findAll() {
        return jpaAccessListRepository.findAll();
    }
    
    @Override
    public boolean existsById(Integer id) {
        return jpaAccessListRepository.existsById(id);
    }
    
    @Override
    public long count() {
        return jpaAccessListRepository.count();
    }
    
    @Override
    public Optional<AccessList> findByName(String name) {
        return jpaAccessListRepository.findByName(name);
    }
    
    @Override
    public List<AccessList> findByNameContaining(String name) {
        return jpaAccessListRepository.findByNameContainingIgnoreCase(name);
    }
    
    @Override
    public List<AccessList> findByMenuId(Integer menuId) {
        return jpaAccessListRepository.findByMenuId(menuId);
    }
}
