package com.carbon.refactor.application.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
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
public class ProposalRequestDTO {
    
    private Integer idProduction;
    
    @NotBlank(message = "Proposal number is required")
    @Size(max = 50, message = "Proposal number must be less than 50 characters")
    private String proposalNumber;
    
    @NotNull(message = "Num is required")
    private Long num;
    
    @NotBlank(message = "Cod is required")
    private String cod;
    
    @NotNull(message = "Create date is required")
    private LocalDateTime createDate;
    
    @NotNull(message = "Validity date is required")
    private LocalDateTime validityDate;
    
    private LocalDateTime finishedDate;
    
    @NotNull(message = "Status classifier ID is required")
    private Integer statusClaId;
    
    private Integer amendmentsStatusCla;
    
    private Integer ledId;
    
    private Boolean finantialContact;
    
    @Size(max = 150, message = "Financial contact name must be less than 150 characters")
    private String finantialContactName;
    
    @Size(max = 100, message = "Financial contact email must be less than 100 characters")
    private String finantialContactEmail;
    
    @Size(max = 45, message = "Financial contact phone must be less than 45 characters")
    private String finantialContactPhone;
    
    private Boolean documentContact;
    
    @Size(max = 150, message = "Document contact name must be less than 150 characters")
    private String documentContactName;
    
    @Size(max = 100, message = "Document contact email must be less than 100 characters")
    private String documentContactEmail;
    
    @Size(max = 45, message = "Document contact phone must be less than 45 characters")
    private String documentContactPhone;
    
    @Size(max = 150, message = "Commercial contact name must be less than 150 characters")
    private String commercialContactName;
    
    @Size(max = 100, message = "Commercial contact email must be less than 100 characters")
    private String commercialContactEmail;
    
    @Size(max = 45, message = "Commercial contact phone must be less than 45 characters")
    private String commercialContactPhone;
    
    @NotNull(message = "Risk classifier ID is required")
    private Integer riskClaId;
    
    @NotNull(message = "Immediate delivery classifier ID is required")
    private Integer immediateDeliveryClaId;
    
    private String contract;
    
    private Integer usrIdCreate;
    
    private Integer usrIdLastUpdate;
    
    private LocalDateTime lastUpdateDate;
    
    private Integer schedulingClaId;
    
    private Boolean contaAzulTriggered;
    
    @Size(max = 15, message = "Cluster name must be less than 15 characters")
    private String clusterName;
    
    private Boolean contaAzulAmendments;
    
    private Boolean signatureContact;
    
    private Boolean basicContact;
    
    private Integer signatureClaId;
    
    @Size(max = 255, message = "Customer name must be less than 255 characters")
    private String customerName;
    
    @Size(max = 255, message = "Customer email must be less than 255 characters")
    private String customerEmail;
    
    @Size(max = 45, message = "Customer phone must be less than 45 characters")
    private String customerPhone;
    
    private Integer finishedWithoutSaleClaId;
    
    private String finishedWithoutSaleComment;
    
    private String backofficeRejectedComment;
    
    private Boolean coparticipationInternalCommission;
    
    private LocalDateTime proposalSentDate;
    
    private Boolean proposalSent;
    
    private Boolean revenueAmendments;
    
    private Boolean totvsTriggered;
}
