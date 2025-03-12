package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.Document;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service interface for read-only operations on Document entities.
 */
public interface DocumentService extends ReadOnlyService<Document, Integer> {
    
    /**
     * Find documents by file name containing the given text.
     * 
     * @param fileName The file name to search for
     * @return A list of documents with file names containing the given text
     */
    List<Document> findByFileNameContaining(String fileName);
    
    /**
     * Find documents by content type.
     * 
     * @param contentType The content type to search for
     * @return A list of documents with the given content type
     */
    List<Document> findByContentType(String contentType);
    
    /**
     * Find documents by description containing the given text.
     * 
     * @param description The description to search for
     * @return A list of documents with descriptions containing the given text
     */
    List<Document> findByDescriptionContaining(String description);
    
    /**
     * Find documents by user ID.
     * 
     * @param userId The user ID
     * @return A list of documents created by the given user
     */
    List<Document> findByUserId(Integer userId);
    
    /**
     * Find documents by type classifier ID.
     * 
     * @param typeClassifierId The type classifier ID
     * @return A list of documents with the given type classifier
     */
    List<Document> findByTypeClassifierId(Integer typeClassifierId);
    
    /**
     * Find documents created after the given date.
     * 
     * @param date The date to search from
     * @return A list of documents created after the given date
     */
    List<Document> findByCreateDateAfter(LocalDateTime date);
    
    /**
     * Find documents created before the given date.
     * 
     * @param date The date to search to
     * @return A list of documents created before the given date
     */
    List<Document> findByCreateDateBefore(LocalDateTime date);
}
