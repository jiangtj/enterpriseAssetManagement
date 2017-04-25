ALTER TABLE `auth_menu`
CHANGE COLUMN `is_menu` `type`  tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否是菜单，1：菜单，2：权限' AFTER `order`,
ADD COLUMN `menu`  varchar(50) NULL COMMENT '菜单编号' AFTER `name`,
ADD COLUMN `url`  varchar(255) NULL COMMENT '菜单请求路径' AFTER `type`,
ADD COLUMN `static_url`  varchar(255) NULL COMMENT '静态资源路径' AFTER `url`;