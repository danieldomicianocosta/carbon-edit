package com.carbon.refactor.support.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.support.domain.entity.PriceProduct;
import com.carbon.refactor.support.domain.repository.PriceProductRepository;
import com.carbon.refactor.support.domain.service.PriceProductService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the PriceProductService interface.
 */
@Service
@RequiredArgsConstructor
public class PriceProductServiceImpl implements PriceProductService {
    
    private final PriceProductRepository priceProductRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<PriceProduct> findById(Integer id) {
        return priceProductRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceProduct> findAll() {
        return priceProductRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return priceProductRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long count() {
        return priceProductRepository.count();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceProduct> findByPriceListId(Integer priceListId) {
        return priceProductRepository.findByPriceListId(priceListId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceProduct> findByProductModelId(Integer productModelId) {
        return priceProductRepository.findByProductModelId(productModelId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceProduct> findByPriceListIdAndProductModelId(Integer priceListId, Integer productModelId) {
        return priceProductRepository.findByPriceListIdAndProductModelId(priceListId, productModelId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<PriceProduct> findByPriceListIdAndProductModelIdAndDeleteDateIsNull(
            Integer priceListId, Integer productModelId) {
        return priceProductRepository.findByPriceListIdAndProductModelIdAndDeleteDateIsNull(priceListId, productModelId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceProduct> findByUserIdCreate(Integer userIdCreate) {
        return priceProductRepository.findByUserIdCreate(userIdCreate);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceProduct> findByUserIdDelete(Integer userIdDelete) {
        return priceProductRepository.findByUserIdDelete(userIdDelete);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceProduct> findByCreateDateAfter(LocalDateTime date) {
        return priceProductRepository.findByCreateDateAfter(date);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceProduct> findByCreateDateBefore(LocalDateTime date) {
        return priceProductRepository.findByCreateDateBefore(date);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PriceProduct> findByDeleteDateIsNull() {
        return priceProductRepository.findByDeleteDateIsNull();
    }
}
