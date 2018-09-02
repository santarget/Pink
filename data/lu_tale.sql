/*
 Navicat Premium Data Transfer

 Source Server         : MySQL5.7_localhost
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : 127.0.0.1:3306
 Source Schema         : lu_tale

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 02/09/2018 10:04:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for addmoneyinfo
-- ----------------------------
DROP TABLE IF EXISTS `addmoneyinfo`;
CREATE TABLE `addmoneyinfo`  (
  `addMoneyInfoPK` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '????????',
  `customerNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `transactionId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '????',
  `addMountVal` decimal(15, 2) NOT NULL COMMENT '?????',
  `beanNum` bigint(12) NULL DEFAULT NULL COMMENT '???????',
  `addMoneyDesc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '????',
  `createUser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '???',
  PRIMARY KEY (`addMoneyInfoPK`) USING BTREE,
  UNIQUE INDEX `Index_transactionId`(`transactionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for buybeaninfo
-- ----------------------------
DROP TABLE IF EXISTS `buybeaninfo`;
CREATE TABLE `buybeaninfo`  (
  `buyBeanInfoPK` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '购买花豆流水信息主键',
  `customerNum` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员编号',
  `buyAmount` decimal(15, 2) NULL DEFAULT NULL COMMENT '购买的金额',
  `buyBeanNum` bigint(20) NULL DEFAULT NULL COMMENT '获取的花豆数量',
  `buyTime` datetime(0) NULL DEFAULT NULL COMMENT '购买时间',
  `createUser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`buyBeanInfoPK`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购买花豆流水信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customerNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '????',
  `fansOrgInfoNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `weiboId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??Id',
  `weiboNum` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `weiboName` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `customerStatus` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `specialFlag` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `memo` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '????',
  `createUser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '???',
  `sessionId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`customerNum`) USING BTREE,
  UNIQUE INDEX `Index_weiboId`(`weiboId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '????' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for customergroupinfo
-- ----------------------------
DROP TABLE IF EXISTS `customergroupinfo`;
CREATE TABLE `customergroupinfo`  (
  `customerGroupNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户小号分组编号',
  `customerNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员编号',
  `customerGroupName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户小号分组名称',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `createUser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`customerGroupNum`) USING BTREE,
  INDEX `FK_Reference_10`(`customerNum`) USING BTREE,
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`customerNum`) REFERENCES `customer` (`customerNum`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户小号分组信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for fansorginfo
-- ----------------------------
DROP TABLE IF EXISTS `fansorginfo`;
CREATE TABLE `fansorginfo`  (
  `fansOrgInfoNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '????',
  `fansOrgInfoName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
  `fansOrgInfotDesc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
  `fansOrgInfoState` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '????',
  `createUser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '???',
  PRIMARY KEY (`fansOrgInfoNum`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '??????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fansorginfo
-- ----------------------------
INSERT INTO `fansorginfo` VALUES ('1', '粉丝组织1', '粉丝组织1的描述', '1', '2018-09-02 09:11:01', 'SERVER');
INSERT INTO `fansorginfo` VALUES ('2', '粉丝组织2', '粉丝组织2的描述', '1', '2018-09-02 09:11:21', 'SERVER');

-- ----------------------------
-- Table structure for productinfo
-- ----------------------------
DROP TABLE IF EXISTS `productinfo`;
CREATE TABLE `productinfo`  (
  `productNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '????',
  `productName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `price` decimal(15, 2) NULL DEFAULT NULL COMMENT '??',
  `productDesc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `productType` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `productState` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '????',
  `createUser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '???',
  PRIMARY KEY (`productNum`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '????' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of productinfo
-- ----------------------------
INSERT INTO `productinfo` VALUES ('1', '按次产品1名称', 12.00, '按次产品1名称的描述11', '0', '1', '2018-09-02 09:10:04', 'SERVER');
INSERT INTO `productinfo` VALUES ('2', '包月产品2名称', 15.00, '包月产品2名称的描述', '1', '1', '2018-09-02 09:10:33', 'SERVER');

-- ----------------------------
-- Table structure for spendbeaninfo
-- ----------------------------
DROP TABLE IF EXISTS `spendbeaninfo`;
CREATE TABLE `spendbeaninfo`  (
  `spendBeanInfoPK` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '购买花豆流水信息主键',
  `customerNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会员编号',
  `transactionId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易编号',
  `spendBeanNum` bigint(20) NOT NULL COMMENT '消费的花豆数量',
  `productNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品编号',
  `spendDesc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消费说明',
  `spendTime` datetime(0) NULL DEFAULT NULL COMMENT '消费时间',
  `createUser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`spendBeanInfoPK`) USING BTREE,
  INDEX `Index_transactionId`(`transactionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购买花豆流水信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_logs
-- ----------------------------
DROP TABLE IF EXISTS `t_logs`;
CREATE TABLE `t_logs`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '????',
  `action` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??',
  `data` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??',
  `authorId` int(10) NULL DEFAULT NULL COMMENT '????',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip??',
  `created` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 177 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_logs
-- ----------------------------
INSERT INTO `t_logs` VALUES (176, '登录后台', NULL, 1, '127.0.0.1', 1535850572);

-- ----------------------------
-- Table structure for t_options
-- ----------------------------
DROP TABLE IF EXISTS `t_options`;
CREATE TABLE `t_options`  (
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `value` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_relationships
-- ----------------------------
DROP TABLE IF EXISTS `t_relationships`;
CREATE TABLE `t_relationships`  (
  `cid` int(10) UNSIGNED NOT NULL,
  `mid` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`cid`, `mid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users`  (
  `uid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `homeUrl` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `screenName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created` int(10) UNSIGNED NULL DEFAULT 0,
  `activated` int(10) UNSIGNED NULL DEFAULT 0,
  `logged` int(10) UNSIGNED NULL DEFAULT 0,
  `groupName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'visitor',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `name`(`username`) USING BTREE,
  UNIQUE INDEX `mail`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES (1, 'admin', 'a66abb5684c45962d887564f08346e8d', '1034683568@qq.com', NULL, 'admin', 1490756162, 0, 0, 'visitor');

-- ----------------------------
-- Table structure for useroderinfo
-- ----------------------------
DROP TABLE IF EXISTS `useroderinfo`;
CREATE TABLE `useroderinfo`  (
  `userOderInfoPK` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户订购关系主键',
  `productNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品编号',
  `customerNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员编号',
  `transactionId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易编号',
  `weiboId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微博Id',
  `weibosmallNumId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微博小号Id',
  `deadlineTime` datetime(0) NULL DEFAULT NULL COMMENT '产品截止时间',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `createUser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`userOderInfoPK`) USING BTREE,
  UNIQUE INDEX `IDX_transactionId`(`transactionId`) USING BTREE,
  INDEX `FK_Reference_7`(`productNum`) USING BTREE,
  INDEX `FK_Reference_8`(`customerNum`) USING BTREE,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`productNum`) REFERENCES `productinfo` (`productNum`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`customerNum`) REFERENCES `customer` (`customerNum`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户订购关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for userssmallnum
-- ----------------------------
DROP TABLE IF EXISTS `userssmallnum`;
CREATE TABLE `userssmallnum`  (
  `smallNumPK` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '??',
  `customerNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `fansOrgInfoNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `customerGroupNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usepwd` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `weibosmallNumId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????Id',
  `smallWeiboNum` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??',
  `smallWeiboName` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??????',
  `smallNumStatus` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?????',
  `cookieId` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cookie??',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '????',
  `createUser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '???',
  PRIMARY KEY (`smallNumPK`) USING BTREE,
  UNIQUE INDEX `Index_weibosmallNumId`(`weibosmallNumId`) USING BTREE,
  INDEX `FK_Reference_6`(`fansOrgInfoNum`) USING BTREE,
  INDEX `FK_Reference_9`(`customerNum`) USING BTREE,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`fansOrgInfoNum`) REFERENCES `fansorginfo` (`fansOrgInfoNum`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`customerNum`) REFERENCES `customer` (`customerNum`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '?????????' ROW_FORMAT = Compact;

-- ----------------------------
-- View structure for full_customer_view
-- ----------------------------
DROP VIEW IF EXISTS `full_customer_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `full_customer_view` AS select `customer`.`customerNum` AS `customerNum`,`customer`.`fansOrgInfoNum` AS `fansOrgInfoNum`,`fansorginfo`.`fansOrgInfoName` AS `fansOrgInfoName`,'' AS `customerGroupNum`,'' AS `customerGroupName`,`customer`.`weiboId` AS `weiboId`,`customer`.`weiboNum` AS `weiboNum`,`customer`.`weiboName` AS `weiboName`,`customer`.`customerStatus` AS `status`,1 AS `isMainUser` from (`customer` left join `fansorginfo` on(((`customer`.`fansOrgInfoNum` = `fansorginfo`.`fansOrgInfoNum`) and (`fansorginfo`.`fansOrgInfoState` = '1')))) union select `userssmallnum`.`customerNum` AS `customerNum`,`userssmallnum`.`fansOrgInfoNum` AS `fansOrgInfoNum`,`fansorginfo`.`fansOrgInfoName` AS `fansOrgInfoName`,`userssmallnum`.`customerGroupNum` AS `customerGroupNum`,`customergroupinfo`.`customerGroupName` AS `customerGroupName`,`userssmallnum`.`weibosmallNumId` AS `weiboId`,`userssmallnum`.`smallWeiboNum` AS `weiboNum`,`userssmallnum`.`smallWeiboName` AS `weiboName`,`userssmallnum`.`smallNumStatus` AS `Status`,0 AS `isMainUser` from ((`userssmallnum` left join `fansorginfo` on(((`userssmallnum`.`fansOrgInfoNum` = `fansorginfo`.`fansOrgInfoNum`) and (`fansorginfo`.`fansOrgInfoState` = '1')))) left join `customergroupinfo` on((`customergroupinfo`.`customerGroupNum` = `userssmallnum`.`customerGroupNum`)));

SET FOREIGN_KEY_CHECKS = 1;
