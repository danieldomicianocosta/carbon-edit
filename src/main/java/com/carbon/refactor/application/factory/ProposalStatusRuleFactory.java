package com.carbon.refactor.application.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.carbon.refactor.domain.rule.ProposalStatusRule;
import com.carbon.refactor.domain.service.ProposalStatusService;

import jakarta.annotation.PostConstruct;

/**
 * Factory for creating and registering ProposalStatusRule instances.
 * This factory automatically registers all ProposalStatusRule beans with the ProposalStatusService.
 */
@Component
public class ProposalStatusRuleFactory {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Autowired
    @Qualifier("proposalStatusServiceImpl")
    private ProposalStatusService proposalStatusService;
    
    /**
     * Initialize the factory by registering all ProposalStatusRule beans.
     */
    @PostConstruct
    public void init() {
        // Get all beans that implement ProposalStatusRule
        String[] beanNames = applicationContext.getBeanNamesForType(ProposalStatusRule.class);
        
        // Register each rule with the service
        for (String beanName : beanNames) {
            ProposalStatusRule rule = applicationContext.getBean(beanName, ProposalStatusRule.class);
            proposalStatusService.registerRule(rule);
        }
    }
    
    /**
     * Create a new rule for a specific transition.
     * 
     * @param sourceStatus The source status
     * @param targetStatus The target status
     * @param ruleLogic The rule logic to execute
     * @return The created rule
     */
    public ProposalStatusRule createRule(Integer sourceStatus, Integer targetStatus, RuleLogic ruleLogic) {
        return createRule(sourceStatus, targetStatus, ruleLogic, null);
    }
    
    /**
     * Create a new rule for a specific transition with a custom error message.
     * 
     * @param sourceStatus The source status
     * @param targetStatus The target status
     * @param ruleLogic The rule logic to execute
     * @param errorMessage The custom error message to display when the rule fails
     * @return The created rule
     */
    public ProposalStatusRule createRule(Integer sourceStatus, Integer targetStatus, RuleLogic ruleLogic, String errorMessage) {
        ProposalStatusRule rule = new ProposalStatusRule() {
            @Override
            public boolean executeRule(
                    com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO proposal,
                    Integer currentStatus, Integer newStatus) {
                return ruleLogic.execute(proposal, currentStatus, newStatus);
            }
            
            @Override
            public Integer getSourceStatus() {
                return sourceStatus;
            }
            
            @Override
            public Integer getTargetStatus() {
                return targetStatus;
            }
            
            @Override
            public String getErrorMessage() {
                return errorMessage;
            }
        };
        
        // Register the rule with the service
        proposalStatusService.registerRule(rule);
        
        return rule;
    }
    
    /**
     * Register multiple rules at once.
     * 
     * @param rules The rules to register
     */
    public void registerRules(List<ProposalStatusRule> rules) {
        for (ProposalStatusRule rule : rules) {
            proposalStatusService.registerRule(rule);
        }
    }
    
    /**
     * Functional interface for defining rule logic.
     */
    @FunctionalInterface
    public interface RuleLogic {
        boolean execute(
                com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO proposal,
                Integer currentStatus, Integer newStatus);
    }
}
