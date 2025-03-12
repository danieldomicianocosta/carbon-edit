package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.Channel;
import com.carbon.refactor.support.domain.repository.ChannelRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the ChannelRepository interface using JPA.
 */
@Repository
@RequiredArgsConstructor
public class ChannelRepositoryImpl implements ChannelRepository {
    
    private final JpaChannelRepository jpaChannelRepository;
    
    @Override
    public Optional<Channel> findById(Integer id) {
        return jpaChannelRepository.findById(id);
    }
    
    @Override
    public List<Channel> findAll() {
        return jpaChannelRepository.findAll();
    }
    
    @Override
    public boolean existsById(Integer id) {
        return jpaChannelRepository.existsById(id);
    }
    
    @Override
    public long count() {
        return jpaChannelRepository.count();
    }
    
    @Override
    public List<Channel> findAllActive() {
        return jpaChannelRepository.findAllActive();
    }
    
    @Override
    public List<Channel> findByNameContaining(String name) {
        return jpaChannelRepository.findByNameContainingIgnoreCase(name);
    }
    
    @Override
    public List<Channel> findByHasPartnerTrue() {
        return jpaChannelRepository.findByHasPartnerTrue();
    }
    
    @Override
    public List<Channel> findByHasInternalSaleTrue() {
        return jpaChannelRepository.findByHasInternalSaleTrue();
    }
}