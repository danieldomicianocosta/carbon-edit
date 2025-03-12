package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.Seller;
import com.carbon.refactor.support.domain.repository.SellerRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the SellerRepository interface using JPA.
 */
@Repository
@RequiredArgsConstructor
public class SellerRepositoryImpl implements SellerRepository {
    
    private final JpaSellerRepository jpaSellerRepository;
    
    @Override
    public Optional<Seller> findById(Integer id) {
        return jpaSellerRepository.findById(id);
    }
    
    @Override
    public List<Seller> findAll() {
        return jpaSellerRepository.findAll();
    }
    
    @Override
    public boolean existsById(Integer id) {
        return jpaSellerRepository.existsById(id);
    }
    
    @Override
    public long count() {
        return jpaSellerRepository.count();
    }
    
    @Override
    public List<Seller> findByActiveTrue() {
        return jpaSellerRepository.findByActiveTrue();
    }
    
    @Override
    public List<Seller> findByPersonId(Integer personId) {
        return jpaSellerRepository.findByPersonId(personId);
    }
    
    @Override
    public List<Seller> findByJobId(Integer jobId) {
        return jpaSellerRepository.findByJobId(jobId);
    }
    
    @Override
    public List<Seller> findByTechnicalAssistanceTrue() {
        return jpaSellerRepository.findByTechnicalAssistanceTrue();
    }
    
    @Override
    public Optional<Seller> findByPersonIdAndJobId(Integer personId, Integer jobId) {
        return jpaSellerRepository.findByPersonIdAndJobId(personId, jobId);
    }
}
