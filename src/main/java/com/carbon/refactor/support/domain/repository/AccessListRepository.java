package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.AccessList;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on AccessList entities.
 */
public interface AccessListRepository extends ReadOnlyRepository<AccessList, Integer> {
    
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
    List<AccessList> findByNameContaining(String name);
    
    /**
     * Find access lists by menu ID.
     * 
     * @param menuId The menu ID
     * @return A list of access lists for the given menu
     */
    List<AccessList> findByMenuId(Integer menuId);
}
