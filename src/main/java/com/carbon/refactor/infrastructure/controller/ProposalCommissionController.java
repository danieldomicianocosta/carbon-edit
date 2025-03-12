package com.carbon.refactor.infrastructure.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.domain.entity.ProposalCommission;
import com.carbon.refactor.domain.service.ProposalCommissionService;
import com.carbon.refactor.infrastructure.dto.request.ProposalCommissionRequestDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalCommissionResponseDTO;
import com.carbon.refactor.infrastructure.mapper.ProposalCommissionMapper;

/**
 * REST controller for managing proposal commissions.
 */
@RestController
@RequestMapping("/api/proposal-commissions")
@RequiredArgsConstructor
public class ProposalCommissionController {
    
    private final ProposalCommissionService proposalCommissionService;
    private final ProposalCommissionMapper proposalCommissionMapper;
    
    /**
     * GET /api/proposal-commissions : Get all proposal commissions.
     * 
     * @return the ResponseEntity with status 200 (OK) and the list of proposal commissions in body
     */
    @GetMapping
    public ResponseEntity<List<ProposalCommissionResponseDTO>> getAllProposalCommissions() {
        List<ProposalCommission> proposalCommissions = proposalCommissionService.findAll();
        List<ProposalCommissionResponseDTO> dtos = proposalCommissionMapper.toDtoList(proposalCommissions);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * GET /api/proposal-commissions/{id} : Get the "id" proposal commission.
     * 
     * @param id the id of the proposal commission to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the proposal commission, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProposalCommissionResponseDTO> getProposalCommission(@PathVariable Integer id) {
        ProposalCommission proposalCommission = proposalCommissionService.findById(id);
        ProposalCommissionResponseDTO dto = proposalCommissionMapper.toDto(proposalCommission);
        return ResponseEntity.ok(dto);
    }
    
    /**
     * GET /api/proposal-commissions/proposal-detail/{proposalDetailId} : Get all proposal commissions for a proposal detail.
     * 
     * @param proposalDetailId the id of the proposal detail to retrieve commissions for
     * @return the ResponseEntity with status 200 (OK) and the list of proposal commissions in body
     */
    @GetMapping("/proposal-detail/{proposalDetailId}")
    public ResponseEntity<List<ProposalCommissionResponseDTO>> getProposalCommissionsByProposalDetailId(
            @PathVariable Integer proposalDetailId) {
        List<ProposalCommission> proposalCommissions = proposalCommissionService.findByProposalDetailId(proposalDetailId);
        List<ProposalCommissionResponseDTO> dtos = proposalCommissionMapper.toDtoList(proposalCommissions);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * GET /api/proposal-commissions/person/{personId} : Get all proposal commissions for a person.
     * 
     * @param personId the id of the person to retrieve commissions for
     * @return the ResponseEntity with status 200 (OK) and the list of proposal commissions in body
     */
    @GetMapping("/person/{personId}")
    public ResponseEntity<List<ProposalCommissionResponseDTO>> getProposalCommissionsByPersonId(
            @PathVariable Integer personId) {
        List<ProposalCommission> proposalCommissions = proposalCommissionService.findByPersonId(personId);
        List<ProposalCommissionResponseDTO> dtos = proposalCommissionMapper.toDtoList(proposalCommissions);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * POST /api/proposal-commissions : Create a new proposal commission.
     * 
     * @param requestDTO the proposal commission to create
     * @return the ResponseEntity with status 201 (Created) and with body the new proposal commission, or with status 400 (Bad Request) if the proposal commission has already an ID
     */
    @PostMapping
    public ResponseEntity<ProposalCommissionResponseDTO> createProposalCommission(
            @Valid @RequestBody ProposalCommissionRequestDTO requestDTO) {
        ProposalCommission proposalCommission = proposalCommissionMapper.toEntity(requestDTO);
        ProposalCommission result = proposalCommissionService.create(proposalCommission);
        ProposalCommissionResponseDTO responseDTO = proposalCommissionMapper.toDto(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    
    /**
     * PUT /api/proposal-commissions/{id} : Updates an existing proposal commission.
     * 
     * @param id the id of the proposal commission to update
     * @param requestDTO the proposal commission to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated proposal commission,
     * or with status 400 (Bad Request) if the proposal commission is not valid,
     * or with status 500 (Internal Server Error) if the proposal commission couldn't be updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProposalCommissionResponseDTO> updateProposalCommission(
            @PathVariable Integer id,
            @Valid @RequestBody ProposalCommissionRequestDTO requestDTO) {
        ProposalCommission proposalCommission = proposalCommissionMapper.toEntity(requestDTO);
        ProposalCommission result = proposalCommissionService.update(id, proposalCommission);
        ProposalCommissionResponseDTO responseDTO = proposalCommissionMapper.toDto(result);
        return ResponseEntity.ok(responseDTO);
    }
    
    /**
     * DELETE /api/proposal-commissions/{id} : Delete the "id" proposal commission.
     * 
     * @param id the id of the proposal commission to delete
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProposalCommission(@PathVariable Integer id) {
        proposalCommissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * DELETE /api/proposal-commissions/proposal-detail/{proposalDetailId} : Delete all proposal commissions for a proposal detail.
     * 
     * @param proposalDetailId the id of the proposal detail to delete commissions for
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/proposal-detail/{proposalDetailId}")
    public ResponseEntity<Void> deleteProposalCommissionsByProposalDetailId(@PathVariable Integer proposalDetailId) {
        proposalCommissionService.deleteByProposalDetailId(proposalDetailId);
        return ResponseEntity.noContent().build();
    }
}
