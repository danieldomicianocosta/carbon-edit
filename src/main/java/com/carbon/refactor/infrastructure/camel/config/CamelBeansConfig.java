package com.carbon.refactor.infrastructure.camel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.carbon.refactor.infrastructure.camel.route.FindProposalRoute;
import com.carbon.refactor.infrastructure.camel.route.UpdateProposalRoute;
import com.carbon.refactor.infrastructure.camel.processor.ProposalProcessor;
import com.carbon.refactor.infrastructure.camel.processor.ProposalDetailProcessor;
import com.carbon.refactor.infrastructure.camel.processor.ProposalDetailVehicleProcessor;
import com.carbon.refactor.infrastructure.camel.processor.ProposalUpdateProcessor;
import com.carbon.refactor.infrastructure.camel.aggregator.ProposalResultAggregator;
import com.carbon.refactor.infrastructure.camel.enricher.ProposalResultEnricher;
import com.carbon.refactor.infrastructure.camel.validator.ProposalValidator;

/**
 * Configuração para registrar os beans necessários para o Camel.
 * Importa todas as classes relacionadas ao Camel para garantir que sejam registradas como beans.
 * 
 * Note: This configuration has been re-enabled with Camel 4.x which is compatible with Jakarta EE.
 */
@Configuration
@Import({
    // Rotas
    FindProposalRoute.class,
    UpdateProposalRoute.class,
    
    // Processadores
    ProposalProcessor.class,
    ProposalDetailProcessor.class,
    ProposalDetailVehicleProcessor.class,
    ProposalUpdateProcessor.class,
    
    // Agregadores
    ProposalResultAggregator.class,
    
    // Enriquecedores
    ProposalResultEnricher.class,
    
    // Validadores
    ProposalValidator.class
})
public class CamelBeansConfig {
    // Esta classe não precisa de métodos, apenas importa as classes necessárias
}
