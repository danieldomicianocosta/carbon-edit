package com.carbon.refactor.infrastructure.camel.config;

import org.apache.camel.component.platform.http.PlatformHttpComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do componente platform-http do Camel para o Spring Boot.
 * Configura o componente platform-http para processar requisições HTTP.
 * 
 * Note: This configuration replaces the previous servlet-based configuration.
 * Camel 4.x uses platform-http component which is compatible with Jakarta EE.
 */
@Configuration
public class CamelServletConfig {

    @Value("${camel.component.platform-http.context-path:/camel}")
    private String contextPath;

    /**
     * Configura o componente platform-http do Camel.
     * 
     * @return Componente platform-http configurado
     */
    @Bean
    public PlatformHttpComponent platformHttp() {
        // In Camel 4.x, the platform-http component is auto-configured
        // The context path is configured via properties
        return new PlatformHttpComponent();
    }
}
