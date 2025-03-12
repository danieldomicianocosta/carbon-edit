package com.carbon.refactor.support.domain.service;

import com.carbon.refactor.support.domain.entity.Product;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for read-only operations on Product entities.
 */
public interface ProductService extends ReadOnlyService<Product, Integer> {
    
    /**
     * Find all active products.
     * 
     * @return A list of active products
     */
    List<Product> findAllActive();
    
    /**
     * Find a product by its name.
     * 
     * @param name The name to search for
     * @return An Optional containing the product if found, or empty if not found
     */
    Optional<Product> findByName(String name);
    
    /**
     * Find products by name containing the given text.
     * 
     * @param name The name to search for
     * @return A list of products with names containing the given text
     */
    List<Product> findByNameContaining(String name);
}
