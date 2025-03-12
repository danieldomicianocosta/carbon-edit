package com.carbon.refactor.application.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.domain.rule.ProposalStatusRule;
import com.carbon.refactor.domain.service.ProposalStatusService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the ProposalStatusService interface.
 * This service handles the validation and updating of proposal statuses
 * according to the state machine defined for the proposal flow.
 */
@Service
@RequiredArgsConstructor
public class ProposalStatusServiceImpl implements ProposalStatusService {
    
    // Map to store valid transitions for each status
    private final Map<Integer, Set<Integer>> validTransitions = initializeValidTransitions();
    
    // List to store rules for status transitions
    private final List<ProposalStatusRule> rules = new ArrayList<>();
    
    /**
     * Initialize the valid transitions map based on the proposal state machine.
     * 
     * @return A map containing the valid transitions for each status
     */
    private Map<Integer, Set<Integer>> initializeValidTransitions() {
        Map<Integer, Set<Integer>> transitions = new HashMap<>();
        
        // INITIAL -> EM_EDICAO
        addTransition(transitions, ProposalStatus.INITIAL.getId(), ProposalStatus.EM_EDICAO.getId());
        
        // EM_EDICAO -> EM_EDICAO
        addTransition(transitions, ProposalStatus.EM_EDICAO.getId(), ProposalStatus.EM_EDICAO.getId());
        
        // EM_EDICAO -> PROPOSTA_ENVIADA
        addTransition(transitions, ProposalStatus.EM_EDICAO.getId(), ProposalStatus.PROPOSTA_ENVIADA.getId());
        
        // EM_EDICAO -> EM_APROVACAO_COMERCIAL
        addTransition(transitions, ProposalStatus.EM_EDICAO.getId(), ProposalStatus.EM_APROVACAO_COMERCIAL.getId());
        
        // EM_EDICAO -> FINALIZADO_SEM_VENDA
        addTransition(transitions, ProposalStatus.EM_EDICAO.getId(), ProposalStatus.FINALIZADO_SEM_VENDA.getId());
        
        // EM_APROVACAO_COMERCIAL -> APROVADO_COMERCIAL
        addTransition(transitions, ProposalStatus.EM_APROVACAO_COMERCIAL.getId(), ProposalStatus.APROVADO_COMERCIAL.getId());
        
        // EM_APROVACAO_COMERCIAL -> REPROVADO_COMERCIAL
        addTransition(transitions, ProposalStatus.EM_APROVACAO_COMERCIAL.getId(), ProposalStatus.REPROVADO_COMERCIAL.getId());
        
        // EM_APROVACAO_COMERCIAL -> EM_APROVACAO_COMERCIAL
        addTransition(transitions, ProposalStatus.EM_APROVACAO_COMERCIAL.getId(), ProposalStatus.EM_APROVACAO_COMERCIAL.getId());
        
        // EM_APROVACAO_COMERCIAL -> FINALIZADO_SEM_VENDA
        addTransition(transitions, ProposalStatus.EM_APROVACAO_COMERCIAL.getId(), ProposalStatus.FINALIZADO_SEM_VENDA.getId());
        
        // REPROVADO_COMERCIAL -> EM_APROVACAO_COMERCIAL
        addTransition(transitions, ProposalStatus.REPROVADO_COMERCIAL.getId(), ProposalStatus.EM_APROVACAO_COMERCIAL.getId());
        
        // REPROVADO_COMERCIAL -> PROPOSTA_ENVIADA
        addTransition(transitions, ProposalStatus.REPROVADO_COMERCIAL.getId(), ProposalStatus.PROPOSTA_ENVIADA.getId());
        
        // REPROVADO_COMERCIAL -> REPROVADO_COMERCIAL
        addTransition(transitions, ProposalStatus.REPROVADO_COMERCIAL.getId(), ProposalStatus.REPROVADO_COMERCIAL.getId());
        
        // REPROVADO_COMERCIAL -> FINALIZADO_SEM_VENDA
        addTransition(transitions, ProposalStatus.REPROVADO_COMERCIAL.getId(), ProposalStatus.FINALIZADO_SEM_VENDA.getId());
        
        // APROVADO_COMERCIAL -> PROPOSTA_ENVIADA
        addTransition(transitions, ProposalStatus.APROVADO_COMERCIAL.getId(), ProposalStatus.PROPOSTA_ENVIADA.getId());
        
        // APROVADO_COMERCIAL -> APROVADO_COMERCIAL
        addTransition(transitions, ProposalStatus.APROVADO_COMERCIAL.getId(), ProposalStatus.APROVADO_COMERCIAL.getId());
        
        // APROVADO_COMERCIAL -> FINALIZADO_SEM_VENDA
        addTransition(transitions, ProposalStatus.APROVADO_COMERCIAL.getId(), ProposalStatus.FINALIZADO_SEM_VENDA.getId());
        
        // PROPOSTA_ENVIADA -> VALIDACAO_BACKOFFICE
        addTransition(transitions, ProposalStatus.PROPOSTA_ENVIADA.getId(), ProposalStatus.VALIDACAO_BACKOFFICE.getId());
        
        // PROPOSTA_ENVIADA -> EM_EDICAO
        addTransition(transitions, ProposalStatus.PROPOSTA_ENVIADA.getId(), ProposalStatus.EM_EDICAO.getId());
        
        // PROPOSTA_ENVIADA -> PROPOSTA_ENVIADA
        addTransition(transitions, ProposalStatus.PROPOSTA_ENVIADA.getId(), ProposalStatus.PROPOSTA_ENVIADA.getId());
        
        // PROPOSTA_ENVIADA -> FINALIZADO_SEM_VENDA
        addTransition(transitions, ProposalStatus.PROPOSTA_ENVIADA.getId(), ProposalStatus.FINALIZADO_SEM_VENDA.getId());
        
        // VALIDACAO_BACKOFFICE -> FINALIZADO_COM_VENDA
        addTransition(transitions, ProposalStatus.VALIDACAO_BACKOFFICE.getId(), ProposalStatus.FINALIZADO_COM_VENDA.getId());
        
        // VALIDACAO_BACKOFFICE -> REPROVADO_BACKOFFICE
        addTransition(transitions, ProposalStatus.VALIDACAO_BACKOFFICE.getId(), ProposalStatus.REPROVADO_BACKOFFICE.getId());
        
        // VALIDACAO_BACKOFFICE -> VALIDACAO_BACKOFFICE
        addTransition(transitions, ProposalStatus.VALIDACAO_BACKOFFICE.getId(), ProposalStatus.VALIDACAO_BACKOFFICE.getId());
        
        // VALIDACAO_BACKOFFICE -> CANCELADO
        addTransition(transitions, ProposalStatus.VALIDACAO_BACKOFFICE.getId(), ProposalStatus.CANCELADO.getId());
        
        // REPROVADO_BACKOFFICE -> VALIDACAO_BACKOFFICE
        addTransition(transitions, ProposalStatus.REPROVADO_BACKOFFICE.getId(), ProposalStatus.VALIDACAO_BACKOFFICE.getId());
        
        // REPROVADO_BACKOFFICE -> REPROVADO_BACKOFFICE
        addTransition(transitions, ProposalStatus.REPROVADO_BACKOFFICE.getId(), ProposalStatus.REPROVADO_BACKOFFICE.getId());
        
        // FINALIZADO_COM_VENDA -> FINALIZADO_COM_VENDA
        addTransition(transitions, ProposalStatus.FINALIZADO_COM_VENDA.getId(), ProposalStatus.FINALIZADO_COM_VENDA.getId());
        
        // FINALIZADO_COM_VENDA -> CANCELADO
        addTransition(transitions, ProposalStatus.FINALIZADO_COM_VENDA.getId(), ProposalStatus.CANCELADO.getId());
        
        // FINALIZADO_SEM_VENDA -> ENCERRADO_SEM_RETORNO
        addTransition(transitions, ProposalStatus.FINALIZADO_SEM_VENDA.getId(), ProposalStatus.ENCERRADO_SEM_RETORNO.getId());
        
        return transitions;
    }
    
