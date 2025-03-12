package com.carbon.refactor.support.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing the contact_simplified table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactSimplified {
    private Integer id;
    private Integer proposalId;
    private String name;
    private String email;
    private String phone;
    private String document;
    private Integer typeCla;
}
