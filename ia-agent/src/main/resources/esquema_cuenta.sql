-- MySQL dump 10.13  Distrib 9.5.0, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cuenta
-- ------------------------------------------------------
-- Server version	8.4.7

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `billetera`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billetera` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estado_cuenta` enum('ACTIVA','ANULADA') DEFAULT NULL,
  `fecha_registro` datetime(6) DEFAULT NULL,
  `premium` bit(1) NOT NULL,
  `saldo` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billetera`
--

INSERT INTO `billetera` VALUES (2,'ACTIVA','2025-11-09 09:30:00.000000',_binary '\0',40500);
INSERT INTO `billetera` VALUES (4,'ACTIVA','2025-11-09 09:30:00.000000',_binary '\0',0);
INSERT INTO `billetera` VALUES (5,'ACTIVA','2025-01-10 14:30:00.000000',_binary '\0',0);
INSERT INTO `billetera` VALUES (6,'ACTIVA','2025-02-01 09:15:20.000000',_binary '',1500.5);

--
-- Table structure for table `carga`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carga` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) DEFAULT NULL,
  `id_usuario` bigint NOT NULL,
  `monto` float NOT NULL,
  `id_billetera` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2m5o804tager3k9wyvirkd804` (`id_billetera`),
  CONSTRAINT `FK2m5o804tager3k9wyvirkd804` FOREIGN KEY (`id_billetera`) REFERENCES `billetera` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carga`
--


--
-- Table structure for table `usuario`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) NOT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `mercado_pago` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` enum('ADMINISTRADOR','USUARIO') NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` VALUES (1,'Cordoba','5491123456789','marcos.cordoba@example.com','MP123456789','marqui bueno','MiClaveSegura123','USUARIO','marcoscordoba');
INSERT INTO `usuario` VALUES (5,'Cordoba','5491123456789','marcos.cordoba@example.com','MP123456789','Marcos','MiClaveSegura123','ADMINISTRADOR','Maximo');

--
-- Table structure for table `usuario_billetera`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_billetera` (
  `usuario_id` bigint NOT NULL,
  `billetera_id` bigint NOT NULL,
  KEY `FKmt13dp6esowry73wx5b51l0nd` (`billetera_id`),
  KEY `FKqnmw8bbtbhg29h1wha70cco3h` (`usuario_id`),
  CONSTRAINT `FKmt13dp6esowry73wx5b51l0nd` FOREIGN KEY (`billetera_id`) REFERENCES `billetera` (`id`),
  CONSTRAINT `FKqnmw8bbtbhg29h1wha70cco3h` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_billetera`
--

INSERT INTO `usuario_billetera` VALUES (1,2);
INSERT INTO `usuario_billetera` VALUES (1,2);
INSERT INTO `usuario_billetera` VALUES (1,2);
INSERT INTO `usuario_billetera` VALUES (1,2);
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-19 20:07:47
