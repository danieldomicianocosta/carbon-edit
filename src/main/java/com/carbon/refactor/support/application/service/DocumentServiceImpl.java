package com.carbon.refactor.support.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.support.domain.entity.Document;
import com.carbon.refactor.support.domain.repository.DocumentRepository;
import com.carbon.refactor.support.domain.service.DocumentService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the DocumentService interface.
 */
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    
    private final DocumentRepository documentRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Document> findById(Integer id) {
        return documentRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Document> findAll() {
        return documentRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return documentRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long count() {
        return documentRepository.count();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Document> findByFileNameContaining(String fileName) {
        return documentRepository.findByFileNameContaining(fileName);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Document> findByContentType(String contentType) {
        return documentRepository.findByContentType(contentType);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Document> findByDescriptionContaining(String description) {
        // Since the repository doesn't have this method, we'll filter the results in memory
        return documentRepository.findAll().stream()
                .filter(doc -> doc.getDescription() != null && 
                        doc.getDescription().toLowerCase().contains(description.toLowerCase()))
                .toList();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Document> findByUserId(Integer userId) {
        return documentRepository.findByUserId(userId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Document> findByTypeClassifierId(Integer typeClassifierId) {
        return documentRepository.findByTypeClaId(typeClassifierId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Document> findByCreateDateAfter(LocalDateTime date) {
        return documentRepository.findByCreateDateAfter(date);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Document> findByCreateDateBefore(LocalDateTime date) {
        return documentRepository.findByCreateDateBefore(date);
    }
}
