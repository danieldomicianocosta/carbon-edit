package com.carbon.refactor.support.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.support.domain.entity.PriceList;
import com.carbon.refactor.support.domain.repository.PriceListRepository;
import com.carbon.refactor.support.domain.service.PriceListService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the PriceListService interface.
 */
@Service
@RequiredArgsConstructor
public class PriceListServiceImpl implements PriceListService {
    
    private final PriceListRepository priceListRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<PriceList> findById(Integer id) {
        return priceListRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceList> findAll() {
        return priceListRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return priceListRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long count() {
        return priceListRepository.count();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<PriceList> findByName(String name) {
        return priceListRepository.findByName(name);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceList> findByNameContaining(String name) {
        return priceListRepository.findByNameContaining(name);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceList> findByChannelId(Integer channelId) {
        return priceListRepository.findByChannelId(channelId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceList> findByAllPartnersTrue() {
        return priceListRepository.findByAllPartnersTrue();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceList> findByValidOnDate(LocalDate date) {
        return priceListRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(date, date);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceList> findByChannelIdAndValidOnDate(Integer channelId, LocalDate date) {
        return priceListRepository.findByChannelIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                channelId, date, date);
    }
}
