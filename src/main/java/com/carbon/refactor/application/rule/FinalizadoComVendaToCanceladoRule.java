package com.carbon.refactor.application.rule;

import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.domain.rule.ProposalStatusRule;
import com.carbon.refactor.infrastructure.security.UserContext;

/**
 * Rule that validates that a proposal can only be transitioned from FINALIZADO_COM_VENDA to CANCELADO
 * if the user has the COM_CEO role.
 */
@Component
public class FinalizadoComVendaToCanceladoRule implements ProposalStatusRule {
    
    private static final String COM_CEO_ROLE = "COM_CEO";
    private static final String ERROR_MESSAGE = "Apenas usuários com o papel COM_CEO podem cancelar propostas finalizadas com venda.";
    
    @Override
    public boolean executeRule(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus) {
        // Check if this rule applies to the current transition
        if (!getSourceStatus().equals(currentStatus) || !getTargetStatus().equals(newStatus)) {
            // If this rule doesn't apply to the current transition, return true
            return true;
        }
        
        // Get the current user from the UserContext
        UserContext.UserInfo currentUser = UserContext.getCurrentUser();
        
        // Rule logic: Only users with COM_CEO role can transition from FINALIZADO_COM_VENDA to CANCELADO
        if (currentUser == null || currentUser.getRole() == null) {
            return false;
        }
        
        return COM_CEO_ROLE.equals(currentUser.getRole());
    }
    
    @Override
    public Integer getSourceStatus() {
        return ProposalStatus.FINALIZADO_COM_VENDA.getId();
    }
    
    @Override
    public Integer getTargetStatus() {
        return ProposalStatus.CANCELADO.getId();
    }
    
    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
