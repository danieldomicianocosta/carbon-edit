package com.carbon.refactor.application.service;

import org.apache.camel.ProducerTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.application.dto.request.CompleteProposalRequestDTO;
import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.domain.service.CompleteProposalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementação do CompleteProposalService que usa o Apache Camel para processar as requisições.
 * Esta implementação delega o processamento para as rotas Camel, que implementam os padrões
 * de integração empresarial (EIPs) para orquestrar o fluxo de dados.
 * 
 * Note: This implementation has been re-enabled with Camel 4.x which is compatible with Jakarta EE.
 */
@Service
@Primary
@RequiredArgsConstructor
@Slf4j
public class CamelCompleteProposalServiceImpl implements CompleteProposalService {

    private final ProducerTemplate producerTemplate;

    @Override
    @Transactional(readOnly = true)
    public CompleteProposalResponseDTO findCompleteProposalById(Integer id) {
        log.info("Delegating findCompleteProposalById to Camel route for ID: {}", id);
        return producerTemplate.requestBody("direct:findCompleteProposal", id, CompleteProposalResponseDTO.class);
    }

    @Override
    @Transactional
    public CompleteProposalResponseDTO updateCompleteProposal(Integer id, CompleteProposalRequestDTO completeProposalRequest) {
        log.info("Delegating updateCompleteProposal to Camel route for ID: {}", id);
        return producerTemplate.requestBodyAndHeader("direct:updateCompleteProposal", 
                                                    completeProposalRequest, 
                                                    "id", id, 
                                                    CompleteProposalResponseDTO.class);
    }
}
