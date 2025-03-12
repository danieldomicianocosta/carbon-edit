package com.carbon.refactor.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbon.refactor.application.dto.ProposalDetailRequestDTO;
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
 * Implementation of the CompleteProposalService interface.
 * This service aggregates data from multiple services to provide a complete view of a proposal
 * and allows updating all proposal-related entities in a single transaction.
 */
@Service
@Slf4j
public class CompleteProposalServiceImpl implements CompleteProposalService {
    
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
    
    public CompleteProposalServiceImpl(
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
            @org.springframework.beans.factory.annotation.Qualifier("proposalStatusServiceImpl") ProposalStatusService proposalStatusService,
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
    
    @Override
    @Transactional(readOnly = true)
    public CompleteProposalResponseDTO findCompleteProposalById(Integer id) {
        log.debug("Iniciando busca da proposta completa com ID: {}", id);
        
        // Fetch the proposal
        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Proposta não encontrada com ID: {}", id);
                    return new EntityNotFoundException("Proposal", id);
                });
        
        // Build the complete proposal DTO
        CompleteProposalResponseDTO completeProposalDTO = new CompleteProposalResponseDTO();
        
        // Set the proposal
        ProposalResponseDTO proposalDTO = proposalMapper.toDto(proposal);
        completeProposalDTO.setProposal(proposalDTO);
        
        // Fetch and set proposal detail and related entities
        proposalDetailRepository.findByProposalId(id)
                .ifPresent(proposalDetail -> {
                    log.debug("Carregando detalhes da proposta com ID: {}", proposalDetail.getId());
                    
                    // Set the proposal detail
                    completeProposalDTO.setProposalDetail(proposalDetailMapper.toDto(proposalDetail));
                    
                    // Load related entities
                    loadProposalDetailRelatedEntities(proposalDetail, completeProposalDTO);
                });
        
        // Load proposal documents
        loadProposalDocuments(id, completeProposalDTO);
        
        // Load proposal follow-ups
        loadProposalFollowUps(id, completeProposalDTO);
        
        // Load possible next statuses
        loadPossibleNextStatuses(proposal, completeProposalDTO);
        
