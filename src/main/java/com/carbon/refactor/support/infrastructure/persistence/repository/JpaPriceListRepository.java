package com.carbon.refactor.support.infrastructure.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carbon.refactor.support.domain.entity.PriceList;

/**
 * JPA repository interface for PriceList entities.
 */
@Repository
public interface JpaPriceListRepository extends JpaRepository<PriceList, Integer> {
    
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
    List<PriceList> findByNameContainingIgnoreCase(String name);
    
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
     * @param sameDate The same date as the first parameter (needed for the query)
     * @return A list of price lists that are valid on the given date
     */
    List<PriceList> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate date, LocalDate sameDate);
    
    /**
     * Find price lists by channel ID and valid on the given date.
     * 
     * @param channelId The channel ID
     * @param date The date to check
     * @param sameDate The same date as the second parameter (needed for the query)
     * @return A list of price lists for the given channel and valid on the given date
     */
    List<PriceList> findByChannelIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Integer channelId, LocalDate date, LocalDate sameDate);
}
