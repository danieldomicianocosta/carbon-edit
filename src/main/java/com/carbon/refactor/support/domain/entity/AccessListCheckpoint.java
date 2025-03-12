package com.carbon.refactor.support.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the access_list_checkpoint table.
 */
@Entity
@Table(name = "access_list_checkpoint")
@IdClass(AccessListCheckpointId.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessListCheckpoint {
    @Id
    @Column(name = "checkpoint_id")
    private Integer checkpointId;
    
    @Id
    @Column(name = "access_list_id")
    private Integer accessListId;
    
    // Relationships
    @ManyToOne
    @JoinColumn(name = "checkpoint_id", insertable = false, updatable = false)
    private Checkpoint checkpoint;
    
    @ManyToOne
    @JoinColumn(name = "access_list_id", insertable = false, updatable = false)
    private AccessList accessList;
}
