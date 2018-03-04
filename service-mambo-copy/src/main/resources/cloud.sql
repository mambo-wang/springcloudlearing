-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.1.71-community - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             8.2.0.4675
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for cloud
CREATE DATABASE IF NOT EXISTS `cloud` /*!40100 DEFAULT CHARACTER SET gbk */;
USE `cloud`;


-- Dumping structure for table cloud.tbl_role
CREATE TABLE IF NOT EXISTS `tbl_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE` varchar(256) NOT NULL DEFAULT '0',
  `DESCRIPTION` varchar(256) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table cloud.tbl_role: ~2 rows (approximately)
/*!40000 ALTER TABLE `tbl_role` DISABLE KEYS */;
INSERT INTO `tbl_role` (`ID`, `ROLE`, `DESCRIPTION`) VALUES
	(1, 'ADMIN', '管理员'),
	(2, 'USER', '普通用户');
/*!40000 ALTER TABLE `tbl_role` ENABLE KEYS */;


-- Dumping structure for table cloud.tbl_user
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `LOGIN_NAME` varchar(256) DEFAULT NULL COMMENT '登录名',
  `USERNAME` varchar(256) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(256) DEFAULT NULL COMMENT '密码',
  `COME_YEAR` int(8) DEFAULT '2017' COMMENT '入职年份',
  `SALT` varchar(256) DEFAULT NULL COMMENT '加密密码的盐',
  `STATE` int(8) DEFAULT NULL COMMENT '用户状态0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.  ',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- Dumping data for table cloud.tbl_user: ~2 rows (approximately)
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` (`ID`, `LOGIN_NAME`, `USERNAME`, `PASSWORD`, `COME_YEAR`, `SALT`, `STATE`) VALUES
	(1, 'admin', 'admin', 'd3c59d25033dbf980d29554025c23a75', 2017, '8d78869f470951332959580424d4bf4f', 1),
	(2, 'mambo', '曼联', 'f0e9d807df59dd4a5bc29d2289b636c4', 2017, '6070c120beb01d3f2d9d30b5c76104b7', 1);
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;


-- Dumping structure for table cloud.tbl_user_role
CREATE TABLE IF NOT EXISTS `tbl_user_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL DEFAULT '0',
  `ROLE_ID` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table cloud.tbl_user_role: ~2 rows (approximately)
/*!40000 ALTER TABLE `tbl_user_role` DISABLE KEYS */;
INSERT INTO `tbl_user_role` (`ID`, `USER_ID`, `ROLE_ID`) VALUES
	(1, 1, 1),
	(2, 2, 2);
/*!40000 ALTER TABLE `tbl_user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
