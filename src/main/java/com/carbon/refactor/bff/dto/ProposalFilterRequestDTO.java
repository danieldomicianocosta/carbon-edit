package com.carbon.refactor.bff.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for filtering proposals in search operations.
 * This DTO provides various filter criteria for searching proposals.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalFilterRequestDTO {
    
    // Filter by proposal status
    private List<Integer> statusIds;
    
    // Filter by proposal number
    private String proposalNumber;
    
    // Filter by update date range
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateDateStart;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateDateEnd;
    
    // Filter by channel
    private Integer channelId;
    
    // Filter by partner
    private Integer partnerId;
    
    // Filter by vehicle
    private Integer vehicleId;
    
    // Filter by model
    private Integer modelId;
    
    // Pagination parameters
    private Integer page;
    private Integer size;
    private String sortBy;
    private String sortDirection;
}
