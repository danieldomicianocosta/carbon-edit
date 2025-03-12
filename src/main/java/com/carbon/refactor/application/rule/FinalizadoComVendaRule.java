package com.carbon.refactor.application.rule;

import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.domain.rule.ProposalStatusRule;

/**
 * Rule that validates that a proposal can only be finalized with a sale
 * if it has at least one vehicle item.
 * 
 * This is an example of a concrete implementation of the ProposalStatusRule interface.
 * It demonstrates how to create a specific rule for a particular status transition.
 */
@Component
public class FinalizadoComVendaRule implements ProposalStatusRule {
    
    private static final String ERROR_MESSAGE = "Para finalizar com venda, a proposta deve ter pelo menos um item de veículo com preço final maior que zero.";
    
    @Override
    public boolean executeRule(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus) {
        // Check if this rule applies to the current transition
        if (!getSourceStatus().equals(currentStatus) || !getTargetStatus().equals(newStatus)) {
            // If this rule doesn't apply to the current transition, return true
            return true;
        }
        
        // Rule logic: A proposal can only be finalized with a sale if it has at least one vehicle item
        if (proposal.getProposalDetailVehicleItems() == null || proposal.getProposalDetailVehicleItems().isEmpty()) {
            return false;
        }
        
        // Additional validation: Check if any vehicle item has a final price greater than zero
        return proposal.getProposalDetailVehicleItems().stream()
                .anyMatch(item -> item.getFinalPrice() != null && item.getFinalPrice().compareTo(java.math.BigDecimal.ZERO) > 0);
    }
    
    @Override
    public Integer getSourceStatus() {
        return ProposalStatus.APROVADO_BACKOFFICE.getId();
    }
    
    @Override
    public Integer getTargetStatus() {
        return ProposalStatus.FINALIZADO_COM_VENDA.getId();
    }
    
    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
