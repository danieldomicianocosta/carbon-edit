package com.carbon.refactor.support.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.support.domain.entity.Channel;
import com.carbon.refactor.support.domain.repository.ChannelRepository;
import com.carbon.refactor.support.domain.service.ChannelService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the ChannelService interface.
 */
@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    
    private final ChannelRepository channelRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Channel> findById(Integer id) {
        return channelRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return channelRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long count() {
        return channelRepository.count();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Channel> findAllActive() {
        return channelRepository.findAllActive();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Channel> findByNameContaining(String name) {
        return channelRepository.findByNameContaining(name);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Channel> findByHasPartnerTrue() {
        return channelRepository.findByHasPartnerTrue();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Channel> findByHasInternalSaleTrue() {
        return channelRepository.findByHasInternalSaleTrue();
    }
}