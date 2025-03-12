package com.carbon.refactor.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carbon.refactor.domain.entity.ProposalDocument;
import com.carbon.refactor.domain.service.ProposalDocumentService;
import com.carbon.refactor.infrastructure.dto.request.ProposalDocumentRequestDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalDocumentResponseDTO;
import com.carbon.refactor.infrastructure.mapper.ProposalDocumentMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST controller for managing proposal documents.
 */
@RestController
@RequestMapping("/api/proposal-documents")
@RequiredArgsConstructor
public class ProposalDocumentController {
    
    private final ProposalDocumentService proposalDocumentService;
    private final ProposalDocumentMapper proposalDocumentMapper;
    
    /**
     * GET /api/proposal-documents : Get all proposal documents.
     * 
     * @return the ResponseEntity with status 200 (OK) and the list of proposal documents in body
     */
    @GetMapping
    public ResponseEntity<List<ProposalDocumentResponseDTO>> getAllProposalDocuments() {
        List<ProposalDocument> proposalDocuments = proposalDocumentService.findAll();
        List<ProposalDocumentResponseDTO> dtos = proposalDocumentMapper.toDtoList(proposalDocuments);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * GET /api/proposal-documents/proposal/{proposalId} : Get all proposal documents for a proposal.
     * 
     * @param proposalId the id of the proposal to retrieve documents for
     * @return the ResponseEntity with status 200 (OK) and the list of proposal documents in body
     */
    @GetMapping("/proposal/{proposalId}")
    public ResponseEntity<List<ProposalDocumentResponseDTO>> getProposalDocumentsByProposalId(
            @PathVariable Integer proposalId) {
        List<ProposalDocument> proposalDocuments = proposalDocumentService.findByProposalId(proposalId);
        List<ProposalDocumentResponseDTO> dtos = proposalDocumentMapper.toDtoList(proposalDocuments);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * GET /api/proposal-documents/document/{documentId} : Get all proposal documents for a document.
     * 
     * @param documentId the id of the document to retrieve proposal documents for
     * @return the ResponseEntity with status 200 (OK) and the list of proposal documents in body
     */
    @GetMapping("/document/{documentId}")
    public ResponseEntity<List<ProposalDocumentResponseDTO>> getProposalDocumentsByDocumentId(
            @PathVariable Integer documentId) {
        List<ProposalDocument> proposalDocuments = proposalDocumentService.findByDocumentId(documentId);
        List<ProposalDocumentResponseDTO> dtos = proposalDocumentMapper.toDtoList(proposalDocuments);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * POST /api/proposal-documents : Create a new proposal document.
     * 
     * @param requestDTO the proposal document to create
     * @return the ResponseEntity with status 201 (Created) and with body the new proposal document, or with status 400 (Bad Request) if the proposal document has already an ID
     */
    @PostMapping
    public ResponseEntity<ProposalDocumentResponseDTO> createProposalDocument(
            @Valid @RequestBody ProposalDocumentRequestDTO requestDTO) {
        // Check if the proposal document already exists
        if (proposalDocumentService.existsByProposalIdAndDocumentId(requestDTO.getProposalId(), requestDTO.getDocumentId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        ProposalDocument proposalDocument = proposalDocumentMapper.toEntity(requestDTO);
        ProposalDocument result = proposalDocumentService.create(proposalDocument);
        ProposalDocumentResponseDTO responseDTO = proposalDocumentMapper.toDto(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    
    /**
     * DELETE /api/proposal-documents/proposal/{proposalId}/document/{documentId} : Delete the proposal document with the given proposal ID and document ID.
     * 
     * @param proposalId the proposal ID of the proposal document to delete
     * @param documentId the document ID of the proposal document to delete
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/proposal/{proposalId}/document/{documentId}")
    public ResponseEntity<Void> deleteProposalDocument(
            @PathVariable Integer proposalId,
            @PathVariable Integer documentId) {
        proposalDocumentService.deleteByProposalIdAndDocumentId(proposalId, documentId);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * DELETE /api/proposal-documents/proposal/{proposalId} : Delete all proposal documents for a proposal.
     * 
     * @param proposalId the id of the proposal to delete documents for
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/proposal/{proposalId}")
    public ResponseEntity<Void> deleteProposalDocumentsByProposalId(@PathVariable Integer proposalId) {
        proposalDocumentService.deleteByProposalId(proposalId);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * DELETE /api/proposal-documents/document/{documentId} : Delete all proposal documents for a document.
     * 
     * @param documentId the id of the document to delete proposal documents for
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/document/{documentId}")
    public ResponseEntity<Void> deleteProposalDocumentsByDocumentId(@PathVariable Integer documentId) {
        proposalDocumentService.deleteByDocumentId(documentId);
        return ResponseEntity.noContent().build();
    }
}
