/*
Navicat MySQL Data Transfer

Source Server         : mysql-mine
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : asset

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-05-05 23:04:39
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
  CONSTRAINT `ab@user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `ab@uuid` FOREIGN KEY (`uuid`) REFERENCES `assets_item` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_borrow
-- ----------------------------
INSERT INTO `assets_borrow` VALUES ('1', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', '2017-05-16 00:00:00', '2', '2017-05-03 00:00:00', null, '2017-05-03 20:00:02', '2017-05-03 20:00:22');
INSERT INTO `assets_borrow` VALUES ('2', 'f76ae024-0132-4c98-8339-e26752649bf3', '1', null, '2', '2017-05-03 20:01:57', null, '2017-05-03 20:01:49', '2017-05-03 20:01:56');
INSERT INTO `assets_borrow` VALUES ('3', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', null, '2', '2017-05-03 20:02:31', null, '2017-05-03 20:02:04', '2017-05-03 20:02:30');
INSERT INTO `assets_borrow` VALUES ('4', '917de91c-96c9-49fe-a607-c68e036f71b0', '1', null, '1', null, null, '2017-05-04 23:59:43', '2017-05-04 23:59:43');
INSERT INTO `assets_borrow` VALUES ('5', 'f76ae024-0132-4c98-8339-e26752649bf3', '1', null, '1', null, null, '2017-05-05 00:00:07', '2017-05-05 00:00:07');
INSERT INTO `assets_borrow` VALUES ('6', 'f0da55e0-8e89-462d-915a-46d66863bf9a', '1', null, '2', '2017-05-05 00:03:03', null, '2017-05-05 00:00:22', '2017-05-05 00:03:02');

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
  CONSTRAINT `ai@assets_type_id` FOREIGN KEY (`assets_type_id`) REFERENCES `assets_type` (`id`),
  CONSTRAINT `ai@point_id` FOREIGN KEY (`point_id`) REFERENCES `point` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

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
INSERT INTO `assets_item` VALUES ('9', 'a13c624e-f42a-4aba-b452-c246b10c68be', 'z8', '测试物品', '100.00', '1', '4', '4', '2017-05-03 19:56:58', '2017-05-03 19:56:58');

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

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
INSERT INTO `assets_stock_take` VALUES ('1', '全部盘点', '1', '1', null, '9', '7', '1', '1', '2017-05-04 20:42:03', '2017-05-04 23:56:10');
INSERT INTO `assets_stock_take` VALUES ('5', '计算机类盘点1', '1', '1', null, '1', '1', '0', '0', '2017-05-04 20:47:23', '2017-05-04 22:27:58');
INSERT INTO `assets_stock_take` VALUES ('6', '计算机类盘点2', '1', '1', null, '9', '0', '0', '0', '2017-05-04 20:47:39', '2017-05-04 22:25:41');
INSERT INTO `assets_stock_take` VALUES ('7', 'xx3', '1', '1', null, '9', '9', '0', '0', '2017-05-04 20:50:53', '2017-05-04 21:54:13');
INSERT INTO `assets_stock_take` VALUES ('8', 'xx2', '1', '1', null, '9', '9', '0', '0', '2017-05-04 20:51:08', '2017-05-04 20:51:08');

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
INSERT INTO `assets_stock_take_item` VALUES ('33', '1', 'f0da55e0-8e89-462d-915a-46d66863bf9a', 'z1', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:42:03', '2017-05-04 20:42:03');
INSERT INTO `assets_stock_take_item` VALUES ('34', '1', 'f76ae024-0132-4c98-8339-e26752649bf3', 'z2', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:42:03', '2017-05-04 20:42:03');
INSERT INTO `assets_stock_take_item` VALUES ('35', '1', '0b87e100-dacd-4fcb-8434-1bfb18a477de', 'z3', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:42:03', '2017-05-04 20:42:03');
INSERT INTO `assets_stock_take_item` VALUES ('36', '1', '3a242fab-0395-494f-ba28-837deea9c519', 'z4', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:42:03', '2017-05-04 20:42:03');
INSERT INTO `assets_stock_take_item` VALUES ('37', '1', '917de91c-96c9-49fe-a607-c68e036f71b0', 'z5', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:42:03', '2017-05-04 20:42:03');
INSERT INTO `assets_stock_take_item` VALUES ('38', '1', '0bd3ed09-9842-45ed-a2fc-3ec7e28bffa6', 'z6', '测试物品', '100.00', '2', '4', '4', '2017-05-04 20:42:03', '2017-05-04 20:53:14');
INSERT INTO `assets_stock_take_item` VALUES ('39', '1', '1eaa891f-cc02-487d-b5ef-5dbedabca45a', 'z7', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:42:03', '2017-05-04 20:42:03');
INSERT INTO `assets_stock_take_item` VALUES ('40', '1', 'a13c624e-f42a-4aba-b452-c246b10c68be', 'z8', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:42:03', '2017-05-04 20:42:03');
INSERT INTO `assets_stock_take_item` VALUES ('47', '5', 'fcd2ed78-3f68-4db8-811d-56f47c593c63', 'dca123', 'ds', '12.00', '1', '1', '4', '2017-05-04 20:47:23', '2017-05-04 20:47:23');
INSERT INTO `assets_stock_take_item` VALUES ('48', '7', 'fcd2ed78-3f68-4db8-811d-56f47c593c63', 'dca123', 'ds', '12.00', '1', '1', '4', '2017-05-04 20:50:53', '2017-05-04 20:50:53');
INSERT INTO `assets_stock_take_item` VALUES ('49', '7', 'f0da55e0-8e89-462d-915a-46d66863bf9a', 'z1', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:50:53', '2017-05-04 20:50:53');
INSERT INTO `assets_stock_take_item` VALUES ('50', '7', 'f76ae024-0132-4c98-8339-e26752649bf3', 'z2', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:50:53', '2017-05-04 20:50:53');
INSERT INTO `assets_stock_take_item` VALUES ('51', '7', '0b87e100-dacd-4fcb-8434-1bfb18a477de', 'z3', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:50:53', '2017-05-04 20:50:53');
INSERT INTO `assets_stock_take_item` VALUES ('52', '7', '3a242fab-0395-494f-ba28-837deea9c519', 'z4', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:50:53', '2017-05-04 20:50:53');
INSERT INTO `assets_stock_take_item` VALUES ('53', '7', '917de91c-96c9-49fe-a607-c68e036f71b0', 'z5', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:50:53', '2017-05-04 20:50:53');
INSERT INTO `assets_stock_take_item` VALUES ('54', '7', '0bd3ed09-9842-45ed-a2fc-3ec7e28bffa6', 'z6', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:50:53', '2017-05-04 20:50:53');
INSERT INTO `assets_stock_take_item` VALUES ('55', '7', '1eaa891f-cc02-487d-b5ef-5dbedabca45a', 'z7', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:50:53', '2017-05-04 20:50:53');
INSERT INTO `assets_stock_take_item` VALUES ('56', '7', 'a13c624e-f42a-4aba-b452-c246b10c68be', 'z8', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:50:53', '2017-05-04 20:50:53');
INSERT INTO `assets_stock_take_item` VALUES ('63', '8', 'fcd2ed78-3f68-4db8-811d-56f47c593c63', 'dca123', 'ds', '12.00', '1', '1', '4', '2017-05-04 20:51:08', '2017-05-04 20:51:08');
INSERT INTO `assets_stock_take_item` VALUES ('64', '8', 'f0da55e0-8e89-462d-915a-46d66863bf9a', 'z1', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:51:08', '2017-05-04 20:51:08');
INSERT INTO `assets_stock_take_item` VALUES ('65', '8', 'f76ae024-0132-4c98-8339-e26752649bf3', 'z2', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:51:08', '2017-05-04 20:51:08');
INSERT INTO `assets_stock_take_item` VALUES ('66', '8', '0b87e100-dacd-4fcb-8434-1bfb18a477de', 'z3', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:51:08', '2017-05-04 20:51:08');
INSERT INTO `assets_stock_take_item` VALUES ('67', '8', '3a242fab-0395-494f-ba28-837deea9c519', 'z4', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:51:08', '2017-05-04 20:51:08');
INSERT INTO `assets_stock_take_item` VALUES ('68', '8', '917de91c-96c9-49fe-a607-c68e036f71b0', 'z5', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:51:08', '2017-05-04 20:51:08');
INSERT INTO `assets_stock_take_item` VALUES ('69', '8', '0bd3ed09-9842-45ed-a2fc-3ec7e28bffa6', 'z6', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:51:08', '2017-05-04 20:51:08');
INSERT INTO `assets_stock_take_item` VALUES ('70', '8', '1eaa891f-cc02-487d-b5ef-5dbedabca45a', 'z7', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:51:08', '2017-05-04 20:51:08');
INSERT INTO `assets_stock_take_item` VALUES ('71', '8', 'a13c624e-f42a-4aba-b452-c246b10c68be', 'z8', '测试物品', '100.00', '1', '4', '4', '2017-05-04 20:51:08', '2017-05-04 20:51:08');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_type
-- ----------------------------
INSERT INTO `assets_type` VALUES ('1', '计算机类', '1', '0', '1', '2017-04-29 19:59:20', '2017-04-29 19:59:20');
INSERT INTO `assets_type` VALUES ('2', '其他', '1', '0', '99', '2017-04-29 20:00:23', '2017-04-29 20:00:23');
INSERT INTO `assets_type` VALUES ('3', '台式机', '2', '1', '1', '2017-04-29 20:01:28', '2017-04-29 20:01:28');
INSERT INTO `assets_type` VALUES ('4', '笔记本', '2', '1', '2', '2017-04-29 20:01:35', '2017-04-29 20:01:35');

-- ----------------------------
-- Table structure for auth_menu
-- ----------------------------
DROP TABLE IF EXISTS `auth_menu`;
CREATE TABLE `auth_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu` varchar(50) NOT NULL COMMENT '菜单编号',
  `level` int(8) NOT NULL COMMENT '级别',
  `pid` bigint(20) NOT NULL COMMENT '父节点id',
  `order` int(8) NOT NULL COMMENT '同一个父节点下面的排序',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否是菜单，1：菜单，2：权限',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单请求路径',
  `static_url` varchar(255) DEFAULT NULL COMMENT '静态资源路径',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `menu@permission_id` (`permission_id`),
  CONSTRAINT `menu@permission_id` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_menu
-- ----------------------------
INSERT INTO `auth_menu` VALUES ('1', '首页', 'Home', '1', '0', '1', '1', 'fa-dashboard', '/', '/model/index.vue', null, '2017-04-25 21:20:36', '2017-04-27 10:20:12');
INSERT INTO `auth_menu` VALUES ('2', '资源管理', 'Asset', '1', '0', '2', '1', 'fa-th-large', '', null, null, '2017-04-25 21:20:59', '2017-04-26 22:10:34');
INSERT INTO `auth_menu` VALUES ('4', '报表分析', 'Report ', '1', '0', '3', '1', 'fa-pie-chart', null, null, null, '2017-04-26 22:11:41', '2017-04-26 22:11:41');
INSERT INTO `auth_menu` VALUES ('5', '系统管理', 'System', '1', '0', '99', '1', 'fa-cogs', null, null, null, '2017-04-26 22:12:16', '2017-04-26 22:12:16');
INSERT INTO `auth_menu` VALUES ('6', '用户管理', 'User', '2', '5', '1', '1', null, '/system/user', '/model/system/user.vue', '4', '2017-04-26 22:13:37', '2017-04-27 10:20:18');
INSERT INTO `auth_menu` VALUES ('7', '角色管理', 'Role', '2', '5', '2', '1', null, '/system/role', '/model/system/role.vue', '5', '2017-04-26 22:14:32', '2017-04-27 10:20:24');
INSERT INTO `auth_menu` VALUES ('8', '权限管理', 'Permission', '2', '5', '3', '1', null, '/system/permission', '/model/system/permission.vue', '9', '2017-04-26 22:15:12', '2017-04-27 10:20:28');
INSERT INTO `auth_menu` VALUES ('9', '菜单管理', 'Menu', '2', '5', '4', '1', null, '/system/menu', '/model/system/menu.vue', '14', '2017-04-26 22:15:43', '2017-04-27 10:20:33');
INSERT INTO `auth_menu` VALUES ('10', '用户添加', 'userAdd', '3', '6', '1', '2', null, null, null, '1', '2017-04-27 10:22:43', '2017-04-27 10:22:43');
INSERT INTO `auth_menu` VALUES ('11', '用户删除', 'userDel', '3', '6', '2', '2', null, null, null, '2', '2017-04-27 10:23:30', '2017-04-27 10:23:30');
INSERT INTO `auth_menu` VALUES ('12', '用户修改', 'userUpdate', '3', '6', '3', '2', null, null, null, '3', '2017-04-27 10:25:45', '2017-04-27 10:25:45');
INSERT INTO `auth_menu` VALUES ('13', '角色添加', 'roleAdd', '3', '7', '1', '2', null, null, null, '6', '2017-04-27 14:59:12', '2017-04-27 14:59:12');
INSERT INTO `auth_menu` VALUES ('14', '角色删除', 'roleDel', '3', '7', '2', '2', null, null, null, '7', '2017-04-27 14:59:36', '2017-04-27 14:59:36');
INSERT INTO `auth_menu` VALUES ('15', '角色修改', 'roleUpdate', '3', '7', '3', '2', null, null, null, '8', '2017-04-27 15:00:39', '2017-04-27 15:00:39');
INSERT INTO `auth_menu` VALUES ('16', '权限添加', 'permissionAdd', '3', '8', '1', '2', null, null, null, '10', '2017-04-27 15:01:16', '2017-04-27 15:01:16');
INSERT INTO `auth_menu` VALUES ('17', '权限快速添加', 'permissionQuick', '3', '8', '2', '2', null, null, null, '11', '2017-04-27 15:02:00', '2017-04-27 15:02:00');
INSERT INTO `auth_menu` VALUES ('18', '权限删除', 'permissionDel', '3', '8', '3', '2', null, null, null, '12', '2017-04-27 15:02:47', '2017-04-27 15:02:47');
INSERT INTO `auth_menu` VALUES ('19', '权限修改', 'Permission', '3', '8', '4', '2', null, null, null, '13', '2017-04-27 15:03:48', '2017-04-27 15:03:48');
INSERT INTO `auth_menu` VALUES ('20', '菜单添加', 'MenuAdd', '3', '9', '1', '2', null, null, null, '15', '2017-04-27 15:04:34', '2017-04-27 15:04:34');
INSERT INTO `auth_menu` VALUES ('21', '菜单删除', 'MenuDel', '3', '9', '2', '2', null, null, null, '16', '2017-04-27 15:05:05', '2017-04-27 15:05:05');
INSERT INTO `auth_menu` VALUES ('22', '菜单修改', 'MenuUpdate', '3', '9', '3', '2', null, null, null, '17', '2017-04-27 15:05:42', '2017-04-27 15:05:42');
INSERT INTO `auth_menu` VALUES ('23', '菜单列表获取', 'MenuGetMenu', '3', '9', '4', '2', null, null, null, '18', '2017-04-27 15:11:40', '2017-04-27 15:52:52');
INSERT INTO `auth_menu` VALUES ('24', '资源类型', 'AssetType', '2', '5', '5', '1', null, '/system/assetType', '/model/system/assetType.vue', '32', '2017-04-29 19:02:04', '2017-05-05 21:14:07');
INSERT INTO `auth_menu` VALUES ('25', '网点管理', 'Point', '2', '5', '6', '1', null, '/system/point', '/model/system/point.vue', '38', '2017-04-30 19:32:38', '2017-05-05 21:14:14');
INSERT INTO `auth_menu` VALUES ('26', '资产添加', 'AssetAdd', '2', '2', '1', '1', null, '/asset/add', '/model/asset/add.vue', '20', '2017-05-02 19:57:00', '2017-05-05 20:45:26');
INSERT INTO `auth_menu` VALUES ('27', '资产列表', 'AssetGet', '2', '2', '2', '1', null, '/asset/get', '/model/asset/get.vue', '23', '2017-05-02 20:20:52', '2017-05-05 20:45:35');
INSERT INTO `auth_menu` VALUES ('28', '借还登记', 'AssetBorrow', '2', '2', '3', '1', null, '/asset/borrow', '/model/asset/borrow.vue', '25', '2017-05-02 22:39:49', '2017-05-05 20:45:55');
INSERT INTO `auth_menu` VALUES ('29', '资产总揽', 'ReportOverall', '2', '4', '1', '1', null, '/report/overall', '/model/report/overall.vue', '41', '2017-05-03 19:53:37', '2017-05-05 21:01:06');
INSERT INTO `auth_menu` VALUES ('30', '借还报表', 'ReportBorrow', '2', '4', '2', '1', null, '/report/borrow', '/model/report/borrow.vue', '42', '2017-05-03 19:54:38', '2017-05-05 21:01:14');
INSERT INTO `auth_menu` VALUES ('31', '资产盘点', 'StockTake', '1', '0', '4', '1', 'fa-certificate', null, null, null, '2017-05-04 16:03:45', '2017-05-04 16:03:45');
INSERT INTO `auth_menu` VALUES ('32', '任务', 'StockTakeTask', '2', '31', '1', '1', null, '/stockTake/task', '/model/stockTake/task.vue', '48', '2017-05-04 16:05:36', '2017-05-05 21:02:36');
INSERT INTO `auth_menu` VALUES ('33', '处理', 'StockTakeHandle', '2', '31', '3', '1', null, '/stockTake/handle', '/model/stockTake/handle.vue', '49', '2017-05-04 16:21:28', '2017-05-05 21:03:03');
INSERT INTO `auth_menu` VALUES ('34', '明细', 'StockTakeItem', '2', '31', '2', '1', null, '/stockTake/item', '/model/stockTake/item.vue', '52', '2017-05-04 22:46:10', '2017-05-05 21:02:45');
INSERT INTO `auth_menu` VALUES ('35', '资源类型pid查询', 'zzz', '3', '26', '1', '2', null, null, null, '34', '2017-05-05 20:49:15', '2017-05-05 20:49:15');
INSERT INTO `auth_menu` VALUES ('36', '资源类型pid查询', 'zzz', '3', '28', '1', '2', null, null, null, '34', '2017-05-05 20:50:07', '2017-05-05 20:50:38');
INSERT INTO `auth_menu` VALUES ('37', '资源删除', 'zzz', '3', '27', '1', '2', null, null, null, '21', '2017-05-05 20:51:47', '2017-05-05 20:51:47');
INSERT INTO `auth_menu` VALUES ('38', '资源修改', 'zzz', '3', '27', '2', '2', null, null, null, '22', '2017-05-05 20:52:12', '2017-05-05 20:52:12');
INSERT INTO `auth_menu` VALUES ('39', '资源操作记录查询', 'zzz', '3', '27', '3', '2', null, null, null, '24', '2017-05-05 20:53:11', '2017-05-05 20:53:11');
INSERT INTO `auth_menu` VALUES ('40', '资源类型pid', 'zzz', '3', '27', '4', '2', null, null, null, '34', '2017-05-05 20:54:00', '2017-05-05 20:54:00');
INSERT INTO `auth_menu` VALUES ('41', '盘点开启', 'zzz', '3', '27', '5', '2', null, null, null, '56', '2017-05-05 20:54:59', '2017-05-05 20:56:59');
INSERT INTO `auth_menu` VALUES ('42', '资产状态更新', 'zzz', '3', '27', '6', '2', null, null, null, '27', '2017-05-05 21:00:05', '2017-05-05 21:00:05');
INSERT INTO `auth_menu` VALUES ('43', '资产类型pid', 'zzz', '3', '33', '1', '2', null, null, null, '34', '2017-05-05 21:03:49', '2017-05-05 21:03:49');
INSERT INTO `auth_menu` VALUES ('44', '异常标记', 'zzz', '3', '34', '1', '2', null, null, null, '53', '2017-05-05 21:05:00', '2017-05-05 21:05:00');
INSERT INTO `auth_menu` VALUES ('45', '资产类型pid', 'zzz', '3', '34', '2', '2', null, null, null, '34', '2017-05-05 21:05:52', '2017-05-05 21:05:52');
INSERT INTO `auth_menu` VALUES ('47', '盘点任务删除', 'zzz', '3', '32', '1', '2', null, null, null, '46', '2017-05-05 21:07:38', '2017-05-05 21:07:38');
INSERT INTO `auth_menu` VALUES ('48', '盘点任务修改', 'zzz', '3', '32', '2', '2', null, null, null, '47', '2017-05-05 21:08:06', '2017-05-05 21:08:06');
INSERT INTO `auth_menu` VALUES ('49', '盘点任务更新', 'zzz', '3', '32', '3', '2', null, null, null, '51', '2017-05-05 21:08:49', '2017-05-05 21:08:49');
INSERT INTO `auth_menu` VALUES ('50', '盘点明细查看', 'zzz', '3', '32', '4', '2', null, null, null, '52', '2017-05-05 21:10:12', '2017-05-05 21:10:12');
INSERT INTO `auth_menu` VALUES ('51', '用户网点配置', 'zzz', '3', '6', '4', '2', null, null, null, '54', '2017-05-05 21:11:26', '2017-05-05 21:11:26');
INSERT INTO `auth_menu` VALUES ('52', '角色网点配置', 'zzz', '3', '7', '4', '2', null, null, null, '43', '2017-05-05 21:12:03', '2017-05-05 21:12:03');
INSERT INTO `auth_menu` VALUES ('53', '角色权限配置', 'zzz', '3', '7', '5', '2', null, null, null, '44', '2017-05-05 21:12:29', '2017-05-05 21:12:29');
INSERT INTO `auth_menu` VALUES ('54', '菜单pid查询', 'zzz', '3', '9', '5', '2', null, null, null, '19', '2017-05-05 21:13:19', '2017-05-05 21:13:19');
INSERT INTO `auth_menu` VALUES ('55', '菜单pid查询', 'zzz', '3', '7', '6', '2', null, null, null, '19', '2017-05-05 21:13:45', '2017-05-05 21:13:45');
INSERT INTO `auth_menu` VALUES ('56', '网点pid', 'zzz', '3', '6', '5', '2', null, null, null, '40', '2017-05-05 21:14:42', '2017-05-05 21:14:42');
INSERT INTO `auth_menu` VALUES ('58', '网点pid', 'zzz', '3', '7', '7', '2', null, null, null, '40', '2017-05-05 21:15:56', '2017-05-05 21:15:56');
INSERT INTO `auth_menu` VALUES ('59', '资源类型pid', 'zzz', '3', '24', '1', '2', null, null, null, '34', '2017-05-05 21:16:35', '2017-05-05 21:16:35');
INSERT INTO `auth_menu` VALUES ('60', '资源类型删除', 'zzz', '3', '24', '1', '2', null, null, null, '29', '2017-05-05 21:16:59', '2017-05-05 21:16:59');
INSERT INTO `auth_menu` VALUES ('61', '资源类型修改', 'zzz', '3', '24', '3', '2', null, null, null, '31', '2017-05-05 21:17:34', '2017-05-05 21:17:34');
INSERT INTO `auth_menu` VALUES ('62', '资源类型type', 'zzz', '3', '24', '4', '2', null, null, null, '33', '2017-05-05 21:17:59', '2017-05-05 21:17:59');
INSERT INTO `auth_menu` VALUES ('63', '资源类型添加', 'zzz', '3', '24', '5', '2', null, null, null, '28', '2017-05-05 21:18:32', '2017-05-05 21:18:32');
INSERT INTO `auth_menu` VALUES ('64', '网点pid', 'zzz', '3', '25', '1', '2', null, null, null, '40', '2017-05-05 21:19:57', '2017-05-05 21:19:57');
INSERT INTO `auth_menu` VALUES ('65', '网点添加', 'zzz', '3', '25', '1', '2', null, null, null, '35', '2017-05-05 21:20:17', '2017-05-05 21:20:17');
INSERT INTO `auth_menu` VALUES ('66', '网点删除', 'zzz', '3', '25', '1', '2', null, null, null, '36', '2017-05-05 21:20:31', '2017-05-05 21:20:31');
INSERT INTO `auth_menu` VALUES ('67', '网点修改', 'zzz', '3', '25', '1', '2', null, null, null, '37', '2017-05-05 21:20:51', '2017-05-05 21:20:51');
INSERT INTO `auth_menu` VALUES ('68', '网点tree', 'zzz', '3', '25', '1', '2', null, null, null, '39', '2017-05-05 21:21:18', '2017-05-05 21:21:18');
INSERT INTO `auth_menu` VALUES ('69', '可用的盘点任务', 'zzz', '3', '33', '1', '2', null, null, null, '50', '2017-05-05 22:56:57', '2017-05-05 22:56:57');

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(50) NOT NULL COMMENT '权限名称',
  `url` varchar(50) NOT NULL COMMENT '请求路径',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique@name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES ('1', 'user:add', '/user/add', '2017-04-17 22:47:12', '2017-04-27 09:37:45');
INSERT INTO `auth_permission` VALUES ('2', 'user:delete', '/user/delete', '2017-04-27 09:38:25', '2017-04-27 09:38:25');
INSERT INTO `auth_permission` VALUES ('3', 'user:update', '/user/update', '2017-04-27 09:39:27', '2017-04-27 09:39:27');
INSERT INTO `auth_permission` VALUES ('4', 'user:getList', '/user/getList', '2017-04-27 09:40:46', '2017-04-27 10:05:59');
INSERT INTO `auth_permission` VALUES ('5', 'role:getList', '/role/getList', '2017-04-27 10:06:29', '2017-04-27 10:06:29');
INSERT INTO `auth_permission` VALUES ('6', 'role:add', '/role/add', '2017-04-27 10:06:39', '2017-04-27 10:06:54');
INSERT INTO `auth_permission` VALUES ('7', 'role:delete', '/role/delete', '2017-04-27 10:07:09', '2017-04-27 10:07:09');
INSERT INTO `auth_permission` VALUES ('8', 'role:update', '/role/update', '2017-04-27 10:07:18', '2017-04-27 10:07:18');
INSERT INTO `auth_permission` VALUES ('9', 'permission:getList', '/permission/getList', '2017-04-27 10:07:26', '2017-04-27 10:07:26');
INSERT INTO `auth_permission` VALUES ('10', 'permission:add', '/permission/add', '2017-04-27 10:07:34', '2017-04-27 10:07:34');
INSERT INTO `auth_permission` VALUES ('11', 'permission:addQuick', '/permission/addQuick', '2017-04-27 10:07:45', '2017-04-27 10:07:45');
INSERT INTO `auth_permission` VALUES ('12', 'permission:delete', '/permission/delete', '2017-04-27 10:07:56', '2017-04-27 10:07:56');
INSERT INTO `auth_permission` VALUES ('13', 'permission:update', '/permission/update', '2017-04-27 10:08:05', '2017-04-27 10:08:05');
INSERT INTO `auth_permission` VALUES ('14', 'menu:getList', '/menu/getList', '2017-04-27 10:08:13', '2017-04-27 10:08:13');
INSERT INTO `auth_permission` VALUES ('15', 'menu:add', '/menu/add', '2017-04-27 10:08:21', '2017-04-27 10:08:21');
INSERT INTO `auth_permission` VALUES ('16', 'menu:delete', '/menu/delete', '2017-04-27 10:08:30', '2017-04-27 10:08:30');
INSERT INTO `auth_permission` VALUES ('17', 'menu:update', '/menu/update', '2017-04-27 10:08:37', '2017-04-27 10:08:37');
INSERT INTO `auth_permission` VALUES ('18', 'menu:getMenu', '/menu/getMenu', '2017-04-27 10:08:45', '2017-04-27 10:08:45');
INSERT INTO `auth_permission` VALUES ('19', 'menu:getMapByPid', '/menu/getMapByPid', '2017-04-27 10:08:53', '2017-05-05 20:39:57');
INSERT INTO `auth_permission` VALUES ('20', 'asset:add', '/asset/add', '2017-05-05 20:34:15', '2017-05-05 20:34:15');
INSERT INTO `auth_permission` VALUES ('21', 'asset:delete', '/asset/delete', '2017-05-05 20:34:28', '2017-05-05 20:34:28');
INSERT INTO `auth_permission` VALUES ('22', 'asset:update', '/asset/update', '2017-05-05 20:34:37', '2017-05-05 20:34:37');
INSERT INTO `auth_permission` VALUES ('23', 'asset:getList', '/asset/getList', '2017-05-05 20:34:50', '2017-05-05 20:34:50');
INSERT INTO `auth_permission` VALUES ('24', 'asset:getOperationRecordByUuid', '/asset/getOperationRecordByUuid', '2017-05-05 20:35:05', '2017-05-05 20:35:05');
INSERT INTO `auth_permission` VALUES ('25', 'asset:borrowAsset', '/asset/borrowAsset', '2017-05-05 20:35:20', '2017-05-05 20:35:20');
INSERT INTO `auth_permission` VALUES ('26', 'asset:returnAsset', '/asset/returnAsset', '2017-05-05 20:35:40', '2017-05-05 20:35:40');
INSERT INTO `auth_permission` VALUES ('27', 'asset:updateStatus', '/asset/updateStatus', '2017-05-05 20:37:46', '2017-05-05 20:37:46');
INSERT INTO `auth_permission` VALUES ('28', 'assetType:add', '/assetType/add', '2017-05-05 20:38:07', '2017-05-05 20:38:07');
INSERT INTO `auth_permission` VALUES ('29', 'assetType:delete', '/assetType/delete', '2017-05-05 20:38:16', '2017-05-05 20:38:16');
INSERT INTO `auth_permission` VALUES ('31', 'assetType:update', '/assetType/update', '2017-05-05 20:38:46', '2017-05-05 20:38:46');
INSERT INTO `auth_permission` VALUES ('32', 'assetType:getList', '/assetType/getList', '2017-05-05 20:38:57', '2017-05-05 20:38:57');
INSERT INTO `auth_permission` VALUES ('33', 'assetType:getType', '/assetType/getType', '2017-05-05 20:39:06', '2017-05-05 20:39:06');
INSERT INTO `auth_permission` VALUES ('34', 'assetType:getMapByPid', '/assetType/getMapByPid', '2017-05-05 20:39:18', '2017-05-05 20:39:18');
INSERT INTO `auth_permission` VALUES ('35', 'point:add', '/point/add', '2017-05-05 20:40:33', '2017-05-05 20:40:33');
INSERT INTO `auth_permission` VALUES ('36', 'point:delete', '/point/delete', '2017-05-05 20:40:39', '2017-05-05 20:40:39');
INSERT INTO `auth_permission` VALUES ('37', 'point:update', '/point/update', '2017-05-05 20:40:45', '2017-05-05 20:40:45');
INSERT INTO `auth_permission` VALUES ('38', 'point:getList', '/point/getList', '2017-05-05 20:40:52', '2017-05-05 20:40:52');
INSERT INTO `auth_permission` VALUES ('39', 'point:getPoint', '/point/getPoint', '2017-05-05 20:41:03', '2017-05-05 20:41:03');
INSERT INTO `auth_permission` VALUES ('40', 'point:getMapByPid', '/point/getMapByPid', '2017-05-05 20:41:10', '2017-05-05 20:41:10');
INSERT INTO `auth_permission` VALUES ('41', 'report:getOverall', '/report/getOverall', '2017-05-05 20:41:31', '2017-05-05 20:41:31');
INSERT INTO `auth_permission` VALUES ('42', 'report:getBorrow', '/report/getBorrow', '2017-05-05 20:41:36', '2017-05-05 20:41:36');
INSERT INTO `auth_permission` VALUES ('43', 'role:updatePoint', '/role/updatePoint', '2017-05-05 20:41:59', '2017-05-05 20:41:59');
INSERT INTO `auth_permission` VALUES ('44', 'role:updatePermission', '/role/updatePermission', '2017-05-05 20:42:10', '2017-05-05 20:42:10');
INSERT INTO `auth_permission` VALUES ('45', 'stockTake:add', '/stockTake/add', '2017-05-05 20:42:59', '2017-05-05 20:42:59');
INSERT INTO `auth_permission` VALUES ('46', 'stockTake:delete', '/stockTake/delete', '2017-05-05 20:43:04', '2017-05-05 20:43:04');
INSERT INTO `auth_permission` VALUES ('47', 'stockTake:update', '/stockTake/update', '2017-05-05 20:43:10', '2017-05-05 20:43:10');
INSERT INTO `auth_permission` VALUES ('48', 'stockTake:getList', '/stockTake/getList', '2017-05-05 20:43:17', '2017-05-05 20:43:17');
INSERT INTO `auth_permission` VALUES ('49', 'stockTake:handle', '/stockTake/handle', '2017-05-05 20:43:23', '2017-05-05 20:43:23');
INSERT INTO `auth_permission` VALUES ('50', 'stockTake:getAvailableMap', '/stockTake/getAvailableMap', '2017-05-05 20:43:38', '2017-05-05 20:43:38');
INSERT INTO `auth_permission` VALUES ('51', 'stockTake:updateAmount', '/stockTake/updateAmount', '2017-05-05 20:43:46', '2017-05-05 20:43:46');
INSERT INTO `auth_permission` VALUES ('52', 'stockTake:getItemList', '/stockTake/getItemList', '2017-05-05 20:43:55', '2017-05-05 20:43:55');
INSERT INTO `auth_permission` VALUES ('53', 'stockTake:updateToAbnormal', '/stockTake/updateToAbnormal', '2017-05-05 20:44:00', '2017-05-05 20:44:00');
INSERT INTO `auth_permission` VALUES ('54', 'user:updatePoint', '/user/updatePoint', '2017-05-05 20:44:27', '2017-05-05 20:44:27');
INSERT INTO `auth_permission` VALUES ('56', 'asset:addStockTake', '/asset/addStockTake', '2017-05-05 20:56:21', '2017-05-05 20:56:21');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('3', '默认角色', '1', '2017-01-30 22:30:37', '2017-05-05 21:22:24');
INSERT INTO `auth_role` VALUES ('4', '超级管理员', '1', '2017-01-30 22:30:59', '2017-05-05 21:29:52');
INSERT INTO `auth_role` VALUES ('5', '未启用角色', '2', '2017-04-16 19:18:55', '2017-05-05 21:29:30');
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
  CONSTRAINT `rat@assets_type_id` FOREIGN KEY (`assets_type_id`) REFERENCES `assets_type` (`id`) ON DELETE CASCADE,
  CONSTRAINT `rat@role_id` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE
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
  CONSTRAINT `rp@permission_id` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`) ON DELETE CASCADE,
  CONSTRAINT `rp@role_id` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role_permission_relation
-- ----------------------------
INSERT INTO `auth_role_permission_relation` VALUES ('80', '6', '32', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('81', '6', '33', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('82', '6', '34', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('83', '6', '41', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('84', '6', '42', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('85', '6', '46', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('86', '6', '47', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('87', '6', '48', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('88', '6', '51', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('89', '6', '20', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('90', '6', '52', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('91', '6', '21', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('92', '6', '53', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('93', '6', '22', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('94', '6', '23', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('95', '6', '24', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('96', '6', '56', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('97', '6', '25', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('98', '6', '27', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('99', '6', '28', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('100', '6', '29', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('101', '6', '31', '2017-05-05 21:42:47', '2017-05-05 21:42:47');
INSERT INTO `auth_role_permission_relation` VALUES ('113', '4', '1', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('114', '4', '2', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('115', '4', '3', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('116', '4', '4', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('117', '4', '5', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('118', '4', '6', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('119', '4', '7', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('120', '4', '8', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('121', '4', '9', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('122', '4', '10', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('123', '4', '11', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('124', '4', '12', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('125', '4', '13', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('126', '4', '14', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('127', '4', '15', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('128', '4', '16', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('129', '4', '17', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('130', '4', '18', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('131', '4', '19', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('132', '4', '20', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('133', '4', '21', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('134', '4', '22', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('135', '4', '23', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('136', '4', '24', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('137', '4', '25', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('138', '4', '27', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('139', '4', '28', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('140', '4', '29', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('141', '4', '31', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('142', '4', '32', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('143', '4', '33', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('144', '4', '34', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('145', '4', '35', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('146', '4', '36', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('147', '4', '37', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('148', '4', '38', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('149', '4', '39', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('150', '4', '40', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('151', '4', '41', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('152', '4', '42', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('153', '4', '43', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('154', '4', '44', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('155', '4', '46', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('156', '4', '47', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('157', '4', '48', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('158', '4', '49', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('159', '4', '50', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('160', '4', '51', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('161', '4', '52', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('162', '4', '53', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('163', '4', '54', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
INSERT INTO `auth_role_permission_relation` VALUES ('164', '4', '56', '2017-05-05 22:57:19', '2017-05-05 22:57:19');
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

-- ----------------------------
-- Table structure for auth_role_point_relation
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_point_relation`;
CREATE TABLE `auth_role_point_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `point_id` bigint(20) NOT NULL COMMENT '网点id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique@role_id, point_id` (`role_id`,`point_id`) USING BTREE,
  KEY `rp1@point_id` (`point_id`) USING BTREE,
  CONSTRAINT `rp1@point_id` FOREIGN KEY (`point_id`) REFERENCES `point` (`id`) ON DELETE CASCADE,
  CONSTRAINT `rp1@role_id` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role_point_relation
-- ----------------------------
INSERT INTO `auth_role_point_relation` VALUES ('13', '4', '1', '2017-05-05 21:31:17', '2017-05-05 21:31:17');
INSERT INTO `auth_role_point_relation` VALUES ('14', '4', '2', '2017-05-05 21:31:17', '2017-05-05 21:31:17');
INSERT INTO `auth_role_point_relation` VALUES ('15', '4', '3', '2017-05-05 21:31:17', '2017-05-05 21:31:17');
INSERT INTO `auth_role_point_relation` VALUES ('16', '4', '4', '2017-05-05 21:31:17', '2017-05-05 21:31:17');
INSERT INTO `auth_role_point_relation` VALUES ('17', '6', '1', '2017-05-05 21:42:58', '2017-05-05 21:42:58');
INSERT INTO `auth_role_point_relation` VALUES ('18', '6', '2', '2017-05-05 21:42:58', '2017-05-05 21:42:58');
INSERT INTO `auth_role_point_relation` VALUES ('19', '6', '3', '2017-05-05 21:42:58', '2017-05-05 21:42:58');
INSERT INTO `auth_role_point_relation` VALUES ('20', '6', '4', '2017-05-05 21:42:58', '2017-05-05 21:42:58');
INSERT INTO `auth_role_point_relation` VALUES ('21', '7', '1', '2017-05-05 21:46:26', '2017-05-05 21:46:26');
INSERT INTO `auth_role_point_relation` VALUES ('22', '7', '2', '2017-05-05 21:46:26', '2017-05-05 21:46:26');
INSERT INTO `auth_role_point_relation` VALUES ('23', '7', '3', '2017-05-05 21:46:26', '2017-05-05 21:46:26');
INSERT INTO `auth_role_point_relation` VALUES ('24', '7', '4', '2017-05-05 21:46:26', '2017-05-05 21:46:26');

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
  KEY `user@role_id` (`role_id`),
  KEY `user@point_id` (`point_id`),
  CONSTRAINT `user@point_id` FOREIGN KEY (`point_id`) REFERENCES `point` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `user@role_id` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES ('1', 'admin', '123456', 'Hi~~~ wo', '4', '4', '2017-01-31 22:39:53', '2017-05-05 21:40:21');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of point
-- ----------------------------
INSERT INTO `point` VALUES ('1', '浙江总部', '1', '0', '1', '2017-04-30 19:34:38', '2017-04-30 19:34:38');
INSERT INTO `point` VALUES ('2', '杭州分部', '2', '1', '1', '2017-04-30 19:34:56', '2017-04-30 19:34:56');
INSERT INTO `point` VALUES ('3', '嘉兴分部', '2', '1', '2', '2017-04-30 19:35:13', '2017-04-30 19:35:13');
INSERT INTO `point` VALUES ('4', '西湖区办事点', '3', '2', '1', '2017-04-30 19:35:42', '2017-04-30 19:35:42');

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
