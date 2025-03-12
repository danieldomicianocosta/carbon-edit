package com.carbon.refactor.application.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning paginated proposal data with essential information.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedProposalResponseDTO {
    
    private Integer id;
    private String status;
    private String customer;
    private String proposalNumber;
    private LocalDateTime proposalDate;
    private String serviceOrder;
    private LocalDateTime serviceOrderDate;
    private String partner;
    private String businessExecutive;
    private String modelBrand;
    private LocalDateTime validityDate;
}
