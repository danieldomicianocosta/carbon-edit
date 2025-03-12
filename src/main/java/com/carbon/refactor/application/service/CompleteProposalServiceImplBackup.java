package com.carbon.refactor.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.application.dto.ProposalDetailRequestDTO;
import com.carbon.refactor.application.dto.ProposalDetailResponseDTO;
import com.carbon.refactor.application.dto.ProposalDetailVehicleRequestDTO;
import com.carbon.refactor.application.dto.ProposalDetailVehicleResponseDTO;
import com.carbon.refactor.application.dto.ProposalRequestDTO;
import com.carbon.refactor.application.dto.ProposalResponseDTO;
import com.carbon.refactor.application.dto.request.CompleteProposalRequestDTO;
import com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO;
import com.carbon.refactor.application.dto.response.PossibleNextStatusesResponseDTO;
import com.carbon.refactor.application.mapper.ProposalDetailMapper;
import com.carbon.refactor.application.mapper.ProposalDetailVehicleMapper;
import com.carbon.refactor.application.mapper.ProposalMapper;
import com.carbon.refactor.application.util.ProposalStatusUtil;
import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.entity.ProposalCommission;
import com.carbon.refactor.domain.entity.ProposalDetail;
import com.carbon.refactor.domain.entity.ProposalDetailVehicle;
import com.carbon.refactor.domain.entity.ProposalDetailVehicleItem;
import com.carbon.refactor.domain.entity.ProposalDocument;
import com.carbon.refactor.domain.entity.ProposalFup;
import com.carbon.refactor.domain.exception.EntityNotFoundException;
import com.carbon.refactor.domain.repository.ProposalCommissionRepository;
import com.carbon.refactor.domain.repository.ProposalDetailRepository;
import com.carbon.refactor.domain.repository.ProposalDetailVehicleItemRepository;
import com.carbon.refactor.domain.repository.ProposalDetailVehicleRepository;
import com.carbon.refactor.domain.repository.ProposalDocumentRepository;
import com.carbon.refactor.domain.repository.ProposalFupRepository;
import com.carbon.refactor.domain.repository.ProposalRepository;
import com.carbon.refactor.domain.service.CompleteProposalService;
import com.carbon.refactor.domain.service.ProposalCommissionService;
import com.carbon.refactor.domain.service.ProposalDetailService;
import com.carbon.refactor.domain.service.ProposalDetailVehicleItemService;
import com.carbon.refactor.domain.service.ProposalDetailVehicleService;
import com.carbon.refactor.domain.service.ProposalDocumentService;
import com.carbon.refactor.domain.service.ProposalFupService;
import com.carbon.refactor.domain.service.ProposalService;
import com.carbon.refactor.domain.service.ProposalStatusService;
import com.carbon.refactor.infrastructure.dto.request.ProposalCommissionRequestDTO;
import com.carbon.refactor.infrastructure.dto.request.ProposalDetailVehicleItemRequestDTO;
import com.carbon.refactor.infrastructure.dto.request.ProposalDocumentRequestDTO;
import com.carbon.refactor.infrastructure.dto.request.ProposalFupRequestDTO;
import com.carbon.refactor.infrastructure.mapper.ProposalCommissionMapper;
import com.carbon.refactor.infrastructure.mapper.ProposalDetailVehicleItemMapper;
import com.carbon.refactor.infrastructure.mapper.ProposalDocumentMapper;
import com.carbon.refactor.infrastructure.mapper.ProposalFupMapper;
import com.carbon.refactor.support.domain.entity.Document;

import lombok.extern.slf4j.Slf4j;

/**
 * Backup of the original CompleteProposalService implementation.
 * This class is kept for reference purposes only and should not be used in production.
 */
@Slf4j
public class CompleteProposalServiceImplBackup {
    
    // Repositories for read operations
    private final ProposalRepository proposalRepository;
    private final ProposalDetailRepository proposalDetailRepository;
    private final ProposalDetailVehicleRepository proposalDetailVehicleRepository;
    private final ProposalDetailVehicleItemRepository proposalDetailVehicleItemRepository;
    private final ProposalCommissionRepository proposalCommissionRepository;
    private final ProposalDocumentRepository proposalDocumentRepository;
    private final ProposalFupRepository proposalFupRepository;
    
    // Services for write operations (to apply business rules)
    private final ProposalService proposalService;
    private final ProposalDetailService proposalDetailService;
    private final ProposalDetailVehicleService proposalDetailVehicleService;
    private final ProposalDetailVehicleItemService proposalDetailVehicleItemService;
    private final ProposalCommissionService proposalCommissionService;
    private final ProposalDocumentService proposalDocumentService;
    private final ProposalFupService proposalFupService;
    private final ProposalStatusService proposalStatusService;
    
