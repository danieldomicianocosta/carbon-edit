package com.carbon.refactor.analysis.controller;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.carbon.refactor.analysis.dto.ProposalCountByStatusDTO;
import com.carbon.refactor.analysis.dto.ProposalsByStatusResponseDTO;
import com.carbon.refactor.analysis.service.ProposalAnalysisService;
import com.carbon.refactor.application.dto.response.PageResponseDTO;
import com.carbon.refactor.domain.enums.ProposalStatus;
import com.carbon.refactor.domain.exception.BusinessException;

@RestController
@RequestMapping("/api/analysis/proposals")
@RequiredArgsConstructor
public class ProposalAnalysisController {
    
    private final ProposalAnalysisService proposalAnalysisService;
    
    /**
     * GET /api/analysis/proposals/status/{statusId} : Get all proposals with the given status.
     * 
     * @param statusId The status ID to filter by
     * @return The ResponseEntity with status 200 (OK) and the list of proposals in the body
     */
    @GetMapping("/status/{statusId}")
    public ResponseEntity<List<ProposalsByStatusResponseDTO>> findProposalsByStatus(
            @PathVariable @Valid Integer statusId) {
        
        // Validate that the status ID exists
        if (ProposalStatus.findById(statusId) == null) {
            throw new IllegalArgumentException("Invalid status ID: " + statusId);
        }
        
        List<ProposalsByStatusResponseDTO> proposals = proposalAnalysisService.findProposalsByStatus(statusId);
        return ResponseEntity.ok(proposals);
    }
    
    /**
     * GET /api/analysis/proposals/status/{statusId}/paginated : Get all proposals with the given status with pagination.
     * 
     * @param statusId The status ID to filter by
     * @param pageable The pagination information
     * @return The ResponseEntity with status 200 (OK) and the paginated proposals in the body
     */
    @GetMapping("/status/{statusId}/paginated")
    public ResponseEntity<PageResponseDTO<ProposalsByStatusResponseDTO>> findProposalsByStatusPaginated(
            @PathVariable @Valid Integer statusId, Pageable pageable) {
        
        // Validate that the status ID exists
        if (ProposalStatus.findById(statusId) == null) {
            throw new IllegalArgumentException("Invalid status ID: " + statusId);
        }
        
        Page<ProposalsByStatusResponseDTO> page = proposalAnalysisService.findProposalsByStatus(statusId, pageable);
        
        PageResponseDTO<ProposalsByStatusResponseDTO> response = PageResponseDTO.<ProposalsByStatusResponseDTO>builder()
                .content(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * GET /api/analysis/proposals/status-name : Get all proposals with the given status name.
     * 
     * @param statusName The status name to filter by (e.g., "FINALIZADO_COM_VENDA")
     * @return The ResponseEntity with status 200 (OK) and the list of proposals in the body
     */
    @GetMapping("/status-name")
    public ResponseEntity<List<ProposalsByStatusResponseDTO>> findProposalsByStatusName(
            @RequestParam @Valid String statusName) {
        
        // Find the status by name
        ProposalStatus status = null;
        try {
            status = ProposalStatus.valueOf(statusName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Invalid status name: " + statusName + ". Valid values are: " + 
                    Arrays.toString(ProposalStatus.values()));
        }
        
        List<ProposalsByStatusResponseDTO> proposals = proposalAnalysisService.findProposalsByStatus(status.getId());
        return ResponseEntity.ok(proposals);
    }
    
    /**
     * GET /api/analysis/proposals/status-name/paginated : Get all proposals with the given status name with pagination.
     * 
     * @param statusName The status name to filter by (e.g., "FINALIZADO_COM_VENDA")
     * @param pageable The pagination information
     * @return The ResponseEntity with status 200 (OK) and the paginated proposals in the body
     */
    @GetMapping("/status-name/paginated")
    public ResponseEntity<PageResponseDTO<ProposalsByStatusResponseDTO>> findProposalsByStatusNamePaginated(
            @RequestParam @Valid String statusName, Pageable pageable) {
        
        // Find the status by name
        ProposalStatus status = null;
        try {
            status = ProposalStatus.valueOf(statusName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Invalid status name: " + statusName + ". Valid values are: " + 
                    Arrays.toString(ProposalStatus.values()));
        }
        
        Page<ProposalsByStatusResponseDTO> page = proposalAnalysisService.findProposalsByStatus(status.getId(), pageable);
        
        PageResponseDTO<ProposalsByStatusResponseDTO> response = PageResponseDTO.<ProposalsByStatusResponseDTO>builder()
                .content(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * GET /api/analysis/proposals/count-by-status : Get the count of proposals grouped by status.
     * 
     * @return The ResponseEntity with status 200 (OK) and the list of counts in the body
     */
    @GetMapping("/count-by-status")
    public ResponseEntity<List<ProposalCountByStatusDTO>> countProposalsByStatus() {
        List<ProposalCountByStatusDTO> counts = proposalAnalysisService.countProposalsByStatus();
        return ResponseEntity.ok(counts);
    }
}
