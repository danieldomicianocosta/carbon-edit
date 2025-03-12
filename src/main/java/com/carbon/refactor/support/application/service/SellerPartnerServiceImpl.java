package com.carbon.refactor.support.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.support.domain.entity.SellerPartner;
import com.carbon.refactor.support.domain.repository.SellerPartnerRepository;
import com.carbon.refactor.support.domain.service.SellerPartnerService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the SellerPartnerService interface.
 */
@Service
@RequiredArgsConstructor
public class SellerPartnerServiceImpl implements SellerPartnerService {
    
    private final SellerPartnerRepository sellerPartnerRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<SellerPartner> findById(Integer id) {
        return sellerPartnerRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SellerPartner> findAll() {
        return sellerPartnerRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return sellerPartnerRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long count() {
        return sellerPartnerRepository.count();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SellerPartner> findBySellerId(Integer sellerId) {
        return sellerPartnerRepository.findBySellerId(sellerId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SellerPartner> findByPartnerId(Integer partnerId) {
        return sellerPartnerRepository.findByPartnerId(partnerId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<SellerPartner> findBySellerIdAndPartnerId(Integer sellerId, Integer partnerId) {
        return sellerPartnerRepository.findBySellerIdAndPartnerId(sellerId, partnerId);
    }
}
