package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.PriceList;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on PriceList entities.
 */
public interface PriceListService extends ReadOnlyService<PriceList, Integer> {
    
    /**
     * Find a price list by its name.
     * 
     * @param name The name to search for
     * @return An Optional containing the price list if found, or empty if not found
     */
    Optional<PriceList> findByName(String name);
    
    /**
     * Find price lists by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of price lists with names containing the given text
     */
    List<PriceList> findByNameContaining(String name);
    
    /**
     * Find price lists by channel ID.
     * 
     * @param channelId The channel ID
     * @return A list of price lists for the given channel
     */
    List<PriceList> findByChannelId(Integer channelId);
    
    /**
     * Find price lists that are valid for all partners.
     * 
     * @return A list of price lists that are valid for all partners
     */
    List<PriceList> findByAllPartnersTrue();
    
    /**
     * Find price lists that are valid on the given date.
     * 
     * @param date The date to check
     * @return A list of price lists that are valid on the given date
     */
    List<PriceList> findByValidOnDate(LocalDate date);
    
    /**
     * Find price lists by channel ID and valid on the given date.
     * 
     * @param channelId The channel ID
     * @param date The date to check
     * @return A list of price lists for the given channel and valid on the given date
     */
    List<PriceList> findByChannelIdAndValidOnDate(Integer channelId, LocalDate date);
}
