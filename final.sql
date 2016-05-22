-- MySQL dump 10.13  Distrib 5.6.30, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: payments
-- ------------------------------------------------------
-- Server version	5.6.30-0ubuntu0.15.10.1

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `act_name` varchar(45) NOT NULL,
  `pass` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `act_name_UNIQUE` (`act_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,'Sasha','$2a$10$Ogw1kABgMFEdtq6GPwE9H.oXbCUGo3NyCjqb2f7.4H7Y9gpC5KyCq
'),(2,'Masha','$2a$10$Ogw1kABgMFEdtq6GPwE9H.oXbCUGo3NyCjqb2f7.4H7Y9gpC5KyCq
'),(3,'Sasha2','$2a$10$Ogw1kABgMFEdtq6GPwE9H.oXbCUGo3NyCjqb2f7.4H7Y9gpC5KyCq
'),(4,'Masha2','$2a$10$Ogw1kABgMFEdtq6GPwE9H.oXbCUGo3NyCjqb2f7.4H7Y9gpC5KyCq
');
/*bCrypt(123) = [$2a$10$Ogw1kABgMFEdtq6GPwE9H.oXbCUGo3NyCjqb2f7.4H7Y9gpC5KyCq] */
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_role`
--

DROP TABLE IF EXISTS `actor_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `actor_id` int(10) unsigned NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_id_role` (`role`,`actor_id`),
  KEY `fk_actor_roles_idx` (`actor_id`),
  CONSTRAINT `fk_userid` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_role`
--

LOCK TABLES `actor_role` WRITE;
/*!40000 ALTER TABLE `actor_role` DISABLE KEYS */;
INSERT INTO `actor_role` VALUES (1,1,'ROLE_ADMIN'),(5,4,'ROLE_ADMIN'),(2,1,'ROLE_CLIENT'),(3,2,'ROLE_CLIENT'),(4,3,'ROLE_CLIENT');
/*!40000 ALTER TABLE `actor_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `isBlocked` bit(1) NOT NULL,
  `score` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,'\0',400),(2,'\0',600),(3,'',700),(4,'\0',200);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `card_name` varchar(45) NOT NULL DEFAULT '',
  `id_bill` int(10) unsigned NOT NULL,
  `id_actor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_bill_UNIQUE` (`id_bill`),
  KEY `Card-Bill` (`id_bill`),
  CONSTRAINT `card_bill` FOREIGN KEY (`id_bill`) REFERENCES `bill` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'card',1,1),(2,'card2',2,1),(3,'card3',3,2),(4,'card4',4,3);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-20 18:22:23
