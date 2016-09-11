-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema lynxc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lynxc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lynxc` DEFAULT CHARACTER SET utf8 ;
USE `lynxc` ;

-- -----------------------------------------------------
-- Table `lynxc`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`group` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`language` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `code` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `default_language_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_country_language1_idx` (`default_language_id` ASC),
  CONSTRAINT `fk_country_language1`
  FOREIGN KEY (`default_language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  COMMENT = 'The country the respondent lives in.';


-- -----------------------------------------------------
-- Table `lynxc`.`respondent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`respondent` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telephone` VARCHAR(45) NULL,
  `pwd_hash` VARCHAR(70) NULL,
  `role_id` INT NULL,
  `group_id` INT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 0,
  `preferred_language_id` INT NULL,
  `country_id` INT NULL,
  `activation_code` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_respondent_role1_idx` (`role_id` ASC),
  INDEX `fk_respondent_group1_idx` (`group_id` ASC),
  INDEX `fk_respondent_language1_idx` (`preferred_language_id` ASC),
  INDEX `fk_respondent_country1_idx` (`country_id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  CONSTRAINT `fk_respondent_role1`
  FOREIGN KEY (`role_id`)
  REFERENCES `lynxc`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_respondent_group1`
  FOREIGN KEY (`group_id`)
  REFERENCES `lynxc`.`group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_respondent_language1`
  FOREIGN KEY (`preferred_language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_respondent_country1`
  FOREIGN KEY (`country_id`)
  REFERENCES `lynxc`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  COMMENT = 'A respondent to a survey.';


-- -----------------------------------------------------
-- Table `lynxc`.`survey_def`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`survey_def` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`section_def`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`section_def` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dynamic` TINYINT(1) NOT NULL DEFAULT 0,
  `survey_def_id` INT NOT NULL,
  `parent_section_def_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_section_def_survey_def1_idx` (`survey_def_id` ASC),
  INDEX `fk_section_def_section_def1_idx` (`parent_section_def_id` ASC),
  CONSTRAINT `fk_section_def_survey_def1`
  FOREIGN KEY (`survey_def_id`)
  REFERENCES `lynxc`.`survey_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_section_def_section_def1`
  FOREIGN KEY (`parent_section_def_id`)
  REFERENCES `lynxc`.`section_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`survey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`survey` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `survey_def_id` INT NOT NULL,
  `updated` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `version` INT NOT NULL DEFAULT 1,
  `locked_by` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_survey_survey_def1_idx` (`survey_def_id` ASC),
  INDEX `fk_survey_respondent1_idx` (`locked_by` ASC),
  CONSTRAINT `fk_survey_survey_def1`
  FOREIGN KEY (`survey_def_id`)
  REFERENCES `lynxc`.`survey_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_survey_respondent1`
  FOREIGN KEY (`locked_by`)
  REFERENCES `lynxc`.`respondent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`survey_respondent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`survey_respondent` (
  `survey_id` INT NOT NULL,
  `respondent_id` INT NOT NULL,
  `can_read` TINYINT(1) NOT NULL DEFAULT 1,
  `can_write` TINYINT(1) NOT NULL DEFAULT 0,
  `last_read` TIMESTAMP NULL,
  `last_write` TIMESTAMP NULL,
  INDEX `fk_survey_respondent_survey1_idx` (`survey_id` ASC),
  INDEX `fk_survey_respondent_respondent1_idx` (`respondent_id` ASC),
  CONSTRAINT `fk_survey_respondent_survey1`
  FOREIGN KEY (`survey_id`)
  REFERENCES `lynxc`.`survey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_survey_respondent_respondent1`
  FOREIGN KEY (`respondent_id`)
  REFERENCES `lynxc`.`respondent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  COMMENT = 'The information for a respondent of a specific survey.';


