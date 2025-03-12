package com.carbon.refactor.support.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the job table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private Integer id;
    private String name;
    private boolean active;
}
