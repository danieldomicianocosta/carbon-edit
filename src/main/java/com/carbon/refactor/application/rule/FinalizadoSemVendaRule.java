package com.carbon.refactor.application.rule;

import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.domain.rule.ProposalStatusRule;

/**
 * Rule that validates that a proposal can only be finalized without a sale
 * if it has a reason (finishedWithoutSaleComment) specified.
 * 
 * This is another example of a concrete implementation of the ProposalStatusRule interface.
 */
@Component
public class FinalizadoSemVendaRule implements ProposalStatusRule {
    
    private static final String ERROR_MESSAGE = "Para finalizar sem venda, é necessário informar um motivo com pelo menos 10 caracteres.";
    
    @Override
    public boolean executeRule(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus) {
        // Check if this rule applies to the current transition
        if (!getSourceStatus().equals(currentStatus) || !getTargetStatus().equals(newStatus)) {
            // If this rule doesn't apply to the current transition, return true
            return true;
        }
        
        // Rule logic: A proposal can only be finalized without a sale if it has a reason specified
        String finishedWithoutSaleComment = proposal.getProposal().getFinishedWithoutSaleComment();
        
        // Check if the finished without sale comment is not null or empty
        if (finishedWithoutSaleComment == null || finishedWithoutSaleComment.trim().isEmpty()) {
            return false;
        }
        
        // Check if the finished without sale comment has at least 10 characters
        return finishedWithoutSaleComment.trim().length() >= 10;
    }
    
    @Override
    public Integer getSourceStatus() {
        return ProposalStatus.PROPOSTA_ENVIADA.getId();
    }
    
    @Override
    public Integer getTargetStatus() {
        return ProposalStatus.FINALIZADO_SEM_VENDA.getId();
    }
    
    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
