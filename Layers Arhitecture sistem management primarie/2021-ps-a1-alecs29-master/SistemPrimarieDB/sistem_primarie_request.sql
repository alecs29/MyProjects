-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sistem_primarie
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `id` varchar(255) NOT NULL,
  `requestName` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `address_id` varchar(255) DEFAULT NULL,
  `approved` varchar(255) DEFAULT NULL,
  `requestDate` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqws2fdeknk90txm7qnm9bxd07` (`user_id`),
  KEY `FK5lgq31tbbs7hag7npu31ha3l4` (`address_id`),
  CONSTRAINT `FK5lgq31tbbs7hag7npu31ha3l4` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKqws2fdeknk90txm7qnm9bxd07` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES ('04e9ed85-268b-4fb4-8b23-00ed49b751ea','document2','697e2e8c-85de-4ed7-a62a-3a3ece708a2e','80ec3ff5-e77b-4588-982b-afc5a5bebe9a','approved',2021),('488830c1-fb7c-41c5-b3f6-a9b4c03c9863','document2','697e2e8c-85de-4ed7-a62a-3a3ece708a2e','98c37c39-b203-42a4-9fe9-f6e1d31f5759','pending',2021),('700ef8e5-672f-48ca-84e2-9ebf8fed9e3d','document1','697e2e8c-85de-4ed7-a62a-3a3ece708a2e','530ab440-a486-49a8-b114-1cc8be4d241d','pending',2021),('8431092c-1757-4721-9024-e12c4e720065','document1','0003088b-1ba5-40c1-9e3b-c4588dc11e92','3325c7ff-c192-4fce-ba58-4f4728d537f6','pending',2021),('887b1a3e-2b5b-431e-8e51-fb8e3d462b47','document1','697e2e8c-85de-4ed7-a62a-3a3ece708a2e','355bd1de-e035-4549-a574-5d675b3e8789','approved',2021),('9de1a251-ef2a-41e0-9b72-b911487422e1','document1','697e2e8c-85de-4ed7-a62a-3a3ece708a2e','98c37c39-b203-42a4-9fe9-f6e1d31f5759','pending',2021),('ad2eea98-3c82-4817-b75b-b1298c336e5b','document2','697e2e8c-85de-4ed7-a62a-3a3ece708a2e','355bd1de-e035-4549-a574-5d675b3e8789','approved',2021),('bb9736fd-561e-4995-be5c-825e0be73c72','document1','697e2e8c-85de-4ed7-a62a-3a3ece708a2e','80ec3ff5-e77b-4588-982b-afc5a5bebe9a','pending',2021);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-26  2:12:20
