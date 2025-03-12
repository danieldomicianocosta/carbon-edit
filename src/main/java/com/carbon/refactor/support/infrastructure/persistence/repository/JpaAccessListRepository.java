package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.AccessList;

/**
 * JPA repository interface for AccessList entities.
 */
@Repository
public interface JpaAccessListRepository extends JpaRepository<AccessList, Integer> {
    
    /**
     * Find an access list by its name.
     * 
     * @param name The name to search for
     * @return An Optional containing the access list if found, or empty if not found
     */
    Optional<AccessList> findByName(String name);
    
    /**
     * Find access lists by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of access lists with names containing the given text
     */
    List<AccessList> findByNameContainingIgnoreCase(String name);
    
    /**
     * Find access lists by menu ID.
     * 
     * @param menuId The menu ID
     * @return A list of access lists for the given menu
     */
    List<AccessList> findByMenuId(Integer menuId);
}
