-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: GroupProject
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `node`
--

DROP TABLE IF EXISTS `node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `node` (
  `id` smallint NOT NULL AUTO_INCREMENT,
  `parent_id` smallint DEFAULT NULL,
  `node_text` varchar(125) NOT NULL,
  `answer` text NOT NULL,
  `Vraag` varchar(125) NOT NULL,
  `Antwoord` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `parentId` (`parent_id`),
  CONSTRAINT `node_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `node` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node`
--

LOCK TABLES `node` WRITE;
/*!40000 ALTER TABLE `node` DISABLE KEYS */;
INSERT INTO `node` VALUES (1,NULL,'Account Credentials','Please help us understand your problem better by choosing another option. ','Accountgegevens','Help ons uw probleem beter te begrijpen door een keuze te maken van een volgende optie alstublieft'),(2,NULL,'Student Housing','Please help us understand your problem better by choosing another option. ','Studentenwoningen','Help ons uw probleem beter te begrijpen door een keuze te maken van een volgende optie alstublieft'),(3,NULL,'Office 365','Please help us understand your problem better by choosing another option. ','Office 365','Help ons uw probleem beter te begrijpen door een keuze te maken van een volgende optie alstublieft'),(4,1,'Forgot Password/Username','You can reset your password/username here: https://goto.fontys.nl/wwvergeten/  or if you are an employee of Fontys or Declarant then you need to use https://intern.fontys.nl/wwselfservice/changepassword.aspx .','Inloggegevens vergeten','U kan uw wachtwoord hier opnieuw instellen: https://goto.fontys.nl/wwvergeten/ . Als medewerker of declarant moet u de volgende link nemen om uw wachtwoord te wijzigen https://intern.fontys.nl/wwselfservice/changepassword.aspx'),(5,1,'My account is hacked','To help retrieve your account please contact the service desk: it-servicedesk@fontys.nl','Mijn account is gehackt','Om uw account terug te halen kunt u contact opnemen met de servicedesk: it-servicedesk@fontys.nl'),(6,1,'Other','What are your having trouble with?','Anders','Waarmee heeft u problemen mee? kies een volgende optie alstublieft'),(7,2,'I am an international looking for housing','Please fill in the form(https://goto.fontys.nl/forms/accommodation.form.location.eindhoven.and.tilburg.28245.en.htm) before the deadline: Fall semester 15 June, Spring semester 8 December. For more info visit: https://fontys.edu/Study-at-Fontys/Practical-information-1/Arriving-in-The-Netherlands-1/Accommodation.htm or send an email to internationalstudents@fontys.nl. ','Ik ben een internationale student zoekend naar een woning','U kunt naar de volgende website gaan voor meer concrete hulp (https://fontys.nl/Studeren/Studeren-in-Fontyssteden.htm) als u de stad waar u gaat studeren klikt en dan scroll naar de sectie \'op kamers\' dan zal u een paar opties zien dat misschien kunnen helpen. Als u dit niet wil doen/niet oplost kunt u ook een mail sturen naar internationalstudents@fontys.nl.'),(8,2,'I want to stay at the social hub','Fontys makes a pre-reservation of several rooms at the Social Hub in Eindhoven. You can book via this link Eindhoven - The Social Hub and fill in the group code. https://fontys.edu/Study-at-Fontys/Practical-information-1/Arriving-in-The-Netherlands-1/Accommodation/The-Social-Hub.htm','Ik wil verblijven bij The social hub','Fontys maakt een pre-reservatie voor meerdere kamers bij The Social Hub in Eindhoven. U kan boeken via de volgende link: https://fontys.edu/Study-at-Fontys/Practical-information-1/Arriving-in-The-Netherlands-1/Accommodation/The-Social-Hub.htm'),(9,2,'Other','What are your having trouble with?','Anders','Waarmee heeft u problemen mee? kies een volgende optie alstublieft'),(10,3,'Can I get Office?','On portal.office.com you can by clicking on \'Install Office\', you may install it on 5 mobile devices, 5 tablets and 5 laptops or desktops. On Fontys computers Office is already installed so please don\'t click on \'install Office\' on a Fontys computer','kan ik Office krijgen?','Àls u naar portal.office.com gaat kunt u kiezen voor de optie \'Apps instaleren\' u mag Office installeren op 5 mobiele telefoons, 5 tablets en 5 laptops of desktops die privé zijn. Office is al geïnstalleerd op Fontys computers dus hoeft u niet op de optie \'Apps installeren\' te klikken.'),(11,3,'Can I add an e-mail account?','You can only add email accounts which support Exchange (no pop3 or imap).','kan ik een e-mail account toevoegen?','U kunt een email account toevoegen wat understeund Exchange (geen pop3 of imap).'),(12,3,'Other','What are your having trouble with?','Anders','Waarmee heeft u problemen mee? kies een volgende optie alstublieft'),(13,NULL,'Welcome','Hello! What can I help you with today?','Welkom','Hallo! waarmee kan ik je vandaag mee helpen?'),(14,22,'Workplace','Please help us understand your problem better by choosing another option.','Werkplaats','Help ons uw probleem beter te begrijpen door een keuze te maken van een volgende optie alstublieft'),(15,14,'Direct access problems','I don\'t have a solution made for me yet please check: https://learn.microsoft.com/en-us/windows-server/remote/remote-access/directaccess/troubleshooting-directaccess if it can solve your issue if not please contact via one of the following methods: Email: it-servicedesk@fontys.nl, Telephone: +31 8850 77777, Whatsapp: https://wa.me/31653721805 or one of the serivepoints https://connect.fontys.nl/diensten/IT/Paginas/(EN)-IT-Servicepoints.aspx ','Direct access problemen','Er is nog geen antwoord voor mij gemaakt probeer het optelossen door: https://learn.microsoft.com/nl-nl/troubleshoot/windows-server/networking/troubleshoot-directaccess-guidance  of neem contact op met iemand via een van de volgende methodes: Email: it-servicedesk@fontys.nl, Telephone: +31 8850 77777, Whatsapp: https://wa.me/31653721805 of een van de serivepoints: https://connect.fontys.nl/diensten/IT/Paginas/IT-Servicepoints.aspx'),(16,14,'Battery issues','Please visit a IT team on your campus/building to check your computer','Batterij problemen','Bezoek een IT-team op uw campus/gebouw om uw computer te controleren.'),(17,14,'Sound issues','I don\'t have a basic solution for you yet if you have windows you can try: https://support.microsoft.com/en-us/windows/fix-sound-or-audio-problems-in-windows-73025246-b61c-40fb-671a-2535c7cd56c8 . For Apple machines try: https://support.apple.com/en-us/HT203186 .Any other systems will probably also get help from the two previous links','Geluid problemen','Ik heb nog geen basis uitleg voor dit als u een Windows machine heeft kunt u de volgende website gebruiken (https://support.microsoft.com/nl-nl/windows/geluids-of-audioproblemen-in-windows-oplossen-73025246-b61c-40fb-671a-2535c7cd56c8) . Als u een apple apperaat heeft (https://support.apple.com/nl-nl/HT203186). Andere systemen zullen waarschijnlijk ook gehulpen worden met de vorige twee links '),(18,14,'Camera issues','I don\'t have a basic solution for you yet if you have a Windows machine you can try: https://support.microsoft.com/en-us/windows/camera-doesn-t-work-in-windows-32adb016-b29c-a928-0073-53d31da0dad5 .For Apple machines try: https://support.apple.com/en-us/HT211130 . Any other systems will probably also get help from the two previous links','Camera problemen','Ik heb nog geen basis uitleg voor dit als u een Windows machine heeft kunt u de volgende website gebruiken (https://support.microsoft.com/nl-nl/windows/camera-werkt-niet-in-windows-32adb016-b29c-a928-0073-53d31da0dad5). Als u een apple apperaat heeft https://support.apple.com/nl-nl/HT211130. Andere systemen zullen waarschijnlijk ook gehulpen worden met de vorige twee links '),(19,22,'Wireless','Please help us understand your problem better by choosing another option.','Draadloos','Help ons uw probleem beter te begrijpen door een keuze te maken van een volgende optie alstublieft'),(20,19,'How to connect to eduroam','If you want to connect to eduroam you need to click on eduroam and then fill in your PCN@student.fontys.nl or  PCN@fontys.nl (PCN is your own personal number it\'s also the same as the one you use to login to outlook) and then your password. If this didn\'t help you can also try https://connect.fontys.nl/diensten/IT/manuals/Paginas/connectwitheduroam.aspx ','Hoe te verbinden met edueroam','U kunt met eduroam verbinden door op eduraom te klikken en dan invullen eerst met PCN@student.fontys.nl of PCN@fontys.nl (PCN is uw persoonlijke nummer het is ook dezelfde die u gebruikt om in te loggen met outlook ) en daarna uw wachtwoord. Als dit niet heeft geholpen of ander problemen zijn kunt u ook de volgende website proberen: https://connect.fontys.nl/diensten/IT/handleidingen/Paginas/verbindenmeteduroam.aspx '),(21,22,'audio video','If you have any problems with your Audiovisual Media you can go to https://connect.fontys.nl/diensten/IT/manuals/Paginas/Audiovisual-media.aspx to read one of the manuals to help fix it or you can contact a person via the following methods Telephone: +31 8850 77777. Email: it-servicedesk@fontys.nl. WhatsApp (text): +31 6 5372 1805. This is only possible from 8:00-17:00 on working days (Monday-Friday)','Audiovisuele Middelen','U kunt via (https://connect.fontys.nl/diensten/IT/handleidingen/Paginas/Audio_visuele_middelen.aspx) een van de handleiding kiezen of u kunt iemand contacteren via Telefoon: +31 8850 77777. Email: it-servicedesk@fontys.nl. Dit is alleen mogelijk tussen 8:00-17:00 op werkdagen (Maandag-Vrijdag)'),(22,NULL,'Other','What are your having trouble with?','Anders','Waarmee heeft u problemen mee? kies een volgende optie alstublieft');
/*!40000 ALTER TABLE `node` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-25 12:38:37
