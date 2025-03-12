package com.carbon.refactor.support.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the model table.
 */
@Entity
@Table(name = "model")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mdl_id")
    private Integer id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "active", nullable = false)
    private boolean active;
}
