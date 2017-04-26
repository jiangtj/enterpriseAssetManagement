ALTER TABLE `auth_menu`
CHANGE COLUMN `is_menu` `type`  tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否是菜单，1：菜单，2：权限' AFTER `order`,
ADD COLUMN `menu`  varchar(50) NULL COMMENT '菜单编号' AFTER `name`,
ADD COLUMN `url`  varchar(255) NULL COMMENT '菜单请求路径' AFTER `type`,
ADD COLUMN `static_url`  varchar(255) NULL COMMENT '静态资源路径' AFTER `url`;

ALTER TABLE `auth_menu`
  ADD COLUMN `icon`  varchar(255) NULL COMMENT '图标' AFTER `type`;

ALTER TABLE `auth_menu`
  MODIFY COLUMN `menu`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单编号' AFTER `name`;

