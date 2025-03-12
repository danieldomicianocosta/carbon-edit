package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.PriceProduct;
import com.carbon.refactor.support.domain.repository.PriceProductRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the PriceProductRepository interface using JPA.
 */
@Repository
@RequiredArgsConstructor
public class PriceProductRepositoryImpl implements PriceProductRepository {
    
    private final JpaPriceProductRepository jpaPriceProductRepository;
    
    @Override
    public Optional<PriceProduct> findById(Integer id) {
        return jpaPriceProductRepository.findById(id);
    }
    
    @Override
    public List<PriceProduct> findAll() {
        return jpaPriceProductRepository.findAll();
    }
    
    @Override
    public boolean existsById(Integer id) {
        return jpaPriceProductRepository.existsById(id);
    }
    
    @Override
    public long count() {
        return jpaPriceProductRepository.count();
    }
    
    @Override
    public List<PriceProduct> findByPriceListId(Integer priceListId) {
        return jpaPriceProductRepository.findByPriceListId(priceListId);
    }
    
    @Override
    public List<PriceProduct> findByProductModelId(Integer productModelId) {
        return jpaPriceProductRepository.findByProductModelId(productModelId);
    }
    
    @Override
    public List<PriceProduct> findByPriceListIdAndProductModelId(Integer priceListId, Integer productModelId) {
        return jpaPriceProductRepository.findByPriceListIdAndProductModelId(priceListId, productModelId);
    }
    
    @Override
    public Optional<PriceProduct> findByPriceListIdAndProductModelIdAndDeleteDateIsNull(
            Integer priceListId, Integer productModelId) {
        return jpaPriceProductRepository.findByPriceListIdAndProductModelIdAndDeleteDateIsNull(priceListId, productModelId);
    }
    
    @Override
    public List<PriceProduct> findByUserIdCreate(Integer userIdCreate) {
        return jpaPriceProductRepository.findByUserIdCreate(userIdCreate);
    }
    
    @Override
    public List<PriceProduct> findByUserIdDelete(Integer userIdDelete) {
        return jpaPriceProductRepository.findByUserIdDelete(userIdDelete);
    }
    
    @Override
    public List<PriceProduct> findByCreateDateAfter(LocalDateTime date) {
        return jpaPriceProductRepository.findByCreateDateAfter(date);
    }
    
    @Override
    public List<PriceProduct> findByCreateDateBefore(LocalDateTime date) {
        return jpaPriceProductRepository.findByCreateDateBefore(date);
    }
    
    @Override
    public List<PriceProduct> findByDeleteDateIsNull() {
        return jpaPriceProductRepository.findByDeleteDateIsNull();
    }
}
