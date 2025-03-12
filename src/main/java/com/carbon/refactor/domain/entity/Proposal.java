package com.carbon.refactor.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proposal", uniqueConstraints = {
    @UniqueConstraint(name = "ukNumCod", columnNames = {"num", "cod"})
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pps_id")
    private Integer id;
    
    @Column(name = "pps_id_production")
    private Integer idProduction;
    
    @Column(name = "proposal_number", nullable = false, length = 50)
    private String proposalNumber;
    
    @Column(name = "num", nullable = false)
    private Long num;
    
    @Column(name = "cod", nullable = false, length = 2)
    private String cod;
    
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
    
    @Column(name = "validity_date", nullable = false)
    private LocalDateTime validityDate;
    
    @Column(name = "finished_date")
    private LocalDateTime finishedDate;
    
    @Column(name = "status_cla_id", nullable = false)
    private Integer statusClaId;
    
    @Column(name = "amendments_status_cla")
    private Integer amendmentsStatusCla;
    
    @Column(name = "led_id")
    private Integer ledId;
    
    @Column(name = "finantial_contact", nullable = false, columnDefinition = "tinyint default 0")
    private Boolean finantialContact;
    
    @Column(name = "finantial_contact_name", length = 150)
    private String finantialContactName;
    
    @Column(name = "finantial_contact_email", length = 100)
    private String finantialContactEmail;
    
    @Column(name = "finantial_contact_phone", length = 45)
    private String finantialContactPhone;
    
    @Column(name = "document_contact", nullable = false, columnDefinition = "tinyint default 0")
    private Boolean documentContact;
    
    @Column(name = "document_contact_name", length = 150)
    private String documentContactName;
    
    @Column(name = "document_contact_email", length = 100)
    private String documentContactEmail;
    
    @Column(name = "document_contact_phone", length = 45)
    private String documentContactPhone;
    
    @Column(name = "commercial_contact_name", length = 150)
    private String commercialContactName;
    
    @Column(name = "commercial_contact_email", length = 100)
    private String commercialContactEmail;
    
    @Column(name = "commercial_contact_phone", length = 45)
    private String commercialContactPhone;
    
    @Column(name = "risk_cla_id", nullable = false)
    private Integer riskClaId;
    
    @Column(name = "immediate_delivery_cla_id", nullable = false, columnDefinition = "int default 0")
    private Integer immediateDeliveryClaId;
    
    @Lob
    @Column(name = "contract")
    private String contract;
    
    @Column(name = "usr_id_create")
    private Integer usrIdCreate;
    
    @Column(name = "usr_id_last_update")
    private Integer usrIdLastUpdate;
    
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;
    
    @Column(name = "scheduling_cla_id")
    private Integer schedulingClaId;
    
    @Column(name = "conta_azul_triggered", columnDefinition = "tinyint")
    private Boolean contaAzulTriggered;
    
    @Column(name = "cluster_name", length = 15)
    private String clusterName;
    
    @Column(name = "conta_azul_amendments", columnDefinition = "tinyint")
    private Boolean contaAzulAmendments;
    
    @Column(name = "signature_contact", columnDefinition = "tinyint default 0")
    private Boolean signatureContact;
    
    @Column(name = "basic_contact", columnDefinition = "tinyint")
    private Boolean basicContact;
    
    @Column(name = "signature_cla_id")
    private Integer signatureClaId;
    
    @Column(name = "customer_name", length = 255)
    private String customerName;
    
    @Column(name = "customer_email", length = 255)
    private String customerEmail;
    
    @Column(name = "customer_phone", length = 45)
    private String customerPhone;
    
    @Column(name = "finished_without_sale_cla_id")
    private Integer finishedWithoutSaleClaId;
    
    @Lob
    @Column(name = "finished_without_sale_comment")
    private String finishedWithoutSaleComment;
    
    @Lob
    @Column(name = "backoffice_rejected_comment")
    private String backofficeRejectedComment;
    
    @Column(name = "coparticipation_internal_commission", columnDefinition = "tinyint")
    private Boolean coparticipationInternalCommission;
    
    @Column(name = "proposal_sent_date")
    private LocalDateTime proposalSentDate;
    
    @Column(name = "proposal_sent", columnDefinition = "tinyint")
    private Boolean proposalSent;
    
    @Column(name = "revenue_amendments", columnDefinition = "tinyint")
    private Boolean revenueAmendments;
    
    @Column(name = "totvs_triggered", columnDefinition = "tinyint")
    private Boolean totvsTriggered;
    
}
