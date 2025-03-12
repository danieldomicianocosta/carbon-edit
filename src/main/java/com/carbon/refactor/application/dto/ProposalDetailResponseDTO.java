package com.carbon.refactor.application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDetailResponseDTO {
    
    private Integer id;
    private Integer proposalId;
    private String proposalNumber;
    private Integer sellerId;
    private Integer internSaleSellerId;
    private Integer channelId;
    private Integer partnerId;
    private Integer userId;
    private String purchaseOrderService;
    private String purchaseOrderProduct;
    private String purchaseOrderDocumentation;
    private Double internalComission;
    private Integer internSaleAdditive;
    private Integer sellerAdditive;
    private LocalDateTime saleDateAdditive;
}
