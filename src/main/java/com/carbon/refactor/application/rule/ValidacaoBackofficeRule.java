package com.carbon.refactor.application.rule;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.domain.rule.ProposalStatusRule;

/**
 * Rule that validates that a proposal can only be sent to backoffice validation
 * if it has a valid customer email.
 * 
 * This is another example of a concrete implementation of the ProposalStatusRule interface.
 */
@Component
public class ValidacaoBackofficeRule implements ProposalStatusRule {
    
    // Regular expression for validating email addresses
    private static final Pattern EMAIL_PATTERN = 
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    
    private static final String ERROR_MESSAGE = "Para enviar para validação do backoffice, é necessário informar um email válido para o cliente.";
    
    @Override
    public boolean executeRule(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus) {
        // Check if this rule applies to the current transition
        if (!getSourceStatus().equals(currentStatus) || !getTargetStatus().equals(newStatus)) {
            // If this rule doesn't apply to the current transition, return true
            return true;
        }
        
        // Rule logic: A proposal can only be sent to backoffice validation if it has a valid customer email
        String customerEmail = proposal.getProposal().getCustomerEmail();
        
        // Check if the customer email is not null or empty
        if (customerEmail == null || customerEmail.trim().isEmpty()) {
            return false;
        }
        
        // Check if the customer email is valid
        return EMAIL_PATTERN.matcher(customerEmail).matches();
    }
    
    @Override
    public Integer getSourceStatus() {
        return ProposalStatus.PROPOSTA_ENVIADA.getId();
    }
    
    @Override
    public Integer getTargetStatus() {
        return ProposalStatus.VALIDACAO_BACKOFFICE.getId();
    }
    
    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
