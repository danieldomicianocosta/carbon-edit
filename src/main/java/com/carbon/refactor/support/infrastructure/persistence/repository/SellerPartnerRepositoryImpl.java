package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.SellerPartner;
import com.carbon.refactor.support.domain.entity.SellerPartnerId;
import com.carbon.refactor.support.domain.repository.SellerPartnerRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the SellerPartnerRepository interface using JPA.
 */
@Repository
@RequiredArgsConstructor
public class SellerPartnerRepositoryImpl implements SellerPartnerRepository {
    
    private final JpaSellerPartnerRepository jpaSellerPartnerRepository;
    
    @Override
    public Optional<SellerPartner> findById(SellerPartnerId id) {
        return jpaSellerPartnerRepository.findById(id);
    }
    
    @Override
    public List<SellerPartner> findAll() {
        return jpaSellerPartnerRepository.findAll();
    }
    
    @Override
    public boolean existsById(SellerPartnerId id) {
        return jpaSellerPartnerRepository.existsById(id);
    }
    
    @Override
    public long count() {
        return jpaSellerPartnerRepository.count();
    }
    
    @Override
    public List<SellerPartner> findBySellerId(Integer sellerId) {
        return jpaSellerPartnerRepository.findBySellerId(sellerId);
    }
    
    @Override
    public List<SellerPartner> findByPartnerId(Integer partnerId) {
        return jpaSellerPartnerRepository.findByPartnerId(partnerId);
    }
    
    @Override
    public Optional<SellerPartner> findBySellerIdAndPartnerId(Integer sellerId, Integer partnerId) {
        return jpaSellerPartnerRepository.findBySellerIdAndPartnerId(sellerId, partnerId);
    }
}
