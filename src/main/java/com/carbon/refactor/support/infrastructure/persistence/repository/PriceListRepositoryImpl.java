package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.PriceList;
import com.carbon.refactor.support.domain.repository.PriceListRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the PriceListRepository interface using JPA.
 */
@Repository
@RequiredArgsConstructor
public class PriceListRepositoryImpl implements PriceListRepository {
    
    private final JpaPriceListRepository jpaPriceListRepository;
    
    @Override
    public Optional<PriceList> findById(Integer id) {
        return jpaPriceListRepository.findById(id);
    }
    
    @Override
    public List<PriceList> findAll() {
        return jpaPriceListRepository.findAll();
    }
    
    @Override
    public boolean existsById(Integer id) {
        return jpaPriceListRepository.existsById(id);
    }
    
    @Override
    public long count() {
        return jpaPriceListRepository.count();
    }
    
    @Override
    public Optional<PriceList> findByName(String name) {
        return jpaPriceListRepository.findByName(name);
    }
    
    @Override
    public List<PriceList> findByNameContaining(String name) {
        return jpaPriceListRepository.findByNameContainingIgnoreCase(name);
    }
    
    @Override
    public List<PriceList> findByChannelId(Integer channelId) {
        return jpaPriceListRepository.findByChannelId(channelId);
    }
    
    @Override
    public List<PriceList> findByAllPartnersTrue() {
        return jpaPriceListRepository.findByAllPartnersTrue();
    }
    
    @Override
    public List<PriceList> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate date, LocalDate sameDate) {
        return jpaPriceListRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(date, sameDate);
    }
    
    @Override
    public List<PriceList> findByChannelIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Integer channelId, LocalDate date, LocalDate sameDate) {
        return jpaPriceListRepository.findByChannelIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                channelId, date, sameDate);
    }
}
