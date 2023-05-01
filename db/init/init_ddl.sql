CREATE TABLE `credit_loan`.`loan_order` (
  `order_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `product_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `loan_amount` decimal(16,4) DEFAULT NULL,
  `annual_interest_rate` decimal(16,4) DEFAULT NULL,
  `items` int DEFAULT NULL,
  `loan_type` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `apply_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`));

CREATE TABLE `credit_loan`.`loan_product` (
 `product_id` VARCHAR(36) NOT NULL,
 `product_name` VARCHAR(64) NULL,
 `product_description` VARCHAR(256) NULL,
 `annual_interest_rate` DECIMAL(16,4) NULL,
 `loan_min` DECIMAL(16,4) NULL,
 `loan_max` DECIMAL(16,4) NULL,
 `create_date` DATETIME NULL,
 `update_date` DATETIME NULL,
 PRIMARY KEY (`product_id`));

CREATE TABLE `credit_loan`.`repayment_plan` (
    `id` BIGINT(64) NOT NULL AUTO_INCREMENT,
    `loan_order_id` VARCHAR(36) NULL,
    `user_id` VARCHAR(36) NULL,
    `item` INT NULL,
    `monthly_repayment` DECIMAL(16,4) NULL,
    `principal` DECIMAL(16,4) NULL,
    `interest` DECIMAL(16,4) NULL,
    `punish_interest` DECIMAL(16,4) NULL,
    `repay_date` VARCHAR(16) NULL,
    `actual_repayment` DECIMAL(16,4) NULL,
    `repay_status` VARCHAR(64) NULL,
    `create_date` DATETIME NULL,
    `update_date` DATETIME NULL,
    PRIMARY KEY (`id`));

CREATE TABLE `credit_loan`.`user` (
     `user_id` VARCHAR(36) NOT NULL,
     `user_name` VARCHAR(32) NULL,
     `password` VARCHAR(64) NULL,
     PRIMARY KEY (`user_id`));

CREATE TABLE `user_info` (
     `user_id` varchar(36) NOT NULL,
     `first_name` varchar(32) DEFAULT NULL,
     `middle_name` varchar(32) DEFAULT NULL,
     `last_name` varchar(32) DEFAULT NULL,
     `phone_no` varchar(32) DEFAULT NULL,
     `singpass_id` varchar(64) DEFAULT NULL,
     `email` varchar(64) DEFAULT NULL,
     `address` varchar(128) DEFAULT NULL,
     `job` varchar(32) DEFAULT NULL,
     `income_year` decimal(16,4) DEFAULT NULL,
     `bank_account` varchar(32) DEFAULT NULL,
     `create_date` datetime DEFAULT NULL,
     `update_date` datetime DEFAULT NULL,
     PRIMARY KEY (`user_id`));