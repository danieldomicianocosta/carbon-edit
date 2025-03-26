package com.carbon.refactor.infrastructure.camel.validator;

import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.request.CompleteProposalRequestDTO;
import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.exception.EntityNotFoundException;
import com.carbon.refactor.domain.repository.ProposalRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Validador para validar a existência da proposta e os dados de entrada para atualização.
 * 
 * Note: This component has been re-enabled with Camel 4.x which is compatible with Jakarta EE.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ProposalValidator {

    private final ProposalRepository proposalRepository;

    /**
     * Valida a existência da proposta.
     * 
     * @param proposalId ID da proposta
     * @return A proposta encontrada
     * @throws EntityNotFoundException se a proposta não for encontrada
     */
    public Proposal validateExistence(Integer proposalId) {
        log.debug("Validando existência da proposta com ID: {}", proposalId);
        
        return proposalRepository.findById(proposalId)
                .orElseThrow(() -> {
                    log.error("Proposta não encontrada com ID: {}", proposalId);
                    return new EntityNotFoundException("Proposal", proposalId);
                });
    }

    /**
     * Valida os dados de entrada para atualização da proposta.
     * 
     * @param requestDTO DTO com os dados de entrada
     * @return O mesmo DTO se for válido
     * @throws IllegalArgumentException se os dados forem inválidos
     */
    public CompleteProposalRequestDTO validateUpdateRequest(CompleteProposalRequestDTO requestDTO) {
        log.debug("Validando dados de entrada para atualização da proposta");
        
        if (requestDTO == null) {
            throw new IllegalArgumentException("Dados de entrada não podem ser nulos");
        }
        
        // Validações específicas podem ser adicionadas aqui
        // Por exemplo, validar se os IDs de entidades relacionadas existem
        
        return requestDTO;
    }
}
