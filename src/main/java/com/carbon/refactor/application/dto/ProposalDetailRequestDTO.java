package com.carbon.refactor.application.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDetailRequestDTO {
    
    @NotNull(message = "Proposal ID is required")
    private Integer proposalId;
    
    @NotNull(message = "Seller ID is required")
    private Integer sellerId;
    
    private Integer internSaleSellerId;
    
    @NotNull(message = "Channel ID is required")
    private Integer channelId;
    
    private Integer partnerId;
    
    private Integer userId;
    
    @Size(max = 50, message = "Purchase order service must be less than 50 characters")
    private String purchaseOrderService;
    
    @Size(max = 50, message = "Purchase order product must be less than 50 characters")
    private String purchaseOrderProduct;
    
    @Size(max = 50, message = "Purchase order documentation must be less than 50 characters")
    private String purchaseOrderDocumentation;
    
    private Double internalComission;
    
    private Integer internSaleAdditive;
    
    private Integer sellerAdditive;
    
    private LocalDateTime saleDateAdditive;
}
