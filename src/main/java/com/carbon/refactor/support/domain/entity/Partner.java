package com.carbon.refactor.support.domain.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the partner table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Partner {
    private Integer id;
    private Integer entityPerId;
    private Integer partnerGroupId;
    private Integer channelId;
    private Integer situationCla;
    private Integer additionalTerm;
    private boolean isAssistance;
    private LocalDate dueCr;
    
    // Relationships
    private Channel channel;
}
