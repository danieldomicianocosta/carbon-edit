package com.carbon.refactor.support.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the checkpoint table.
 */
@Entity
@Table(name = "checkpoint")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Checkpoint {
    @Id
    private Integer id;
    private String name;
    private String description;
}
