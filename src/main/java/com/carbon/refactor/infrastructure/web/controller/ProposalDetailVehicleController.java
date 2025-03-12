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
import com.carbon.refactor.application.dto.ProposalDetailVehicleRequestDTO;
import com.carbon.refactor.application.dto.ProposalDetailVehicleResponseDTO;
import com.carbon.refactor.application.mapper.ProposalDetailVehicleMapper;
import com.carbon.refactor.domain.entity.ProposalDetailVehicle;
import com.carbon.refactor.domain.service.ProposalDetailVehicleService;

@RestController
@RequestMapping("/api/proposal-detail-vehicles")
@RequiredArgsConstructor
public class ProposalDetailVehicleController {
    
    private final ProposalDetailVehicleService proposalDetailVehicleService;
    private final ProposalDetailVehicleMapper proposalDetailVehicleMapper;
    
    @GetMapping
    public ResponseEntity<List<ProposalDetailVehicleResponseDTO>> findAll() {
        List<ProposalDetailVehicle> proposalDetailVehicles = proposalDetailVehicleService.findAll();
        List<ProposalDetailVehicleResponseDTO> response = proposalDetailVehicles.stream()
                .map(proposalDetailVehicleMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProposalDetailVehicleResponseDTO> findById(@PathVariable Integer id) {
        ProposalDetailVehicle proposalDetailVehicle = proposalDetailVehicleService.findById(id);
        return ResponseEntity.ok(proposalDetailVehicleMapper.toDto(proposalDetailVehicle));
    }
    
    @GetMapping("/proposal-detail/{proposalDetailId}")
    public ResponseEntity<List<ProposalDetailVehicleResponseDTO>> findByProposalDetailId(@PathVariable Integer proposalDetailId) {
        List<ProposalDetailVehicle> proposalDetailVehicles = proposalDetailVehicleService.findByProposalDetailId(proposalDetailId);
        List<ProposalDetailVehicleResponseDTO> response = proposalDetailVehicles.stream()
                .map(proposalDetailVehicleMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<ProposalDetailVehicleResponseDTO> create(@Valid @RequestBody ProposalDetailVehicleRequestDTO requestDTO) {
        ProposalDetailVehicle proposalDetailVehicle = proposalDetailVehicleMapper.toEntity(requestDTO);
        ProposalDetailVehicle savedProposalDetailVehicle = proposalDetailVehicleService.create(proposalDetailVehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(proposalDetailVehicleMapper.toDto(savedProposalDetailVehicle));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProposalDetailVehicleResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ProposalDetailVehicleRequestDTO requestDTO) {
        ProposalDetailVehicle proposalDetailVehicle = proposalDetailVehicleMapper.toEntity(requestDTO, id);
        ProposalDetailVehicle updatedProposalDetailVehicle = proposalDetailVehicleService.update(id, proposalDetailVehicle);
        return ResponseEntity.ok(proposalDetailVehicleMapper.toDto(updatedProposalDetailVehicle));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        proposalDetailVehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/proposal-detail/{proposalDetailId}")
    public ResponseEntity<Void> deleteByProposalDetailId(@PathVariable Integer proposalDetailId) {
        proposalDetailVehicleService.deleteByProposalDetailId(proposalDetailId);
        return ResponseEntity.noContent().build();
    }
}
