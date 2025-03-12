package com.carbon.refactor.application.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.infrastructure.dto.response.ProposalDetailVehicleItemResponseDTO;

@ExtendWith(MockitoExtension.class)
public class FinalizadoComVendaRuleTest {

    @InjectMocks
    private FinalizadoComVendaRule rule;
    
    @Test
    public void testExecuteRule_WhenHasVehicleItemWithPositivePrice_ShouldReturnTrue() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        List<ProposalDetailVehicleItemResponseDTO> vehicleItems = new ArrayList<>();
        
        ProposalDetailVehicleItemResponseDTO item = new ProposalDetailVehicleItemResponseDTO();
        item.setFinalPrice(BigDecimal.valueOf(1000));
        vehicleItems.add(item);
        
        proposal.setProposalDetailVehicleItems(vehicleItems);
        
        Integer currentStatus = ProposalStatus.APROVADO_BACKOFFICE.getId();
        Integer newStatus = ProposalStatus.FINALIZADO_COM_VENDA.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testExecuteRule_WhenHasVehicleItemWithZeroPrice_ShouldReturnFalse() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        List<ProposalDetailVehicleItemResponseDTO> vehicleItems = new ArrayList<>();
        
        ProposalDetailVehicleItemResponseDTO item = new ProposalDetailVehicleItemResponseDTO();
        item.setFinalPrice(BigDecimal.ZERO);
        vehicleItems.add(item);
        
        proposal.setProposalDetailVehicleItems(vehicleItems);
        
        Integer currentStatus = ProposalStatus.APROVADO_BACKOFFICE.getId();
        Integer newStatus = ProposalStatus.FINALIZADO_COM_VENDA.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testExecuteRule_WhenNoVehicleItems_ShouldReturnFalse() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        proposal.setProposalDetailVehicleItems(Collections.emptyList());
        
        Integer currentStatus = ProposalStatus.APROVADO_BACKOFFICE.getId();
        Integer newStatus = ProposalStatus.FINALIZADO_COM_VENDA.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testExecuteRule_WhenNullVehicleItems_ShouldReturnFalse() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        proposal.setProposalDetailVehicleItems(null);
        
        Integer currentStatus = ProposalStatus.APROVADO_BACKOFFICE.getId();
        Integer newStatus = ProposalStatus.FINALIZADO_COM_VENDA.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testExecuteRule_WhenDifferentTransition_ShouldReturnTrue() {
        // Arrange
        CompleteProposalResponseDTO proposal = new CompleteProposalResponseDTO();
        proposal.setProposalDetailVehicleItems(null); // This would normally cause the rule to fail
        
        Integer currentStatus = ProposalStatus.VALIDACAO_BACKOFFICE.getId();
        Integer newStatus = ProposalStatus.CANCELADO.getId();
        
        // Act
        boolean result = rule.executeRule(proposal, currentStatus, newStatus);
        
        // Assert
        assertTrue(result);
    }
    
    @Test
    public void testGetSourceStatus_ShouldReturnAprovadoBackoffice() {
        // Act
        Integer result = rule.getSourceStatus();
        
        // Assert
        assertEquals(ProposalStatus.APROVADO_BACKOFFICE.getId(), result);
    }
    
    @Test
    public void testGetTargetStatus_ShouldReturnFinalizadoComVenda() {
        // Act
        Integer result = rule.getTargetStatus();
        
        // Assert
        assertEquals(ProposalStatus.FINALIZADO_COM_VENDA.getId(), result);
    }
    
    @Test
    public void testGetErrorMessage_ShouldReturnCustomMessage() {
        // Act
        String errorMessage = rule.getErrorMessage();
        
        // Assert
        assertNotNull(errorMessage);
        assertEquals("Para finalizar com venda, a proposta deve ter pelo menos um item de veículo com preço final maior que zero.", errorMessage);
    }
}
