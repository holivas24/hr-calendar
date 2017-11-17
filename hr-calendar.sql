-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: herocash
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Campus`
--

DROP TABLE IF EXISTS `Campus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Campus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Campus`
--

LOCK TABLES `Campus` WRITE;
/*!40000 ALTER TABLE `Campus` DISABLE KEYS */;
INSERT INTO `Campus` VALUES (1,'East'),(2,'Northeast'),(3,'West'),(4,'Chihuahua');
/*!40000 ALTER TABLE `Campus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Nivel`
--

DROP TABLE IF EXISTS `Nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Nivel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `numero` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Nivel`
--

LOCK TABLES `Nivel` WRITE;
/*!40000 ALTER TABLE `Nivel` DISABLE KEYS */;
INSERT INTO `Nivel` VALUES (1,'Superusuario',1),(2,'Administrador Global',2),(3,'Administrador de Campus',3),(4,'Usuario',4),(5,'Heroe',5);
/*!40000 ALTER TABLE `Nivel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Registros`
--

DROP TABLE IF EXISTS `Registros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Registros` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` bigint(20) NOT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `log` varchar(255) DEFAULT NULL,
  `admin_id` bigint(20) DEFAULT NULL,
  `hero_id` bigint(20) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l37tgr4uywc8y76dybt6fpn2j` (`admin_id`),
  KEY `FK_f8n6o294ju0dlyi838gb3hfdr` (`hero_id`),
  CONSTRAINT `FK_f8n6o294ju0dlyi838gb3hfdr` FOREIGN KEY (`hero_id`) REFERENCES `Usuario` (`id`),
  CONSTRAINT `FK_l37tgr4uywc8y76dybt6fpn2j` FOREIGN KEY (`admin_id`) REFERENCES `Usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Registros`
--

LOCK TABLES `Registros` WRITE;
/*!40000 ALTER TABLE `Registros` DISABLE KEYS */;
/*!40000 ALTER TABLE `Registros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `puntos` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `campus_id` bigint(20) DEFAULT NULL,
  `nivel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_60oip607la1lppw1wbynw5la8` (`campus_id`),
  KEY `FK_87kkgmk1jw5ogcawkaq222jlx` (`nivel_id`),
  CONSTRAINT `FK_60oip607la1lppw1wbynw5la8` FOREIGN KEY (`campus_id`) REFERENCES `Campus` (`id`),
  CONSTRAINT `FK_87kkgmk1jw5ogcawkaq222jlx` FOREIGN KEY (`nivel_id`) REFERENCES `Nivel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,'Olivas','Hector','827ccb0eea8a706c4c34a16891f84e7b',0,'heoli',4,1),(6,'Richards','Sarita','5876aa3d665344f263865515364f98cd',0,'saric60',4,2),(9,'Sigala','Daniel','68053af2923e00204c3ca7c6a3150cf7',0,'dasig93',1,3),(10,'Sigala','Cristy','202cb962ac59075b964b07152d234b70',0,'crsig28',1,3),(11,'Avalos','Lina','d8578edf8458ce06fbc5bb76a58c5ca4',0,'liava32',1,2),(12,'Ortega','Ivan','040b7cf4a55014e185813e0644502ea9',0,'ivort67',1,3),(13,'Rodriguez','Rafael','eb89f40da6a539dd1b1776e522459763',0,'rarod96',4,3),(15,'Molinda','Lizbeth','6bd8f459218a9e6c729108aaa72c2b74',0,'limol92',1,5),(16,'Juarez','Annahi','6bd8f459218a9e6c729108aaa72c2b74',0,'anjua91',1,5),(17,'Mora','Montse','6bd8f459218a9e6c729108aaa72c2b74',0,'momor91',1,5),(18,'Chavez','Pamela','6bd8f459218a9e6c729108aaa72c2b74',0,'pacha2',1,5),(19,'Cabral','Jacob','6bd8f459218a9e6c729108aaa72c2b74',0,'jacab51',1,5),(20,'Cabral','Kevin','6bd8f459218a9e6c729108aaa72c2b74',0,'kecab7',1,5),(21,'Sandoval','Itzel','6bd8f459218a9e6c729108aaa72c2b74',0,'itsan22',1,5),(22,'Cardona','Natalia','6bd8f459218a9e6c729108aaa72c2b74',0,'nacar64',1,5),(23,'Buendia','Astrid','6bd8f459218a9e6c729108aaa72c2b74',0,'asbue60',1,5),(24,'Gomez','Andres','6bd8f459218a9e6c729108aaa72c2b74',0,'angom45',1,5),(25,'Ortega','Daisy','6bd8f459218a9e6c729108aaa72c2b74',0,'daort43',1,5),(26,'Mendoza','Rudy','6bd8f459218a9e6c729108aaa72c2b74',0,'rumen33',1,5),(27,'Mendoza','Andrea','6bd8f459218a9e6c729108aaa72c2b74',0,'anmen27',1,5),(28,'De Los Rios','Alejandra','6bd8f459218a9e6c729108aaa72c2b74',0,'alde 36',1,5),(29,'Rodriguez','Ismael','6bd8f459218a9e6c729108aaa72c2b74',0,'isrod71',1,5),(30,'Almeraz','Noemi','6bd8f459218a9e6c729108aaa72c2b74',0,'noalm17',1,5),(31,'Jaramillo','Ruth','6bd8f459218a9e6c729108aaa72c2b74',0,'rujar59',1,5),(32,'Santa Cruz','Aaron','6bd8f459218a9e6c729108aaa72c2b74',0,'aasan52',1,5),(33,'Portillo','Evelyn','6bd8f459218a9e6c729108aaa72c2b74',0,'evpor44',1,5),(34,'Alvarez','Nubia','6bd8f459218a9e6c729108aaa72c2b74',0,'nualv20',1,5),(35,'Hernandez','Irene','6bd8f459218a9e6c729108aaa72c2b74',0,'irher36',1,5),(36,'Quezada','Miguel','6bd8f459218a9e6c729108aaa72c2b74',0,'mique40',1,5),(37,'Perez','Karla','6bd8f459218a9e6c729108aaa72c2b74',0,'kaper75',1,5),(38,'Perez','Moises','6bd8f459218a9e6c729108aaa72c2b74',0,'moper1',1,5),(39,'Perales','Daniela','6bd8f459218a9e6c729108aaa72c2b74',0,'daper5',1,5),(40,'Munoz','Victoria','6bd8f459218a9e6c729108aaa72c2b74',0,'vimun24',1,5),(41,'Perales','Isaac','6bd8f459218a9e6c729108aaa72c2b74',0,'isper87',1,5),(42,'Bolly','Tania','6bd8f459218a9e6c729108aaa72c2b74',0,'tabol60',1,5),(43,'Garcia','Dora','6bd8f459218a9e6c729108aaa72c2b74',0,'dogar78',1,5),(44,'Garcia','Nadia','6bd8f459218a9e6c729108aaa72c2b74',0,'nagar14',1,5),(45,'Herrera','Valeria','6bd8f459218a9e6c729108aaa72c2b74',0,'vaher51',1,5),(46,'Sigala','Raomi','6bd8f459218a9e6c729108aaa72c2b74',0,'rasig66',1,5),(47,'Bull Valles','Fernando','6bd8f459218a9e6c729108aaa72c2b74',0,'febul2',1,5);
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-02 17:03:09