    // Mappers
    private final ProposalMapper proposalMapper;
    private final ProposalDetailMapper proposalDetailMapper;
    private final ProposalDetailVehicleMapper proposalDetailVehicleMapper;
    private final ProposalDetailVehicleItemMapper proposalDetailVehicleItemMapper;
    private final ProposalCommissionMapper proposalCommissionMapper;
    private final ProposalDocumentMapper proposalDocumentMapper;
    private final ProposalFupMapper proposalFupMapper;
    
    public CompleteProposalServiceImplBackup(
            ProposalRepository proposalRepository,
            ProposalDetailRepository proposalDetailRepository,
            ProposalDetailVehicleRepository proposalDetailVehicleRepository,
            ProposalDetailVehicleItemRepository proposalDetailVehicleItemRepository,
            ProposalCommissionRepository proposalCommissionRepository,
            ProposalDocumentRepository proposalDocumentRepository,
            ProposalFupRepository proposalFupRepository,
            ProposalService proposalService,
            ProposalDetailService proposalDetailService,
            ProposalDetailVehicleService proposalDetailVehicleService,
            ProposalDetailVehicleItemService proposalDetailVehicleItemService,
            ProposalCommissionService proposalCommissionService,
            ProposalDocumentService proposalDocumentService,
            ProposalFupService proposalFupService,
            ProposalStatusService proposalStatusService,
            ProposalMapper proposalMapper,
            ProposalDetailMapper proposalDetailMapper,
            ProposalDetailVehicleMapper proposalDetailVehicleMapper,
            ProposalDetailVehicleItemMapper proposalDetailVehicleItemMapper,
            ProposalCommissionMapper proposalCommissionMapper,
            ProposalDocumentMapper proposalDocumentMapper,
            ProposalFupMapper proposalFupMapper) {
        this.proposalRepository = proposalRepository;
        this.proposalDetailRepository = proposalDetailRepository;
        this.proposalDetailVehicleRepository = proposalDetailVehicleRepository;
        this.proposalDetailVehicleItemRepository = proposalDetailVehicleItemRepository;
        this.proposalCommissionRepository = proposalCommissionRepository;
        this.proposalDocumentRepository = proposalDocumentRepository;
        this.proposalFupRepository = proposalFupRepository;
        this.proposalService = proposalService;
        this.proposalDetailService = proposalDetailService;
        this.proposalDetailVehicleService = proposalDetailVehicleService;
        this.proposalDetailVehicleItemService = proposalDetailVehicleItemService;
        this.proposalCommissionService = proposalCommissionService;
        this.proposalDocumentService = proposalDocumentService;
        this.proposalFupService = proposalFupService;
        this.proposalStatusService = proposalStatusService;
        this.proposalMapper = proposalMapper;
        this.proposalDetailMapper = proposalDetailMapper;
        this.proposalDetailVehicleMapper = proposalDetailVehicleMapper;
        this.proposalDetailVehicleItemMapper = proposalDetailVehicleItemMapper;
        this.proposalCommissionMapper = proposalCommissionMapper;
        this.proposalDocumentMapper = proposalDocumentMapper;
        this.proposalFupMapper = proposalFupMapper;
    }
    
