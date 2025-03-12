package com.carbon.refactor.application.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.domain.entity.ProposalDetailVehicleItem;
import com.carbon.refactor.domain.exception.EntityNotFoundException;
import com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException;
import com.carbon.refactor.domain.repository.ProposalDetailVehicleItemRepository;
import com.carbon.refactor.domain.service.ProposalDetailVehicleItemService;
import com.carbon.refactor.domain.service.ProposalDetailVehicleService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the ProposalDetailVehicleItemService interface.
 */
@Service
@RequiredArgsConstructor
public class ProposalDetailVehicleItemServiceImpl implements ProposalDetailVehicleItemService {
    
    private final ProposalDetailVehicleItemRepository proposalDetailVehicleItemRepository;
    private final ProposalDetailVehicleService proposalDetailVehicleService;
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalDetailVehicleItem> findAll() {
        return proposalDetailVehicleItemRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProposalDetailVehicleItem findById(Integer id) {
        return proposalDetailVehicleItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProposalDetailVehicleItem", id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalDetailVehicleItem> findByProposalDetailVehicleId(Integer proposalDetailVehicleId) {
        return proposalDetailVehicleItemRepository.findByProposalDetailVehicleId(proposalDetailVehicleId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalDetailVehicleItem> findBySellerId(Integer sellerId) {
        return proposalDetailVehicleItemRepository.findBySellerId(sellerId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalDetailVehicleItem> findByPriceItemId(Integer priceItemId) {
        return proposalDetailVehicleItemRepository.findByPriceItemId(priceItemId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalDetailVehicleItem> findByPriceItemModelId(Integer priceItemModelId) {
        return proposalDetailVehicleItemRepository.findByPriceItemModelId(priceItemModelId);
    }
    
    @Override
    @Transactional
    public ProposalDetailVehicleItem create(ProposalDetailVehicleItem proposalDetailVehicleItem) {
        try {
            validateForeignKeys(proposalDetailVehicleItem);
            return proposalDetailVehicleItemRepository.save(proposalDetailVehicleItem);
        } catch (DataIntegrityViolationException e) {
            handleDataIntegrityViolation(e);
            // This line will never be reached, but is needed to satisfy the compiler
            return null;
        }
    }
    
    @Override
    @Transactional
    public ProposalDetailVehicleItem update(Integer id, ProposalDetailVehicleItem proposalDetailVehicleItem) {
        if (!proposalDetailVehicleItemRepository.existsById(id)) {
            throw new EntityNotFoundException("ProposalDetailVehicleItem", id);
        }
        
        try {
            validateForeignKeys(proposalDetailVehicleItem);
            proposalDetailVehicleItem.setId(id);
            return proposalDetailVehicleItemRepository.save(proposalDetailVehicleItem);
        } catch (DataIntegrityViolationException e) {
            handleDataIntegrityViolation(e);
            // This line will never be reached, but is needed to satisfy the compiler
            return null;
        }
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        if (!proposalDetailVehicleItemRepository.existsById(id)) {
            throw new EntityNotFoundException("ProposalDetailVehicleItem", id);
        }
        
        proposalDetailVehicleItemRepository.deleteById(id);
    }
    
    @Override
    @Transactional
    public void deleteByProposalDetailVehicleId(Integer proposalDetailVehicleId) {
        proposalDetailVehicleItemRepository.deleteByProposalDetailVehicleId(proposalDetailVehicleId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return proposalDetailVehicleItemRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByProposalDetailVehicleId(Integer proposalDetailVehicleId) {
        return proposalDetailVehicleItemRepository.existsByProposalDetailVehicleId(proposalDetailVehicleId);
    }
    
    /**
     * Validates that all foreign keys in the proposal detail vehicle item exist.
     * 
     * @param proposalDetailVehicleItem The proposal detail vehicle item to validate
     * @throws ForeignKeyConstraintViolationException if any foreign key constraints are violated
     */
    private void validateForeignKeys(ProposalDetailVehicleItem proposalDetailVehicleItem) {
        // Validate proposal detail vehicle exists
        if (proposalDetailVehicleItem.getProposalDetailVehicle() != null) {
            Integer proposalDetailVehicleId = proposalDetailVehicleItem.getProposalDetailVehicle().getId();
            if (!proposalDetailVehicleService.existsById(proposalDetailVehicleId)) {
                throw new ForeignKeyConstraintViolationException("ProposalDetailVehicle", "pdv_id", proposalDetailVehicleId);
            }
        } else {
            throw new IllegalArgumentException("Proposal detail vehicle is required");
        }
        
        // Validate seller exists
        if (proposalDetailVehicleItem.getSellerId() == null) {
            throw new IllegalArgumentException("Seller is required");
        }
        
        // Other validations can be added here as needed
    }
    
    /**
     * Handles data integrity violations by extracting the constraint name and throwing an appropriate exception.
     * 
     * @param e The DataIntegrityViolationException to handle
     * @throws ForeignKeyConstraintViolationException with details about the violated constraint
     */
    private void handleDataIntegrityViolation(DataIntegrityViolationException e) {
        String message = e.getMessage() != null ? e.getMessage() : "";
        
        if (message.contains("fk_proposal_detail_vehicle_item_price_list_proposal_detail_ve1")) {
            throw new ForeignKeyConstraintViolationException("ProposalDetailVehicle", "pdv_id", "Unknown");
        } else if (message.contains("fk_proposal_detail_vehicle_item_item_price1")) {
            throw new ForeignKeyConstraintViolationException("PriceItem", "pci_id", "Unknown");
        } else if (message.contains("fk_proposal_detail_vehicle_item_item_model_price1")) {
            throw new ForeignKeyConstraintViolationException("PriceItemModel", "pim_id", "Unknown");
        } else {
            throw new ForeignKeyConstraintViolationException("Unknown", "Unknown", "Unknown");
        }
    }
}
