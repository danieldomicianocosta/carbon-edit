package com.carbon.refactor.infrastructure.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Rota Camel para atualizar uma proposta completa.
 * Implementa o padrão Content-Based Router para direcionar diferentes partes da proposta
 * para os processadores apropriados.
 * 
 * Note: This component has been re-enabled with Camel 4.x which is compatible with Jakarta EE.
 */
@Component
@Slf4j
public class UpdateProposalRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Configuração de tratamento de erros específico para esta rota
        errorHandler(defaultErrorHandler()
            .logExhaustedMessageHistory(true)
            .maximumRedeliveries(2)
            .redeliveryDelay(1000)
            .useOriginalMessage());
        
        // Rota principal para atualizar proposta completa
        from("direct:updateCompleteProposal")
            .log("Atualizando proposta completa com ID: ${header.id}")
            .setProperty("proposalId", header("id"))
            // Validação inicial
            .to("bean:proposalValidator?method=validateExistence(${exchangeProperty.proposalId})")
            // Validação dos dados de entrada
            .to("bean:proposalValidator?method=validateUpdateRequest")
            // Início da transação
            .transacted()
            // Roteamento baseado no conteúdo usando o padrão Content-Based Router
            .process("proposalUpdateProcessor")
            // Busca da proposta atualizada
            .to("direct:findCompleteProposal");
        
        // Sub-rota para atualizar a proposta básica
        from("direct:updateProposal")
            .log("Atualizando proposta básica com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalProcessor?method=process");
        
        // Sub-rota para atualizar os detalhes da proposta
        from("direct:updateProposalDetail")
            .log("Atualizando detalhes da proposta com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalDetailProcessor?method=process");
        
        // Sub-rota para atualizar os veículos da proposta
        from("direct:updateProposalVehicles")
            .log("Atualizando veículos da proposta com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalDetailVehicleProcessor?method=process");
        
        // Sub-rota para atualizar os itens dos veículos da proposta
        from("direct:updateProposalVehicleItems")
            .log("Atualizando itens dos veículos da proposta com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalDetailVehicleItemProcessor?method=process");
        
        // Sub-rota para atualizar as comissões da proposta
        from("direct:updateProposalCommissions")
            .log("Atualizando comissões da proposta com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalCommissionProcessor?method=process");
        
        // Sub-rota para atualizar os documentos da proposta
        from("direct:updateProposalDocuments")
            .log("Atualizando documentos da proposta com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalDocumentProcessor?method=process");
        
        // Sub-rota para atualizar os acompanhamentos da proposta
        from("direct:updateProposalFups")
            .log("Atualizando acompanhamentos da proposta com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalFupProcessor?method=process");
    }
}
