-- carbon.checkpoint definition

CREATE TABLE `checkpoint` (
  `ckp_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ckp_id`),
  UNIQUE KEY `uq_checkpoint` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb3;


-- carbon.access_list definition

CREATE TABLE `access_list` (
  `acl_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `mnu_id` int DEFAULT NULL,
  PRIMARY KEY (`acl_id`),
  UNIQUE KEY `uk_access_list` (`name`),
  KEY `fk_access_list_default_route_idx` (`mnu_id`),
  CONSTRAINT `fk_access_list_default_route` FOREIGN KEY (`mnu_id`) REFERENCES `menu` (`mnu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb3;


-- carbon.access_list_checkpoint definition

CREATE TABLE `access_list_checkpoint` (
  `ckp_id` int NOT NULL,
  `acl_id` int NOT NULL,
  PRIMARY KEY (`ckp_id`,`acl_id`),
  KEY `fk_access_list_checkpoint_access_list1_idx` (`acl_id`),
  CONSTRAINT `fk_access_list_checkpoint_access_list` FOREIGN KEY (`acl_id`) REFERENCES `access_list` (`acl_id`),
  CONSTRAINT `fk_access_list_checkpoint_checkpoint` FOREIGN KEY (`ckp_id`) REFERENCES `checkpoint` (`ckp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


-- carbon.seller definition

CREATE TABLE `seller` (
  `sel_id` int NOT NULL AUTO_INCREMENT,
  `per_id` int NOT NULL,
  `job_id` int NOT NULL,
  `active` tinyint DEFAULT NULL,
  `technical_assistance` tinyint(1) DEFAULT '0',
  `img_seller` mediumblob,
  PRIMARY KEY (`sel_id`),
  KEY `fk_seller_person1_idx` (`per_id`),
  KEY `fk_seller_job_idx` (`job_id`),
  KEY `idx_seller_per_id` (`per_id`),
  CONSTRAINT `fk_seller_job` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`),
  CONSTRAINT `fk_seller_person1` FOREIGN KEY (`per_id`) REFERENCES `person` (`per_id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8mb3;


-- carbon.seller_partner definition

CREATE TABLE `seller_partner` (
  `sel_id` int NOT NULL,
  `ptn_id` int NOT NULL,
  PRIMARY KEY (`sel_id`,`ptn_id`),
  KEY `fk_seller_has_partner_partner1_idx` (`ptn_id`),
  KEY `fk_seller_has_partner_seller1_idx` (`sel_id`),
  CONSTRAINT `fk_seller_has_partner_partner1` FOREIGN KEY (`ptn_id`) REFERENCES `partner` (`ptn_id`),
  CONSTRAINT `fk_seller_has_partner_seller1` FOREIGN KEY (`sel_id`) REFERENCES `seller` (`sel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;