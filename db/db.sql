-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema lynxc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lynxc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lynxc` DEFAULT CHARACTER SET utf8 ;
USE `lynxc` ;

-- -----------------------------------------------------
-- Table `lynxc`.`configuration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`configuration` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `key` VARCHAR(45) NOT NULL,
  `value` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `key_UNIQUE` (`key` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`content_def`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`content_def` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`group` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`language` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `code` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 175
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`country` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `default_language_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_country_language1_idx` (`default_language_id` ASC),
  CONSTRAINT `fk_country_language1`
  FOREIGN KEY (`default_language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 194
  DEFAULT CHARACTER SET = utf8
  COMMENT = 'The country the user lives in.';


-- -----------------------------------------------------
-- Table `lynxc`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telephone` VARCHAR(45) NULL DEFAULT NULL,
  `pwd_hash` VARCHAR(70) NULL DEFAULT NULL,
  `role_id` INT(11) NULL DEFAULT NULL,
  `group_id` INT(11) NULL DEFAULT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT '0',
  `preferred_language_id` INT(11) NULL DEFAULT NULL,
  `country_id` INT(11) NULL DEFAULT NULL,
  `activation_code` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_user_role1_idx` (`role_id` ASC),
  INDEX `fk_user_group1_idx` (`group_id` ASC),
  INDEX `fk_user_language1_idx` (`preferred_language_id` ASC),
  INDEX `fk_user_country1_idx` (`country_id` ASC),
  CONSTRAINT `fk_user_role1`
  FOREIGN KEY (`role_id`)
  REFERENCES `lynxc`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_group1`
  FOREIGN KEY (`group_id`)
  REFERENCES `lynxc`.`group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_language1`
  FOREIGN KEY (`preferred_language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_country1`
  FOREIGN KEY (`country_id`)
  REFERENCES `lynxc`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = 'A user to a content.';


-- -----------------------------------------------------
-- Table `lynxc`.`content`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`content` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `content_def_id` INT(11) NOT NULL,
  `updated` TIMESTAMP NULL DEFAULT NULL,
  `created` DATETIME NOT NULL,
  `version` INT(11) NOT NULL DEFAULT '1',
  `locked_by` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_content_content_def1_idx` (`content_def_id` ASC),
  INDEX `fk_content_user1_idx` (`locked_by` ASC),
  CONSTRAINT `fk_content_content_def1`
  FOREIGN KEY (`content_def_id`)
  REFERENCES `lynxc`.`content_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_content_user1`
  FOREIGN KEY (`locked_by`)
  REFERENCES `lynxc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`content_admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`content_admin` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `content_def_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_content_admin_user1_idx` (`user_id` ASC),
  INDEX `fk_content_admin_content_def1_idx` (`content_def_id` ASC),
  CONSTRAINT `fk_content_admin_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `lynxc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_content_admin_content_def1`
  FOREIGN KEY (`content_def_id`)
  REFERENCES `lynxc`.`content_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`content_def_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`content_def_lang` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `content_def_id` INT(11) NOT NULL,
  `language_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_content_def_lang_content_def1_idx` (`content_def_id` ASC),
  INDEX `fk_content_def_lang_language1_idx` (`language_id` ASC),
  CONSTRAINT `fk_content_def_lang_content_def1`
  FOREIGN KEY (`content_def_id`)
  REFERENCES `lynxc`.`content_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_content_def_lang_language1`
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`content_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`content_lang` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `content_id` INT(11) NOT NULL,
  `language_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_content_lang_content1_idx` (`content_id` ASC),
  INDEX `fk_content_lang_language1_idx` (`language_id` ASC),
  CONSTRAINT `fk_content_lang_content1`
  FOREIGN KEY (`content_id`)
  REFERENCES `lynxc`.`content` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_content_lang_language1`
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`content_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`content_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `content_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `can_read` TINYINT(1) NOT NULL DEFAULT '1',
  `can_write` TINYINT(1) NOT NULL DEFAULT '0',
  `last_read` TIMESTAMP NULL DEFAULT NULL,
  `last_write` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_content_user_content1_idx` (`content_id` ASC),
  INDEX `fk_content_user_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_content_user_content1`
  FOREIGN KEY (`content_id`)
  REFERENCES `lynxc`.`content` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_content_user_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `lynxc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COMMENT = 'The information for a user of a specific content.';


-- -----------------------------------------------------
-- Table `lynxc`.`country_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`country_lang` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country_id` INT(11) NOT NULL,
  `language_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `fk_country_lang_country1_idx` (`country_id` ASC),
  INDEX `fk_country_lang_language1_idx` (`language_id` ASC),
  CONSTRAINT `fk_country_lang_country1`
  FOREIGN KEY (`country_id`)
  REFERENCES `lynxc`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_country_lang_language1`
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 194
  DEFAULT CHARACTER SET = utf8
  COMMENT = 'The name of the country in a specific language.';


-- -----------------------------------------------------
-- Table `lynxc`.`email_template`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`email_template` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `subject` VARCHAR(250) NOT NULL,
  `body` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`section_def`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`section_def` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `dynamic` TINYINT(1) NOT NULL DEFAULT '0',
  `content_def_id` INT(11) NOT NULL,
  `parent_section_def_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_section_def_content_def1_idx` (`content_def_id` ASC),
  INDEX `fk_section_def_section_def1_idx` (`parent_section_def_id` ASC),
  CONSTRAINT `fk_section_def_content_def1`
  FOREIGN KEY (`content_def_id`)
  REFERENCES `lynxc`.`content_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_section_def_section_def1`
  FOREIGN KEY (`parent_section_def_id`)
  REFERENCES `lynxc`.`section_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`item_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`item_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `value_UNIQUE` (`value` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`resource_layout`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`resource_layout` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `value_UNIQUE` (`value` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`item_def`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`item_def` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `section_def_id` INT(11) NOT NULL,
  `item_type_id` INT(11) NOT NULL,
  `required` TINYINT(1) NOT NULL DEFAULT '0',
  `regex` VARCHAR(300) NULL DEFAULT NULL,
  `min` VARCHAR(100) NULL DEFAULT NULL,
  `max` VARCHAR(100) NULL DEFAULT NULL,
  `resource_layout_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_item_def_section_def1_idx` (`section_def_id` ASC),
  INDEX `fk_item_def_item_type1_idx` (`item_type_id` ASC),
  INDEX `fk_item_def_resource_layout1_idx` (`resource_layout_id` ASC),
  CONSTRAINT `fk_item_def_section_def1`
  FOREIGN KEY (`section_def_id`)
  REFERENCES `lynxc`.`section_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_def_item_type1`
  FOREIGN KEY (`item_type_id`)
  REFERENCES `lynxc`.`item_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_def_resource_layout1`
  FOREIGN KEY (`resource_layout_id`)
  REFERENCES `lynxc`.`resource_layout` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`section`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`section` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `section_def_id` INT(11) NOT NULL,
  `content_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_section_section_def1_idx` (`section_def_id` ASC),
  INDEX `fk_section_content1_idx` (`content_id` ASC),
  CONSTRAINT `fk_section_section_def1`
  FOREIGN KEY (`section_def_id`)
  REFERENCES `lynxc`.`section_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_section_content1`
  FOREIGN KEY (`content_id`)
  REFERENCES `lynxc`.`content` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`item` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `item_def_id` INT(11) NOT NULL,
  `section_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_item_item_def1_idx` (`item_def_id` ASC),
  INDEX `fk_item_section1_idx` (`section_id` ASC),
  CONSTRAINT `fk_item_item_def1`
  FOREIGN KEY (`item_def_id`)
  REFERENCES `lynxc`.`item_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_section1`
  FOREIGN KEY (`section_id`)
  REFERENCES `lynxc`.`section` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`item_def_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`item_def_lang` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `question` VARCHAR(300) NULL DEFAULT NULL,
  `regex` VARCHAR(300) NULL DEFAULT NULL,
  `max` VARCHAR(100) NULL DEFAULT NULL,
  `min` VARCHAR(100) NULL DEFAULT NULL,
  `language_id` INT(11) NOT NULL,
  `item_def_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_item_def_lang_language1_idx` (`language_id` ASC),
  INDEX `fk_item_def_lang_item_def1_idx` (`item_def_id` ASC),
  CONSTRAINT `fk_item_def_lang_language1`
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_def_lang_item_def1`
  FOREIGN KEY (`item_def_id`)
  REFERENCES `lynxc`.`item_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`media_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`media_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `template` VARCHAR(150) NULL,
  `reference` VARCHAR(250) NULL,
  `icon_path` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `value_UNIQUE` (`name` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`resource`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`resource` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `media_type_id` INT(11) NOT NULL,
  `path` VARCHAR(250) NOT NULL,
  `link` VARCHAR(250) NULL DEFAULT NULL,
  `item_def_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_resource_media_type1_idx` (`media_type_id` ASC),
  INDEX `fk_resource_item_def1_idx` (`item_def_id` ASC),
  CONSTRAINT `fk_resource_media_type1`
  FOREIGN KEY (`media_type_id`)
  REFERENCES `lynxc`.`media_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resource_item_def1`
  FOREIGN KEY (`item_def_id`)
  REFERENCES `lynxc`.`item_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`resource_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`resource_lang` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `resource_id` INT(11) NOT NULL,
  `language_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_resource_lang_resource1_idx` (`resource_id` ASC),
  INDEX `fk_resource_lang_language1_idx` (`language_id` ASC),
  CONSTRAINT `fk_resource_lang_resource1`
  FOREIGN KEY (`resource_id`)
  REFERENCES `lynxc`.`resource` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resource_lang_language1`
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`role_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`role_lang` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `role_id` INT(11) NOT NULL,
  `language_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_role_lang_role1_idx` (`role_id` ASC),
  INDEX `fk_role_lang_language1_idx` (`language_id` ASC),
  CONSTRAINT `fk_role_lang_role1`
  FOREIGN KEY (`role_id`)
  REFERENCES `lynxc`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_lang_language1`
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`section_def_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`section_def_lang` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `section_def_id` INT(11) NOT NULL,
  `language_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_section_def_lang_section_def1_idx` (`section_def_id` ASC),
  INDEX `fk_section_def_lang_language1_idx` (`language_id` ASC),
  CONSTRAINT `fk_section_def_lang_section_def1`
  FOREIGN KEY (`section_def_id`)
  REFERENCES `lynxc`.`section_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_section_def_lang_language1`
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`value_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`value_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`value_def`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`value_def` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `item_def_id` INT(11) NOT NULL,
  `value_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_item_value_def_item_def1_idx` (`item_def_id` ASC),
  INDEX `fk_value_def_value_type1_idx` (`value_type_id` ASC),
  CONSTRAINT `fk_item_value_def_item_def1`
  FOREIGN KEY (`item_def_id`)
  REFERENCES `lynxc`.`item_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_value_def_value_type1`
  FOREIGN KEY (`value_type_id`)
  REFERENCES `lynxc`.`value_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`value`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`value` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `short_text` VARCHAR(150) NULL,
  `long_text` TEXT NULL,
  `date` DATETIME NULL,
  `integer` INT NULL,
  `decimal` DECIMAL(13,2) NULL,
  `flag` TINYINT(1) NULL,
  `value_def_id` INT(11) NOT NULL,
  `item_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_value_value_def1_idx` (`value_def_id` ASC),
  INDEX `fk_value_item1_idx` (`item_id` ASC),
  CONSTRAINT `fk_value_value_def1`
  FOREIGN KEY (`value_def_id`)
  REFERENCES `lynxc`.`value_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_value_item1`
  FOREIGN KEY (`item_id`)
  REFERENCES `lynxc`.`item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `lynxc`.`value_def_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`value_def_lang` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `language_id` INT(11) NOT NULL,
  `value_def_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_value_def_lang_language1_idx` (`language_id` ASC),
  INDEX `fk_value_def_lang_value_def1_idx` (`value_def_id` ASC),
  CONSTRAINT `fk_value_def_lang_language1`
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_value_def_lang_value_def1`
  FOREIGN KEY (`value_def_id`)
  REFERENCES `lynxc`.`value_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
