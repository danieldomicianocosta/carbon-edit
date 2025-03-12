package com.carbon.refactor.infrastructure.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning proposal document data to clients.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDocumentResponseDTO {
    
    private Integer proposalId;
    private Integer documentId;
    private String proposalName;
    private String documentName;
}
