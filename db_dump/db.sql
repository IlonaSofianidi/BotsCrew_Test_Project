CREATE DATABASE  IF NOT EXISTS `botscrew_test_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `botscrew_test_db`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: botscrew_test_db
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `departments` (
  `id_department` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(45) NOT NULL,
  `head_of_department_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_department`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'Human Resources Development','Ivan Ivanov'),(14,'Ukrainian language department','Alice Bard'),(15,'Accounting Division',' Jasmin Brush'),(18,'Anesthesiology','Yu Marvel'),(21,'Italian language department','Angeline Dark'),(22,'Anesthesiology',' Cori Rackham'),(23,'Anthropology','Jaimie Landaverde');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments_lectors`
--

DROP TABLE IF EXISTS `departments_lectors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `departments_lectors` (
  `id_department` int(11) NOT NULL,
  `id_lector` int(11) NOT NULL,
  KEY `id_lector_idx` (`id_lector`),
  KEY `id_department_idx` (`id_department`),
  CONSTRAINT `id_department` FOREIGN KEY (`id_department`) REFERENCES `departments` (`id_department`),
  CONSTRAINT `id_lector` FOREIGN KEY (`id_lector`) REFERENCES `lectors` (`id_lector`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments_lectors`
--

LOCK TABLES `departments_lectors` WRITE;
/*!40000 ALTER TABLE `departments_lectors` DISABLE KEYS */;
INSERT INTO `departments_lectors` VALUES (14,13),(15,14),(18,15),(21,18),(1,19),(1,18),(1,3),(1,13),(14,18),(14,3),(14,19),(15,13),(15,19),(21,3),(21,14),(21,15),(21,19),(22,18),(22,14),(22,15),(23,14);
/*!40000 ALTER TABLE `departments_lectors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lectors`
--

DROP TABLE IF EXISTS `lectors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lectors` (
  `id_lector` int(11) NOT NULL AUTO_INCREMENT,
  `lector_name` varchar(45) NOT NULL,
  `degree` enum('ASSISTANT','ASSOCIATE_PROFESSOR','PROFESSOR') NOT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_lector`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lectors`
--

LOCK TABLES `lectors` WRITE;
/*!40000 ALTER TABLE `lectors` DISABLE KEYS */;
INSERT INTO `lectors` VALUES (3,'Oleg Vinuk','ASSISTANT',12560.00),(13,'Mad Daymon','PROFESSOR',10000.00),(14,'Call Twice','PROFESSOR',103000.00),(15,'Call Twice','PROFESSOR',103000.00),(18,'Anna','ASSOCIATE_PROFESSOR',2333.00),(19,'Byron Rote','ASSOCIATE_PROFESSOR',542218.00);
/*!40000 ALTER TABLE `lectors` ENABLE KEYS */;
UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-31 17:48:15
