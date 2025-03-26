package com.carbon.refactor.analysis.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalsByStatusResponseDTO {
    
    private Integer id;
    private String proposalNumber;
    private LocalDateTime createDate;
    private LocalDateTime validityDate;
    private LocalDateTime finishedDate;
    private String statusDescription;
    private String customerName;
    private String partnerName;
    private String businessExecutive;
    private String modelBrand;
    private String serviceOrder;
}
