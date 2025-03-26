package com.carbon.refactor.infrastructure.camel.processor;

import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.carbon.refactor.application.dto.ProposalDetailRequestDTO;
import com.carbon.refactor.application.dto.ProposalDetailResponseDTO;
import com.carbon.refactor.application.mapper.ProposalDetailMapper;
import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.entity.ProposalDetail;
import com.carbon.refactor.domain.repository.ProposalRepository;
import com.carbon.refactor.domain.service.ProposalDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Processador Camel para atualizar os detalhes da proposta.
 * 
 * Note: This component has been re-enabled with Camel 4.x which is compatible with Jakarta EE.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ProposalDetailProcessor implements Processor {

    private final ProposalDetailService proposalDetailService;
    private final ProposalRepository proposalRepository;
    private final ProposalDetailMapper proposalDetailMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer proposalId = exchange.getProperty("proposalId", Integer.class);
        ProposalDetailRequestDTO requestDTO = exchange.getIn().getBody(ProposalDetailRequestDTO.class);
        
        log.debug("Processando atualização dos detalhes da proposta com ID: {}", proposalId);
        
        // Busca a proposta
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new IllegalStateException("Proposta não encontrada com ID: " + proposalId));
        
        // Verifica se já existe um detalhe para esta proposta
        Optional<ProposalDetail> existingDetailOpt = proposalDetailService.existsByProposalId(proposalId)
                ? Optional.of(proposalDetailService.findByProposalId(proposalId))
                : Optional.empty();
        
        ProposalDetail updatedDetail;
        
        if (existingDetailOpt.isPresent()) {
            // Atualiza o detalhe existente
            ProposalDetail existingDetail = existingDetailOpt.get();
            ProposalDetail detailToUpdate = proposalDetailMapper.toEntity(requestDTO, existingDetail.getId());
            detailToUpdate.setProposal(proposal);
            updatedDetail = proposalDetailService.update(existingDetail.getId(), detailToUpdate);
        } else {
            // Cria um novo detalhe
            ProposalDetail newDetail = proposalDetailMapper.toEntity(requestDTO);
            newDetail.setProposal(proposal);
            updatedDetail = proposalDetailService.create(newDetail);
        }
        
        // Converte a entidade atualizada para DTO
        ProposalDetailResponseDTO responseDTO = proposalDetailMapper.toDto(updatedDetail);
        
        // Define o corpo da mensagem como o DTO de resposta
        exchange.getIn().setBody(responseDTO);
    }
}