    /**
     * Helper method to add a transition to the map.
     * 
     * @param transitions The map of transitions
     * @param fromStatus The status to transition from
     * @param toStatus The status to transition to
     */
    private void addTransition(Map<Integer, Set<Integer>> transitions, Integer fromStatus, Integer toStatus) {
        transitions.computeIfAbsent(fromStatus, k -> new HashSet<>()).add(toStatus);
    }
    
    @Override
    public boolean isValidStatusTransition(Integer currentStatus, Integer newStatus) {
        // If the statuses are the same, it's not a transition
        if (currentStatus.equals(newStatus)) {
            return true;
        }
        
        // Check if the transition is valid
        Set<Integer> validNextStatuses = validTransitions.get(currentStatus);
        return validNextStatuses != null && validNextStatuses.contains(newStatus);
    }
    
    
    @Override
    public void registerRule(ProposalStatusRule rule) {
        rules.add(rule);
    }
    
    @Override
    public boolean executeRules(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus) {
        // Filter rules that apply to this transition
        List<ProposalStatusRule> applicableRules = rules.stream()
                .filter(rule -> rule.getSourceStatus().equals(currentStatus) && rule.getTargetStatus().equals(newStatus))
                .collect(Collectors.toList());
        
        // If no rules apply, return true
        if (applicableRules.isEmpty()) {
            return true;
        }
        
        // Execute all applicable rules
        boolean allRulesSucceeded = true;
        for (ProposalStatusRule rule : applicableRules) {
            boolean ruleResult = rule.executeRule(proposal, currentStatus, newStatus);
            if (!ruleResult) {
                allRulesSucceeded = false;
                // We continue executing all rules even if one fails
            }
        }
        
        return allRulesSucceeded;
    }
    
    @Override
    public String getErrorMessage(CompleteProposalResponseDTO proposal, Integer currentStatus, Integer newStatus) {
        // Filter rules that apply to this transition
        List<ProposalStatusRule> applicableRules = rules.stream()
                .filter(rule -> rule.getSourceStatus().equals(currentStatus) && rule.getTargetStatus().equals(newStatus))
                .collect(Collectors.toList());
        
        // If no rules apply, return null
        if (applicableRules.isEmpty()) {
            return null;
        }
        
        // Execute all applicable rules and collect error messages from failed rules
        for (ProposalStatusRule rule : applicableRules) {
            boolean ruleResult = rule.executeRule(proposal, currentStatus, newStatus);
            if (!ruleResult) {
                // Return the first error message we find
                String errorMessage = rule.getErrorMessage();
                if (errorMessage != null && !errorMessage.isEmpty()) {
                    return errorMessage;
                }
            }
        }
        
        // If no specific error message was found, return a default message
        return "A regra de transição de status não permite esta operação.";
    }
    
    @Override
    public List<Integer> getPossibleNextStatuses(Integer currentStatus) {
        // Get the set of valid next statuses for the current status
        Set<Integer> nextStatuses = validTransitions.get(currentStatus);
        
        // If there are no valid transitions, return an empty list
        if (nextStatuses == null) {
            return Collections.emptyList();
        }
        
        // Convert the set to a list and return it
        return new ArrayList<>(nextStatuses);
    }
}
