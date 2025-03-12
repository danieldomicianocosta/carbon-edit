package com.carbon.refactor.application.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carbon.refactor.application.factory.ProposalStatusRuleFactory;
import com.carbon.refactor.domain.enums.ProposalStatus;

/**
 * Configuration class for programmatically creating and registering ProposalStatusRules.
 * This demonstrates how to use the ProposalStatusRuleFactory to create rules without
 * having to create separate classes for each rule.
 */
@Configuration
public class ProposalStatusRuleConfig {
    
    /**
     * Configure rules for proposal status transitions.
     * 
     * @param ruleFactory The rule factory
     * @return The rule factory
     */
    @Bean
    public ProposalStatusRuleFactory configureRules(
            @Qualifier("proposalStatusRuleFactory") ProposalStatusRuleFactory ruleFactory) {
        // Example rule: When transitioning from PROPOSTA_ENVIADA to FINALIZADO_SEM_VENDA,
        // check if the proposal has a finishedWithoutSaleClaId
        // This rule includes a custom error message
        ruleFactory.createRule(
                ProposalStatus.PROPOSTA_ENVIADA.getId(),
                ProposalStatus.FINALIZADO_SEM_VENDA.getId(),
                (proposal, currentStatus, newStatus) -> {
                    // Check if the proposal has a finishedWithoutSaleClaId
                    return proposal.getProposal().getFinishedWithoutSaleClaId() != null;
                },
                "É necessário informar o motivo de finalização sem venda.");
        
        // Example rule: When transitioning from EM_EDICAO to FINALIZADO_COM_VENDA,
        // check if the proposal has at least one commission
        // This rule does not include a custom error message, so a default message will be used
        ruleFactory.createRule(
                ProposalStatus.EM_EDICAO.getId(),
                ProposalStatus.FINALIZADO_COM_VENDA.getId(),
                (proposal, currentStatus, newStatus) -> {
                    // Check if the proposal has at least one commission
                    return proposal.getProposalCommissions() != null && !proposal.getProposalCommissions().isEmpty();
                });
        
        return ruleFactory;
    }
}
