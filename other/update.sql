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


ALTER TABLE `assets_stock_take`
  ADD COLUMN `status`  tinyint(4) NULL DEFAULT 1 COMMENT '状态，1：盘点中，2：盘点完成' AFTER `user_id`;
INSERT INTO `asset`.`system_dictionary` (`id`, `table`, `column`, `key`, `value`, `create_time`, `update_time`) VALUES ('19', 'assets_stock_take', 'status', '1', '盘点中', '2017-05-04 23:43:05', '2017-05-04 23:43:05');
INSERT INTO `asset`.`system_dictionary` (`id`, `table`, `column`, `key`, `value`, `create_time`, `update_time`) VALUES ('20', 'assets_stock_take', 'status', '2', '盘点完成', '2017-05-04 23:43:05', '2017-05-04 23:43:05');

UPDATE `asset`.`auth_menu` SET `id`='32', `name`='任务', `menu`='StockTakeTask', `level`='2', `pid`='31', `order`='1', `type`='1', `icon`=NULL, `url`='/stockTake/task', `static_url`='/model/stockTake/task.vue', `permission_id`=NULL, `create_time`='2017-05-04 16:05:36', `update_time`='2017-05-04 22:46:29' WHERE (`id`='32');
UPDATE `asset`.`auth_menu` SET `id`='33', `name`='处理', `menu`='StockTakeHandle', `level`='2', `pid`='31', `order`='3', `type`='1', `icon`=NULL, `url`='/stockTake/handle', `static_url`='/model/stockTake/handle.vue', `permission_id`=NULL, `create_time`='2017-05-04 16:21:28', `update_time`='2017-05-04 22:46:34' WHERE (`id`='33');
INSERT INTO `asset`.`auth_menu` (`id`, `name`, `menu`, `level`, `pid`, `order`, `type`, `icon`, `url`, `static_url`, `permission_id`, `create_time`, `update_time`) VALUES ('34', '明细', 'StockTakeItem', '2', '31', '2', '1', NULL, '/stockTake/item', '/model/stockTake/item.vue', NULL, '2017-05-04 22:46:10', '2017-05-04 22:46:38');
