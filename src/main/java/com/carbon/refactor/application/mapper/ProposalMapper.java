package com.carbon.refactor.application.mapper;

import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.ProposalRequestDTO;
import com.carbon.refactor.application.dto.ProposalResponseDTO;
import com.carbon.refactor.domain.entity.Proposal;

@Component
public class ProposalMapper {
    
    public Proposal toEntity(ProposalRequestDTO dto) {
        return Proposal.builder()
                .idProduction(dto.getIdProduction())
                .proposalNumber(dto.getProposalNumber())
                .num(dto.getNum())
                .cod(dto.getCod())
                .createDate(dto.getCreateDate())
                .validityDate(dto.getValidityDate())
                .finishedDate(dto.getFinishedDate())
                .statusClaId(dto.getStatusClaId())
                .amendmentsStatusCla(dto.getAmendmentsStatusCla())
                .ledId(dto.getLedId())
                .finantialContact(dto.getFinantialContact())
                .finantialContactName(dto.getFinantialContactName())
                .finantialContactEmail(dto.getFinantialContactEmail())
                .finantialContactPhone(dto.getFinantialContactPhone())
                .documentContact(dto.getDocumentContact())
                .documentContactName(dto.getDocumentContactName())
                .documentContactEmail(dto.getDocumentContactEmail())
                .documentContactPhone(dto.getDocumentContactPhone())
                .commercialContactName(dto.getCommercialContactName())
                .commercialContactEmail(dto.getCommercialContactEmail())
                .commercialContactPhone(dto.getCommercialContactPhone())
                .riskClaId(dto.getRiskClaId())
                .immediateDeliveryClaId(dto.getImmediateDeliveryClaId())
                .contract(dto.getContract())
                .usrIdCreate(dto.getUsrIdCreate())
                .usrIdLastUpdate(dto.getUsrIdLastUpdate())
                .lastUpdateDate(dto.getLastUpdateDate())
                .schedulingClaId(dto.getSchedulingClaId())
                .contaAzulTriggered(dto.getContaAzulTriggered())
                .clusterName(dto.getClusterName())
                .contaAzulAmendments(dto.getContaAzulAmendments())
                .signatureContact(dto.getSignatureContact())
                .basicContact(dto.getBasicContact())
                .signatureClaId(dto.getSignatureClaId())
                .customerName(dto.getCustomerName())
                .customerEmail(dto.getCustomerEmail())
                .customerPhone(dto.getCustomerPhone())
                .finishedWithoutSaleClaId(dto.getFinishedWithoutSaleClaId())
                .finishedWithoutSaleComment(dto.getFinishedWithoutSaleComment())
                .backofficeRejectedComment(dto.getBackofficeRejectedComment())
                .coparticipationInternalCommission(dto.getCoparticipationInternalCommission())
                .proposalSentDate(dto.getProposalSentDate())
                .proposalSent(dto.getProposalSent())
                .revenueAmendments(dto.getRevenueAmendments())
                .totvsTriggered(dto.getTotvsTriggered())
                .build();
    }
    
    public Proposal toEntity(ProposalRequestDTO dto, Integer id) {
        Proposal entity = toEntity(dto);
        entity.setId(id);
        return entity;
    }
    
    public ProposalResponseDTO toDto(Proposal entity) {
        return ProposalResponseDTO.builder()
                .id(entity.getId())
                .idProduction(entity.getIdProduction())
                .proposalNumber(entity.getProposalNumber())
                .num(entity.getNum())
                .cod(entity.getCod())
                .createDate(entity.getCreateDate())
                .validityDate(entity.getValidityDate())
                .finishedDate(entity.getFinishedDate())
                .statusClaId(entity.getStatusClaId())
                .amendmentsStatusCla(entity.getAmendmentsStatusCla())
                .ledId(entity.getLedId())
                .finantialContact(entity.getFinantialContact())
                .finantialContactName(entity.getFinantialContactName())
                .finantialContactEmail(entity.getFinantialContactEmail())
                .finantialContactPhone(entity.getFinantialContactPhone())
                .documentContact(entity.getDocumentContact())
                .documentContactName(entity.getDocumentContactName())
                .documentContactEmail(entity.getDocumentContactEmail())
                .documentContactPhone(entity.getDocumentContactPhone())
                .commercialContactName(entity.getCommercialContactName())
                .commercialContactEmail(entity.getCommercialContactEmail())
                .commercialContactPhone(entity.getCommercialContactPhone())
                .riskClaId(entity.getRiskClaId())
                .immediateDeliveryClaId(entity.getImmediateDeliveryClaId())
                .contract(entity.getContract())
                .usrIdCreate(entity.getUsrIdCreate())
                .usrIdLastUpdate(entity.getUsrIdLastUpdate())
                .lastUpdateDate(entity.getLastUpdateDate())
                .schedulingClaId(entity.getSchedulingClaId())
                .contaAzulTriggered(entity.getContaAzulTriggered())
                .clusterName(entity.getClusterName())
                .contaAzulAmendments(entity.getContaAzulAmendments())
                .signatureContact(entity.getSignatureContact())
                .basicContact(entity.getBasicContact())
                .signatureClaId(entity.getSignatureClaId())
                .customerName(entity.getCustomerName())
                .customerEmail(entity.getCustomerEmail())
                .customerPhone(entity.getCustomerPhone())
                .finishedWithoutSaleClaId(entity.getFinishedWithoutSaleClaId())
                .finishedWithoutSaleComment(entity.getFinishedWithoutSaleComment())
                .backofficeRejectedComment(entity.getBackofficeRejectedComment())
                .coparticipationInternalCommission(entity.getCoparticipationInternalCommission())
                .proposalSentDate(entity.getProposalSentDate())
                .proposalSent(entity.getProposalSent())
                .revenueAmendments(entity.getRevenueAmendments())
                .totvsTriggered(entity.getTotvsTriggered())
                .build();
    }
}
