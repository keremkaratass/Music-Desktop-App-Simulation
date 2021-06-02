-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: baglanti
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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `album` (
  `id` double NOT NULL,
  `album_ad` varchar(45) NOT NULL,
  `album_turu` varchar(45) DEFAULT NULL,
  `album_tarih` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `album_turu_idx` (`album_turu`),
  CONSTRAINT `album_turu` FOREIGN KEY (`album_turu`) REFERENCES `tur` (`tur_ad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (1,'say plays say','klasik','05.05.2014'),(2,'come fly with me','jazz','04.08.1958'),(3,'what a wonderful world','jazz','14.07.1968'),(4,'revival','pop','21.12.2015'),(5,'21','pop','04.02.2011'),(6,'aventine',NULL,'06.07.2014'),(7,'citizen of glass',NULL,'22.11.2016'),(8,'amir','pop','04.11.2018'),(9,'ölürüm sana','pop','14.04.1997'),(10,'karma','pop','11.08.2001'),(11,'mesafe','pop','15.09.2006'),(12,'nocturnes','klasik','1830'),(13,'symphonies','klasik','1800'),(14,'ı put a spell on you','jazz','1965');
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `album_sanatci`
--

DROP TABLE IF EXISTS `album_sanatci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `album_sanatci` (
  `id` double NOT NULL,
  `album_id` double NOT NULL,
  `sanatci_id` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `album_id_idx` (`album_id`),
  KEY `sanatci_id_idx` (`sanatci_id`),
  CONSTRAINT `album_id_idx` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sanatci_id_idx` FOREIGN KEY (`sanatci_id`) REFERENCES `sanatci` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album_sanatci`
--

LOCK TABLES `album_sanatci` WRITE;
/*!40000 ALTER TABLE `album_sanatci` DISABLE KEYS */;
INSERT INTO `album_sanatci` VALUES (1,1,2),(2,2,3),(3,3,4),(4,4,5),(5,5,1),(6,6,7),(7,7,7),(8,8,9),(9,9,11),(10,10,11),(11,11,12),(12,12,13),(13,13,14),(14,14,15);
/*!40000 ALTER TABLE `album_sanatci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arama`
--

DROP TABLE IF EXISTS `arama`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arama` (
  `id` int NOT NULL AUTO_INCREMENT,
  `arama_id` double NOT NULL,
  `arama_ad` varchar(45) NOT NULL,
  `arama_tablo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=397 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arama`
--

LOCK TABLES `arama` WRITE;
/*!40000 ALTER TABLE `arama` DISABLE KEYS */;
INSERT INTO `arama` VALUES (1,0,'admin','kullanici'),(2,1,'erenguner41','kullanici'),(3,2,'neseguner41','kullanici'),(4,3,'metinguner41','kullanici'),(5,4,'deneme','kullanici'),(6,5,'deneme2','kullanici'),(7,6,'deneme3','kullanici'),(8,7,'deneme4','kullanici'),(9,8,'fatihguner41','kullanici'),(10,9,'dogukan','kullanici'),(11,10,'melih','kullanici'),(12,11,'faruk','kullanici'),(13,1,'kumru','sarki'),(14,2,'ses','sarki'),(15,3,'kara toprak','sarki'),(16,4,'sevenlere dair','sarki'),(17,5,'nazım','sarki'),(18,6,'come fly with me','sarki'),(19,7,'chicago','sarki'),(20,8,'moonlight in vermont','sarki'),(21,9,'what a wonderful world','sarki'),(22,10,'dream a little dream of me','sarki'),(23,11,'same old love','sarki'),(24,12,'kill em with kindness','sarki'),(25,13,'good for you','sarki'),(26,14,'rolling in the deep','sarki'),(27,15,'set fire to the rain','sarki'),(28,16,'someone like you','sarki'),(29,17,'tokka','sarki'),(30,18,'words are dead','sarki'),(31,19,'golden green','sarki'),(32,20,'mary','sarki'),(33,21,'indigo night','sarki'),(34,22,'cigar','sarki'),(35,23,'habibi','sarki'),(36,24,'tummy','sarki'),(37,25,'şımarık','sarki'),(38,26,'ikimizin yerine','sarki'),(39,27,'ölürüm sana','sarki'),(40,28,'salına salına sinsice','sarki'),(41,29,'inci tanem','sarki'),(42,30,'aşk','sarki'),(43,31,'ay','sarki'),(44,32,'kuzu kuzu','sarki'),(45,33,'sen başkasın','sarki'),(46,34,'sor','sarki'),(47,35,'dansöz','sarki'),(48,36,'mesafe','sarki'),(49,37,'gitme','sarki'),(50,38,'nocturne no1','sarki'),(51,39,'nocturne no2','sarki'),(52,40,'nocturne no8','sarki'),(53,41,'nocturne no3','sarki'),(54,42,'moonlight sonata','sarki'),(55,43,'symphony no5','sarki'),(56,44,'für elise','sarki'),(57,45,'feeling good','sarki'),(58,46,'take care of business','sarki'),(59,47,'ı put a spell on you','sarki'),(60,48,'ne me quitte pas','sarki'),(61,49,'cheek to cheek','sarki'),(62,50,'anything goes','sarki'),(63,51,'anything goes','sarki'),(64,1,'adele','sanatci'),(65,2,'fazil say','sanatci'),(66,3,'frank sinatra','sanatci'),(67,4,'louis armstrong','sanatci'),(68,5,'selena gomez','sanatci'),(69,6,'asap rocky','sanatci'),(70,7,'agnes obel','sanatci'),(71,8,'lana del rey','sanatci'),(72,9,'tamino','sanatci'),(73,10,'evgeny grinko','sanatci'),(74,11,'tarkan','sanatci'),(75,12,'serdar ortaç','sanatci'),(76,13,'chopin','sanatci'),(77,14,'beethoven','sanatci'),(78,15,'nina simone','sanatci'),(79,16,'tony bennett','sanatci'),(80,17,'lady gaga','sanatci'),(81,1,'say plays say','album'),(82,2,'come fly with me','album'),(83,3,'what a wonderful world','album'),(84,4,'revival','album'),(85,5,'21','album'),(86,6,'aventine','album'),(87,7,'citizen of glass','album'),(88,8,'amir','album'),(89,9,'ölürüm sana','album'),(90,10,'karma','album'),(91,11,'mesafe','album'),(92,12,'nocturnes','album'),(93,13,'symphonies','album'),(94,14,'ı put a spell on you','album'),(95,15,'cheek to cheek','album'),(96,0,'pop','calma_liste'),(97,1,'jazz','calma_liste'),(98,2,'klasik','calma_liste'),(99,3,'pop','calma_liste'),(100,4,'jazz','calma_liste'),(101,5,'klasik','calma_liste'),(102,6,'pop','calma_liste'),(103,7,'jazz','calma_liste'),(104,8,'klasik','calma_liste'),(105,9,'pop','calma_liste'),(106,10,'jazz','calma_liste'),(107,11,'klasik','calma_liste'),(108,12,'pop','calma_liste'),(109,13,'jazz','calma_liste'),(110,14,'klasik','calma_liste'),(111,15,'pop','calma_liste'),(112,16,'jazz','calma_liste'),(113,17,'klasik','calma_liste'),(114,18,'pop','calma_liste'),(115,19,'jazz','calma_liste'),(116,20,'klasik','calma_liste'),(117,21,'pop','calma_liste'),(118,22,'jazz','calma_liste'),(119,23,'klasik','calma_liste'),(120,24,'yeni liste','calma_liste'),(121,25,'denemeekjkl','calma_liste'),(122,26,'deneme','calma_liste'),(123,27,'pop','calma_liste'),(124,28,'jazz','calma_liste'),(125,29,'klasik','calma_liste'),(126,30,'pop','calma_liste'),(127,31,'jazz','calma_liste'),(128,32,'klasik','calma_liste'),(129,33,'pop','calma_liste'),(130,34,'jazz','calma_liste'),(131,35,'klasik','calma_liste');
/*!40000 ALTER TABLE `arama` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calma_liste`
--

DROP TABLE IF EXISTS `calma_liste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calma_liste` (
  `id` double NOT NULL,
  `calma_liste_ad` varchar(45) NOT NULL,
  `kullanici_id` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `kullanici_id_idx` (`kullanici_id`),
  CONSTRAINT `kullanici_id` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calma_liste`
--

LOCK TABLES `calma_liste` WRITE;
/*!40000 ALTER TABLE `calma_liste` DISABLE KEYS */;
INSERT INTO `calma_liste` VALUES (0,'pop',1),(1,'jazz',1),(2,'klasik',1),(3,'pop',2),(4,'jazz',2),(5,'klasik',2),(6,'pop',3),(7,'jazz',3),(8,'klasik',3),(9,'pop',4),(10,'jazz',4),(11,'klasik',4),(12,'pop',5),(13,'jazz',5),(14,'klasik',5),(15,'pop',6),(16,'jazz',6),(17,'klasik',6),(18,'pop',7),(19,'jazz',7),(20,'klasik',7),(21,'pop',8),(22,'jazz',8),(23,'klasik',8),(24,'yeni liste',1),(25,'denemeekjkl',4),(26,'deneme',9),(27,'pop',9),(28,'jazz',9),(29,'klasik',9),(30,'pop',10),(31,'jazz',10),(32,'klasik',10),(33,'pop',11),(34,'jazz',11),(35,'klasik',11);
/*!40000 ALTER TABLE `calma_liste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kullanici`
--

DROP TABLE IF EXISTS `kullanici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kullanici` (
  `id` double NOT NULL,
  `uyelik_tip_ad` varchar(45) DEFAULT NULL,
  `ulke_ad` varchar(45) DEFAULT NULL,
  `kullanici_ad` varchar(45) NOT NULL,
  `kullanici_email` varchar(45) NOT NULL,
  `kullanici_sifre` varchar(45) NOT NULL,
  `odemeli_mi` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `kullanici_ad_UNIQUE` (`kullanici_ad`),
  UNIQUE KEY `kullanici_email_UNIQUE` (`kullanici_email`),
  KEY `uyelik_tip_ad_idx` (`uyelik_tip_ad`),
  KEY `ulke_ad_idx_idx` (`ulke_ad`),
  CONSTRAINT `ulke_ad_idx` FOREIGN KEY (`ulke_ad`) REFERENCES `ulke` (`ulke_ad`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uyelik_tip_ad` FOREIGN KEY (`uyelik_tip_ad`) REFERENCES `uyelik_tip` (`uyelik_tip_ad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kullanici`
--

LOCK TABLES `kullanici` WRITE;
/*!40000 ALTER TABLE `kullanici` DISABLE KEYS */;
INSERT INTO `kullanici` VALUES (0,'admin','turkiye','admin','admin@hotmail.com','123','odemesiz'),(1,'premium','turkiye','erenguner41','eren_guner@hotmail.com','123321fg','odemeli'),(2,'normal','turkiye','neseguner41','nese_guner@hotmail.com','123321fg','odemesiz'),(3,'normal','turkiye','metinguner41','metin_guner@hotmail.com','123321fg','odemesiz'),(4,'normal','danimarka','deneme','deneme@hotmail.com','123321fg','odemesiz'),(5,'normal','turkiye','deneme2','deneme2@hotmail.com','123321fg','odemesiz'),(6,'normal','turkiye','deneme3','deneme3@hotmail.com','123321fg','odemesiz'),(7,'normal','turkiye','deneme4','deneme4@hotmail.com','123321fg','odemesiz'),(8,'premium','turkiye','fatihguner41','fatihguner41@hotmail.com','123321fg','odemeli'),(9,'premium','turkiye','dogukan','dogukan@hotmail.com','123321fg','odemeli'),(10,'premium','turkiye','melih','melih@hotmail.com','123321fg','odemeli'),(11,'premium','amerika','faruk','faruk@hotmail.com','123321fg','odemeli');
/*!40000 ALTER TABLE `kullanici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kullanici_calma_liste_goruntuleme`
--

DROP TABLE IF EXISTS `kullanici_calma_liste_goruntuleme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kullanici_calma_liste_goruntuleme` (
  `id` double NOT NULL,
  `kullanici_id` double NOT NULL,
  `calma_liste_id` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kullanici_id_idx2_idx` (`kullanici_id`),
  KEY `calma_liste_id_idx_idx` (`calma_liste_id`),
  CONSTRAINT `calma_liste_id_idx2` FOREIGN KEY (`calma_liste_id`) REFERENCES `calma_liste` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kullanici_id_idx2` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kullanici_calma_liste_goruntuleme`
--

LOCK TABLES `kullanici_calma_liste_goruntuleme` WRITE;
/*!40000 ALTER TABLE `kullanici_calma_liste_goruntuleme` DISABLE KEYS */;
INSERT INTO `kullanici_calma_liste_goruntuleme` VALUES (1,1,22),(2,1,25),(5,11,23),(6,11,24);
/*!40000 ALTER TABLE `kullanici_calma_liste_goruntuleme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kullanici_takip`
--

DROP TABLE IF EXISTS `kullanici_takip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kullanici_takip` (
  `id` double NOT NULL,
  `kullanici_id` double NOT NULL,
  `takip_edilen_id` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kullanici_id_idx` (`kullanici_id`),
  KEY `takip_edilen_id_idx` (`takip_edilen_id`),
  CONSTRAINT `kullanici_id_idx` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `takip_edilen_id` FOREIGN KEY (`takip_edilen_id`) REFERENCES `kullanici` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kullanici_takip`
--

LOCK TABLES `kullanici_takip` WRITE;
/*!40000 ALTER TABLE `kullanici_takip` DISABLE KEYS */;
INSERT INTO `kullanici_takip` VALUES (1,0,8),(2,2,8),(3,2,1),(4,2,8),(5,1,9),(6,1,10),(7,1,1),(8,1,8),(9,11,1),(10,11,8),(11,8,9),(12,8,1),(13,8,10),(14,8,11);
/*!40000 ALTER TABLE `kullanici_takip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanatci`
--

DROP TABLE IF EXISTS `sanatci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanatci` (
  `id` double NOT NULL,
  `ulke_ad` varchar(45) NOT NULL,
  `sanatci_ad` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ulke_ad_idx` (`ulke_ad`),
  CONSTRAINT `ulke_ad` FOREIGN KEY (`ulke_ad`) REFERENCES `ulke` (`ulke_ad`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanatci`
--

LOCK TABLES `sanatci` WRITE;
/*!40000 ALTER TABLE `sanatci` DISABLE KEYS */;
INSERT INTO `sanatci` VALUES (1,'amerika','adele'),(2,'turkiye','fazil say'),(3,'amerika','frank sinatra'),(4,'amerika','louis armstrong'),(5,'amerika','selena gomez'),(6,'amerika','asap rocky'),(7,'danimarka','agnes obel'),(8,'amerika','lana del rey'),(9,'mısır','tamino'),(10,'rusya','evgeny grinko'),(11,'turkiye','tarkan'),(12,'turkiye','serdar ortaç'),(13,'polonya','chopin'),(14,'almanya','beethoven'),(15,'amerika','nina simone'),(16,'amerika','tony bennett'),(17,'amerika','lady gaga');
/*!40000 ALTER TABLE `sanatci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sarki`
--

DROP TABLE IF EXISTS `sarki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sarki` (
  `id` double NOT NULL,
  `album_id` double DEFAULT NULL,
  `tur_ad` varchar(45) DEFAULT NULL,
  `sarki_ad` varchar(45) NOT NULL,
  `sarki_tarih` varchar(45) NOT NULL,
  `sarki_dinlenme_sayisi` double NOT NULL,
  `sarki_sure` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `album_id_idx` (`album_id`),
  KEY `tur_ad_idx` (`tur_ad`),
  CONSTRAINT `album_id` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `tur_ad` FOREIGN KEY (`tur_ad`) REFERENCES `tur` (`tur_ad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sarki`
--

LOCK TABLES `sarki` WRITE;
/*!40000 ALTER TABLE `sarki` DISABLE KEYS */;
INSERT INTO `sarki` VALUES (1,1,'klasik','kumru','05.05.2014',2156455,'2.43'),(2,1,'klasik','ses','05.05.2014',348512,'3.46'),(3,1,'klasik','kara toprak','05.05.2014',1435475,'6.11'),(4,1,'klasik','sevenlere dair','05.05.2014',1567473,'4.18'),(5,1,'klasik','nazım','05.05.2014',410899,'3.18'),(6,2,'jazz','come fly with me','04.08.1958',141894578,'3.19'),(7,2,'jazz','chicago','04.08.1958',13450147,'2.14'),(8,2,'jazz','moonlight in vermont','04.08.1958',12457478,'3.33'),(9,3,'jazz','what a wonderful world','14.07.1968',279841524,'2.18'),(10,3,'jazz','dream a little dream of me','14.07.1968',167195847,'3.15'),(11,4,'pop','same old love','21.12.2015',451254785,'3.49'),(12,4,'pop','kill em with kindness','21.12.2015',465147852,'3.38'),(13,4,'pop','good for you','21.12.2015',552456321,'3.41'),(14,5,'pop','rolling in the deep','04.02.2011',821452365,'3.48'),(15,5,'pop','set fire to the rain','04.02.2011',557457856,'4.03'),(16,5,'pop','someone like you','04.02.2011',1001458785,'4.45'),(17,6,'klasik','tokka','06.07.2014',54412523,'1.30'),(18,6,'pop','words are dead','06.07.2014',5965856,'3.44'),(19,7,'pop','golden green','22.11.2016',3841523,'4.00'),(20,7,'pop','mary','22.11.2016',3452125,'5.47'),(21,8,'pop','indigo night','04.11.2018',3554147,'4.11'),(22,8,'pop','cigar','04.11.2018',1843009,'4.30'),(23,8,'pop','habibi','04.11.2018',2456321,'4.37'),(24,8,'pop','tummy','04.11.2018',902337,'3.18'),(25,9,'pop','şımarık','14.04.1997',44157478,'3.55'),(26,9,'pop','ikimizin yerine','14.04.1997',3287478,'4.42'),(27,9,'pop','ölürüm sana','14.04.1997',17508409,'4.06'),(28,9,'pop','salına salına sinsice','14.04.1997',7685257,'3.56'),(29,9,'pop','inci tanem','14.04.1997',6650224,'5.39'),(30,10,'pop','aşk','11.08.2001',4751694,'4.22'),(31,10,'pop','ay','11.08.2001',12818315,'4.20'),(32,10,'pop','kuzu kuzu','11.08.2001',23896822,'3.53'),(33,10,'pop','sen başkasın','11.08.2001',10752062,'4.16'),(34,11,'pop','sor','15.09.2006',11029455,'3.55'),(35,11,'pop','dansöz','15.09.2006',12539904,'5.16'),(36,11,'pop','mesafe','15.09.2006',25177904,'5.01'),(37,11,'pop','gitme','15.09.2006',2819312,'3.46'),(38,12,'klasik','nocturne no1','1830',7953158,'4.46'),(39,12,'klasik','nocturne no2','1830',4687682,'4.02'),(40,12,'klasik','nocturne no8','1830',16543236,'4.54'),(41,12,'klasik','nocturne no3','1830',1103157,'5.40'),(42,13,'klasik','moonlight sonata','1800',89729245,'5.15'),(43,13,'klasik','symphony no5','1800',26545103,'7.22'),(44,13,'klasik','für elise','1800',64523654,'3.08'),(45,14,'jazz','feeling good','1965',251500162,'2.54'),(46,14,'jazz','take care of business','1965',9193036,'2.05'),(47,14,'jazz','ı put a spell on you','1965',82876511,'2.35'),(48,14,'jazz','ne me quitte pas','1965',14547563,'3.34');
/*!40000 ALTER TABLE `sarki` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sarki_calma_liste`
--

DROP TABLE IF EXISTS `sarki_calma_liste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sarki_calma_liste` (
  `id` double NOT NULL,
  `sarki_id` double NOT NULL,
  `calma_liste_id` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sarki_id_UNIQUE` (`sarki_id`,`calma_liste_id`),
  KEY `sarki_id_idx2_idx` (`sarki_id`),
  KEY `calma_liste_id_idx_idx` (`calma_liste_id`),
  CONSTRAINT `calma_liste_id_idx` FOREIGN KEY (`calma_liste_id`) REFERENCES `calma_liste` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sarki_id_idx2` FOREIGN KEY (`sarki_id`) REFERENCES `sarki` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sarki_calma_liste`
--

LOCK TABLES `sarki_calma_liste` WRITE;
/*!40000 ALTER TABLE `sarki_calma_liste` DISABLE KEYS */;
INSERT INTO `sarki_calma_liste` VALUES (24,1,0),(12,1,2),(4,1,21),(11,1,24),(39,1,35),(14,2,2),(5,2,21),(25,3,0),(16,3,2),(6,3,21),(15,3,24),(41,3,35),(26,4,0),(18,4,2),(7,4,21),(17,4,24),(43,4,35),(27,5,0),(20,5,2),(8,5,21),(19,5,24),(33,5,35),(22,6,1),(9,6,21),(1,11,0),(0,11,24),(35,11,33),(31,16,0),(30,16,24),(45,16,33),(29,17,2),(23,20,0),(3,20,24),(37,20,33),(50,32,0),(51,32,21),(48,47,0),(49,47,22);
/*!40000 ALTER TABLE `sarki_calma_liste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sarki_sanatci`
--

DROP TABLE IF EXISTS `sarki_sanatci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sarki_sanatci` (
  `id` double NOT NULL,
  `sarki_id` double NOT NULL,
  `sanatci_id` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sarki_id_idx` (`sarki_id`),
  KEY `sanatci_id_idx` (`sanatci_id`),
  CONSTRAINT `sanatci_id` FOREIGN KEY (`sanatci_id`) REFERENCES `sanatci` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sarki_id` FOREIGN KEY (`sarki_id`) REFERENCES `sarki` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sarki_sanatci`
--

LOCK TABLES `sarki_sanatci` WRITE;
/*!40000 ALTER TABLE `sarki_sanatci` DISABLE KEYS */;
INSERT INTO `sarki_sanatci` VALUES (1,1,2),(2,2,2),(3,3,2),(4,4,2),(5,5,2),(6,6,3),(7,7,3),(8,8,3),(9,9,4),(10,10,4),(11,11,5),(12,12,5),(13,13,5),(14,13,6),(15,14,1),(16,15,1),(17,16,1),(18,17,7),(19,18,7),(20,19,7),(21,20,7),(22,21,9),(23,22,9),(24,23,9),(25,24,9),(26,25,11),(27,26,11),(28,27,11),(29,28,11),(30,29,11),(31,30,11),(32,31,11),(33,32,11),(34,33,11),(35,34,12),(36,35,12),(37,36,12),(38,37,12),(39,38,13),(40,39,13),(41,40,13),(42,41,13),(43,42,14),(44,43,14),(45,44,14),(46,45,15),(47,46,15),(48,47,15),(49,48,15);
/*!40000 ALTER TABLE `sarki_sanatci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tur`
--

DROP TABLE IF EXISTS `tur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tur` (
  `id` double NOT NULL,
  `tur_ad` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tur_ad_UNIQUE` (`tur_ad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tur`
--

LOCK TABLES `tur` WRITE;
/*!40000 ALTER TABLE `tur` DISABLE KEYS */;
INSERT INTO `tur` VALUES (2,'jazz'),(3,'klasik'),(1,'pop');
/*!40000 ALTER TABLE `tur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ulke`
--

DROP TABLE IF EXISTS `ulke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ulke` (
  `id` double NOT NULL,
  `ulke_ad` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ulke_ad_UNIQUE` (`ulke_ad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ulke`
--

LOCK TABLES `ulke` WRITE;
/*!40000 ALTER TABLE `ulke` DISABLE KEYS */;
INSERT INTO `ulke` VALUES (2,'almanya'),(1,'amerika'),(4,'danimarka'),(5,'mısır'),(7,'polonya'),(6,'rusya'),(3,'turkiye');
/*!40000 ALTER TABLE `ulke` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uyelik_tip`
--

DROP TABLE IF EXISTS `uyelik_tip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uyelik_tip` (
  `id` double NOT NULL,
  `uyelik_tip_ad` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uyelik_tip_ad_UNIQUE` (`uyelik_tip_ad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uyelik_tip`
--

LOCK TABLES `uyelik_tip` WRITE;
/*!40000 ALTER TABLE `uyelik_tip` DISABLE KEYS */;
INSERT INTO `uyelik_tip` VALUES (1,'admin'),(2,'normal'),(3,'premium');
/*!40000 ALTER TABLE `uyelik_tip` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-20 13:17:36
