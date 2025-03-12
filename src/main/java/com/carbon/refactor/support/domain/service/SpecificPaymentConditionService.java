package com.carbon.refactor.support.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.carbon.refactor.support.domain.entity.SpecificPaymentCondition;

/**
 * Service interface for read-only operations on SpecificPaymentCondition entities.
 */
public interface SpecificPaymentConditionService extends ReadOnlyService<SpecificPaymentCondition, Integer> {
    
    /**
     * Find all active specific payment conditions.
     * 
     * @return A list of active specific payment conditions
     */
    List<SpecificPaymentCondition> findAllActive();
    
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
     * Find specific payment conditions by max installments.
     * 
     * @param maxInstallments The max installments to search for
     * @return A list of specific payment conditions with the given max installments
     */
    List<SpecificPaymentCondition> findByMaxIntallments(Integer maxInstallments);
    
    /**
     * Find specific payment conditions that allow immediate delivery.
     * 
     * @return A list of specific payment conditions that allow immediate delivery
     */
    List<SpecificPaymentCondition> findByImmediateDeliveryTrue();
    
    /**
     * Find specific payment conditions by max days classifier ID.
     * 
     * @param maxQtdeDiasClassifierId The max days classifier ID
     * @return A list of specific payment conditions with the given max days classifier
     */
    List<SpecificPaymentCondition> findByMaxQtdeDiasClassifierId(Integer maxQtdeDiasClassifierId);
    
    /**
     * Find active specific payment conditions.
     * 
     * @return A list of active specific payment conditions
     */
    List<SpecificPaymentCondition> findByDeleteDateIsNull();
}
