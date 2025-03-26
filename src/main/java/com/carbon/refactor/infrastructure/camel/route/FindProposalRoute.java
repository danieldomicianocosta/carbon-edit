package com.carbon.refactor.infrastructure.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Rota Camel para buscar uma proposta completa.
 * Implementa o padrão Splitter para dividir a consulta em sub-consultas paralelas
 * e o padrão Aggregator para combinar os resultados.
 * 
 * Note: This component has been re-enabled with Camel 4.x which is compatible with Jakarta EE.
 */
@Component
@Slf4j
public class FindProposalRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Rota principal para buscar proposta completa
        from("direct:findCompleteProposal")
            .log("Buscando proposta completa com ID: ${header.id}")
            .setProperty("proposalId", header("id"))
            // Validação inicial
            .to("bean:proposalValidator?method=validateExistence(${exchangeProperty.proposalId})")
            // Divisão em sub-consultas paralelas usando o padrão Splitter
            .multicast().parallelProcessing()
                .to("direct:findProposal")
                .to("direct:findProposalDetail")
                .to("direct:findProposalVehicles")
                .to("direct:findProposalDocuments")
                .to("direct:findProposalCommissions")
                .to("direct:findProposalFups")
            .end()
            // Agregação dos resultados
            .to("direct:aggregateProposalResults")
            // Enriquecimento com status possíveis
            .to("direct:enrichWithPossibleStatuses")
            // Conversão para JSON
            .marshal().json(JsonLibrary.Jackson)
            .setHeader(Exchange.CONTENT_TYPE, constant("application/json"));
        
        // Sub-rota para buscar a proposta básica
        from("direct:findProposal")
            .log("Buscando proposta básica com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalRepository?method=findById(${exchangeProperty.proposalId})")
            .choice()
                .when(body().isNull())
                    .throwException(new RuntimeException("Proposta não encontrada com ID: ${exchangeProperty.proposalId}"))
                .otherwise()
                    .to("bean:proposalMapper?method=toDto");
        
        // Sub-rota para buscar os detalhes da proposta
        from("direct:findProposalDetail")
            .log("Buscando detalhes da proposta com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalDetailRepository?method=findByProposalId(${exchangeProperty.proposalId})")
            .choice()
                .when(body().isNotNull())
                    .to("bean:proposalDetailMapper?method=toDto");
        
        // Sub-rota para buscar os veículos da proposta
        from("direct:findProposalVehicles")
            .log("Buscando veículos da proposta com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalDetailRepository?method=findByProposalId(${exchangeProperty.proposalId})")
            .choice()
                .when(body().isNotNull())
                    .setProperty("detailId", simple("${body.id}"))
                    .to("bean:proposalDetailVehicleRepository?method=findByProposalDetailId(${exchangeProperty.detailId})")
                    .split(body())
                        .to("bean:proposalDetailVehicleMapper?method=toDto")
                    .end();
        
        // Sub-rota para buscar os documentos da proposta
        from("direct:findProposalDocuments")
            .log("Buscando documentos da proposta com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalDocumentRepository?method=findByProposalIdWithProposal(${exchangeProperty.proposalId})")
            .split(body())
                .to("bean:proposalDocumentMapper?method=toDto")
            .end();
        
        // Sub-rota para buscar as comissões da proposta
        from("direct:findProposalCommissions")
            .log("Buscando comissões da proposta com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalDetailRepository?method=findByProposalId(${exchangeProperty.proposalId})")
            .choice()
                .when(body().isNotNull())
                    .setProperty("detailId", simple("${body.id}"))
                    .to("bean:proposalCommissionRepository?method=findByProposalDetailId(${exchangeProperty.detailId})")
                    .split(body())
                        .to("bean:proposalCommissionMapper?method=toDto")
                    .end();
        
        // Sub-rota para buscar os acompanhamentos da proposta
        from("direct:findProposalFups")
            .log("Buscando acompanhamentos da proposta com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalFupRepository?method=findByProposalIdOrderByDateDescWithProposal(${exchangeProperty.proposalId})")
            .split(body())
                .to("bean:proposalFupMapper?method=toDto")
            .end();
        
        // Sub-rota para agregar os resultados
        from("direct:aggregateProposalResults")
            .log("Agregando resultados da proposta com ID: ${exchangeProperty.proposalId}")
            .bean("proposalResultAggregator", "aggregate");
        
        // Sub-rota para enriquecer com possíveis próximos status
        from("direct:enrichWithPossibleStatuses")
            .log("Enriquecendo com possíveis próximos status para proposta com ID: ${exchangeProperty.proposalId}")
            .to("bean:proposalStatusService?method=getPossibleNextStatuses(${body.proposal.statusClaId})")
            .to("bean:proposalStatusUtil?method=mapStatusIdsToStatusDTOs")
            .to("bean:proposalResultEnricher?method=enrichWithPossibleStatuses");
    }
}
