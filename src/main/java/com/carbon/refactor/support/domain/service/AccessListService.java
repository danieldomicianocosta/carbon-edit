package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.AccessList;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on AccessList entities.
 */
public interface AccessListService extends ReadOnlyService<AccessList, Integer> {
    
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
