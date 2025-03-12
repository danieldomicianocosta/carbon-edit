package com.carbon.refactor.infrastructure.controller;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.domain.entity.ProposalFup;
import com.carbon.refactor.domain.service.ProposalFupService;
import com.carbon.refactor.infrastructure.dto.request.ProposalFupRequestDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalFupResponseDTO;
import com.carbon.refactor.infrastructure.mapper.ProposalFupMapper;

/**
 * REST controller for managing proposal follow-ups.
 */
@RestController
@RequestMapping("/api/proposal-fups")
@RequiredArgsConstructor
public class ProposalFupController {
    
    private final ProposalFupService proposalFupService;
    private final ProposalFupMapper proposalFupMapper;
    
    /**
     * GET /api/proposal-fups : Get all proposal follow-ups.
     * 
     * @return the ResponseEntity with status 200 (OK) and the list of proposal follow-ups in body
     */
    @GetMapping
    public ResponseEntity<List<ProposalFupResponseDTO>> getAllProposalFups() {
        List<ProposalFup> proposalFups = proposalFupService.findAll();
        List<ProposalFupResponseDTO> dtos = proposalFupMapper.toDtoList(proposalFups);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * GET /api/proposal-fups/{id} : Get the "id" proposal follow-up.
     * 
     * @param id the id of the proposal follow-up to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the proposal follow-up, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProposalFupResponseDTO> getProposalFup(@PathVariable Integer id) {
        ProposalFup proposalFup = proposalFupService.findById(id);
        ProposalFupResponseDTO dto = proposalFupMapper.toDto(proposalFup);
        return ResponseEntity.ok(dto);
    }
    
    /**
     * GET /api/proposal-fups/proposal/{proposalId} : Get all proposal follow-ups for a proposal.
     * 
     * @param proposalId the id of the proposal to retrieve follow-ups for
     * @return the ResponseEntity with status 200 (OK) and the list of proposal follow-ups in body
     */
    @GetMapping("/proposal/{proposalId}")
    public ResponseEntity<List<ProposalFupResponseDTO>> getProposalFupsByProposalId(@PathVariable Integer proposalId) {
        List<ProposalFup> proposalFups = proposalFupService.findByProposalIdOrderByDateDesc(proposalId);
        List<ProposalFupResponseDTO> dtos = proposalFupMapper.toDtoList(proposalFups);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * GET /api/proposal-fups/user/{userId} : Get all proposal follow-ups for a user.
     * 
     * @param userId the id of the user to retrieve follow-ups for
     * @return the ResponseEntity with status 200 (OK) and the list of proposal follow-ups in body
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProposalFupResponseDTO>> getProposalFupsByUserId(@PathVariable Integer userId) {
        List<ProposalFup> proposalFups = proposalFupService.findByUserId(userId);
        List<ProposalFupResponseDTO> dtos = proposalFupMapper.toDtoList(proposalFups);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * GET /api/proposal-fups/media/{mediaClassifierId} : Get all proposal follow-ups for a media classifier.
     * 
     * @param mediaClassifierId the id of the media classifier to retrieve follow-ups for
     * @return the ResponseEntity with status 200 (OK) and the list of proposal follow-ups in body
     */
    @GetMapping("/media/{mediaClassifierId}")
    public ResponseEntity<List<ProposalFupResponseDTO>> getProposalFupsByMediaClassifierId(@PathVariable Integer mediaClassifierId) {
        List<ProposalFup> proposalFups = proposalFupService.findByMediaClassifierId(mediaClassifierId);
        List<ProposalFupResponseDTO> dtos = proposalFupMapper.toDtoList(proposalFups);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * GET /api/proposal-fups/follow-up-type/{followUpTypeClassifierId} : Get all proposal follow-ups for a follow-up type classifier.
     * 
     * @param followUpTypeClassifierId the id of the follow-up type classifier to retrieve follow-ups for
     * @return the ResponseEntity with status 200 (OK) and the list of proposal follow-ups in body
     */
    @GetMapping("/follow-up-type/{followUpTypeClassifierId}")
    public ResponseEntity<List<ProposalFupResponseDTO>> getProposalFupsByFollowUpTypeClassifierId(@PathVariable Integer followUpTypeClassifierId) {
        List<ProposalFup> proposalFups = proposalFupService.findByFollowUpTypeClassifierId(followUpTypeClassifierId);
        List<ProposalFupResponseDTO> dtos = proposalFupMapper.toDtoList(proposalFups);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * GET /api/proposal-fups/date-range : Get all proposal follow-ups between start date and end date.
     * 
     * @param startDate the start date to search for
     * @param endDate the end date to search for
     * @return the ResponseEntity with status 200 (OK) and the list of proposal follow-ups in body
     */
    @GetMapping("/date-range")
    public ResponseEntity<List<ProposalFupResponseDTO>> getProposalFupsByDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<ProposalFup> proposalFups = proposalFupService.findByDateBetween(startDate, endDate);
        List<ProposalFupResponseDTO> dtos = proposalFupMapper.toDtoList(proposalFups);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * POST /api/proposal-fups : Create a new proposal follow-up.
     * 
     * @param requestDTO the proposal follow-up to create
     * @return the ResponseEntity with status 201 (Created) and with body the new proposal follow-up, or with status 400 (Bad Request) if the proposal follow-up has already an ID
     */
    @PostMapping
    public ResponseEntity<ProposalFupResponseDTO> createProposalFup(@Valid @RequestBody ProposalFupRequestDTO requestDTO) {
        ProposalFup proposalFup = proposalFupMapper.toEntity(requestDTO);
        ProposalFup result = proposalFupService.create(proposalFup);
        ProposalFupResponseDTO responseDTO = proposalFupMapper.toDto(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    
    /**
     * PUT /api/proposal-fups/{id} : Updates an existing proposal follow-up.
     * 
     * @param id the id of the proposal follow-up to update
     * @param requestDTO the proposal follow-up to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated proposal follow-up,
     * or with status 400 (Bad Request) if the proposal follow-up is not valid,
     * or with status 500 (Internal Server Error) if the proposal follow-up couldn't be updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProposalFupResponseDTO> updateProposalFup(
            @PathVariable Integer id,
            @Valid @RequestBody ProposalFupRequestDTO requestDTO) {
        ProposalFup existingProposalFup = proposalFupService.findById(id);
        ProposalFup proposalFup = proposalFupMapper.updateEntityFromDto(existingProposalFup, requestDTO);
        ProposalFup result = proposalFupService.update(id, proposalFup);
        ProposalFupResponseDTO responseDTO = proposalFupMapper.toDto(result);
        return ResponseEntity.ok(responseDTO);
    }
    
    /**
     * DELETE /api/proposal-fups/{id} : Delete the "id" proposal follow-up.
     * 
     * @param id the id of the proposal follow-up to delete
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProposalFup(@PathVariable Integer id) {
        proposalFupService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * DELETE /api/proposal-fups/proposal/{proposalId} : Delete all proposal follow-ups for a proposal.
     * 
     * @param proposalId the id of the proposal to delete follow-ups for
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/proposal/{proposalId}")
    public ResponseEntity<Void> deleteProposalFupsByProposalId(@PathVariable Integer proposalId) {
        proposalFupService.deleteByProposalId(proposalId);
        return ResponseEntity.noContent().build();
    }
}
