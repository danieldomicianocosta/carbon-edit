package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.Document;
import com.carbon.refactor.support.domain.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the DocumentRepository interface using JPA.
 */
@Repository
@RequiredArgsConstructor
public class DocumentRepositoryImpl implements DocumentRepository {
    
    private final JpaDocumentRepository jpaDocumentRepository;
    
    @Override
    public Optional<Document> findById(Integer id) {
        return jpaDocumentRepository.findById(id);
    }
    
    @Override
    public List<Document> findAll() {
        return jpaDocumentRepository.findAll();
    }
    
    @Override
    public boolean existsById(Integer id) {
        return jpaDocumentRepository.existsById(id);
    }
    
    @Override
    public long count() {
        return jpaDocumentRepository.count();
    }
    
    @Override
    public List<Document> findByFileNameContaining(String fileName) {
        return jpaDocumentRepository.findByFileNameContainingIgnoreCase(fileName);
    }
    
    @Override
    public List<Document> findByContentType(String contentType) {
        return jpaDocumentRepository.findByContentType(contentType);
    }
    
    @Override
    public List<Document> findByUserId(Integer userId) {
        return jpaDocumentRepository.findByUserId(userId);
    }
    
    @Override
    public List<Document> findByTypeClaId(Integer typeClaId) {
        return jpaDocumentRepository.findByTypeClaId(typeClaId);
    }
    
    @Override
    public List<Document> findByCreateDateAfter(LocalDateTime date) {
        return jpaDocumentRepository.findByCreateDateAfter(date);
    }
    
    @Override
    public List<Document> findByCreateDateBefore(LocalDateTime date) {
        return jpaDocumentRepository.findByCreateDateBefore(date);
    }
}
