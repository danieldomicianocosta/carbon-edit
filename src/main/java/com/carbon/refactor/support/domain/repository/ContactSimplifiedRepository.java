package com.carbon.refactor.support.domain.repository;

import java.util.List;

import com.carbon.refactor.support.domain.entity.ContactSimplified;

/**
 * Repository interface for read-only operations on ContactSimplified entities.
 */
public interface ContactSimplifiedRepository extends ReadOnlyRepository<ContactSimplified, Integer> {
    
    /**
     * Find contacts by proposal ID.
     * 
     * @param proposalId The proposal ID
     * @return A list of contacts for the given proposal
     */
    List<ContactSimplified> findByProposalId(Integer proposalId);
    
    /**
     * Find contacts by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of contacts with names containing the given text
     */
    List<ContactSimplified> findByNameContaining(String name);
    
    /**
     * Find contacts by email containing the given text.
     * 
     * @param email The email to search for
     * @return A list of contacts with emails containing the given text
     */
    List<ContactSimplified> findByEmailContaining(String email);
    
    /**
     * Find contacts by document.
     * 
     * @param document The document to search for
     * @return A list of contacts with the given document
     */
    List<ContactSimplified> findByDocument(String document);
    
    /**
     * Find contacts by type classifier.
     * 
     * @param typeCla The type classifier
     * @return A list of contacts with the given type classifier
     */
    List<ContactSimplified> findByTypeCla(Integer typeCla);
}
