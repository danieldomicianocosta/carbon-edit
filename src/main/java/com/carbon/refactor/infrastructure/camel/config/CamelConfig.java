package com.carbon.refactor.infrastructure.camel.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração global do Apache Camel para o projeto Carbon.
 * Define configurações comuns e endpoints REST para as rotas de proposta.
 * 
 * Note: This configuration has been updated to use Camel 4.x with platform-http component
 * which is compatible with Jakarta EE used by Spring Boot 3.x.
 */
@Configuration
public class CamelConfig {

    /**
     * Configura as rotas REST para as operações de proposta.
     * 
     * @return RouteBuilder configurado com endpoints REST
     */
    @Bean
    public RouteBuilder restRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Configuração global de tratamento de erros
                errorHandler(defaultErrorHandler()
                    .logExhaustedMessageHistory(true)
                    .maximumRedeliveries(3)
                    .redeliveryDelay(1000));
                
                // Configuração dos endpoints REST
                restConfiguration()
                    .component("platform-http")
                    .bindingMode(RestBindingMode.json)
                    .dataFormatProperty("prettyPrint", "true");
                
                // Definição das rotas diretas para propostas
                from("direct:findCompleteProposal")
                    .log("Rota direta: Buscando proposta completa com ID: ${header.id}")
                    .to("direct:findProposal");
                
                from("direct:updateCompleteProposal")
                    .log("Rota direta: Atualizando proposta completa com ID: ${header.id}")
                    .to("direct:updateProposal");
            }
        };
    }
}
