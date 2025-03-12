package com.carbon.refactor.domain.service;

import java.util.List;

import com.carbon.refactor.domain.entity.ProposalDetailVehicleItem;

/**
 * Service interface for managing ProposalDetailVehicleItem entities.
 */
public interface ProposalDetailVehicleItemService {
    
    /**
     * Find all proposal detail vehicle items.
     * 
     * @return A list of all proposal detail vehicle items
     */
    List<ProposalDetailVehicleItem> findAll();
    
    /**
     * Find a proposal detail vehicle item by ID.
     * 
     * @param id The ID to search for
     * @return The proposal detail vehicle item with the given ID
     * @throws com.carbon.refactor.domain.exception.EntityNotFoundException if no proposal detail vehicle item is found with the given ID
     */
    ProposalDetailVehicleItem findById(Integer id);
    
    /**
     * Find all proposal detail vehicle items by proposal detail vehicle ID.
     * 
     * @param proposalDetailVehicleId The proposal detail vehicle ID to search for
     * @return A list of proposal detail vehicle items for the given proposal detail vehicle
     */
    List<ProposalDetailVehicleItem> findByProposalDetailVehicleId(Integer proposalDetailVehicleId);
    
    /**
     * Find all proposal detail vehicle items by seller ID.
     * 
     * @param sellerId The seller ID to search for
     * @return A list of proposal detail vehicle items for the given seller
     */
    List<ProposalDetailVehicleItem> findBySellerId(Integer sellerId);
    
    /**
     * Find all proposal detail vehicle items by price item ID.
     * 
     * @param priceItemId The price item ID to search for
     * @return A list of proposal detail vehicle items for the given price item
     */
    List<ProposalDetailVehicleItem> findByPriceItemId(Integer priceItemId);
    
    /**
     * Find all proposal detail vehicle items by price item model ID.
     * 
     * @param priceItemModelId The price item model ID to search for
     * @return A list of proposal detail vehicle items for the given price item model
     */
    List<ProposalDetailVehicleItem> findByPriceItemModelId(Integer priceItemModelId);
    
    /**
     * Create a new proposal detail vehicle item.
     * 
     * @param proposalDetailVehicleItem The proposal detail vehicle item to create
     * @return The created proposal detail vehicle item
     * @throws com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException if any foreign key constraints are violated
     */
    ProposalDetailVehicleItem create(ProposalDetailVehicleItem proposalDetailVehicleItem);
    
    /**
     * Update an existing proposal detail vehicle item.
     * 
     * @param id The ID of the proposal detail vehicle item to update
     * @param proposalDetailVehicleItem The updated proposal detail vehicle item data
     * @return The updated proposal detail vehicle item
     * @throws com.carbon.refactor.domain.exception.EntityNotFoundException if no proposal detail vehicle item is found with the given ID
     * @throws com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException if any foreign key constraints are violated
     */
    ProposalDetailVehicleItem update(Integer id, ProposalDetailVehicleItem proposalDetailVehicleItem);
    
    /**
     * Delete a proposal detail vehicle item by ID.
     * 
     * @param id The ID of the proposal detail vehicle item to delete
     * @throws com.carbon.refactor.domain.exception.EntityNotFoundException if no proposal detail vehicle item is found with the given ID
     */
    void delete(Integer id);
    
    /**
     * Delete all proposal detail vehicle items by proposal detail vehicle ID.
     * 
     * @param proposalDetailVehicleId The proposal detail vehicle ID to delete items for
     */
    void deleteByProposalDetailVehicleId(Integer proposalDetailVehicleId);
    
    /**
     * Check if a proposal detail vehicle item exists with the given ID.
     * 
     * @param id The ID to check
     * @return true if a proposal detail vehicle item exists with the given ID, false otherwise
     */
    boolean existsById(Integer id);
    
    /**
     * Check if any proposal detail vehicle items exist for the given proposal detail vehicle ID.
     * 
     * @param proposalDetailVehicleId The proposal detail vehicle ID to check
     * @return true if any proposal detail vehicle items exist, false otherwise
     */
    boolean existsByProposalDetailVehicleId(Integer proposalDetailVehicleId);
}
