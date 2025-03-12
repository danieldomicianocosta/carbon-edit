package com.carbon.refactor.application.rule;

import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.domain.rule.ProposalStatusRule;

/**
 * Sample implementation of the ProposalStatusRule interface.
 * This rule validates that a proposal can only be approved by backoffice
 * if it has at least one document attached.
 */
@Component
public class ProposalStatusRuleImpl implements ProposalStatusRule {
    
    @Override
    public boolean executeRule(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus) {
        // Example rule: When transitioning to APROVADO_BACKOFFICE, check if there are documents
        if (getSourceStatus().equals(currentStatus) && getTargetStatus().equals(newStatus)) {
            // Check if the proposal has documents
            return proposal.getProposalDocuments() != null && !proposal.getProposalDocuments().isEmpty();
        }
        
        // If this rule doesn't apply to the current transition, return true
        return true;
    }
    
    @Override
    public Integer getSourceStatus() {
        return ProposalStatus.VALIDACAO_BACKOFFICE.getId();
    }
    
    @Override
    public Integer getTargetStatus() {
        return ProposalStatus.APROVADO_BACKOFFICE.getId();
    }
}
