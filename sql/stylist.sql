/*
 Navicat Premium Data Transfer

 Source Server         : mysql-local
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : mr_huzi

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 06/06/2020 00:41:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for stylist
-- ----------------------------
DROP TABLE IF EXISTS `stylist`;
CREATE TABLE `stylist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `person_name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `introduction` varchar(900) DEFAULT NULL COMMENT '简介',
  `english_name` varchar(100) DEFAULT NULL COMMENT '英文名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `picture_path` varchar(300) DEFAULT NULL COMMENT '照片路径',
  `remark` varchar(900) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发型师表';

SET FOREIGN_KEY_CHECKS = 1;
