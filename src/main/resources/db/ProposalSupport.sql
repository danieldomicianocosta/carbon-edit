-- carbon.proposal_commission definition

CREATE TABLE `proposal_commission` (
  `pcm_id` int NOT NULL AUTO_INCREMENT,
  `cmn_type_cla_id` int NOT NULL COMMENT 'Tipo de comissionado: parceiro, colaborador ou montadora',
  `per_id` int NOT NULL,
  `person_type_cla_id` int DEFAULT NULL COMMENT 'Perfil do Colaborador do Parceiro',
  `due_date` datetime DEFAULT NULL,
  `value` decimal(13,2) NOT NULL,
  `notes` text,
  `cmt_id` int DEFAULT NULL COMMENT 'Tipo de comissão: comissão, bonus e etc',
  `ppd_id` int NOT NULL,
  `act_id` int DEFAULT NULL COMMENT 'Conta bancária',
  `payment_cla_id` int NOT NULL COMMENT 'Tipo de pagamenot',
  `bank_data` varchar(255) DEFAULT NULL COMMENT 'DADOS BANCÁRIOS DO PARCEIRO',
  `number_nf` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pcm_id`),
  KEY `fk_comission_person1_idx` (`per_id`),
  KEY `fk_comission_person_proposal_detail1_idx` (`ppd_id`),
  KEY `fk_proposal_commission_account_idx` (`act_id`),
  KEY `fk_commission_payment_classifier_idx` (`payment_cla_id`),
  KEY `fk_proposal_commission_commission_type` (`cmt_id`),
  KEY `fk_comission_person_type_idx` (`person_type_cla_id`),
  KEY `fk_commissioned_type_idx` (`cmn_type_cla_id`),
  CONSTRAINT `fk_comission_person` FOREIGN KEY (`per_id`) REFERENCES `person` (`per_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_comission_proposal_bank_account` FOREIGN KEY (`act_id`) REFERENCES `bank_account` (`act_id`),
  CONSTRAINT `fk_comission_proposal_detail` FOREIGN KEY (`ppd_id`) REFERENCES `proposal_detail` (`ppd_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_commission_payment_classifier` FOREIGN KEY (`payment_cla_id`) REFERENCES `classifier` (`cla_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_proposal_commission_commission_type` FOREIGN KEY (`cmt_id`) REFERENCES `commission_type` (`cmt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29228 DEFAULT CHARSET=utf8mb3;


-- carbon.proposal_detail_vehicle_item definition

CREATE TABLE `proposal_detail_vehicle_item` (
  `pdvi_id` int NOT NULL AUTO_INCREMENT,
  `amount_discount` decimal(13,2) NOT NULL DEFAULT '0.00',
  `percent_discount` decimal(3,2) NOT NULL DEFAULT '0.00',
  `final_price` decimal(13,2) NOT NULL DEFAULT '0.00',
  `table_price_tax` decimal(13,2) NOT NULL,
  `for_free` tinyint(1) NOT NULL DEFAULT '0',
  `pdv_id` int NOT NULL,
  `seller_id` int NOT NULL,
  `pci_id` int DEFAULT NULL,
  `pim_id` int DEFAULT NULL,
  `amendment` int NOT NULL DEFAULT '0',
  `immediate_delivery` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pdvi_id`),
  KEY `fk_proposal_detail_vehicle_item_price_list_proposal_detail__idx` (`pdv_id`),
  KEY `fk_proposal_detail_vehicle_item_item_price1_idx` (`pci_id`),
  KEY `fk_proposal_detail_vehicle_item_item_model_price1_idx` (`pim_id`),
  KEY `fk_proposal_detail_vehicle_item_person1_idx` (`seller_id`),
  CONSTRAINT `fk_proposal_detail_vehicle_item_item_model_price1` FOREIGN KEY (`pim_id`) REFERENCES `price_item_model` (`pim_id`),
  CONSTRAINT `fk_proposal_detail_vehicle_item_item_price1` FOREIGN KEY (`pci_id`) REFERENCES `price_item` (`pci_id`),
  CONSTRAINT `fk_proposal_detail_vehicle_item_price_list_proposal_detail_ve1` FOREIGN KEY (`pdv_id`) REFERENCES `proposal_detail_vehicle` (`pdv_id`)
) ENGINE=InnoDB AUTO_INCREMENT=157569 DEFAULT CHARSET=utf8mb3;


-- carbon.proposal_document definition

CREATE TABLE `proposal_document` (
  `pps_id` int NOT NULL,
  `doc_id` int NOT NULL,
  PRIMARY KEY (`pps_id`,`doc_id`),
  KEY `fk_proposal_document_document1_idx` (`doc_id`),
  KEY `fk_proposal_document_proposal1_idx` (`pps_id`),
  CONSTRAINT `fk_proposal_document_document1` FOREIGN KEY (`doc_id`) REFERENCES `document` (`doc_id`),
  CONSTRAINT `fk_proposal_document_proposal1` FOREIGN KEY (`pps_id`) REFERENCES `proposal` (`pps_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- carbon.proposal_fup definition

CREATE TABLE `proposal_fup` (
  `pfp_id` int NOT NULL AUTO_INCREMENT,
  `pps_id` int NOT NULL,
  `date` datetime NOT NULL,
  `media_cla_id` int NOT NULL,
  `person` varchar(150) NOT NULL,
  `comment` varchar(1000) DEFAULT NULL,
  `fup_type_cla_id` int DEFAULT NULL,
  `usr_id` int DEFAULT NULL,
  PRIMARY KEY (`pfp_id`),
  KEY `fk_proposal_fup_proposal1_idx` (`pps_id`),
  KEY `fk_proposal_fup_media_idx` (`media_cla_id`),
  KEY `idx_proposal_fup_date` (`date`),
  KEY `fk_fup_type_cla_id` (`fup_type_cla_id`),
  KEY `fk_proposal_fup_usr` (`usr_id`),
  CONSTRAINT `fk_fup_type_cla_id` FOREIGN KEY (`fup_type_cla_id`) REFERENCES `classifier` (`cla_id`),
  CONSTRAINT `fk_proposal_fup_media` FOREIGN KEY (`media_cla_id`) REFERENCES `classifier` (`cla_id`),
  CONSTRAINT `fk_proposal_fup_proposal1` FOREIGN KEY (`pps_id`) REFERENCES `proposal` (`pps_id`),
  CONSTRAINT `fk_proposal_fup_usr` FOREIGN KEY (`usr_id`) REFERENCES `user` (`usr_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=676 DEFAULT CHARSET=utf8mb3;


-- carbon.proposal_payment definition

CREATE TABLE `proposal_payment` (
  `ppy_id` int NOT NULL AUTO_INCREMENT,
  `payment_amount` decimal(13,2) NOT NULL DEFAULT '0.00' COMMENT 'Valor para ser pago na parcela',
  `due_date` datetime DEFAULT NULL,
  `installment_amount` decimal(13,2) DEFAULT NULL,
  `interest` decimal(13,2) NOT NULL,
  `ppd_id` int NOT NULL COMMENT 'Quantidade total de parcelas para o método e escolhido',
  `pym_id` int DEFAULT NULL,
  `pyr_id` int DEFAULT NULL,
  `payer_cla_id` int NOT NULL,
  `event_cla_id` int NOT NULL,
  `days` int DEFAULT NULL,
  `pre_approved` tinyint NOT NULL,
  `antecipated_billing` tinyint NOT NULL DEFAULT '0',
  `position` int DEFAULT NULL,
  `quantity_days` int DEFAULT NULL,
  `carbon_billing` tinyint NOT NULL DEFAULT '0',
  `amendment` int NOT NULL DEFAULT '0',
  `simple_interest` tinyint(1) NOT NULL,
  PRIMARY KEY (`ppy_id`),
  KEY `fk_payment_detail_proposal_detail1_idx` (`ppd_id`),
  KEY `fk_proposal_payment_classifier1_idx` (`payer_cla_id`),
  KEY `fk_proposal_payment_classifier2_idx` (`event_cla_id`),
  CONSTRAINT `fk_payment_detail_proposal_detail1` FOREIGN KEY (`ppd_id`) REFERENCES `proposal_detail` (`ppd_id`),
  CONSTRAINT `fk_proposal_payment_classifier1` FOREIGN KEY (`payer_cla_id`) REFERENCES `classifier` (`cla_id`),
  CONSTRAINT `fk_proposal_payment_classifier2` FOREIGN KEY (`event_cla_id`) REFERENCES `classifier` (`cla_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46208 DEFAULT CHARSET=utf8mb3;


-- carbon.proposal_person_client definition

CREATE TABLE `proposal_person_client` (
  `pps_id` int NOT NULL,
  `per_id` int NOT NULL,
  `customer_cla_id` int NOT NULL,
  `customer_seq` int DEFAULT NULL,
  PRIMARY KEY (`pps_id`,`per_id`,`customer_cla_id`),
  KEY `fk_proposal_person_person1_idx` (`per_id`),
  KEY `fk_proposal_person_proposal1_idx` (`pps_id`),
  KEY `fk_proposal_person_client_classifier1_idx` (`customer_cla_id`),
  CONSTRAINT `fk_proposal_person_client_classifier1` FOREIGN KEY (`customer_cla_id`) REFERENCES `classifier` (`cla_id`),
  CONSTRAINT `fk_proposal_person_person1` FOREIGN KEY (`per_id`) REFERENCES `person` (`per_id`),
  CONSTRAINT `fk_proposal_person_proposal1` FOREIGN KEY (`pps_id`) REFERENCES `proposal` (`pps_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- carbon.sales_order definition

CREATE TABLE `sales_order` (
  `sor_id` int NOT NULL AUTO_INCREMENT,
  `pps_id_production` int DEFAULT NULL,
  `pps_id_sale` int DEFAULT NULL,
  `sale_date` datetime DEFAULT NULL,
  `order_number` int NOT NULL,
  `jira_key` varchar(10) NOT NULL,
  `usr_id` int NOT NULL,
  `production_date` datetime NOT NULL,
  `os_canceled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`sor_id`),
  UNIQUE KEY `order_number_unique` (`order_number`),
  KEY `fk_sales_order_proposal_idx` (`pps_id_production`),
  KEY `fk_sales_order_user_idx` (`usr_id`),
  KEY `fk_sales_order_proposal_sale_idx` (`pps_id_sale`),
  CONSTRAINT `fk_sales_order_proposal` FOREIGN KEY (`pps_id_production`) REFERENCES `proposal` (`pps_id`),
  CONSTRAINT `fk_sales_order_proposal_sale` FOREIGN KEY (`pps_id_sale`) REFERENCES `proposal` (`pps_id`),
  CONSTRAINT `fk_sales_order_user` FOREIGN KEY (`usr_id`) REFERENCES `user` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7537 DEFAULT CHARSET=utf8mb3;