package com.carbon.refactor.infrastructure.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbon.refactor.application.dto.request.CompleteProposalRequestDTO;
import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para operações relacionadas a propostas completas.
 * Utiliza rotas Camel para processar as requisições.
 * 
 * Note: This controller has been re-enabled with Camel 4.x which is compatible with Jakarta EE.
 */
@RestController
@RequestMapping("/api/proposals")
@RequiredArgsConstructor
@Slf4j
public class CamelCompleteProposalController {

    private final ProducerTemplate producerTemplate;

    /**
     * Busca uma proposta completa pelo ID.
     * 
     * @param id ID da proposta
     * @return Proposta completa
     */
    @GetMapping("/{id}/complete")
    public ResponseEntity<CompleteProposalResponseDTO> findCompleteProposalById(@PathVariable Integer id) {
        log.info("REST request to find complete proposal with ID: {}", id);
        CompleteProposalResponseDTO response = producerTemplate.requestBody("direct:findCompleteProposal", id, CompleteProposalResponseDTO.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Atualiza uma proposta completa pelo ID.
     * 
     * @param id ID da proposta
     * @param requestDTO DTO com os dados da proposta
     * @return Proposta completa atualizada
     */
    @PutMapping("/{id}/complete")
    public ResponseEntity<CompleteProposalResponseDTO> updateCompleteProposal(
            @PathVariable Integer id,
            @RequestBody CompleteProposalRequestDTO requestDTO) {
        
        log.info("REST request to update complete proposal with ID: {}", id);
        CompleteProposalResponseDTO response = producerTemplate.requestBodyAndHeader(
                "direct:updateCompleteProposal", 
                requestDTO, 
                "id", id, 
                CompleteProposalResponseDTO.class);
        
        return ResponseEntity.ok(response);
    }
}
