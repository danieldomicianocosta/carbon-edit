package com.carbon.refactor.infrastructure.camel.aggregator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.ProposalDetailResponseDTO;
import com.carbon.refactor.application.dto.ProposalDetailVehicleResponseDTO;
import com.carbon.refactor.application.dto.ProposalResponseDTO;
import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalCommissionResponseDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalDetailVehicleItemResponseDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalDocumentResponseDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalFupResponseDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * Agregador responsável por combinar os resultados das sub-consultas em uma única resposta completa.
 * Implementa o padrão Aggregator do Enterprise Integration Patterns.
 * 
 * Note: This component has been re-enabled with Camel 4.x which is compatible with Jakarta EE.
 */
@Component
@Slf4j
public class ProposalResultAggregator {

    /**
     * Agrega os resultados das sub-consultas em uma única resposta completa.
     * 
     * @param proposal Proposta básica
     * @param proposalDetail Detalhes da proposta
     * @param vehicles Veículos da proposta
     * @param vehicleItems Itens dos veículos da proposta
     * @param commissions Comissões da proposta
     * @param documents Documentos da proposta
     * @param fups Acompanhamentos da proposta
     * @return Proposta completa
     */
    public CompleteProposalResponseDTO aggregate(
            ProposalResponseDTO proposal,
            ProposalDetailResponseDTO proposalDetail,
            List<ProposalDetailVehicleResponseDTO> vehicles,
            List<ProposalDetailVehicleItemResponseDTO> vehicleItems,
            List<ProposalCommissionResponseDTO> commissions,
            List<ProposalDocumentResponseDTO> documents,
            List<ProposalFupResponseDTO> fups) {
        
        log.debug("Agregando resultados para proposta com ID: {}", proposal != null ? proposal.getId() : null);
        
        CompleteProposalResponseDTO result = new CompleteProposalResponseDTO();
        
        // Adiciona a proposta básica
        if (proposal != null) {
            result.setProposal(proposal);
        }
        
        // Adiciona os detalhes da proposta
        if (proposalDetail != null) {
            result.setProposalDetail(proposalDetail);
        }
        
        // Adiciona os veículos da proposta
        if (vehicles != null && !vehicles.isEmpty()) {
            result.setProposalDetailVehicles(vehicles);
        } else {
            result.setProposalDetailVehicles(new ArrayList<>());
        }
        
        // Adiciona os itens dos veículos da proposta
        if (vehicleItems != null && !vehicleItems.isEmpty()) {
            result.setProposalDetailVehicleItems(vehicleItems);
        } else {
            result.setProposalDetailVehicleItems(new ArrayList<>());
        }
        
        // Adiciona as comissões da proposta
        if (commissions != null && !commissions.isEmpty()) {
            result.setProposalCommissions(commissions);
        } else {
            result.setProposalCommissions(new ArrayList<>());
        }
        
        // Adiciona os documentos da proposta
        if (documents != null && !documents.isEmpty()) {
            result.setProposalDocuments(documents);
        } else {
            result.setProposalDocuments(new ArrayList<>());
        }
        
        // Adiciona os acompanhamentos da proposta
        if (fups != null && !fups.isEmpty()) {
            result.setProposalFups(fups);
        } else {
            result.setProposalFups(new ArrayList<>());
        }
        
        return result;
    }
}
