package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.SpecificPaymentEvent;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on SpecificPaymentEvent entities.
 */
public interface SpecificPaymentEventService extends ReadOnlyService<SpecificPaymentEvent, Integer> {
    
    /**
     * Find specific payment events by specific payment condition ID.
     * 
     * @param specificPaymentConditionId The specific payment condition ID
     * @return A list of specific payment events for the given specific payment condition
     */
    List<SpecificPaymentEvent> findBySpecificPaymentConditionId(Integer specificPaymentConditionId);
    
    /**
     * Find specific payment events by classifier ID.
     * 
     * @param classifierId The classifier ID
     * @return A list of specific payment events with the given classifier
     */
    List<SpecificPaymentEvent> findByClassifierId(Integer classifierId);
    
    /**
     * Find a specific payment event by specific payment condition ID and classifier ID.
     * 
     * @param specificPaymentConditionId The specific payment condition ID
     * @param classifierId The classifier ID
     * @return An Optional containing the specific payment event if found, or empty if not found
     */
    Optional<SpecificPaymentEvent> findBySpecificPaymentConditionIdAndClassifierId(
            Integer specificPaymentConditionId, Integer classifierId);
}
