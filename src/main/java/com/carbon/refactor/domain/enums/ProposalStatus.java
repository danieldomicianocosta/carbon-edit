package com.carbon.refactor.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum representing the possible statuses of a proposal.
 */
@Getter
@RequiredArgsConstructor
public enum ProposalStatus {
    
    INITIAL(60, "Início do Fluxo"),
    EM_EDICAO(61, "Em Edição"),
    REPROVADO_COMERCIAL(63, "Reprovado Comercial"),
    APROVADO_COMERCIAL(64, "Aprovado Comercial"),
    EM_APROVACAO_COMERCIAL(62, "Em Aprovação Comercial"),
    APROVACAO_COMERCIAL(64, "Aprovação Comercial"),
    PROPOSTA_ENVIADA(65, "Proposta Enviada"),
    VALIDACAO_BACKOFFICE(66, "Validação Backoffice"),
    APROVADO_BACKOFFICE(72, "Aprovado Backoffice"),
    FINALIZADO_SEM_VENDA(69, "Finalizado Sem Venda"),
    REPROVADO_BACKOFFICE(67, "Reprovado Backoffice"),
    ENCERRADO_SEM_RETORNO(70, "Encerrado Sem Retorno"),
    FINALIZADO_COM_VENDA(68, "Finalizado Com Venda"),
    CANCELADO(71, "Cancelado");
    
    private final Integer id;
    private final String description;
    
    /**
     * Find a ProposalStatus by its ID.
     *
     * @param id The ID to search for
     * @return The ProposalStatus with the given ID, or null if not found
     */
    public static ProposalStatus findById(Integer id) {
        if (id == null) {
            return null;
        }
        
        for (ProposalStatus status : values()) {
            if (status.getId().equals(id)) {
                return status;
            }
        }
        
        return null;
    }
}
