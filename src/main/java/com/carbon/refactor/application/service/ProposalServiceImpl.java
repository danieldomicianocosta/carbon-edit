package com.carbon.refactor.application.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.carbon.refactor.application.dto.response.PaginatedProposalResponseDTO;
import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.entity.ProposalDetail;
import com.carbon.refactor.domain.entity.ProposalDetailVehicle;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.domain.exception.BusinessException;
import com.carbon.refactor.domain.exception.EntityNotFoundException;
import com.carbon.refactor.domain.exception.ForeignKeyConstraintViolationException;
import com.carbon.refactor.domain.repository.ProposalDetailRepository;
import com.carbon.refactor.domain.repository.ProposalDetailVehicleRepository;
import com.carbon.refactor.domain.repository.ProposalDocumentRepository;
import com.carbon.refactor.domain.repository.ProposalRepository;
import com.carbon.refactor.domain.service.ProposalService;
import com.carbon.refactor.domain.service.ProposalStatusService;
import com.carbon.refactor.infrastructure.security.AccessControlUtil;
import com.carbon.refactor.support.domain.service.ChannelService;

@Service
@Slf4j
public class ProposalServiceImpl implements ProposalService {
    
    private final ProposalRepository proposalRepository;
    private final ProposalDetailRepository proposalDetailRepository;
    private final ProposalDetailVehicleRepository proposalDetailVehicleRepository;
    private final ProposalDocumentRepository proposalDocumentRepository;
    private final ChannelService channelService;
    private final ProposalStatusService proposalStatusService;
    //Security
    private final AccessControlUtil accessControlUtil;
    
