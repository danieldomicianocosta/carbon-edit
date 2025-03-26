package com.carbon.refactor.bff.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.application.dto.request.CompleteProposalRequestDTO;
import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.bff.facade.ProposalFacade;
import com.carbon.refactor.domain.service.CompleteProposalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * BFF implementation of the CompleteProposalService interface.
 * This implementation delegates to the ProposalFacade, providing a bridge
 * between the existing service interface and the new BFF architecture.
 * 
 * The @Primary annotation ensures that this implementation is used by default
 * when the CompleteProposalService interface is autowired.
 */
@Service
@Primary
@Slf4j
public class BFFCompleteProposalServiceImpl implements CompleteProposalService {
    
    private final ProposalFacade proposalFacade;
    
    public BFFCompleteProposalServiceImpl(ProposalFacade proposalFacade) {
        this.proposalFacade = proposalFacade;
    }
    
    @Override
    @Transactional(readOnly = true)
    public CompleteProposalResponseDTO findCompleteProposalById(Integer id) {
        log.debug("Delegando busca da proposta completa com ID: {} para o ProposalFacade", id);
        return proposalFacade.findCompleteProposalById(id);
    }
    
    @Override
    @Transactional
    public CompleteProposalResponseDTO updateCompleteProposal(Integer id, CompleteProposalRequestDTO completeProposal) {
        log.debug("Delegando atualização da proposta completa com ID: {} para o ProposalFacade", id);
        return proposalFacade.updateCompleteProposal(id, completeProposal);
    }
}
