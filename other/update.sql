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