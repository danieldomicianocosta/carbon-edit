package com.carbon.refactor.analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalCountByStatusDTO {
    
    private Integer statusId;
    private String statusName;
    private String statusDescription;
    private Long count;
}
