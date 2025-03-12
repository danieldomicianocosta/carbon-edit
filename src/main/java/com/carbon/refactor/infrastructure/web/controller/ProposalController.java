package com.carbon.refactor.infrastructure.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.application.dto.ProposalRequestDTO;
import com.carbon.refactor.application.dto.ProposalResponseDTO;
import com.carbon.refactor.application.dto.response.PageResponseDTO;
import com.carbon.refactor.application.dto.response.PaginatedProposalResponseDTO;
import com.carbon.refactor.application.mapper.ProposalMapper;
import com.carbon.refactor.domain.entity.Proposal;
import com.carbon.refactor.domain.service.ProposalService;

@RestController
@RequestMapping("/api/proposals")
@RequiredArgsConstructor
public class ProposalController {
    
    private final ProposalService proposalService;
    private final ProposalMapper proposalMapper;
    
    @GetMapping
    public ResponseEntity<List<ProposalResponseDTO>> findAll() {
        List<Proposal> proposals = proposalService.findAll();
        List<ProposalResponseDTO> response = proposals.stream()
                .map(proposalMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
    /**
     * GET /api/proposals/paginated : Get all proposals with pagination.
     * 
     * @param pageable The pagination information
     * @return The ResponseEntity with status 200 (OK) and the paginated proposals in the body
     */
    @GetMapping("/paginated")
    public ResponseEntity<PageResponseDTO<PaginatedProposalResponseDTO>> findAllPaginated(Pageable pageable) {
        Page<PaginatedProposalResponseDTO> page = proposalService.findAllPaginated(pageable);
        
        PageResponseDTO<PaginatedProposalResponseDTO> response = PageResponseDTO.<PaginatedProposalResponseDTO>builder()
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
    
    @GetMapping("/{id}")
    public ResponseEntity<ProposalResponseDTO> findById(@PathVariable Integer id) {
        Proposal proposal = proposalService.findById(id);
        return ResponseEntity.ok(proposalMapper.toDto(proposal));
    }
    
    @GetMapping("/number/{proposalNumber}")
    public ResponseEntity<ProposalResponseDTO> findByProposalNumber(@PathVariable String proposalNumber) {
        Proposal proposal = proposalService.findByProposalNumber(proposalNumber);
        return ResponseEntity.ok(proposalMapper.toDto(proposal));
    }
    
    @GetMapping("/num/{num}/cod/{cod}")
    public ResponseEntity<ProposalResponseDTO> findByNumAndCod(@PathVariable Long num, @PathVariable String cod) {
        Proposal proposal = proposalService.findByNumAndCod(num, cod);
        return ResponseEntity.ok(proposalMapper.toDto(proposal));
    }
    
    @PostMapping
    public ResponseEntity<ProposalResponseDTO> create(@Valid @RequestBody ProposalRequestDTO requestDTO) {
        Proposal proposal = proposalMapper.toEntity(requestDTO);
        Proposal savedProposal = proposalService.create(proposal);
        return ResponseEntity.status(HttpStatus.CREATED).body(proposalMapper.toDto(savedProposal));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProposalResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ProposalRequestDTO requestDTO) {
        Proposal proposal = proposalMapper.toEntity(requestDTO, id);
        Proposal updatedProposal = proposalService.update(id, proposal);
        return ResponseEntity.ok(proposalMapper.toDto(updatedProposal));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        proposalService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable Integer id) {
        proposalService.activate(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Integer id) {
        proposalService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
