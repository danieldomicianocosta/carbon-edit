package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.Partner;
import java.util.List;

/**
 * Repository interface for read-only operations on Partner entities.
 */
public interface PartnerRepository extends ReadOnlyRepository<Partner, Integer> {
    
    /**
     * Find partners by channel ID.
     * 
     * @param channelId The channel ID
     * @return A list of partners for the given channel
     */
    List<Partner> findByChannelId(Integer channelId);
    
    /**
     * Find partners by entity person ID.
     * 
     * @param entityPerId The entity person ID
     * @return A list of partners with the given entity person ID
     */
    List<Partner> findByEntityPerId(Integer entityPerId);
    
    /**
     * Find partners by partner group ID.
     * 
     * @param partnerGroupId The partner group ID
     * @return A list of partners with the given partner group ID
     */
    List<Partner> findByPartnerGroupId(Integer partnerGroupId);
    
    /**
     * Find partners that are assistance.
     * 
     * @return A list of partners that are assistance
     */
    List<Partner> findByIsAssistanceTrue();
    
    /**
     * Find partners by situation classifier.
     * 
     * @param situationCla The situation classifier
     * @return A list of partners with the given situation classifier
     */
    List<Partner> findBySituationCla(Integer situationCla);
}
