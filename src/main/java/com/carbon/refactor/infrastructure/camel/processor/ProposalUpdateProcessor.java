package com.carbon.refactor.infrastructure.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.request.CompleteProposalRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Processador Camel que implementa o padrão Content-Based Router para direcionar
 * diferentes partes da proposta para as sub-rotas apropriadas.
 * 
 * Note: This component has been re-enabled with Camel 4.x which is compatible with Jakarta EE.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ProposalUpdateProcessor implements Processor {

    private final ProducerTemplate producerTemplate;

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer proposalId = exchange.getProperty("proposalId", Integer.class);
        CompleteProposalRequestDTO requestDTO = exchange.getIn().getBody(CompleteProposalRequestDTO.class);
        
        log.debug("Processando atualização da proposta completa com ID: {}", proposalId);
        
        // Roteamento baseado no conteúdo
        if (requestDTO.getProposal() != null) {
            log.debug("Enviando proposta básica para atualização");
            producerTemplate.send("direct:updateProposal", createExchange(exchange, requestDTO.getProposal()));
        }
        
        if (requestDTO.getProposalDetail() != null) {
            log.debug("Enviando detalhes da proposta para atualização");
            producerTemplate.send("direct:updateProposalDetail", createExchange(exchange, requestDTO.getProposalDetail()));
        }
        
        if (requestDTO.getProposalDetailVehicles() != null && !requestDTO.getProposalDetailVehicles().isEmpty()) {
            log.debug("Enviando veículos da proposta para atualização");
            producerTemplate.send("direct:updateProposalVehicles", createExchange(exchange, requestDTO.getProposalDetailVehicles()));
        }
        
        if (requestDTO.getProposalDetailVehicleItems() != null && !requestDTO.getProposalDetailVehicleItems().isEmpty()) {
            log.debug("Enviando itens dos veículos da proposta para atualização");
            producerTemplate.send("direct:updateProposalVehicleItems", createExchange(exchange, requestDTO.getProposalDetailVehicleItems()));
        }
        
        if (requestDTO.getProposalCommissions() != null && !requestDTO.getProposalCommissions().isEmpty()) {
            log.debug("Enviando comissões da proposta para atualização");
            producerTemplate.send("direct:updateProposalCommissions", createExchange(exchange, requestDTO.getProposalCommissions()));
        }
        
        if (requestDTO.getProposalDocuments() != null && !requestDTO.getProposalDocuments().isEmpty()) {
            log.debug("Enviando documentos da proposta para atualização");
            producerTemplate.send("direct:updateProposalDocuments", createExchange(exchange, requestDTO.getProposalDocuments()));
        }
        
        if (requestDTO.getProposalFups() != null && !requestDTO.getProposalFups().isEmpty()) {
            log.debug("Enviando acompanhamentos da proposta para atualização");
            producerTemplate.send("direct:updateProposalFups", createExchange(exchange, requestDTO.getProposalFups()));
        }
    }
    
    /**
     * Cria um novo Exchange com as propriedades do Exchange original e um novo corpo.
     * 
     * @param originalExchange Exchange original
     * @param body Novo corpo
     * @return Novo Exchange
     */
    private Exchange createExchange(Exchange originalExchange, Object body) {
        Exchange newExchange = originalExchange.copy();
        newExchange.getIn().setBody(body);
        return newExchange;
    }
}
