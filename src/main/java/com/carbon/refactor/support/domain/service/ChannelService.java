package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.Channel;
import java.util.List;

/**
 * Service interface for read-only operations on Channel entities.
 */
public interface ChannelService extends ReadOnlyService<Channel, Integer> {
    
    /**
     * Find all active channels.
     * 
     * @return A list of active channels
     */
    List<Channel> findAllActive();
    
    /**
     * Find channels by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of channels with names containing the given text
     */
    List<Channel> findByNameContaining(String name);
    
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
