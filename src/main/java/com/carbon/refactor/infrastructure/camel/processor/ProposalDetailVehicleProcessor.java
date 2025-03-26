package com.carbon.refactor.infrastructure.camel.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.ProposalDetailVehicleRequestDTO;
import com.carbon.refactor.application.dto.ProposalDetailVehicleResponseDTO;
import com.carbon.refactor.application.mapper.ProposalDetailVehicleMapper;
import com.carbon.refactor.domain.entity.ProposalDetail;
import com.carbon.refactor.domain.entity.ProposalDetailVehicle;
import com.carbon.refactor.domain.service.ProposalDetailService;
import com.carbon.refactor.domain.service.ProposalDetailVehicleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Processador Camel para atualizar os veículos da proposta.
 * 
 * Note: This component has been re-enabled with Camel 4.x which is compatible with Jakarta EE.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ProposalDetailVehicleProcessor implements Processor {

    private final ProposalDetailVehicleService proposalDetailVehicleService;
    private final ProposalDetailService proposalDetailService;
    private final ProposalDetailVehicleMapper proposalDetailVehicleMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer proposalId = exchange.getProperty("proposalId", Integer.class);
        @SuppressWarnings("unchecked")
        List<ProposalDetailVehicleRequestDTO> requestDTOs = exchange.getIn().getBody(List.class);
        
        log.debug("Processando atualização de {} veículos da proposta com ID: {}", 
                requestDTOs != null ? requestDTOs.size() : 0, proposalId);
        
        // Verifica se existe um detalhe para esta proposta
        if (!proposalDetailService.existsByProposalId(proposalId)) {
            throw new IllegalStateException("Detalhe da proposta não encontrado para proposta com ID: " + proposalId);
        }
        
        // Busca o detalhe da proposta
        ProposalDetail proposalDetail = proposalDetailService.findByProposalId(proposalId);
        
        // Busca os veículos existentes
        List<ProposalDetailVehicle> existingVehicles = 
                proposalDetailVehicleService.findByProposalDetailId(proposalDetail.getId());
        
        // Remove os veículos existentes
        for (ProposalDetailVehicle vehicle : existingVehicles) {
            proposalDetailVehicleService.delete(vehicle.getId());
        }
        
        // Cria os novos veículos
        List<ProposalDetailVehicle> updatedVehicles = new ArrayList<>();
        List<ProposalDetailVehicleResponseDTO> responseDTOs = new ArrayList<>();
        
        if (requestDTOs != null && !requestDTOs.isEmpty()) {
            for (ProposalDetailVehicleRequestDTO requestDTO : requestDTOs) {
                ProposalDetailVehicle vehicle = proposalDetailVehicleMapper.toEntity(requestDTO);
                vehicle.setProposalDetail(proposalDetail);
                
                ProposalDetailVehicle createdVehicle = proposalDetailVehicleService.create(vehicle);
                updatedVehicles.add(createdVehicle);
                
                // Converte a entidade criada para DTO
                ProposalDetailVehicleResponseDTO responseDTO = proposalDetailVehicleMapper.toDto(createdVehicle);
                responseDTOs.add(responseDTO);
            }
        }
        
        // Define o corpo da mensagem como a lista de DTOs de resposta
        exchange.getIn().setBody(responseDTOs);
        
        // Armazena os veículos atualizados como uma propriedade para uso posterior
        exchange.setProperty("updatedVehicles", updatedVehicles);
    }
}
