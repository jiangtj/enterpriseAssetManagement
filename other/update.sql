# 由于开发有两mysql，这里的数据做同步用

ALTER TABLE `auth_permission`
  CHANGE COLUMN `name` `code`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限code' AFTER `id`,
  CHANGE COLUMN `url` `name`  varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称' AFTER `code`;