-- -----------------------------------------------------
-- Table `lynxc`.`fact_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`fact_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`fact_def`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`fact_def` (
  `id` INT NOT NULL,
  `section_def_id` INT NOT NULL,
  `fact_type_id` INT NOT NULL,
  `required` TINYINT(1) NOT NULL DEFAULT 0,
  `regex` VARCHAR(200) NULL,
  `min` VARCHAR(100) NULL,
  `max` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fact_def_section_def1_idx` (`section_def_id` ASC),
  INDEX `fk_fact_def_fact_type1_idx` (`fact_type_id` ASC),
  CONSTRAINT `fk_fact_def_section_def1`
  FOREIGN KEY (`section_def_id`)
  REFERENCES `lynxc`.`section_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fact_def_fact_type1`
  FOREIGN KEY (`fact_type_id`)
  REFERENCES `lynxc`.`fact_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`option_def`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`option_def` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fact_def_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fact_option_def_fact_def1_idx` (`fact_def_id` ASC),
  CONSTRAINT `fk_fact_option_def_fact_def1`
  FOREIGN KEY (`fact_def_id`)
  REFERENCES `lynxc`.`fact_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`section`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`section` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `section_def_id` INT NOT NULL,
  `survey_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_section_section_def1_idx` (`section_def_id` ASC),
  INDEX `fk_section_survey1_idx` (`survey_id` ASC),
  CONSTRAINT `fk_section_section_def1`
  FOREIGN KEY (`section_def_id`)
  REFERENCES `lynxc`.`section_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_section_survey1`
  FOREIGN KEY (`survey_id`)
  REFERENCES `lynxc`.`survey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`fact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`fact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fact_def_id` INT NOT NULL,
  `section_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fact_fact_def1_idx` (`fact_def_id` ASC),
  INDEX `fk_fact_section1_idx` (`section_id` ASC),
  CONSTRAINT `fk_fact_fact_def1`
  FOREIGN KEY (`fact_def_id`)
  REFERENCES `lynxc`.`fact_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fact_section1`
  FOREIGN KEY (`section_id`)
  REFERENCES `lynxc`.`section` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`option`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`option` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(150) NOT NULL,
  `option_def_id` INT NOT NULL,
  `fact_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_option_option_def1_idx` (`option_def_id` ASC),
  INDEX `fk_option_fact1_idx` (`fact_id` ASC),
  CONSTRAINT `fk_option_option_def1`
  FOREIGN KEY (`option_def_id`)
  REFERENCES `lynxc`.`option_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_option_fact1`
  FOREIGN KEY (`fact_id`)
  REFERENCES `lynxc`.`fact` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`role_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`role_lang` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `role_id` INT NOT NULL,
  `language_id` INT NOT NULL,
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
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`fact_def_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`fact_def_lang` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `question` VARCHAR(250) NULL,
  `language_id` INT NOT NULL,
  `fact_def_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fact_def_lang_language1_idx` (`language_id` ASC),
  INDEX `fk_fact_def_lang_fact_def1_idx` (`fact_def_id` ASC),
  CONSTRAINT `fk_fact_def_lang_language1`
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fact_def_lang_fact_def1`
  FOREIGN KEY (`fact_def_id`)
  REFERENCES `lynxc`.`fact_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`option_def_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`option_def_lang` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `language_id` INT NOT NULL,
  `option_def_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_option_def_lang_language1_idx` (`language_id` ASC),
  INDEX `fk_option_def_lang_option_def1_idx` (`option_def_id` ASC),
  CONSTRAINT `fk_option_def_lang_language1`
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_option_def_lang_option_def1`
  FOREIGN KEY (`option_def_id`)
  REFERENCES `lynxc`.`option_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`section_def_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`section_def_lang` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `section_def_id` INT NOT NULL,
  `language_id` INT NOT NULL,
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
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`survey_def_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`survey_def_lang` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `survey_def_id` INT NOT NULL,
  `language_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_survey_def_lang_survey_def1_idx` (`survey_def_id` ASC),
  INDEX `fk_survey_def_lang_language1_idx` (`language_id` ASC),
  CONSTRAINT `fk_survey_def_lang_survey_def1`
  FOREIGN KEY (`survey_def_id`)
  REFERENCES `lynxc`.`survey_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_survey_def_lang_language1`
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`country_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`country_lang` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country_id` INT NOT NULL,
  `language_id` INT NOT NULL,
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
  COMMENT = 'The name of the country in a specific language.';


-- -----------------------------------------------------
-- Table `lynxc`.`survey_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`survey_lang` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` TEXT NULL,
  `survey_id` INT NOT NULL,
  `language_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_survey_lang_survey1_idx` (`survey_id` ASC),
  INDEX `fk_survey_lang_language1_idx` (`language_id` ASC),
  CONSTRAINT `fk_survey_lang_survey1`
  FOREIGN KEY (`survey_id`)
  REFERENCES `lynxc`.`survey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_survey_lang_language1`
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`email_template`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`email_template` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `subject` VARCHAR(250) NOT NULL,
  `body` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`configuration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`configuration` (
  `id` INT NOT NULL,
  `key` VARCHAR(45) NOT NULL,
  `value` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `key_UNIQUE` (`key` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`survey_admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`survey_admin` (
  `id` INT NOT NULL,
  `respondent_id` INT NOT NULL,
  `survey_def_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_survey_admin_respondent1_idx` (`respondent_id` ASC),
  INDEX `fk_survey_admin_survey_def1_idx` (`survey_def_id` ASC),
  CONSTRAINT `fk_survey_admin_respondent1`
  FOREIGN KEY (`respondent_id`)
  REFERENCES `lynxc`.`respondent` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_survey_admin_survey_def1`
  FOREIGN KEY (`survey_def_id`)
  REFERENCES `lynxc`.`survey_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
