package com.carbon.refactor.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.domain.entity.ProposalCommission;
import com.carbon.refactor.domain.entity.ProposalDetail;
import com.carbon.refactor.domain.service.ProposalDetailService;
import com.carbon.refactor.infrastructure.dto.request.ProposalCommissionRequestDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalCommissionResponseDTO;

/**
 * Mapper for converting between ProposalCommission entities and DTOs.
 */
@Component
@RequiredArgsConstructor
public class ProposalCommissionMapper {
    
    private final ProposalDetailService proposalDetailService;
    
    /**
     * Convert a ProposalCommissionRequestDTO to a ProposalCommission entity.
     * 
     * @param dto The DTO to convert
     * @return The converted entity
     */
    public ProposalCommission toEntity(ProposalCommissionRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        ProposalDetail proposalDetail = null;
        if (dto.getProposalDetailId() != null) {
            proposalDetail = proposalDetailService.findById(dto.getProposalDetailId());
        }
        
        return ProposalCommission.builder()
                .commissionedTypeClassifierId(dto.getCommissionedTypeClassifierId())
                .personId(dto.getPersonId())
                .personTypeClassifierId(dto.getPersonTypeClassifierId())
                .dueDate(dto.getDueDate())
                .value(dto.getValue())
                .notes(dto.getNotes())
                .commissionTypeId(dto.getCommissionTypeId())
                .proposalDetail(proposalDetail)
                .accountId(dto.getAccountId())
                .paymentClassifierId(dto.getPaymentClassifierId())
                .bankData(dto.getBankData())
                .numberNf(dto.getNumberNf())
                .build();
    }
    
    /**
     * Convert a ProposalCommission entity to a ProposalCommissionResponseDTO.
     * 
     * @param entity The entity to convert
     * @return The converted DTO
     */
    public ProposalCommissionResponseDTO toDto(ProposalCommission entity) {
        if (entity == null) {
            return null;
        }
        
        Integer proposalDetailId = null;
        if (entity.getProposalDetail() != null) {
            proposalDetailId = entity.getProposalDetail().getId();
        }
        
        return ProposalCommissionResponseDTO.builder()
                .id(entity.getId())
                .commissionedTypeClassifierId(entity.getCommissionedTypeClassifierId())
                .personId(entity.getPersonId())
                .personTypeClassifierId(entity.getPersonTypeClassifierId())
                .dueDate(entity.getDueDate())
                .value(entity.getValue())
                .notes(entity.getNotes())
                .commissionTypeId(entity.getCommissionTypeId())
                .proposalDetailId(proposalDetailId)
                .accountId(entity.getAccountId())
                .paymentClassifierId(entity.getPaymentClassifierId())
                .bankData(entity.getBankData())
                .numberNf(entity.getNumberNf())
                .build();
    }
    
    /**
     * Convert a list of ProposalCommission entities to a list of ProposalCommissionResponseDTOs.
     * 
     * @param entities The entities to convert
     * @return The converted DTOs
     */
    public List<ProposalCommissionResponseDTO> toDtoList(List<ProposalCommission> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Update a ProposalCommission entity with data from a ProposalCommissionRequestDTO.
     * 
     * @param entity The entity to update
     * @param dto The DTO containing the new data
     * @return The updated entity
     */
    public ProposalCommission updateEntityFromDto(ProposalCommission entity, ProposalCommissionRequestDTO dto) {
        if (entity == null || dto == null) {
            return entity;
        }
        
        entity.setCommissionedTypeClassifierId(dto.getCommissionedTypeClassifierId());
        entity.setPersonId(dto.getPersonId());
        entity.setPersonTypeClassifierId(dto.getPersonTypeClassifierId());
        entity.setDueDate(dto.getDueDate());
        entity.setValue(dto.getValue());
        entity.setNotes(dto.getNotes());
        entity.setCommissionTypeId(dto.getCommissionTypeId());
        
        if (dto.getProposalDetailId() != null) {
            ProposalDetail proposalDetail = proposalDetailService.findById(dto.getProposalDetailId());
            entity.setProposalDetail(proposalDetail);
        }
        
        entity.setAccountId(dto.getAccountId());
        entity.setPaymentClassifierId(dto.getPaymentClassifierId());
        entity.setBankData(dto.getBankData());
        entity.setNumberNf(dto.getNumberNf());
        
        return entity;
    }
}
