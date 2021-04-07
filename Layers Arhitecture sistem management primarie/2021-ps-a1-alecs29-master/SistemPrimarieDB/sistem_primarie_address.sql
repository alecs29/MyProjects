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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` varchar(255) NOT NULL,
  `addressName` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `NumbersOFRequests` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKda8tuywtf0gb6sedwk7la1pgi` (`user_id`),
  CONSTRAINT `FKda8tuywtf0gb6sedwk7la1pgi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES ('02bb8439-d0e5-487b-98ab-acd56d182c91','loc2','371fe4ac-de0f-47af-987e-7e55b10d1a7b',3),('3325c7ff-c192-4fce-ba58-4f4728d537f6','Alba Iulia, str. Randunicii nr. 2','0003088b-1ba5-40c1-9e3b-c4588dc11e92',3),('355bd1de-e035-4549-a574-5d675b3e8789','locunta4','697e2e8c-85de-4ed7-a62a-3a3ece708a2e',2),('38b85023-f61a-4afd-bc38-ea848a774b73','Alba Iulia, str. Ponor, nr.18','532728bd-4e48-4fa8-8e29-13564f017df6',3),('530ab440-a486-49a8-b114-1cc8be4d241d','Cluj-Napoca, str. Vlaicu, nr.33','697e2e8c-85de-4ed7-a62a-3a3ece708a2e',3),('80ec3ff5-e77b-4588-982b-afc5a5bebe9a','loc1','697e2e8c-85de-4ed7-a62a-3a3ece708a2e',2),('98c37c39-b203-42a4-9fe9-f6e1d31f5759','locunta3','697e2e8c-85de-4ed7-a62a-3a3ece708a2e',2),('cf432717-0dad-48aa-b792-6afc174a3a3c','Cluj Napoca, str. Goldis, nr. 2','e74df62c-5cb4-47f0-bc0c-530a877f60f3',3),('d379704a-52d6-4ed9-8a05-30166316d065','Alba Iulia, str. Randunicii nr. 12','0003088b-1ba5-40c1-9e3b-c4588dc11e92',3);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
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
