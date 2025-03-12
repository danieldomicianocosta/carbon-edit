package com.carbon.refactor.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.entity.ProposalDocument;
import com.carbon.refactor.domain.service.ProposalService;
import com.carbon.refactor.infrastructure.dto.request.ProposalDocumentRequestDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalDocumentResponseDTO;
import com.carbon.refactor.support.domain.entity.Document;
import com.carbon.refactor.support.domain.service.DocumentService;

import lombok.RequiredArgsConstructor;

/**
 * Mapper for converting between ProposalDocument entities and DTOs.
 */
@Component
@RequiredArgsConstructor
public class ProposalDocumentMapper {
    
    private final ProposalService proposalService;
    private final DocumentService documentService;
    
    /**
     * Convert a ProposalDocumentRequestDTO to a ProposalDocument entity.
     * 
     * @param dto The DTO to convert
     * @return The converted entity
     */
    public ProposalDocument toEntity(ProposalDocumentRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Proposal proposal = null;
        if (dto.getProposalId() != null) {
            proposal = proposalService.findById(dto.getProposalId());
        }
        
        Document document = null;
        if (dto.getDocumentId() != null) {
            document = documentService.findById(dto.getDocumentId()).orElse(null);
        }
        
        return ProposalDocument.builder()
                .proposal(proposal)
                .document(document)
                .build();
    }
    
    /**
     * Convert a ProposalDocument entity to a ProposalDocumentResponseDTO.
     * 
     * @param entity The entity to convert
     * @return The converted DTO
     */
    public ProposalDocumentResponseDTO toDto(ProposalDocument entity) {
        if (entity == null) {
            return null;
        }
        
        Integer proposalId = null;
        String proposalName = null;
        if (entity.getProposal() != null) {
            proposalId = entity.getProposal().getId();
            proposalName = entity.getProposal().getProposalNumber();
        }
        
        Integer documentId = null;
        String documentName = null;
        if (entity.getDocument() != null) {
            documentId = entity.getDocument().getId();
            documentName = entity.getDocument().getFileName();
        }
        
        return ProposalDocumentResponseDTO.builder()
                .proposalId(proposalId)
                .documentId(documentId)
                .proposalName(proposalName)
                .documentName(documentName)
                .build();
    }
    
    /**
     * Convert a list of ProposalDocument entities to a list of ProposalDocumentResponseDTOs.
     * 
     * @param entities The entities to convert
     * @return The converted DTOs
     */
    public List<ProposalDocumentResponseDTO> toDtoList(List<ProposalDocument> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
