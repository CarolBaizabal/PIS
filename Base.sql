CREATE DATABASE  IF NOT EXISTS `base` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `base`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: base
-- ------------------------------------------------------
-- Server version	5.5.62

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `catalogo`
--

DROP TABLE IF EXISTS `catalogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogo` (
  `idCatalogo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `idCategoria` int(11) DEFAULT NULL,
  `estatus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCatalogo`),
  KEY `idCategoria_idx` (`idCategoria`),
  KEY `nombre` (`nombre`),
  CONSTRAINT `` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo`
--

LOCK TABLES `catalogo` WRITE;
/*!40000 ALTER TABLE `catalogo` DISABLE KEYS */;
INSERT INTO `catalogo` VALUES (1,'Cobro de interes',1,'Activo'),(2,'Aumento de fondo',1,'Activo'),(3,'Venta de prendas',1,'Activo'),(4,'Rescuperación de préstamo',1,'Activo'),(5,'Pago de salario',2,'Activo'),(6,'Empeño',2,'Activo'),(7,'Nomina',2,'Activo'),(8,'Mantenimiento',2,'Activo'),(9,'Activo',3,'Activo'),(10,'Inactivo',3,'Activo'),(11,'Administrador',4,'Activo'),(12,'Cajero',4,'Activo'),(13,'Gerente',4,'Activo'),(14,'Activo',5,'Activo'),(15,'Cancelado',5,'Activo'),(16,'Finiquitado',6,'Activo'),(17,'Refrendado',6,'Activo'),(18,'Cancelado',6,'Activo'),(19,'En espera',6,'Activo'),(20,'Vendido',7,'Activo'),(21,'Remate',7,'Activo');
/*!40000 ALTER TABLE `catalogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `catalogo_activo_prendafullinfo`
--

DROP TABLE IF EXISTS `catalogo_activo_prendafullinfo`;
/*!50001 DROP VIEW IF EXISTS `catalogo_activo_prendafullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `catalogo_activo_prendafullinfo` AS SELECT 
 1 AS `idCatalogo`,
 1 AS `nombre`,
 1 AS `categoria`,
 1 AS `idCategoria`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `catalogo_activofullinfo`
--

DROP TABLE IF EXISTS `catalogo_activofullinfo`;
/*!50001 DROP VIEW IF EXISTS `catalogo_activofullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `catalogo_activofullinfo` AS SELECT 
 1 AS `idCatalogo`,
 1 AS `nombre`,
 1 AS `categoria`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `catalogo_prenda`
--

DROP TABLE IF EXISTS `catalogo_prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogo_prenda` (
  `idCatalogo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  `estatus` varchar(45) NOT NULL,
  PRIMARY KEY (`idCatalogo`),
  KEY `idCategoria_idx` (`categoria`),
  KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo_prenda`
--

LOCK TABLES `catalogo_prenda` WRITE;
/*!40000 ALTER TABLE `catalogo_prenda` DISABLE KEYS */;
INSERT INTO `catalogo_prenda` VALUES (1,'Oro','2','Activo'),(2,'Plata','2','Activo'),(3,'Computo','1','Activo'),(4,'Electrodomesticos','1','Activo'),(5,'Entretenimiento','1','Activo'),(6,'ejemplo','2','Activo');
/*!40000 ALTER TABLE `catalogo_prenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `catalogofullinfo`
--

DROP TABLE IF EXISTS `catalogofullinfo`;
/*!50001 DROP VIEW IF EXISTS `catalogofullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `catalogofullinfo` AS SELECT 
 1 AS `idCatalogo`,
 1 AS `nombre`,
 1 AS `categoria`,
 1 AS `estatus`,
 1 AS `idCategoria`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `catalogoprendafullinfo`
--

DROP TABLE IF EXISTS `catalogoprendafullinfo`;
/*!50001 DROP VIEW IF EXISTS `catalogoprendafullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `catalogoprendafullinfo` AS SELECT 
 1 AS `idCatalogo`,
 1 AS `nombre`,
 1 AS `categoria`,
 1 AS `idCategoria`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `estatus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`),
  KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Ingreso','Activo'),(2,'Egreso','Activo'),(3,'Estatus','Activo'),(4,'Rol','Activo'),(5,'EstatusRefrendo','Activo'),(6,'EstatusContrato','Activo'),(7,'EstatusVentas','Activo');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `categoria_activofullinfo`
--

DROP TABLE IF EXISTS `categoria_activofullinfo`;
/*!50001 DROP VIEW IF EXISTS `categoria_activofullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `categoria_activofullinfo` AS SELECT 
 1 AS `idCategoria`,
 1 AS `nombre`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `categoria_activoprendafullinfo`
--

DROP TABLE IF EXISTS `categoria_activoprendafullinfo`;
/*!50001 DROP VIEW IF EXISTS `categoria_activoprendafullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `categoria_activoprendafullinfo` AS SELECT 
 1 AS `idCategoria`,
 1 AS `nombre`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `categoria_prenda`
--

DROP TABLE IF EXISTS `categoria_prenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_prenda` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `estatus` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategoria`),
  KEY `nombre` (`nombre`),
  KEY `idCategoria` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_prenda`
--

LOCK TABLES `categoria_prenda` WRITE;
/*!40000 ALTER TABLE `categoria_prenda` DISABLE KEYS */;
INSERT INTO `categoria_prenda` VALUES (1,'Aparato','Activo'),(2,'Metal','Activo');
/*!40000 ALTER TABLE `categoria_prenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `categoriafullinfo`
--

DROP TABLE IF EXISTS `categoriafullinfo`;
/*!50001 DROP VIEW IF EXISTS `categoriafullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `categoriafullinfo` AS SELECT 
 1 AS `idCategoria`,
 1 AS `nombre`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `categoriaprendafullinfo`
--

DROP TABLE IF EXISTS `categoriaprendafullinfo`;
/*!50001 DROP VIEW IF EXISTS `categoriaprendafullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `categoriaprendafullinfo` AS SELECT 
 1 AS `idCategoria`,
 1 AS `nombre`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidoPaterno` varchar(45) NOT NULL,
  `apellidoMaterno` varchar(45) NOT NULL,
  `calle` varchar(45) NOT NULL,
  `cp` varchar(45) NOT NULL,
  `colonia` varchar(45) NOT NULL,
  `municipio` varchar(45) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `idemex` varchar(45) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `curp` varchar(45) NOT NULL,
  `rfc` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `comentarios` varchar(45) DEFAULT NULL,
  `sexo` varchar(45) NOT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `fechaActualizacion` datetime DEFAULT NULL,
  `usuarioA` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Flor','Juarez','Perez','Morelos','91532','Revolucion','Emiliano Zapata','Veracruz','43214','2000-01-12','FJP5432','FGV234','7748753355','flor@gmail.com','Ninguno','Femenino','2023-06-21 00:00:00','Carol','2023-06-25 00:00:00','Carol'),(5,'Juan','Lopez','Arriaga','Avila Camacho','91465','Centro','Xalapa','Veracruz','26574','1994-06-16','LLA2345','HHG6543','665678754','luis@gmail.com','Ninguno','Masculino','2023-06-25 05:03:50','Carol','2023-06-25 06:50:09','Carol');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `clientefullinfo`
--

DROP TABLE IF EXISTS `clientefullinfo`;
/*!50001 DROP VIEW IF EXISTS `clientefullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `clientefullinfo` AS SELECT 
 1 AS `idCliente`,
 1 AS `nombre`,
 1 AS `apellidoPaterno`,
 1 AS `apellidoMaterno`,
 1 AS `calle`,
 1 AS `cp`,
 1 AS `colonia`,
 1 AS `municipio`,
 1 AS `estado`,
 1 AS `idemex`,
 1 AS `fechaNacimiento`,
 1 AS `curp`,
 1 AS `rfc`,
 1 AS `telefono`,
 1 AS `correo`,
 1 AS `comentarios`,
 1 AS `sexo`,
 1 AS `fechaCreacion`,
 1 AS `usuario`,
 1 AS `fechaActualizacion`,
 1 AS `usuarioA`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `comercializacion`
--

DROP TABLE IF EXISTS `comercializacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comercializacion` (
  `idComercializacion` int(11) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `fechaInicioBusqueda` datetime NOT NULL,
  `fechaFinalBusqueda` datetime NOT NULL,
  `Observaciones` varchar(45) NOT NULL,
  `metal` varchar(45) NOT NULL,
  `idDetalleComercializacion` int(11) NOT NULL,
  PRIMARY KEY (`idComercializacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercializacion`
--

LOCK TABLES `comercializacion` WRITE;
/*!40000 ALTER TABLE `comercializacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `comercializacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comercializacion_detalle`
--

DROP TABLE IF EXISTS `comercializacion_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comercializacion_detalle` (
  `idDetalle` int(11) NOT NULL AUTO_INCREMENT,
  `idComercializacion` int(11) DEFAULT NULL,
  `idContrato` int(11) DEFAULT NULL,
  `idEmp` int(11) DEFAULT NULL,
  `prenda` varchar(45) DEFAULT NULL,
  `precioComercializacion` float DEFAULT NULL,
  PRIMARY KEY (`idDetalle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercializacion_detalle`
--

LOCK TABLES `comercializacion_detalle` WRITE;
/*!40000 ALTER TABLE `comercializacion_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `comercializacion_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `comercializacion_detallefullinfo`
--

DROP TABLE IF EXISTS `comercializacion_detallefullinfo`;
/*!50001 DROP VIEW IF EXISTS `comercializacion_detallefullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `comercializacion_detallefullinfo` AS SELECT 
 1 AS `idDetalle`,
 1 AS `idComercializacion`,
 1 AS `idContrato`,
 1 AS `idEmp`,
 1 AS `prenda`,
 1 AS `precioComercializacion`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contrato` (
  `idContrato` int(11) NOT NULL AUTO_INCREMENT,
  `idEmp` int(11) DEFAULT NULL,
  `fechaCreacion` date DEFAULT NULL,
  `fechaActualizacion` date DEFAULT NULL,
  `fechaLimiteRefrendo` date DEFAULT NULL,
  `FechaComercializacion` date DEFAULT NULL,
  `importePrestamo` float DEFAULT NULL,
  `estatus` varchar(45) DEFAULT NULL,
  `idContratoAnterior` int(11) DEFAULT NULL,
  `idContratoSiguiente` int(11) DEFAULT NULL,
  `fechaCreacionActual` varchar(45) DEFAULT NULL,
  `fechaComercializacionActual` date DEFAULT NULL,
  `fechaCancelacion` date DEFAULT NULL,
  `usuario` int(11) DEFAULT NULL,
  `observaciones` varchar(45) DEFAULT NULL,
  `idRefrendo` int(11) DEFAULT NULL,
  `idFiniquito` int(11) DEFAULT NULL,
  `idAumentoEspera` int(11) DEFAULT NULL,
  PRIMARY KEY (`idContrato`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
INSERT INTO `contrato` VALUES (4,52,'2023-06-28',NULL,'2023-07-28','2023-07-29',1000,'Finiquitado',NULL,30,'2023-07-28','2023-07-29',NULL,NULL,'ntng',NULL,NULL,NULL),(5,53,'2023-06-28',NULL,'2023-07-28','2023-07-29',48,'Vigente',NULL,30,'2023-07-28','2023-07-29',NULL,NULL,'rb',NULL,NULL,NULL),(6,54,'2023-06-28',NULL,'2023-07-28','2023-07-29',1,'Vigente',NULL,30,'2023-07-28','2023-07-29',NULL,NULL,'fs',NULL,NULL,NULL),(7,55,'2023-06-28',NULL,'2023-07-28','2023-07-29',1,'Vigente',NULL,30,'2023-07-28','2023-07-29',NULL,NULL,'fdv',NULL,NULL,NULL),(8,56,'2023-06-28',NULL,'2023-07-28','2023-07-29',1,'Vigente',NULL,30,'2023-07-28','2023-07-29',NULL,NULL,'dv',NULL,NULL,NULL),(9,57,'2023-06-28',NULL,'2023-07-28','2023-07-29',10,'Vigente',NULL,30,'2023-07-28','2023-07-29',NULL,NULL,'db',NULL,NULL,NULL),(10,58,'2023-06-28',NULL,'2023-07-28','2023-07-29',100,'Finiquitado',NULL,30,'2023-07-28','2023-07-29',NULL,NULL,'f',NULL,NULL,NULL);
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egreso`
--

DROP TABLE IF EXISTS `egreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `egreso` (
  `idEgreso` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) NOT NULL,
  `motivo` varchar(500) NOT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `usuarioA` varchar(45) DEFAULT NULL,
  `estatus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEgreso`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egreso`
--

LOCK TABLES `egreso` WRITE;
/*!40000 ALTER TABLE `egreso` DISABLE KEYS */;
INSERT INTO `egreso` VALUES (1,128,'Empeño',NULL,NULL,NULL,NULL,NULL,'Activo'),(2,654,'Nomina',NULL,NULL,NULL,NULL,NULL,'Activo'),(3,765,'10',NULL,NULL,NULL,NULL,NULL,'Inactivo'),(4,34,'9',NULL,'2023-06-25 04:21:06','Carol','2023-06-25 22:52:59','Carol','Activo'),(5,34,'22',NULL,'2023-06-25 04:23:28','Carol','2023-06-25 04:36:16','Carol','Activo'),(6,65,'Empeño','gb','2023-06-26 03:31:45','Carol',NULL,NULL,'Activo');
/*!40000 ALTER TABLE `egreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `egreso_activofullinfo`
--

DROP TABLE IF EXISTS `egreso_activofullinfo`;
/*!50001 DROP VIEW IF EXISTS `egreso_activofullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `egreso_activofullinfo` AS SELECT 
 1 AS `idEgreso`,
 1 AS `cantidad`,
 1 AS `motivo`,
 1 AS `observaciones`,
 1 AS `fechaCreacion`,
 1 AS `usuario`,
 1 AS `fechaModificacion`,
 1 AS `usuarioA`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `egresofullinfo`
--

DROP TABLE IF EXISTS `egresofullinfo`;
/*!50001 DROP VIEW IF EXISTS `egresofullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `egresofullinfo` AS SELECT 
 1 AS `idEgreso`,
 1 AS `cantidad`,
 1 AS `motivo`,
 1 AS `observaciones`,
 1 AS `fechaCreacion`,
 1 AS `usuario`,
 1 AS `fechaModificacion`,
 1 AS `usuarioA`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp` (
  `idEmp` int(11) NOT NULL AUTO_INCREMENT,
  `idCliente` int(11) DEFAULT NULL,
  `fechaCreacion` date DEFAULT NULL,
  `Observaciones` varchar(45) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `idContrato` int(11) DEFAULT NULL,
  `fechaActualizacion` date DEFAULT NULL,
  `interesPorcentaje` int(11) DEFAULT NULL,
  `interes` float DEFAULT NULL,
  `almacenajePorcentaje` int(11) DEFAULT NULL,
  `almacenaje` float DEFAULT NULL,
  `periodos` int(11) DEFAULT NULL,
  `diasPeriodos` int(11) DEFAULT NULL,
  `iva` float DEFAULT NULL,
  `tasaComercializacion` float DEFAULT NULL,
  `estatus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEmp`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--

LOCK TABLES `emp` WRITE;
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
INSERT INTO `emp` VALUES (1,1,'2023-05-12','ejemplo','Carol',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Vigente'),(4,1,'2023-07-12','ejemplo','Carol',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Vigente'),(51,NULL,'2023-06-27','','Carol',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Finiquitado'),(52,5,'2023-06-28','fbdf','Carol',4,NULL,32,48,8,12,2,30,9.6,NULL,'Finiquitado'),(53,5,'2023-06-28','er','Carol',5,NULL,32,2.304,8,0.576,2,30,0.4608,NULL,'Vigente'),(54,1,'2023-06-28','v','Carol',6,NULL,32,0.048,8,0.012,2,30,0.0096,NULL,'Vigente'),(55,5,'2023-06-28','fd','Carol',7,NULL,32,0.048,8,0.012,2,30,0.0096,NULL,'Vigente'),(56,5,'2023-06-28','dv','Carol',8,NULL,32,0.048,8,0.012,2,30,0.0096,NULL,'Vigente'),(57,1,'2023-06-28','f','Carol',9,NULL,32,0.48,8,0.12,2,30,0.096,NULL,'Vigente'),(58,5,'2023-06-28','f','Carol',10,NULL,32,4.8,8,1.2,2,30,0.96,NULL,'Finiquitado'),(63,NULL,'2023-06-28','','Carol',NULL,NULL,32,NULL,8,NULL,2,30,NULL,20,'Vigente');
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `empfullinfo`
--

DROP TABLE IF EXISTS `empfullinfo`;
/*!50001 DROP VIEW IF EXISTS `empfullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `empfullinfo` AS SELECT 
 1 AS `idEmp`,
 1 AS `idCliente`,
 1 AS `fechaCreacion`,
 1 AS `observaciones`,
 1 AS `usuario`,
 1 AS `idContrato`,
 1 AS `fechaActualizacion`,
 1 AS `interesPorcentaje`,
 1 AS `interes`,
 1 AS `almacenajePorcentaje`,
 1 AS `almacenaje`,
 1 AS `periodos`,
 1 AS `diasPeriodos`,
 1 AS `iva`,
 1 AS `tasaComercializacion`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `esperas`
--

DROP TABLE IF EXISTS `esperas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `esperas` (
  `idEspera` int(11) NOT NULL,
  `fechaInicio` datetime NOT NULL,
  `fechaFin` datetime NOT NULL,
  PRIMARY KEY (`idEspera`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `esperas`
--

LOCK TABLES `esperas` WRITE;
/*!40000 ALTER TABLE `esperas` DISABLE KEYS */;
/*!40000 ALTER TABLE `esperas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finiquito`
--

DROP TABLE IF EXISTS `finiquito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `finiquito` (
  `idFiniquito` int(11) NOT NULL AUTO_INCREMENT,
  `idEmp` int(11) DEFAULT NULL,
  `idContrato` int(11) DEFAULT NULL,
  `fechaCreacion` date DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `interes` float DEFAULT NULL,
  `importeAlmacenaje` float DEFAULT NULL,
  `subtotal` float DEFAULT NULL,
  `iva` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  PRIMARY KEY (`idFiniquito`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finiquito`
--

LOCK TABLES `finiquito` WRITE;
/*!40000 ALTER TABLE `finiquito` DISABLE KEYS */;
/*!40000 ALTER TABLE `finiquito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingreso`
--

DROP TABLE IF EXISTS `ingreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingreso` (
  `idIngreso` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) NOT NULL,
  `motivo` varchar(500) NOT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `usuarioA` varchar(45) DEFAULT NULL,
  `estatus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idIngreso`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingreso`
--

LOCK TABLES `ingreso` WRITE;
/*!40000 ALTER TABLE `ingreso` DISABLE KEYS */;
INSERT INTO `ingreso` VALUES (1,543,'Cobro de interes',NULL,NULL,NULL,NULL,NULL,'Activo'),(2,323,'Aumento de fondo',NULL,NULL,NULL,NULL,NULL,'Activo'),(3,654,'ejemplo',NULL,NULL,NULL,NULL,NULL,'Activo'),(8,12,'otro ejemplo','ta weno',NULL,NULL,NULL,NULL,'Inactivo'),(9,654,'Activo',NULL,NULL,'df',NULL,NULL,'Inactivo'),(10,765,'Vendido','dv',NULL,'grberg','2023-06-24 14:45:51',NULL,'Inactivo'),(11,654,'ejemplo','vcs','2023-06-24 14:45:34','Carol','2023-06-24 14:51:46','Carol','Inactivo'),(12,543,'Empeño','dc','2023-06-28 17:43:27','Carol','2023-06-28 17:43:36','Carol','Activo');
/*!40000 ALTER TABLE `ingreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `ingreso_activofullinfo`
--

DROP TABLE IF EXISTS `ingreso_activofullinfo`;
/*!50001 DROP VIEW IF EXISTS `ingreso_activofullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `ingreso_activofullinfo` AS SELECT 
 1 AS `idIngreso`,
 1 AS `cantidad`,
 1 AS `motivo`,
 1 AS `observaciones`,
 1 AS `fechaCreacion`,
 1 AS `usuario`,
 1 AS `fechaModificacion`,
 1 AS `usuarioA`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `ingresofullinfo`
--

DROP TABLE IF EXISTS `ingresofullinfo`;
/*!50001 DROP VIEW IF EXISTS `ingresofullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `ingresofullinfo` AS SELECT 
 1 AS `idIngreso`,
 1 AS `cantidad`,
 1 AS `motivo`,
 1 AS `observaciones`,
 1 AS `fechaCreacion`,
 1 AS `usuario`,
 1 AS `fechaModificacion`,
 1 AS `usuarioA`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `prendafullinfo`
--

DROP TABLE IF EXISTS `prendafullinfo`;
/*!50001 DROP VIEW IF EXISTS `prendafullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `prendafullinfo` AS SELECT 
 1 AS `idPrendas`,
 1 AS `nombre`,
 1 AS `idEmp`,
 1 AS `categoria`,
 1 AS `numPiezas`,
 1 AS `serie`,
 1 AS `modelo`,
 1 AS `subcategoria`,
 1 AS `descripcion`,
 1 AS `metal`,
 1 AS `peso`,
 1 AS `kilataje`,
 1 AS `prestamo`,
 1 AS `precioComercializacion`,
 1 AS `precioVenta`,
 1 AS `estatus`,
 1 AS `idComercializacion`,
 1 AS `fechaCreacion`,
 1 AS `fechaComercializacion`,
 1 AS `fechaVenta`,
 1 AS `usuario`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `prendas`
--

DROP TABLE IF EXISTS `prendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prendas` (
  `idPrendas` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `idEmp` int(11) DEFAULT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  `numPiezas` int(11) DEFAULT NULL,
  `serie` varchar(45) DEFAULT NULL,
  `modelo` varchar(45) DEFAULT NULL,
  `subcategoria` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `metal` varchar(45) DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `kilataje` float DEFAULT NULL,
  `prestamo` float DEFAULT NULL,
  `precioComercializacion` float DEFAULT NULL,
  `precioVenta` float DEFAULT NULL,
  `estatus` varchar(45) DEFAULT NULL,
  `idComercializacion` int(11) DEFAULT NULL,
  `fechaCreacion` date DEFAULT NULL,
  `fechaComercializacion` date DEFAULT NULL,
  `fechaVenta` date DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPrendas`),
  KEY `idEmp_idx` (`idEmp`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prendas`
--

LOCK TABLES `prendas` WRITE;
/*!40000 ALTER TABLE `prendas` DISABLE KEYS */;
INSERT INTO `prendas` VALUES (20,'fdbfb',49,NULL,123,'fb','fb','Entretenimiento','fb','',0,0,1000,NULL,NULL,'Vigente',NULL,'2023-06-27',NULL,NULL,'Carol'),(21,'gnf',49,NULL,2,'gb','gng','Electrodomesticos','gf','',0,0,40,NULL,NULL,'Vigente',NULL,'2023-06-27',NULL,NULL,'Carol'),(22,'bg',50,'Aparato',2,'fdff','rgv','Computo','rg','No',0,0,4000,NULL,NULL,'Vigente',NULL,'2023-06-27',NULL,NULL,'Carol'),(23,'gfb',52,'Aparato',1,'fng','ngtf','Electrodomesticos','gnf','No',0,0,1000,NULL,NULL,'Finiquitada',NULL,'2023-06-28',NULL,NULL,'Carol'),(24,'gfb',53,'Metal',4,'b','fd','Plata','fd','Si',0,0,48,NULL,NULL,'Vigente',NULL,'2023-06-28',NULL,NULL,'Carol'),(25,'fd',54,'Aparato',1,'vs','fs','Entretenimiento','ffs','',0,0,1,NULL,NULL,'Vigente',NULL,'2023-06-28',NULL,NULL,'Carol'),(26,'sx',55,'Aparato',1,'v','fd','Computo','dv','',0,0,1,NULL,NULL,'Vigente',NULL,'2023-06-28',NULL,NULL,'Carol'),(27,'ccx',56,'Aparato',1,'dv','dv','Computo','dv','',0,0,1,0,NULL,'Vigente',NULL,'2023-06-28',NULL,NULL,'Carol'),(28,'ff',57,'Aparato',1,'dsv','dv','Computo','d','',0,0,10,2,NULL,'Vigente',NULL,'2023-06-28',NULL,NULL,'Carol'),(29,'fv',58,'Aparato',1,'nfgngn','gf','Computo','gnf','',0,0,100,120,NULL,'Finiquitada',NULL,'2023-06-28',NULL,NULL,'Carol'),(30,'fb',63,'Aparato',1,'dv','vd','Computo','vd','',0,0,1,1.2,1.2,'Vigente',NULL,'2023-06-28','2023-07-31',NULL,'Carol');
/*!40000 ALTER TABLE `prendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refrendo`
--

DROP TABLE IF EXISTS `refrendo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refrendo` (
  `idRefrendo` int(11) NOT NULL AUTO_INCREMENT,
  `idEmp` int(11) NOT NULL,
  `idContrato` int(11) NOT NULL,
  `fechaCreacion` varchar(45) NOT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `interes` float DEFAULT NULL,
  `almacenaje` float DEFAULT NULL,
  `subtotal` float DEFAULT NULL,
  `iva` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `estatus` varchar(45) NOT NULL,
  PRIMARY KEY (`idRefrendo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refrendo`
--

LOCK TABLES `refrendo` WRITE;
/*!40000 ALTER TABLE `refrendo` DISABLE KEYS */;
INSERT INTO `refrendo` VALUES (1,52,4,'2023-06-28','Carol',48,12,60,9.6,1000,'Activo'),(2,53,5,'2023-06-28','Carol',2.304,0.576,2.88,0.4608,48,'Activo'),(3,54,6,'2023-06-28','Carol',0.048,0.012,0.06,0.0096,1,'Activo'),(4,55,7,'2023-06-28','Carol',0.048,0.012,0.06,0.0096,1,'Activo'),(5,56,8,'2023-06-28','Carol',0.048,0.012,0.06,0.0096,1,'Activo'),(6,57,9,'2023-06-28','Carol',0.48,0.12,0.6,0.096,10,'Activo'),(7,58,10,'2023-06-28','Carol',4.8,1.2,6,0.96,100,'Activo');
/*!40000 ALTER TABLE `refrendo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `nombreRol` varchar(45) NOT NULL,
  `estatus` varchar(45) NOT NULL,
  PRIMARY KEY (`idRol`),
  KEY `nombreRol` (`nombreRol`),
  CONSTRAINT `nombreRol` FOREIGN KEY (`nombreRol`) REFERENCES `catalogo` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (201,'Administrador','Activo'),(202,'Cajero','Activo'),(203,'Gerente','Activo');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `rol_activofullinfo`
--

DROP TABLE IF EXISTS `rol_activofullinfo`;
/*!50001 DROP VIEW IF EXISTS `rol_activofullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `rol_activofullinfo` AS SELECT 
 1 AS `idRol`,
 1 AS `nombreRol`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `rolfullinfo`
--

DROP TABLE IF EXISTS `rolfullinfo`;
/*!50001 DROP VIEW IF EXISTS `rolfullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `rolfullinfo` AS SELECT 
 1 AS `idRol`,
 1 AS `nombreRol`,
 1 AS `estatus`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellidoPaterno` varchar(45) DEFAULT NULL,
  `apellidoMaterno` varchar(45) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `rol` varchar(45) DEFAULT NULL,
  `estatus` varchar(45) DEFAULT NULL,
  `permisos` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `estatus` (`estatus`),
  KEY `rol` (`rol`),
  CONSTRAINT `estatus` FOREIGN KEY (`estatus`) REFERENCES `catalogo` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rol` FOREIGN KEY (`rol`) REFERENCES `catalogo` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Carol','Pacheco','Baizabal','carol','123456','2288565661','Administrador','Activo',NULL),(2,'Esteban','Ruiz','Perez','esteban','43','3423343451','Cajero','Activo',NULL),(4,'Julio','Colorado','Hernandez','julio','1A5D10A1D102AA4B5B40B7274A16AC7996B567587C0B01E204D477C71E393DBC88B78E8259B111DB80D4AF1605ED84B8C96681F911AD2274733B60759409B06C','4499656823','Gerente','Activo',NULL),(5,'Celina','Juarez','Quiroz','celi','1A5D10A1D102AA4B5B40B7274A16AC7996B567587C0B01E204D477C71E393DBC88B78E8259B111DB80D4AF1605ED84B8C96681F911AD2274733B60759409B06C','662457862','Cajero','Activo',NULL),(6,'Juan','Lopez','Gonzalez','juanito','1A5D10A1D102AA4B5B40B7274A16AC7996B567587C0B01E204D477C71E393DBC88B78E8259B111DB80D4AF1605ED84B8C96681F911AD2274733B60759409B06C','1386345698','Gerente','Activo',NULL),(7,'Mariana','Lopez','Fernandez','mari','C1A897D9E4D4A48D039474F0892C2A4ECBE088A093682AE77B563C761552CD961BA8C2771A66A0EB30B5FCC29AE47A03AB2BBA3248589D12A1FD1AFDC7711783','6673469523','Cajero','Activo',NULL),(8,'hb','bgb','gfb',' gh','ECF4E08560EC119E74F6B5D0FA0C1A56D45B32E4166FBAF1AB01DAE60DDC01BEA036B3A82C94BDB312D49EDE35D29372F5C4B969F0CD7D4757A1555D7B9475E7','65','Administrador','Activo',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `usuariofullinfo`
--

DROP TABLE IF EXISTS `usuariofullinfo`;
/*!50001 DROP VIEW IF EXISTS `usuariofullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `usuariofullinfo` AS SELECT 
 1 AS `idUsuario`,
 1 AS `nombre`,
 1 AS `apellidoPaterno`,
 1 AS `apellidoMaterno`,
 1 AS `usuario`,
 1 AS `password`,
 1 AS `telefono`,
 1 AS `estatus`,
 1 AS `rol`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `usuarios_permisos`
--

DROP TABLE IF EXISTS `usuarios_permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_permisos` (
  `idPermiso` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idUsuario` varchar(45) NOT NULL,
  PRIMARY KEY (`idPermiso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_permisos`
--

LOCK TABLES `usuarios_permisos` WRITE;
/*!40000 ALTER TABLE `usuarios_permisos` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios_permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas_remates`
--

DROP TABLE IF EXISTS `ventas_remates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas_remates` (
  `idventasRemates` int(11) NOT NULL AUTO_INCREMENT,
  `subtotal` float DEFAULT NULL,
  `iva` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `estatus` varchar(45) DEFAULT NULL,
  `cliente` varchar(45) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `fechaVenta` date DEFAULT NULL,
  `totalPrendas` int(11) DEFAULT NULL,
  `fechaCancelacion` date DEFAULT NULL,
  `usuarioCancelar` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idventasRemates`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas_remates`
--

LOCK TABLES `ventas_remates` WRITE;
/*!40000 ALTER TABLE `ventas_remates` DISABLE KEYS */;
INSERT INTO `ventas_remates` VALUES (1,100,16,116,'Venta','Flor','','2023-06-28',1,NULL,NULL,'Oro'),(2,233,16,32,'Venta','Flor','','2023-06-28',2,'2023-06-28','Carol','Computo'),(3,54,16,3,'Venta','Flor',NULL,'2023-06-29',3,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ventas_remates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas_remates_detalle`
--

DROP TABLE IF EXISTS `ventas_remates_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas_remates_detalle` (
  `idventasRemateDetalle` int(11) NOT NULL AUTO_INCREMENT,
  `prenda` varchar(45) DEFAULT NULL,
  `idEmp` int(11) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `iva` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `prestamo` float DEFAULT NULL,
  `descripcionPrenda` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idventasRemateDetalle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas_remates_detalle`
--

LOCK TABLES `ventas_remates_detalle` WRITE;
/*!40000 ALTER TABLE `ventas_remates_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `ventas_remates_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `ventas_remates_detallefullinfo`
--

DROP TABLE IF EXISTS `ventas_remates_detallefullinfo`;
/*!50001 DROP VIEW IF EXISTS `ventas_remates_detallefullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `ventas_remates_detallefullinfo` AS SELECT 
 1 AS `idventasRemateDetalle`,
 1 AS `prenda`,
 1 AS `idEmp`,
 1 AS `precio`,
 1 AS `iva`,
 1 AS `total`,
 1 AS `prestamo`,
 1 AS `descripcionPrenda`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `ventas_remates_fullinfo`
--

DROP TABLE IF EXISTS `ventas_remates_fullinfo`;
/*!50001 DROP VIEW IF EXISTS `ventas_remates_fullinfo`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `ventas_remates_fullinfo` AS SELECT 
 1 AS `idventasRemates`,
 1 AS `subtotal`,
 1 AS `iva`,
 1 AS `total`,
 1 AS `estatus`,
 1 AS `cliente`,
 1 AS `usuario`,
 1 AS `fechaVenta`,
 1 AS `totalPrendas`,
 1 AS `fechaCancelacion`,
 1 AS `usuarioCancelar`,
 1 AS `tipo`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `catalogo_activo_prendafullinfo`
--

/*!50001 DROP VIEW IF EXISTS `catalogo_activo_prendafullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `catalogo_activo_prendafullinfo` AS select `c`.`idCatalogo` AS `idCatalogo`,`c`.`nombre` AS `nombre`,`ca`.`nombre` AS `categoria`,`ca`.`idCategoria` AS `idCategoria`,`c`.`estatus` AS `estatus` from (`catalogo_prenda` `c` join `categoria_prenda` `ca` on((`ca`.`idCategoria` = `c`.`categoria`))) where (`c`.`estatus` = 'Activo') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `catalogo_activofullinfo`
--

/*!50001 DROP VIEW IF EXISTS `catalogo_activofullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `catalogo_activofullinfo` AS select `c`.`idCatalogo` AS `idCatalogo`,`c`.`nombre` AS `nombre`,`ca`.`nombre` AS `categoria`,`c`.`estatus` AS `estatus` from (`catalogo` `c` join `categoria` `ca` on((`ca`.`idCategoria` = `c`.`idCategoria`))) where (`c`.`estatus` = 'Activo') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `catalogofullinfo`
--

/*!50001 DROP VIEW IF EXISTS `catalogofullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `catalogofullinfo` AS select `c`.`idCatalogo` AS `idCatalogo`,`c`.`nombre` AS `nombre`,`ca`.`nombre` AS `categoria`,`c`.`estatus` AS `estatus`,`c`.`idCategoria` AS `idCategoria` from (`catalogo` `c` join `categoria` `ca` on((`ca`.`idCategoria` = `c`.`idCategoria`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `catalogoprendafullinfo`
--

/*!50001 DROP VIEW IF EXISTS `catalogoprendafullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `catalogoprendafullinfo` AS select `c`.`idCatalogo` AS `idCatalogo`,`c`.`nombre` AS `nombre`,`ca`.`nombre` AS `categoria`,`ca`.`idCategoria` AS `idCategoria`,`c`.`estatus` AS `estatus` from (`catalogo_prenda` `c` join `categoria_prenda` `ca` on((`ca`.`idCategoria` = `c`.`categoria`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `categoria_activofullinfo`
--

/*!50001 DROP VIEW IF EXISTS `categoria_activofullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `categoria_activofullinfo` AS select `categoria`.`idCategoria` AS `idCategoria`,`categoria`.`nombre` AS `nombre`,`categoria`.`estatus` AS `estatus` from `categoria` where (`categoria`.`estatus` = 'Activo') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `categoria_activoprendafullinfo`
--

/*!50001 DROP VIEW IF EXISTS `categoria_activoprendafullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `categoria_activoprendafullinfo` AS select `categoria_prenda`.`idCategoria` AS `idCategoria`,`categoria_prenda`.`nombre` AS `nombre`,`categoria_prenda`.`estatus` AS `estatus` from `categoria_prenda` where (`categoria_prenda`.`estatus` = 'Activo') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `categoriafullinfo`
--

/*!50001 DROP VIEW IF EXISTS `categoriafullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `categoriafullinfo` AS select `categoria`.`idCategoria` AS `idCategoria`,`categoria`.`nombre` AS `nombre`,`categoria`.`estatus` AS `estatus` from `categoria` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `categoriaprendafullinfo`
--

/*!50001 DROP VIEW IF EXISTS `categoriaprendafullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `categoriaprendafullinfo` AS select `c`.`idCategoria` AS `idCategoria`,`c`.`nombre` AS `nombre`,`c`.`estatus` AS `estatus` from `categoria_prenda` `c` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `clientefullinfo`
--

/*!50001 DROP VIEW IF EXISTS `clientefullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `clientefullinfo` AS select `c`.`idCliente` AS `idCliente`,`c`.`nombre` AS `nombre`,`c`.`apellidoPaterno` AS `apellidoPaterno`,`c`.`apellidoMaterno` AS `apellidoMaterno`,`c`.`calle` AS `calle`,`c`.`cp` AS `cp`,`c`.`colonia` AS `colonia`,`c`.`municipio` AS `municipio`,`c`.`estado` AS `estado`,`c`.`idemex` AS `idemex`,`c`.`fechaNacimiento` AS `fechaNacimiento`,`c`.`curp` AS `curp`,`c`.`rfc` AS `rfc`,`c`.`telefono` AS `telefono`,`c`.`correo` AS `correo`,`c`.`comentarios` AS `comentarios`,`c`.`sexo` AS `sexo`,`c`.`fechaCreacion` AS `fechaCreacion`,`c`.`usuario` AS `usuario`,`c`.`fechaActualizacion` AS `fechaActualizacion`,`c`.`usuarioA` AS `usuarioA` from `cliente` `c` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `comercializacion_detallefullinfo`
--

/*!50001 DROP VIEW IF EXISTS `comercializacion_detallefullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `comercializacion_detallefullinfo` AS select `v`.`idDetalle` AS `idDetalle`,`v`.`idComercializacion` AS `idComercializacion`,`v`.`idContrato` AS `idContrato`,`v`.`idEmp` AS `idEmp`,`v`.`prenda` AS `prenda`,`v`.`precioComercializacion` AS `precioComercializacion` from `comercializacion_detalle` `v` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `egreso_activofullinfo`
--

/*!50001 DROP VIEW IF EXISTS `egreso_activofullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `egreso_activofullinfo` AS select `egreso`.`idEgreso` AS `idEgreso`,`egreso`.`cantidad` AS `cantidad`,`egreso`.`motivo` AS `motivo`,`egreso`.`observaciones` AS `observaciones`,`egreso`.`fechaCreacion` AS `fechaCreacion`,`egreso`.`usuario` AS `usuario`,`egreso`.`fechaModificacion` AS `fechaModificacion`,`egreso`.`usuarioA` AS `usuarioA`,`egreso`.`estatus` AS `estatus` from `egreso` where (`egreso`.`estatus` = 'Activo') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `egresofullinfo`
--

/*!50001 DROP VIEW IF EXISTS `egresofullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `egresofullinfo` AS select `egreso`.`idEgreso` AS `idEgreso`,`egreso`.`cantidad` AS `cantidad`,`egreso`.`motivo` AS `motivo`,`egreso`.`observaciones` AS `observaciones`,`egreso`.`fechaCreacion` AS `fechaCreacion`,`egreso`.`usuario` AS `usuario`,`egreso`.`fechaModificacion` AS `fechaModificacion`,`egreso`.`usuarioA` AS `usuarioA`,`egreso`.`estatus` AS `estatus` from `egreso` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `empfullinfo`
--

/*!50001 DROP VIEW IF EXISTS `empfullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `empfullinfo` AS select `e`.`idEmp` AS `idEmp`,`e`.`idCliente` AS `idCliente`,`e`.`fechaCreacion` AS `fechaCreacion`,`e`.`Observaciones` AS `observaciones`,`e`.`usuario` AS `usuario`,`e`.`idContrato` AS `idContrato`,`e`.`fechaActualizacion` AS `fechaActualizacion`,`e`.`interesPorcentaje` AS `interesPorcentaje`,`e`.`interes` AS `interes`,`e`.`almacenajePorcentaje` AS `almacenajePorcentaje`,`e`.`almacenaje` AS `almacenaje`,`e`.`periodos` AS `periodos`,`e`.`diasPeriodos` AS `diasPeriodos`,`e`.`iva` AS `iva`,`e`.`tasaComercializacion` AS `tasaComercializacion`,`e`.`estatus` AS `estatus` from `emp` `e` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ingreso_activofullinfo`
--

/*!50001 DROP VIEW IF EXISTS `ingreso_activofullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ingreso_activofullinfo` AS select `ingreso`.`idIngreso` AS `idIngreso`,`ingreso`.`cantidad` AS `cantidad`,`ingreso`.`motivo` AS `motivo`,`ingreso`.`observaciones` AS `observaciones`,`ingreso`.`fechaCreacion` AS `fechaCreacion`,`ingreso`.`usuario` AS `usuario`,`ingreso`.`fechaModificacion` AS `fechaModificacion`,`ingreso`.`usuarioA` AS `usuarioA`,`ingreso`.`estatus` AS `estatus` from `ingreso` where (`ingreso`.`estatus` = 'Activo') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ingresofullinfo`
--

/*!50001 DROP VIEW IF EXISTS `ingresofullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ingresofullinfo` AS select `ingreso`.`idIngreso` AS `idIngreso`,`ingreso`.`cantidad` AS `cantidad`,`ingreso`.`motivo` AS `motivo`,`ingreso`.`observaciones` AS `observaciones`,`ingreso`.`fechaCreacion` AS `fechaCreacion`,`ingreso`.`usuario` AS `usuario`,`ingreso`.`fechaModificacion` AS `fechaModificacion`,`ingreso`.`usuarioA` AS `usuarioA`,`ingreso`.`estatus` AS `estatus` from `ingreso` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `prendafullinfo`
--

/*!50001 DROP VIEW IF EXISTS `prendafullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `prendafullinfo` AS select `p`.`idPrendas` AS `idPrendas`,`p`.`nombre` AS `nombre`,`p`.`idEmp` AS `idEmp`,`p`.`categoria` AS `categoria`,`p`.`numPiezas` AS `numPiezas`,`p`.`serie` AS `serie`,`p`.`modelo` AS `modelo`,`p`.`subcategoria` AS `subcategoria`,`p`.`descripcion` AS `descripcion`,`p`.`metal` AS `metal`,`p`.`peso` AS `peso`,`p`.`kilataje` AS `kilataje`,`p`.`prestamo` AS `prestamo`,`p`.`precioComercializacion` AS `precioComercializacion`,`p`.`precioVenta` AS `precioVenta`,`p`.`estatus` AS `estatus`,`p`.`idComercializacion` AS `idComercializacion`,`p`.`fechaCreacion` AS `fechaCreacion`,`p`.`fechaComercializacion` AS `fechaComercializacion`,`p`.`fechaVenta` AS `fechaVenta`,`p`.`usuario` AS `usuario` from `prendas` `p` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `rol_activofullinfo`
--

/*!50001 DROP VIEW IF EXISTS `rol_activofullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `rol_activofullinfo` AS select `rol`.`idRol` AS `idRol`,`rol`.`nombreRol` AS `nombreRol`,`rol`.`estatus` AS `estatus` from `rol` where (`rol`.`estatus` = 'Activo') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `rolfullinfo`
--

/*!50001 DROP VIEW IF EXISTS `rolfullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `rolfullinfo` AS select `rol`.`idRol` AS `idRol`,`rol`.`nombreRol` AS `nombreRol`,`rol`.`estatus` AS `estatus` from `rol` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `usuariofullinfo`
--

/*!50001 DROP VIEW IF EXISTS `usuariofullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `usuariofullinfo` AS select `u`.`idUsuario` AS `idUsuario`,`u`.`nombre` AS `nombre`,`u`.`apellidoPaterno` AS `apellidoPaterno`,`u`.`apellidoMaterno` AS `apellidoMaterno`,`u`.`usuario` AS `usuario`,`u`.`password` AS `password`,`u`.`telefono` AS `telefono`,`u`.`estatus` AS `estatus`,`r`.`nombreRol` AS `rol` from (`usuario` `u` join `rol` `r` on((`r`.`nombreRol` = `u`.`rol`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ventas_remates_detallefullinfo`
--

/*!50001 DROP VIEW IF EXISTS `ventas_remates_detallefullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ventas_remates_detallefullinfo` AS select `v`.`idventasRemateDetalle` AS `idventasRemateDetalle`,`v`.`prenda` AS `prenda`,`v`.`idEmp` AS `idEmp`,`v`.`precio` AS `precio`,`v`.`iva` AS `iva`,`v`.`total` AS `total`,`v`.`prestamo` AS `prestamo`,`v`.`descripcionPrenda` AS `descripcionPrenda` from `ventas_remates_detalle` `v` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ventas_remates_fullinfo`
--

/*!50001 DROP VIEW IF EXISTS `ventas_remates_fullinfo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ventas_remates_fullinfo` AS select `v`.`idventasRemates` AS `idventasRemates`,`v`.`subtotal` AS `subtotal`,`v`.`iva` AS `iva`,`v`.`total` AS `total`,`v`.`estatus` AS `estatus`,`v`.`cliente` AS `cliente`,`v`.`usuario` AS `usuario`,`v`.`fechaVenta` AS `fechaVenta`,`v`.`totalPrendas` AS `totalPrendas`,`v`.`fechaCancelacion` AS `fechaCancelacion`,`v`.`usuarioCancelar` AS `usuarioCancelar`,`v`.`tipo` AS `tipo` from `ventas_remates` `v` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-28 22:10:42
