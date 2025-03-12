package com.carbon.refactor.support.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the specific_payment_condition table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecificPaymentCondition {
    private Integer id;
    private String name;
    private BigDecimal tax;
    private Boolean active;
    private boolean armor;
    private boolean amendments;
    private boolean technicalAssistance;
    private String description;
    private Integer maxIntallments;
    private Boolean immediateDelivery;
    private String maxDiasParaFrenteDataPactuada;
    private Integer maxQtdeDiasClaId;
    private String percentMinPrimeiraParcela;
    private String maxIntervaloParcelas;
    private String tipoMaxIntervaloParcelas;
    private Integer userIdCreate;
    private LocalDateTime createDate;
    private Integer userIdDelete;
    private LocalDateTime deleteDate;
}
