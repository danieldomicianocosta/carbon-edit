package com.carbon.refactor.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.domain.entity.ProposalDetailVehicleItem;

/**
 * Repository interface for ProposalDetailVehicleItem entities.
 */
@Repository
public interface ProposalDetailVehicleItemRepository extends JpaRepository<ProposalDetailVehicleItem, Integer> {
    
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
     * Delete all proposal detail vehicle items by proposal detail vehicle ID.
     * 
     * @param proposalDetailVehicleId The proposal detail vehicle ID to delete items for
     */
    void deleteByProposalDetailVehicleId(Integer proposalDetailVehicleId);
    
    /**
     * Check if any proposal detail vehicle items exist for the given proposal detail vehicle ID.
     * 
     * @param proposalDetailVehicleId The proposal detail vehicle ID to check
     * @return true if any proposal detail vehicle items exist, false otherwise
     */
    boolean existsByProposalDetailVehicleId(Integer proposalDetailVehicleId);
}
