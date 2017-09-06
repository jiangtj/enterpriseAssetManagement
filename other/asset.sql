/*
Navicat MySQL Data Transfer

Source Server         : mysql-mine
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : asset

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-09-06 22:16:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for assets_borrow
-- ----------------------------
DROP TABLE IF EXISTS `assets_borrow`;
CREATE TABLE `assets_borrow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `user_id` bigint(20) NOT NULL COMMENT '租借人id',
  `expect_return_time` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，1：在借，2：已归还',
  `return_time` datetime DEFAULT NULL COMMENT '归还时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '资产名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ab@user_id` (`user_id`) USING BTREE,
  KEY `ab@uuid` (`uuid`) USING BTREE,
  CONSTRAINT `assets_borrow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `assets_borrow_ibfk_2` FOREIGN KEY (`uuid`) REFERENCES `assets_item` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_borrow
-- ----------------------------
INSERT INTO `assets_borrow` VALUES ('1', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', '2017-05-16 00:00:00', '2', '2017-05-03 00:00:00', null, '2017-05-03 20:00:02', '2017-05-03 20:00:22');
INSERT INTO `assets_borrow` VALUES ('2', 'f76ae024-0132-4c98-8339-e26752649bf3', '1', null, '2', '2017-05-03 20:01:57', null, '2017-05-03 20:01:49', '2017-05-03 20:01:56');
INSERT INTO `assets_borrow` VALUES ('3', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', null, '2', '2017-05-03 20:02:31', null, '2017-05-03 20:02:04', '2017-05-03 20:02:30');
INSERT INTO `assets_borrow` VALUES ('4', '917de91c-96c9-49fe-a607-c68e036f71b0', '1', null, '1', null, null, '2017-05-04 23:59:43', '2017-05-04 23:59:43');
INSERT INTO `assets_borrow` VALUES ('5', 'f76ae024-0132-4c98-8339-e26752649bf3', '1', null, '1', null, null, '2017-05-05 00:00:07', '2017-05-05 00:00:07');
INSERT INTO `assets_borrow` VALUES ('6', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', null, '2', '2017-05-05 00:03:03', null, '2017-05-05 00:00:22', '2017-05-05 00:03:02');
INSERT INTO `assets_borrow` VALUES ('7', 'a13c624e-f42a-4aba-b452-c246b10c68be', '1', '2017-09-04 00:00:00', '2', '2017-09-03 23:35:36', null, '2017-09-03 23:31:41', '2017-09-03 23:35:36');
INSERT INTO `assets_borrow` VALUES ('8', 'a13c624e-f42a-4aba-b452-c246b10c68be', '1', '2017-09-04 00:00:00', '2', '2017-09-04 00:00:00', null, '2017-09-04 23:42:18', '2017-09-04 23:42:25');

-- ----------------------------
-- Table structure for assets_item
-- ----------------------------
DROP TABLE IF EXISTS `assets_item`;
CREATE TABLE `assets_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `customs_id` varchar(50) NOT NULL COMMENT '用户提供的资产编码',
  `name` varchar(255) NOT NULL COMMENT '资产名称',
  `price` decimal(10,2) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，2：租借，3：维修，4：报废',
  `assets_type_id` bigint(20) NOT NULL COMMENT '资产类型id',
  `point_id` bigint(20) NOT NULL COMMENT '网点id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique@uuid` (`uuid`) USING BTREE,
  KEY `ai@point_id` (`point_id`) USING BTREE,
  KEY `ai@assets_type_id` (`assets_type_id`) USING BTREE,
  CONSTRAINT `assets_item_ibfk_1` FOREIGN KEY (`assets_type_id`) REFERENCES `assets_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_item
-- ----------------------------
INSERT INTO `assets_item` VALUES ('1', 'fcd2ed78-3f68-4db8-811d-56f47c593c63', 'dca123', 'ds', '12.00', '1', '1', '4', '2017-05-02 20:05:48', '2017-05-05 00:00:49');
INSERT INTO `assets_item` VALUES ('2', 'f0da55e0-8e89-462d-915a-46d66863bf9a', 'z1', '测试物品', '100.00', '1', '4', '4', '2017-05-03 19:56:45', '2017-05-05 00:03:02');
INSERT INTO `assets_item` VALUES ('3', 'f76ae024-0132-4c98-8339-e26752649bf3', 'z2', '测试物品', '100.00', '2', '4', '4', '2017-05-03 19:56:47', '2017-05-05 00:00:07');
INSERT INTO `assets_item` VALUES ('4', '0b87e100-dacd-4fcb-8434-1bfb18a477de', 'z3', '测试物品', '100.00', '1', '4', '4', '2017-05-03 19:56:48', '2017-05-03 19:56:48');
INSERT INTO `assets_item` VALUES ('5', '3a242fab-0395-494f-ba28-837deea9c519', 'z4', '测试物品', '100.00', '1', '4', '4', '2017-05-03 19:56:50', '2017-05-03 19:56:50');
INSERT INTO `assets_item` VALUES ('6', '917de91c-96c9-49fe-a607-c68e036f71b0', 'z5', '测试物品', '100.00', '2', '4', '4', '2017-05-03 19:56:52', '2017-05-04 23:59:43');
INSERT INTO `assets_item` VALUES ('7', '0bd3ed09-9842-45ed-a2fc-3ec7e28bffa6', 'z6', '测试物品', '100.00', '1', '4', '4', '2017-05-03 19:56:54', '2017-05-03 19:56:54');
INSERT INTO `assets_item` VALUES ('8', '1eaa891f-cc02-487d-b5ef-5dbedabca45a', 'z7', '测试物品', '100.00', '4', '4', '4', '2017-05-03 19:56:56', '2017-05-03 20:14:44');
INSERT INTO `assets_item` VALUES ('9', 'a13c624e-f42a-4aba-b452-c246b10c68be', 'z8', '测试物品', '100.00', '1', '4', '4', '2017-05-03 19:56:58', '2017-09-04 23:42:25');

-- ----------------------------
-- Table structure for assets_operation_record
-- ----------------------------
DROP TABLE IF EXISTS `assets_operation_record`;
CREATE TABLE `assets_operation_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `user_id` bigint(20) NOT NULL COMMENT '操作人id',
  `operation_type` tinyint(4) NOT NULL COMMENT '操作类型，1：登记，2：借，3：换，4：丢失，5：报修，6：作废，7：盘点',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_operation_record
-- ----------------------------
INSERT INTO `assets_operation_record` VALUES ('1', 'fcd2ed78-3f68-4db8-811d-56f47c593c63', '1', '1', null, '2017-05-02 20:05:48', '2017-05-02 20:05:48');
INSERT INTO `assets_operation_record` VALUES ('2', 'fcd2ed78-3f68-4db8-811d-56f47c593c63', '1', '2', '成功', '2017-05-02 22:45:00', '2017-05-02 22:45:00');
INSERT INTO `assets_operation_record` VALUES ('3', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', '1', '成功', '2017-05-03 19:56:45', '2017-05-03 19:56:45');
INSERT INTO `assets_operation_record` VALUES ('4', 'f76ae024-0132-4c98-8339-e26752649bf3', '1', '1', '成功', '2017-05-03 19:56:47', '2017-05-03 19:56:47');
INSERT INTO `assets_operation_record` VALUES ('5', '0b87e100-dacd-4fcb-8434-1bfb18a477de', '1', '1', '成功', '2017-05-03 19:56:48', '2017-05-03 19:56:48');
INSERT INTO `assets_operation_record` VALUES ('6', '3a242fab-0395-494f-ba28-837deea9c519', '1', '1', '成功', '2017-05-03 19:56:50', '2017-05-03 19:56:50');
INSERT INTO `assets_operation_record` VALUES ('7', '917de91c-96c9-49fe-a607-c68e036f71b0', '1', '1', '成功', '2017-05-03 19:56:52', '2017-05-03 19:56:52');
INSERT INTO `assets_operation_record` VALUES ('8', '0bd3ed09-9842-45ed-a2fc-3ec7e28bffa6', '1', '1', '成功', '2017-05-03 19:56:54', '2017-05-03 19:56:54');
INSERT INTO `assets_operation_record` VALUES ('9', '1eaa891f-cc02-487d-b5ef-5dbedabca45a', '1', '1', '成功', '2017-05-03 19:56:56', '2017-05-03 19:56:56');
INSERT INTO `assets_operation_record` VALUES ('10', 'a13c624e-f42a-4aba-b452-c246b10c68be', '1', '1', '成功', '2017-05-03 19:56:58', '2017-05-03 19:56:58');
INSERT INTO `assets_operation_record` VALUES ('11', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', '2', '成功,租借人：1', '2017-05-03 20:00:02', '2017-05-03 20:00:02');
INSERT INTO `assets_operation_record` VALUES ('12', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', '3', '成功,归还人：1', '2017-05-03 20:00:22', '2017-05-03 20:00:22');
INSERT INTO `assets_operation_record` VALUES ('13', 'f76ae024-0132-4c98-8339-e26752649bf3', '1', '2', '成功,租借人：1', '2017-05-03 20:01:49', '2017-05-03 20:01:49');
INSERT INTO `assets_operation_record` VALUES ('14', 'f76ae024-0132-4c98-8339-e26752649bf3', '1', '3', '成功,归还人：1', '2017-05-03 20:01:56', '2017-05-03 20:01:56');
INSERT INTO `assets_operation_record` VALUES ('15', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', '2', '成功,租借人：1', '2017-05-03 20:02:04', '2017-05-03 20:02:04');
INSERT INTO `assets_operation_record` VALUES ('16', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', '3', '成功,归还人：1', '2017-05-03 20:02:30', '2017-05-03 20:02:30');
INSERT INTO `assets_operation_record` VALUES ('17', '1eaa891f-cc02-487d-b5ef-5dbedabca45a', '1', '6', '成功,退货', '2017-05-03 20:14:44', '2017-05-03 20:14:44');
INSERT INTO `assets_operation_record` VALUES ('18', '0bd3ed09-9842-45ed-a2fc-3ec7e28bffa6', '1', '7', '成功', '2017-05-04 20:53:14', '2017-05-04 20:53:14');
INSERT INTO `assets_operation_record` VALUES ('19', '917de91c-96c9-49fe-a607-c68e036f71b0', '1', '2', '成功,租借人：1', '2017-05-04 23:59:43', '2017-05-04 23:59:43');
INSERT INTO `assets_operation_record` VALUES ('20', 'f76ae024-0132-4c98-8339-e26752649bf3', '1', '2', '成功,租借人：1', '2017-05-05 00:00:07', '2017-05-05 00:00:07');
INSERT INTO `assets_operation_record` VALUES ('21', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', '2', '成功,租借人：1', '2017-05-05 00:00:22', '2017-05-05 00:00:22');
INSERT INTO `assets_operation_record` VALUES ('22', 'fcd2ed78-3f68-4db8-811d-56f47c593c63', '1', '3', '成功,归还人：1', '2017-05-05 00:00:49', '2017-05-05 00:00:49');
INSERT INTO `assets_operation_record` VALUES ('23', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', '3', '成功,归还人：1', '2017-05-05 00:03:02', '2017-05-05 00:03:02');
INSERT INTO `assets_operation_record` VALUES ('24', 'a13c624e-f42a-4aba-b452-c246b10c68be', '1', '2', '成功,租借人：1', '2017-09-03 23:31:41', '2017-09-03 23:31:41');
INSERT INTO `assets_operation_record` VALUES ('25', 'a13c624e-f42a-4aba-b452-c246b10c68be', '1', '3', '操作成功,归还人：1', '2017-09-03 23:35:36', '2017-09-03 23:35:36');
INSERT INTO `assets_operation_record` VALUES ('26', 'a13c624e-f42a-4aba-b452-c246b10c68be', '1', '2', '操作成功,租借人：cfs', '2017-09-03 23:39:20', '2017-09-03 23:39:20');
INSERT INTO `assets_operation_record` VALUES ('27', 'a13c624e-f42a-4aba-b452-c246b10c68be', '1', '3', '操作成功,归还人：cds', '2017-09-03 23:40:52', '2017-09-03 23:40:52');
INSERT INTO `assets_operation_record` VALUES ('28', 'a13c624e-f42a-4aba-b452-c246b10c68be', '1', '2', '操作成功,租借人：fdv', '2017-09-03 23:41:49', '2017-09-03 23:41:49');
INSERT INTO `assets_operation_record` VALUES ('29', 'a13c624e-f42a-4aba-b452-c246b10c68be', '1', '3', '操作成功,归还人：fdv', '2017-09-03 23:42:29', '2017-09-03 23:42:29');
INSERT INTO `assets_operation_record` VALUES ('30', 'dccd1fa2-23e5-4c85-bc0f-f279d236e228', '1', '1', '添加成功', '2017-09-04 23:15:05', '2017-09-04 23:15:05');
INSERT INTO `assets_operation_record` VALUES ('31', '46c4e8b9-c5d5-4e87-a6ef-30109f787937', '1', '1', '添加成功', '2017-09-04 23:15:37', '2017-09-04 23:15:37');
INSERT INTO `assets_operation_record` VALUES ('32', '46c4e8b9-c5d5-4e87-a6ef-30109f787937', '1', '6', '成功,sf', '2017-09-04 23:27:50', '2017-09-04 23:27:50');
INSERT INTO `assets_operation_record` VALUES ('33', '3694d2ec-2e76-4e90-8bee-7fd4dcb18a3f', '1', '1', '添加成功', '2017-09-04 23:41:23', '2017-09-04 23:41:23');
INSERT INTO `assets_operation_record` VALUES ('34', '3694d2ec-2e76-4e90-8bee-7fd4dcb18a3f', '1', '5', '成功,de', '2017-09-04 23:41:47', '2017-09-04 23:41:47');
INSERT INTO `assets_operation_record` VALUES ('35', 'a13c624e-f42a-4aba-b452-c246b10c68be', '1', '2', '操作成功,租借人：1', '2017-09-04 23:42:18', '2017-09-04 23:42:18');
INSERT INTO `assets_operation_record` VALUES ('36', 'a13c624e-f42a-4aba-b452-c246b10c68be', '1', '3', '操作成功,归还人：1', '2017-09-04 23:42:25', '2017-09-04 23:42:25');
INSERT INTO `assets_operation_record` VALUES ('37', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', '7', '成功', '2017-09-05 00:00:45', '2017-09-05 00:00:45');

-- ----------------------------
-- Table structure for assets_stock_take
-- ----------------------------
DROP TABLE IF EXISTS `assets_stock_take`;
CREATE TABLE `assets_stock_take` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '盘点名称',
  `user_id` bigint(20) NOT NULL COMMENT '盘点负责人',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，1：盘点中，2：盘点完成',
  `end_time` datetime DEFAULT NULL COMMENT '盘点结束时间',
  `all_amount` int(8) DEFAULT '0' COMMENT '盘点数目',
  `handling_amount` int(8) DEFAULT '0' COMMENT '待处理数目',
  `normal_amount` int(8) DEFAULT '0' COMMENT '正常数目',
  `abnormal_amount` int(8) DEFAULT '0' COMMENT '异常数目',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_stock_take
-- ----------------------------
INSERT INTO `assets_stock_take` VALUES ('1', '全部盘点', '1', '2', '2017-05-08 11:31:01', '9', '0', '1', '8', '2017-05-04 20:42:03', '2017-05-08 11:31:00');
INSERT INTO `assets_stock_take` VALUES ('5', '计算机类盘点1', '1', '1', null, '1', '1', '0', '0', '2017-05-04 20:47:23', '2017-05-04 22:27:58');
INSERT INTO `assets_stock_take` VALUES ('6', '计算机类盘点2', '1', '1', null, '9', '0', '0', '0', '2017-05-04 20:47:39', '2017-05-04 22:25:41');
INSERT INTO `assets_stock_take` VALUES ('7', 'xx3', '1', '2', '2017-09-05 00:01:23', '9', '0', '1', '8', '2017-05-04 20:50:53', '2017-09-05 00:01:23');
INSERT INTO `assets_stock_take` VALUES ('8', 'xx2', '1', '2', '2017-05-08 11:30:47', '9', '0', '0', '9', '2017-05-04 20:51:08', '2017-05-08 11:30:47');

-- ----------------------------
-- Table structure for assets_stock_take_item
-- ----------------------------
DROP TABLE IF EXISTS `assets_stock_take_item`;
CREATE TABLE `assets_stock_take_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `stock_take_id` bigint(36) NOT NULL COMMENT '审核记录id',
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `customs_id` varchar(50) NOT NULL COMMENT '用户提供的资产编码',
  `name` varchar(255) NOT NULL COMMENT '资产名称',
  `price` decimal(10,2) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，1：待盘点，2：正常，3：异常',
  `assets_type_id` bigint(20) NOT NULL COMMENT '资产类型id',
  `point_id` bigint(20) NOT NULL COMMENT '网点id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique@stock_take_id, uuid` (`stock_take_id`,`uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_stock_take_item
-- ----------------------------
INSERT INTO `assets_stock_take_item` VALUES ('32', '1', 'fcd2ed78-3f68-4db8-811d-56f47c593c63', 'dca123', 'ds', '12.00', '3', '1', '4', '2017-05-04 20:42:03', '2017-05-04 23:55:56');
INSERT INTO `assets_stock_take_item` VALUES ('33', '1', 'f0da55e0-8e89-462d-915a-46d66863bf9a', 'z1', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:42:03', '2017-05-08 11:31:00');
INSERT INTO `assets_stock_take_item` VALUES ('34', '1', 'f76ae024-0132-4c98-8339-e26752649bf3', 'z2', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:42:03', '2017-05-08 11:31:00');
INSERT INTO `assets_stock_take_item` VALUES ('35', '1', '0b87e100-dacd-4fcb-8434-1bfb18a477de', 'z3', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:42:03', '2017-05-08 11:31:00');
INSERT INTO `assets_stock_take_item` VALUES ('36', '1', '3a242fab-0395-494f-ba28-837deea9c519', 'z4', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:42:03', '2017-05-08 11:31:00');
INSERT INTO `assets_stock_take_item` VALUES ('37', '1', '917de91c-96c9-49fe-a607-c68e036f71b0', 'z5', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:42:03', '2017-05-08 11:31:00');
INSERT INTO `assets_stock_take_item` VALUES ('38', '1', '0bd3ed09-9842-45ed-a2fc-3ec7e28bffa6', 'z6', '测试物品', '100.00', '2', '4', '4', '2017-05-04 20:42:03', '2017-05-04 20:53:14');
INSERT INTO `assets_stock_take_item` VALUES ('39', '1', '1eaa891f-cc02-487d-b5ef-5dbedabca45a', 'z7', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:42:03', '2017-05-08 11:31:00');
INSERT INTO `assets_stock_take_item` VALUES ('40', '1', 'a13c624e-f42a-4aba-b452-c246b10c68be', 'z8', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:42:03', '2017-05-08 11:31:00');
INSERT INTO `assets_stock_take_item` VALUES ('47', '5', 'fcd2ed78-3f68-4db8-811d-56f47c593c63', 'dca123', 'ds', '12.00', '1', '1', '4', '2017-05-04 20:47:23', '2017-05-04 20:47:23');
INSERT INTO `assets_stock_take_item` VALUES ('48', '7', 'fcd2ed78-3f68-4db8-811d-56f47c593c63', 'dca123', 'ds', '12.00', '3', '1', '4', '2017-05-04 20:50:53', '2017-09-04 23:59:46');
INSERT INTO `assets_stock_take_item` VALUES ('49', '7', 'f0da55e0-8e89-462d-915a-46d66863bf9a', 'z1', '测试物品', '100.00', '2', '4', '4', '2017-05-04 20:50:53', '2017-09-05 00:00:45');
INSERT INTO `assets_stock_take_item` VALUES ('50', '7', 'f76ae024-0132-4c98-8339-e26752649bf3', 'z2', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:50:53', '2017-09-05 00:01:13');
INSERT INTO `assets_stock_take_item` VALUES ('51', '7', '0b87e100-dacd-4fcb-8434-1bfb18a477de', 'z3', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:50:53', '2017-09-05 00:01:23');
INSERT INTO `assets_stock_take_item` VALUES ('52', '7', '3a242fab-0395-494f-ba28-837deea9c519', 'z4', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:50:53', '2017-09-05 00:01:23');
INSERT INTO `assets_stock_take_item` VALUES ('53', '7', '917de91c-96c9-49fe-a607-c68e036f71b0', 'z5', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:50:53', '2017-09-05 00:01:23');
INSERT INTO `assets_stock_take_item` VALUES ('54', '7', '0bd3ed09-9842-45ed-a2fc-3ec7e28bffa6', 'z6', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:50:53', '2017-09-05 00:01:23');
INSERT INTO `assets_stock_take_item` VALUES ('55', '7', '1eaa891f-cc02-487d-b5ef-5dbedabca45a', 'z7', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:50:53', '2017-09-05 00:01:23');
INSERT INTO `assets_stock_take_item` VALUES ('56', '7', 'a13c624e-f42a-4aba-b452-c246b10c68be', 'z8', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:50:53', '2017-09-05 00:01:23');
INSERT INTO `assets_stock_take_item` VALUES ('63', '8', 'fcd2ed78-3f68-4db8-811d-56f47c593c63', 'dca123', 'ds', '12.00', '3', '1', '4', '2017-05-04 20:51:08', '2017-05-08 11:29:32');
INSERT INTO `assets_stock_take_item` VALUES ('64', '8', 'f0da55e0-8e89-462d-915a-46d66863bf9a', 'z1', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:51:08', '2017-05-08 11:29:32');
INSERT INTO `assets_stock_take_item` VALUES ('65', '8', 'f76ae024-0132-4c98-8339-e26752649bf3', 'z2', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:51:08', '2017-05-08 11:29:32');
INSERT INTO `assets_stock_take_item` VALUES ('66', '8', '0b87e100-dacd-4fcb-8434-1bfb18a477de', 'z3', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:51:08', '2017-05-08 11:29:32');
INSERT INTO `assets_stock_take_item` VALUES ('67', '8', '3a242fab-0395-494f-ba28-837deea9c519', 'z4', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:51:08', '2017-05-08 11:29:32');
INSERT INTO `assets_stock_take_item` VALUES ('68', '8', '917de91c-96c9-49fe-a607-c68e036f71b0', 'z5', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:51:08', '2017-05-08 11:29:32');
INSERT INTO `assets_stock_take_item` VALUES ('69', '8', '0bd3ed09-9842-45ed-a2fc-3ec7e28bffa6', 'z6', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:51:08', '2017-05-08 11:29:32');
INSERT INTO `assets_stock_take_item` VALUES ('70', '8', '1eaa891f-cc02-487d-b5ef-5dbedabca45a', 'z7', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:51:08', '2017-05-08 11:29:32');
INSERT INTO `assets_stock_take_item` VALUES ('71', '8', 'a13c624e-f42a-4aba-b452-c246b10c68be', 'z8', '测试物品', '100.00', '3', '4', '4', '2017-05-04 20:51:08', '2017-05-08 11:29:32');

-- ----------------------------
-- Table structure for assets_type
-- ----------------------------
DROP TABLE IF EXISTS `assets_type`;
CREATE TABLE `assets_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `name` varchar(50) NOT NULL COMMENT '类型名称',
  `level` int(8) NOT NULL COMMENT '级别',
  `pid` bigint(20) NOT NULL COMMENT '父节点id',
  `order` int(8) NOT NULL COMMENT '同一个父节点下面的排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_type
-- ----------------------------
INSERT INTO `assets_type` VALUES ('1', '计算机类', '1', '0', '1', '2017-04-29 19:59:20', '2017-04-29 19:59:20');
INSERT INTO `assets_type` VALUES ('2', '其他', '1', '0', '99', '2017-04-29 20:00:23', '2017-04-29 20:00:23');
INSERT INTO `assets_type` VALUES ('3', '台式机', '2', '1', '1', '2017-04-29 20:01:28', '2017-04-29 20:01:28');
INSERT INTO `assets_type` VALUES ('4', '笔记本', '2', '1', '2', '2017-04-29 20:01:35', '2017-04-29 20:01:35');
INSERT INTO `assets_type` VALUES ('5', '测试机', '3', '3', '1', '2017-07-24 12:56:47', '2017-07-24 12:58:12');

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `code` varchar(50) NOT NULL COMMENT '权限code',
  `name` varchar(250) NOT NULL COMMENT '权限名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique@name` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES ('1', 'sys:user:add', '用户添加', '2017-04-17 22:47:12', '2017-09-05 00:17:00');
INSERT INTO `auth_permission` VALUES ('2', 'sys:user:delete', '用户删除', '2017-04-27 09:38:25', '2017-08-01 13:26:49');
INSERT INTO `auth_permission` VALUES ('3', 'sys:user:update', '用户更新', '2017-04-27 09:39:27', '2017-08-01 13:26:51');
INSERT INTO `auth_permission` VALUES ('4', 'sys:user:getList', '用户列表', '2017-04-27 09:40:46', '2017-08-01 13:26:55');
INSERT INTO `auth_permission` VALUES ('9', 'sys:develop', '开发模式', '2017-04-27 10:07:26', '2017-08-01 13:27:47');
INSERT INTO `auth_permission` VALUES ('20', 'asset:add', '资产添加', '2017-05-05 20:34:15', '2017-08-01 13:29:14');
INSERT INTO `auth_permission` VALUES ('21', 'asset:delete', '资产删除', '2017-05-05 20:34:28', '2017-08-01 13:29:22');
INSERT INTO `auth_permission` VALUES ('22', 'asset:update', '资产更新', '2017-05-05 20:34:37', '2017-08-01 13:29:28');
INSERT INTO `auth_permission` VALUES ('23', 'asset:getList', '资产列表', '2017-05-05 20:34:50', '2017-08-01 13:29:34');
INSERT INTO `auth_permission` VALUES ('24', 'asset:record:getByUuid', '资产记录列表', '2017-05-05 20:35:05', '2017-08-01 13:30:18');
INSERT INTO `auth_permission` VALUES ('25', 'asset:borrow:add', '资产租借', '2017-05-05 20:35:20', '2017-08-01 13:30:49');
INSERT INTO `auth_permission` VALUES ('26', 'asset:borrow:return', '资产归还', '2017-05-05 20:35:40', '2017-08-17 23:03:12');
INSERT INTO `auth_permission` VALUES ('27', 'asset:updateStatus', '资产状态更新', '2017-05-05 20:37:46', '2017-08-01 13:31:49');
INSERT INTO `auth_permission` VALUES ('28', 'sys:assetType:add', '资产类型添加', '2017-05-05 20:38:07', '2017-08-01 13:33:02');
INSERT INTO `auth_permission` VALUES ('29', 'sys:assetType:delete', '资产类型删除', '2017-05-05 20:38:16', '2017-08-01 13:33:11');
INSERT INTO `auth_permission` VALUES ('31', 'sys:assetType:update', '资产类型更新', '2017-05-05 20:38:46', '2017-08-01 13:33:18');
INSERT INTO `auth_permission` VALUES ('32', 'sys:assetType:getList', '资产类型列表', '2017-05-05 20:38:57', '2017-08-01 13:33:26');
INSERT INTO `auth_permission` VALUES ('33', 'sys:assetType:getType', '资产类型xx', '2017-05-05 20:39:06', '2017-08-01 13:33:41');
INSERT INTO `auth_permission` VALUES ('34', 'sys:assetType:getMapByPid', '资产类型xx', '2017-05-05 20:39:18', '2017-08-01 13:33:47');
INSERT INTO `auth_permission` VALUES ('41', 'report:getOverall', '报表总揽', '2017-05-05 20:41:31', '2017-08-01 13:34:23');
INSERT INTO `auth_permission` VALUES ('42', 'report:getBorrow', '报表借还', '2017-05-05 20:41:36', '2017-08-01 13:34:32');
INSERT INTO `auth_permission` VALUES ('45', 'stockTake:add', '盘点添加', '2017-05-05 20:42:59', '2017-08-01 13:35:05');
INSERT INTO `auth_permission` VALUES ('46', 'stockTake:delete', '盘点删除', '2017-05-05 20:43:04', '2017-08-01 13:35:22');
INSERT INTO `auth_permission` VALUES ('47', 'stockTake:update', '盘点更新', '2017-05-05 20:43:10', '2017-08-01 13:35:28');
INSERT INTO `auth_permission` VALUES ('48', 'stockTake:getList', '盘点列表', '2017-05-05 20:43:17', '2017-08-01 13:35:36');
INSERT INTO `auth_permission` VALUES ('49', 'stockTake:handle', '盘点处理', '2017-05-05 20:43:23', '2017-08-01 13:35:46');
INSERT INTO `auth_permission` VALUES ('50', 'stockTake:getAvailableMap', '盘点xx', '2017-05-05 20:43:38', '2017-08-01 13:36:00');
INSERT INTO `auth_permission` VALUES ('51', 'stockTake:updateAmount', '盘点数量更新', '2017-05-05 20:43:46', '2017-08-01 13:36:10');
INSERT INTO `auth_permission` VALUES ('52', 'stockTake:getItemList', '盘点明显列表', '2017-05-05 20:43:55', '2017-08-01 13:36:20');
INSERT INTO `auth_permission` VALUES ('53', 'stockTake:updateToAbnormal', '盘点标记异常', '2017-05-05 20:44:00', '2017-08-01 13:36:37');
INSERT INTO `auth_permission` VALUES ('54', 'user:updatePoint', '用户网点更新', '2017-05-05 20:44:27', '2017-08-01 13:36:55');
INSERT INTO `auth_permission` VALUES ('56', 'asset:stockTake:add', '资产盘点开启', '2017-05-05 20:56:21', '2017-08-01 13:37:56');
INSERT INTO `auth_permission` VALUES ('57', 'stockTake:close', '盘点关闭', '2017-05-08 11:34:15', '2017-08-01 13:37:11');
INSERT INTO `auth_permission` VALUES ('58', 'system-point-subordinate:query', '网点下级查询', '2017-08-25 22:31:09', '2017-08-25 22:31:09');
INSERT INTO `auth_permission` VALUES ('59', 'system-point-subordinate:query:edit', '网点下级管理', '2017-08-25 22:31:32', '2017-08-25 22:31:32');
INSERT INTO `auth_permission` VALUES ('60', 'system-point-subordinate:query:globe', '网点全局查询', '2017-08-25 22:31:54', '2017-08-25 22:31:54');
INSERT INTO `auth_permission` VALUES ('61', 'sys:point', '网点管理', '2017-08-27 22:36:26', '2017-08-27 22:36:26');

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，1：启用，2：不启用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('3', '默认角色', '1', '2017-01-30 22:30:37', '2017-05-05 21:22:24');
INSERT INTO `auth_role` VALUES ('4', '超级管理员', '1', '2017-01-30 22:30:59', '2017-05-05 21:29:52');
INSERT INTO `auth_role` VALUES ('5', '未启用角色', '2', '2017-04-16 19:18:55', '2017-09-05 00:16:31');
INSERT INTO `auth_role` VALUES ('6', '财务', '1', '2017-05-05 21:41:41', '2017-05-05 21:41:41');
INSERT INTO `auth_role` VALUES ('7', '经理', '1', '2017-05-05 21:43:15', '2017-05-05 21:45:33');
INSERT INTO `auth_role` VALUES ('8', '员工', '1', '2017-05-05 21:45:45', '2017-05-05 21:45:45');

-- ----------------------------
-- Table structure for auth_role_assets_type_relation
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_assets_type_relation`;
CREATE TABLE `auth_role_assets_type_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `assets_type_id` bigint(20) NOT NULL COMMENT '资产类型id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique@role_id, assets_type_id` (`role_id`,`assets_type_id`) USING BTREE,
  KEY `rat@assets_type_id` (`assets_type_id`) USING BTREE,
  CONSTRAINT `auth_role_assets_type_relation_ibfk_1` FOREIGN KEY (`assets_type_id`) REFERENCES `assets_type` (`id`) ON DELETE CASCADE,
  CONSTRAINT `auth_role_assets_type_relation_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role_assets_type_relation
-- ----------------------------

-- ----------------------------
-- Table structure for auth_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_permission_relation`;
CREATE TABLE `auth_role_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique@role_id, permission_id` (`role_id`,`permission_id`) USING BTREE,
  KEY `rp@permission_id` (`permission_id`) USING BTREE,
  CONSTRAINT `auth_role_permission_relation_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`) ON DELETE CASCADE,
  CONSTRAINT `auth_role_permission_relation_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=314 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role_permission_relation
-- ----------------------------
INSERT INTO `auth_role_permission_relation` VALUES ('165', '7', '49', '2017-05-05 22:58:15', '2017-05-05 22:58:15');
INSERT INTO `auth_role_permission_relation` VALUES ('166', '7', '34', '2017-05-05 22:58:15', '2017-05-05 22:58:15');
INSERT INTO `auth_role_permission_relation` VALUES ('167', '7', '50', '2017-05-05 22:58:15', '2017-05-05 22:58:15');
INSERT INTO `auth_role_permission_relation` VALUES ('168', '7', '20', '2017-05-05 22:58:15', '2017-05-05 22:58:15');
INSERT INTO `auth_role_permission_relation` VALUES ('169', '7', '21', '2017-05-05 22:58:15', '2017-05-05 22:58:15');
INSERT INTO `auth_role_permission_relation` VALUES ('170', '7', '22', '2017-05-05 22:58:15', '2017-05-05 22:58:15');
INSERT INTO `auth_role_permission_relation` VALUES ('171', '7', '23', '2017-05-05 22:58:15', '2017-05-05 22:58:15');
INSERT INTO `auth_role_permission_relation` VALUES ('172', '7', '24', '2017-05-05 22:58:15', '2017-05-05 22:58:15');
INSERT INTO `auth_role_permission_relation` VALUES ('173', '7', '56', '2017-05-05 22:58:15', '2017-05-05 22:58:15');
INSERT INTO `auth_role_permission_relation` VALUES ('174', '7', '25', '2017-05-05 22:58:15', '2017-05-05 22:58:15');
INSERT INTO `auth_role_permission_relation` VALUES ('175', '7', '27', '2017-05-05 22:58:15', '2017-05-05 22:58:15');
INSERT INTO `auth_role_permission_relation` VALUES ('176', '8', '49', '2017-05-05 22:58:21', '2017-05-05 22:58:21');
INSERT INTO `auth_role_permission_relation` VALUES ('177', '8', '34', '2017-05-05 22:58:21', '2017-05-05 22:58:21');
INSERT INTO `auth_role_permission_relation` VALUES ('178', '8', '50', '2017-05-05 22:58:21', '2017-05-05 22:58:21');
INSERT INTO `auth_role_permission_relation` VALUES ('179', '6', '20', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('180', '6', '21', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('181', '6', '22', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('182', '6', '23', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('183', '6', '24', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('184', '6', '25', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('185', '6', '27', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('186', '6', '28', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('187', '6', '29', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('188', '6', '31', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('189', '6', '32', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('190', '6', '33', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('191', '6', '34', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('192', '6', '41', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('193', '6', '42', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('194', '6', '46', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('195', '6', '47', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('196', '6', '48', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('197', '6', '49', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('198', '6', '50', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('199', '6', '51', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('200', '6', '52', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('201', '6', '53', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('202', '6', '56', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('203', '6', '57', '2017-05-08 11:36:37', '2017-05-08 11:36:37');
INSERT INTO `auth_role_permission_relation` VALUES ('273', '4', '1', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('274', '4', '2', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('275', '4', '3', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('276', '4', '4', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('277', '4', '9', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('278', '4', '20', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('279', '4', '21', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('280', '4', '22', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('281', '4', '23', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('282', '4', '24', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('283', '4', '25', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('284', '4', '26', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('285', '4', '27', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('286', '4', '28', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('287', '4', '29', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('288', '4', '31', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('289', '4', '32', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('290', '4', '33', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('291', '4', '34', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('292', '4', '41', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('293', '4', '42', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('294', '4', '45', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('295', '4', '46', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('296', '4', '47', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('297', '4', '48', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('298', '4', '49', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('299', '4', '50', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('300', '4', '51', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('301', '4', '52', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('302', '4', '53', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('303', '4', '54', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('304', '4', '56', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('305', '4', '57', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('306', '4', '58', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('307', '4', '59', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('308', '4', '60', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('309', '4', '61', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('310', '5', '3', '2017-09-05 00:16:11', '2017-09-05 00:16:11');
INSERT INTO `auth_role_permission_relation` VALUES ('311', '5', '4', '2017-09-05 00:16:11', '2017-09-05 00:16:11');
INSERT INTO `auth_role_permission_relation` VALUES ('312', '5', '9', '2017-09-05 00:16:11', '2017-09-05 00:16:11');
INSERT INTO `auth_role_permission_relation` VALUES ('313', '5', '20', '2017-09-05 00:16:11', '2017-09-05 00:16:11');

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(50) NOT NULL COMMENT '用户名称',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `description` varchar(255) DEFAULT NULL COMMENT '个人介绍',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `point_id` bigint(20) DEFAULT NULL COMMENT '网点id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique@name` (`name`) USING BTREE,
  KEY `user@role_id` (`role_id`) USING BTREE,
  KEY `user@point_id` (`point_id`) USING BTREE,
  CONSTRAINT `auth_user_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES ('1', 'admin', '123456', 'Hi~~~ wo', '4', '3', '2017-01-31 22:39:53', '2017-09-05 00:02:07');
INSERT INTO `auth_user` VALUES ('16', 'jen', '123456', null, '3', '3', '2017-05-05 21:34:58', '2017-05-05 21:34:58');
INSERT INTO `auth_user` VALUES ('17', 'mik', '123456', null, '4', '1', '2017-05-05 21:35:49', '2017-05-05 21:35:49');
INSERT INTO `auth_user` VALUES ('18', 'cai', '123456', null, '6', '1', '2017-05-05 21:47:05', '2017-05-05 21:47:05');
INSERT INTO `auth_user` VALUES ('19', 'jin', '123456', null, '7', '1', '2017-05-05 21:47:28', '2017-05-05 21:47:28');
INSERT INTO `auth_user` VALUES ('20', 'yuan', '123456', null, '8', '4', '2017-05-05 21:47:55', '2017-05-05 21:47:55');

-- ----------------------------
-- Table structure for point
-- ----------------------------
DROP TABLE IF EXISTS `point`;
CREATE TABLE `point` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '网点id',
  `name` varchar(50) NOT NULL COMMENT '网点名称',
  `level` int(8) NOT NULL COMMENT '级别',
  `pid` bigint(20) NOT NULL COMMENT '父节点id',
  `order` int(8) NOT NULL COMMENT '同一个父节点下面的排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of point
-- ----------------------------
INSERT INTO `point` VALUES ('1', '浙江总部', '1', '0', '1', '2017-04-30 19:34:38', '2017-08-25 22:10:07');
INSERT INTO `point` VALUES ('2', '杭州分部', '2', '1', '1', '2017-04-30 19:34:56', '2017-04-30 19:34:56');
INSERT INTO `point` VALUES ('3', '嘉兴分部', '2', '1', '2', '2017-04-30 19:35:13', '2017-04-30 19:35:13');
INSERT INTO `point` VALUES ('4', '西湖区办事点', '3', '2', '1', '2017-04-30 19:35:42', '2017-08-04 23:01:55');

-- ----------------------------
-- Table structure for system_config
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `variable` varchar(128) NOT NULL COMMENT '系统变量',
  `value` varchar(128) DEFAULT NULL COMMENT '系统值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique@variable` (`variable`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_config
-- ----------------------------

-- ----------------------------
-- Table structure for system_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `system_dictionary`;
CREATE TABLE `system_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `table` varchar(255) NOT NULL COMMENT '表',
  `column` varchar(255) NOT NULL COMMENT '列',
  `key` varchar(255) NOT NULL COMMENT 'key',
  `value` varchar(255) NOT NULL COMMENT 'value',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique@table, column, key` (`table`,`column`,`key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_dictionary
-- ----------------------------
INSERT INTO `system_dictionary` VALUES ('1', 'auth_role', 'status', '1', '启用', '2017-04-16 18:23:37', '2017-04-16 18:23:37');
INSERT INTO `system_dictionary` VALUES ('2', 'auth_role', 'status', '2', '不启用', '2017-04-16 18:23:58', '2017-04-16 18:23:58');
INSERT INTO `system_dictionary` VALUES ('3', 'auth_menu', 'type', '1', '菜单', '2017-04-23 20:49:09', '2017-04-25 20:27:19');
INSERT INTO `system_dictionary` VALUES ('4', 'auth_menu', 'type', '2', '权限', '2017-04-23 20:50:20', '2017-04-25 20:27:22');
INSERT INTO `system_dictionary` VALUES ('5', 'assets_operation_record', 'operation_type', '1', '登记', '2017-05-02 21:46:29', '2017-05-02 21:46:29');
INSERT INTO `system_dictionary` VALUES ('6', 'assets_operation_record', 'operation_type', '2', '借', '2017-05-02 21:46:29', '2017-05-02 21:46:29');
INSERT INTO `system_dictionary` VALUES ('7', 'assets_operation_record', 'operation_type', '3', '还', '2017-05-02 21:49:44', '2017-05-03 20:00:57');
INSERT INTO `system_dictionary` VALUES ('8', 'assets_operation_record', 'operation_type', '4', '丢失', '2017-05-02 21:49:44', '2017-05-02 21:49:44');
INSERT INTO `system_dictionary` VALUES ('9', 'assets_operation_record', 'operation_type', '5', '报修', '2017-05-02 21:49:44', '2017-05-02 21:49:44');
INSERT INTO `system_dictionary` VALUES ('10', 'assets_operation_record', 'operation_type', '6', '作废', '2017-05-02 21:49:44', '2017-05-02 21:49:44');
INSERT INTO `system_dictionary` VALUES ('11', 'assets_operation_record', 'operation_type', '7', '盘点', '2017-05-02 21:49:44', '2017-05-02 21:49:44');
INSERT INTO `system_dictionary` VALUES ('12', 'assets_item', 'status', '1', '正常', '2017-05-03 16:22:14', '2017-05-03 16:22:14');
INSERT INTO `system_dictionary` VALUES ('13', 'assets_item', 'status', '2', '租借', '2017-05-03 16:22:14', '2017-05-03 16:22:14');
INSERT INTO `system_dictionary` VALUES ('14', 'assets_item', 'status', '3', '维修', '2017-05-03 16:22:14', '2017-05-03 16:22:14');
INSERT INTO `system_dictionary` VALUES ('15', 'assets_item', 'status', '4', '报废', '2017-05-03 16:22:14', '2017-05-03 16:22:14');
INSERT INTO `system_dictionary` VALUES ('16', 'assets_stock_take_item', 'status', '1', '待处理', '2017-05-04 17:40:55', '2017-05-04 17:40:55');
INSERT INTO `system_dictionary` VALUES ('17', 'assets_stock_take_item', 'status', '2', '正常', '2017-05-04 17:40:55', '2017-05-04 17:40:55');
INSERT INTO `system_dictionary` VALUES ('18', 'assets_stock_take_item', 'status', '3', '异常', '2017-05-04 17:40:55', '2017-05-04 17:40:55');
INSERT INTO `system_dictionary` VALUES ('19', 'assets_stock_take', 'status', '1', '盘点中', '2017-05-04 23:43:05', '2017-05-04 23:43:05');
INSERT INTO `system_dictionary` VALUES ('20', 'assets_stock_take', 'status', '2', '盘点完成', '2017-05-04 23:43:05', '2017-05-04 23:43:05');
