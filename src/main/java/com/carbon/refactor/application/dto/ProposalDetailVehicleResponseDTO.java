package com.carbon.refactor.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDetailVehicleResponseDTO {
    
    private Integer id;
    private Integer proposalDetailId;
    private Integer vehicleId;
    private Integer modelId;
    private String version;
    private Integer modelYear;
    private Integer priceProductId;
    private Double productAmountDiscount;
    private Double productPercentDiscount;
    private Double productFinalPrice;
    private Double productTablePriceTax;
    private Double overPrice;
    private Double overPriceCarbon;
    private Double overPricePartnerDiscountAmount;
    private Double overPricePartnerDiscountPercent;
    private Double priceDiscountAmount;
    private Double priceDiscountPercent;
    private Double totalAmount;
    private Double totalTaxAmount;
    private Double totalTaxPercent;
    private Integer standardTermDays;
    private Integer agreedTermDays;
    private Integer specificPaymentConditionId;
    private Double specificPaymentConditionTax;
    private Integer specificPaymentConditionAmmendmentId;
    private Double specificPaymentConditionAmmendmentTax;
    private Double overPriceCarbonAmmendment;
    private Double priceDiscountAmountAmmendment;
    private Double overPriceAmmendment;
    private Double overPricePartnerDiscountAmountAmmendment;
}
