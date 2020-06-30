/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 30/06/2020 15:04:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章id',
  `author` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `title` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '摘要',
  `cover` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '封面',
  `views` int(11) NOT NULL DEFAULT 0 COMMENT '浏览量',
  `comment_count` int(11) NOT NULL DEFAULT 0 COMMENT '评论总数',
  `like_count` int(11) NOT NULL DEFAULT 0 COMMENT '点赞总数',
  `top` tinyint(1) NULL DEFAULT 0 COMMENT '是否置顶(0:否,1:是)',
  `recommend` tinyint(1) NULL DEFAULT 0 COMMENT '是否推荐阅读(0:否,1:是)',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `enable` tinyint(1) NOT NULL COMMENT '是否启用',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除(0否，1是)',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章分类id',
  `article_id` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章id',
  `category_id` int(11) NOT NULL COMMENT '分类id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_comment
-- ----------------------------
DROP TABLE IF EXISTS `article_comment`;
CREATE TABLE `article_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论用户',
  `article_id` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被评论的文章id',
  `parent_id` int(11) NOT NULL DEFAULT -1 COMMENT '父id(被回复的评论id)',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '发布日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章标签id',
  `article_id` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章id',
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `category_name` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `parent_category_id` int(11) NULL DEFAULT -1 COMMENT '父分类id',
  `enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for friend_link
-- ----------------------------
DROP TABLE IF EXISTS `friend_link`;
CREATE TABLE `friend_link`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `url` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '链接',
  `enable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态(0：审核中;1：成功)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `url` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求路径',
  `params` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `status` int(1) NOT NULL COMMENT '访问状态，1正常0异常',
  `message` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常消息',
  `method` varchar(7) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求方式',
  `used_time` int(11) NOT NULL DEFAULT 0 COMMENT '响应时间',
  `result` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '返回值',
  `ip` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求ip',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5191 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_perms
-- ----------------------------
DROP TABLE IF EXISTS `sys_perms`;
CREATE TABLE `sys_perms`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `url` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'url',
  `description` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_perms
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_perms`;
CREATE TABLE `sys_role_perms`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `perms_id` int(11) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `ip` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ip',
  `username` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `age` tinyint(2) NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(2) NULL DEFAULT NULL COMMENT '性别(0:未知,1:男,2:女)',
  `user_create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '用户注册时间',
  `enable` tinyint(1) NULL DEFAULT NULL COMMENT '状态(0:停用,1启用)',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `name` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for goods_case_rel_view
-- ----------------------------
DROP VIEW IF EXISTS `goods_case_rel_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `goods_case_rel_view` AS (select `rel`.`id` AS `id`,`rel`.`spu_id` AS `spu_id`,`rel`.`case_id` AS `case_id`,`rel`.`create_by` AS `create_by`,`rel`.`create_time` AS `create_time`,`rel`.`update_by` AS `update_by`,`rel`.`update_time` AS `update_time`,`rel`.`del_flag` AS `del_flag`,`goods_case`.`cust_name` AS `case_cust_name`,`goods_case`.`pic_path` AS `case_pic_path`,`goods_case`.`title` AS `case_title`,`goods_case`.`industry` AS `case_industry`,`goods_case`.`detail` AS `case_detail`,`goods_spu`.`goods_name` AS `goods_name`,`goods_spu`.`pic_path` AS `goods_pic_path`,`rel`.`note` AS `note`,`goods_category`.`category_name` AS `goods_category_name` from (((`goods_case_relation` `rel` join `goods_case`) join `goods_spu`) join `goods_category`) where ((`rel`.`case_id` = `goods_case`.`id`) and (`rel`.`spu_id` = `goods_spu`.`id`) and (`goods_category`.`id` = `goods_spu`.`category_id`)));

-- ----------------------------
-- View structure for goods_props_view
-- ----------------------------
DROP VIEW IF EXISTS `goods_props_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `goods_props_view` AS (select `goods_props_key`.`id` AS `key_id`,`goods_props_key`.`key` AS `key`,`goods_props_value`.`id` AS `id`,`goods_props_value`.`value` AS `value`,`goods_props_key`.`purpose` AS `purpose` from (`goods_props_key` join `goods_props_value`) where (`goods_props_key`.`id` = `goods_props_value`.`key_id`));

-- ----------------------------
-- View structure for goods_spu_sku_view
-- ----------------------------
DROP VIEW IF EXISTS `goods_spu_sku_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `goods_spu_sku_view` AS (select `goods_brand`.`brand_name` AS `brand_name`,`goods_category`.`category_name` AS `category_name`,`spu`.`id` AS `spu_id`,`spu`.`goods_name` AS `goods_name`,`spu`.`category_id` AS `category_id`,`spu`.`brand_id` AS `brand_id`,`spu`.`title` AS `title`,`spu`.`props` AS `props`,`spu`.`props_str` AS `props_str`,`spu`.`detail` AS `detail`,`spu`.`status` AS `status`,`spu`.`pic_path` AS `pic_path`,`spu`.`pic_path_pc` AS `pic_path_pc`,`spu`.`create_time` AS `spu_create_time`,`spu`.`update_time` AS `spu_update_time`,`spu`.`del_flag` AS `del_flag`,`spu`.`sub_title` AS `sub_title`,`spu`.`cust_img` AS `cust_img`,`spu`.`start_validity` AS `spu_start_validity`,`spu`.`end_validity` AS `spu_end_validity`,`sku`.`id` AS `sku_id`,`sku`.`sku_name` AS `sku_name`,`sku`.`pic_path` AS `sku_pic_path`,`sku`.`props` AS `sku_props`,`sku`.`props_str` AS `sku_props_str`,`sku`.`quantity` AS `quantity`,`sku`.`sale_count` AS `sale_count`,`sku`.`sale_price` AS `sale_price`,`sku`.`cost_price` AS `cost_price`,`sku`.`status` AS `sku_status`,`sku`.`create_time` AS `sku_create_time`,`sku`.`update_time` AS `sku_update_time`,`sku`.`start_validity` AS `sku_start_validity`,`sku`.`end_validity` AS `sku_end_validity`,`sku`.`goods_authority` AS `goods_authority`,`sku`.`del_flag` AS `sku_del_flag`,`sku`.`is_default` AS `is_default`,`sku`.`ad_img` AS `sku_ad_img` from (((`goods_spu` `spu` left join `goods_sku` `sku` on((`sku`.`spu_id` = `spu`.`id`))) join `goods_brand` on((`spu`.`brand_id` = `goods_brand`.`id`))) join `goods_category` on((`spu`.`category_id` = `goods_category`.`id`))));

SET FOREIGN_KEY_CHECKS = 1;