        log.debug("Busca da proposta completa com ID: {} finalizada com sucesso", id);
        return completeProposalDTO;
    }
    
    /**
     * Loads all entities related to a proposal detail into the complete proposal DTO.
     * 
     * @param proposalDetail The proposal detail entity
     * @param completeProposalDTO The DTO to populate
     */
    private void loadProposalDetailRelatedEntities(ProposalDetail proposalDetail, CompleteProposalResponseDTO completeProposalDTO) {
        Integer detailId = proposalDetail.getId();
        
        // Load vehicles
        List<ProposalDetailVehicle> vehicles = proposalDetailVehicleRepository.findByProposalDetailId(detailId);
        
        // Map vehicles to DTOs
        List<ProposalDetailVehicleResponseDTO> vehicleDTOs = vehicles.stream()
                .map(proposalDetailVehicleMapper::toDto)
                .collect(Collectors.toList());
        completeProposalDTO.setProposalDetailVehicles(vehicleDTOs);
        
        // Load vehicle items
        if (!vehicles.isEmpty()) {
            loadVehicleItems(vehicles, completeProposalDTO);
        }
        
        // Load commissions
        loadCommissions(detailId, completeProposalDTO);
    }
    
    /**
     * Loads vehicle items for a list of vehicles into the complete proposal DTO.
     * 
     * @param vehicles The list of vehicles
     * @param completeProposalDTO The DTO to populate
     */
    private void loadVehicleItems(List<ProposalDetailVehicle> vehicles, CompleteProposalResponseDTO completeProposalDTO) {
        // Extract vehicle IDs
        List<Integer> vehicleIds = vehicles.stream()
                .map(ProposalDetailVehicle::getId)
                .collect(Collectors.toList());
        
        // Fetch all items for these vehicles in a single query (to avoid N+1 problem)
        // Note: This assumes the repository has a method to fetch items for multiple vehicles
        // If not, we can still optimize by using a batch approach
        List<ProposalDetailVehicleItem> allItems = new ArrayList<>();
        
        // This could be optimized with a single query if the repository supports it
        for (Integer vehicleId : vehicleIds) {
            allItems.addAll(proposalDetailVehicleItemRepository.findByProposalDetailVehicleId(vehicleId));
        }
        
        // Map items to DTOs
        completeProposalDTO.setProposalDetailVehicleItems(
                allItems.stream()
                        .map(proposalDetailVehicleItemMapper::toDto)
                        .collect(Collectors.toList())
        );
    }
    
    /**
     * Loads commissions for a proposal detail into the complete proposal DTO.
     * 
     * @param detailId The proposal detail ID
     * @param completeProposalDTO The DTO to populate
     */
    private void loadCommissions(Integer detailId, CompleteProposalResponseDTO completeProposalDTO) {
        completeProposalDTO.setProposalCommissions(
                proposalCommissionRepository.findByProposalDetailId(detailId).stream()
                        .map(proposalCommissionMapper::toDto)
                        .collect(Collectors.toList())
        );
    }
    
    /**
     * Loads documents for a proposal into the complete proposal DTO.
     * 
     * @param proposalId The proposal ID
     * @param completeProposalDTO The DTO to populate
     */
    private void loadProposalDocuments(Integer proposalId, CompleteProposalResponseDTO completeProposalDTO) {
        completeProposalDTO.setProposalDocuments(
                proposalDocumentRepository.findByProposalIdWithProposal(proposalId).stream()
                        .map(proposalDocumentMapper::toDto)
                        .collect(Collectors.toList())
        );
    }
    
    /**
     * Loads follow-ups for a proposal into the complete proposal DTO.
     * 
     * @param proposalId The proposal ID
     * @param completeProposalDTO The DTO to populate
     */
    private void loadProposalFollowUps(Integer proposalId, CompleteProposalResponseDTO completeProposalDTO) {
        completeProposalDTO.setProposalFups(
                proposalFupRepository.findByProposalIdOrderByDateDescWithProposal(proposalId).stream()
                        .map(proposalFupMapper::toDto)
                        .collect(Collectors.toList())
        );
    }
    
    /**
     * Loads possible next statuses for a proposal into the complete proposal DTO.
     * 
     * @param proposal The proposal entity
     * @param completeProposalDTO The DTO to populate
     */
    private void loadPossibleNextStatuses(Proposal proposal, CompleteProposalResponseDTO completeProposalDTO) {
        // Get the current status of the proposal
        Integer currentStatus = proposal.getStatusClaId();
        
        // Get the possible next statuses from the ProposalStatusService
        List<Integer> nextStatusIds = proposalStatusService.getPossibleNextStatuses(currentStatus);
        
        // Map the status IDs to DTOs with descriptions using the utility class
        List<PossibleNextStatusesResponseDTO.StatusDTO> nextStatuses = 
                ProposalStatusUtil.mapStatusIdsToStatusDTOs(nextStatusIds);
        
        // Set the possible next statuses
        completeProposalDTO.setPossibleNextStatuses(nextStatuses);
    }
    
    @Override
    @Transactional
    public CompleteProposalResponseDTO updateCompleteProposal(Integer id, CompleteProposalRequestDTO completeProposalRequest) {
        log.info("Iniciando atualização da proposta completa com ID: {}", id);
        
        // Verify that the proposal exists
        if (!proposalService.existsById(id)) {
            log.error("Proposta não encontrada com ID: {}", id);
            throw new EntityNotFoundException("Proposal", id);
        }
        
        // Update the main proposal
        Proposal proposal = updateMainProposal(id, completeProposalRequest.getProposal());
        
        // Update or create the proposal detail if provided
        ProposalDetail proposalDetail = null;
        if (completeProposalRequest.getProposalDetail() != null) {
            proposalDetail = updateProposalDetail(id, proposal, completeProposalRequest.getProposalDetail());
            
            // Update related entities if proposal detail exists
            if (proposalDetail != null) {
                // Update vehicles and their items
                List<ProposalDetailVehicle> updatedVehicles = 
                        updateProposalVehicles(proposalDetail, completeProposalRequest.getProposalDetailVehicles());
                
                updateVehicleItems(updatedVehicles, completeProposalRequest.getProposalDetailVehicleItems());
                
                // Update commissions
                updateCommissions(proposalDetail, completeProposalRequest.getProposalCommissions());
            }
        }
        
        // Update documents
        updateDocuments(id, proposal, completeProposalRequest.getProposalDocuments());
        
        // Update follow-ups
        updateFollowUps(id, proposal, completeProposalRequest.getProposalFups());
        
        log.info("Proposta com ID: {} atualizada com sucesso", id);
        
        // Return the updated complete proposal
        return findCompleteProposalById(id);
    }
    
    /**
     * Updates the main proposal entity.
     * 
     * @param id The proposal ID
     * @param proposalRequestDTO The proposal request DTO
     * @return The updated proposal entity
     */
    private Proposal updateMainProposal(Integer id, ProposalRequestDTO proposalRequestDTO) {
        log.debug("Atualizando proposta principal com ID: {}", id);
        Proposal proposal = proposalMapper.toEntity(proposalRequestDTO, id);
        return proposalService.update(id, proposal);
    }
    
    /**
     * Updates or creates a proposal detail entity.
     * 
     * @param proposalId The proposal ID
     * @param proposal The proposal entity
     * @param proposalDetailRequestDTO The proposal detail request DTO
     * @return The updated or created proposal detail entity
     */
    private ProposalDetail updateProposalDetail(Integer proposalId, Proposal proposal, 
            ProposalDetailRequestDTO proposalDetailRequestDTO) {
        
        log.debug("Atualizando detalhes da proposta com ID: {}", proposalId);
        
        // Check if proposal detail exists
        Optional<ProposalDetail> existingProposalDetailOpt = proposalDetailService.existsByProposalId(proposalId) 
                ? Optional.of(proposalDetailService.findByProposalId(proposalId))
                : Optional.empty();
        
        // Update or create proposal detail
        return existingProposalDetailOpt.map(existingDetail -> {
            // Update existing proposal detail
            ProposalDetail updatedDetail = proposalDetailMapper.toEntity(proposalDetailRequestDTO, existingDetail.getId());
            updatedDetail.setProposal(proposal);
            return proposalDetailService.update(existingDetail.getId(), updatedDetail);
        }).orElseGet(() -> {
            // Create new proposal detail
            ProposalDetail newDetail = proposalDetailMapper.toEntity(proposalDetailRequestDTO);
            newDetail.setProposal(proposal);
            return proposalDetailService.create(newDetail);
        });
    }
    
    /**
     * Updates proposal vehicles for a proposal detail.
     * 
     * @param proposalDetail The proposal detail entity
     * @param vehicleRequestDTOs The list of vehicle request DTOs
     * @return The list of updated or created vehicles
     */
    private List<ProposalDetailVehicle> updateProposalVehicles(ProposalDetail proposalDetail, 
            List<ProposalDetailVehicleRequestDTO> vehicleRequestDTOs) {
        
        if (vehicleRequestDTOs == null || vehicleRequestDTOs.isEmpty()) {
            log.debug("Nenhum veículo para atualizar para o detalhe da proposta com ID: {}", proposalDetail.getId());
            return new ArrayList<>();
        }
        
        log.debug("Atualizando {} veículos para o detalhe da proposta com ID: {}", 
                vehicleRequestDTOs.size(), proposalDetail.getId());
        
        // Get existing vehicles
        List<ProposalDetailVehicle> existingVehicles = 
                proposalDetailVehicleService.findByProposalDetailId(proposalDetail.getId());
        
        // Since we don't have a way to identify which vehicles to keep, we'll delete all and recreate
        for (ProposalDetailVehicle vehicle : existingVehicles) {
            proposalDetailVehicleService.delete(vehicle.getId());
        }
        
        // Create new vehicles
        List<ProposalDetailVehicle> updatedVehicles = new ArrayList<>();
        for (ProposalDetailVehicleRequestDTO vehicleDTO : vehicleRequestDTOs) {
            ProposalDetailVehicle vehicle = proposalDetailVehicleMapper.toEntity(vehicleDTO);
            vehicle.setProposalDetail(proposalDetail);
            vehicle = proposalDetailVehicleService.create(vehicle);
            updatedVehicles.add(vehicle);
        }
        
        return updatedVehicles;
    }
    
    /**
     * Updates vehicle items for a list of vehicles.
     * 
     * @param vehicles The list of vehicles
     * @param itemRequestDTOs The list of item request DTOs
     */
    private void updateVehicleItems(List<ProposalDetailVehicle> vehicles, 
            List<ProposalDetailVehicleItemRequestDTO> itemRequestDTOs) {
        
        if (vehicles.isEmpty() || itemRequestDTOs == null || itemRequestDTOs.isEmpty()) {
            log.debug("Nenhum item de veículo para atualizar");
            return;
        }
        
        log.debug("Atualizando {} itens para {} veículos", itemRequestDTOs.size(), vehicles.size());
        
        // Get all vehicle IDs
        List<Integer> vehicleIds = vehicles.stream()
                .map(ProposalDetailVehicle::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        
        // Delete existing items for these vehicles
        for (Integer vehicleId : vehicleIds) {
            proposalDetailVehicleItemService.deleteByProposalDetailVehicleId(vehicleId);
        }
        
        // Create new items
        for (ProposalDetailVehicleItemRequestDTO itemDTO : itemRequestDTOs) {
            // Find the vehicle for this item
            Integer vehicleId = itemDTO.getProposalDetailVehicleId();
            Optional<ProposalDetailVehicle> vehicleOpt = vehicles.stream()
                    .filter(v -> v.getId().equals(vehicleId))
                    .findFirst();
            
            if (vehicleOpt.isPresent()) {
                // Create the item
                ProposalDetailVehicleItem item = ProposalDetailVehicleItem.builder()
                        .amountDiscount(itemDTO.getAmountDiscount())
                        .percentDiscount(itemDTO.getPercentDiscount())
                        .finalPrice(itemDTO.getFinalPrice())
                        .tablePriceTax(itemDTO.getTablePriceTax())
                        .forFree(itemDTO.getForFree())
                        .sellerId(itemDTO.getSellerId())
                        .priceItemId(itemDTO.getPriceItemId())
                        .priceItemModelId(itemDTO.getPriceItemModelId())
                        .amendment(itemDTO.getAmendment())
                        .immediateDelivery(itemDTO.getImmediateDelivery())
                        .proposalDetailVehicle(vehicleOpt.get())
                        .build();
                
                proposalDetailVehicleItemService.create(item);
            } else {
                log.warn("Veículo com ID {} não encontrado para o item", vehicleId);
            }
        }
    }
    
    /**
     * Updates commissions for a proposal detail.
     * 
     * @param proposalDetail The proposal detail entity
     * @param commissionRequestDTOs The list of commission request DTOs
     */
    private void updateCommissions(ProposalDetail proposalDetail, 
            List<ProposalCommissionRequestDTO> commissionRequestDTOs) {
        
        if (commissionRequestDTOs == null || commissionRequestDTOs.isEmpty()) {
            log.debug("Nenhuma comissão para atualizar para o detalhe da proposta com ID: {}", proposalDetail.getId());
            return;
        }
        
        log.debug("Atualizando {} comissões para o detalhe da proposta com ID: {}", 
                commissionRequestDTOs.size(), proposalDetail.getId());
        
        // Delete existing commissions
        proposalCommissionService.deleteByProposalDetailId(proposalDetail.getId());
        
        // Create new commissions
        for (ProposalCommissionRequestDTO commissionDTO : commissionRequestDTOs) {
            ProposalCommission commission = proposalCommissionMapper.toEntity(commissionDTO);
            commission.setProposalDetail(proposalDetail);
            proposalCommissionService.create(commission);
        }
    }
    
    /**
     * Updates documents for a proposal.
     * 
     * @param proposalId The proposal ID
     * @param proposal The proposal entity
     * @param documentRequestDTOs The list of document request DTOs
     */
    private void updateDocuments(Integer proposalId, Proposal proposal, 
            List<ProposalDocumentRequestDTO> documentRequestDTOs) {
        
        if (documentRequestDTOs == null || documentRequestDTOs.isEmpty()) {
            log.debug("Nenhum documento para atualizar para a proposta com ID: {}", proposalId);
            return;
        }
        
        log.debug("Atualizando {} documentos para a proposta com ID: {}", 
                documentRequestDTOs.size(), proposalId);
        
        // Get document IDs from the request
        List<Integer> requestDocumentIds = documentRequestDTOs.stream()
                .map(ProposalDocumentRequestDTO::getDocumentId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        
        // Get existing documents
        List<ProposalDocument> existingDocuments = proposalDocumentService.findByProposalId(proposalId);
        
        // Delete documents that are not in the request
        for (ProposalDocument doc : existingDocuments) {
            if (!requestDocumentIds.contains(doc.getDocument().getId())) {
                proposalDocumentService.deleteByProposalIdAndDocumentId(proposalId, doc.getDocument().getId());
            }
        }
        
        // Create new documents or update existing ones
        for (ProposalDocumentRequestDTO documentDTO : documentRequestDTOs) {
            if (documentDTO.getDocumentId() != null) {
                // Check if document already exists
                if (proposalDocumentService.existsByProposalIdAndDocumentId(proposalId, documentDTO.getDocumentId())) {
                    // Delete existing document and create a new one (since there's no update method)
                    proposalDocumentService.deleteByProposalIdAndDocumentId(proposalId, documentDTO.getDocumentId());
                }
                
                // Create new document
                ProposalDocument proposalDocument = new ProposalDocument();
                proposalDocument.setProposal(proposal);
                
                Document document = new Document();
                document.setId(documentDTO.getDocumentId());
                proposalDocument.setDocument(document);
                
                proposalDocumentService.create(proposalDocument);
            }
        }
    }
    
    /**
     * Updates follow-ups for a proposal.
     * 
     * @param proposalId The proposal ID
     * @param proposal The proposal entity
     * @param fupRequestDTOs The list of follow-up request DTOs
     */
    private void updateFollowUps(Integer proposalId, Proposal proposal, 
            List<ProposalFupRequestDTO> fupRequestDTOs) {
        
        if (fupRequestDTOs == null || fupRequestDTOs.isEmpty()) {
            log.debug("Nenhum acompanhamento para atualizar para a proposta com ID: {}", proposalId);
            return;
        }
        
        log.debug("Atualizando {} acompanhamentos para a proposta com ID: {}", 
                fupRequestDTOs.size(), proposalId);
        
        // Delete existing follow-ups
        proposalFupService.deleteByProposalId(proposalId);
        
        // Create new follow-ups
        for (ProposalFupRequestDTO fupDTO : fupRequestDTOs) {
            ProposalFup fup = proposalFupMapper.toEntity(fupDTO);
            
            // Set the proposal
            fup.setProposal(proposal);
            
            proposalFupService.create(fup);
        }
    }
}
