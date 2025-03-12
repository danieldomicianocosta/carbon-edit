package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.PaymentMethod;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on PaymentMethod entities.
 */
public interface PaymentMethodRepository extends ReadOnlyRepository<PaymentMethod, Integer> {
    
    /**
     * Find all active payment methods.
     * 
     * @return A list of active payment methods
     */
    List<PaymentMethod> findAllActive();
    
    /**
     * Find a payment method by its name.
     * 
     * @param name The name to search for
     * @return An Optional containing the payment method if found, or empty if not found
     */
    Optional<PaymentMethod> findByName(String name);
    
    /**
     * Find payment methods by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of payment methods with names containing the given text
     */
    List<PaymentMethod> findByNameContaining(String name);
}
