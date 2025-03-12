package com.carbon.refactor.application.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.entity.ProposalDetailVehicle;
import com.carbon.refactor.domain.exception.BusinessException;
import com.carbon.refactor.domain.exception.EntityNotFoundException;
import com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException;
import com.carbon.refactor.domain.repository.ProposalDetailVehicleItemRepository;
import com.carbon.refactor.domain.repository.ProposalDetailVehicleRepository;
import com.carbon.refactor.domain.service.ProposalDetailService;
import com.carbon.refactor.domain.service.ProposalDetailVehicleService;
import com.carbon.refactor.support.domain.entity.PriceList;
import com.carbon.refactor.support.domain.entity.PriceProduct;
import com.carbon.refactor.support.domain.service.PriceListService;
import com.carbon.refactor.support.domain.service.PriceProductService;

@Service
@RequiredArgsConstructor
public class ProposalDetailVehicleServiceImpl implements ProposalDetailVehicleService {
    
    private final ProposalDetailVehicleRepository proposalDetailVehicleRepository;
    private final ProposalDetailService proposalDetailService;
    private final PriceProductService priceProductService;
    private final PriceListService priceListService;
    private final ProposalDetailVehicleItemRepository proposalDetailVehicleItemRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalDetailVehicle> findAll() {
        return proposalDetailVehicleRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProposalDetailVehicle findById(Integer id) {
        return proposalDetailVehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProposalDetailVehicle", id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalDetailVehicle> findByProposalDetailId(Integer proposalDetailId) {
        return proposalDetailVehicleRepository.findByProposalDetailId(proposalDetailId);
    }
    
    @Override
    @Transactional
    public ProposalDetailVehicle create(ProposalDetailVehicle proposalDetailVehicle) {
        validateForeignKeys(proposalDetailVehicle);
        validateProposalAndPriceListValidity(proposalDetailVehicle);
        
        return proposalDetailVehicleRepository.save(proposalDetailVehicle);
    }
    
    @Override
    @Transactional
    public ProposalDetailVehicle update(Integer id, ProposalDetailVehicle proposalDetailVehicle) {
        if (!proposalDetailVehicleRepository.existsById(id)) {
            throw new EntityNotFoundException("ProposalDetailVehicle", id);
        }
        
        validateForeignKeys(proposalDetailVehicle);
        validateProposalAndPriceListValidity(proposalDetailVehicle);
        
        proposalDetailVehicle.setId(id);
        return proposalDetailVehicleRepository.save(proposalDetailVehicle);
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        if (!proposalDetailVehicleRepository.existsById(id)) {
            throw new EntityNotFoundException("ProposalDetailVehicle", id);
        }
        
        // First delete all related ProposalDetailVehicleItem entities
        proposalDetailVehicleItemRepository.deleteByProposalDetailVehicleId(id);
        
        // Then delete the ProposalDetailVehicle entity
        proposalDetailVehicleRepository.deleteById(id);
    }
    
    @Override
    @Transactional
    public void deleteByProposalDetailId(Integer proposalDetailId) {
        // Get all ProposalDetailVehicle entities for this proposalDetailId
        List<ProposalDetailVehicle> vehicles = proposalDetailVehicleRepository.findByProposalDetailId(proposalDetailId);
        
        // Delete all related ProposalDetailVehicleItem entities first
        for (ProposalDetailVehicle vehicle : vehicles) {
            proposalDetailVehicleItemRepository.deleteByProposalDetailVehicleId(vehicle.getId());
        }
        
        // Then delete the ProposalDetailVehicle entities
        proposalDetailVehicleRepository.deleteByProposalDetailId(proposalDetailId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return proposalDetailVehicleRepository.existsById(id);
    }
    
    /**
     * Validates that both the proposal and price list are valid according to the business rules.
     * Throws a BusinessException if the proposal and price list are both expired.
     * 
     * @param proposalDetailVehicle The ProposalDetailVehicle to validate
     * @throws BusinessException if both the proposal and price list are expired
     * @throws EntityNotFoundException if the PriceProduct or PriceList cannot be found
     */
    private void validateProposalAndPriceListValidity(ProposalDetailVehicle proposalDetailVehicle) {
        // Get the PriceList.endDate through the relationships
        Integer priceProductId = proposalDetailVehicle.getPriceProductId();
        Optional<PriceProduct> priceProductOpt = priceProductService.findById(priceProductId);
        
        if (!priceProductOpt.isPresent()) {
            throw new EntityNotFoundException("PriceProduct", priceProductId);
        }
        
        PriceProduct priceProduct = priceProductOpt.get();
        Integer priceListId = priceProduct.getPriceListId();
        
        Optional<PriceList> priceListOpt = priceListService.findById(priceListId);
        
        if (!priceListOpt.isPresent()) {
            throw new EntityNotFoundException("PriceList", priceListId);
        }
        
        PriceList priceList = priceListOpt.get();
        LocalDate priceListEndDate = priceList.getEndDate();
        
        // Get the Proposal.validityDate through the relationships
        Proposal proposal = proposalDetailVehicle.getProposalDetail().getProposal();
        LocalDateTime proposalValidityDate = proposal.getValidityDate();
        
        // Current date for comparison
        LocalDate currentDate = LocalDate.now();
        
        // Check conditions for update
        boolean isPriceListValid = priceListEndDate.isEqual(currentDate) || priceListEndDate.isAfter(currentDate);
        boolean isProposalValid = proposalValidityDate.toLocalDate().isEqual(currentDate) || 
                                 proposalValidityDate.toLocalDate().isAfter(currentDate);
        
        // Apply the update rules based on the table conditions
        if (!isPriceListValid && !isProposalValid) {
            throw new BusinessException("Não é possível atualizar a proposta pois a proposta e a tabema de preços estão vencidas.");
        }
    }
    
    private void validateForeignKeys(ProposalDetailVehicle proposalDetailVehicle) {
        // Validate proposal detail exists
        if (proposalDetailVehicle.getProposalDetail() != null) {
            Integer proposalDetailId = proposalDetailVehicle.getProposalDetail().getId();
            if (!proposalDetailService.existsById(proposalDetailId)) {
                throw new ForeignKeyConstraintViolationException("ProposalDetail", "ppd_id", proposalDetailId);
            }
        } else {
            throw new IllegalArgumentException("Proposal detail is required");
        }
        
        // Validate vehicle exists
        if (proposalDetailVehicle.getVehicleId() != null) {
            if (proposalDetailVehicle.getVehicleId() <= 0) {
                throw new ForeignKeyConstraintViolationException("Vehicle", "vhe_id", proposalDetailVehicle.getVehicleId());
            }
        }
        
        // Validate model exists
        if (proposalDetailVehicle.getModelId() != null) {
            if (proposalDetailVehicle.getModelId() <= 0) {
                throw new ForeignKeyConstraintViolationException("Model", "mdl_id", proposalDetailVehicle.getModelId());
            }
        }
        
        // Validate price product exists
        if (proposalDetailVehicle.getPriceProductId() != null) {
            if (proposalDetailVehicle.getPriceProductId() <= 0) {
                throw new ForeignKeyConstraintViolationException("PriceProduct", "ppr_id", proposalDetailVehicle.getPriceProductId());
            }
        }
        
        // Validate specific payment condition exists
        if (proposalDetailVehicle.getSpecificPaymentConditionId() != null) {
            if (proposalDetailVehicle.getSpecificPaymentConditionId() <= 0) {
                throw new ForeignKeyConstraintViolationException("SpecificPaymentCondition", "spc_id", proposalDetailVehicle.getSpecificPaymentConditionId());
            }
        }
        
        // Validate specific payment condition amendment exists
        if (proposalDetailVehicle.getSpecificPaymentConditionAmmendmentId() != null) {
            if (proposalDetailVehicle.getSpecificPaymentConditionAmmendmentId() <= 0) {
                throw new ForeignKeyConstraintViolationException("SpecificPaymentCondition", "spc_id_ammendment", proposalDetailVehicle.getSpecificPaymentConditionAmmendmentId());
            }
        }
    }
}
