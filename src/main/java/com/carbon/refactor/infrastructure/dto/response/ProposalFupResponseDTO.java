package com.carbon.refactor.infrastructure.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning proposal follow-up data to clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalFupResponseDTO {
    
    private Integer id;
    private Integer proposalId;
    private String proposalName;
    private LocalDateTime date;
    private Integer mediaClassifierId;
    private String mediaClassifierName;
    private String person;
    private String comment;
    private Integer followUpTypeClassifierId;
    private String followUpTypeClassifierName;
    private Integer userId;
    private String userName;
}
