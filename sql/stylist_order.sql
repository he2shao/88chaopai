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

 Date: 07/06/2020 23:18:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for stylist_order
-- ----------------------------
DROP TABLE IF EXISTS `stylist_order`;
CREATE TABLE `stylist_order` (
  `id` varchar(32) NOT NULL COMMENT '订单号',
  `person_name` varchar(100) DEFAULT NULL COMMENT '预约人姓名',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `order_time` datetime DEFAULT NULL COMMENT '预约时间',
  `order_num` int(11) DEFAULT NULL COMMENT '预约人数',
  `order_stylist` bigint(20) DEFAULT NULL COMMENT '预约发型师',
  `order_project` varchar(100) DEFAULT NULL COMMENT '预约项目',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(900) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约订单表';

SET FOREIGN_KEY_CHECKS = 1;
