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
INSERT INTO `auth_permission` VALUES ('25', 'asset:borrow:operation', '资产租借操作', '2017-05-05 20:35:20', '2017-08-01 13:30:49');
INSERT INTO `auth_permission` VALUES ('26', 'asset:borrow:list', '资产租借列表', '2017-05-05 20:35:40', '2017-08-17 23:03:12');
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
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` (`id`, `name`, `status`, `create_time`, `update_time`) VALUES ('2', '默认角色', '1', '2017-01-30 22:30:37', '2017-05-05 21:22:24');
INSERT INTO `auth_role` (`id`, `name`, `status`, `create_time`, `update_time`) VALUES ('1', '超级管理员', '1', '2017-01-30 22:30:59', '2017-05-05 21:29:52');


-- ----------------------------
-- Records of auth_role_permission_relation
-- ----------------------------
INSERT INTO `auth_role_permission_relation` VALUES ('273', '1', '1', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('274', '1', '2', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('275', '1', '3', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('276', '1', '4', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('277', '1', '9', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('278', '1', '20', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('279', '1', '21', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('280', '1', '22', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('281', '1', '23', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('282', '1', '24', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('283', '1', '25', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('284', '1', '26', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('285', '1', '27', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('286', '1', '28', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('287', '1', '29', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('288', '1', '31', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('289', '1', '32', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('290', '1', '33', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('291', '1', '34', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('292', '1', '41', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('293', '1', '42', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('294', '1', '45', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('295', '1', '46', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('296', '1', '47', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('297', '1', '48', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('298', '1', '49', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('299', '1', '50', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('300', '1', '51', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('301', '1', '52', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('302', '1', '53', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('303', '1', '54', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('304', '1', '56', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('305', '1', '57', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('306', '1', '58', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('307', '1', '59', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('308', '1', '60', '2017-08-28 00:06:37', '2017-08-28 00:06:37');
INSERT INTO `auth_role_permission_relation` VALUES ('309', '1', '61', '2017-08-28 00:06:37', '2017-08-28 00:06:37');

-- ----------------------------
-- Records of point
-- ----------------------------
INSERT INTO `point` (`id`, `name`, `pid`, `order`, `create_time`, `update_time`) VALUES ('1', '浙江总部', '1', '0', '2017-04-30 19:34:38', '2017-08-25 22:10:07');

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` (`id`, `name`, `password`, `description`, `role_id`, `point_id`, `create_time`, `update_time`) VALUES ('1', 'admin', '123456', 'Hi~~~ wo', '1', '1', '2017-01-31 22:39:53', '2017-09-05 00:02:07');

-- ----------------------------
-- Records of assets_type
-- ----------------------------
INSERT INTO `assets_type` (`id`, `name`, `pid`, `order`, `create_time`, `update_time`) VALUES ('1', '计算机类', '0', '1', '2017-04-29 19:59:20', '2017-04-29 19:59:20');

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
