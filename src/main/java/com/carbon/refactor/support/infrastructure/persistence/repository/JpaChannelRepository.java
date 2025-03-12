package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.Channel;

/**
 * JPA repository interface for Channel entities.
 */
@Repository
public interface JpaChannelRepository extends JpaRepository<Channel, Integer> {
    
    /**
     * Find all active channels.
     * 
     * @return A list of active channels
     */
    @Query("SELECT c FROM Channel c WHERE c.active = true")
    List<Channel> findAllActive();
    
    /**
     * Find channels by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of channels with names containing the given text
     */
    List<Channel> findByNameContainingIgnoreCase(String name);
    
    /**
     * Find channels that have partners.
     * 
     * @return A list of channels that have partners
     */
    List<Channel> findByHasPartnerTrue();
    
    /**
     * Find channels that have internal sales.
     * 
     * @return A list of channels that have internal sales
     */
    List<Channel> findByHasInternalSaleTrue();
}