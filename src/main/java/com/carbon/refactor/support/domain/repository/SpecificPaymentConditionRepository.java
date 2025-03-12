package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.SpecificPaymentCondition;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on SpecificPaymentCondition entities.
 */
public interface SpecificPaymentConditionRepository extends ReadOnlyRepository<SpecificPaymentCondition, Integer> {
    
    /**
     * Find all active specific payment conditions.
     * 
     * @return A list of active specific payment conditions
     */
    List<SpecificPaymentCondition> findByActiveTrue();
    
    /**
     * Find a specific payment condition by its name.
     * 
     * @param name The name to search for
     * @return An Optional containing the specific payment condition if found, or empty if not found
     */
    Optional<SpecificPaymentCondition> findByName(String name);
    
    /**
     * Find specific payment conditions by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of specific payment conditions with names containing the given text
     */
    List<SpecificPaymentCondition> findByNameContaining(String name);
    
    /**
     * Find specific payment conditions by tax.
     * 
     * @param tax The tax to search for
     * @return A list of specific payment conditions with the given tax
     */
    List<SpecificPaymentCondition> findByTax(BigDecimal tax);
    
    /**
     * Find specific payment conditions by tax less than or equal to the given value.
     * 
     * @param tax The maximum tax
     * @return A list of specific payment conditions with taxes less than or equal to the given value
     */
    List<SpecificPaymentCondition> findByTaxLessThanEqual(BigDecimal tax);
    
    /**
     * Find specific payment conditions by tax greater than or equal to the given value.
     * 
     * @param tax The minimum tax
     * @return A list of specific payment conditions with taxes greater than or equal to the given value
     */
    List<SpecificPaymentCondition> findByTaxGreaterThanEqual(BigDecimal tax);
    
    /**
     * Find specific payment conditions that allow armor.
     * 
     * @return A list of specific payment conditions that allow armor
     */
    List<SpecificPaymentCondition> findByArmorTrue();
    
    /**
     * Find specific payment conditions that allow amendments.
     * 
     * @return A list of specific payment conditions that allow amendments
     */
    List<SpecificPaymentCondition> findByAmendmentsTrue();
    
    /**
     * Find specific payment conditions that allow technical assistance.
     * 
     * @return A list of specific payment conditions that allow technical assistance
     */
    List<SpecificPaymentCondition> findByTechnicalAssistanceTrue();
    
    /**
     * Find specific payment conditions by maximum installments.
     * 
     * @param maxInstallments The maximum installments
     * @return A list of specific payment conditions with the given maximum installments
     */
    List<SpecificPaymentCondition> findByMaxIntallments(Integer maxInstallments);
    
    /**
     * Find specific payment conditions that allow immediate delivery.
     * 
     * @return A list of specific payment conditions that allow immediate delivery
     */
    List<SpecificPaymentCondition> findByImmediateDeliveryTrue();
    
    /**
     * Find specific payment conditions by user ID who created them.
     * 
     * @param userIdCreate The user ID who created the specific payment conditions
     * @return A list of specific payment conditions created by the given user
     */
    List<SpecificPaymentCondition> findByUserIdCreate(Integer userIdCreate);
    
    /**
     * Find specific payment conditions by user ID who deleted them.
     * 
     * @param userIdDelete The user ID who deleted the specific payment conditions
     * @return A list of specific payment conditions deleted by the given user
     */
    List<SpecificPaymentCondition> findByUserIdDelete(Integer userIdDelete);
    
    /**
     * Find specific payment conditions created after the given date.
     * 
     * @param date The date to search from
     * @return A list of specific payment conditions created after the given date
     */
    List<SpecificPaymentCondition> findByCreateDateAfter(LocalDateTime date);
    
    /**
     * Find specific payment conditions created before the given date.
     * 
     * @param date The date to search to
     * @return A list of specific payment conditions created before the given date
     */
    List<SpecificPaymentCondition> findByCreateDateBefore(LocalDateTime date);
    
    /**
     * Find active specific payment conditions.
     * 
     * @return A list of active specific payment conditions
     */
    List<SpecificPaymentCondition> findByDeleteDateIsNull();
}
