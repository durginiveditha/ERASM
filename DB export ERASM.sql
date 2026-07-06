CREATE DATABASE  IF NOT EXISTS `erasm_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `erasm_db`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: erasm_db
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Table structure for table `allocations`
--

DROP TABLE IF EXISTS `allocations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allocations` (
  `allocation_percentage` double NOT NULL,
  `allocation_id` bigint NOT NULL AUTO_INCREMENT,
  `employee_id` bigint NOT NULL,
  `project_id` bigint NOT NULL,
  `allocation_date` varchar(255) NOT NULL,
  `release_date` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`allocation_id`),
  KEY `FKlidedpimhddtisgg66kca1to7` (`employee_id`),
  KEY `FKlgyvi1ifhphug0govaqwnsqyf` (`project_id`),
  CONSTRAINT `FKlgyvi1ifhphug0govaqwnsqyf` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`),
  CONSTRAINT `FKlidedpimhddtisgg66kca1to7` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allocations`
--

LOCK TABLES `allocations` WRITE;
/*!40000 ALTER TABLE `allocations` DISABLE KEYS */;
INSERT INTO `allocations` VALUES (75,1,1,2,'2026-07-06','2027-01-31','ALLOCATED');
/*!40000 ALTER TABLE `allocations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit_logs`
--

DROP TABLE IF EXISTS `audit_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit_logs` (
  `audit_id` bigint NOT NULL AUTO_INCREMENT,
  `action` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` varchar(255) NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `modified_date` varchar(255) NOT NULL,
  PRIMARY KEY (`audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_logs`
--

LOCK TABLES `audit_logs` WRITE;
/*!40000 ALTER TABLE `audit_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `audit_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certifications`
--

DROP TABLE IF EXISTS `certifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certifications` (
  `certification_id` bigint NOT NULL AUTO_INCREMENT,
  `employee_id` bigint NOT NULL,
  `certification_name` varchar(255) NOT NULL,
  `expiry_date` varchar(255) DEFAULT NULL,
  `issue_date` varchar(255) NOT NULL,
  `issuing_organization` varchar(255) NOT NULL,
  PRIMARY KEY (`certification_id`),
  KEY `FK8jk3p8d9bytcopb1mb9yb3to9` (`employee_id`),
  CONSTRAINT `FK8jk3p8d9bytcopb1mb9yb3to9` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certifications`
--

LOCK TABLES `certifications` WRITE;
/*!40000 ALTER TABLE `certifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `certifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_skills`
--

DROP TABLE IF EXISTS `employee_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_skills` (
  `experience_years` int NOT NULL,
  `employee_id` bigint NOT NULL,
  `employee_skill_id` bigint NOT NULL AUTO_INCREMENT,
  `skill_id` bigint NOT NULL,
  `skill_level` varchar(255) NOT NULL,
  PRIMARY KEY (`employee_skill_id`),
  KEY `FKf1412wfxc2xjq9gbfm6v0op0` (`employee_id`),
  KEY `FK8anwsnenk9d8nirjuov0ywinb` (`skill_id`),
  CONSTRAINT `FK8anwsnenk9d8nirjuov0ywinb` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`skill_id`),
  CONSTRAINT `FKf1412wfxc2xjq9gbfm6v0op0` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_skills`
--

LOCK TABLES `employee_skills` WRITE;
/*!40000 ALTER TABLE `employee_skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `availability` bit(1) NOT NULL,
  `experience` int NOT NULL,
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `department` varchar(255) NOT NULL,
  `designation` varchar(255) NOT NULL,
  `employee_code` varchar(255) NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `UKetqhw9qqnad1kyjq3ks1glw8x` (`employee_code`),
  UNIQUE KEY `UKj2dmgsma6pont6kf7nic9elpd` (`user_id`),
  KEY `FKah490190ww1q2a4piuv41hk6e` (`role_id`),
  CONSTRAINT `FK69x3vjuy1t5p18a5llb8h2fjx` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKah490190ww1q2a4piuv41hk6e` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (_binary '',5,1,1,1,'IT','Administrator','EMP001'),(_binary '',4,3,2,2,'Development','Senior Developer','EMP002');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `budget` double NOT NULL,
  `project_id` bigint NOT NULL AUTO_INCREMENT,
  `client_name` varchar(255) NOT NULL,
  `end_date` varchar(255) NOT NULL,
  `project_name` varchar(255) NOT NULL,
  `start_date` varchar(255) NOT NULL,
  `technology_stack` varchar(255) NOT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (500000,2,'ABC Technologies','2026-12-31','Hospital Management System','2026-07-06','Java, Spring Boot, React');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_requests`
--

DROP TABLE IF EXISTS `resource_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource_requests` (
  `required_count` int NOT NULL,
  `project_id` bigint NOT NULL,
  `request_id` bigint NOT NULL AUTO_INCREMENT,
  `required_skill` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`request_id`),
  KEY `FKtghqmt481tvabqx37bfp8ueoh` (`project_id`),
  CONSTRAINT `FKtghqmt481tvabqx37bfp8ueoh` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_requests`
--

LOCK TABLES `resource_requests` WRITE;
/*!40000 ALTER TABLE `resource_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK716hgxp60ym1lifrdgp67xt5k` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(4,'DELIVERY_MANAGER'),(2,'EMPLOYEE'),(3,'RESOURCE_MANAGER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skills` (
  `skill_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `skill_name` varchar(255) NOT NULL,
  PRIMARY KEY (`skill_id`),
  UNIQUE KEY `UKqdkr64bxreqdbn6b4k9vumvp3` (`skill_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `active` bit(1) NOT NULL,
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (_binary '',1,'admin@test.com','Admin','User','$2a$10$x6eJ7aiSJRNUTypL4zOWHOiXxBAzrJe6peWysfojFIwcbM9VzH98e'),(_binary '',2,'rahul@test.com','Rahul','Kumar','$2a$10$yxWDgftNrybnN3hYe5TBKeMioXmhM6XbKpwGuPjkj.O6w0F6bnPSy');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-06 22:30:03
