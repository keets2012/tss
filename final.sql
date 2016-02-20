-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: mytest2
-- ------------------------------------------------------
-- Server version	5.6.23

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
-- Table structure for table `njit_course`
--

DROP TABLE IF EXISTS `njit_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `courseNo` varchar(255) DEFAULT NULL,
  `credit` float DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `curriculumId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK537A5F732E1EFF21` (`curriculumId`),
  KEY `FK537A5F73FD857641` (`userId`),
  CONSTRAINT `FK537A5F732E1EFF21` FOREIGN KEY (`curriculumId`) REFERENCES `njit_curriculum` (`id`),
  CONSTRAINT `FK537A5F73FD857641` FOREIGN KEY (`userId`) REFERENCES `njit_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_course`
--

LOCK TABLES `njit_course` WRITE;
/*!40000 ALTER TABLE `njit_course` DISABLE KEYS */;
INSERT INTO `njit_course` VALUES (1,NULL,NULL,0,NULL,1,2),(2,NULL,NULL,0,NULL,1,4),(3,NULL,NULL,0,NULL,2,5),(4,NULL,NULL,0,NULL,3,2),(5,NULL,NULL,0,NULL,2,7),(6,NULL,NULL,0,NULL,3,5);
/*!40000 ALTER TABLE `njit_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_curriculum`
--

DROP TABLE IF EXISTS `njit_curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_curriculum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `courseNo` varchar(255) DEFAULT NULL,
  `credit` float DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_curriculum`
--

LOCK TABLES `njit_curriculum` WRITE;
/*!40000 ALTER TABLE `njit_curriculum` DISABLE KEYS */;
INSERT INTO `njit_curriculum` VALUES (1,'数据结构','00281',4.5,'算法与程序'),(2,'数据库','00287',4,'关系数据库操作！'),(3,'JAVA程序设计','00288',4,'程序设计'),(4,'电路分析','00211',4,'电路'),(5,'C++程序设计','00212',4,'程序设计');
/*!40000 ALTER TABLE `njit_curriculum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_department`
--

DROP TABLE IF EXISTS `njit_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE5C656AA2DCDB2E7` (`parentId`),
  CONSTRAINT `FKE5C656AA2DCDB2E7` FOREIGN KEY (`parentId`) REFERENCES `njit_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_department`
--

LOCK TABLES `njit_department` WRITE;
/*!40000 ALTER TABLE `njit_department` DISABLE KEYS */;
INSERT INTO `njit_department` VALUES (1,'计算机工程学院','',NULL),(19,'软件工程系','',1),(20,'网络工程系','',1),(21,'数字媒体系','',1),(22,'计算机嵌入式系','',1),(23,'电子科学与技术系','',1),(24,'软件卓越111','',19),(25,'软件测试111','',19),(26,'网络工程111','',20),(27,'多媒体111','',21),(28,'计算机111','',22),(29,'电科技111','',23),(30,'电科技112','',23);
/*!40000 ALTER TABLE `njit_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_department_course`
--

DROP TABLE IF EXISTS `njit_department_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_department_course` (
  `departmentId` bigint(20) NOT NULL,
  `courseId` bigint(20) NOT NULL,
  PRIMARY KEY (`courseId`,`departmentId`),
  KEY `FK20C793508CFCB6E1` (`courseId`),
  KEY `FK20C79350AFCCE38F` (`departmentId`),
  CONSTRAINT `FK20C793508CFCB6E1` FOREIGN KEY (`courseId`) REFERENCES `njit_course` (`id`),
  CONSTRAINT `FK20C79350AFCCE38F` FOREIGN KEY (`departmentId`) REFERENCES `njit_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_department_course`
--

LOCK TABLES `njit_department_course` WRITE;
/*!40000 ALTER TABLE `njit_department_course` DISABLE KEYS */;
INSERT INTO `njit_department_course` VALUES (24,1),(26,1),(25,2),(27,2),(26,3),(27,3),(24,4),(26,4),(25,5),(28,5),(30,6);
/*!40000 ALTER TABLE `njit_department_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_department_task`
--

DROP TABLE IF EXISTS `njit_department_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_department_task` (
  `departmentId` bigint(20) NOT NULL,
  `taskId` bigint(20) NOT NULL,
  PRIMARY KEY (`taskId`,`departmentId`),
  KEY `FKAE4831FAAFCCE38F` (`departmentId`),
  KEY `FKAE4831FAFAD88335` (`taskId`),
  CONSTRAINT `FKAE4831FAAFCCE38F` FOREIGN KEY (`departmentId`) REFERENCES `njit_department` (`id`),
  CONSTRAINT `FKAE4831FAFAD88335` FOREIGN KEY (`taskId`) REFERENCES `njit_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_department_task`
--

LOCK TABLES `njit_department_task` WRITE;
/*!40000 ALTER TABLE `njit_department_task` DISABLE KEYS */;
INSERT INTO `njit_department_task` VALUES (24,1),(24,3),(24,4),(24,5),(26,1),(26,3),(26,4),(26,5);
/*!40000 ALTER TABLE `njit_department_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_experiment`
--

DROP TABLE IF EXISTS `njit_experiment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_experiment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expTime` varchar(255) DEFAULT NULL,
  `expId` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `departmentId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `labId` bigint(20) DEFAULT NULL,
  `projectId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAE21EF553A5C46BD` (`projectId`),
  KEY `FKAE21EF557459CB6B` (`labId`),
  KEY `FKAE21EF55AFCCE38F` (`departmentId`),
  KEY `FKAE21EF55FD857641` (`userId`),
  CONSTRAINT `FKAE21EF553A5C46BD` FOREIGN KEY (`projectId`) REFERENCES `njit_project` (`id`),
  CONSTRAINT `FKAE21EF557459CB6B` FOREIGN KEY (`labId`) REFERENCES `njit_laboratory` (`id`),
  CONSTRAINT `FKAE21EF55AFCCE38F` FOREIGN KEY (`departmentId`) REFERENCES `njit_department` (`id`),
  CONSTRAINT `FKAE21EF55FD857641` FOREIGN KEY (`userId`) REFERENCES `njit_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_experiment`
