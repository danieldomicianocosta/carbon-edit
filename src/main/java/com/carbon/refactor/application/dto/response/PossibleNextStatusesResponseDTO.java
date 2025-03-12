package com.carbon.refactor.application.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for the response of possible next statuses for a given current status.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PossibleNextStatusesResponseDTO {
    
    /**
     * The current status ID.
     */
    private Integer currentStatusId;
    
    /**
     * The current status description.
     */
    private String currentStatusDescription;
    
    /**
     * List of possible next statuses.
     */
    private List<StatusDTO> possibleNextStatuses;
    
    /**
     * Inner DTO for status information.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatusDTO {
        
        /**
         * The status ID.
         */
        private Integer id;
        
        /**
         * The status description.
         */
        private String description;
    }
}
