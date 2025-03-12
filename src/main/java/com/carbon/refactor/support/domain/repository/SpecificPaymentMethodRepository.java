package com.carbon.refactor.support.domain.repository;

import com.carbon.refactor.support.domain.entity.SpecificPaymentMethod;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for read-only operations on SpecificPaymentMethod entities.
 */
public interface SpecificPaymentMethodRepository extends ReadOnlyRepository<SpecificPaymentMethod, Integer> {
    
    /**
     * Find specific payment methods by payment method ID.
     * 
     * @param paymentMethodId The payment method ID
     * @return A list of specific payment methods for the given payment method
     */
    List<SpecificPaymentMethod> findByPaymentMethodId(Integer paymentMethodId);
    
    /**
     * Find specific payment methods by specific payment condition ID.
     * 
     * @param specificPaymentConditionId The specific payment condition ID
     * @return A list of specific payment methods for the given specific payment condition
     */
    List<SpecificPaymentMethod> findBySpecificPaymentConditionId(Integer specificPaymentConditionId);
    
    /**
     * Find specific payment methods by meio pagamento etapa classifier ID.
     * 
     * @param meioPagamentoEtapaClaId The meio pagamento etapa classifier ID
     * @return A list of specific payment methods with the given meio pagamento etapa classifier
     */
    List<SpecificPaymentMethod> findByMeioPagamentoEtapaClaId(Integer meioPagamentoEtapaClaId);
    
    /**
     * Find a specific payment method by payment method ID and specific payment condition ID.
     * 
     * @param paymentMethodId The payment method ID
     * @param specificPaymentConditionId The specific payment condition ID
     * @return An Optional containing the specific payment method if found, or empty if not found
     */
    Optional<SpecificPaymentMethod> findByPaymentMethodIdAndSpecificPaymentConditionId(
            Integer paymentMethodId, Integer specificPaymentConditionId);
}