--

LOCK TABLES `njit_experiment` WRITE;
/*!40000 ALTER TABLE `njit_experiment` DISABLE KEYS */;
INSERT INTO `njit_experiment` VALUES (26,'2w1w1',NULL,'3344',24,2,4,2),(27,'5w1w1',NULL,'3344',24,2,5,2),(28,'5w1w1',NULL,'3344',24,2,4,2),(31,'6w1w1',NULL,'44',26,2,4,3),(32,'1w1w1',NULL,'55',26,2,5,5),(33,'2w1w1',NULL,'55',26,2,5,6),(34,'3w1w1',NULL,'66',24,2,4,7),(35,'4w1w1',NULL,'66',24,2,5,7),(36,'5w2w1',NULL,'66',24,2,4,7),(38,'6w2w1',NULL,'66',26,2,4,7),(39,'7w3w1',NULL,'55',26,2,4,8),(40,'8w3w1',NULL,'55',26,2,4,8);
/*!40000 ALTER TABLE `njit_experiment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_forum`
--

DROP TABLE IF EXISTS `njit_forum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_forum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  `topicCount` int(11) DEFAULT NULL,
  `articleCount` int(11) DEFAULT NULL,
  `lastTopicId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `lastTopicId` (`lastTopicId`),
  KEY `FK9780C2C9850C4213` (`lastTopicId`),
  CONSTRAINT `FK9780C2C9850C4213` FOREIGN KEY (`lastTopicId`) REFERENCES `njit_topic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_forum`
--

LOCK TABLES `njit_forum` WRITE;
/*!40000 ALTER TABLE `njit_forum` DISABLE KEYS */;
INSERT INTO `njit_forum` VALUES (1,'数据结构','讨论答疑专区，各位老师同学踊跃发言！',1,11,12,11),(2,'数据库','讨论答疑专区，各位同学老师踊跃发言！',2,0,0,NULL),(3,'JAVA EE','讨论答疑专区，各位同学老师踊跃发言！',3,0,0,NULL),(4,'JAVA web开发技术','讨论答疑专区，各位同学老师踊跃发言！',4,0,0,NULL),(5,'JAVA程序设计','讨论答疑专区，各位同学老师踊跃发言！',5,0,0,NULL);
/*!40000 ALTER TABLE `njit_forum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_laboratory`
--

DROP TABLE IF EXISTS `njit_laboratory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_laboratory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `avail` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_laboratory`
--

LOCK TABLES `njit_laboratory` WRITE;
/*!40000 ALTER TABLE `njit_laboratory` DISABLE KEYS */;
INSERT INTO `njit_laboratory` VALUES (4,'信息楼A216',48,'信息与安全！'),(5,'图书馆A304',80,'基础机房！');
/*!40000 ALTER TABLE `njit_laboratory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_privilege`
--

DROP TABLE IF EXISTS `njit_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB847185969BDE3A6` (`parentId`),
  CONSTRAINT `FKB847185969BDE3A6` FOREIGN KEY (`parentId`) REFERENCES `njit_privilege` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_privilege`
--

LOCK TABLES `njit_privilege` WRITE;
/*!40000 ALTER TABLE `njit_privilege` DISABLE KEYS */;
INSERT INTO `njit_privilege` VALUES (1,'实验资源',NULL,NULL),(2,'实验室','/lab_list',1),(3,'实验课程','/cur_list',1),(4,'课程分配','/course_arrList',1),(5,'实验项目','/project_list',1),(6,'课程资源',NULL,NULL),(7,'我的课程','/course_myList',6),(8,'共享资料','/share_list',6),(9,'我的共享','/share_myList',6),(10,'我的实验',NULL,NULL),(11,'预约实验','/exp_queryLab',10),(12,'实验安排','/exp_list',10),(13,'我的课表','/exp_myCourse',10),(14,'查看课表','/exp_course',10),(15,'实验报告',NULL,NULL),(16,'下发任务','/task_addUI',15),(17,'提交报告','/report_myList',15),(18,'报告统计','/report_count',15),(19,'我的报告','/report_list',15),(20,'我的下发','/task_list',15),(21,'CS Forum',NULL,NULL),(22,'论坛管理','/forumManage_list',21),(23,'论坛','/forum_list',21),(24,'系统管理',NULL,NULL),(25,'岗位管理','/role_list',24),(26,'部门管理','/department_list',24),(27,'用户管理','/user_list',24),(28,'岗位列表','/role_list',25),(29,'岗位删除','/role_delete',25),(30,'岗位添加','/role_add',25),(31,'权限设置','/role_setPrivilegeUI',25),(32,'岗位修改','/role_edit',25),(33,'部门列表','/dept_list',26),(34,'部门删除','/dept_delete',26),(35,'部门添加','/dept_add',26),(36,'部门修改','/deptment_edit',26),(37,'用户列表','/user_list',27),(38,'用户删除','/user_delete',27),(39,'用户添加','/user_add',27),(40,'用户修改','/user_edit',27),(41,'初始化密码','/user_initPassword',27),(42,'个人设置',NULL,NULL),(43,'个人信息','/user_myInfo',42),(44,'密码修改','/user_editMy',42);
/*!40000 ALTER TABLE `njit_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_privilege_role`
--

DROP TABLE IF EXISTS `njit_privilege_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_privilege_role` (
  `roleId` bigint(20) NOT NULL,
  `privilegeId` bigint(20) NOT NULL,
  PRIMARY KEY (`privilegeId`,`roleId`),
  KEY `FKEDB7709C32CA6BED` (`privilegeId`),
  KEY `FKEDB7709CF83020D7` (`roleId`),
  CONSTRAINT `FKEDB7709C32CA6BED` FOREIGN KEY (`privilegeId`) REFERENCES `njit_privilege` (`id`),
  CONSTRAINT `FKEDB7709CF83020D7` FOREIGN KEY (`roleId`) REFERENCES `njit_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_privilege_role`
--

LOCK TABLES `njit_privilege_role` WRITE;
/*!40000 ALTER TABLE `njit_privilege_role` DISABLE KEYS */;
INSERT INTO `njit_privilege_role` VALUES (11,6),(12,6),(11,7),(11,8),(12,8),(11,9),(11,10),(12,10),(11,11),(11,12),(11,13),(12,13),(11,14),(11,15),(12,15),(11,16),(12,17),(11,18),(12,19),(11,20),(11,21),(12,21),(11,23),(12,23),(11,24),(11,25),(11,26),(11,27),(11,28),(11,32),(11,37),(11,42),(12,42),(11,43),(12,43),(11,44),(12,44);
/*!40000 ALTER TABLE `njit_privilege_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_project`
--

DROP TABLE IF EXISTS `njit_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `curriculumId` bigint(20) DEFAULT NULL,
  `projectNo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD048B6C12E1EFF21` (`curriculumId`),
  CONSTRAINT `FKD048B6C12E1EFF21` FOREIGN KEY (`curriculumId`) REFERENCES `njit_curriculum` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_project`
--

LOCK TABLES `njit_project` WRITE;
/*!40000 ALTER TABLE `njit_project` DISABLE KEYS */;
INSERT INTO `njit_project` VALUES (1,'实验一',2,'4',1,'00111'),(2,'实验二',2,'',1,'001112'),(3,'实验三',2,'鹅鹅鹅',1,'001114'),(4,'实验四',2,'嗯嗯',1,'000113'),(5,'java实验一',2,'55',3,'00298'),(6,'JAVA实验二',2,'',3,'00298'),(7,'JAVA实验三',2,'33',3,'00299'),(8,'JAVA实验四',2,'嗯嗯',3,'00300'),(9,'JAVA实验五',2,'55',3,'00311'),(10,'JAVA实验六',2,'55',3,'00312'),(11,'JAVA实验四',2,'33',3,'00313'),(12,'数据库实验一',2,'99',2,'00399'),(13,'数据库实验二',2,'55',2,'00344'),(14,'数据库实验三',2,'嗯嗯',2,'00333');
/*!40000 ALTER TABLE `njit_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_reply`
--

DROP TABLE IF EXISTS `njit_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext,
  `postTime` datetime DEFAULT NULL,
  `ipAddr` varchar(255) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `topicId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9825489277F6C969` (`topicId`),
  KEY `FK9825489284C53C01` (`authorId`),
  CONSTRAINT `FK9825489277F6C969` FOREIGN KEY (`topicId`) REFERENCES `njit_topic` (`id`),
  CONSTRAINT `FK9825489284C53C01` FOREIGN KEY (`authorId`) REFERENCES `njit_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_reply`
--

LOCK TABLES `njit_reply` WRITE;
/*!40000 ALTER TABLE `njit_reply` DISABLE KEYS */;
INSERT INTO `njit_reply` VALUES (1,'回复：13','45','2015-05-09 19:05:33','0:0:0:0:0:0:0:1',3,11);
/*!40000 ALTER TABLE `njit_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_report`
--

DROP TABLE IF EXISTS `njit_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `reportState` bit(1) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `handleDate` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `taskId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6C83D48CFAD88335` (`taskId`),
  KEY `FK6C83D48CFD857641` (`userId`),
  CONSTRAINT `FK6C83D48CFAD88335` FOREIGN KEY (`taskId`) REFERENCES `njit_task` (`id`),
  CONSTRAINT `FK6C83D48CFD857641` FOREIGN KEY (`userId`) REFERENCES `njit_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_report`
--

LOCK TABLES `njit_report` WRITE;
/*!40000 ALTER TABLE `njit_report` DISABLE KEYS */;
INSERT INTO `njit_report` VALUES (1,'报告','\0','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\001\\面试题.docx','2015-05-07 00:14:45','订单',3,1),(2,'刘德华的报告','\0','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\002\\网络111获奖.xls','2015-05-08 17:41:23','报告',11,5),(3,'数据结构-刘德华','\0','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\002\\QQ.docx','2015-05-08 17:42:28','55',11,1),(4,'数据结构-周润发','\0','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\003\\坦克大战.docx','2015-05-08 17:44:17','77',12,5),(5,'JAVA周润发','\0','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\003\\学生管理系统.docx','2015-05-08 17:44:49','33',12,1);
/*!40000 ALTER TABLE `njit_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_role`
--

DROP TABLE IF EXISTS `njit_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_role`
--

LOCK TABLES `njit_role` WRITE;
/*!40000 ALTER TABLE `njit_role` DISABLE KEYS */;
INSERT INTO `njit_role` VALUES (9,'管理员',''),(10,'系主任',''),(11,'教师',''),(12,'学生','');
/*!40000 ALTER TABLE `njit_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_share`
--

DROP TABLE IF EXISTS `njit_share`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_share` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `courseId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK983485878CFCB6E1` (`courseId`),
  KEY `FK98348587FD857641` (`userId`),
  CONSTRAINT `FK983485878CFCB6E1` FOREIGN KEY (`courseId`) REFERENCES `njit_course` (`id`),
  CONSTRAINT `FK98348587FD857641` FOREIGN KEY (`userId`) REFERENCES `njit_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_share`
--

LOCK TABLES `njit_share` WRITE;
/*!40000 ALTER TABLE `njit_share` DISABLE KEYS */;
INSERT INTO `njit_share` VALUES (1,'南京工程学院应届毕业生招聘201504.docx','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\100\\南京工程学院应届毕业生招聘201504.docx','2015-05-08 17:49:47','myeclipse',2,1),(2,'计算机工程学院2015届毕业生就业情况统计表.doc','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\100\\计算机工程学院2015届毕业生就业情况统计表.doc','2015-05-08 17:50:47','33',2,4),(3,'大学生参军入伍享受的优惠政策（2015年）.doc','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\100\\大学生参军入伍享受的优惠政策（2015年）.doc','2015-05-08 17:51:48','443',2,1),(4,'jquery.form.min.zip','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\100\\jquery.form.min.zip','2015-05-08 17:53:05','22',2,1),(5,'网络111获奖.xls','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\100\\网络111获奖.xls','2015-05-08 17:54:28','77',2,4),(6,'5db8a4345e70925557792010988c3016.jpg','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\100\\5db8a4345e70925557792010988c3016.jpg','2015-05-09 15:32:45','66',2,1),(7,'java 三子棋 源代码.rar','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\100\\java 三子棋 源代码.rar','2015-05-09 15:33:08','77',2,4),(8,'np路由.doc','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\100\\np路由.doc','2015-05-09 15:33:22','55',2,1),(9,'TicTacToe.rar','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\100\\TicTacToe.rar','2015-05-09 15:33:55','55',2,1),(10,'201531713925334.xls','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\100\\201531713925334.xls','2015-05-09 15:34:22','66',2,4),(11,'2015319143258522.xls','D:\\AMP\\apache-tomcat-7.0.35\\webapps\\Test\\uploads\\100\\2015319143258522.xls','2015-05-09 15:34:37','88',2,1);
/*!40000 ALTER TABLE `njit_share` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_task`
--

DROP TABLE IF EXISTS `njit_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `assignDate` datetime DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `courseId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3675AADD8CFCB6E1` (`courseId`),
  KEY `FK3675AADDFD857641` (`userId`),
  CONSTRAINT `FK3675AADD8CFCB6E1` FOREIGN KEY (`courseId`) REFERENCES `njit_course` (`id`),
  CONSTRAINT `FK3675AADDFD857641` FOREIGN KEY (`userId`) REFERENCES `njit_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_task`
--

LOCK TABLES `njit_task` WRITE;
/*!40000 ALTER TABLE `njit_task` DISABLE KEYS */;
INSERT INTO `njit_task` VALUES (1,'实验一报告','ee','2015-05-07 00:05:04','2015-05-19 00:00:00',2,1),(3,'实验二','嗯嗯','2015-05-07 00:19:38','2015-06-06 00:00:00',2,4),(4,'实验无报告','报告','2015-05-08 00:07:47','2015-05-30 00:00:00',2,4),(5,'JAVA实验一','33','2015-05-08 17:40:14','2015-05-07 00:00:00',2,4);
/*!40000 ALTER TABLE `njit_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_topic`
--

DROP TABLE IF EXISTS `njit_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext,
  `postTime` datetime DEFAULT NULL,
  `ipAddr` varchar(255) DEFAULT NULL,
  `authorId` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `replyCount` int(11) DEFAULT NULL,
  `lastUpdateTime` datetime DEFAULT NULL,
  `forumId` bigint(20) DEFAULT NULL,
  `lastReplyId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `lastReplyId` (`lastReplyId`),
  KEY `FK984602D7A104AC9` (`lastReplyId`),
  KEY `FK984602D784C53C01` (`authorId`),
  KEY `FK984602D792BC14CD` (`forumId`),
  CONSTRAINT `FK984602D784C53C01` FOREIGN KEY (`authorId`) REFERENCES `njit_user` (`id`),
  CONSTRAINT `FK984602D792BC14CD` FOREIGN KEY (`forumId`) REFERENCES `njit_forum` (`id`),
  CONSTRAINT `FK984602D7A104AC9` FOREIGN KEY (`lastReplyId`) REFERENCES `njit_reply` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_topic`
--

LOCK TABLES `njit_topic` WRITE;
/*!40000 ALTER TABLE `njit_topic` DISABLE KEYS */;
INSERT INTO `njit_topic` VALUES (1,'11','11','2015-05-09 14:59:44','0:0:0:0:0:0:0:1',1,0,0,'2015-05-09 14:59:44',1,NULL),(2,'22','22','2015-05-09 14:59:55','0:0:0:0:0:0:0:1',1,0,0,'2015-05-09 14:59:55',1,NULL),(3,'33','33','2015-05-09 15:00:00','0:0:0:0:0:0:0:1',1,0,0,'2015-05-09 15:00:00',1,NULL),(4,'44','44','2015-05-09 15:00:05','0:0:0:0:0:0:0:1',1,0,0,'2015-05-09 15:00:05',1,NULL),(5,'55','55','2015-05-09 15:00:10','0:0:0:0:0:0:0:1',1,0,0,'2015-05-09 15:00:10',1,NULL),(6,'66','66','2015-05-09 15:00:15','0:0:0:0:0:0:0:1',1,0,0,'2015-05-09 15:00:15',1,NULL),(7,'77','77','2015-05-09 15:00:21','0:0:0:0:0:0:0:1',1,0,0,'2015-05-09 15:00:21',1,NULL),(8,'88','88','2015-05-09 15:00:26','0:0:0:0:0:0:0:1',1,0,0,'2015-05-09 15:00:26',1,NULL),(9,'99','99','2015-05-09 15:00:32','0:0:0:0:0:0:0:1',1,0,0,'2015-05-09 15:00:32',1,NULL),(10,'12','12','2015-05-09 15:00:38','0:0:0:0:0:0:0:1',1,0,0,'2015-05-09 15:00:38',1,NULL),(11,'13','13','2015-05-09 15:00:45','0:0:0:0:0:0:0:1',1,0,1,'2015-05-09 19:05:33',1,1);
/*!40000 ALTER TABLE `njit_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_user`
--

DROP TABLE IF EXISTS `njit_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `departmentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36766123AFCCE38F` (`departmentId`),
  CONSTRAINT `FK36766123AFCCE38F` FOREIGN KEY (`departmentId`) REFERENCES `njit_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_user`
--

LOCK TABLES `njit_user` WRITE;
/*!40000 ALTER TABLE `njit_user` DISABLE KEYS */;
INSERT INTO `njit_user` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','超级管理员',NULL,NULL,NULL,NULL,NULL),(2,'100','81dc9bdb52d04dc20036dbd8313ed055','叶核亚','女','','','',19),(3,'001','81dc9bdb52d04dc20036dbd8313ed055','朱荣鑫','男','','','',26),(4,'101',NULL,'黄炜','女','','','',19),(5,'102',NULL,'温志平','女','','','',20),(6,'104',NULL,'徐金宝','男','','','',19),(7,'103',NULL,'程初','女','','','',20),(8,'105',NULL,'吴秀波','男','','','',21),(9,'106',NULL,'周立波','男','','','',22),(10,'107',NULL,'郑胜男','女','','','',23),(11,'002','81dc9bdb52d04dc20036dbd8313ed055','刘德华','男','','','',26),(12,'003','81dc9bdb52d04dc20036dbd8313ed055','周润发','男','','','',26),(13,NULL,NULL,'test_65',NULL,NULL,NULL,NULL,26),(14,NULL,NULL,'test_66',NULL,NULL,NULL,NULL,26),(15,NULL,NULL,'test_67',NULL,NULL,NULL,NULL,26),(16,NULL,NULL,'test_68',NULL,NULL,NULL,NULL,26),(17,NULL,NULL,'test_69',NULL,NULL,NULL,NULL,26),(18,NULL,NULL,'test_70',NULL,NULL,NULL,NULL,26),(19,NULL,NULL,'test_71',NULL,NULL,NULL,NULL,26),(20,NULL,NULL,'test_72',NULL,NULL,NULL,NULL,26),(21,NULL,NULL,'test_73',NULL,NULL,NULL,NULL,26),(22,NULL,NULL,'test_74',NULL,NULL,NULL,NULL,26),(23,NULL,NULL,'test_75',NULL,NULL,NULL,NULL,26),(24,NULL,NULL,'test_76',NULL,NULL,NULL,NULL,26),(25,NULL,NULL,'test_77',NULL,NULL,NULL,NULL,26),(26,NULL,NULL,'test_78',NULL,NULL,NULL,NULL,26),(27,NULL,NULL,'test_79',NULL,NULL,NULL,NULL,26),(28,NULL,NULL,'test_80',NULL,NULL,NULL,NULL,26),(29,NULL,NULL,'test_81',NULL,NULL,NULL,NULL,26),(30,NULL,NULL,'test_82',NULL,NULL,NULL,NULL,26),(31,NULL,NULL,'test_83',NULL,NULL,NULL,NULL,26),(32,NULL,NULL,'test_84',NULL,NULL,NULL,NULL,26),(33,NULL,NULL,'test_85',NULL,NULL,NULL,NULL,26),(34,NULL,NULL,'test_86',NULL,NULL,NULL,NULL,26),(35,NULL,NULL,'test_87',NULL,NULL,NULL,NULL,26),(36,NULL,NULL,'test_88',NULL,NULL,NULL,NULL,26),(37,NULL,NULL,'test_89',NULL,NULL,NULL,NULL,26),(38,NULL,NULL,'test_90',NULL,NULL,NULL,NULL,26),(39,NULL,NULL,'test_91',NULL,NULL,NULL,NULL,26),(40,NULL,NULL,'test_92',NULL,NULL,NULL,NULL,26),(41,NULL,NULL,'test_93',NULL,NULL,NULL,NULL,26),(42,NULL,NULL,'test_94',NULL,NULL,NULL,NULL,26),(43,NULL,NULL,'test_95',NULL,NULL,NULL,NULL,26),(44,NULL,NULL,'test_96',NULL,NULL,NULL,NULL,26),(45,NULL,NULL,'test_97',NULL,NULL,NULL,NULL,26),(46,NULL,NULL,'test_98',NULL,NULL,NULL,NULL,26),(47,NULL,NULL,'test_99',NULL,NULL,NULL,NULL,26),(48,NULL,NULL,'test_100',NULL,NULL,NULL,NULL,26),(49,NULL,NULL,'test_101',NULL,NULL,NULL,NULL,26),(50,NULL,NULL,'test_102',NULL,NULL,NULL,NULL,26),(51,NULL,NULL,'test_103',NULL,NULL,NULL,NULL,26),(52,NULL,NULL,'test_104',NULL,NULL,NULL,NULL,26),(53,NULL,NULL,'test_105',NULL,NULL,NULL,NULL,26),(54,NULL,NULL,'test_106',NULL,NULL,NULL,NULL,26),(55,NULL,NULL,'test_107',NULL,NULL,NULL,NULL,26),(56,NULL,NULL,'test_108',NULL,NULL,NULL,NULL,26),(57,NULL,NULL,'test_109',NULL,NULL,NULL,NULL,26),(58,NULL,NULL,'test_110',NULL,NULL,NULL,NULL,26),(59,NULL,NULL,'test_111',NULL,NULL,NULL,NULL,26),(60,NULL,NULL,'test_112',NULL,NULL,NULL,NULL,26),(61,NULL,NULL,'test_113',NULL,NULL,NULL,NULL,26),(62,NULL,NULL,'test_114',NULL,NULL,NULL,NULL,26),(63,NULL,NULL,'test_115',NULL,NULL,NULL,NULL,26),(64,NULL,NULL,'test_116',NULL,NULL,NULL,NULL,26),(65,NULL,NULL,'test_117',NULL,NULL,NULL,NULL,NULL),(66,NULL,NULL,'test_118',NULL,NULL,NULL,NULL,NULL),(67,NULL,NULL,'test_119',NULL,NULL,NULL,NULL,NULL),(68,NULL,NULL,'test_120',NULL,NULL,NULL,NULL,NULL),(69,NULL,NULL,'test_121',NULL,NULL,NULL,NULL,NULL),(70,NULL,NULL,'test_122',NULL,NULL,NULL,NULL,NULL),(71,NULL,NULL,'test_123',NULL,NULL,NULL,NULL,NULL),(72,NULL,NULL,'test_124',NULL,NULL,NULL,NULL,NULL),(73,NULL,NULL,'test_125',NULL,NULL,NULL,NULL,NULL),(74,NULL,NULL,'test_126',NULL,NULL,NULL,NULL,NULL),(75,NULL,NULL,'test_127',NULL,NULL,NULL,NULL,NULL),(76,NULL,NULL,'test_128',NULL,NULL,NULL,NULL,NULL),(77,NULL,NULL,'test_129',NULL,NULL,NULL,NULL,NULL),(78,NULL,NULL,'test_130',NULL,NULL,NULL,NULL,NULL),(79,NULL,NULL,'test_131',NULL,NULL,NULL,NULL,NULL),(80,NULL,NULL,'test_132',NULL,NULL,NULL,NULL,NULL),(81,NULL,NULL,'test_133',NULL,NULL,NULL,NULL,NULL),(82,NULL,NULL,'test_134',NULL,NULL,NULL,NULL,NULL),(83,NULL,NULL,'test_135',NULL,NULL,NULL,NULL,NULL),(84,NULL,NULL,'test_136',NULL,NULL,NULL,NULL,NULL),(85,NULL,NULL,'test_137',NULL,NULL,NULL,NULL,NULL),(86,NULL,NULL,'test_138',NULL,NULL,NULL,NULL,NULL),(87,NULL,NULL,'test_139',NULL,NULL,NULL,NULL,NULL),(88,NULL,NULL,'test_140',NULL,NULL,NULL,NULL,NULL),(89,NULL,NULL,'test_141',NULL,NULL,NULL,NULL,NULL),(90,NULL,NULL,'test_142',NULL,NULL,NULL,NULL,NULL),(91,NULL,NULL,'test_143',NULL,NULL,NULL,NULL,NULL),(92,NULL,NULL,'test_144',NULL,NULL,NULL,NULL,NULL),(93,NULL,NULL,'test_145',NULL,NULL,NULL,NULL,NULL),(94,NULL,NULL,'test_146',NULL,NULL,NULL,NULL,NULL),(95,NULL,NULL,'test_147',NULL,NULL,NULL,NULL,NULL),(96,NULL,NULL,'test_148',NULL,NULL,NULL,NULL,NULL),(97,NULL,NULL,'test_149',NULL,NULL,NULL,NULL,NULL),(98,NULL,NULL,'test_150',NULL,NULL,NULL,NULL,NULL),(99,NULL,NULL,'test_151',NULL,NULL,NULL,NULL,NULL),(100,NULL,NULL,'test_152',NULL,NULL,NULL,NULL,NULL),(101,NULL,NULL,'test_153',NULL,NULL,NULL,NULL,NULL),(102,NULL,NULL,'test_154',NULL,NULL,NULL,NULL,NULL),(103,NULL,NULL,'test_155',NULL,NULL,NULL,NULL,NULL),(104,NULL,NULL,'test_156',NULL,NULL,NULL,NULL,NULL),(105,NULL,NULL,'test_157',NULL,NULL,NULL,NULL,NULL),(106,NULL,NULL,'test_158',NULL,NULL,NULL,NULL,NULL),(107,NULL,NULL,'test_159',NULL,NULL,NULL,NULL,NULL),(108,NULL,NULL,'test_160',NULL,NULL,NULL,NULL,NULL),(109,NULL,NULL,'test_161',NULL,NULL,NULL,NULL,NULL),(110,NULL,NULL,'test_162',NULL,NULL,NULL,NULL,NULL),(111,NULL,NULL,'test_163',NULL,NULL,NULL,NULL,NULL),(112,NULL,NULL,'test_164',NULL,NULL,NULL,NULL,NULL),(113,NULL,NULL,'test_165',NULL,NULL,NULL,NULL,NULL),(114,NULL,NULL,'test_166',NULL,NULL,NULL,NULL,NULL),(115,NULL,NULL,'test_167',NULL,NULL,NULL,NULL,NULL),(116,NULL,NULL,'test_168',NULL,NULL,NULL,NULL,NULL),(117,NULL,NULL,'test_169',NULL,NULL,NULL,NULL,NULL),(118,NULL,NULL,'test_170',NULL,NULL,NULL,NULL,NULL),(119,NULL,NULL,'test_171',NULL,NULL,NULL,NULL,NULL),(120,NULL,NULL,'test_172',NULL,NULL,NULL,NULL,NULL),(121,NULL,NULL,'test_173',NULL,NULL,NULL,NULL,NULL),(122,NULL,NULL,'test_174',NULL,NULL,NULL,NULL,NULL),(123,NULL,NULL,'test_175',NULL,NULL,NULL,NULL,NULL),(124,NULL,NULL,'test_176',NULL,NULL,NULL,NULL,NULL),(125,NULL,NULL,'test_177',NULL,NULL,NULL,NULL,NULL),(126,NULL,NULL,'test_178',NULL,NULL,NULL,NULL,NULL),(127,NULL,NULL,'test_179',NULL,NULL,NULL,NULL,NULL),(128,NULL,NULL,'test_180',NULL,NULL,NULL,NULL,NULL),(129,NULL,NULL,'test_181',NULL,NULL,NULL,NULL,NULL),(130,NULL,NULL,'test_182',NULL,NULL,NULL,NULL,NULL),(131,NULL,NULL,'test_183',NULL,NULL,NULL,NULL,NULL),(132,NULL,NULL,'test_184',NULL,NULL,NULL,NULL,NULL),(133,NULL,NULL,'test_185',NULL,NULL,NULL,NULL,NULL),(134,NULL,NULL,'test_186',NULL,NULL,NULL,NULL,NULL),(135,NULL,NULL,'test_187',NULL,NULL,NULL,NULL,NULL),(136,NULL,NULL,'test_188',NULL,NULL,NULL,NULL,NULL),(137,NULL,NULL,'test_189',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `njit_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `njit_user_role`
--

DROP TABLE IF EXISTS `njit_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `njit_user_role` (
  `roleId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FK2A881612F83020D7` (`roleId`),
  KEY `FK2A881612FD857641` (`userId`),
  CONSTRAINT `FK2A881612F83020D7` FOREIGN KEY (`roleId`) REFERENCES `njit_role` (`id`),
  CONSTRAINT `FK2A881612FD857641` FOREIGN KEY (`userId`) REFERENCES `njit_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `njit_user_role`
--

LOCK TABLES `njit_user_role` WRITE;
/*!40000 ALTER TABLE `njit_user_role` DISABLE KEYS */;
INSERT INTO `njit_user_role` VALUES (11,2),(11,4),(11,5),(11,6),(11,7),(11,8),(11,9),(11,10),(12,3),(12,11),(12,12);
/*!40000 ALTER TABLE `njit_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-09 19:37:26
