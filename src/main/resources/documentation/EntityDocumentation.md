# Entity Documentation

This document provides detailed information about the database tables and their corresponding entity classes in the Carbon Backend project.

## Table of Contents

### Core Proposal Entities
- [Proposal](#proposal)
- [ProposalCommission](#proposalcommission)
- [ProposalDetail](#proposaldetail)
- [ProposalDetailVehicle](#proposaldetailvehicle)
- [ProposalDetailVehicleItem](#proposaldetailvehicleitem)
- [ProposalDocument](#proposaldocument)
- [ProposalFup](#proposalfup)
- [ProposalApprovalRule](#proposalapprovalrule)
- [ContactSimplified](#contactsimplified)
- [Document](#document)

### Product and Pricing Entities
- [Product](#product)
- [ProductModel](#productmodel)
- [ProductModelBonus](#productmodelbonus)
- [PriceList](#pricelist)
- [PriceListPartner](#pricelistpartner)
- [PriceProduct](#priceproduct)
- [PriceItem](#priceitem)
- [PriceItemModel](#priceitemmodel)
- [Item](#item)
- [ItemModel](#itemmodel)
- [Brand](#brand)
- [Model](#model)
- [CommissionType](#commissiontype)

### Partner and Seller Entities
- [Partner](#partner)
- [PartnerBrand](#partnerbrand)
- [Seller](#seller)
- [SellerPartner](#sellerpartner)
- [Channel](#channel)
- [Job](#job)

### Payment-Related Entities
- [PaymentMethod](#paymentmethod)
- [PaymentRule](#paymentrule)
- [PaymentRuleInstallment](#paymentruleinstallment)
- [SpecificPaymentCondition](#specificpaymentcondition)
- [SpecificPaymentEvent](#specificpaymentevent)
- [SpecificPaymentMethod](#specificpaymentmethod)
- [SpecificPaymentRule](#specificpaymentrule)

### Access Control Entities
- [AccessList](#accesslist)
- [AccessListCheckpoint](#accesslistcheckpoint)
- [Checkpoint](#checkpoint)

## Proposal

### Table: `proposal`

#### Description
The Proposal table is the central entity in the system that stores information about sales proposals. It contains details about the proposal status, dates, contacts, and other key information related to the sales process.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| pps_id | int | No | - | Primary Key |
| pps_id_production | int | Yes | - | |
| proposal_number | varchar(50) | No | - | |
| num | bigint | No | - | |
| cod | char(2) | No | - | |
| create_date | datetime | No | - | |
| validity_date | datetime | No | - | |
| finished_date | datetime | Yes | - | |
| status_cla_id | int | No | - | |
| amendments_status_cla | int | Yes | - | |
| led_id | int | Yes | - | |
| finantial_contact | tinyint | No | 0 | |
| finantial_contact_name | varchar(150) | Yes | - | |
| finantial_contact_email | varchar(100) | Yes | - | |
| finantial_contact_phone | varchar(45) | Yes | - | |
| document_contact | tinyint | No | 0 | |
| document_contact_name | varchar(150) | Yes | - | |
| document_contact_email | varchar(100) | Yes | - | |
| document_contact_phone | varchar(45) | Yes | - | |
| commercial_contact_name | varchar(150) | Yes | - | |
| commercial_contact_email | varchar(100) | Yes | - | |
| commercial_contact_phone | varchar(45) | Yes | - | |
| risk_cla_id | int | No | - | |
| immediate_delivery_cla_id | int | No | 0 | |
| contract | text | Yes | - | |
| usr_id_create | int | Yes | - | |
| usr_id_last_update | int | Yes | - | |
| last_update_date | datetime | Yes | - | |
| scheduling_cla_id | int | Yes | - | |
| conta_azul_triggered | tinyint | Yes | - | |
| cluster_name | varchar(15) | Yes | - | |
| conta_azul_amendments | tinyint | Yes | - | |
| signature_contact | tinyint | Yes | 0 | |
| basic_contact | tinyint | Yes | - | |
| signature_cla_id | int | Yes | - | |
| customer_name | varchar(255) | Yes | - | |
| customer_email | varchar(255) | Yes | - | |
| customer_phone | varchar(45) | Yes | - | |
| finished_without_sale_cla_id | int | Yes | - | |
| finished_without_sale_comment | text | Yes | - | |
| backoffice_rejected_comment | text | Yes | - | |
| coparticipation_internal_commission | tinyint | Yes | - | |
| proposal_sent_date | datetime | Yes | - | |
| proposal_sent | tinyint | Yes | - | |
| revenue_amendments | tinyint | Yes | - | |
| totvs_triggered | tinyint | Yes | - | |

#### Example Data

```json
{
  "proposal_sent": null,
  "risk_cla_id": 140,
  "document_contact_name": null,
  "signature_contact": 0,
  "validity_date": [2023, 9, 25, 9, 54, 50],
  "led_id": null,
  "document_contact": 1,
  "num": 1,
  "finantial_contact_name": null,
  "totvs_triggered": null,
  "finished_date": null,
  "usr_id_last_update": 5,
  "proposal_sent_date": null,
  "immediate_delivery_cla_id": 40,
  "proposal_number": "B2309-1A",
  "usr_id_create": 5,
  "amendments_status_cla": 320,
  "signature_cla_id": null,
  "pps_id": 330,
  "commercial_contact_email": null,
  "create_date": [2023, 3, 31, 12, 0],
  "basic_contact": null,
  "finantial_contact": 1,
  "last_update_date": [2023, 9, 15, 10, 14, 11],
  "finished_without_sale_comment": null,
  "cluster_name": "CLUSTER II",
  "backoffice_rejected_comment": null,
  "document_contact_email": null,
  "contract": null,
  "customer_phone": null,
  "finantial_contact_email": null,
  "finished_without_sale_cla_id": null,
  "pps_id_production": null,
  "commercial_contact_phone": null,
  "revenue_amendments": null,
  "document_contact_phone": null,
  "conta_azul_triggered": null,
  "coparticipation_internal_commission": null,
  "customer_email": null,
  "scheduling_cla_id": 462,
  "cod": "A",
  "status_cla_id": 68,
  "finantial_contact_phone": null,
  "customer_name": null,
  "commercial_contact_name": null,
  "conta_azul_amendments": null
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| classifier | Many-to-One | finished_without_sale_cla_id |
| classifier | Many-to-One | amendments_status_cla |
| classifier | Many-to-One | status_cla_id |
| classifier | Many-to-One | risk_cla_id |
| classifier | Many-to-One | scheduling_cla_id |
| classifier | Many-to-One | signature_cla_id |
| classifier | Many-to-One | immediate_delivery_cla_id |
| lead | Many-to-One | led_id |
| proposal | Many-to-One | pps_id_production |
| user | Many-to-One | usr_id_create |
| user | Many-to-One | usr_id_last_update |

## ProposalCommission

### Table: `proposal_commission`

#### Description
The ProposalCommission table stores information about commissions associated with proposals. It tracks commission amounts, recipients, payment details, and other commission-related information.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| pcm_id | int | No | - | Primary Key |
| cmn_type_cla_id | int | No | - | Tipo de comissionado: parceiro, colaborador ou montadora |
| per_id | int | No | - | |
| person_type_cla_id | int | Yes | - | Perfil do Colaborador do Parceiro |
| due_date | datetime | Yes | - | |
| value | decimal | No | - | |
| notes | text | Yes | - | |
| cmt_id | int | Yes | - | Tipo de comissão: comissão, bonus e etc |
| ppd_id | int | No | - | |
| act_id | int | Yes | - | Conta bancária |
| payment_cla_id | int | No | - | Tipo de pagamenot |
| bank_data | varchar(255) | Yes | - | DADOS BANCÁRIOS DO PARCEIRO |
| number_nf | varchar(100) | Yes | - | |

#### Example Data

```json
{
  "ppd_id": 335,
  "person_type_cla_id": null,
  "notes": null,
  "pcm_id": 33,
  "act_id": 224,
  "due_date": null,
  "cmn_type_cla_id": 530,
  "cmt_id": 1,
  "payment_cla_id": 464,
  "number_nf": null,
  "value": 8300.00,
  "per_id": 349,
  "bank_data": null
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| person | Many-to-One | per_id |
| bank_account | Many-to-One | act_id |
| proposal_detail | Many-to-One | ppd_id |
| classifier | Many-to-One | payment_cla_id |
| commission_type | Many-to-One | cmt_id |

## ProposalDetail

### Table: `proposal_detail`

#### Description
The ProposalDetail table stores detailed information about a proposal, including the seller, channel, partner, and purchase order information. It serves as a link between the proposal and its associated details.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| ppd_id | int | No | - | Primary Key |
| pps_id | int | No | - | |
| sel_id | int | No | - | |
| intern_sale_sel_id | int | Yes | - | |
| chn_id | int | No | - | |
| ptn_id | int | Yes | - | |
| usr_id | int | Yes | - | |
| purchase_order_service | varchar(50) | Yes | - | |
| purchase_order_product | varchar(50) | Yes | - | |
| purchase_order_documentation | varchar(50) | Yes | - | |
| internal_comission | decimal | No | 0.00 | |
| intern_sale_additive | int | Yes | - | |
| seller_additive | int | Yes | - | |
| sale_date_additive | datetime | Yes | - | |

#### Example Data

```json
{
  "ppd_id": 328,
  "ptn_id": null,
  "intern_sale_sel_id": null,
  "sale_date_additive": null,
  "purchase_order_service": null,
  "chn_id": 3,
  "seller_additive": null,
  "internal_comission": 35000.00,
  "purchase_order_documentation": null,
  "usr_id": 5,
  "purchase_order_product": null,
  "pps_id": 330,
  "intern_sale_additive": null,
  "sel_id": 53
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| channel | Many-to-One | chn_id |
| seller | Many-to-One | intern_sale_sel_id |
| seller | Many-to-One | intern_sale_additive |
| partner | Many-to-One | ptn_id |
| proposal | Many-to-One | pps_id |
| seller | Many-to-One | sel_id |
| seller | Many-to-One | seller_additive |

## ProposalDetailVehicle

### Table: `proposal_detail_vehicle`

#### Description
The ProposalDetailVehicle table stores information about vehicles associated with a proposal. It contains details about the vehicle model, pricing, discounts, and payment terms.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| pdv_id | int | No | - | Primary Key |
| ppd_id | int | No | - | |
| vhe_id | int | Yes | - | |
| mdl_id | int | No | - | |
| version | varchar(100) | Yes | - | |
| model_year | int | No | - | |
| ppr_id | int | No | - | |
| product_amount_discount | decimal | No | 0.00 | |
| product_percent_discount | decimal | No | 0.00 | |
| product_final_price | decimal | No | 0.00 | |
| product_table_price_tax | decimal | Yes | - | |
| over_price | decimal | No | - | |
| over_price_carbon | decimal | Yes | - | |
| over_price_partner_discount_amount | decimal | No | - | |
| over_price_partner_discount_percent | decimal | No | - | |
| price_discount_amount | decimal | No | - | |
| price_discount_percent | decimal | No | - | |
| total_amount | decimal | No | - | |
| total_tax_amount | decimal | No | - | |
| total_tax_percent | decimal | No | - | |
| standard_term_days | int | No | - | |
| agreed_term_days | int | No | - | |
| spc_id | int | Yes | - | |
| spc_tax | decimal | Yes | - | |
| spc_id_ammendment | int | Yes | - | |
| spc_ammendment_tax | decimal | Yes | - | |
| over_price_carbon_ammendment | decimal | Yes | - | |
| price_discount_amount_ammendment | decimal | Yes | - | |
| over_price_ammendment | decimal | Yes | - | |
| over_price_partner_discount_amount_ammendment | decimal | Yes | - | |

#### Example Data

```json
{
  "product_amount_discount": 0.00,
  "over_price_ammendment": null,
  "spc_id": null,
  "price_discount_amount_ammendment": null,
  "price_discount_percent": 0.00,
  "over_price_partner_discount_percent": 0.00,
  "mdl_id": 21,
  "standard_term_days": 30,
  "spc_ammendment_tax": null,
  "total_tax_amount": 0.00,
  "price_discount_amount": 26213.40,
  "product_final_price": 91000.00,
  "ppd_id": 328,
  "over_price_partner_discount_amount": 0.00,
  "over_price": 0.00,
  "vhe_id": 301,
  "over_price_carbon_ammendment": null,
  "model_year": 2019,
  "product_percent_discount": 0.00,
  "version": "2.0 TFSI",
  "over_price_carbon": null,
  "pdv_id": 312,
  "total_amount": 89276.60,
  "ppr_id": 8713,
  "total_tax_percent": 0.00,
  "spc_tax": null,
  "over_price_partner_discount_amount_ammendment": null,
  "agreed_term_days": 30,
  "product_table_price_tax": null,
  "spc_id_ammendment": null
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| model | Many-to-One | mdl_id |
| price_product | Many-to-One | ppr_id |
| proposal_detail | Many-to-One | ppd_id |
| vehicle | Many-to-One | vhe_id |
| specific_payment_condition | Many-to-One | spc_id |
| specific_payment_condition | Many-to-One | spc_id_ammendment |

## ProposalDetailVehicleItem

### Table: `proposal_detail_vehicle_item`

#### Description
The ProposalDetailVehicleItem table stores information about additional items associated with a vehicle in a proposal. These items can include services, accessories, or other add-ons that are part of the vehicle sale.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| pdvi_id | int | No | - | Primary Key |
| amount_discount | decimal | No | 0.00 | |
| percent_discount | decimal | No | 0.00 | |
| final_price | decimal | No | 0.00 | |
| table_price_tax | decimal | No | - | |
| for_free | tinyint | No | 0 | |
| pdv_id | int | No | - | |
| seller_id | int | No | - | |
| pci_id | int | Yes | - | |
| pim_id | int | Yes | - | |
| amendment | int | No | 0 | |
| immediate_delivery | tinyint | No | 0 | |

#### Example Data

```json
{
  "amendment": 0,
  "amount_discount": 0.00,
  "final_price": 4500.00,
  "pci_id": null,
  "table_price_tax": 0.00,
  "immediate_delivery": false,
  "percent_discount": 0.00,
  "for_free": false,
  "pdvi_id": 1493,
  "seller_id": 53,
  "pdv_id": 312,
  "pim_id": 1531
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| price_item_model | Many-to-One | pim_id |
| price_item | Many-to-One | pci_id |
| proposal_detail_vehicle | Many-to-One | pdv_id |

## ProposalDocument

### Table: `proposal_document`

#### Description
The ProposalDocument table establishes a many-to-many relationship between proposals and documents. It tracks which documents are associated with which proposals.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| pps_id | int | No | - | Primary Key (part of composite key) |
| doc_id | int | No | - | Primary Key (part of composite key) |

#### Example Data

```json
{
  "pps_id": 2907,
  "doc_id": 19
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| document | Many-to-One | doc_id |
| proposal | Many-to-One | pps_id |

## ProposalFup

### Table: `proposal_fup`

#### Description
The ProposalFup table stores follow-up information for proposals. It tracks interactions, communications, and updates related to a proposal throughout its lifecycle.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| pfp_id | int | No | - | Primary Key |
| pps_id | int | No | - | |
| date | datetime | No | - | |
| media_cla_id | int | No | - | |
| person | varchar(150) | No | - | |
| comment | varchar(1000) | Yes | - | |
| fup_type_cla_id | int | Yes | - | |
| usr_id | int | Yes | - | |

#### Example Data

```json
{
  "pfp_id": 3,
  "date": [2024, 3, 28, 11, 44, 2],
  "person": "Antonio",
  "usr_id": null,
  "pps_id": 3056,
  "comment": "Fechou com a VRZ",
  "media_cla_id": 243,
  "fup_type_cla_id": null
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| classifier | Many-to-One | fup_type_cla_id |
| classifier | Many-to-One | media_cla_id |
| proposal | Many-to-One | pps_id |
| user | Many-to-One | usr_id |

## AccessList

### Table: `access_list`

#### Description
The AccessList table stores information about access control lists in the system. It defines groups of permissions that can be assigned to users to control their access to different parts of the application.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| acl_id | int | No | - | Primary Key |
| name | varchar(45) | No | - | |
| mnu_id | int | Yes | - | |

#### Example Data

```json
{
  "mnu_id": 25,
  "name": "IT_ADMINISTRADOR",
  "acl_id": 1
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| menu | Many-to-One | mnu_id |

## AccessListCheckpoint

### Table: `access_list_checkpoint`

#### Description
The AccessListCheckpoint table establishes a many-to-many relationship between access lists and checkpoints. It defines which checkpoints (permissions) are included in which access lists.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| ckp_id | int | No | - | Primary Key (part of composite key) |
| acl_id | int | No | - | Primary Key (part of composite key) |

#### Example Data

```json
{
  "ckp_id": 1,
  "acl_id": 1
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| access_list | Many-to-One | acl_id |
| checkpoint | Many-to-One | ckp_id |

## Checkpoint

### Table: `checkpoint`

#### Description
The Checkpoint table stores information about permission checkpoints in the system. Each checkpoint represents a specific permission or access control point that can be assigned to access lists to control user access to different parts of the application.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| ckp_id | int | No | - | Primary Key |
| name | varchar(45) | No | - | |
| description | varchar(255) | Yes | - | |

#### Example Data

```json
{
  "ckp_id": 1,
  "name": "PROPOSAL.CREATE.OWNER",
  "description": "PERMITE CRIAR UMA NOVA PROPOSTA COM SEU USUÁRIO NO CAMPO EXECUTIVO DE NEGÓCIO"
}
```

#### Relationships

This table has no foreign keys.

## Channel

### Table: `channel`

#### Description
The Channel table stores information about different sales channels through which products and services are offered. It defines the types of sales channels available in the system, such as dealerships, direct sales, etc.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| chn_id | int | No | - | Primary Key |
| name | varchar(45) | No | - | Name of the sales channel |
| active | tinyint | No | 1 | Flag indicating if the channel is active |
| has_partner | tinyint | No | 1 | Flag indicating if the channel can have partners |
| has_internal_sale | tinyint | No | 1 | Flag indicating if the channel can have internal sales |
| has_bonus_bucket | tinyint | Yes | 0 | Flag indicating if the channel has bonus bucket |
| totvs_nature_code | varchar(50) | Yes | - | Integration code with TOTVS ERP system |

#### Example Data

```json
{
  "has_internal_sale": true,
  "has_partner": true,
  "name": "CONCESSIONÁRIA",
  "has_bonus_bucket": false,
  "totvs_nature_code": "31101",
  "active": true,
  "chn_id": 1
}
```

#### Relationships

This table has no foreign keys.

## Brand

### Table: `brand`

#### Description
The Brand table stores information about vehicle brands or manufacturers. It is used to categorize vehicles by their manufacturer.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| brd_id | int | No | - | Primary Key |
| name | varchar(45) | No | - | Name of the brand |
| active | tinyint | No | 1 | Flag indicating if the brand is active |
| ptn_id | int | Yes | - | Foreign key to partner table |

#### Example Data

```json
{
  "ptn_id": null,
  "brd_id": 9,
  "name": "AUDI",
  "active": true
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| partner | Many-to-One | ptn_id |

## CommissionType

### Table: `commission_type`

#### Description
The CommissionType table defines different types of commissions that can be applied to sales. It categorizes commissions by their purpose and characteristics.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| cmt_id | int | No | - | Primary Key |
| name | varchar(255) | No | - | Name of the commission type |
| description | varchar(255) | Yes | - | Description of the commission type |
| default_maximum_value | decimal | Yes | - | Default maximum value for this commission type |
| manufacturer | tinyint | No | 0 | Flag indicating if this commission is for manufacturers |
| overprice | tinyint | No | 0 | Flag indicating if this commission is related to overprice |

#### Example Data

```json
{
  "overprice": 0,
  "cmt_id": 1,
  "default_maximum_value": 1000000.00,
  "name": "COMISSÃO",
  "description": null,
  "manufacturer": 0
}
```

#### Relationships

This table has no foreign keys.

## ContactSimplified

### Table: `contact_simplified`

#### Description
The ContactSimplified table stores simplified contact information for individuals associated with proposals. It provides a lightweight way to store contact details without creating full person records.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| cts_id | int | No | - | Primary Key |
| pps_id | int | Yes | - | Foreign key to proposal table |
| name | varchar(250) | Yes | - | Contact name |
| email | varchar(150) | Yes | - | Contact email address |
| phone | varchar(150) | Yes | - | Contact phone number |
| document | varchar(150) | Yes | - | Contact document number (like ID or CPF) |
| type_cla | int | Yes | - | Foreign key to classifier table for contact type |

#### Example Data

```json
{
  "cts_id": 1,
  "phone": null,
  "type_cla": 482,
  "document": null,
  "name": "RALPH LUIZ CUSTODIO DA COSTA",
  "pps_id": 2820,
  "email": "RALPH.COSTA@ALTAVW.COM.BR"
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| classifier | Many-to-One | type_cla |
| proposal | Many-to-One | pps_id |

## Document

### Table: `document`

#### Description
The Document table stores information about files and documents uploaded to the system. It tracks metadata about documents such as file names, paths, types, and ownership.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| doc_id | int | No | - | Primary Key |
| file_name | varchar(150) | No | - | Original name of the file |
| content_type | varchar(50) | No | - | MIME type of the file |
| description | varchar(255) | Yes | - | Description of the document |
| file_path | varchar(255) | No | - | Path where the file is stored |
| create_date | datetime | No | - | Date when the document was created |
| usr_id | int | No | - | Foreign key to user who uploaded the document |
| type_cla_id | int | No | - | Foreign key to classifier for document type |
| file_hash | text | Yes | - | Hash of the file for integrity verification |

#### Example Data

```json
{
  "file_path": "document_1662743410904.png",
  "content_type": "image/png",
  "file_hash": null,
  "file_name": "BG - Software.png",
  "usr_id": 4,
  "description": null,
  "type_cla_id": 81,
  "create_date": [2022, 9, 9, 14, 10, 11],
  "doc_id": 1
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| classifier | Many-to-One | type_cla_id |
| user | Many-to-One | usr_id |

## Item

### Table: `item`

#### Description
The Item table stores information about different items or services that can be included in proposals. These items represent products or services that can be sold to customers.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| itm_id | int | No | - | Primary Key |
| name | varchar(255) | No | - | Name of the item |
| cod | varchar(45) | Yes | - | Code that references the item in the ERP system |
| seq | int | No | 0 | Sequence number for ordering items in UI |
| for_free | tinyint | No | 0 | Flag indicating if the item is offered for free |
| generic | tinyint | No | 1 | Flag indicating if the item is generic |
| mandatory_cla_id | int | No | - | Foreign key to classifier for mandatory type |
| itt_id | int | No | - | Foreign key to item_type table |
| file | varchar(255) | Yes | - | File associated with the item |
| icon | varchar(255) | Yes | - | Icon for the item |
| description | text | Yes | - | Description of the item |
| hyperlink | text | Yes | - | Hyperlink related to the item |
| responsability_cla_id | int | No | - | Foreign key to classifier for responsibility |
| term | int | No | - | Term in days |
| term_work_day | tinyint | No | - | Flag indicating if term is in work days |
| highlight | tinyint | No | - | Flag indicating if item should be highlighted |
| flagPurchases | tinyint | Yes | 0 | Flag for purchases |
| description_purchases | varchar(255) | Yes | - | Description for purchases |
| enable_export_code | tinyint | Yes | - | Flag for enabling export code |
| export_code_conta_azul | varchar(45) | Yes | - | Export code for Conta Azul integration |
| factory_settings | tinyint | Yes | - | Flag for factory settings |
| custom_field_jira | int | Yes | - | Custom field ID for Jira integration |
| flag_production | tinyint | Yes | - | Flag for production |
| jira_integration_cla_id | int | Yes | - | Foreign key to classifier for Jira integration |
| name_commercial | varchar(255) | Yes | - | Commercial name of the item |
| description_commercial | text | Yes | - | Commercial description of the item |
| group_commercial_cla_id | int | Yes | - | Foreign key to classifier for commercial group |
| item_order | int | Yes | - | Order of the item |

#### Example Data

```json
{
  "flagPurchases": false,
  "jira_integration_cla_id": 495,
  "enable_export_code": 0,
  "icon": null,
  "description": null,
  "description_purchases": "AUTORIZAÇÃO DE BLINDAGEM",
  "item_order": null,
  "highlight": 0,
  "file": null,
  "export_code_conta_azul": null,
  "itm_id": 3,
  "term": 0,
  "factory_settings": null,
  "flag_production": 0,
  "for_free": true,
  "seq": 2,
  "mandatory_cla_id": 23,
  "hyperlink": null,
  "name_commercial": "Autorização de Blindagem (Exército)",
  "responsability_cla_id": 250,
  "generic": true,
  "term_work_day": 0,
  "itt_id": 3,
  "name": "AUTORIZAÇÃO DE BLINDAGEM",
  "cod": null,
  "group_commercial_cla_id": 515,
  "custom_field_jira": 11332,
  "description_commercial": null
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| classifier | Many-to-One | mandatory_cla_id |
| classifier | Many-to-One | group_commercial_cla_id |
| item_type | Many-to-One | itt_id |
| classifier | Many-to-One | jira_integration_cla_id |
| classifier | Many-to-One | responsability_cla_id |

## ItemModel

### Table: `item_model`

#### Description
The ItemModel table associates items with vehicle models, defining which items are available for which vehicle models and in which model years. This allows for specific items to be offered for specific vehicle models.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| imd_id | int | No | - | Primary Key |
| model_year_start | int | No | 0 | Starting model year for which this item is available |
| model_year_end | int | No | 9999 | Ending model year for which this item is available |
| itm_id | int | No | - | Foreign key to item table |
| mdl_id | int | No | - | Foreign key to model table |
| additional_term | int | Yes | - | Additional term in days |
| factory_settings | tinyint | Yes | - | Flag for factory settings |
| mandatory_cla_id | int | Yes | - | Foreign key to classifier for mandatory type |

#### Example Data

```json
{
  "imd_id": 4,
  "mandatory_cla_id": 1410,
  "model_year_end": 2200,
  "mdl_id": 119,
  "itm_id": 41,
  "factory_settings": 0,
  "additional_term": 7,
  "model_year_start": 1900
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| item | Many-to-One | itm_id |
| model | Many-to-One | mdl_id |
| classifier | Many-to-One | mandatory_cla_id |

## Job

### Table: `job`

#### Description
The Job table stores information about job positions or roles within the organization. It defines the hierarchy of positions and is used for authorization and workflow purposes.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| job_id | int | No | - | Primary Key |
| name | varchar(150) | No | - | Name of the job position |
| level | tinyint | No | - | Hierarchical level of the position |

#### Example Data

```json
{
  "level": 1,
  "job_id": 1,
  "name": "CEO"
}
```

#### Relationships

This table has no foreign keys.

## Model

### Table: `model`

#### Description
The Model table stores information about vehicle models. It categorizes vehicles by their model, brand, body type, and other characteristics.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| mdl_id | int | No | - | Primary Key |
| name | varchar(45) | No | - | Name of the model |
| active | tinyint | No | 1 | Flag indicating if the model is active |
| brd_id | int | No | - | Foreign key to brand table |
| body_type_cla_id | int | No | - | Foreign key to classifier for body type (SUV/HATCH/SEDAN) |
| category_cla_id | int | No | - | Foreign key to classifier for category (STANDARD/PREMIUM) |
| type_cla_id | int | No | - | Foreign key to classifier for type |
| cod_fipe | varchar(15) | Yes | - | FIPE code (Brazilian vehicle pricing reference) |

#### Example Data

```json
{
  "mdl_id": 18,
  "brd_id": 9,
  "name": "A1 HATCH",
  "category_cla_id": 102,
  "cod_fipe": null,
  "active": true,
  "type_cla_id": 95,
  "body_type_cla_id": 193
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| classifier | Many-to-One | body_type_cla_id |
| classifier | Many-to-One | category_cla_id |
| brand | Many-to-One | brd_id |
| classifier | Many-to-One | type_cla_id |

## Partner

### Table: `partner`

#### Description
The Partner table stores information about business partners that collaborate with the company. These can be dealerships, service providers, or other organizations that have a business relationship with the company.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| ptn_id | int | No | - | Primary Key |
| entity_per_id | int | No | - | Foreign key to person table for partner entity data |
| ptg_id | int | Yes | - | Foreign key to partner_group table |
| chn_id | int | No | - | Foreign key to channel table |
| situation_cla | int | No | - | Foreign key to classifier for partner situation |
| additional_term | int | No | 0 | Additional term in days |
| is_assistance | tinyint | No | 0 | Flag indicating if partner provides assistance |
| due_cr | date | Yes | - | Due date for CR (Certificate of Regularity) |

#### Example Data

```json
{
  "entity_per_id": 72,
  "is_assistance": false,
  "ptn_id": 10,
  "situation_cla": 210,
  "ptg_id": 14,
  "additional_term": 0,
  "chn_id": 7,
  "due_cr": null
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| channel | Many-to-One | chn_id |
| partner_group | Many-to-One | ptg_id |
| person | Many-to-One | entity_per_id |
| classifier | Many-to-One | situation_cla |

## PartnerBrand

### Table: `partner_brand`

#### Description
The PartnerBrand table establishes a many-to-many relationship between partners and brands. It defines which brands each partner is authorized to work with.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| ptn_id | int | No | - | Primary Key (part of composite key) |
| brd_id | int | No | - | Primary Key (part of composite key) |

#### Example Data

```json
{
  "ptn_id": 10,
  "brd_id": 9
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| partner | Many-to-One | ptn_id |
| brand | Many-to-One | brd_id |

## PaymentMethod

### Table: `payment_method`

#### Description
The PaymentMethod table defines the different methods of payment that can be used in transactions, such as credit card, bank transfer, etc.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| pym_id | int | No | - | Primary Key |
| name | varchar(45) | No | - | Name of the payment method |
| active | tinyint | No | 1 | Flag indicating if the payment method is active |

#### Example Data

```json
{
  "name": "CARTÃO DE CRÉDITO",
  "active": true,
  "pym_id": 1
}
```

#### Relationships

This table has no foreign keys.

## PaymentRule

### Table: `payment_rule`

#### Description
The PaymentRule table defines rules for payments, including installment options, interest rates, and other payment conditions.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| pyr_id | int | No | - | Primary Key |
| name | varchar(45) | No | - | Name of the payment rule |
| installments | int | No | - | Number of installments |
| tax | decimal | No | - | Tax or interest rate |
| active | tinyint | No | 1 | Flag indicating if the rule is active |
| pre_approved | tinyint | No | 0 | Flag indicating if pre-approval is required |
| pym_id | int | No | - | Foreign key to payment_method table |
| apply_tax_proposal | tinyint | No | 1 | Flag indicating if tax applies to proposal |
| simple_interest | tinyint | No | 1 | Flag indicating if interest is simple |
| crm_blindagem | tinyint | No | 1 | Flag for CRM blindagem |
| technical_assistance | tinyint | No | 1 | Flag for technical assistance |
| totvs_payment_code | varchar(50) | Yes | - | Integration code with TOTVS ERP system |

#### Example Data

```json
{
  "simple_interest": true,
  "installments": 2,
  "pre_approved": true,
  "name": "2X",
  "totvs_payment_code": null,
  "active": true,
  "crm_blindagem": true,
  "tax": 6.6500,
  "pyr_id": 16,
  "pym_id": 2,
  "apply_tax_proposal": true,
  "technical_assistance": false
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| payment_method | Many-to-One | pym_id |

## PaymentRuleInstallment

### Table: `payment_rule_installment`

#### Description
The PaymentRuleInstallment table defines the installment structure for payment rules, specifying when each installment is due.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| pyi_id | int | No | - | Primary Key |
| installments | int | No | - | Number of installments |
| payment | int | No | - | Payment day or period |

#### Example Data

```json
{
  "pyi_id": 29,
  "installments": 1,
  "payment": 0
}
```

#### Relationships

This table has no foreign keys.

## PriceItem

### Table: `price_item`

#### Description
The PriceItem table stores pricing information for items in a specific price list. It defines how much each item costs in a particular price list.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| pci_id | int | No | - | Primary Key |
| price | decimal | No | - | Price of the item |
| itm_id | int | No | - | Foreign key to item table |
| prl_id | int | No | - | Foreign key to price_list table |
| for_free | tinyint | No | 0 | Flag indicating if the item is offered for free |
| usr_id_create | int | Yes | - | Foreign key to user who created the record |
| usr_id_delete | int | Yes | - | Foreign key to user who deleted the record |
| create_date | datetime | Yes | - | Date when the record was created |
| delete_date | datetime | Yes | - | Date when the record was deleted |

#### Example Data

```json
{
  "usr_id_create": null,
  "usr_id_delete": null,
  "price": 750.00,
  "pci_id": 628,
  "prl_id": 42,
  "delete_date": null,
  "itm_id": 3,
  "for_free": false,
  "create_date": null
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| item | Many-to-One | itm_id |
| price_list | Many-to-One | prl_id |
| user | Many-to-One | usr_id_create |
| user | Many-to-One | usr_id_delete |

## PriceItemModel

### Table: `price_item_model`

#### Description
The PriceItemModel table stores pricing information for items specific to vehicle models. It allows for different pricing of the same item for different vehicle models.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| pim_id | int | No | - | Primary Key |
| price | decimal | No | 0.00 | Price of the item for this model |
| all_models | tinyint | No | 0 | Flag indicating if this price applies to all models |
| all_brands | tinyint | No | 0 | Flag indicating if this price applies to all brands |
| prl_id | int | No | - | Foreign key to price_list table |
| imd_id | int | Yes | - | Foreign key to item_model table |
| brd_id | int | Yes | - | Foreign key to brand table |
| itm_id | int | No | - | Foreign key to item table |
| for_free | tinyint | No | 0 | Flag indicating if the item is offered for free |
| usr_id_create | int | Yes | - | Foreign key to user who created the record |
| usr_id_delete | int | Yes | - | Foreign key to user who deleted the record |
| create_date | datetime | Yes | - | Date when the record was created |
| delete_date | datetime | Yes | - | Date when the record was deleted |

#### Example Data

```json
{
  "imd_id": null,
  "usr_id_delete": null,
  "all_models": true,
  "prl_id": 42,
  "usr_id_create": null,
  "price": 2500.00,
  "brd_id": null,
  "delete_date": null,
  "all_brands": true,
  "itm_id": 34,
  "for_free": false,
  "create_date": null,
  "pim_id": 1305
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| brand | Many-to-One | brd_id |
| item | Many-to-One | itm_id |
| user | Many-to-One | usr_id_create |
| user | Many-to-One | usr_id_delete |
| item_model | Many-to-One | imd_id |
| price_list | Many-to-One | prl_id |

## PriceList

### Table: `price_list`

#### Description
The PriceList table defines different price lists that can be used in the system. Each price list has a validity period and is associated with a specific sales channel.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| prl_id | int | No | - | Primary Key |
| name | varchar(45) | No | - | Name of the price list |
| start_date | date | No | - | Start date of the price list validity |
| end_date | date | No | - | End date of the price list validity |
| chn_id | int | No | - | Foreign key to channel table |
| all_partners | tinyint | No | 0 | Flag indicating if this price list applies to all partners |

#### Example Data

```json
{
  "end_date": 1711854000000,
  "all_partners": true,
  "prl_id": 42,
  "name": "2023.V1 CSS",
  "chn_id": 1,
  "start_date": 1682478000000
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| channel | Many-to-One | chn_id |

## PriceListPartner

### Table: `price_list_partner`

#### Description
The PriceListPartner table establishes a many-to-many relationship between price lists and partners. It defines which price lists are available to which partners.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| ptn_id | int | No | - | Primary Key (part of composite key) |
| prl_id | int | No | - | Primary Key (part of composite key) |

#### Example Data

```json
{
  "ptn_id": 40,
  "prl_id": 44
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| partner | Many-to-One | ptn_id |
| price_list | Many-to-One | prl_id |

## PriceProduct

### Table: `price_product`

#### Description
The PriceProduct table stores pricing information for products in a specific price list. It defines how much each product costs in a particular price list.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| ppr_id | int | No | - | Primary Key |
| price | decimal | No | - | Price of the product |
| prl_id | int | No | - | Foreign key to price_list table |
| prm_id | int | No | - | Foreign key to product_model table |
| usr_id_create | int | Yes | - | Foreign key to user who created the record |
| usr_id_delete | int | Yes | - | Foreign key to user who deleted the record |
| create_date | datetime | Yes | - | Date when the record was created |
| delete_date | datetime | Yes | - | Date when the record was deleted |

#### Example Data

```json
{
  "usr_id_create": null,
  "usr_id_delete": null,
  "ppr_id": 8361,
  "price": 64500.00,
  "prl_id": 42,
  "prm_id": 229,
  "delete_date": null,
  "create_date": null
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| user | Many-to-One | usr_id_create |
| user | Many-to-One | usr_id_delete |
| price_list | Many-to-One | prl_id |
| product_model | Many-to-One | prm_id |

## Product

### Table: `product`

#### Description
The Product table stores information about products offered by the company. These are the main products that can be sold to customers.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| prd_id | int | No | - | Primary Key |
| name | varchar(45) | No | - | Name of the product |
| active | tinyint | No | 1 | Flag indicating if the product is active |
| proposal_expiration_days | int | No | 10 | Number of days until a proposal for this product expires |
| product_description | varchar(255) | Yes | - | Description of the product |
| template_path | varchar(255) | Yes | - | Path to the product template |
| template_path_amendments | varchar(255) | Yes | - | Path to the amendments template |
| template_pre_proposal | varchar(255) | Yes | - | Path to the pre-proposal template |
| glass | text | Yes | - | Glass specifications |
| name_commercial | varchar(255) | Yes | - | Commercial name of the product |
| description_commercial | text | Yes | - | Commercial description of the product |
| glass_specification_commercial | text | Yes | - | Commercial glass specifications |
| opaque_specification_commercial | text | Yes | - | Commercial opaque specifications |

#### Example Data

```json
{
  "glass": null,
  "name_commercial": "CARBON BLACK",
  "active": false,
  "template_path": "/Templates_PDF_V7/Proposta_Comercial.pdf",
  "prd_id": 1,
  "template_path_amendments": "/",
  "opaque_specification_commercial": "Placa de fibras de aramida Kevlar®<sup>1</sup>",
  "glass_specification_commercial": "CARBON Black\nUma experiência única de leveza, proteção, o máximo conforto óptico e a mais alta transparência do mercado.",
  "template_pre_proposal": "/Pre_Proposta/pre_Proposta_Comercial.pdf",
  "name": "CARBON BLACK",
  "proposal_expiration_days": 10,
  "product_description": "OS MATERIAIS INSTALADOS SEGUEM AS ESPECIFICAÇÕES DAS NORMAS ABNT 15.000 / PADRÃO NIJ - NATIONAL INSTITUTE OF JUSTICE, EUA E NORMA VPAM BR 2009, EUROPA PARA PROTEÇÃO NÍVEL III-A\n",
  "description_commercial": "Leveza, a máxima proteção balística e o prazer de dirigir com segurança em todos os seus caminhos."
}
```

#### Relationships

This table has no foreign keys.

## ProductModel

### Table: `product_model`

#### Description
The ProductModel table associates products with vehicle models, defining which products are available for which vehicle models and in which model years. This allows for specific products to be offered for specific vehicle models.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| prm_id | int | No | - | Primary Key |
| has_project | tinyint | No | 1 | Flag indicating if this product model has a project |
| model_year_start | int | No | - | Starting model year for which this product is available |
| model_year_end | int | No | - | Ending model year for which this product is available |
| manufacture_days | int | No | 30 | Number of days for manufacturing |
| prd_id | int | No | - | Foreign key to product table |
| mdl_id | int | No | - | Foreign key to model table |
| customer_days | int | No | - | Number of days for customer delivery |

#### Example Data

```json
{
  "model_year_end": 2023,
  "mdl_id": 284,
  "prm_id": 23,
  "customer_days": 22,
  "manufacture_days": 22,
  "prd_id": 1,
  "has_project": true,
  "model_year_start": 2022
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| model | Many-to-One | mdl_id |
| product | Many-to-One | prd_id |

## ProductModelBonus

### Table: `product_model_bonus`

#### Description
The ProductModelBonus table defines bonus values for specific product models and brands. It allows for setting different bonus amounts based on payment types.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| pmb_id | int | No | - | Primary Key |
| brd_id | int | No | - | Foreign key to brand table |
| prm_id | int | No | - | Foreign key to product_model table |
| bonus_value | decimal | Yes | - | Bonus value amount |
| is_bonus_type_percentage | tinyint | Yes | 0 | Flag indicating if bonus is a percentage |
| cla_payment_type | int | Yes | - | Foreign key to classifier for payment type |

#### Example Data

```json
{
  "brd_id": 27,
  "prm_id": 561,
  "is_bonus_type_percentage": false,
  "pmb_id": 3,
  "cla_payment_type": 464,
  "bonus_value": 6000.00
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| classifier | Many-to-One | cla_payment_type |
| brand | Many-to-One | brd_id |
| product_model | Many-to-One | prm_id |

## ProposalApprovalRule

### Table: `proposal_approval_rule`

#### Description
The ProposalApprovalRule table defines rules for proposal approval based on job positions. It specifies the maximum value that a person in a specific job position can approve for a proposal.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| par_id | int | No | - | Primary Key (part of composite key) |
| value | decimal | No | - | Maximum value that can be approved |
| job_id | int | No | - | Primary Key (part of composite key), Foreign key to job table |
| immediate_delivery | tinyint | Yes | - | Flag indicating if immediate delivery is allowed |

#### Example Data

```json
{
  "par_id": 1,
  "job_id": 1,
  "immediate_delivery": 1,
  "value": 1000000.00
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| job | Many-to-One | job_id |

## Seller

### Table: `seller`

#### Description
The Seller table stores information about sellers or sales representatives. These are individuals who sell products and services to customers.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| sel_id | int | No | - | Primary Key |
| per_id | int | No | - | Foreign key to person table |
| job_id | int | No | - | Foreign key to job table |
| active | tinyint | Yes | - | Flag indicating if the seller is active |
| technical_assistance | tinyint | Yes | 0 | Flag indicating if the seller provides technical assistance |
| img_seller | mediumblob | Yes | - | Image of the seller |

#### Example Data

```json
{
  "img_seller": null,
  "job_id": 1,
  "active": 0,
  "per_id": 1,
  "sel_id": 1,
  "technical_assistance": true
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| job | Many-to-One | job_id |
| person | Many-to-One | per_id |

## SellerPartner

### Table: `seller_partner`

#### Description
The SellerPartner table establishes a many-to-many relationship between sellers and partners. It defines which sellers are associated with which partners.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| sel_id | int | No | - | Primary Key (part of composite key) |
| ptn_id | int | No | - | Primary Key (part of composite key) |

#### Example Data

```json
{
  "ptn_id": 10,
  "sel_id": 69
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| partner | Many-to-One | ptn_id |
| seller | Many-to-One | sel_id |

## SpecificPaymentCondition

### Table: `specific_payment_condition`

#### Description
The SpecificPaymentCondition table defines specific payment conditions that can be applied to proposals. It specifies details like tax rates, maximum installments, and other payment terms.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| spc_id | int | No | - | Primary Key |
| name | varchar(255) | Yes | - | Name of the payment condition |
| tax | decimal | No | - | Tax or interest rate |
| active | tinyint | Yes | - | Flag indicating if the condition is active |
| armor | tinyint | No | 0 | Flag for armor products |
| amendments | tinyint | No | 0 | Flag for amendments |
| technical_assistance | tinyint | No | 0 | Flag for technical assistance |
| description | varchar(255) | No | - | Description of the payment condition |
| maxIntallments | int | No | - | Maximum number of installments |
| immediateDelivery | tinyint | Yes | - | Flag for immediate delivery |
| max_dias_para_frente_data_pactuada | varchar(3) | Yes | - | Maximum days ahead of agreed date |
| max_qtde_dias_cla_id | int | Yes | - | Foreign key to classifier for maximum days |
| percent_min_primeira_parcela | varchar(3) | Yes | - | Minimum percentage for first installment |
| max_intervalo_parcelas | varchar(3) | Yes | - | Maximum interval between installments |
| tipo_max_intervalo_parcelas | varchar(1) | Yes | - | Type of maximum interval between installments |
| usr_id_create | int | Yes | - | Foreign key to user who created the record |
| create_date | datetime | Yes | - | Date when the record was created |
| usr_id_delete | int | Yes | - | Foreign key to user who deleted the record |
| delete_date | datetime | Yes | - | Date when the record was deleted |

#### Example Data

```json
{
  "usr_id_delete": 12,
  "spc_id": 1,
  "max_dias_para_frente_data_pactuada": "7",
  "active": true,
  "amendments": true,
  "description": "PAGAMENTO EM ATÉ 05 DIAS UTEIS A PARTIR DA DATA DE ABERTURA DA OS",
  "tax": 0.0000,
  "technical_assistance": true,
  "maxIntallments": 1,
  "usr_id_create": null,
  "armor": true,
  "max_qtde_dias_cla_id": null,
  "name": "A VISTA (01 PARCELA TED, PIX OU BOLETO)",
  "immediateDelivery": 0,
  "percent_min_primeira_parcela": null,
  "delete_date": [2024, 8, 22, 15, 42, 34],
  "tipo_max_intervalo_parcelas": null,
  "create_date": null,
  "max_intervalo_parcelas": null
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| classifier | Many-to-One | max_qtde_dias_cla_id |
| user | Many-to-One | usr_id_create |
| user | Many-to-One | usr_id_delete |

## SpecificPaymentEvent

### Table: `specific_payment_event`

#### Description
The SpecificPaymentEvent table associates specific payment conditions with events. It defines which payment conditions are available for which events.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| spe_id | int | No | - | Primary Key |
| spc_id | int | No | - | Foreign key to specific_payment_condition table |
| cla_id | int | No | - | Foreign key to classifier for event type |

#### Example Data

```json
{
  "spc_id": 13,
  "spe_id": 48,
  "cla_id": 261
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| classifier | Many-to-One | cla_id |
| specific_payment_condition | Many-to-One | spc_id |

## SpecificPaymentMethod

### Table: `specific_payment_method`

#### Description
The SpecificPaymentMethod table associates specific payment conditions with payment methods. It defines which payment methods are available for which payment conditions.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| spm_id | int | No | - | Primary Key |
| pym_id | int | No | - | Foreign key to payment_method table |
| spc_id | int | No | - | Foreign key to specific_payment_condition table |
| meio_pagamento_etapa_cla_id | int | Yes | - | Foreign key to classifier for payment method stage |

#### Example Data

```json
{
  "spm_id": 2,
  "spc_id": 1,
  "meio_pagamento_etapa_cla_id": 490,
  "pym_id": 3
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| classifier | Many-to-One | meio_pagamento_etapa_cla_id |
| payment_method | Many-to-One | pym_id |
| specific_payment_condition | Many-to-One | spc_id |

## SpecificPaymentRule

### Table: `specific_payment_rule`

#### Description
The SpecificPaymentRule table associates specific payment methods with payment rules. It defines which payment rules are available for which payment methods.

#### Fields

| Field Name | Type | Nullable | Default | Description |
|------------|------|----------|---------|-------------|
| spr_id | int | No | - | Primary Key |
| spm_id | int | No | - | Foreign key to specific_payment_method table |
| pyr_id | int | No | - | Foreign key to payment_rule table |

#### Example Data

```json
{
  "spm_id": 2,
  "spr_id": 2,
  "pyr_id": 26
}
```

#### Relationships

| Related Table | Relationship Type | Foreign Key |
|---------------|------------------|-------------|
| payment_rule | Many-to-One | pyr_id |
| specific_payment_method | Many-to-One | spm_id |
