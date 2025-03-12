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
public class FinalizadoSemVendaRuleTest {

    @InjectMocks
    private FinalizadoSemVendaRule rule;
    
    @Test
    public void testExecuteRule_WhenHasValidComment_ShouldReturnTrue() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        ProposalResponseDTO proposalDTO = new ProposalResponseDTO();
        proposalDTO.setFinishedWithoutSaleComment("This is a valid comment with more than 10 characters");
        proposal.setProposal(proposalDTO);
        
        Integer currentStatus = ProposalStatus.PROPOSTA_ENVIADA.getId();
        Integer newStatus = ProposalStatus.FINALIZADO_SEM_VENDA.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testExecuteRule_WhenCommentTooShort_ShouldReturnFalse() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        ProposalResponseDTO proposalDTO = new ProposalResponseDTO();
        proposalDTO.setFinishedWithoutSaleComment("Too short");
        proposal.setProposal(proposalDTO);
        
        Integer currentStatus = ProposalStatus.PROPOSTA_ENVIADA.getId();
        Integer newStatus = ProposalStatus.FINALIZADO_SEM_VENDA.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testExecuteRule_WhenNoComment_ShouldReturnFalse() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        ProposalResponseDTO proposalDTO = new ProposalResponseDTO();
        proposalDTO.setFinishedWithoutSaleComment("");
        proposal.setProposal(proposalDTO);
        
        Integer currentStatus = ProposalStatus.PROPOSTA_ENVIADA.getId();
        Integer newStatus = ProposalStatus.FINALIZADO_SEM_VENDA.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testExecuteRule_WhenNullComment_ShouldReturnFalse() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        ProposalResponseDTO proposalDTO = new ProposalResponseDTO();
        proposalDTO.setFinishedWithoutSaleComment(null);
        proposal.setProposal(proposalDTO);
        
        Integer currentStatus = ProposalStatus.PROPOSTA_ENVIADA.getId();
        Integer newStatus = ProposalStatus.FINALIZADO_SEM_VENDA.getId();
        
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
        proposalDTO.setFinishedWithoutSaleComment(null); // This would normally cause the rule to fail
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
    public void testGetTargetStatus_ShouldReturnFinalizadoSemVenda() {
        // Act
        Integer result = rule.getTargetStatus();
        
        // Assert
        assertEquals(ProposalStatus.FINALIZADO_SEM_VENDA.getId(), result);
    }
    
    @Test
    public void testGetErrorMessage_ShouldReturnCustomMessage() {
        // Act
        String errorMessage = rule.getErrorMessage();
        
        // Assert
        assertNotNull(errorMessage);
        assertEquals("Para finalizar sem venda, é necessário informar um motivo com pelo menos 10 caracteres.", errorMessage);
    }
}
