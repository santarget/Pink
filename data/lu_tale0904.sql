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

 Date: 04/09/2018 00:02:41
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
-- Records of addmoneyinfo
-- ----------------------------
INSERT INTO `addmoneyinfo` VALUES ('A0902104947223013451', 'C0902100924280005699', '123123122223231', 123.00, 1230, NULL, '2018-09-02 10:49:47', 'APP');
INSERT INTO `addmoneyinfo` VALUES ('A0902163056750016473', 'C0902100924280005699', 'T0902163045744005092', 12.68, 123, '服务端充值测试', '2018-09-02 16:30:57', 'APP');
INSERT INTO `addmoneyinfo` VALUES ('A0902182135227013418', 'C0902174600152008895', 'T0902182135221005022', 123.00, 1230, '服务端充值测试', '2018-09-02 18:21:35', 'SERVER');

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
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('C0902100924280005699', '2', 'weiboidid1', '111111111', '微博名称1', '1', '1', '', '2018-09-02 10:09:24', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173729565001224', '1', 'weiboidid21', '2222222221', '微博名称21', '1', '0', NULL, '2018-09-02 17:37:30', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173743532019989', '1', 'weiboidid22', '2222222222', '微博名称22', '1', '0', NULL, '2018-09-02 17:37:44', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173754637023818', '1', 'weiboidid23', '2222222223', '微博名称23', '1', '0', NULL, '2018-09-02 17:37:55', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173801305036083', '1', 'weiboidid24', '2222222224', '微博名称24', '1', '0', NULL, '2018-09-02 17:38:01', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173808413041905', '1', 'weiboidid25', '2222222225', '微博名称25', '1', '0', NULL, '2018-09-02 17:38:08', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173820400537325', '1', 'weiboidid26', '2222222226', '微博名称26', '1', '0', NULL, '2018-09-02 17:38:20', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173827170067399', '1', 'weiboidid27', '2222222227', '微博名称27', '1', '0', NULL, '2018-09-02 17:38:27', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173834240079090', '1', 'weiboidid28', '2222222228', '微博名称28', '1', '0', NULL, '2018-09-02 17:38:34', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173841759080681', '1', 'weiboidid29', '2222222229', '微博名称29', '1', '0', NULL, '2018-09-02 17:38:42', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173855602095476', '2', 'weiboidid19', '1111111119', '微博名称19', '1', '0', NULL, '2018-09-02 17:38:56', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173906370104799', '2', 'weiboidid18', '1111111118', '微博名称18', '1', '0', NULL, '2018-09-02 17:39:06', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173915415119872', '2', 'weiboidid17', '1111111117', '微博名称17', '1', '0', NULL, '2018-09-02 17:39:15', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173923841265466', '2', 'weiboidid16', '1111111116', '微博名称16', '1', '0', NULL, '2018-09-02 17:39:23', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173930444135651', '2', 'weiboidid15', '1111111115', '微博名称15', '1', '0', NULL, '2018-09-02 17:39:30', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173939595146845', '2', 'weiboidid14', '1111111114', '微博名称14', '1', '0', NULL, '2018-09-02 17:39:40', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173946832153789', '2', 'weiboidid13', '1111111113', '微博名称13', '1', '0', NULL, '2018-09-02 17:39:47', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902173955862163076', '2', 'weiboidid12', '1111111112', '微博名称12', '1', '0', NULL, '2018-09-02 17:39:56', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902174004451760718', '2', 'weiboidid11', '1111111111', '微博名称11', '1', '0', NULL, '2018-09-02 17:40:04', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902174013665189479', '2', 'weiboidid10', '1111111110', '微博名称10', '1', '0', NULL, '2018-09-02 17:40:14', 'APP', NULL);
INSERT INTO `customer` VALUES ('C0902174600152008895', '2', 'weiboidid90', '1111111190', '微博名称90', '1', '1', '', '2018-09-02 17:46:00', 'APP', NULL);

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
-- Records of customergroupinfo
-- ----------------------------
INSERT INTO `customergroupinfo` VALUES ('G0902124723469015326', 'C0902100924280005699', '修改后的小组名称', '2018-09-02 12:47:23', 'APP');
INSERT INTO `customergroupinfo` VALUES ('G0902210717625009098', 'C0902100924280005699', '小组名称1', '2018-09-02 21:07:18', 'APP');

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
INSERT INTO `productinfo` VALUES ('1', '按次产品1名称', 12.00, '按次产品1名称的描述11', '0', '0', '2018-09-02 09:10:04', 'SERVER');
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
-- Records of spendbeaninfo
-- ----------------------------
INSERT INTO `spendbeaninfo` VALUES ('S0902111244282008397', 'C0902100924280005699', '1231231222312231', 15, '2', '订购包月产品：包月产品2名称', '2018-09-02 11:12:44', 'APP');
INSERT INTO `spendbeaninfo` VALUES ('S0902111407595024455', 'C0902100924280005699', '12312312223231', 1, '1', '消费说明信息：例如使用的是那个按次产品', '2018-09-02 11:14:08', 'APP');
INSERT INTO `spendbeaninfo` VALUES ('S0902213638837008574', 'C0902174600152008895', '12312323231', 1, '1', '消费说明信息：例如使用的是那个按次产品', '2018-09-02 21:36:39', 'APP');
INSERT INTO `spendbeaninfo` VALUES ('S0902215117890090302', 'C0902174600152008895', '12312322223231', 1, '1', '消费说明信息：例如使用的是那个按次产品', '2018-09-02 21:51:17', 'APP');
INSERT INTO `spendbeaninfo` VALUES ('S0902215554809016223', 'C0902174600152008895', '1231232223231', 1, '1', '消费说明信息：例如使用的是那个按次产品', '2018-09-02 21:55:55', 'APP');

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
) ENGINE = InnoDB AUTO_INCREMENT = 208 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_logs
-- ----------------------------
INSERT INTO `t_logs` VALUES (176, '登录后台', NULL, 1, '127.0.0.1', 1535850572);
INSERT INTO `t_logs` VALUES (177, '登录后台', NULL, 1, '127.0.0.1', 1535854038);
INSERT INTO `t_logs` VALUES (178, '登录后台', NULL, 1, '127.0.0.1', 1535856132);
INSERT INTO `t_logs` VALUES (179, '登录后台', NULL, 1, '127.0.0.1', 1535857946);
INSERT INTO `t_logs` VALUES (180, '登录后台', NULL, 1, '127.0.0.1', 1535858394);
INSERT INTO `t_logs` VALUES (181, '登录后台', NULL, 1, '127.0.0.1', 1535861317);
INSERT INTO `t_logs` VALUES (182, '登录后台', NULL, 1, '127.0.0.1', 1535863445);
INSERT INTO `t_logs` VALUES (183, '登录后台', NULL, 1, '127.0.0.1', 1535863603);
INSERT INTO `t_logs` VALUES (184, '登录后台', NULL, 1, '127.0.0.1', 1535865303);
INSERT INTO `t_logs` VALUES (185, '登录后台', NULL, 1, '127.0.0.1', 1535865840);
INSERT INTO `t_logs` VALUES (186, '登录后台', NULL, 1, '127.0.0.1', 1535867612);
INSERT INTO `t_logs` VALUES (187, '登录后台', NULL, 1, '127.0.0.1', 1535873191);
INSERT INTO `t_logs` VALUES (188, '登录后台', NULL, 1, '127.0.0.1', 1535875651);
INSERT INTO `t_logs` VALUES (189, '登录后台', NULL, 1, '127.0.0.1', 1535876121);
INSERT INTO `t_logs` VALUES (190, '登录后台', NULL, 1, '127.0.0.1', 1535876843);
INSERT INTO `t_logs` VALUES (191, '登录后台', NULL, 1, '127.0.0.1', 1535876982);
INSERT INTO `t_logs` VALUES (192, '登录后台', NULL, 1, '127.0.0.1', 1535877394);
INSERT INTO `t_logs` VALUES (193, '登录后台', NULL, 1, '127.0.0.1', 1535880167);
INSERT INTO `t_logs` VALUES (194, '登录后台', NULL, 1, '127.0.0.1', 1535880936);
INSERT INTO `t_logs` VALUES (195, '登录后台', NULL, 1, '127.0.0.1', 1535881538);
INSERT INTO `t_logs` VALUES (196, '登录后台', NULL, 1, '127.0.0.1', 1535881973);
INSERT INTO `t_logs` VALUES (197, '登录后台', NULL, 1, '127.0.0.1', 1535882243);
INSERT INTO `t_logs` VALUES (198, '登录后台', NULL, 1, '127.0.0.1', 1535887061);
INSERT INTO `t_logs` VALUES (199, '登录后台', NULL, 1, '127.0.0.1', 1535888789);
INSERT INTO `t_logs` VALUES (200, '登录后台', NULL, 1, '127.0.0.1', 1535889255);
INSERT INTO `t_logs` VALUES (201, '登录后台', NULL, 1, '127.0.0.1', 1535889713);
INSERT INTO `t_logs` VALUES (202, '登录后台', NULL, 1, '127.0.0.1', 1535890385);
INSERT INTO `t_logs` VALUES (203, '登录后台', NULL, 1, '127.0.0.1', 1535891408);
INSERT INTO `t_logs` VALUES (204, '登录后台', NULL, 1, '127.0.0.1', 1535893569);
INSERT INTO `t_logs` VALUES (205, '登录后台', NULL, 1, '127.0.0.1', 1535894933);
INSERT INTO `t_logs` VALUES (206, '登录后台', NULL, 1, '127.0.0.1', 1535895256);
INSERT INTO `t_logs` VALUES (207, '登录后台', NULL, 1, '127.0.0.1', 1535899322);

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
-- Records of useroderinfo
-- ----------------------------
INSERT INTO `useroderinfo` VALUES ('U0902105728758003013', '1', 'C0902100924280005699', '12312312223231', 'weiboidid1', NULL, NULL, '1', '2018-09-02 10:57:29', 'APP');
INSERT INTO `useroderinfo` VALUES ('U0902111244288015927', '2', 'C0902100924280005699', '1231231222312231', 'weiboidid1', NULL, '2018-10-02 11:12:44', '1', '2018-09-02 11:12:44', 'APP');

