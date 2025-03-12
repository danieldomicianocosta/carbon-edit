package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.Item;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on Item entities.
 */
public interface ItemService extends ReadOnlyService<Item, Integer> {
    
    /**
     * Find a item by its name.
     * 
     * @param name The name to search for
     * @return An Optional containing the item if found, or empty if not found
     */
    Optional<Item> findByName(String name);
    
    /**
     * Find items by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of items with names containing the given text
     */
    List<Item> findByNameContaining(String name);
    
    /**
     * Find items by code.
     * 
     * @param code The code to search for
     * @return A list of items with the given code
     */
    List<Item> findByCode(String code);
    
    /**
     * Find items by sequence.
     * 
     * @param seq The sequence to search for
     * @return A list of items with the given sequence
     */
    List<Item> findBySeq(Integer seq);
    
    /**
     * Find items that are for free.
     * 
     * @return A list of items that are for free
     */
    List<Item> findByForFreeTrue();
    
    /**
     * Find items that are generic.
     * 
     * @return A list of items that are generic
     */
    List<Item> findByGenericTrue();
    
    /**
     * Find items by mandatory classifier ID.
     * 
     * @param mandatoryClassifierId The mandatory classifier ID
     * @return A list of items with the given mandatory classifier
     */
    List<Item> findByMandatoryClassifierId(Integer mandatoryClassifierId);
    
    /**
     * Find items by item type ID.
     * 
     * @param itemTypeId The item type ID
     * @return A list of items with the given item type
     */
    List<Item> findByItemTypeId(Integer itemTypeId);
    
    /**
     * Find items by responsibility classifier ID.
     * 
     * @param responsibilityClassifierId The responsibility classifier ID
     * @return A list of items with the given responsibility classifier
     */
    List<Item> findByResponsibilityClassifierId(Integer responsibilityClassifierId);
    
    /**
     * Find items that are highlighted.
     * 
     * @return A list of items that are highlighted
     */
    List<Item> findByHighlightTrue();
    
    /**
     * Find items that are for purchases.
     * 
     * @return A list of items that are for purchases
     */
    List<Item> findByFlagPurchasesTrue();
}
