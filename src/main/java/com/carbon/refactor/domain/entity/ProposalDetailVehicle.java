package com.carbon.refactor.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proposal_detail_vehicle")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDetailVehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pdv_id")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ppd_id", nullable = false)
    private ProposalDetail proposalDetail;
    
    @Column(name = "vhe_id")
    private Integer vehicleId;
    
    @Column(name = "mdl_id", nullable = false)
    private Integer modelId;
    
    @Column(name = "version", length = 100)
    private String version;
    
    @Column(name = "model_year", nullable = false)
    private Integer modelYear;
    
    @Column(name = "ppr_id", nullable = false)
    private Integer priceProductId;
    
    @Column(name = "product_amount_discount", nullable = false, columnDefinition = "decimal(13,2) default 0.00")
    private Double productAmountDiscount;
    
    @Column(name = "product_percent_discount", nullable = false, columnDefinition = "decimal(5,2) default 0.00")
    private Double productPercentDiscount;
    
    @Column(name = "product_final_price", nullable = false, columnDefinition = "decimal(13,2) default 0.00")
    private Double productFinalPrice;
    
    @Column(name = "product_table_price_tax")
    private Double productTablePriceTax;
    
    @Column(name = "over_price", nullable = false)
    private Double overPrice;
    
    @Column(name = "over_price_carbon")
    private Double overPriceCarbon;
    
    @Column(name = "over_price_partner_discount_amount", nullable = false)
    private Double overPricePartnerDiscountAmount;
    
    @Column(name = "over_price_partner_discount_percent", nullable = false)
    private Double overPricePartnerDiscountPercent;
    
    @Column(name = "price_discount_amount", nullable = false)
    private Double priceDiscountAmount;
    
    @Column(name = "price_discount_percent", nullable = false)
    private Double priceDiscountPercent;
    
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;
    
    @Column(name = "total_tax_amount", nullable = false)
    private Double totalTaxAmount;
    
    @Column(name = "total_tax_percent", nullable = false)
    private Double totalTaxPercent;
    
    @Column(name = "standard_term_days", nullable = false)
    private Integer standardTermDays;
    
    @Column(name = "agreed_term_days", nullable = false)
    private Integer agreedTermDays;
    
    @Column(name = "spc_id")
    private Integer specificPaymentConditionId;
    
    @Column(name = "spc_tax")
    private Double specificPaymentConditionTax;
    
    @Column(name = "spc_id_ammendment")
    private Integer specificPaymentConditionAmmendmentId;
    
    @Column(name = "spc_ammendment_tax")
    private Double specificPaymentConditionAmmendmentTax;
    
    @Column(name = "over_price_carbon_ammendment")
    private Double overPriceCarbonAmmendment;
    
    @Column(name = "price_discount_amount_ammendment")
    private Double priceDiscountAmountAmmendment;
    
    @Column(name = "over_price_ammendment")
    private Double overPriceAmmendment;
    
    @Column(name = "over_price_partner_discount_amount_ammendment")
    private Double overPricePartnerDiscountAmountAmmendment;
}