    @Transactional(readOnly = true)
    public CompleteProposalResponseDTO findCompleteProposalById(Integer id) {
        
        
        // Fetch the proposal
        log.debug("Buscando proposta com ID: {}", id);
        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Proposta não encontrada com ID: {}", id);
                    return new EntityNotFoundException("Proposal", id);
                });
        
        // Fetch the proposal detail
        ProposalDetail proposalDetail = proposalDetailRepository.findByProposalId(id)
                .orElse(null);
        
        // Build the complete proposal DTO
        CompleteProposalResponseDTO completeProposalDTO = new CompleteProposalResponseDTO();
        
        // Set the proposal
        ProposalResponseDTO proposalDTO = proposalMapper.toDto(proposal);
        completeProposalDTO.setProposal(proposalDTO);
        
        // If proposal detail exists, set it and fetch related entities
        if (proposalDetail != null) {
            // Set the proposal detail
            ProposalDetailResponseDTO proposalDetailDTO = proposalDetailMapper.toDto(proposalDetail);
            completeProposalDTO.setProposalDetail(proposalDetailDTO);
            
            // Fetch and set proposal detail vehicles
            List<ProposalDetailVehicle> proposalDetailVehicles = 
                    proposalDetailVehicleRepository.findByProposalDetailId(proposalDetail.getId());
            
            List<ProposalDetailVehicleResponseDTO> proposalDetailVehicleDTOs = 
                    proposalDetailVehicles.stream()
                    .map(proposalDetailVehicleMapper::toDto)
                    .collect(Collectors.toList());
            
            completeProposalDTO.setProposalDetailVehicles(proposalDetailVehicleDTOs);
            
            // Fetch and set proposal detail vehicle items
            completeProposalDTO.setProposalDetailVehicleItems(
                    proposalDetailVehicles.stream()
                    .flatMap(pdv -> proposalDetailVehicleItemRepository.findByProposalDetailVehicleId(pdv.getId()).stream())
                    .map(proposalDetailVehicleItemMapper::toDto)
                    .collect(Collectors.toList())
            );
            
            // Fetch and set proposal commissions
            completeProposalDTO.setProposalCommissions(
                    proposalCommissionRepository.findByProposalDetailId(proposalDetail.getId()).stream()
                    .map(proposalCommissionMapper::toDto)
                    .collect(Collectors.toList())
            );
        }
        
        // Fetch and set proposal documents
        completeProposalDTO.setProposalDocuments(
                proposalDocumentRepository.findByProposalIdWithProposal(id).stream()
                .map(proposalDocumentMapper::toDto)
                .collect(Collectors.toList())
        );
        
        // Fetch and set proposal follow-ups
        completeProposalDTO.setProposalFups(
                proposalFupRepository.findByProposalIdOrderByDateDescWithProposal(id).stream()
                .map(proposalFupMapper::toDto)
                .collect(Collectors.toList())
        );
        
        // Get the current status of the proposal
        Integer currentStatus = proposal.getStatusClaId();
        
        // Get the possible next statuses from the ProposalStatusService
        List<Integer> nextStatusIds = proposalStatusService.getPossibleNextStatuses(currentStatus);
        
        // Map the status IDs to DTOs with descriptions using the utility class
        List<PossibleNextStatusesResponseDTO.StatusDTO> nextStatuses = 
                ProposalStatusUtil.mapStatusIdsToStatusDTOs(nextStatusIds);
        
        // Set the possible next statuses
        completeProposalDTO.setPossibleNextStatuses(nextStatuses);
        
        return completeProposalDTO;
    }
    
    @Transactional
    public CompleteProposalResponseDTO updateCompleteProposal(Integer id, CompleteProposalRequestDTO completeProposalRequest) {
        // Verify that the proposal exists
        if (!proposalService.existsById(id)) {
            throw new EntityNotFoundException("Proposal", id);
        }
        
        // Update the proposal using the service to apply business rules
        ProposalRequestDTO proposalRequestDTO = completeProposalRequest.getProposal();
        Proposal proposal = proposalMapper.toEntity(proposalRequestDTO, id);
        proposal = proposalService.update(id, proposal);
        
        // Update or create the proposal detail
        ProposalDetail proposalDetail = null;
        if (completeProposalRequest.getProposalDetail() != null) {
            ProposalDetailRequestDTO proposalDetailRequestDTO = completeProposalRequest.getProposalDetail();
            
            // Check if proposal detail exists
            boolean proposalDetailExists = proposalDetailService.existsByProposalId(id);
            
            if (proposalDetailExists) {
                // Get existing proposal detail
                ProposalDetail existingProposalDetail = proposalDetailService.findByProposalId(id);
                
                // Update existing proposal detail
                proposalDetail = proposalDetailMapper.toEntity(proposalDetailRequestDTO, existingProposalDetail.getId());
                proposalDetail.setProposal(proposal);
                proposalDetail = proposalDetailService.update(existingProposalDetail.getId(), proposalDetail);
            } else {
                // Create new proposal detail
                proposalDetail = proposalDetailMapper.toEntity(proposalDetailRequestDTO);
                proposalDetail.setProposal(proposal);
                proposalDetail = proposalDetailService.create(proposalDetail);
            }
        }
        
        // Update proposal detail vehicles if proposal detail exists
        List<ProposalDetailVehicle> updatedProposalDetailVehicles = new ArrayList<>();
        if (proposalDetail != null && completeProposalRequest.getProposalDetailVehicles() != null) {
            // Get existing vehicles
            List<ProposalDetailVehicle> existingVehicles = proposalDetailVehicleService.findByProposalDetailId(proposalDetail.getId());
            
            // Delete existing vehicles that are not in the request
            // Since we don't have a way to identify which vehicles to keep, we'll delete all and recreate
            for (ProposalDetailVehicle vehicle : existingVehicles) {
                proposalDetailVehicleService.delete(vehicle.getId());
            }
            
            // Create new vehicles
            for (ProposalDetailVehicleRequestDTO vehicleRequestDTO : completeProposalRequest.getProposalDetailVehicles()) {
                ProposalDetailVehicle vehicle = proposalDetailVehicleMapper.toEntity(vehicleRequestDTO);
                vehicle.setProposalDetail(proposalDetail);
                vehicle = proposalDetailVehicleService.create(vehicle);
                updatedProposalDetailVehicles.add(vehicle);
            }
        }
        
        // Update proposal detail vehicle items if there are vehicles
        if (!updatedProposalDetailVehicles.isEmpty() && completeProposalRequest.getProposalDetailVehicleItems() != null) {
            // Get all vehicle IDs
            List<Integer> vehicleIds = updatedProposalDetailVehicles.stream()
                    .map(ProposalDetailVehicle::getId)
                    .collect(Collectors.toList());
            
            // Delete existing items for these vehicles
            for (Integer vehicleId : vehicleIds) {
                proposalDetailVehicleItemService.deleteByProposalDetailVehicleId(vehicleId);
            }
            
            // Create new items
            for (ProposalDetailVehicleItemRequestDTO itemRequestDTO : completeProposalRequest.getProposalDetailVehicleItems()) {
                // Skip the mapper's attempt to find the vehicle and create the item manually
                ProposalDetailVehicleItem item = ProposalDetailVehicleItem.builder()
                    .amountDiscount(itemRequestDTO.getAmountDiscount())
                    .percentDiscount(itemRequestDTO.getPercentDiscount())
                    .finalPrice(itemRequestDTO.getFinalPrice())
                    .tablePriceTax(itemRequestDTO.getTablePriceTax())
                    .forFree(itemRequestDTO.getForFree())
                    .sellerId(itemRequestDTO.getSellerId())
                    .priceItemId(itemRequestDTO.getPriceItemId())
                    .priceItemModelId(itemRequestDTO.getPriceItemModelId())
                    .amendment(itemRequestDTO.getAmendment())
                    .immediateDelivery(itemRequestDTO.getImmediateDelivery())
                    .build();
                
                // Set the vehicle to the first one in the list (since we're recreating all vehicles)
                // In a more sophisticated implementation, you might want to map old vehicle IDs to new ones
                if (!updatedProposalDetailVehicles.isEmpty()) {
                    item.setProposalDetailVehicle(updatedProposalDetailVehicles.get(0));
                    proposalDetailVehicleItemService.create(item);
                }
            }
        }
        
        // Update proposal commissions if proposal detail exists
        if (proposalDetail != null && completeProposalRequest.getProposalCommissions() != null) {
            // Delete existing commissions
            proposalCommissionService.deleteByProposalDetailId(proposalDetail.getId());
            
            // Create new commissions
            for (ProposalCommissionRequestDTO commissionRequestDTO : completeProposalRequest.getProposalCommissions()) {
                ProposalCommission commission = proposalCommissionMapper.toEntity(commissionRequestDTO);
                commission.setProposalDetail(proposalDetail);
                proposalCommissionService.create(commission);
            }
        }
        
        // Update proposal documents
        if (completeProposalRequest.getProposalDocuments() != null) {
            // Get document IDs from the request
            List<Integer> requestDocumentIds = completeProposalRequest.getProposalDocuments().stream()
                    .map(ProposalDocumentRequestDTO::getDocumentId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            
            // Get existing documents
            List<ProposalDocument> existingDocuments = proposalDocumentService.findByProposalId(id);
            
            // Delete documents that are not in the request
            for (ProposalDocument doc : existingDocuments) {
                if (!requestDocumentIds.contains(doc.getDocument().getId())) {
                    proposalDocumentService.deleteByProposalIdAndDocumentId(id, doc.getDocument().getId());
                }
            }
            
            // Create new documents or update existing ones
            for (ProposalDocumentRequestDTO documentRequestDTO : completeProposalRequest.getProposalDocuments()) {
                if (documentRequestDTO.getDocumentId() != null) {
                    // Check if document already exists
                    if (proposalDocumentService.existsByProposalIdAndDocumentId(id, documentRequestDTO.getDocumentId())) {
                        // Delete existing document and create a new one (since there's no update method)
                        proposalDocumentService.deleteByProposalIdAndDocumentId(id, documentRequestDTO.getDocumentId());
                    }
                    
                    // Create new document
                    ProposalDocument proposalDocument = new ProposalDocument();
                    proposalDocument.setProposal(proposal);
                    
                    Document document = new Document();
                    document.setId(documentRequestDTO.getDocumentId());
                    proposalDocument.setDocument(document);
                    
                    proposalDocumentService.create(proposalDocument);
                }
            }
        }
        
        // Update proposal follow-ups
        if (completeProposalRequest.getProposalFups() != null) {
            // Delete existing follow-ups
            proposalFupService.deleteByProposalId(id);
            
            // Create new follow-ups
            for (ProposalFupRequestDTO fupRequestDTO : completeProposalRequest.getProposalFups()) {
                ProposalFup fup = proposalFupMapper.toEntity(fupRequestDTO);
                
                // Set the proposal
                Proposal fupProposal = new Proposal();
                fupProposal.setId(id);
                fup.setProposal(fupProposal);
                
                proposalFupService.create(fup);
            }
        }
        
        // Return the updated complete proposal
        return findCompleteProposalById(id);
    }
    
}
