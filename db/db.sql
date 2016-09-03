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
-- Table `lynxc`.`respondent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`respondent` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telephone` VARCHAR(45) NULL,
  `pwd_hash` VARCHAR(45) NULL,
  `role_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `language_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_respondent_role1_idx` (`role_id` ASC),
  INDEX `fk_respondent_group1_idx` (`group_id` ASC),
  INDEX `fk_respondent_language1_idx` (`language_id` ASC),
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
  FOREIGN KEY (`language_id`)
  REFERENCES `lynxc`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


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
  `static` TINYINT(1) NULL,
  `survey_def_id` INT NOT NULL,
  `section_def_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_section_def_survey_def1_idx` (`survey_def_id` ASC),
  INDEX `fk_section_def_section_def1_idx` (`section_def_id` ASC),
  CONSTRAINT `fk_section_def_survey_def1`
  FOREIGN KEY (`survey_def_id`)
  REFERENCES `lynxc`.`survey_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_section_def_section_def1`
  FOREIGN KEY (`section_def_id`)
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
  PRIMARY KEY (`id`),
  INDEX `fk_survey_survey_def1_idx` (`survey_def_id` ASC),
  CONSTRAINT `fk_survey_survey_def1`
  FOREIGN KEY (`survey_def_id`)
  REFERENCES `lynxc`.`survey_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`survey_respondent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`survey_respondent` (
  `survey_id` INT NOT NULL,
  `respondent_id` INT NOT NULL,
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
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`fact_def`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`fact_def` (
  `id` INT NOT NULL,
  `section_def_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fact_def_section_def1_idx` (`section_def_id` ASC),
  CONSTRAINT `fk_fact_def_section_def1`
  FOREIGN KEY (`section_def_id`)
  REFERENCES `lynxc`.`section_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`option_def`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`option_def` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `fact_def_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fact_option_def_fact_def1_idx` (`fact_def_id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
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
  `section_id` INT NOT NULL AUTO_INCREMENT,
  `fact_def_id` INT NOT NULL,
  INDEX `fk_fact_section1_idx` (`section_id` ASC),
  INDEX `fk_fact_fact_def1_idx` (`fact_def_id` ASC),
  PRIMARY KEY (`section_id`, `fact_def_id`),
  CONSTRAINT `fk_fact_section1`
  FOREIGN KEY (`section_id`)
  REFERENCES `lynxc`.`section` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fact_fact_def1`
  FOREIGN KEY (`fact_def_id`)
  REFERENCES `lynxc`.`fact_def` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lynxc`.`option`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lynxc`.`option` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fact_id` INT NOT NULL,
  `fact_option_def_id` INT NOT NULL,
  `value` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_option_fact_option_def1_idx` (`fact_option_def_id` ASC),
  CONSTRAINT `fk_option_fact_option_def1`
  FOREIGN KEY (`fact_option_def_id`)
  REFERENCES `lynxc`.`option_def` (`id`)
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
  `type` VARCHAR(45) NULL,
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
