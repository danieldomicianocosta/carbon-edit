package com.carbon.refactor.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDetailVehicleRequestDTO {
    
    @NotNull(message = "Proposal detail ID is required")
    private Integer proposalDetailId;
    
    private Integer vehicleId;
    
    @NotNull(message = "Model ID is required")
    private Integer modelId;
    
    @Size(max = 100, message = "Version must be less than 100 characters")
    private String version;
    
    @NotNull(message = "Model year is required")
    private Integer modelYear;
    
    @NotNull(message = "Price product ID is required")
    private Integer priceProductId;
    
    private Double productAmountDiscount;
    
    private Double productPercentDiscount;
    
    private Double productFinalPrice;
    
    private Double productTablePriceTax;
    
    @NotNull(message = "Over price is required")
    private Double overPrice;
    
    private Double overPriceCarbon;
    
    @NotNull(message = "Over price partner discount amount is required")
    private Double overPricePartnerDiscountAmount;
    
    @NotNull(message = "Over price partner discount percent is required")
    private Double overPricePartnerDiscountPercent;
    
    @NotNull(message = "Price discount amount is required")
    private Double priceDiscountAmount;
    
    @NotNull(message = "Price discount percent is required")
    private Double priceDiscountPercent;
    
    @NotNull(message = "Total amount is required")
    private Double totalAmount;
    
    @NotNull(message = "Total tax amount is required")
    private Double totalTaxAmount;
    
    @NotNull(message = "Total tax percent is required")
    private Double totalTaxPercent;
    
    @NotNull(message = "Standard term days is required")
    private Integer standardTermDays;
    
    @NotNull(message = "Agreed term days is required")
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