    public ProposalServiceImpl(
            ProposalRepository proposalRepository,
            ProposalDetailRepository proposalDetailRepository,
            ProposalDetailVehicleRepository proposalDetailVehicleRepository,
            ProposalDocumentRepository proposalDocumentRepository,
            ChannelService channelService,
            @org.springframework.beans.factory.annotation.Qualifier("proposalStatusServiceImpl") ProposalStatusService proposalStatusService,
            AccessControlUtil accessControlUtil) {
        this.proposalRepository = proposalRepository;
        this.proposalDetailRepository = proposalDetailRepository;
        this.proposalDetailVehicleRepository = proposalDetailVehicleRepository;
        this.proposalDocumentRepository = proposalDocumentRepository;
        this.channelService = channelService;
        this.proposalStatusService = proposalStatusService;
        this.accessControlUtil = accessControlUtil;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Proposal> findAll() {
        return proposalRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Proposal> findAll(Pageable pageable) {
        return proposalRepository.findAll(pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<PaginatedProposalResponseDTO> findAllPaginated(Pageable pageable) {
        Page<Proposal> proposalPage = proposalRepository.findAll(pageable);
        
        List<PaginatedProposalResponseDTO> paginatedProposals = proposalPage.getContent().stream()
                .map(this::mapToPaginatedDTO)
                .collect(Collectors.toList());
        
        return new PageImpl<>(paginatedProposals, pageable, proposalPage.getTotalElements());
    }
    
    private PaginatedProposalResponseDTO mapToPaginatedDTO(Proposal proposal) {
        PaginatedProposalResponseDTO dto = new PaginatedProposalResponseDTO();
        dto.setId(proposal.getId());
        
        // Set status
        ProposalStatus status = ProposalStatus.findById(proposal.getStatusClaId());
        dto.setStatus(status != null ? status.getDescription() : "");
        
        // Set customer
        dto.setCustomer(proposal.getCustomerName());
        
        // Set proposal number and date
        dto.setProposalNumber(proposal.getProposalNumber());
        dto.setProposalDate(proposal.getCreateDate());
        
        // Set validity date
        dto.setValidityDate(proposal.getValidityDate());
        
        try {
            // Get proposal detail for service order, partner, and business executive
            ProposalDetail detail = proposalDetailRepository.findByProposalId(proposal.getId())
                    .orElse(null);
            
            if (detail != null) {
                // Set service order
                dto.setServiceOrder(detail.getPurchaseOrderService());
                
                // Set partner
                // In a real implementation, you would fetch the partner name from a service
                dto.setPartner("Partner " + detail.getPartnerId());
                
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
    
    @Override
    @Transactional(readOnly = true)
    public Proposal findById(Integer id) {
        return proposalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proposal", id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Proposal findByProposalNumber(String proposalNumber) {
        return proposalRepository.findByProposalNumber(proposalNumber)
                .orElseThrow(() -> new EntityNotFoundException("Proposal", "proposal number", proposalNumber));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Proposal findByNumAndCod(Long num, String cod) {
        return proposalRepository.findByNumAndCod(num, cod)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Proposal with num %d and cod %s not found", num, cod)));
    }
    
    @Override
    @Transactional
    public Proposal create(Proposal proposal) {
        validateForeignKeys(proposal);
        
        // Check if immediateDeliveryClaId is 42 (Venda), then idProduction is mandatory
        if (proposal.getImmediateDeliveryClaId() != null && proposal.getImmediateDeliveryClaId() == 42 && proposal.getIdProduction() == null) {
            throw new BusinessException("Para propostas com entrega imediata do tipo Venda, é obrigatório informar o ID da proposta de produção.");
        }
        
        if (proposal.getProposalNumber() != null && proposalRepository.existsByProposalNumber(proposal.getProposalNumber())) {
            throw new IllegalArgumentException("Proposal with this proposal number already exists");
        }
        
        if (proposal.getNum() != null && proposal.getCod() != null && 
                proposalRepository.existsByNumAndCod(proposal.getNum(), proposal.getCod())) {
            throw new IllegalArgumentException("Proposal with this num and cod already exists");
        }
        
        return proposalRepository.save(proposal);
    }
    
    
    @Override
    @Transactional
    public Proposal update(Integer id, Proposal proposal) {
        Proposal existingProposal = findById(id);
        validateForeignKeys(proposal);

 /*        // Verificar se o usuário tem um papel específico
        log.debug("Verificando permissões do usuário para acessar a proposta com ID: {}", id);
        if (!accessControlUtil.hasRole("IT_ADMINISTRADOR_")) {
            // Lógica para administradores
            log.warn("Usuário sem permissão tentou acessar a proposta com ID: {}", id);
            throw new BusinessException("Usuário não tem permissão para acessar essa funcionalidade");
        } */
        
        // Check if the lastUpdateDate from the database is different from the one being passed
        // This indicates that the proposal has been edited by another user
        if (proposal.getLastUpdateDate() != null && existingProposal.getLastUpdateDate() != null && 
                !proposal.getLastUpdateDate().equals(existingProposal.getLastUpdateDate())) {
            throw new BusinessException("A proposta foi editada por outro usuário. Por favor, recarregue a proposta para continuar.");
        }
        
        // Check if immediateDeliveryClaId is 42 (Venda), then idProduction is mandatory
        if (proposal.getImmediateDeliveryClaId() != null && proposal.getImmediateDeliveryClaId() == 42 && proposal.getIdProduction() == null) {
            throw new BusinessException("Para propostas com entrega imediata do tipo Venda, é obrigatório informar o ID da proposta de produção.");
        }
        
        // Check if proposal number is being changed and if the new one already exists
        if (proposal.getProposalNumber() != null && 
                !proposal.getProposalNumber().equals(existingProposal.getProposalNumber()) && 
                proposalRepository.existsByProposalNumber(proposal.getProposalNumber())) {
            throw new IllegalArgumentException("Proposal with this proposal number already exists");
        }
        
        // Check if num and cod are being changed and if the new ones already exist
        if (proposal.getNum() != null && proposal.getCod() != null && 
                !(proposal.getNum().equals(existingProposal.getNum()) && 
                  proposal.getCod().equals(existingProposal.getCod())) && 
                proposalRepository.existsByNumAndCod(proposal.getNum(), proposal.getCod())) {
            throw new IllegalArgumentException("Proposal with this num and cod already exists");
        }
        
        // Check if status is being changed and validate the transition
        if (proposal.getStatusClaId() != null && 
                !proposal.getStatusClaId().equals(existingProposal.getStatusClaId())) {
            
            Integer currentStatus = existingProposal.getStatusClaId();
            Integer newStatus = proposal.getStatusClaId();
            
            // Validate the status transition using ProposalStatusService
            if (!proposalStatusService.isValidStatusTransition(currentStatus, newStatus)) {
                ProposalStatus current = ProposalStatus.findById(currentStatus);
                ProposalStatus next = ProposalStatus.findById(newStatus);
                
                String currentDesc = current != null ? current.getDescription() : "Unknown";
                String nextDesc = next != null ? next.getDescription() : "Unknown";
                
                throw new BusinessException(
                        String.format("Invalid status transition from '%s' to '%s'", currentDesc, nextDesc));
            }
            
            // Get the complete proposal data for rule execution
            // Since we don't have direct access to CompleteProposalService, we'll build a simplified version
            // of the CompleteProposalResponseDTO with the data we have
            com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO completeProposal = buildCompleteProposalDTO(id);
            
            // Execute rules for this transition using ProposalStatusService
            boolean rulesSucceeded = proposalStatusService.executeRules(completeProposal, currentStatus, newStatus);
            
            // If rules failed, throw an exception
            if (!rulesSucceeded) {
                throw new BusinessException(proposalStatusService.getErrorMessage(completeProposal, currentStatus, newStatus));
            }
            
            // Handle specific status transitions
            handleSpecificStatusTransition(proposal, currentStatus, newStatus);
        }
        
        proposal.setId(id);
        proposal.setLastUpdateDate(LocalDateTime.now());
        
        return proposalRepository.save(proposal);
    }
    
    /**
     * Build a simplified CompleteProposalResponseDTO with the data we have.
     * 
     * @param proposalId The proposal ID
     * @return A simplified CompleteProposalResponseDTO
     */
    private com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO buildCompleteProposalDTO(Integer proposalId) {
        com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO dto = new com.carbon.refactor.application.dto.response.CompleteProposalResponseDTO();
        
        // Get the proposal
        Proposal proposal = findById(proposalId);
        
        // Convert the proposal to a ProposalResponseDTO
        com.carbon.refactor.application.dto.ProposalResponseDTO proposalDTO = new com.carbon.refactor.application.dto.ProposalResponseDTO();
        proposalDTO.setId(proposal.getId());
        proposalDTO.setProposalNumber(proposal.getProposalNumber());
        proposalDTO.setStatusClaId(proposal.getStatusClaId());
        // Set other fields as needed
        
        dto.setProposal(proposalDTO);
        
        // Get the proposal detail
        ProposalDetail detail = proposalDetailRepository.findByProposalId(proposalId).orElse(null);
        if (detail != null) {
            // Convert the detail to a ProposalDetailResponseDTO
            com.carbon.refactor.application.dto.ProposalDetailResponseDTO detailDTO = new com.carbon.refactor.application.dto.ProposalDetailResponseDTO();
            detailDTO.setId(detail.getId());
            detailDTO.setProposalId(detail.getProposal().getId());
            detailDTO.setPartnerId(detail.getPartnerId());
            // Set other fields as needed
            
            dto.setProposalDetail(detailDTO);
            
            // Get the proposal detail vehicles
            List<ProposalDetailVehicle> vehicles = proposalDetailVehicleRepository.findByProposalDetailId(detail.getId());
            if (vehicles != null && !vehicles.isEmpty()) {
                // Convert the vehicles to ProposalDetailVehicleResponseDTOs
                List<com.carbon.refactor.application.dto.ProposalDetailVehicleResponseDTO> vehicleDTOs = vehicles.stream()
                        .map(vehicle -> {
                            com.carbon.refactor.application.dto.ProposalDetailVehicleResponseDTO vehicleDTO = new com.carbon.refactor.application.dto.ProposalDetailVehicleResponseDTO();
                            vehicleDTO.setId(vehicle.getId());
                            vehicleDTO.setProposalDetailId(vehicle.getProposalDetail().getId());
                            vehicleDTO.setModelId(vehicle.getModelId());
                            // Set other fields as needed
                            return vehicleDTO;
                        })
                        .collect(Collectors.toList());
                
                dto.setProposalDetailVehicles(vehicleDTOs);
            }
        }
        
        // Get the proposal documents
        List<com.carbon.refactor.domain.entity.ProposalDocument> documents = proposalDocumentRepository.findByProposalId(proposalId);
        if (documents != null && !documents.isEmpty()) {
            // Convert the documents to ProposalDocumentResponseDTOs
            List<com.carbon.refactor.infrastructure.dto.response.ProposalDocumentResponseDTO> documentDTOs = documents.stream()
                    .map(document -> {
                        com.carbon.refactor.infrastructure.dto.response.ProposalDocumentResponseDTO documentDTO = new com.carbon.refactor.infrastructure.dto.response.ProposalDocumentResponseDTO();
                        documentDTO.setProposalId(document.getProposal().getId());
                        documentDTO.setDocumentId(document.getDocument().getId());
                        // Set other fields as needed
                        return documentDTO;
                    })
                    .collect(Collectors.toList());
            
            dto.setProposalDocuments(documentDTOs);
        }
        
        return dto;
    }
    
    
    /**
     * Handle specific actions for certain status transitions.
     * 
     * @param proposal The proposal being updated
     * @param oldStatus The old status
     * @param newStatus The new status
     */
    private void handleSpecificStatusTransition(Proposal proposal, Integer oldStatus, Integer newStatus) {
        // When a proposal is sent
        if (newStatus.equals(ProposalStatus.PROPOSTA_ENVIADA.getId())) {
            proposal.setProposalSent(true);
            proposal.setProposalSentDate(LocalDateTime.now());
        }
        
        // When a proposal is finalized with sale
        if (newStatus.equals(ProposalStatus.FINALIZADO_COM_VENDA.getId())) {
            proposal.setFinishedDate(LocalDateTime.now());
        }
        
        // When a proposal is finalized without sale
        if (newStatus.equals(ProposalStatus.FINALIZADO_SEM_VENDA.getId())) {
            proposal.setFinishedDate(LocalDateTime.now());
        }
        
        // When a proposal is closed without return
        if (newStatus.equals(ProposalStatus.ENCERRADO_SEM_RETORNO.getId())) {
            proposal.setFinishedDate(LocalDateTime.now());
        }
        
        // When a proposal is canceled
        if (newStatus.equals(ProposalStatus.CANCELADO.getId())) {
            proposal.setFinishedDate(LocalDateTime.now());
        }
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        if (!proposalRepository.existsById(id)) {
            throw new EntityNotFoundException("Proposal", id);
        }
        
        proposalRepository.deleteById(id);
    }
    
    @Override
    @Transactional
    public void activate(Integer id) {
        Proposal proposal = findById(id);
        proposal.setProposalSent(true);
        proposal.setProposalSentDate(LocalDateTime.now());
        proposalRepository.save(proposal);
    }
    
    @Override
    @Transactional
    public void deactivate(Integer id) {
        Proposal proposal = findById(id);
        proposal.setProposalSent(false);
        proposalRepository.save(proposal);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return proposalRepository.existsById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByProposalNumber(String proposalNumber) {
        return proposalRepository.existsByProposalNumber(proposalNumber);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByNumAndCod(Long num, String cod) {
        return proposalRepository.existsByNumAndCod(num, cod);
    }
    
    private void validateForeignKeys(Proposal proposal) {
        // Validate foreign keys that don't have corresponding tables in the SQL file
        // These validations would typically check if the referenced entities exist
        
        // Example validation for statusClaId
        if (proposal.getStatusClaId() != null) {
            // In a real implementation, you would check if the classifier exists
            // For now, we'll just throw an exception if it's a specific invalid value
            if (proposal.getStatusClaId() <= 0) {
                throw new ForeignKeyConstraintViolationException("Classifier", "status_cla_id", proposal.getStatusClaId());
            }
        }
        
        // Example validation for riskClaId
        if (proposal.getRiskClaId() != null) {
            if (proposal.getRiskClaId() <= 0) {
                throw new ForeignKeyConstraintViolationException("Classifier", "risk_cla_id", proposal.getRiskClaId());
            }
        }
        
        // Example validation for immediateDeliveryClaId
        if (proposal.getImmediateDeliveryClaId() != null) {
            if (proposal.getImmediateDeliveryClaId() < 0) {
                throw new ForeignKeyConstraintViolationException("Classifier", "immediate_delivery_cla_id", proposal.getImmediateDeliveryClaId());
            }
        }
        
        // Example validation for ledId
        if (proposal.getLedId() != null) {
            if (proposal.getLedId() <= 0) {
                throw new ForeignKeyConstraintViolationException("Lead", "led_id", proposal.getLedId());
            }
        }
        
        // Example validation for usrIdCreate
        if (proposal.getUsrIdCreate() != null) {
            if (proposal.getUsrIdCreate() <= 0) {
                throw new ForeignKeyConstraintViolationException("User", "usr_id_create", proposal.getUsrIdCreate());
            }
        }
        
        // Example validation for usrIdLastUpdate
        if (proposal.getUsrIdLastUpdate() != null) {
            if (proposal.getUsrIdLastUpdate() <= 0) {
                throw new ForeignKeyConstraintViolationException("User", "usr_id_last_update", proposal.getUsrIdLastUpdate());
            }
        }
        
        // Example validation for schedulingClaId
        if (proposal.getSchedulingClaId() != null) {
            if (proposal.getSchedulingClaId() <= 0) {
                throw new ForeignKeyConstraintViolationException("Classifier", "scheduling_cla_id", proposal.getSchedulingClaId());
            }
        }
        
        // Example validation for signatureClaId
        if (proposal.getSignatureClaId() != null) {
            if (proposal.getSignatureClaId() <= 0) {
                throw new ForeignKeyConstraintViolationException("Classifier", "signature_cla_id", proposal.getSignatureClaId());
            }
        }
        
        // Example validation for finishedWithoutSaleClaId
        if (proposal.getFinishedWithoutSaleClaId() != null) {
            if (proposal.getFinishedWithoutSaleClaId() <= 0) {
                throw new ForeignKeyConstraintViolationException("Classifier", "finished_without_sale_cla_id", proposal.getFinishedWithoutSaleClaId());
            }
        }
        
        // Example validation for amendmentsStatusCla
        if (proposal.getAmendmentsStatusCla() != null) {
            if (proposal.getAmendmentsStatusCla() <= 0) {
                throw new ForeignKeyConstraintViolationException("Classifier", "amendments_status_cla", proposal.getAmendmentsStatusCla());
            }
        }
        
        // Example validation for idProduction
        if (proposal.getIdProduction() != null) {
            if (!proposalRepository.existsById(proposal.getIdProduction())) {
                throw new ForeignKeyConstraintViolationException("Proposal", "pps_id_production", proposal.getIdProduction());
            }
        }
    }
}
