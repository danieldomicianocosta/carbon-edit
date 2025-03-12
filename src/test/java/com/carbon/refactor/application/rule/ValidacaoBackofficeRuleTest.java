package com.carbon.refactor.application.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.carbon.refactor.application.dto.ProposalResponseDTO;
import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.domain.enums.ProposalStatus;

@ExtendWith(MockitoExtension.class)
public class ValidacaoBackofficeRuleTest {

    @InjectMocks
    private ValidacaoBackofficeRule rule;
    
    @Test
    public void testExecuteRule_WhenHasValidEmail_ShouldReturnTrue() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        ProposalResponseDTO proposalDTO = new ProposalResponseDTO();
        proposalDTO.setCustomerEmail("customer@example.com");
        proposal.setProposal(proposalDTO);
        
        Integer currentStatus = ProposalStatus.PROPOSTA_ENVIADA.getId();
        Integer newStatus = ProposalStatus.VALIDACAO_BACKOFFICE.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testExecuteRule_WhenInvalidEmail_ShouldReturnFalse() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        ProposalResponseDTO proposalDTO = new ProposalResponseDTO();
        proposalDTO.setCustomerEmail("invalid-email");
        proposal.setProposal(proposalDTO);
        
        Integer currentStatus = ProposalStatus.PROPOSTA_ENVIADA.getId();
        Integer newStatus = ProposalStatus.VALIDACAO_BACKOFFICE.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testExecuteRule_WhenNoEmail_ShouldReturnFalse() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        ProposalResponseDTO proposalDTO = new ProposalResponseDTO();
        proposalDTO.setCustomerEmail("");
        proposal.setProposal(proposalDTO);
        
        Integer currentStatus = ProposalStatus.PROPOSTA_ENVIADA.getId();
        Integer newStatus = ProposalStatus.VALIDACAO_BACKOFFICE.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testExecuteRule_WhenNullEmail_ShouldReturnFalse() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        ProposalResponseDTO proposalDTO = new ProposalResponseDTO();
        proposalDTO.setCustomerEmail(null);
        proposal.setProposal(proposalDTO);
        
        Integer currentStatus = ProposalStatus.PROPOSTA_ENVIADA.getId();
        Integer newStatus = ProposalStatus.VALIDACAO_BACKOFFICE.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testExecuteRule_WhenDifferentTransition_ShouldReturnTrue() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        ProposalResponseDTO proposalDTO = new ProposalResponseDTO();
        proposalDTO.setCustomerEmail(null); // This would normally cause the rule to fail
        proposal.setProposal(proposalDTO);
        
        Integer currentStatus = ProposalStatus.VALIDACAO_BACKOFFICE.getId();
        Integer newStatus = ProposalStatus.CANCELADO.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testGetSourceStatus_ShouldReturnPropostaEnviada() {
        // Act
        Integer result = rule.getSourceStatus();
        
        // Assert
        assertEquals(ProposalStatus.PROPOSTA_ENVIADA.getId(), result);
    }
    
    @Test
    public void testGetTargetStatus_ShouldReturnValidacaoBackoffice() {
        // Act
        Integer result = rule.getTargetStatus();
        
        // Assert
        assertEquals(ProposalStatus.VALIDACAO_BACKOFFICE.getId(), result);
    }
    
    @Test
    public void testGetErrorMessage_ShouldReturnCustomMessage() {
        // Act
        String errorMessage = rule.getErrorMessage();
        
        // Assert
        assertNotNull(errorMessage);
        assertEquals("Para enviar para validação do backoffice, é necessário informar um email válido para o cliente.", errorMessage);
    }
}
