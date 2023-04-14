/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : xyyq_ms

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 13/04/2023 17:00:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for health_sm
-- ----------------------------
DROP TABLE IF EXISTS `health_sm`;
CREATE TABLE `health_sm`  (
                              `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `uid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uid 学号',
                              `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
                              `physical_condition` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '身体状况 1（正常），2（有发热、咳嗽、呼吸困难等症状），3（其他症状）',
                              `at_school` int NOT NULL DEFAULT 1 COMMENT '1-在校 0-不在校',
                              `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '现在所在地',
                              `is_high_risk` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '高风险 1（true）是， 0（false）否',
                              `health_code_status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '健康码状态 1（green）绿码， 2（yellow）黄码，3（red）红码',
                              `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE INDEX `uid`(`uid`) USING BTREE,
                              INDEX `idx_sm_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '健康上报表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of health_sm
-- ----------------------------
INSERT INTO `health_sm` VALUES (1, '20190411430402', '蔡镇龙', 1, 0, '深圳市', 0, 1, '2023-04-12 17:12:14', '2023-04-12 17:12:14', 0);

-- ----------------------------
-- Table structure for leave_apply
-- ----------------------------
DROP TABLE IF EXISTS `leave_apply`;
CREATE TABLE `leave_apply`  (
                                `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
                                `uid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '学生学号',
                                `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '学生姓名',
                                `department` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '院系',
                                `classes` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '班级',
                                `dormitory_no` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '宿舍号',
                                `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '请假原因',
                                `leave_type` int NOT NULL COMMENT '请假类型（1：事假 2：病假 3：公假  4：申请校外实习）',
                                `status` int NOT NULL DEFAULT 1 COMMENT '状态（0：撤销 1：待审核 2：审核通过 3：审核不通过）',
                                `time` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请假时间区间',
                                `day` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请假天数',
                                `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '目的地',
                                `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                `traffic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '交通工具',
                                `emergency_telephone_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '宿舍',
                                `emergency_contact` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
                                `opinion` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '审核意见',
                                `is_delete` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
                                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '请假审批表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leave_apply
-- ----------------------------
INSERT INTO `leave_apply` VALUES ('1646082489413844994', '20190411430402', '蔡镇龙', '计算机科学与工程', '19计科4班', '22-201', '有事', 1, 2, '2023-04-12 00:00:11,2023-04-13 00:00:24', '', '213123123', '1111', 'https://xyyq-system.oss-cn-hangzhou.aliyuncs.com/2023/04/12/8bc03420809c40f1b67382909c4412a9Desk.jpg', '12312', '有事', '有事', '', 0, '2023-04-12 17:27:02', '2023-04-12 17:27:02');

-- ----------------------------
-- Table structure for material_info
-- ----------------------------
DROP TABLE IF EXISTS `material_info`;
CREATE TABLE `material_info`  (
                                  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
                                  `type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '物资类型名称',
                                  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '物资名称',
                                  `img` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物资图片',
                                  `specification` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '物资规格',
                                  `unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '物资单位',
                                  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
                                  `total` int NOT NULL COMMENT '库存',
                                  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否启用 0-不可用 1-可用',
                                  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  UNIQUE INDEX `idx_material_info_name`(`name`) USING BTREE,
                                  INDEX `idx_material_info_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '物资信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of material_info
-- ----------------------------
INSERT INTO `material_info` VALUES (5, '食物', '饮用水', 'https://xyyq-system.oss-cn-hangzhou.aliyuncs.com/2023/04/12/82e5aa014b7742719bf3227542a96d12Desk.jpg', '15瓶/打', '瓶', '蔡镇龙', '饮用水', 40, 1, '2023-04-12 17:14:48', '2023-04-12 17:23:41', 0);

-- ----------------------------
-- Table structure for material_out
-- ----------------------------
DROP TABLE IF EXISTS `material_out`;
CREATE TABLE `material_out`  (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '物资名称',
                                 `quantity` int NOT NULL COMMENT '出库数量',
                                 `superintendent` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '出库负责人',
                                 `reason` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '出库事由',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `material_info_out_name`(`name`) USING BTREE,
                                 CONSTRAINT `material_info_out_name` FOREIGN KEY (`name`) REFERENCES `material_info` (`name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '物资出库信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of material_out
-- ----------------------------
INSERT INTO `material_out` VALUES (2, '饮用水', 50, '菜菜', '物资支出，核酸需要', '2023-04-12 17:19:34', '2023-04-12 17:19:34', 0);
INSERT INTO `material_out` VALUES (3, '饮用水', 50, 'cc', 'cc', '2023-04-12 17:20:07', '2023-04-12 17:20:07', 0);
INSERT INTO `material_out` VALUES (4, '饮用水', 10, 'cc', 'test', '2023-04-12 17:23:41', '2023-04-12 17:23:41', 0);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
                           `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
                           `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '公告内容',
                           `publisher` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '学生姓名',
                           `type` int NOT NULL DEFAULT 0 COMMENT '公告类型 0-普通通知 1-讲座通知 2-重要公告',
                           `is_delete` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '明天下午15：00行政楼核酸', '蔡镇龙', 2, 0, '2023-04-12 17:07:03', '2023-04-12 17:07:03');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
                         `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
                         `user_password` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
                         `gender` int NULL DEFAULT NULL COMMENT '性别',
                         `phone` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
                         `nike_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信昵称',
                         `avatar_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
                         `email` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                         `user_status` int NULL DEFAULT 1 COMMENT '用户状态（0-禁用，1-正常）',
                         `user_role` int NOT NULL DEFAULT 0 COMMENT '用户角色 0-学生 1-老师 2-管理员',
                         `department` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '院系',
                         `classes` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '班级',
                         `dormitory_no` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '宿舍号',
                         `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除',
                         PRIMARY KEY (`id`) USING BTREE,
                         UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('20190411430401', '教师A', 'dac99e804306e04fe2d5b13e9915a74c', 2, '12345678910', NULL, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202107%2F19%2F20210719150601_4401e.thumb.1000_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1683884002&t=0f740b3511bc1685b54d8180d604dde5', '13729417461', 1, 1, '13729417461', '13729417461', '13729417461', '2023-04-12 18:08:29', '2023-04-13 14:36:41', 0);
INSERT INTO `user` VALUES ('20190411430402', '菜菜打篮球', 'dac99e804306e04fe2d5b13e9915a74c', 1, '13729417462', '菜菜', 'https://xyyq-system.oss-cn-hangzhou.aliyuncs.com/2023/04/13/1a4eefdf8604491495e0d1402802a589Desk.jpg', 'caizhenlong07@163.com', 1, 2, '计算机科学与工程', '19计科4班', '22-201', '2023-02-25 10:50:52', '2023-04-13 15:40:29', 0);
INSERT INTO `user` VALUES ('20190411430403', '学生B', 'dac99e804306e04fe2d5b13e9915a74c', 2, '12345678999', NULL, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202107%2F19%2F20210719150601_4401e.thumb.1000_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1683884002&t=0f740b3511bc1685b54d8180d604dde5', '12345678999', 1, 0, '12345678999', '12345678999', '12345678999', '2023-04-12 18:06:20', '2023-04-13 14:37:43', 0);
INSERT INTO `user` VALUES ('caicai', 'caicai', 'a8ba0bcad59c115e66d8e706ba2c19ca', NULL, NULL, NULL, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202107%2F19%2F20210719150601_4401e.thumb.1000_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1683884002&t=0f740b3511bc1685b54d8180d604dde5', NULL, 1, 2, NULL, NULL, NULL, '2023-04-13 13:14:28', '2023-04-13 13:15:44', 0);

SET FOREIGN_KEY_CHECKS = 1;
