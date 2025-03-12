package com.carbon.refactor.infrastructure.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for receiving proposal follow-up data from clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalFupRequestDTO {
    
    @NotNull(message = "Proposal ID is required")
    @Positive(message = "Proposal ID must be positive")
    private Integer proposalId;
    
    private LocalDateTime date;
    
    @NotNull(message = "Media classifier ID is required")
    @Positive(message = "Media classifier ID must be positive")
    private Integer mediaClassifierId;
    
    @NotBlank(message = "Person is required")
    @Size(max = 150, message = "Person must be less than 150 characters")
    private String person;
    
    @Size(max = 1000, message = "Comment must be less than 1000 characters")
    private String comment;
    
    private Integer followUpTypeClassifierId;
    
    private Integer userId;
}
