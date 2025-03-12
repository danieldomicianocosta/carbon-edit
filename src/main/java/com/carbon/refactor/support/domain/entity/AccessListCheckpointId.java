package com.carbon.refactor.support.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Composite primary key class for AccessListCheckpoint entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessListCheckpointId implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer checkpointId;
    private Integer accessListId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessListCheckpointId that = (AccessListCheckpointId) o;
        return Objects.equals(checkpointId, that.checkpointId) &&
               Objects.equals(accessListId, that.accessListId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(checkpointId, accessListId);
    }
}
