CREATE TABLE `assets_stock_take_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `stock_take_id` bigint(36) NOT NULL COMMENT '盘点记录id',
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

ALTER TABLE `assets_stock_take`
  DROP COLUMN `no`;

INSERT INTO `asset`.`auth_menu` (`id`, `name`, `menu`, `level`, `pid`, `order`, `type`, `icon`, `url`, `static_url`, `permission_id`, `create_time`, `update_time`) VALUES ('31', '资产盘点', 'StockTake', '1', '0', '4', '1', 'fa-certificate', NULL, NULL, NULL, '2017-05-04 16:03:45', '2017-05-04 16:03:45');
INSERT INTO `asset`.`auth_menu` (`id`, `name`, `menu`, `level`, `pid`, `order`, `type`, `icon`, `url`, `static_url`, `permission_id`, `create_time`, `update_time`) VALUES ('32', '盘点任务', 'StockTakeTask', '2', '31', '1', '1', NULL, '/stockTake/task', '/model/stockTake/task.vue', NULL, '2017-05-04 16:05:36', '2017-05-04 16:05:36');
INSERT INTO `asset`.`auth_menu` (`id`, `name`, `menu`, `level`, `pid`, `order`, `type`, `icon`, `url`, `static_url`, `permission_id`, `create_time`, `update_time`) VALUES ('33', '处理', 'StockTakeHandle', '2', '31', '2', '1', NULL, '/stockTake/handle', '/model/stockTake/handle.vue', NULL, '2017-05-04 16:21:28', '2017-05-04 16:21:28');

INSERT INTO `asset`.`system_dictionary` (`id`, `table`, `column`, `key`, `value`, `create_time`, `update_time`) VALUES ('16', 'assets_stock_take_item', 'status', '1', '待处理', '2017-05-04 17:40:55', '2017-05-04 17:40:55');
INSERT INTO `asset`.`system_dictionary` (`id`, `table`, `column`, `key`, `value`, `create_time`, `update_time`) VALUES ('17', 'assets_stock_take_item', 'status', '2', '正常', '2017-05-04 17:40:55', '2017-05-04 17:40:55');
INSERT INTO `asset`.`system_dictionary` (`id`, `table`, `column`, `key`, `value`, `create_time`, `update_time`) VALUES ('18', 'assets_stock_take_item', 'status', '3', '异常', '2017-05-04 17:40:55', '2017-05-04 17:40:55');
