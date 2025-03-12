package com.carbon.refactor.application.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carbon.refactor.application.factory.ProposalStatusRuleFactory;
import com.carbon.refactor.domain.enums.ProposalStatus;

/**
 * Configuration class demonstrating how to use the ProposalStatusRuleFactory
 * to create and register rules programmatically.
 * 
 * This approach is useful when you want to create rules on-the-fly without
 * creating separate classes for each rule.
 */
@Configuration
public class FactoryRuleRegistrationConfig {
    
    /**
     * Configure rules using the ProposalStatusRuleFactory.
     * 
     * @param ruleFactory The rule factory
     * @return The rule factory
     */
    @Bean
    public ProposalStatusRuleFactory configureFactoryRules(
            @Qualifier("proposalStatusRuleFactory") ProposalStatusRuleFactory ruleFactory) {
        // Example 1: Rule that checks if a proposal has a customer phone number
        // when transitioning from EM_EDICAO to EM_APROVACAO_COMERCIAL
        ruleFactory.createRule(
                ProposalStatus.EM_EDICAO.getId(),
                ProposalStatus.EM_APROVACAO_COMERCIAL.getId(),
                (proposal, currentStatus, newStatus) -> {
                    String customerPhone = proposal.getProposal().getCustomerPhone();
                    return customerPhone != null && !customerPhone.trim().isEmpty();
                });
        
        // Example 2: Rule that checks if a proposal has a customer name
        // when transitioning from APROVADO_COMERCIAL to PROPOSTA_ENVIADA
        ruleFactory.createRule(
                ProposalStatus.APROVADO_COMERCIAL.getId(),
                ProposalStatus.PROPOSTA_ENVIADA.getId(),
                (proposal, currentStatus, newStatus) -> {
                    String customerName = proposal.getProposal().getCustomerName();
                    return customerName != null && !customerName.trim().isEmpty();
                });
        
        // Example 3: Rule that checks if a proposal has a valid date
        // when transitioning from VALIDACAO_BACKOFFICE to APROVADO_BACKOFFICE
        ruleFactory.createRule(
                ProposalStatus.VALIDACAO_BACKOFFICE.getId(),
                ProposalStatus.APROVADO_BACKOFFICE.getId(),
                (proposal, currentStatus, newStatus) -> {
                    return proposal.getProposal().getValidityDate() != null;
                });
        
        return ruleFactory;
    }
}
