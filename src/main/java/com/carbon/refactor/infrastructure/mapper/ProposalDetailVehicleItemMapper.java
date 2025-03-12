package com.carbon.refactor.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.domain.entity.ProposalDetailVehicle;
import com.carbon.refactor.domain.entity.ProposalDetailVehicleItem;
import com.carbon.refactor.domain.service.ProposalDetailVehicleService;
import com.carbon.refactor.infrastructure.dto.request.ProposalDetailVehicleItemRequestDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalDetailVehicleItemResponseDTO;

/**
 * Mapper for converting between ProposalDetailVehicleItem entities and DTOs.
 */
@Component
@RequiredArgsConstructor
public class ProposalDetailVehicleItemMapper {
    
    private final ProposalDetailVehicleService proposalDetailVehicleService;
    
    /**
     * Convert a ProposalDetailVehicleItemRequestDTO to a ProposalDetailVehicleItem entity.
     * 
     * @param dto The DTO to convert
     * @return The converted entity
     */
    public ProposalDetailVehicleItem toEntity(ProposalDetailVehicleItemRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        ProposalDetailVehicle proposalDetailVehicle = null;
        if (dto.getProposalDetailVehicleId() != null) {
            proposalDetailVehicle = proposalDetailVehicleService.findById(dto.getProposalDetailVehicleId());
        }
        
        return ProposalDetailVehicleItem.builder()
                .amountDiscount(dto.getAmountDiscount())
                .percentDiscount(dto.getPercentDiscount())
                .finalPrice(dto.getFinalPrice())
                .tablePriceTax(dto.getTablePriceTax())
                .forFree(dto.getForFree())
                .proposalDetailVehicle(proposalDetailVehicle)
                .sellerId(dto.getSellerId())
                .priceItemId(dto.getPriceItemId())
                .priceItemModelId(dto.getPriceItemModelId())
                .amendment(dto.getAmendment())
                .immediateDelivery(dto.getImmediateDelivery())
                .build();
    }
    
    /**
     * Convert a ProposalDetailVehicleItem entity to a ProposalDetailVehicleItemResponseDTO.
     * 
     * @param entity The entity to convert
     * @return The converted DTO
     */
    public ProposalDetailVehicleItemResponseDTO toDto(ProposalDetailVehicleItem entity) {
        if (entity == null) {
            return null;
        }
        
        Integer proposalDetailVehicleId = null;
        if (entity.getProposalDetailVehicle() != null) {
            proposalDetailVehicleId = entity.getProposalDetailVehicle().getId();
        }
        
        return ProposalDetailVehicleItemResponseDTO.builder()
                .id(entity.getId())
                .amountDiscount(entity.getAmountDiscount())
                .percentDiscount(entity.getPercentDiscount())
                .finalPrice(entity.getFinalPrice())
                .tablePriceTax(entity.getTablePriceTax())
                .forFree(entity.getForFree())
                .proposalDetailVehicleId(proposalDetailVehicleId)
                .sellerId(entity.getSellerId())
                .priceItemId(entity.getPriceItemId())
                .priceItemModelId(entity.getPriceItemModelId())
                .amendment(entity.getAmendment())
                .immediateDelivery(entity.getImmediateDelivery())
                .build();
    }
    
    /**
     * Convert a list of ProposalDetailVehicleItem entities to a list of ProposalDetailVehicleItemResponseDTOs.
     * 
     * @param entities The entities to convert
     * @return The converted DTOs
     */
    public List<ProposalDetailVehicleItemResponseDTO> toDtoList(List<ProposalDetailVehicleItem> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Update a ProposalDetailVehicleItem entity with data from a ProposalDetailVehicleItemRequestDTO.
     * 
     * @param entity The entity to update
     * @param dto The DTO containing the new data
     * @return The updated entity
     */
    public ProposalDetailVehicleItem updateEntityFromDto(ProposalDetailVehicleItem entity, ProposalDetailVehicleItemRequestDTO dto) {
        if (entity == null || dto == null) {
            return entity;
        }
        
        entity.setAmountDiscount(dto.getAmountDiscount());
        entity.setPercentDiscount(dto.getPercentDiscount());
        entity.setFinalPrice(dto.getFinalPrice());
        entity.setTablePriceTax(dto.getTablePriceTax());
        entity.setForFree(dto.getForFree());
        
        if (dto.getProposalDetailVehicleId() != null) {
            ProposalDetailVehicle proposalDetailVehicle = proposalDetailVehicleService.findById(dto.getProposalDetailVehicleId());
            entity.setProposalDetailVehicle(proposalDetailVehicle);
        }
        
        entity.setSellerId(dto.getSellerId());
        entity.setPriceItemId(dto.getPriceItemId());
        entity.setPriceItemModelId(dto.getPriceItemModelId());
        entity.setAmendment(dto.getAmendment());
        entity.setImmediateDelivery(dto.getImmediateDelivery());
        
        return entity;
    }
}
