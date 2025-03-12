package com.carbon.refactor.application.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import com.carbon.refactor.application.dto.ProposalDetailVehicleRequestDTO;
import com.carbon.refactor.application.dto.ProposalDetailVehicleResponseDTO;
import com.carbon.refactor.domain.entity.ProposalDetail;
import com.carbon.refactor.domain.entity.ProposalDetailVehicle;
import com.carbon.refactor.domain.service.ProposalDetailService;

@Component
@RequiredArgsConstructor
public class ProposalDetailVehicleMapper {
    
    private final ProposalDetailService proposalDetailService;
    
    public ProposalDetailVehicle toEntity(ProposalDetailVehicleRequestDTO dto) {
        ProposalDetail proposalDetail = proposalDetailService.findById(dto.getProposalDetailId());
        
        return ProposalDetailVehicle.builder()
                .proposalDetail(proposalDetail)
                .vehicleId(dto.getVehicleId())
                .modelId(dto.getModelId())
                .version(dto.getVersion())
                .modelYear(dto.getModelYear())
                .priceProductId(dto.getPriceProductId())
                .productAmountDiscount(dto.getProductAmountDiscount())
                .productPercentDiscount(dto.getProductPercentDiscount())
                .productFinalPrice(dto.getProductFinalPrice())
                .productTablePriceTax(dto.getProductTablePriceTax())
                .overPrice(dto.getOverPrice())
                .overPriceCarbon(dto.getOverPriceCarbon())
                .overPricePartnerDiscountAmount(dto.getOverPricePartnerDiscountAmount())
                .overPricePartnerDiscountPercent(dto.getOverPricePartnerDiscountPercent())
                .priceDiscountAmount(dto.getPriceDiscountAmount())
                .priceDiscountPercent(dto.getPriceDiscountPercent())
                .totalAmount(dto.getTotalAmount())
                .totalTaxAmount(dto.getTotalTaxAmount())
                .totalTaxPercent(dto.getTotalTaxPercent())
                .standardTermDays(dto.getStandardTermDays())
                .agreedTermDays(dto.getAgreedTermDays())
                .specificPaymentConditionId(dto.getSpecificPaymentConditionId())
                .specificPaymentConditionTax(dto.getSpecificPaymentConditionTax())
                .specificPaymentConditionAmmendmentId(dto.getSpecificPaymentConditionAmmendmentId())
                .specificPaymentConditionAmmendmentTax(dto.getSpecificPaymentConditionAmmendmentTax())
                .overPriceCarbonAmmendment(dto.getOverPriceCarbonAmmendment())
                .priceDiscountAmountAmmendment(dto.getPriceDiscountAmountAmmendment())
                .overPriceAmmendment(dto.getOverPriceAmmendment())
                .overPricePartnerDiscountAmountAmmendment(dto.getOverPricePartnerDiscountAmountAmmendment())
                .build();
    }
    
    public ProposalDetailVehicle toEntity(ProposalDetailVehicleRequestDTO dto, Integer id) {
        ProposalDetailVehicle entity = toEntity(dto);
        entity.setId(id);
        return entity;
    }
    
    public ProposalDetailVehicleResponseDTO toDto(ProposalDetailVehicle entity) {
        return ProposalDetailVehicleResponseDTO.builder()
                .id(entity.getId())
                .proposalDetailId(entity.getProposalDetail().getId())
                .vehicleId(entity.getVehicleId())
                .modelId(entity.getModelId())
                .version(entity.getVersion())
                .modelYear(entity.getModelYear())
                .priceProductId(entity.getPriceProductId())
                .productAmountDiscount(entity.getProductAmountDiscount())
                .productPercentDiscount(entity.getProductPercentDiscount())
                .productFinalPrice(entity.getProductFinalPrice())
                .productTablePriceTax(entity.getProductTablePriceTax())
                .overPrice(entity.getOverPrice())
                .overPriceCarbon(entity.getOverPriceCarbon())
                .overPricePartnerDiscountAmount(entity.getOverPricePartnerDiscountAmount())
                .overPricePartnerDiscountPercent(entity.getOverPricePartnerDiscountPercent())
                .priceDiscountAmount(entity.getPriceDiscountAmount())
                .priceDiscountPercent(entity.getPriceDiscountPercent())
                .totalAmount(entity.getTotalAmount())
                .totalTaxAmount(entity.getTotalTaxAmount())
                .totalTaxPercent(entity.getTotalTaxPercent())
                .standardTermDays(entity.getStandardTermDays())
                .agreedTermDays(entity.getAgreedTermDays())
                .specificPaymentConditionId(entity.getSpecificPaymentConditionId())
                .specificPaymentConditionTax(entity.getSpecificPaymentConditionTax())
                .specificPaymentConditionAmmendmentId(entity.getSpecificPaymentConditionAmmendmentId())
                .specificPaymentConditionAmmendmentTax(entity.getSpecificPaymentConditionAmmendmentTax())
                .overPriceCarbonAmmendment(entity.getOverPriceCarbonAmmendment())
                .priceDiscountAmountAmmendment(entity.getPriceDiscountAmountAmmendment())
                .overPriceAmmendment(entity.getOverPriceAmmendment())
                .overPricePartnerDiscountAmountAmmendment(entity.getOverPricePartnerDiscountAmountAmmendment())
                .build();
    }
}
