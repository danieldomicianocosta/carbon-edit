package com.carbon.refactor.infrastructure.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.ProposalRequestDTO;
import com.carbon.refactor.application.dto.ProposalResponseDTO;
import com.carbon.refactor.application.mapper.ProposalMapper;
import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.service.ProposalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Processador Camel para atualizar a proposta básica.
 * 
 * Note: This component has been re-enabled with Camel 4.x which is compatible with Jakarta EE.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ProposalProcessor implements Processor {

    private final ProposalService proposalService;
    private final ProposalMapper proposalMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer proposalId = exchange.getProperty("proposalId", Integer.class);
        ProposalRequestDTO requestDTO = exchange.getIn().getBody(ProposalRequestDTO.class);
        
        log.debug("Processando atualização da proposta básica com ID: {}", proposalId);
        
        // Converte o DTO para entidade
        Proposal proposal = proposalMapper.toEntity(requestDTO, proposalId);
        
        // Atualiza a proposta
        Proposal updatedProposal = proposalService.update(proposalId, proposal);
        
        // Converte a entidade atualizada para DTO
        ProposalResponseDTO responseDTO = proposalMapper.toDto(updatedProposal);
        
        // Define o corpo da mensagem como o DTO de resposta
        exchange.getIn().setBody(responseDTO);
    }
}
