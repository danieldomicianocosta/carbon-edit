package com.carbon.refactor.infrastructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.carbon.refactor.application.dto.ProposalDetailRequestDTO;
import com.carbon.refactor.application.dto.ProposalDetailResponseDTO;
import com.carbon.refactor.application.mapper.ProposalDetailMapper;
import com.carbon.refactor.domain.entity.ProposalDetail;
import com.carbon.refactor.domain.service.ProposalDetailService;

@RestController
@RequestMapping("/api/proposal-details")
@RequiredArgsConstructor
public class ProposalDetailController {
    
    private final ProposalDetailService proposalDetailService;
    private final ProposalDetailMapper proposalDetailMapper;
    
    @GetMapping
    public ResponseEntity<List<ProposalDetailResponseDTO>> findAll() {
        List<ProposalDetail> proposalDetails = proposalDetailService.findAll();
        List<ProposalDetailResponseDTO> response = proposalDetails.stream()
                .map(proposalDetailMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProposalDetailResponseDTO> findById(@PathVariable Integer id) {
        ProposalDetail proposalDetail = proposalDetailService.findById(id);
        return ResponseEntity.ok(proposalDetailMapper.toDto(proposalDetail));
    }
    
    @GetMapping("/proposal/{proposalId}")
    public ResponseEntity<ProposalDetailResponseDTO> findByProposalId(@PathVariable Integer proposalId) {
        ProposalDetail proposalDetail = proposalDetailService.findByProposalId(proposalId);
        return ResponseEntity.ok(proposalDetailMapper.toDto(proposalDetail));
    }
    
    @PostMapping
    public ResponseEntity<ProposalDetailResponseDTO> create(@Valid @RequestBody ProposalDetailRequestDTO requestDTO) {
        ProposalDetail proposalDetail = proposalDetailMapper.toEntity(requestDTO);
        ProposalDetail savedProposalDetail = proposalDetailService.create(proposalDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(proposalDetailMapper.toDto(savedProposalDetail));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProposalDetailResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ProposalDetailRequestDTO requestDTO) {
        ProposalDetail proposalDetail = proposalDetailMapper.toEntity(requestDTO, id);
        ProposalDetail updatedProposalDetail = proposalDetailService.update(id, proposalDetail);
        return ResponseEntity.ok(proposalDetailMapper.toDto(updatedProposalDetail));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        proposalDetailService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
