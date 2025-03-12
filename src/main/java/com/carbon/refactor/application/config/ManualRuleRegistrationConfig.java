package com.carbon.refactor.application.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carbon.refactor.application.rule.FinalizadoComVendaRule;
import com.carbon.refactor.application.rule.FinalizadoSemVendaRule;
import com.carbon.refactor.application.rule.ProposalStatusRuleImpl;
import com.carbon.refactor.application.rule.ValidacaoBackofficeRule;
import com.carbon.refactor.domain.rule.ProposalStatusRule;
import com.carbon.refactor.domain.service.ProposalStatusService;

/**
 * Configuration class demonstrating how to manually register concrete rule classes.
 * 
 * This approach can be used when you want more control over rule registration,
 * or when you want to register rules conditionally based on configuration properties.
 */
@Configuration
public class ManualRuleRegistrationConfig {
    
    /**
     * Manually register concrete rule classes with the ProposalStatusService.
     * 
     * @param proposalStatusService The service to register rules with
     * @return The service with rules registered
     */
    @Bean
    public ProposalStatusService registerConcreteRules(
            @Qualifier("proposalStatusServiceImpl") ProposalStatusService proposalStatusService,
            FinalizadoComVendaRule finalizadoComVendaRule,
            FinalizadoSemVendaRule finalizadoSemVendaRule,
            ValidacaoBackofficeRule validacaoBackofficeRule,
            ProposalStatusRuleImpl proposalStatusRuleImpl) {
        
        // Register each concrete rule with the service
        proposalStatusService.registerRule(finalizadoComVendaRule);
        proposalStatusService.registerRule(finalizadoSemVendaRule);
        proposalStatusService.registerRule(validacaoBackofficeRule);
        proposalStatusService.registerRule(proposalStatusRuleImpl);
        
        return proposalStatusService;
    }
    
    /**
     * Example of creating and registering a rule programmatically.
     * 
     * @param proposalStatusService The service to register the rule with
     * @return The created rule
     */
    @Bean
    public ProposalStatusRule createAndRegisterCustomRule(
            @Qualifier("proposalStatusServiceImpl") ProposalStatusService proposalStatusService) {
        // Create a custom rule
        ProposalStatusRule customRule = new ProposalStatusRule() {
            @Override
            public boolean executeRule(
                    com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO proposal,
                    Integer currentStatus, Integer newStatus) {
                // Custom rule logic here
                return true;
            }
            
            @Override
            public Integer getSourceStatus() {
                return com.carbon.refactor.domain.enums.ProposalStatus.EM_EDICAO.getId();
            }
            
            @Override
            public Integer getTargetStatus() {
                return com.carbon.refactor.domain.enums.ProposalStatus.EM_APROVACAO_COMERCIAL.getId();
            }
        };
        
        // Register the custom rule
        proposalStatusService.registerRule(customRule);
        
        return customRule;
    }
}
