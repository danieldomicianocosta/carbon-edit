package com.carbon.refactor.infrastructure.camel.enricher;

import java.util.List;

import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.application.dto.response.PossibleNextStatusesResponseDTO.StatusDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * Enriquecedor responsável por adicionar informações adicionais à proposta completa.
 * Implementa o padrão Content Enricher do Enterprise Integration Patterns.
 * 
 * Note: This component has been re-enabled with Camel 4.x which is compatible with Jakarta EE.
 */
@Component
@Slf4j
public class ProposalResultEnricher {

    /**
     * Enriquece a proposta completa com os possíveis próximos status.
     * 
     * @param completeProposal Proposta completa
     * @param possibleNextStatuses Possíveis próximos status
     * @return Proposta completa enriquecida
     */
    public CompleteProposalResponseDTO enrichWithPossibleStatuses(
            CompleteProposalResponseDTO completeProposal,
            List<StatusDTO> possibleNextStatuses) {
        
        log.debug("Enriquecendo proposta com ID: {} com {} possíveis próximos status",
                completeProposal.getProposal() != null ? completeProposal.getProposal().getId() : null,
                possibleNextStatuses != null ? possibleNextStatuses.size() : 0);
        
        completeProposal.setPossibleNextStatuses(possibleNextStatuses);
        
        return completeProposal;
    }
}
