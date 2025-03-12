package com.carbon.refactor.support.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Composite primary key class for SellerPartner entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerPartnerId implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer sellerId;
    private Integer partnerId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerPartnerId that = (SellerPartnerId) o;
        return Objects.equals(sellerId, that.sellerId) &&
               Objects.equals(partnerId, that.partnerId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(sellerId, partnerId);
    }
}
