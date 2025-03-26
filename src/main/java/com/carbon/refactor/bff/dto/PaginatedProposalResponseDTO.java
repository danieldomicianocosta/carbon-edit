package com.carbon.refactor.bff.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning paginated proposal search results.
 * This DTO includes both the proposal summaries and pagination metadata.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedProposalResponseDTO {
    
    // List of proposal summaries for the current page
    private List<ProposalSummaryDTO> proposals;
    
    // Pagination metadata
    private PaginationMetadata pagination;
    
    /**
     * Nested class for pagination metadata.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaginationMetadata {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean first;
        private boolean last;
    }
}
