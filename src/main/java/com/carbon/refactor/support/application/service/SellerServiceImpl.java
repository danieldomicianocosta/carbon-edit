package com.carbon.refactor.support.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.support.domain.entity.Seller;
import com.carbon.refactor.support.domain.repository.SellerRepository;
import com.carbon.refactor.support.domain.service.SellerService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the SellerService interface.
 */
@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    
    private final SellerRepository sellerRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Seller> findById(Integer id) {
        return sellerRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return sellerRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long count() {
        return sellerRepository.count();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Seller> findByActiveTrue() {
        return sellerRepository.findByActiveTrue();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Seller> findByPersonId(Integer personId) {
        return sellerRepository.findByPersonId(personId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Seller> findByJobId(Integer jobId) {
        return sellerRepository.findByJobId(jobId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Seller> findByTechnicalAssistanceTrue() {
        return sellerRepository.findByTechnicalAssistanceTrue();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Seller> findByPersonIdAndJobId(Integer personId, Integer jobId) {
        return sellerRepository.findByPersonIdAndJobId(personId, jobId);
    }
}
