package com.carbon.refactor.application.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.domain.repository.ProposalRepository;
import com.carbon.refactor.domain.service.ProposalService;

@ExtendWith(MockitoExtension.class)
public class ProposalStatusServiceImplTest {
    
    @Mock
    private ProposalService proposalService;
    
    @Mock
    private ProposalRepository proposalRepository;
    
    @InjectMocks
    private ProposalStatusServiceImpl proposalStatusService;
    
    @BeforeEach
    public void setUp() {
        // The service initializes the valid transitions in the constructor
        // No additional setup needed
    }
    
    @Test
    public void testValidStatusTransitions() {
        // Test valid transitions
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.INITIAL.getId(), ProposalStatus.EM_EDICAO.getId()));
        
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.EM_EDICAO.getId(), ProposalStatus.EM_APROVACAO_COMERCIAL.getId()));
        
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.EM_APROVACAO_COMERCIAL.getId(), ProposalStatus.APROVADO_COMERCIAL.getId()));
        
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.APROVADO_COMERCIAL.getId(), ProposalStatus.PROPOSTA_ENVIADA.getId()));
        
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.PROPOSTA_ENVIADA.getId(), ProposalStatus.VALIDACAO_BACKOFFICE.getId()));
        
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.VALIDACAO_BACKOFFICE.getId(), ProposalStatus.FINALIZADO_COM_VENDA.getId()));
    }
    
    @Test
    public void testInvalidStatusTransitions() {
        // Test invalid transitions
        assertFalse(proposalStatusService.isValidStatusTransition(
                ProposalStatus.INITIAL.getId(), ProposalStatus.APROVADO_COMERCIAL.getId()));
        
        assertFalse(proposalStatusService.isValidStatusTransition(
                ProposalStatus.EM_EDICAO.getId(), ProposalStatus.APROVADO_BACKOFFICE.getId()));
        
        assertFalse(proposalStatusService.isValidStatusTransition(
                ProposalStatus.PROPOSTA_ENVIADA.getId(), ProposalStatus.APROVADO_COMERCIAL.getId()));
        
        assertFalse(proposalStatusService.isValidStatusTransition(
                ProposalStatus.VALIDACAO_BACKOFFICE.getId(), ProposalStatus.EM_APROVACAO_COMERCIAL.getId()));
        
        assertFalse(proposalStatusService.isValidStatusTransition(
                ProposalStatus.FINALIZADO_COM_VENDA.getId(), ProposalStatus.PROPOSTA_ENVIADA.getId()));
    }
    
    @Test
    public void testSameStatusIsValid() {
        // Same status should be valid (no transition)
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.EM_EDICAO.getId(), ProposalStatus.EM_EDICAO.getId()));
        
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.APROVADO_COMERCIAL.getId(), ProposalStatus.APROVADO_COMERCIAL.getId()));
    }
    
    @Test
    public void testRenegotiationFlow() {
        // Test renegotiation flow: PROPOSTA_ENVIADA -> EM_EDICAO
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.PROPOSTA_ENVIADA.getId(), ProposalStatus.EM_EDICAO.getId()));
    }
    
    @Test
    public void testRejectionFlows() {
        // Test commercial rejection flow
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.EM_APROVACAO_COMERCIAL.getId(), ProposalStatus.REPROVADO_COMERCIAL.getId()));
        
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.REPROVADO_COMERCIAL.getId(), ProposalStatus.EM_APROVACAO_COMERCIAL.getId()));
        
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.REPROVADO_COMERCIAL.getId(), ProposalStatus.PROPOSTA_ENVIADA.getId()));
        
        // Test backoffice rejection flow
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.VALIDACAO_BACKOFFICE.getId(), ProposalStatus.REPROVADO_BACKOFFICE.getId()));
        
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.REPROVADO_BACKOFFICE.getId(), ProposalStatus.VALIDACAO_BACKOFFICE.getId()));
    }
    
    @Test
    public void testFinalizationFlows() {
        // Test finalization without sale flow
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.PROPOSTA_ENVIADA.getId(), ProposalStatus.FINALIZADO_SEM_VENDA.getId()));
        
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.FINALIZADO_SEM_VENDA.getId(), ProposalStatus.ENCERRADO_SEM_RETORNO.getId()));
        
        // Test finalization with sale flow
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.VALIDACAO_BACKOFFICE.getId(), ProposalStatus.FINALIZADO_COM_VENDA.getId()));
    }
    
    @Test
    public void testCancellationFlows() {
        // Test cancellation from validation backoffice
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.VALIDACAO_BACKOFFICE.getId(), ProposalStatus.CANCELADO.getId()));
        
        // Test cancellation from finalized with sale
        assertTrue(proposalStatusService.isValidStatusTransition(
                ProposalStatus.FINALIZADO_COM_VENDA.getId(), ProposalStatus.CANCELADO.getId()));
    }
}
