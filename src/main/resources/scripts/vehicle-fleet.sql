CREATE DATABASE  IF NOT EXISTS `vehicle-fleet` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `vehicle-fleet`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: vehicle-fleet
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` int(11) NOT NULL,
  `bus_id` int(11) NOT NULL,
  `driver_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `appointment1` (`driver_id`,`date`),
  UNIQUE KEY `UKtabe03qyf8kjp2hq31ixfb5wg` (`driver_id`,`date`),
  UNIQUE KEY `appointment` (`driver_id`,`date`),
  KEY `route_id` (`route_id`),
  KEY `bus_id` (`bus_id`),
  CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`),
  CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`id`),
  CONSTRAINT `appointment_ibfk_3` FOREIGN KEY (`driver_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,1,1,4,'2020-02-13','FINISHED'),(2,3,3,2,'2020-02-13','FINISHED'),(3,4,2,5,'2020-02-13','FINISHED'),(4,2,4,3,'2020-02-13','FINISHED'),(5,1,1,4,'2020-02-14','FINISHED'),(6,3,2,2,'2020-02-14','FINISHED'),(7,2,3,3,'2020-02-14','FINISHED'),(8,1,1,1,'2020-02-17','FINISHED'),(9,2,2,2,'2020-02-17','FINISHED'),(10,3,3,3,'2020-02-17','FINISHED'),(11,4,4,4,'2020-02-17','FINISHED'),(12,2,1,1,'2020-02-18','FINISHED'),(13,1,2,2,'2020-02-18','FINISHED'),(14,3,3,4,'2020-02-18','FINISHED'),(15,4,4,3,'2020-02-18','FINISHED'),(16,1,1,4,'2020-02-19','FINISHED'),(17,2,2,2,'2020-02-19','FINISHED'),(18,3,3,3,'2020-02-19','FINISHED'),(19,1,1,4,'2020-02-20','FINISHED'),(20,2,2,2,'2020-02-20','FINISHED'),(21,3,3,3,'2020-02-20','FINISHED'),(22,1,1,3,'2020-02-15','FINISHED'),(23,1,3,2,'2020-02-15','FINISHED'),(24,1,2,4,'2020-02-15','FINISHED'),(25,1,4,1,'2020-02-15','FINISHED'),(30,1,2,1,'2020-02-21','FINISHED'),(31,2,1,2,'2020-02-21','FINISHED'),(32,3,3,4,'2020-02-21','FINISHED'),(33,3,4,3,'2020-02-21','FINISHED'),(34,1,2,3,'2020-02-16','FINISHED'),(35,1,1,1,'2020-02-16','FINISHED'),(36,2,3,2,'2020-02-16','FINISHED'),(37,4,4,5,'2020-02-16','FINISHED'),(38,1,1,1,'2020-02-22','FINISHED'),(39,2,2,2,'2020-02-22','FINISHED'),(40,4,4,4,'2020-02-22','FINISHED'),(41,3,3,3,'2020-02-22','FINISHED'),(42,2,1,4,'2020-02-23','FINISHED'),(43,1,2,1,'2020-02-23','NEW');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mark` varchar(50) NOT NULL,
  `license_plate` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `license_plate` (`license_plate`),
  UNIQUE KEY `UKm77bxrb3rxk5t2ejgx82epsyd` (`license_plate`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (1,'Toyota','AA 1234 PP'),(2,'Mercedes-Benz','AA 6789 PP'),(3,'Volkswagen','AA 0147 PP'),(4,'Fiat','AA 6438 PP');
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_driver`
--

DROP TABLE IF EXISTS `bus_driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_driver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `driver_id` int(11) DEFAULT NULL,
  `bus_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bus_driver` (`driver_id`,`bus_id`),
  KEY `bus_id` (`bus_id`),
  CONSTRAINT `bus_driver_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `user` (`id`),
  CONSTRAINT `bus_driver_ibfk_2` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_driver`
--

LOCK TABLES `bus_driver` WRITE;
/*!40000 ALTER TABLE `bus_driver` DISABLE KEYS */;
INSERT INTO `bus_driver` VALUES (1,2,2),(2,2,3),(3,3,3),(4,3,4),(6,4,1),(5,4,4),(8,5,1),(7,5,2);
/*!40000 ALTER TABLE `bus_driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `departure_from_city` varchar(50) NOT NULL,
  `departure_from_city_uk` varchar(50) NOT NULL,
  `arrival_to_city` varchar(50) NOT NULL,
  `arrival_to_city_uk` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,102,'Prague','Прага','Kyiv','Київ'),(2,103,'Kyiv','Київ','Prague','Прага'),(3,104,'Warsaw','Варшава','Kyiv','Київ'),(4,105,'Kyiv','Київ','Warsaw','Варшава');
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `origin_first_name` varchar(20) NOT NULL,
  `origin_last_name` varchar(20) NOT NULL,
  `email` varchar(65) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Mikola','Ivanov','Микола','Іванов','ivanov@gmail.com','$2a$10$pQOGfl0lFp34yH.LVHa5KuIG2Ch8QPFcZSoiJDUKP8dx2qURr2C3u','ROLE_ADMIN'),(2,'Oleksandr','Ptushkin','Олександр','Птушкін','ptushkin@gmail.com','$2a$10$pQOGfl0lFp34yH.LVHa5KuIG2Ch8QPFcZSoiJDUKP8dx2qURr2C3u','ROLE_DRIVER'),(3,'Ivan','Tkach','Іван','Ткач','tkach@gmail.com','$2a$10$iUCLJMX3j5fJ68t41hIzUu6lF.eRoAHYoRLOgPBFrfsmhDJvxUTam','ROLE_DRIVER'),(4,'Maksim','Smilenko','Максим','Сміленко','smilenko@gmail.com','$2a$10$iUCLJMX3j5fJ68t41hIzUu6lF.eRoAHYoRLOgPBFrfsmhDJvxUTam','ROLE_DRIVER'),(5,'Andriy','Orel','Андрій','Орел','orel@gmail.com','$2a$10$iUCLJMX3j5fJ68t41hIzUu6lF.eRoAHYoRLOgPBFrfsmhDJvxUTam','ROLE_ADMIN'),(23,'Nazar','Yovko','Назар','Йовко','zinich@gmail.com','$2a$10$X96I2ymamVsCz65aUzsA9.GKxjISIRot/HAHqN5hoxQveACWCAPQ6','ROLE_DRIVER'),(40,'Sergiy','Bilous','Сергій','Білоус','bilous@gmail.com','$2a$10$Cckv8Gp/HDVhnqjsicgEIedIsa2vQ2XxOEGeBjAXl5yP9AzMslFjy','ROLE_DRIVER'),(43,'Artem','Voronin','Артем','Воронін','voronin@gmail.com','$2a$10$mNRjneo.HsPxd9m61fclAercs0WDbIE8HAXHB3q3IrSqMId2gsLOG','ROLE_DRIVER'),(44,'Artem','Ribak','Артем','Рибак','ribak@gmal.com','$2a$10$RsGwjjn0Iq8W3XbwguAjMOOcVkwRcEkH2VOGyV8z4gHyQpedcntOK','ROLE_DRIVER'),(45,'Sergiy','Topolya','Сергій','Тополя','driver@gmail.com','$2a$10$ks3DmGQuMFfTTcRxhyCuieTy5Z7UNMThp2RLwgK1Ub/0n4KyPc1yW','ROLE_DRIVER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-10 18:27:40