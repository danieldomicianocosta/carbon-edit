package com.carbon.refactor.analysis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.carbon.refactor.analysis.dto.ProposalCountByStatusDTO;
import com.carbon.refactor.analysis.dto.ProposalsByStatusResponseDTO;
import com.carbon.refactor.analysis.repository.ProposalAnalysisRepository;
import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.entity.ProposalDetail;
import com.carbon.refactor.domain.entity.ProposalDetailVehicle;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.domain.repository.ProposalDetailRepository;
import com.carbon.refactor.domain.repository.ProposalDetailVehicleRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProposalAnalysisServiceImpl implements ProposalAnalysisService {
    
    private final ProposalAnalysisRepository proposalAnalysisRepository;
    private final ProposalDetailRepository proposalDetailRepository;
    private final ProposalDetailVehicleRepository proposalDetailVehicleRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalsByStatusResponseDTO> findProposalsByStatus(Integer statusId) {
        List<Proposal> proposals = proposalAnalysisRepository.findByStatusClaIdWithDetails(statusId);
        return mapToResponseDTOs(proposals);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<ProposalsByStatusResponseDTO> findProposalsByStatus(Integer statusId, Pageable pageable) {
        // Use the optimized query for pagination
        Page<Proposal> proposalPage = proposalAnalysisRepository.findPageByStatusClaId(statusId, pageable);
        
        // Get the IDs of the proposals in the current page
        List<Integer> proposalIds = proposalPage.getContent().stream()
                .map(Proposal::getId)
                .collect(Collectors.toList());
        
        // If there are no proposals in the current page, return an empty page
        if (proposalIds.isEmpty()) {
            return new PageImpl<>(List.of(), pageable, 0);
        }
        
        // Fetch the proposals with their details using the IDs
        List<Proposal> proposalsWithDetails = proposalAnalysisRepository.findAllById(proposalIds);
        
        // Map the proposals to DTOs
        List<ProposalsByStatusResponseDTO> dtos = mapToResponseDTOs(proposalsWithDetails);
        
        // Return a new page with the DTOs
        return new PageImpl<>(dtos, pageable, proposalPage.getTotalElements());
    }
    
    private List<ProposalsByStatusResponseDTO> mapToResponseDTOs(List<Proposal> proposals) {
        return proposals.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProposalCountByStatusDTO> countProposalsByStatus() {
        List<Object[]> results = proposalAnalysisRepository.countGroupByStatus();
        List<ProposalCountByStatusDTO> dtos = new ArrayList<>();
        
        for (Object[] result : results) {
            Integer statusId = (Integer) result[0];
            Long count = (Long) result[1];
            
            ProposalStatus status = ProposalStatus.findById(statusId);
            if (status != null) {
                ProposalCountByStatusDTO dto = ProposalCountByStatusDTO.builder()
                        .statusId(statusId)
                        .statusName(status.name())
                        .statusDescription(status.getDescription())
                        .count(count)
                        .build();
                dtos.add(dto);
            } else {
                // If the status is not found in the enum, still include it with a default description
                ProposalCountByStatusDTO dto = ProposalCountByStatusDTO.builder()
                        .statusId(statusId)
                        .statusName("UNKNOWN")
                        .statusDescription("Unknown Status")
                        .count(count)
                        .build();
                dtos.add(dto);
            }
        }
        
        return dtos;
    }
    
    private ProposalsByStatusResponseDTO mapToResponseDTO(Proposal proposal) {
        ProposalsByStatusResponseDTO dto = new ProposalsByStatusResponseDTO();
        dto.setId(proposal.getId());
        dto.setProposalNumber(proposal.getProposalNumber());
        dto.setCreateDate(proposal.getCreateDate());
        dto.setValidityDate(proposal.getValidityDate());
        dto.setFinishedDate(proposal.getFinishedDate());
        
        // Set status description
        ProposalStatus status = ProposalStatus.findById(proposal.getStatusClaId());
        dto.setStatusDescription(status != null ? status.getDescription() : "");
        
        // Set customer name
        dto.setCustomerName(proposal.getCustomerName());
        
        try {
            // Get proposal detail for service order, partner, and business executive
            ProposalDetail detail = proposalDetailRepository.findByProposalId(proposal.getId())
                    .orElse(null);
            
            if (detail != null) {
                // Set service order
                dto.setServiceOrder(detail.getPurchaseOrderService());
                
                // Set partner name
                // In a real implementation, you would fetch the partner name from a service
                dto.setPartnerName("Partner " + detail.getPartnerId());
                
                // Set business executive
                // In a real implementation, you would fetch the user name from a service
                dto.setBusinessExecutive("Executive " + detail.getUserId());
                
                // Get proposal detail vehicle for model and brand
                ProposalDetailVehicle vehicle = proposalDetailVehicleRepository.findByProposalDetailId(detail.getId())
                        .stream()
                        .findFirst()
                        .orElse(null);
                
                if (vehicle != null) {
                    // Set model and brand
                    // In a real implementation, you would fetch the model and brand names from services
                    dto.setModelBrand("Model " + vehicle.getModelId());
                }
            }
        } catch (Exception e) {
            // Log the exception but continue processing
            log.error("Error fetching related data for proposal {}: {}", proposal.getId(), e.getMessage());
        }
        
        return dto;
    }
}
