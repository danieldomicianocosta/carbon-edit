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
import com.carbon.refactor.domain.entity.ProposalDetailVehicleItem;
import com.carbon.refactor.domain.service.ProposalDetailVehicleItemService;
import com.carbon.refactor.infrastructure.dto.request.ProposalDetailVehicleItemRequestDTO;
import com.carbon.refactor.infrastructure.dto.response.ProposalDetailVehicleItemResponseDTO;
import com.carbon.refactor.infrastructure.mapper.ProposalDetailVehicleItemMapper;

/**
 * REST controller for managing proposal detail vehicle items.
 */
@RestController
@RequestMapping("/api/proposal-detail-vehicle-items")
@RequiredArgsConstructor
public class ProposalDetailVehicleItemController {
    
    private final ProposalDetailVehicleItemService proposalDetailVehicleItemService;
    private final ProposalDetailVehicleItemMapper proposalDetailVehicleItemMapper;
    
    /**
     * GET /api/proposal-detail-vehicle-items : Get all proposal detail vehicle items.
     * 
     * @return the ResponseEntity with status 200 (OK) and the list of proposal detail vehicle items in body
     */
    @GetMapping
    public ResponseEntity<List<ProposalDetailVehicleItemResponseDTO>> getAllProposalDetailVehicleItems() {
        List<ProposalDetailVehicleItem> proposalDetailVehicleItems = proposalDetailVehicleItemService.findAll();
        List<ProposalDetailVehicleItemResponseDTO> dtos = proposalDetailVehicleItemMapper.toDtoList(proposalDetailVehicleItems);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * GET /api/proposal-detail-vehicle-items/{id} : Get the "id" proposal detail vehicle item.
     * 
     * @param id the id of the proposal detail vehicle item to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the proposal detail vehicle item, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProposalDetailVehicleItemResponseDTO> getProposalDetailVehicleItem(@PathVariable Integer id) {
        ProposalDetailVehicleItem proposalDetailVehicleItem = proposalDetailVehicleItemService.findById(id);
        ProposalDetailVehicleItemResponseDTO dto = proposalDetailVehicleItemMapper.toDto(proposalDetailVehicleItem);
        return ResponseEntity.ok(dto);
    }
    
    /**
     * GET /api/proposal-detail-vehicle-items/proposal-detail-vehicle/{proposalDetailVehicleId} : Get all proposal detail vehicle items for a proposal detail vehicle.
     * 
     * @param proposalDetailVehicleId the id of the proposal detail vehicle to retrieve items for
     * @return the ResponseEntity with status 200 (OK) and the list of proposal detail vehicle items in body
     */
    @GetMapping("/proposal-detail-vehicle/{proposalDetailVehicleId}")
    public ResponseEntity<List<ProposalDetailVehicleItemResponseDTO>> getProposalDetailVehicleItemsByProposalDetailVehicleId(
            @PathVariable Integer proposalDetailVehicleId) {
        List<ProposalDetailVehicleItem> proposalDetailVehicleItems = proposalDetailVehicleItemService.findByProposalDetailVehicleId(proposalDetailVehicleId);
        List<ProposalDetailVehicleItemResponseDTO> dtos = proposalDetailVehicleItemMapper.toDtoList(proposalDetailVehicleItems);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * GET /api/proposal-detail-vehicle-items/seller/{sellerId} : Get all proposal detail vehicle items for a seller.
     * 
     * @param sellerId the id of the seller to retrieve items for
     * @return the ResponseEntity with status 200 (OK) and the list of proposal detail vehicle items in body
     */
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<ProposalDetailVehicleItemResponseDTO>> getProposalDetailVehicleItemsBySellerId(
            @PathVariable Integer sellerId) {
        List<ProposalDetailVehicleItem> proposalDetailVehicleItems = proposalDetailVehicleItemService.findBySellerId(sellerId);
        List<ProposalDetailVehicleItemResponseDTO> dtos = proposalDetailVehicleItemMapper.toDtoList(proposalDetailVehicleItems);
        return ResponseEntity.ok(dtos);
    }
    
    /**
     * POST /api/proposal-detail-vehicle-items : Create a new proposal detail vehicle item.
     * 
     * @param requestDTO the proposal detail vehicle item to create
     * @return the ResponseEntity with status 201 (Created) and with body the new proposal detail vehicle item, or with status 400 (Bad Request) if the proposal detail vehicle item has already an ID
     */
    @PostMapping
    public ResponseEntity<ProposalDetailVehicleItemResponseDTO> createProposalDetailVehicleItem(
            @Valid @RequestBody ProposalDetailVehicleItemRequestDTO requestDTO) {
        ProposalDetailVehicleItem proposalDetailVehicleItem = proposalDetailVehicleItemMapper.toEntity(requestDTO);
        ProposalDetailVehicleItem result = proposalDetailVehicleItemService.create(proposalDetailVehicleItem);
        ProposalDetailVehicleItemResponseDTO responseDTO = proposalDetailVehicleItemMapper.toDto(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    
    /**
     * PUT /api/proposal-detail-vehicle-items/{id} : Updates an existing proposal detail vehicle item.
     * 
     * @param id the id of the proposal detail vehicle item to update
     * @param requestDTO the proposal detail vehicle item to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated proposal detail vehicle item,
     * or with status 400 (Bad Request) if the proposal detail vehicle item is not valid,
     * or with status 500 (Internal Server Error) if the proposal detail vehicle item couldn't be updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProposalDetailVehicleItemResponseDTO> updateProposalDetailVehicleItem(
            @PathVariable Integer id,
            @Valid @RequestBody ProposalDetailVehicleItemRequestDTO requestDTO) {
        ProposalDetailVehicleItem proposalDetailVehicleItem = proposalDetailVehicleItemMapper.toEntity(requestDTO);
        ProposalDetailVehicleItem result = proposalDetailVehicleItemService.update(id, proposalDetailVehicleItem);
        ProposalDetailVehicleItemResponseDTO responseDTO = proposalDetailVehicleItemMapper.toDto(result);
        return ResponseEntity.ok(responseDTO);
    }
    
    /**
     * DELETE /api/proposal-detail-vehicle-items/{id} : Delete the "id" proposal detail vehicle item.
     * 
     * @param id the id of the proposal detail vehicle item to delete
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProposalDetailVehicleItem(@PathVariable Integer id) {
        proposalDetailVehicleItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * DELETE /api/proposal-detail-vehicle-items/proposal-detail-vehicle/{proposalDetailVehicleId} : Delete all proposal detail vehicle items for a proposal detail vehicle.
     * 
     * @param proposalDetailVehicleId the id of the proposal detail vehicle to delete items for
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/proposal-detail-vehicle/{proposalDetailVehicleId}")
    public ResponseEntity<Void> deleteProposalDetailVehicleItemsByProposalDetailVehicleId(@PathVariable Integer proposalDetailVehicleId) {
        proposalDetailVehicleItemService.deleteByProposalDetailVehicleId(proposalDetailVehicleId);
        return ResponseEntity.noContent().build();
    }
}
