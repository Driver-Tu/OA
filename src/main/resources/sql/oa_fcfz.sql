/*
 Navicat Premium Dump SQL

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : oa_fcfz

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 18/09/2024 14:10:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `attendance_id` int NOT NULL AUTO_INCREMENT COMMENT '考勤id',
  `attendance_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '考勤人id',
  `time_in` datetime NOT NULL COMMENT '上班打卡时间',
  `time_out` datetime NOT NULL COMMENT '下班打卡时间',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '打卡日期',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '打卡状态',
  PRIMARY KEY (`attendance_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of attendance
-- ----------------------------

-- ----------------------------
-- Table structure for depart
-- ----------------------------
DROP TABLE IF EXISTS `depart`;
CREATE TABLE `depart`  (
  `depart_id` int NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `depart_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `depart_telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门电话号码',
  `depart_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门邮箱',
  `depart_message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门信息',
  `ct_time` datetime NOT NULL COMMENT '创建时间',
  `up_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` int NOT NULL DEFAULT 1 COMMENT '1是活跃，0是休息',
  PRIMARY KEY (`depart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of depart
-- ----------------------------
INSERT INTO `depart` VALUES (1, '销售部', '769-1050-3828', 'zeng8@qq.com', 'A2gEK28zk3', '2000-11-28 05:57:18', NULL, 1);
INSERT INTO `depart` VALUES (2, '人事部', '171-1132-3748', 'yunxidai9@qq.com', 'BwZ3qrlAIV', '2007-03-23 17:20:02', NULL, 1);
INSERT INTO `depart` VALUES (3, '综合部', '755-455-4310', 'yuningfeng@qq.com', 'fuXJrZpQWN', '2019-01-05 15:02:32', NULL, 0);
INSERT INTO `depart` VALUES (4, '财务部', '143-1154-5502', 'lzh123@qq.com', 'C9aJV40flW', '2018-04-19 01:34:43', NULL, 0);
INSERT INTO `depart` VALUES (5, '技术部', '148-5422-0199', 'ziyiden515@qq.com', 'IaVnwDunpq', '2012-02-05 08:22:18', NULL, 0);

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名称',
  `file_uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存储文件被 uuid 化后的文件名',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件截取根路径的子路径，是磁盘路径',
  `file_size` bigint NULL DEFAULT NULL COMMENT '文件大小',
  `file_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件类型 pdf',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '如有即是云服务器中访问文件的路径',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '特殊备注，或描述',
  `uploader_id` int NULL DEFAULT NULL COMMENT '上传人 ID',
  `upload_time` datetime NULL DEFAULT NULL COMMENT '上传时间',
  `business_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务类型，',
  `business_id` int NULL DEFAULT NULL COMMENT '关联业务 ID',
  `file_owner_id` int NULL DEFAULT NULL COMMENT '文件拥有者 ID',
  `file_status` tinyint NULL DEFAULT NULL COMMENT '0 代表已删除，1 代表正常，2 代表已归档',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int NOT NULL AUTO_INCREMENT COMMENT '主菜单id',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `permission` int NOT NULL COMMENT '职权',
  `menu_router` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路由',
  `type` int NOT NULL COMMENT '层级（几层）',
  `father_menu_id` int NOT NULL COMMENT '父id',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '首页', 2, '/hose', 1, 0);
INSERT INTO `menu` VALUES (2, '企业管理', 2, '/dept', 1, 0);
INSERT INTO `menu` VALUES (3, '考勤信息', 3, '/check', 1, 0);
INSERT INTO `menu` VALUES (4, 'OA审批', 3, '/oaflow', 1, 0);
INSERT INTO `menu` VALUES (5, '员工信息', 3, '/people', 1, 0);
INSERT INTO `menu` VALUES (6, '其他', 3, '/other', 1, 0);
INSERT INTO `menu` VALUES (7, '主页', 3, '/houseAll/main', 2, 1);
INSERT INTO `menu` VALUES (8, '人员管理', 2, '/dept/member', 2, 2);
INSERT INTO `menu` VALUES (9, '部门管理', 2, '/dept/department', 2, 2);
INSERT INTO `menu` VALUES (10, '职位管理', 2, '/dept/role', 2, 2);
INSERT INTO `menu` VALUES (11, '打卡信息', 3, '/check/message', 2, 3);
INSERT INTO `menu` VALUES (12, '考勤管理', 2, '/check/controller', 2, 3);
INSERT INTO `menu` VALUES (13, '可视化界面', 3, '/check/message/echart', 3, 11);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `Permissions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限',
  `ct_time` datetime NOT NULL COMMENT '新增时间',
  `up_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'boss', '1', '2018-08-07 17:32:32', '2019-11-27 21:02:31');
INSERT INTO `role` VALUES (2, 'admin', '2', '2012-01-09 13:27:48', '2002-10-03 15:31:56');
INSERT INTO `role` VALUES (3, 'user', '3', '2017-06-12 03:23:46', '2000-06-16 23:13:48');

-- ----------------------------
-- Table structure for son_menu
-- ----------------------------
DROP TABLE IF EXISTS `son_menu`;
CREATE TABLE `son_menu`  (
  `son_menu_id` int NOT NULL AUTO_INCREMENT,
  `son_menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `son_menu_router` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `son_menu_father` int NOT NULL,
  `son_menu_permission` int NOT NULL,
  PRIMARY KEY (`son_menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of son_menu
-- ----------------------------
INSERT INTO `son_menu` VALUES (1, '主页', '/houseAll/main', 1, 3);
INSERT INTO `son_menu` VALUES (2, '人员管理', '/dept/member', 2, 2);
INSERT INTO `son_menu` VALUES (3, '部门管理', '/dept/deptment', 2, 2);
INSERT INTO `son_menu` VALUES (4, '职位管理', '/dept/role', 2, 2);
INSERT INTO `son_menu` VALUES (5, '打卡信息', '/check/message', 3, 3);
INSERT INTO `son_menu` VALUES (6, '考勤管理', '/check/controller', 3, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` int NOT NULL COMMENT '角色id',
  `department_id` int NOT NULL COMMENT '部门id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户姓名',
  `user_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户头像',
  `emp_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户工号',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态（判断是否在工作）',
  `ct_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `up_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 1, 1, '梁震南', '1yeWOPS3Kd', '5938189848', '123456', '192-0151-2897', 'liangzhenn8@qq.com', 0, '2024-09-10 15:13:01', '2024-09-10 15:13:01');
INSERT INTO `user` VALUES (2, 2, 2, '萧璐', 'q8fX9ms3FS', '0640707652', '123456', '155-6121-6220', 'luxiao@qq.com', 0, '2024-09-10 11:07:21', NULL);
INSERT INTO `user` VALUES (3, 2, 3, '何子异', '3StKs8VbER', '9917916138', '123456', '760-771-3163', 'ziyih@qq.com', 0, '2024-09-10 16:39:59', NULL);
INSERT INTO `user` VALUES (5, 2, 5, '曹岚', 'EiwJyvOFW4', '6418582407', '123456', '28-460-1738', 'lancao@qq.com', 0, '2024-09-10 11:07:28', NULL);
INSERT INTO `user` VALUES (6, 3, 2, '黄岚', 'SHhufLveRR', '5855972111', '123456', '760-6340-3641', 'huang12@qq.com', 1, '2024-09-10 11:07:31', NULL);
INSERT INTO `user` VALUES (7, 2, 2, '小涂', 'ssss', '20221107040', '123456', NULL, NULL, 1, '2024-09-10 16:46:42', NULL);

SET FOREIGN_KEY_CHECKS = 1;
