package com.carbon.refactor.infrastructure.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.entity.ProposalFup;
import com.carbon.refactor.domain.service.ProposalService;
import com.carbon.refactor.infrastructure.dto.request.ProposalFupRequestDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalFupResponseDTO;

import lombok.RequiredArgsConstructor;

/**
 * Mapper for converting between ProposalFup entities and DTOs.
 */
@Component
@RequiredArgsConstructor
public class ProposalFupMapper {
    
    private final ProposalService proposalService;
    
    /**
     * Convert a ProposalFupRequestDTO to a ProposalFup entity.
     * 
     * @param dto The DTO to convert
     * @return The converted entity
     */
    public ProposalFup toEntity(ProposalFupRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Proposal proposal = null;
        if (dto.getProposalId() != null) {
            proposal = proposalService.findById(dto.getProposalId());
        }
        
        LocalDateTime date = dto.getDate();
        if (date == null) {
            date = LocalDateTime.now();
        }
        
        return ProposalFup.builder()
                .proposal(proposal)
                .date(date)
                .mediaClassifierId(dto.getMediaClassifierId())
                .person(dto.getPerson())
                .comment(dto.getComment())
                .followUpTypeClassifierId(dto.getFollowUpTypeClassifierId())
                .userId(dto.getUserId())
                .build();
    }
    
    /**
     * Convert a ProposalFup entity to a ProposalFupResponseDTO.
     * 
     * @param entity The entity to convert
     * @return The converted DTO
     */
    public ProposalFupResponseDTO toDto(ProposalFup entity) {
        if (entity == null) {
            return null;
        }
        
        Integer proposalId = null;
        String proposalName = null;
        if (entity.getProposal() != null) {
            proposalId = entity.getProposal().getId();
            proposalName = entity.getProposal().getProposalNumber();
        }
        
        return ProposalFupResponseDTO.builder()
                .id(entity.getId())
                .proposalId(proposalId)
                .proposalName(proposalName)
                .date(entity.getDate())
                .mediaClassifierId(entity.getMediaClassifierId())
                .person(entity.getPerson())
                .comment(entity.getComment())
                .followUpTypeClassifierId(entity.getFollowUpTypeClassifierId())
                .userId(entity.getUserId())
                .build();
    }
    
    /**
     * Convert a list of ProposalFup entities to a list of ProposalFupResponseDTOs.
     * 
     * @param entities The entities to convert
     * @return The converted DTOs
     */
    public List<ProposalFupResponseDTO> toDtoList(List<ProposalFup> entities) {
        if (entities == null) {
            return null;
        }
        
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Update a ProposalFup entity with data from a ProposalFupRequestDTO.
     * 
     * @param entity The entity to update
     * @param dto The DTO containing the new data
     * @return The updated entity
     */
    public ProposalFup updateEntityFromDto(ProposalFup entity, ProposalFupRequestDTO dto) {
        if (entity == null || dto == null) {
            return entity;
        }
        
        if (dto.getProposalId() != null) {
            Proposal proposal = proposalService.findById(dto.getProposalId());
            entity.setProposal(proposal);
        }
        
        if (dto.getDate() != null) {
            entity.setDate(dto.getDate());
        }
        
        entity.setMediaClassifierId(dto.getMediaClassifierId());
        entity.setPerson(dto.getPerson());
        entity.setComment(dto.getComment());
        entity.setFollowUpTypeClassifierId(dto.getFollowUpTypeClassifierId());
        entity.setUserId(dto.getUserId());
        
        return entity;
    }
}
