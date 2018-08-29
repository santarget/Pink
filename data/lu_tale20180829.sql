/*
Navicat MySQL Data Transfer

Source Server         : MySQL5.7_localhost
Source Server Version : 50641
Source Host           : 127.0.0.1:3306
Source Database       : lu_tale

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2018-08-29 23:22:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `addmoneyinfo`
-- ----------------------------
DROP TABLE IF EXISTS `addmoneyinfo`;
CREATE TABLE `addmoneyinfo` (
  `addMoneyInfoPK` char(20) NOT NULL COMMENT '????????',
  `customerNum` char(20) DEFAULT NULL COMMENT '????',
  `transactionId` varchar(64) NOT NULL COMMENT '????',
  `addMountVal` decimal(15,2) NOT NULL COMMENT '?????',
  `beanNum` bigint(12) DEFAULT NULL COMMENT '???????',
  `addMoneyDesc` varchar(128) DEFAULT NULL COMMENT '????',
  `createTime` datetime DEFAULT NULL COMMENT '????',
  `createUser` varchar(20) DEFAULT NULL COMMENT '???',
  PRIMARY KEY (`addMoneyInfoPK`),
  UNIQUE KEY `Index_transactionId` (`transactionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??????';

-- ----------------------------
-- Records of addmoneyinfo
-- ----------------------------
INSERT INTO `addmoneyinfo` VALUES ('A0829224655708001118', 'C0825231708734008524', '123123122223231', '123.00', '1230', null, '2018-08-29 22:46:56', 'APP');

-- ----------------------------
-- Table structure for `buybeaninfo`
-- ----------------------------
DROP TABLE IF EXISTS `buybeaninfo`;
CREATE TABLE `buybeaninfo` (
  `buyBeanInfoPK` char(20) NOT NULL COMMENT '购买花豆流水信息主键',
  `customerNum` char(15) DEFAULT NULL COMMENT '会员编号',
  `buyAmount` decimal(15,2) DEFAULT NULL COMMENT '购买的金额',
  `buyBeanNum` bigint(20) DEFAULT NULL COMMENT '获取的花豆数量',
  `buyTime` datetime DEFAULT NULL COMMENT '购买时间',
  `createUser` varchar(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`buyBeanInfoPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购买花豆流水信息';

-- ----------------------------
-- Records of buybeaninfo
-- ----------------------------

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customerNum` char(20) NOT NULL COMMENT '????',
  `fansOrgInfoNum` char(20) DEFAULT NULL COMMENT '????',
  `weiboId` varchar(64) DEFAULT NULL COMMENT '??Id',
  `weiboNum` varchar(64) DEFAULT NULL COMMENT '????',
  `weiboName` varchar(256) DEFAULT NULL COMMENT '????',
  `customerStatus` char(2) DEFAULT NULL COMMENT '????',
  `specialFlag` char(2) DEFAULT NULL COMMENT '????',
  `memo` varchar(256) DEFAULT NULL COMMENT '??????',
  `createTime` datetime DEFAULT NULL COMMENT '????',
  `createUser` varchar(20) DEFAULT NULL COMMENT '???',
  PRIMARY KEY (`customerNum`),
  UNIQUE KEY `Index_weiboId` (`weiboId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='????';

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('C0825231708734008524', '1', '123', '12312', '123123', '1', '0', null, null, null);
INSERT INTO `customer` VALUES ('C0827000938150076046', '1', 'weiboidA111111111', '12312312321', '微博名称A', '1', '0', null, '2018-08-27 00:09:43', 'APP');
INSERT INTO `customer` VALUES ('C0827002054681009578', '1', 'weiboidA1111111', '1231231231', '微博名称A', '1', '0', null, '2018-08-27 00:20:55', 'APP');

-- ----------------------------
-- Table structure for `fansorginfo`
-- ----------------------------
DROP TABLE IF EXISTS `fansorginfo`;
CREATE TABLE `fansorginfo` (
  `fansOrgInfoNum` char(20) NOT NULL COMMENT '????',
  `fansOrgInfoName` varchar(128) DEFAULT NULL COMMENT '??????',
  `fansOrgInfotDesc` varchar(128) DEFAULT NULL COMMENT '??????',
  `fansOrgInfoState` varchar(2) DEFAULT NULL COMMENT '??????',
  `createTime` datetime DEFAULT NULL COMMENT '????',
  `createUser` varchar(20) DEFAULT NULL COMMENT '???',
  PRIMARY KEY (`fansOrgInfoNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??????';

-- ----------------------------
-- Records of fansorginfo
-- ----------------------------
INSERT INTO `fansorginfo` VALUES ('1', '粉丝组织1', '粉丝组织1描述', '1', '2018-08-25 23:13:15', 'APP');
INSERT INTO `fansorginfo` VALUES ('2', '粉丝组织2', '粉丝组织2描述', '1', '2018-08-25 23:13:21', 'APP');
INSERT INTO `fansorginfo` VALUES ('3', '粉丝组织3', '粉丝组织3描述', '1', '2018-08-25 23:13:26', 'APP');
INSERT INTO `fansorginfo` VALUES ('4', '粉丝组织4', '粉丝组织4描述信息', '1', '2018-08-26 20:10:49', 'APP');
INSERT INTO `fansorginfo` VALUES ('7', '测试粉丝组织7', '测试粉丝组织7描述', '1', '2018-08-26 23:15:46', 'APP');

-- ----------------------------
-- Table structure for `productinfo`
-- ----------------------------
DROP TABLE IF EXISTS `productinfo`;
CREATE TABLE `productinfo` (
  `productNum` char(20) NOT NULL COMMENT '????',
  `productName` varchar(128) DEFAULT NULL COMMENT '????',
  `price` decimal(15,2) DEFAULT NULL COMMENT '??',
  `productDesc` varchar(128) DEFAULT NULL COMMENT '????',
  `productType` varchar(10) DEFAULT NULL COMMENT '????',
  `productState` varchar(2) DEFAULT NULL COMMENT '????',
  `createTime` datetime DEFAULT NULL COMMENT '????',
  `createUser` varchar(20) DEFAULT NULL COMMENT '???',
  PRIMARY KEY (`productNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='????';

-- ----------------------------
-- Records of productinfo
-- ----------------------------
INSERT INTO `productinfo` VALUES ('1', '按次产品1名称', '12.00', '按次产品1描述', '0', '1', '2018-08-25 23:14:23', null);
INSERT INTO `productinfo` VALUES ('2', '包月产品2名称', '2.00', '包月产品2描述', '1', '1', '2018-08-26 23:16:41', null);
INSERT INTO `productinfo` VALUES ('3', '测试产品名称', '123.00', '阿斯蒂芬阿萨德', '1', '1', '2018-08-26 23:24:13', 'APP');

-- ----------------------------
-- Table structure for `spendbeaninfo`
-- ----------------------------
DROP TABLE IF EXISTS `spendbeaninfo`;
CREATE TABLE `spendbeaninfo` (
  `spendBeanInfoPK` char(20) NOT NULL COMMENT '??????????',
  `customerNum` char(20) NOT NULL COMMENT '????',
  `transactionId` varchar(64) NOT NULL COMMENT '????',
  `spendBeanNum` bigint(20) NOT NULL COMMENT '???????',
  `spendDesc` varchar(200) DEFAULT NULL COMMENT '????',
  `spendTime` datetime DEFAULT NULL COMMENT '????',
  `createUser` varchar(20) DEFAULT NULL COMMENT '???',
  PRIMARY KEY (`spendBeanInfoPK`),
  KEY `Index_transactionId` (`transactionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='????????';

-- ----------------------------
-- Records of spendbeaninfo
-- ----------------------------
INSERT INTO `spendbeaninfo` VALUES ('1', '1', '1', '12', '1', null, null);
INSERT INTO `spendbeaninfo` VALUES ('2', '2', '22', '22', '2222', null, null);
INSERT INTO `spendbeaninfo` VALUES ('3', '1', '12', '33', '345', null, null);
INSERT INTO `spendbeaninfo` VALUES ('S0827065625236026361', 'C0827002054681009578', '123123122223231', '123', '消费说明信息', '2018-08-27 06:56:25', 'APP');
INSERT INTO `spendbeaninfo` VALUES ('S0829224856595018183', 'C0827002054681009578', '12312312223231', '123', '消费说明信息：例如使用的是那个按次产品', '2018-08-29 22:48:57', 'APP');

-- ----------------------------
-- Table structure for `t_attach`
-- ----------------------------
DROP TABLE IF EXISTS `t_attach`;
CREATE TABLE `t_attach` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) NOT NULL DEFAULT '',
  `ftype` varchar(50) DEFAULT '',
  `fkey` text NOT NULL,
  `authorId` int(10) DEFAULT NULL,
  `created` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_attach
-- ----------------------------
INSERT INTO `t_attach` VALUES ('16', 'upload/2018/05/0ef1so63luhn9olt146ci7vc8u.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/0ef1so63luhn9olt146ci7vc8u.jpg', '1', '1525158033');
INSERT INTO `t_attach` VALUES ('17', 'upload/2018/05/rum3qpmfo6h24pvcmmjcnhehtc.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/rum3qpmfo6h24pvcmmjcnhehtc.jpg', '1', '1525158058');
INSERT INTO `t_attach` VALUES ('18', 'upload/2018/05/3ctmdjc8t4j0gq1od08m23892o.png', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/3ctmdjc8t4j0gq1od08m23892o.png', '1', '1525158058');
INSERT INTO `t_attach` VALUES ('19', 'upload/2018/05/7fjcluf9k8ge0qihhqo073dkp8.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/7fjcluf9k8ge0qihhqo073dkp8.jpg', '1', '1525158102');
INSERT INTO `t_attach` VALUES ('20', 'upload/2018/05/5mth8ji26sibhpd1m89vh789nr.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/5mth8ji26sibhpd1m89vh789nr.jpg', '1', '1525158103');
INSERT INTO `t_attach` VALUES ('21', 'upload/2018/05/35si3d2as2gtnrr66utok87hii.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/35si3d2as2gtnrr66utok87hii.jpg', '1', '1525158104');
INSERT INTO `t_attach` VALUES ('22', 'upload/2018/05/6cern2gl1mje1p2n3vckr9kg42.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/6cern2gl1mje1p2n3vckr9kg42.jpg', '1', '1525158145');
INSERT INTO `t_attach` VALUES ('23', 'upload/2018/05/2nj54lgjm2h5arq8r8idjcm8vu.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/2nj54lgjm2h5arq8r8idjcm8vu.jpg', '1', '1525158146');
INSERT INTO `t_attach` VALUES ('24', 'upload/2018/05/seuf3fpnosgampf9b64ffd2kvc.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/seuf3fpnosgampf9b64ffd2kvc.jpg', '1', '1525158147');
INSERT INTO `t_attach` VALUES ('25', 'upload/2018/05/rh7g86tp22hd9oq6kleckr4isl.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/rh7g86tp22hd9oq6kleckr4isl.jpg', '1', '1525158172');
INSERT INTO `t_attach` VALUES ('26', 'upload/2018/05/op4lnphhgmhpmonapbe6m3aam3.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/op4lnphhgmhpmonapbe6m3aam3.jpg', '1', '1525158180');
INSERT INTO `t_attach` VALUES ('27', 'upload/2018/05/tlmuit4ahijjrosc3l6t9icqi4.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/tlmuit4ahijjrosc3l6t9icqi4.jpg', '1', '1525158203');
INSERT INTO `t_attach` VALUES ('28', 'upload/2018/05/3dli566ni4iioouvtl34er09g9.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/3dli566ni4iioouvtl34er09g9.jpg', '1', '1525158210');
INSERT INTO `t_attach` VALUES ('29', 'upload/2018/05/r72s8160rejg8om374u832ujkm.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/r72s8160rejg8om374u832ujkm.jpg', '1', '1525162637');
INSERT INTO `t_attach` VALUES ('30', 'upload/2018/05/6ffm0m4ho8j0mpqv7tjl5ba690.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/6ffm0m4ho8j0mpqv7tjl5ba690.jpg', '1', '1525162670');
INSERT INTO `t_attach` VALUES ('31', 'upload/2018/05/s3gdoifqluik5o22snliphbck9.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/s3gdoifqluik5o22snliphbck9.jpg', '1', '1525162703');
INSERT INTO `t_attach` VALUES ('32', 'upload/2018/05/uu2ta03pechqsppgsr8rv2hfsh.jpg', 'image', 'http://ozlpw4ja9.bkt.clouddn.com/upload/2018/05/uu2ta03pechqsppgsr8rv2hfsh.jpg', '1', '1525162712');

-- ----------------------------
-- Table structure for `t_comments`
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
  `coid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cid` int(10) unsigned DEFAULT '0',
  `created` int(10) unsigned DEFAULT '0',
  `author` varchar(200) DEFAULT NULL,
  `authorId` int(10) unsigned DEFAULT '0',
  `ownerId` int(10) unsigned DEFAULT '0',
  `mail` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `agent` varchar(200) DEFAULT NULL,
  `content` text,
  `type` varchar(16) DEFAULT 'comment',
  `status` varchar(16) DEFAULT 'approved',
  `parent` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`coid`),
  KEY `cid` (`cid`),
  KEY `created` (`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_comments
-- ----------------------------

-- ----------------------------
-- Table structure for `t_contents`
-- ----------------------------
DROP TABLE IF EXISTS `t_contents`;
CREATE TABLE `t_contents` (
  `cid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `titlePic` varchar(55) DEFAULT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `created` int(10) unsigned DEFAULT '0',
  `modified` int(10) unsigned DEFAULT '0',
  `content` text COMMENT '????',
  `authorId` int(10) unsigned DEFAULT '0',
  `type` varchar(16) DEFAULT 'post',
  `status` varchar(16) DEFAULT 'publish',
  `tags` varchar(200) DEFAULT NULL,
  `categories` varchar(200) DEFAULT NULL,
  `hits` int(10) unsigned DEFAULT '0',
  `commentsNum` int(10) unsigned DEFAULT '0',
  `allowComment` tinyint(1) DEFAULT '1',
  `allowPing` tinyint(1) DEFAULT '1',
  `allowFeed` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `slug` (`slug`),
  KEY `created` (`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_contents
-- ----------------------------

-- ----------------------------
-- Table structure for `t_logs`
-- ----------------------------
DROP TABLE IF EXISTS `t_logs`;
CREATE TABLE `t_logs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '????',
  `action` varchar(100) DEFAULT NULL COMMENT '??',
  `data` varchar(2000) DEFAULT NULL COMMENT '??',
  `authorId` int(10) DEFAULT NULL COMMENT '????',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip??',
  `created` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_logs
-- ----------------------------
INSERT INTO `t_logs` VALUES ('77', '登录后台', null, '1', '127.0.0.1', '1535038848');
INSERT INTO `t_logs` VALUES ('78', '登录后台', null, '1', '127.0.0.1', '1535038950');
INSERT INTO `t_logs` VALUES ('79', '登录后台', null, '1', '127.0.0.1', '1535114606');
INSERT INTO `t_logs` VALUES ('80', '登录后台', null, '1', '127.0.0.1', '1535115788');
INSERT INTO `t_logs` VALUES ('81', '登录后台', null, '1', '127.0.0.1', '1535116518');
INSERT INTO `t_logs` VALUES ('82', '登录后台', null, '1', '127.0.0.1', '1535117314');
INSERT INTO `t_logs` VALUES ('83', '登录后台', null, '1', '127.0.0.1', '1535119887');
INSERT INTO `t_logs` VALUES ('84', '登录后台', null, '1', '127.0.0.1', '1535120463');
INSERT INTO `t_logs` VALUES ('85', '登录后台', null, '1', '127.0.0.1', '1535122896');
INSERT INTO `t_logs` VALUES ('86', '登录后台', null, '1', '127.0.0.1', '1535123679');
INSERT INTO `t_logs` VALUES ('87', '登录后台', null, '1', '127.0.0.1', '1535123730');
INSERT INTO `t_logs` VALUES ('88', '登录后台', null, '1', '127.0.0.1', '1535124811');
INSERT INTO `t_logs` VALUES ('89', '登录后台', null, '1', '127.0.0.1', '1535125228');
INSERT INTO `t_logs` VALUES ('90', '登录后台', null, '1', '127.0.0.1', '1535126575');
INSERT INTO `t_logs` VALUES ('91', '登录后台', null, '1', '127.0.0.1', '1535148944');
INSERT INTO `t_logs` VALUES ('92', '登录后台', null, '1', '127.0.0.1', '1535203069');
INSERT INTO `t_logs` VALUES ('93', '登录后台', null, '1', '127.0.0.1', '1535203260');
INSERT INTO `t_logs` VALUES ('94', '登录后台', null, '1', '127.0.0.1', '1535204776');
INSERT INTO `t_logs` VALUES ('95', '登录后台', null, '1', '127.0.0.1', '1535205875');
INSERT INTO `t_logs` VALUES ('96', '登录后台', null, '1', '127.0.0.1', '1535206654');
INSERT INTO `t_logs` VALUES ('97', '登录后台', null, '1', '127.0.0.1', '1535206861');
INSERT INTO `t_logs` VALUES ('98', '登录后台', null, '1', '127.0.0.1', '1535208091');
INSERT INTO `t_logs` VALUES ('99', '登录后台', null, '1', '127.0.0.1', '1535208590');
INSERT INTO `t_logs` VALUES ('100', '登录后台', null, '1', '127.0.0.1', '1535209878');
INSERT INTO `t_logs` VALUES ('101', '登录后台', null, '1', '127.0.0.1', '1535211432');
INSERT INTO `t_logs` VALUES ('102', '登录后台', null, '1', '127.0.0.1', '1535242910');
INSERT INTO `t_logs` VALUES ('103', '登录后台', null, '1', '127.0.0.1', '1535257196');
INSERT INTO `t_logs` VALUES ('104', '登录后台', null, '1', '127.0.0.1', '1535257890');
INSERT INTO `t_logs` VALUES ('105', '登录后台', null, '1', '127.0.0.1', '1535258156');
INSERT INTO `t_logs` VALUES ('106', '登录后台', null, '1', '127.0.0.1', '1535258497');
INSERT INTO `t_logs` VALUES ('107', '登录后台', null, '1', '127.0.0.1', '1535264205');
INSERT INTO `t_logs` VALUES ('108', '登录后台', null, '1', '127.0.0.1', '1535264472');
INSERT INTO `t_logs` VALUES ('109', '登录后台', null, '1', '127.0.0.1', '1535265528');
INSERT INTO `t_logs` VALUES ('110', '登录后台', null, '1', '127.0.0.1', '1535265594');
INSERT INTO `t_logs` VALUES ('111', '登录后台', null, '1', '127.0.0.1', '1535267575');
INSERT INTO `t_logs` VALUES ('112', '登录后台', null, '1', '127.0.0.1', '1535270455');
INSERT INTO `t_logs` VALUES ('113', '登录后台', null, '1', '127.0.0.1', '1535274750');
INSERT INTO `t_logs` VALUES ('114', '登录后台', null, '1', '127.0.0.1', '1535285160');
INSERT INTO `t_logs` VALUES ('115', '登录后台', null, '1', '127.0.0.1', '1535285590');
INSERT INTO `t_logs` VALUES ('116', '登录后台', null, '1', '127.0.0.1', '1535285815');
INSERT INTO `t_logs` VALUES ('117', '登录后台', null, '1', '127.0.0.1', '1535288408');
INSERT INTO `t_logs` VALUES ('118', '登录后台', null, '1', '127.0.0.1', '1535289944');
INSERT INTO `t_logs` VALUES ('119', '修改个人信息', '{\"uid\":1,\"email\":\"admin@qq.com\",\"screenName\":\"admin\"}', '1', '127.0.0.1', '1535290044');
INSERT INTO `t_logs` VALUES ('120', '登录后台', null, '1', '127.0.0.1', '1535290863');
INSERT INTO `t_logs` VALUES ('121', '登录后台', null, '1', '127.0.0.1', '1535291108');
INSERT INTO `t_logs` VALUES ('122', '登录后台', null, '1', '127.0.0.1', '1535291313');
INSERT INTO `t_logs` VALUES ('123', '登录后台', null, '1', '127.0.0.1', '1535296429');
INSERT INTO `t_logs` VALUES ('124', '登录后台', null, '1', '127.0.0.1', '1535296827');
INSERT INTO `t_logs` VALUES ('125', '登录后台', null, '1', '127.0.0.1', '1535297427');
INSERT INTO `t_logs` VALUES ('126', '登录后台', null, '1', '127.0.0.1', '1535298045');
INSERT INTO `t_logs` VALUES ('127', '登录后台', null, '1', '127.0.0.1', '1535298313');
INSERT INTO `t_logs` VALUES ('128', '登录后台', null, '1', '127.0.0.1', '1535298323');
INSERT INTO `t_logs` VALUES ('129', '登录后台', null, '1', '127.0.0.1', '1535298744');
INSERT INTO `t_logs` VALUES ('130', '登录后台', null, '1', '127.0.0.1', '1535299753');
INSERT INTO `t_logs` VALUES ('131', '登录后台', null, '1', '127.0.0.1', '1535300212');
INSERT INTO `t_logs` VALUES ('132', '登录后台', null, '1', '127.0.0.1', '1535300449');
INSERT INTO `t_logs` VALUES ('133', '登录后台', null, '1', '127.0.0.1', '1535301426');
INSERT INTO `t_logs` VALUES ('134', '登录后台', null, '1', '127.0.0.1', '1535301542');
INSERT INTO `t_logs` VALUES ('135', '登录后台', null, '1', '127.0.0.1', '1535381482');
INSERT INTO `t_logs` VALUES ('136', '登录后台', null, '1', '127.0.0.1', '1535383327');
INSERT INTO `t_logs` VALUES ('137', '登录后台', null, '1', '127.0.0.1', '1535386009');
INSERT INTO `t_logs` VALUES ('138', '登录后台', null, '1', '127.0.0.1', '1535409570');
INSERT INTO `t_logs` VALUES ('139', '登录后台', null, '1', '127.0.0.1', '1535410594');
INSERT INTO `t_logs` VALUES ('140', '修改密码', null, '1', '127.0.0.1', '1535410670');
INSERT INTO `t_logs` VALUES ('141', '登录后台', null, '1', '127.0.0.1', '1535410680');
INSERT INTO `t_logs` VALUES ('142', '修改密码', null, '1', '127.0.0.1', '1535410695');
INSERT INTO `t_logs` VALUES ('143', '登录后台', null, '1', '127.0.0.1', '1535467462');
INSERT INTO `t_logs` VALUES ('144', '修改密码', null, '1', '127.0.0.1', '1535467476');
INSERT INTO `t_logs` VALUES ('145', '登录后台', null, '1', '127.0.0.1', '1535467480');
INSERT INTO `t_logs` VALUES ('146', '登录后台', null, '1', '127.0.0.1', '1535467488');
INSERT INTO `t_logs` VALUES ('147', '登录后台', null, '1', '127.0.0.1', '1535468434');
INSERT INTO `t_logs` VALUES ('148', '登录后台', null, '1', '127.0.0.1', '1535468854');
INSERT INTO `t_logs` VALUES ('149', '登录后台', null, '1', '127.0.0.1', '1535553355');
INSERT INTO `t_logs` VALUES ('150', '登录后台', null, '1', '127.0.0.1', '1535554173');

-- ----------------------------
-- Table structure for `t_metas`
-- ----------------------------
DROP TABLE IF EXISTS `t_metas`;
CREATE TABLE `t_metas` (
  `mid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `type` varchar(32) NOT NULL DEFAULT '',
  `contentType` varchar(32) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `sort` int(10) unsigned DEFAULT '0',
  `parent` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`mid`),
  KEY `slug` (`slug`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_metas
-- ----------------------------

-- ----------------------------
-- Table structure for `t_options`
-- ----------------------------
DROP TABLE IF EXISTS `t_options`;
CREATE TABLE `t_options` (
  `name` varchar(32) NOT NULL DEFAULT '',
  `value` varchar(1000) DEFAULT '',
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_options
-- ----------------------------
INSERT INTO `t_options` VALUES ('baidu_site_verification', null, '???????');
INSERT INTO `t_options` VALUES ('google_site_verification', null, 'google?????');
INSERT INTO `t_options` VALUES ('site_description', null, '????');
INSERT INTO `t_options` VALUES ('site_keywords', null, null);
INSERT INTO `t_options` VALUES ('site_record', null, '???');
INSERT INTO `t_options` VALUES ('site_title', null, '????');
INSERT INTO `t_options` VALUES ('social_csdn', null, 'csdn');
INSERT INTO `t_options` VALUES ('social_github', null, 'github');
INSERT INTO `t_options` VALUES ('social_jianshu', null, '????');
INSERT INTO `t_options` VALUES ('social_resume', null, '????');
INSERT INTO `t_options` VALUES ('social_twitter', null, 'twitter');
INSERT INTO `t_options` VALUES ('social_weibo', null, '????');
INSERT INTO `t_options` VALUES ('social_zhihu', null, '????');

-- ----------------------------
-- Table structure for `t_relationships`
-- ----------------------------
DROP TABLE IF EXISTS `t_relationships`;
CREATE TABLE `t_relationships` (
  `cid` int(10) unsigned NOT NULL,
  `mid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cid`,`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_relationships
-- ----------------------------

-- ----------------------------
-- Table structure for `t_users`
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `homeUrl` varchar(200) DEFAULT NULL,
  `screenName` varchar(32) DEFAULT NULL,
  `created` int(10) unsigned DEFAULT '0',
  `activated` int(10) unsigned DEFAULT '0',
  `logged` int(10) unsigned DEFAULT '0',
  `groupName` varchar(16) DEFAULT 'visitor',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `name` (`username`),
  UNIQUE KEY `mail` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('1', 'admin', 'a66abb5684c45962d887564f08346e8d', '1034683568@qq.com', null, 'admin', '1490756162', '0', '0', 'visitor');

-- ----------------------------
-- Table structure for `useroderinfo`
-- ----------------------------
DROP TABLE IF EXISTS `useroderinfo`;
CREATE TABLE `useroderinfo` (
  `userOderInfoPK` char(20) NOT NULL COMMENT '用户订购关系主键',
  `productNum` char(20) DEFAULT NULL COMMENT '产品编号',
  `customerNum` char(20) DEFAULT NULL COMMENT '会员编号',
  `transactionId` varchar(64) NOT NULL COMMENT '交易编号',
  `weiboId` varchar(64) DEFAULT NULL COMMENT '微博Id',
  `weibosmallNumId` varchar(64) DEFAULT NULL COMMENT '微博小号Id',
  `deadlineTime` datetime DEFAULT NULL COMMENT '产品截止时间',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `createUser` varchar(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`userOderInfoPK`),
  UNIQUE KEY `IDX_transactionId` (`transactionId`),
  KEY `FK_Reference_7` (`productNum`),
  KEY `FK_Reference_8` (`customerNum`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`productNum`) REFERENCES `productinfo` (`productNum`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`customerNum`) REFERENCES `customer` (`customerNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户订购关系';

-- ----------------------------
-- Records of useroderinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `userssmallnum`
-- ----------------------------
DROP TABLE IF EXISTS `userssmallnum`;
CREATE TABLE `userssmallnum` (
  `smallNumPK` char(20) NOT NULL COMMENT '??',
  `customerNum` char(20) DEFAULT NULL COMMENT '????',
  `fansOrgInfoNum` char(20) DEFAULT NULL COMMENT '????',
  `usepwd` varchar(128) DEFAULT NULL COMMENT '????',
  `weibosmallNumId` varchar(64) DEFAULT NULL COMMENT '????Id',
  `smallNum` varchar(32) DEFAULT NULL COMMENT '??',
  `smallWeiboName` varchar(128) DEFAULT NULL COMMENT '??????',
  `smallNumStatus` varchar(2) DEFAULT NULL COMMENT '?????',
  `cookieId` varchar(128) DEFAULT NULL COMMENT 'cookie??',
  `createTime` datetime DEFAULT NULL COMMENT '????',
  `createUser` varchar(20) DEFAULT NULL COMMENT '???',
  PRIMARY KEY (`smallNumPK`),
  UNIQUE KEY `Index_weibosmallNumId` (`weibosmallNumId`),
  KEY `FK_Reference_6` (`fansOrgInfoNum`),
  KEY `FK_Reference_9` (`customerNum`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`fansOrgInfoNum`) REFERENCES `fansorginfo` (`fansOrgInfoNum`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`customerNum`) REFERENCES `customer` (`customerNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?????????';

-- ----------------------------
-- Records of userssmallnum
-- ----------------------------
INSERT INTO `userssmallnum` VALUES ('1', 'C0825231708734008524', '1', '1231', '123', '123', '123', '1', '123123', '2015-12-12 00:00:00', null);
INSERT INTO `userssmallnum` VALUES ('12', 'C0825231708734008524', '1', '234234', '35455', '2345345', '34545', '1', '234234', '2015-12-12 00:00:00', null);
INSERT INTO `userssmallnum` VALUES ('2', 'C0825231708734008524', '1', '1232', '12323423', '234234', '234234', '1', '23234', '2018-12-12 00:00:00', null);
