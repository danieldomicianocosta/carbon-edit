package com.carbon.refactor.application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalResponseDTO {
    
    private Integer id;
    private Integer idProduction;
    private String proposalNumber;
    private Long num;
    private String cod;
    private LocalDateTime createDate;
    private LocalDateTime validityDate;
    private LocalDateTime finishedDate;
    private Integer statusClaId;
    private Integer amendmentsStatusCla;
    private Integer ledId;
    private Boolean finantialContact;
    private String finantialContactName;
    private String finantialContactEmail;
    private String finantialContactPhone;
    private Boolean documentContact;
    private String documentContactName;
    private String documentContactEmail;
    private String documentContactPhone;
    private String commercialContactName;
    private String commercialContactEmail;
    private String commercialContactPhone;
    private Integer riskClaId;
    private Integer immediateDeliveryClaId;
    private String contract;
    private Integer usrIdCreate;
    private Integer usrIdLastUpdate;
    private LocalDateTime lastUpdateDate;
    private Integer schedulingClaId;
    private Boolean contaAzulTriggered;
    private String clusterName;
    private Boolean contaAzulAmendments;
    private Boolean signatureContact;
    private Boolean basicContact;
    private Integer signatureClaId;
    private String customerName;
    private String customerEmail;
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
