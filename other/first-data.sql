/*
Navicat MySQL Data Transfer

Source Server         : mysql-mine
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : first

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-04-27 00:31:17
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
  `remark` varchar(255) DEFAULT NULL COMMENT '资产名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique@uuid` (`uuid`) USING BTREE,
  KEY `ab@user_id` (`user_id`) USING BTREE,
  CONSTRAINT `ab@user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `ab@uuid` FOREIGN KEY (`uuid`) REFERENCES `assets_item` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_borrow
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_item
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_operation_record
-- ----------------------------

-- ----------------------------
-- Table structure for assets_stock_take
-- ----------------------------
DROP TABLE IF EXISTS `assets_stock_take`;
CREATE TABLE `assets_stock_take` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `no` varchar(50) NOT NULL COMMENT '盘点编号',
  `name` varchar(50) NOT NULL COMMENT '盘点名称',
  `user_id` bigint(20) NOT NULL COMMENT '盘点负责人',
  `end_time` datetime DEFAULT NULL COMMENT '盘点结束时间',
  `all_amount` int(8) DEFAULT '0' COMMENT '盘点数目',
  `handling_amount` int(8) DEFAULT '0' COMMENT '待处理数目',
  `normal_amount` int(8) DEFAULT '0' COMMENT '正常数目',
  `abnormal_amount` int(8) DEFAULT '0' COMMENT '异常数目',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_stock_take
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets_type
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_menu
-- ----------------------------
INSERT INTO `auth_menu` VALUES ('1', '首页', 'Home', '1', '0', '1', '1', 'fa-dashboard', '/', '/model/index.vue', null, '2017-04-25 21:20:36', '2017-04-26 22:13:48');
INSERT INTO `auth_menu` VALUES ('2', '资源管理', 'Asset', '1', '0', '2', '1', 'fa-th-large', '', null, null, '2017-04-25 21:20:59', '2017-04-26 22:10:34');
INSERT INTO `auth_menu` VALUES ('4', '报表分析', 'Report ', '1', '0', '3', '1', 'fa-pie-chart', null, null, null, '2017-04-26 22:11:41', '2017-04-26 22:11:41');
INSERT INTO `auth_menu` VALUES ('5', '系统管理', 'System', '1', '0', '99', '1', 'fa-cogs', null, null, null, '2017-04-26 22:12:16', '2017-04-26 22:12:16');
INSERT INTO `auth_menu` VALUES ('6', '用户管理', 'User', '2', '5', '1', '1', null, '/system/user', '/model/system/user.vue', null, '2017-04-26 22:13:37', '2017-04-26 22:13:37');
INSERT INTO `auth_menu` VALUES ('7', '角色管理', 'Role', '2', '5', '2', '1', null, '/system/role', '/model/system/role.vue', null, '2017-04-26 22:14:32', '2017-04-26 22:14:32');
INSERT INTO `auth_menu` VALUES ('8', '权限管理', 'Permission', '2', '5', '3', '1', null, '/system/permission', '/model/system/permission.vue', null, '2017-04-26 22:15:12', '2017-04-26 22:15:12');
INSERT INTO `auth_menu` VALUES ('9', '菜单管理', 'Menu', '2', '5', '4', '1', null, '/system/menu', '/model/system/menu.vue', null, '2017-04-26 22:15:43', '2017-04-26 22:15:43');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES ('1', 'uw', '/edsc/dvr', '2017-04-17 22:47:12', '2017-04-17 22:47:12');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('3', '1', '1', '2017-01-30 22:30:37', '2017-01-30 22:30:55');
INSERT INTO `auth_role` VALUES ('4', '2', '1', '2017-01-30 22:30:59', '2017-01-30 22:30:59');
INSERT INTO `auth_role` VALUES ('5', '差价款', '2', '2017-04-16 19:18:55', '2017-04-16 19:18:55');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role_permission_relation
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role_point_relation
-- ----------------------------

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
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique@name` (`name`) USING BTREE,
  KEY `user@role_id` (`role_id`),
  CONSTRAINT `user@role_id` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES ('1', '1', '1', 'Hi~~~ wo', '3', '2017-01-31 22:39:53', '2017-04-11 21:35:29');
INSERT INTO `auth_user` VALUES ('2', '11', '111111', '的大尚书是 的', '3', '2017-04-08 22:19:40', '2017-04-09 22:42:02');
INSERT INTO `auth_user` VALUES ('4', 'dcs', 'rvvsvfb', null, '3', '2017-04-09 17:15:54', '2017-04-09 20:40:28');
INSERT INTO `auth_user` VALUES ('6', 'veave', 'veavfdv', null, '3', '2017-04-09 17:16:27', '2017-04-09 22:45:39');
INSERT INTO `auth_user` VALUES ('7', 'vevdfv', 'reveavreav', null, '3', '2017-04-09 17:16:41', '2017-04-09 17:16:41');
INSERT INTO `auth_user` VALUES ('8', 'eveavf', 'fdfd a', null, '4', '2017-04-09 17:16:50', '2017-04-09 17:16:50');
INSERT INTO `auth_user` VALUES ('10', 'vfdave', 'fdvervre', null, '4', '2017-04-09 17:17:12', '2017-04-09 17:17:12');
INSERT INTO `auth_user` VALUES ('11', 'vdvezv', 'svrsvgd', null, '4', '2017-04-09 17:17:25', '2017-04-09 17:17:25');
INSERT INTO `auth_user` VALUES ('12', 'vtvg', 'gtbsrvf', null, '4', '2017-04-09 17:17:36', '2017-04-09 23:15:32');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of point
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_dictionary
-- ----------------------------
INSERT INTO `system_dictionary` VALUES ('1', 'auth_role', 'status', '1', '启用', '2017-04-16 18:23:37', '2017-04-16 18:23:37');
INSERT INTO `system_dictionary` VALUES ('2', 'auth_role', 'status', '2', '不启用', '2017-04-16 18:23:58', '2017-04-16 18:23:58');
INSERT INTO `system_dictionary` VALUES ('3', 'auth_menu', 'type', '1', '菜单', '2017-04-23 20:49:09', '2017-04-25 20:27:19');
INSERT INTO `system_dictionary` VALUES ('4', 'auth_menu', 'type', '2', '权限', '2017-04-23 20:50:20', '2017-04-25 20:27:22');
