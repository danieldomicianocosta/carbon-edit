package com.carbon.refactor.application.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.application.dto.ProposalDetailRequestDTO;
import com.carbon.refactor.application.dto.ProposalDetailResponseDTO;
import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.entity.ProposalDetail;
import com.carbon.refactor.domain.service.ProposalService;

@Component
@RequiredArgsConstructor
public class ProposalDetailMapper {
    
    private final ProposalService proposalService;
    
    public ProposalDetail toEntity(ProposalDetailRequestDTO dto) {
        Proposal proposal = proposalService.findById(dto.getProposalId());
        
        return ProposalDetail.builder()
                .proposal(proposal)
                .sellerId(dto.getSellerId())
                .internSaleSellerId(dto.getInternSaleSellerId())
                .channelId(dto.getChannelId())
                .partnerId(dto.getPartnerId())
                .userId(dto.getUserId())
                .purchaseOrderService(dto.getPurchaseOrderService())
                .purchaseOrderProduct(dto.getPurchaseOrderProduct())
                .purchaseOrderDocumentation(dto.getPurchaseOrderDocumentation())
                .internalComission(dto.getInternalComission())
                .internSaleAdditive(dto.getInternSaleAdditive())
                .sellerAdditive(dto.getSellerAdditive())
                .saleDateAdditive(dto.getSaleDateAdditive())
                .build();
    }
    
    public ProposalDetail toEntity(ProposalDetailRequestDTO dto, Integer id) {
        ProposalDetail entity = toEntity(dto);
        entity.setId(id);
        return entity;
    }
    
    public ProposalDetailResponseDTO toDto(ProposalDetail entity) {
        return ProposalDetailResponseDTO.builder()
                .id(entity.getId())
                .proposalId(entity.getProposal().getId())
                .proposalNumber(entity.getProposal().getProposalNumber())
                .sellerId(entity.getSellerId())
                .internSaleSellerId(entity.getInternSaleSellerId())
                .channelId(entity.getChannelId())
                .partnerId(entity.getPartnerId())
                .userId(entity.getUserId())
                .purchaseOrderService(entity.getPurchaseOrderService())
                .purchaseOrderProduct(entity.getPurchaseOrderProduct())
                .purchaseOrderDocumentation(entity.getPurchaseOrderDocumentation())
                .internalComission(entity.getInternalComission())
                .internSaleAdditive(entity.getInternSaleAdditive())
                .sellerAdditive(entity.getSellerAdditive())
                .saleDateAdditive(entity.getSaleDateAdditive())
                .build();
    }
}
