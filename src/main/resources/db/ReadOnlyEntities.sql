-- carbon.channel definition

CREATE TABLE `channel` (
  `chn_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `has_partner` tinyint(1) NOT NULL DEFAULT '1',
  `has_internal_sale` tinyint(1) NOT NULL DEFAULT '1',
  `has_bonus_bucket` tinyint(1) DEFAULT '0',
  `totvs_nature_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`chn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;


-- carbon.commission_type definition

CREATE TABLE `commission_type` (
  `cmt_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `default_maximum_value` decimal(13,2) DEFAULT NULL,
  `manufacturer` tinyint NOT NULL DEFAULT '0',
  `overprice` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`cmt_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;


-- carbon.payment_method definition

CREATE TABLE `payment_method` (
  `pym_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'Ativa ou desativa a forma de pagamento para aparecer na tela',
  PRIMARY KEY (`pym_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;


-- carbon.payment_rule_installment definition

CREATE TABLE `payment_rule_installment` (
  `pyi_id` int NOT NULL AUTO_INCREMENT,
  `installments` int NOT NULL,
  `payment` int NOT NULL,
  PRIMARY KEY (`pyi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3;


-- carbon.payment_rule definition

CREATE TABLE `payment_rule` (
  `pyr_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `installments` int NOT NULL,
  `tax` decimal(8,4) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `pre_approved` tinyint(1) NOT NULL DEFAULT '0',
  `pym_id` int NOT NULL,
  `apply_tax_proposal` tinyint(1) NOT NULL DEFAULT '1',
  `simple_interest` tinyint(1) NOT NULL DEFAULT '1',
  `crm_blindagem` tinyint(1) NOT NULL DEFAULT '1',
  `technical_assistance` tinyint(1) NOT NULL DEFAULT '1',
  `totvs_payment_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pyr_id`),
  UNIQUE KEY `name_UNIQUE` (`name`,`pym_id`),
  KEY `fk_payment_rule_payment_method1_idx` (`pym_id`),
  CONSTRAINT `fk_payment_rule_payment_method1` FOREIGN KEY (`pym_id`) REFERENCES `payment_method` (`pym_id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb3;


-- carbon.price_list definition

CREATE TABLE `price_list` (
  `prl_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `chn_id` int NOT NULL,
  `all_partners` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`prl_id`),
  KEY `fk_price_list_channel1_idx` (`chn_id`),
  CONSTRAINT `fk_price_list_channel1` FOREIGN KEY (`chn_id`) REFERENCES `channel` (`chn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8mb3;


-- carbon.brand definition

CREATE TABLE `brand` (
  `brd_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `ptn_id` int DEFAULT NULL COMMENT 'FK de Partner',
  PRIMARY KEY (`brd_id`),
  UNIQUE KEY `ukName` (`name`),
  KEY `fk_brand_partner` (`ptn_id`),
  CONSTRAINT `fk_brand_partner` FOREIGN KEY (`ptn_id`) REFERENCES `partner` (`ptn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb3;


-- carbon.contact_simplified definition

CREATE TABLE `contact_simplified` (
  `cts_id` int NOT NULL AUTO_INCREMENT,
  `pps_id` int DEFAULT NULL,
  `name` varchar(250) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `phone` varchar(150) DEFAULT NULL,
  `document` varchar(150) DEFAULT NULL,
  `type_cla` int DEFAULT NULL,
  PRIMARY KEY (`cts_id`),
  KEY `fk_pps_id_idx` (`pps_id`),
  KEY `fk_cla_id_idx` (`type_cla`),
  CONSTRAINT `fk_cla_id` FOREIGN KEY (`type_cla`) REFERENCES `classifier` (`cla_id`),
  CONSTRAINT `fk_pps_id` FOREIGN KEY (`pps_id`) REFERENCES `proposal` (`pps_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48693 DEFAULT CHARSET=utf8mb3;


-- carbon.document definition

CREATE TABLE `document` (
  `doc_id` int NOT NULL AUTO_INCREMENT,
  `file_name` varchar(150) NOT NULL,
  `content_type` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `usr_id` int NOT NULL,
  `type_cla_id` int NOT NULL,
  `file_hash` text,
  PRIMARY KEY (`doc_id`),
  KEY `fk_document_user_idx` (`usr_id`),
  KEY `fk_document_classifier1_idx` (`type_cla_id`),
  CONSTRAINT `fk_document_classifier1` FOREIGN KEY (`type_cla_id`) REFERENCES `classifier` (`cla_id`),
  CONSTRAINT `fk_document_user` FOREIGN KEY (`usr_id`) REFERENCES `user` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39510 DEFAULT CHARSET=utf8mb3;


-- carbon.item definition

CREATE TABLE `item` (
  `itm_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT 'Nome do item',
  `cod` varchar(45) DEFAULT NULL COMMENT 'Campo para possivelmente guardar o código que referência o item no ERP',
  `seq` int NOT NULL DEFAULT '0' COMMENT 'Número que será usado para ordenar o item nas telas',
  `for_free` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Flag que marca o item como sendo cortesia',
  `generic` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'Flag que marca o item como sendo item genérico',
  `mandatory_cla_id` int NOT NULL COMMENT 'Classificação do item que determina qual o tipo de obrigatoriedade',
  `itt_id` int NOT NULL,
  `file` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `description` text,
  `hyperlink` text,
  `responsability_cla_id` int NOT NULL,
  `term` int NOT NULL,
  `term_work_day` tinyint NOT NULL,
  `highlight` tinyint NOT NULL,
  `flagPurchases` tinyint(1) DEFAULT '0',
  `description_purchases` varchar(255) DEFAULT NULL,
  `enable_export_code` tinyint DEFAULT NULL,
  `export_code_conta_azul` varchar(45) DEFAULT NULL,
  `factory_settings` tinyint DEFAULT NULL,
  `custom_field_jira` int DEFAULT NULL,
  `flag_production` tinyint DEFAULT NULL,
  `jira_integration_cla_id` int DEFAULT NULL,
  `name_commercial` varchar(255) DEFAULT NULL,
  `description_commercial` text,
  `group_commercial_cla_id` int DEFAULT NULL,
  `item_order` int DEFAULT NULL,
  PRIMARY KEY (`itm_id`),
  KEY `fk_item_item_type_idx` (`itt_id`),
  KEY `fk_item_classifier1_idx` (`mandatory_cla_id`),
  KEY `fk_item_responsability_idx` (`responsability_cla_id`),
  KEY `fk_item_jira_integration` (`jira_integration_cla_id`),
  KEY `fk_item_group_commercial_idx` (`group_commercial_cla_id`),
  CONSTRAINT `fk_item_classifier1` FOREIGN KEY (`mandatory_cla_id`) REFERENCES `classifier` (`cla_id`),
  CONSTRAINT `fk_item_group_commercial_cla_id` FOREIGN KEY (`group_commercial_cla_id`) REFERENCES `classifier` (`cla_id`),
  CONSTRAINT `fk_item_item_type` FOREIGN KEY (`itt_id`) REFERENCES `item_type` (`itt_id`),
  CONSTRAINT `fk_item_jira_integration` FOREIGN KEY (`jira_integration_cla_id`) REFERENCES `classifier` (`cla_id`),
  CONSTRAINT `fk_item_reponsability` FOREIGN KEY (`responsability_cla_id`) REFERENCES `classifier` (`cla_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb3;


-- carbon.item_model definition

CREATE TABLE `item_model` (
  `imd_id` int NOT NULL AUTO_INCREMENT,
  `model_year_start` int NOT NULL DEFAULT '0',
  `model_year_end` int NOT NULL DEFAULT '9999',
  `itm_id` int NOT NULL,
  `mdl_id` int NOT NULL,
  `additional_term` int DEFAULT NULL,
  `factory_settings` tinyint DEFAULT NULL,
  `mandatory_cla_id` int DEFAULT NULL,
  PRIMARY KEY (`imd_id`),
  KEY `fk_item_vehicle_model_item1_idx` (`itm_id`),
  KEY `fk_item_vehicle_model_model1_idx` (`mdl_id`),
  KEY `mandatory_cla_id` (`mandatory_cla_id`),
  CONSTRAINT `fk_item_vehicle_model_item1` FOREIGN KEY (`itm_id`) REFERENCES `item` (`itm_id`),
  CONSTRAINT `fk_item_vehicle_model_model1` FOREIGN KEY (`mdl_id`) REFERENCES `model` (`mdl_id`),
  CONSTRAINT `item_model_ibfk_1` FOREIGN KEY (`mandatory_cla_id`) REFERENCES `classifier` (`cla_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12734 DEFAULT CHARSET=utf8mb3;


-- carbon.partner definition

CREATE TABLE `partner` (
  `ptn_id` int NOT NULL AUTO_INCREMENT,
  `entity_per_id` int NOT NULL COMMENT 'Dado de cadastro do parceiro, seja PF ou PJ',
  `ptg_id` int DEFAULT NULL,
  `chn_id` int NOT NULL,
  `situation_cla` int NOT NULL,
  `additional_term` int NOT NULL DEFAULT '0',
  `is_assistance` tinyint(1) NOT NULL DEFAULT '0',
  `due_cr` date DEFAULT NULL,
  PRIMARY KEY (`ptn_id`),
  KEY `fk_partner_person1_idx` (`entity_per_id`),
  KEY `fk_partner_partner_group1_idx` (`ptg_id`),
  KEY `fk_partner_channel1_idx` (`chn_id`),
  KEY `fk_partiner_situation_idx` (`situation_cla`),
  CONSTRAINT `fk_partner_channel1` FOREIGN KEY (`chn_id`) REFERENCES `channel` (`chn_id`),
  CONSTRAINT `fk_partner_partner_group1` FOREIGN KEY (`ptg_id`) REFERENCES `partner_group` (`ptg_id`),
  CONSTRAINT `fk_partner_person1` FOREIGN KEY (`entity_per_id`) REFERENCES `person` (`per_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2307 DEFAULT CHARSET=utf8mb3;


-- carbon.partner_brand definition

CREATE TABLE `partner_brand` (
  `ptn_id` int NOT NULL,
  `brd_id` int NOT NULL,
  PRIMARY KEY (`ptn_id`,`brd_id`),
  KEY `fk_partner_brand_brand1_idx` (`brd_id`),
  KEY `fk_partner_brand_partner1_idx` (`ptn_id`),
  CONSTRAINT `fk_partner_brand_brand1` FOREIGN KEY (`brd_id`) REFERENCES `brand` (`brd_id`),
  CONSTRAINT `fk_partner_brand_partner1` FOREIGN KEY (`ptn_id`) REFERENCES `partner` (`ptn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- carbon.price_item definition

CREATE TABLE `price_item` (
  `pci_id` int NOT NULL AUTO_INCREMENT,
  `price` decimal(13,2) NOT NULL,
  `itm_id` int NOT NULL,
  `prl_id` int NOT NULL,
  `for_free` tinyint(1) NOT NULL DEFAULT '0',
  `usr_id_create` int DEFAULT NULL,
  `usr_id_delete` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`pci_id`),
  UNIQUE KEY `ukPriceItemVersion` (`itm_id`,`prl_id`,`delete_date`),
  KEY `fk_item_price_list_item1_idx` (`itm_id`),
  KEY `fk_item_price_price_list1_idx` (`prl_id`),
  KEY `fk_price_item_usr_id_create_idx` (`usr_id_create`),
  KEY `fk_price_item_usr_id_delete_idx` (`usr_id_delete`),
  CONSTRAINT `fk_item_price_list_item1` FOREIGN KEY (`itm_id`) REFERENCES `item` (`itm_id`),
  CONSTRAINT `fk_item_price_price_list1` FOREIGN KEY (`prl_id`) REFERENCES `price_list` (`prl_id`),
  CONSTRAINT `fk_price_item_usr_id_create` FOREIGN KEY (`usr_id_create`) REFERENCES `user` (`usr_id`),
  CONSTRAINT `fk_price_item_usr_id_delete` FOREIGN KEY (`usr_id_delete`) REFERENCES `user` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3203 DEFAULT CHARSET=utf8mb3;


-- carbon.price_item_model definition

CREATE TABLE `price_item_model` (
  `pim_id` int NOT NULL AUTO_INCREMENT,
  `price` decimal(13,2) NOT NULL DEFAULT '0.00',
  `all_models` tinyint(1) NOT NULL DEFAULT '0',
  `all_brands` tinyint(1) NOT NULL DEFAULT '0',
  `prl_id` int NOT NULL,
  `imd_id` int DEFAULT NULL,
  `brd_id` int DEFAULT NULL,
  `itm_id` int NOT NULL,
  `for_free` tinyint(1) NOT NULL DEFAULT '0',
  `usr_id_create` int DEFAULT NULL,
  `usr_id_delete` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`pim_id`),
  KEY `fk_price_list_item_model_item_model1_idx` (`imd_id`),
  KEY `fk_price_list_item_model_price_list1_idx` (`prl_id`),
  KEY `fk_item_model_price_brand1_idx` (`brd_id`),
  KEY `fk_price_item_model_item1_idx` (`itm_id`),
  KEY `fk_price_item_usr_id_create_idx` (`usr_id_create`),
  KEY `fk_price_item_usr_id_delete_idx` (`usr_id_delete`),
  CONSTRAINT `fk_item_model_price_brand1` FOREIGN KEY (`brd_id`) REFERENCES `brand` (`brd_id`),
  CONSTRAINT `fk_price_item_model_item1` FOREIGN KEY (`itm_id`) REFERENCES `item` (`itm_id`),
  CONSTRAINT `fk_price_item_model_usr_id_create` FOREIGN KEY (`usr_id_create`) REFERENCES `user` (`usr_id`),
  CONSTRAINT `fk_price_item_model_usr_id_delete` FOREIGN KEY (`usr_id_delete`) REFERENCES `user` (`usr_id`),
  CONSTRAINT `fk_price_list_item_model_item_model1` FOREIGN KEY (`imd_id`) REFERENCES `item_model` (`imd_id`),
  CONSTRAINT `fk_price_list_item_model_price_list1` FOREIGN KEY (`prl_id`) REFERENCES `price_list` (`prl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13335 DEFAULT CHARSET=utf8mb3;


-- carbon.price_list_partner definition

CREATE TABLE `price_list_partner` (
  `ptn_id` int NOT NULL,
  `prl_id` int NOT NULL,
  PRIMARY KEY (`ptn_id`,`prl_id`),
  KEY `fk_partner_price_list_price_list1_idx` (`prl_id`),
  KEY `fk_partner_price_list_partner1_idx` (`ptn_id`),
  CONSTRAINT `fk_partner_price_list_partner1` FOREIGN KEY (`ptn_id`) REFERENCES `partner` (`ptn_id`),
  CONSTRAINT `fk_partner_price_list_price_list1` FOREIGN KEY (`prl_id`) REFERENCES `price_list` (`prl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- carbon.price_product definition

CREATE TABLE `price_product` (
  `ppr_id` int NOT NULL AUTO_INCREMENT,
  `price` decimal(13,2) NOT NULL COMMENT 'Valor do produto praticado',
  `prl_id` int NOT NULL,
  `prm_id` int NOT NULL,
  `usr_id_create` int DEFAULT NULL,
  `usr_id_delete` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`ppr_id`),
  KEY `fk_product_price_price_list1_idx` (`prl_id`),
  KEY `fk_product_price_product_model1_idx` (`prm_id`),
  KEY `fk_price_product_usr_id_create_idx` (`usr_id_create`),
  KEY `fk_price_product_usr_id_delete_idx` (`usr_id_delete`),
  CONSTRAINT `fk_price_product_usr_id_create` FOREIGN KEY (`usr_id_create`) REFERENCES `user` (`usr_id`),
  CONSTRAINT `fk_price_product_usr_id_delete` FOREIGN KEY (`usr_id_delete`) REFERENCES `user` (`usr_id`),
  CONSTRAINT `fk_product_price_price_list1` FOREIGN KEY (`prl_id`) REFERENCES `price_list` (`prl_id`),
  CONSTRAINT `fk_product_price_product_model1` FOREIGN KEY (`prm_id`) REFERENCES `product_model` (`prm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55074 DEFAULT CHARSET=utf8mb3;


-- carbon.product_model definition

CREATE TABLE `product_model` (
  `prm_id` int NOT NULL AUTO_INCREMENT,
  `has_project` tinyint(1) NOT NULL DEFAULT '1',
  `model_year_start` int NOT NULL,
  `model_year_end` int NOT NULL,
  `manufacture_days` int NOT NULL DEFAULT '30' COMMENT '\n',
  `prd_id` int NOT NULL,
  `mdl_id` int NOT NULL,
  `customer_days` int NOT NULL,
  PRIMARY KEY (`prm_id`),
  KEY `fk_product_vehicle_model_product1_idx` (`prd_id`),
  KEY `fk_product_vehicle_model_model1_idx` (`mdl_id`),
  CONSTRAINT `fk_product_vehicle_model_model1` FOREIGN KEY (`mdl_id`) REFERENCES `model` (`mdl_id`),
  CONSTRAINT `fk_product_vehicle_model_product1` FOREIGN KEY (`prd_id`) REFERENCES `product` (`prd_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1124 DEFAULT CHARSET=utf8mb3;


-- carbon.product_model_bonus definition

CREATE TABLE `product_model_bonus` (
  `pmb_id` int NOT NULL AUTO_INCREMENT COMMENT 'ID da tabela de product_model X bonus',
  `brd_id` int NOT NULL COMMENT 'ID da marca (chave estrangeira da brand)',
  `prm_id` int NOT NULL COMMENT 'ID do produto modelo (chave estrangeira de product_model)',
  `bonus_value` decimal(13,2) DEFAULT NULL COMMENT 'valor do bonus',
  `is_bonus_type_percentage` tinyint(1) DEFAULT '0' COMMENT 'tipo do bonus',
  `cla_payment_type` int DEFAULT NULL COMMENT 'FK de classifier',
  PRIMARY KEY (`pmb_id`),
  KEY `fk_product_model_bonus_brand` (`brd_id`),
  KEY `fk_product_model_bonus_product_model` (`prm_id`),
  KEY `fk_classifier_product_model_bonus` (`cla_payment_type`),
  CONSTRAINT `fk_classifier_product_model_bonus` FOREIGN KEY (`cla_payment_type`) REFERENCES `classifier` (`cla_id`),
  CONSTRAINT `fk_product_model_bonus_brand` FOREIGN KEY (`brd_id`) REFERENCES `brand` (`brd_id`),
  CONSTRAINT `fk_product_model_bonus_product_model` FOREIGN KEY (`prm_id`) REFERENCES `product_model` (`prm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb3;


-- carbon.proposal_approval_rule definition

CREATE TABLE `proposal_approval_rule` (
  `par_id` int NOT NULL AUTO_INCREMENT,
  `value` decimal(13,2) NOT NULL,
  `job_id` int NOT NULL,
  `immediate_delivery` tinyint DEFAULT NULL,
  PRIMARY KEY (`par_id`,`job_id`),
  KEY `fk_proposal_approval_rule_job_idx` (`job_id`),
  CONSTRAINT `fk_proposal_approval_rule_job` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;


-- carbon.specific_payment_condition definition

CREATE TABLE `specific_payment_condition` (
  `spc_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `tax` decimal(8,4) NOT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `armor` tinyint(1) NOT NULL DEFAULT '0',
  `amendments` tinyint(1) NOT NULL DEFAULT '0',
  `technical_assistance` tinyint(1) NOT NULL DEFAULT '0',
  `description` varchar(255) NOT NULL,
  `maxIntallments` int NOT NULL,
  `immediateDelivery` tinyint DEFAULT NULL,
  `max_dias_para_frente_data_pactuada` varchar(3) DEFAULT NULL,
  `max_qtde_dias_cla_id` int DEFAULT NULL,
  `percent_min_primeira_parcela` varchar(3) DEFAULT NULL,
  `max_intervalo_parcelas` varchar(3) DEFAULT NULL,
  `tipo_max_intervalo_parcelas` varchar(1) DEFAULT NULL,
  `usr_id_create` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `usr_id_delete` int DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`spc_id`),
  KEY `fk_max_qtde_dias_classifier` (`max_qtde_dias_cla_id`),
  KEY `fk_specific_payment_usr_id_create_idx` (`usr_id_create`),
  KEY `fk_specific_payment_usr_id_delete_idx` (`usr_id_delete`),
  CONSTRAINT `fk_max_qtde_dias_classifier` FOREIGN KEY (`max_qtde_dias_cla_id`) REFERENCES `classifier` (`cla_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_specific_payment_usr_id_create` FOREIGN KEY (`usr_id_create`) REFERENCES `user` (`usr_id`),
  CONSTRAINT `fk_specific_payment_usr_id_delete` FOREIGN KEY (`usr_id_delete`) REFERENCES `user` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=943 DEFAULT CHARSET=utf8mb3;


-- carbon.specific_payment_event definition

CREATE TABLE `specific_payment_event` (
  `spe_id` int NOT NULL AUTO_INCREMENT,
  `spc_id` int NOT NULL,
  `cla_id` int NOT NULL,
  PRIMARY KEY (`spe_id`),
  KEY `fk_spc_id_idx` (`spc_id`),
  KEY `fk_cla_id_idx` (`cla_id`),
  CONSTRAINT `fk_event_type` FOREIGN KEY (`cla_id`) REFERENCES `classifier` (`cla_id`),
  CONSTRAINT `fk_specific_payment` FOREIGN KEY (`spc_id`) REFERENCES `specific_payment_condition` (`spc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2390 DEFAULT CHARSET=utf8mb3;


-- carbon.specific_payment_method definition

CREATE TABLE `specific_payment_method` (
  `spm_id` int NOT NULL AUTO_INCREMENT,
  `pym_id` int NOT NULL,
  `spc_id` int NOT NULL,
  `meio_pagamento_etapa_cla_id` int DEFAULT NULL,
  PRIMARY KEY (`spm_id`),
  KEY `fk_pym_id` (`pym_id`),
  KEY `fk_spc_id` (`spc_id`),
  KEY `fk_meio_pagamento_etapa_classifier` (`meio_pagamento_etapa_cla_id`),
  CONSTRAINT `fk_meio_pagamento_etapa_classifier` FOREIGN KEY (`meio_pagamento_etapa_cla_id`) REFERENCES `classifier` (`cla_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_pym_id` FOREIGN KEY (`pym_id`) REFERENCES `payment_method` (`pym_id`),
  CONSTRAINT `fk_spc_id` FOREIGN KEY (`spc_id`) REFERENCES `specific_payment_condition` (`spc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1593 DEFAULT CHARSET=utf8mb3;


-- carbon.specific_payment_rule definition

CREATE TABLE `specific_payment_rule` (
  `spr_id` int NOT NULL AUTO_INCREMENT,
  `spm_id` int NOT NULL,
  `pyr_id` int NOT NULL,
  PRIMARY KEY (`spr_id`),
  KEY `fk_spm_id` (`spm_id`),
  KEY `fk_ pyr_id` (`pyr_id`),
  CONSTRAINT `fk_pyr_id` FOREIGN KEY (`pyr_id`) REFERENCES `payment_rule` (`pyr_id`),
  CONSTRAINT `fk_spm_id` FOREIGN KEY (`spm_id`) REFERENCES `specific_payment_method` (`spm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3255 DEFAULT CHARSET=utf8mb3;