-- ----------------------------
-- Table structure for userssmallnum
-- ----------------------------
DROP TABLE IF EXISTS `userssmallnum`;
CREATE TABLE `userssmallnum`  (
  `smallNumPK` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '??',
  `customerNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `fansOrgInfoNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
  `customerGroupNum` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usepwd` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '????',
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
-- Records of userssmallnum
-- ----------------------------
INSERT INTO `userssmallnum` VALUES ('S0902104853134008187', 'C0902100924280005699', '1', 'G0902124723469015326', '462ee09d44fcf6be36c081c82cf55ef3', 'weiboidid22', '1230', 'weibosmal0', '0', '240C6D0A2F954A7E833D10FF83A52033', '2018-09-02 10:48:53', NULL);

-- ----------------------------
-- View structure for full_customer_view
-- ----------------------------
DROP VIEW IF EXISTS `full_customer_view`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `full_customer_view` AS select `customer`.`customerNum` AS `customerNum`,`customer`.`fansOrgInfoNum` AS `fansOrgInfoNum`,`fansorginfo`.`fansOrgInfoName` AS `fansOrgInfoName`,'' AS `customerGroupNum`,'' AS `customerGroupName`,`customer`.`weiboId` AS `weiboId`,`customer`.`weiboNum` AS `weiboNum`,`customer`.`weiboName` AS `weiboName`,`customer`.`customerStatus` AS `status`,1 AS `isMainUser` from (`customer` left join `fansorginfo` on(((`customer`.`fansOrgInfoNum` = `fansorginfo`.`fansOrgInfoNum`) and (`fansorginfo`.`fansOrgInfoState` = '1')))) union select `userssmallnum`.`customerNum` AS `customerNum`,`userssmallnum`.`fansOrgInfoNum` AS `fansOrgInfoNum`,`fansorginfo`.`fansOrgInfoName` AS `fansOrgInfoName`,`userssmallnum`.`customerGroupNum` AS `customerGroupNum`,`customergroupinfo`.`customerGroupName` AS `customerGroupName`,`userssmallnum`.`weibosmallNumId` AS `weiboId`,`userssmallnum`.`smallWeiboNum` AS `weiboNum`,`userssmallnum`.`smallWeiboName` AS `weiboName`,`userssmallnum`.`smallNumStatus` AS `Status`,0 AS `isMainUser` from ((`userssmallnum` left join `fansorginfo` on(((`userssmallnum`.`fansOrgInfoNum` = `fansorginfo`.`fansOrgInfoNum`) and (`fansorginfo`.`fansOrgInfoState` = '1')))) left join `customergroupinfo` on((`customergroupinfo`.`customerGroupNum` = `userssmallnum`.`customerGroupNum`)));

SET FOREIGN_KEY_CHECKS = 1;
