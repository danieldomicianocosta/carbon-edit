package com.carbon.refactor.application.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.infrastructure.security.UserContext;
import com.carbon.refactor.infrastructure.security.UserContext.UserInfo;

@ExtendWith(MockitoExtension.class)
public class FinalizadoComVendaToCanceladoRuleTest {

    @InjectMocks
    private FinalizadoComVendaToCanceladoRule rule;
    
    private MockedStatic<UserContext> userContextMock;
    
    @BeforeEach
    public void setUp() {
        userContextMock = mockStatic(UserContext.class);
    }
    
    @AfterEach
    public void tearDown() {
        userContextMock.close();
    }
    
    @Test
    public void testExecuteRule_WhenUserHasCOMCEORole_ShouldReturnTrue() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        Integer currentStatus = ProposalStatus.FINALIZADO_COM_VENDA.getId();
        Integer newStatus = ProposalStatus.CANCELADO.getId();
        
        UserInfo userInfo = new UserInfo("ceo@example.com", "COM_CEO");
        userContextMock.when(UserContext::getCurrentUser).thenReturn(userInfo);
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testExecuteRule_WhenUserHasDifferentRole_ShouldReturnFalse() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        Integer currentStatus = ProposalStatus.FINALIZADO_COM_VENDA.getId();
        Integer newStatus = ProposalStatus.CANCELADO.getId();
        
        UserInfo userInfo = new UserInfo("user@example.com", "SOME_OTHER_ROLE");
        userContextMock.when(UserContext::getCurrentUser).thenReturn(userInfo);
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testExecuteRule_WhenUserHasNoRole_ShouldReturnFalse() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        Integer currentStatus = ProposalStatus.FINALIZADO_COM_VENDA.getId();
        Integer newStatus = ProposalStatus.CANCELADO.getId();
        
        UserInfo userInfo = new UserInfo("user@example.com", null);
        userContextMock.when(UserContext::getCurrentUser).thenReturn(userInfo);
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testExecuteRule_WhenNoUser_ShouldReturnFalse() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        Integer currentStatus = ProposalStatus.FINALIZADO_COM_VENDA.getId();
        Integer newStatus = ProposalStatus.CANCELADO.getId();
        
        userContextMock.when(UserContext::getCurrentUser).thenReturn(null);
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testExecuteRule_WhenDifferentTransition_ShouldReturnTrue() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        Integer currentStatus = ProposalStatus.VALIDACAO_BACKOFFICE.getId();
        Integer newStatus = ProposalStatus.CANCELADO.getId();
        
        // No need to mock UserContext as the rule should return true without checking it
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testGetSourceStatus_ShouldReturnFinalizadoComVenda() {
        // Act
        Integer result = rule.getSourceStatus();
        
        // Assert
        assertTrue(result.equals(ProposalStatus.FINALIZADO_COM_VENDA.getId()));
    }
    
    @Test
    public void testGetTargetStatus_ShouldReturnCancelado() {
        // Act
        Integer result = rule.getTargetStatus();
        
        // Assert
        assertTrue(result.equals(ProposalStatus.CANCELADO.getId()));
    }
    
    @Test
    public void testGetErrorMessage_ShouldReturnCustomMessage() {
        // Act
        String errorMessage = rule.getErrorMessage();
        
        // Assert
        assertNotNull(errorMessage);
        assertEquals("Apenas usuários com o papel COM_CEO podem cancelar propostas finalizadas com venda.", errorMessage);
    }
}
