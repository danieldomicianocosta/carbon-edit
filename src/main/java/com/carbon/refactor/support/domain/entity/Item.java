package com.carbon.refactor.support.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the item table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Integer id;
    private String name;
    private String cod;
    private Integer seq;
    private boolean forFree;
    private boolean generic;
    private Integer mandatoryClaId;
    private Integer itemTypeId;
    private String file;
    private String icon;
    private String description;
    private String hyperlink;
    private Integer responsabilityClaId;
    private Integer term;
    private boolean termWorkDay;
    private boolean highlight;
    private boolean flagPurchases;
    private String descriptionPurchases;
    private Boolean enableExportCode;
    private String exportCodeContaAzul;
    private Boolean factorySettings;
    private Integer customFieldJira;
    private Boolean flagProduction;
    private Integer jiraIntegrationClaId;
    private String nameCommercial;
    private String descriptionCommercial;
    private Integer groupCommercialClaId;
    private Integer itemOrder;
}
