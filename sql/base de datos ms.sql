-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `idusuario` INT NOT NULL,
  `correo` VARCHAR(45) NULL,
  `contrasenia` VARCHAR(45) NULL,
  PRIMARY KEY (`idusuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`compra` (
  `idcompra` INT NOT NULL,
  `cantidad` INT NULL,
  `usuario_idusuario` INT NOT NULL,
  PRIMARY KEY (`idcompra`),
  INDEX `fk_compra_usuario1_idx` (`usuario_idusuario` ASC) VISIBLE,
  CONSTRAINT `fk_compra_usuario1`
    FOREIGN KEY (`usuario_idusuario`)
    REFERENCES `mydb`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`producto` (
  `idproducto` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `referencia` VARCHAR(45) NULL,
  `precio` VARCHAR(45) NULL,
  `stock` VARCHAR(45) NULL,
  `compra_idcompra` INT NOT NULL,
  PRIMARY KEY (`idproducto`),
  INDEX `fk_producto_compra_idx` (`compra_idcompra` ASC) VISIBLE,
  CONSTRAINT `fk_producto_compra`
    FOREIGN KEY (`compra_idcompra`)
    REFERENCES `mydb`.`compra` (`idcompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`productos_compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`productos_compra` (
  `producto_id` INT NOT NULL,
  `compra_id` INT NOT NULL,
  INDEX `fk_compra_idx` (`compra_id` ASC) VISIBLE,
  INDEX `fk_producto_idx` (`producto_id` ASC) VISIBLE,
  CONSTRAINT `fk_compra`
    FOREIGN KEY (`compra_id`)
    REFERENCES `mydb`.`compra` (`idcompra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto`
    FOREIGN KEY (`producto_id`)
    REFERENCES `mydb`.`producto` (`idproducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
