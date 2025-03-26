package com.carbon.refactor.analysis.dto;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalsByStatusRequestDTO {
    
    @NotNull(message = "Status ID is required")
    private Integer statusId;
}
