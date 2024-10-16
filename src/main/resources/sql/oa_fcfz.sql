/*
 Navicat Premium Dump SQL

 Source Server         : 牢涂的 MySQL
 Source Server Type    : MySQL
 Source Server Version : 80039 (8.0.39)
 Source Host           : 192.168.0.196:3306
 Source Schema         : oa_fcfz

 Target Server Type    : MySQL
 Target Server Version : 80039 (8.0.39)
 File Encoding         : 65001

 Date: 16/10/2024 10:43:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `address_id` int NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `address_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址名称',
  `longitude` decimal(10, 7) NOT NULL COMMENT '经度',
  `Latitude` decimal(10, 7) NOT NULL COMMENT '纬度',
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for approval_forms
-- ----------------------------
DROP TABLE IF EXISTS `approval_forms`;
CREATE TABLE `approval_forms`  (
  `form_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '审批表编号',
  `applicant_id` int NOT NULL COMMENT '申请人id',
  `all_id` int NULL DEFAULT 1 COMMENT '各类表id',
  `from_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请备注',
  `application_date` datetime NOT NULL COMMENT '申请日期',
  `status` enum('申请通过','申请失败','已完成','未完成') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '审批状态',
  `type` enum('请假','出差','入职','报销','加班','补签','培训','薪资调整','离职','采购','用车','预算','招聘','设备维修','合同签署','项目立项') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '审批类型',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '详细信息',
  PRIMARY KEY (`form_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '审批表基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of approval_forms
-- ----------------------------
INSERT INTO `approval_forms` VALUES ('1', 7, 1, '生病请假', '2024-10-09 11:02:31', '已完成', '请假', '第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发第三方斯蒂芬斯蒂芬是的发发发沙发沙发');
INSERT INTO `approval_forms` VALUES ('2', 7, 1, '出差北京', '2024-10-09 13:06:13', '已完成', '出差', '湿哒哒大电视');

-- ----------------------------
-- Table structure for approval_steps
-- ----------------------------
DROP TABLE IF EXISTS `approval_steps`;
CREATE TABLE `approval_steps`  (
  `step_id` int NOT NULL AUTO_INCREMENT COMMENT '审批步骤ID',
  `form_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '审批表编号',
  `approver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批人',
  `opinion` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '审批意见',
  `approval_date` datetime NULL DEFAULT NULL COMMENT '审批日期',
  `result` enum('成功','失败') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批结果',
  PRIMARY KEY (`step_id`) USING BTREE,
  INDEX `idx_form_id`(`form_id` ASC) USING BTREE,
  CONSTRAINT `approval_steps_ibfk_1` FOREIGN KEY (`form_id`) REFERENCES `approval_forms` (`form_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '审批流程信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of approval_steps
-- ----------------------------
INSERT INTO `approval_steps` VALUES (1, '1', '8', '同意休息', '2024-10-09 11:03:58', '成功');
INSERT INTO `approval_steps` VALUES (2, '2', '8', '注意安全', '2024-10-09 13:11:10', '成功');

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `attendance_id` int NOT NULL AUTO_INCREMENT COMMENT '考勤id',
  `attendance_user_id` int NOT NULL COMMENT '考勤人id',
  `time_in` datetime NOT NULL COMMENT '上班打卡时间',
  `time_out` datetime NULL DEFAULT NULL COMMENT '下班打卡时间',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '打卡日期',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '打卡状态',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '打卡地址',
  `longitude` decimal(10, 7) NOT NULL COMMENT '经度',
  `latitude` decimal(10, 7) NOT NULL COMMENT '纬度',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '打卡类型',
  PRIMARY KEY (`attendance_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1046 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES (1, 7, '2024-10-09 14:56:22', '2024-10-09 14:56:29', '2024-10-02', '打卡失败', '碎甲', 12.0000000, 45.0000000, '出差打卡');
INSERT INTO `attendance` VALUES (1027, 7, '2024-09-23 08:40:13', '2024-09-23 17:40:14', '2024-09-23', '打卡成功', '碎甲', 122.0909000, 133.2123120, '上班打卡');
INSERT INTO `attendance` VALUES (1028, 7, '2024-09-24 08:41:52', '2024-09-24 17:42:01', '2024-09-24', '打卡成功', '碎甲', 312.0000000, 312.0000000, '上班打卡');
INSERT INTO `attendance` VALUES (1029, 7, '2024-10-01 15:03:16', NULL, '2024-10-01', '打卡成功', '碎甲', 12.0000000, 23.0000000, '上班打卡');
INSERT INTO `attendance` VALUES (1030, 8, '2024-10-12 08:22:00', '2024-10-12 17:36:08', '2024-10-12', '打卡成功', '汉街总部', 123.0000000, 124.0000000, '正常打卡');
INSERT INTO `attendance` VALUES (1031, 8, '2024-10-21 09:08:25', '2024-10-21 17:21:48', '2024-10-21', '打卡失败', '珊瑚路802号', 85.8475619, 27.8917728, '正常打卡');
INSERT INTO `attendance` VALUES (1032, 8, '2024-10-25 08:37:57', '2024-10-25 16:56:34', '2024-10-25', '打卡失败', '锦江区人民南路四段903号', 120.7407720, 74.5524700, '正常打卡');
INSERT INTO `attendance` VALUES (1033, 8, '2024-10-15 08:36:02', '2024-10-15 17:23:56', '2024-10-15', '打卡成功', '白云区小坪东路927号', 86.9242185, 12.0206626, '出差打卡');
INSERT INTO `attendance` VALUES (1034, 8, '2024-10-28 08:35:44', '2024-10-28 16:54:05', '2024-10-28', '打卡失败', '锦江区人民南路四段714号', 118.7519405, 64.1143247, '正常打卡');
INSERT INTO `attendance` VALUES (1035, 8, '2024-10-16 09:08:35', '2024-10-16 17:28:55', '2024-10-16', '打卡失败', '延庆区028县道329号', 28.7400146, 38.4047007, '出差打卡');
INSERT INTO `attendance` VALUES (1036, 8, '2024-10-14 09:07:36', '2024-10-14 17:16:57', '2024-10-14', '打卡失败', '乐丰六路363号', 87.7815349, 42.1752618, '出差打卡');
INSERT INTO `attendance` VALUES (1037, 8, '2024-10-22 08:59:47', '2024-10-22 17:09:16', '2024-10-22', '打卡成功', '罗湖区蔡屋围深南东路809号', 72.2682526, 5.1126349, '出差打卡');
INSERT INTO `attendance` VALUES (1038, 8, '2024-10-26 09:05:18', '2024-10-26 17:21:15', '2024-10-26', '打卡失败', '白云区小坪东路622号', 18.2622030, 33.7353141, '正常打卡');
INSERT INTO `attendance` VALUES (1039, 8, '2024-10-18 08:33:24', '2024-10-18 16:50:07', '2024-10-18', '打卡失败', '珊瑚路587号', 83.4691540, 7.5423038, '正常打卡');
INSERT INTO `attendance` VALUES (1040, 8, '2024-10-31 08:49:51', '2024-10-31 17:15:06', '2024-10-31', '打卡成功', '东泰五街831号', 34.9958835, 64.3126105, '出差打卡');
INSERT INTO `attendance` VALUES (1041, 8, '2024-10-23 09:05:36', '2024-10-23 17:23:39', '2024-10-23', '打卡失败', '福田区深南大道915号', 85.2858114, 87.7417074, '出差打卡');
INSERT INTO `attendance` VALUES (1042, 8, '2024-10-30 08:52:08', '2024-10-30 17:26:01', '2024-10-30', '打卡成功', '罗湖区蔡屋围深南东路50号', 92.9356805, 81.9798108, '正常打卡');
INSERT INTO `attendance` VALUES (1043, 8, '2024-10-29 08:31:36', '2024-10-29 17:14:09', '2024-10-29', '打卡成功', '浦东新区健祥路438号', 111.9670490, 4.4752440, '出差打卡');
INSERT INTO `attendance` VALUES (1044, 8, '2024-10-27 08:49:08', '2024-10-27 17:04:31', '2024-10-27', '打卡成功', '罗湖区清水河一路727号', 101.3917345, 9.0779395, '正常打卡');
INSERT INTO `attendance` VALUES (1045, 8, '2024-10-09 08:48:01', '2024-10-09 17:29:59', '2024-10-09', '打卡成功', '福田区深南大道567号', 91.9772607, 42.8246851, '正常打卡');

-- ----------------------------
-- Table structure for bluetooth
-- ----------------------------
DROP TABLE IF EXISTS `bluetooth`;
CREATE TABLE `bluetooth`  (
  `bluetooth_id` int NOT NULL AUTO_INCREMENT COMMENT '蓝牙id',
  `device_id` int NOT NULL COMMENT '蓝牙设备id',
  `bluetooth_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '蓝牙名称',
  `status` int NOT NULL DEFAULT 0 COMMENT '状态1，0',
  PRIMARY KEY (`bluetooth_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bluetooth
-- ----------------------------
INSERT INTO `bluetooth` VALUES (1, 11111, 'yigfviytgv', 1);

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business`  (
  `business_id` int NOT NULL COMMENT '出差id',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '出差地址',
  `start_time` datetime NOT NULL COMMENT '出差开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`business_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES (1, '北京', '2024-10-09 13:10:33', '2024-10-10 13:10:35');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for leave_form
-- ----------------------------
DROP TABLE IF EXISTS `leave_form`;
CREATE TABLE `leave_form`  (
  `leave_id` int NOT NULL AUTO_INCREMENT COMMENT '请假表id',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`leave_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leave_form
-- ----------------------------
INSERT INTO `leave_form` VALUES (1, '2024-10-08 11:04:53', '2024-10-09 11:04:56', NULL);

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

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `report_id` int NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `report_user_id` int NOT NULL COMMENT '报告归属人',
  `report_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日志名称',
  `type` enum('日报','周报','月报') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日志类型',
  `report_date` date NOT NULL COMMENT '日志对应时间',
  `up_date` datetime NULL DEFAULT NULL COMMENT '记录修改时间',
  `ct_date` datetime NOT NULL COMMENT '记录生成时间',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '日志文件路径',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日志内容',
  PRIMARY KEY (`report_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of report
-- ----------------------------

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
  `birthday_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `time_in` datetime NULL DEFAULT NULL COMMENT '入职时间',
  `deleted` int NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 300 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 1, 1, '梁震南', '1yeWOPS3Kd', '5938189848', '123456', '192-0151-2897', 'liangzhenn8@qq.com', 0, '2024-10-08 11:07:33', '2024-09-10 15:13:01', '42900620041203232X', '2024-09-05 11:07:28', 0);
INSERT INTO `user` VALUES (2, 2, 2, '萧璐', 'q8fX9ms3FS', '0640707652', '123456', '155-6121-6220', 'luxiao@qq.com', 0, '2024-10-09 15:57:08', NULL, '429006199809251234', NULL, 0);
INSERT INTO `user` VALUES (3, 2, 3, '何子异', '3StKs8VbER', '9917916138', '123456', '760-771-3163', 'ziyih@qq.com', 0, '2024-10-09 15:57:11', NULL, '429006200112052345', NULL, 0);
INSERT INTO `user` VALUES (4, 2, 4, 'sadas', 'sadas', 'sdad', 'sdas', 'asdas', 'asdas', 1, '2024-10-09 15:57:15', NULL, '420981200302198765', NULL, 0);
INSERT INTO `user` VALUES (5, 2, 5, '曹岚', 'EiwJyvOFW4', '6418582407', '123456', '28-460-1738', 'lancao@qq.com', 0, '2024-10-09 15:57:26', NULL, '420981199912308765', NULL, 0);
INSERT INTO `user` VALUES (6, 3, 1, '黄岚', 'SHhufLveRR', '5855972111', '123456', '760-6340-3641', 'huang12@qq.com', 1, '2024-10-09 15:57:42', NULL, '429006200506152678', NULL, 0);
INSERT INTO `user` VALUES (7, 2, 2, '涂子航', 'ssss', '20221107040', '123456', '13094295529', '3109702282@qq.com', 1, '2024-10-08 11:07:00', '2024-09-30 10:01:22', '429006200412032313', '2024-08-15 11:06:02', 0);
INSERT INTO `user` VALUES (8, 2, 3, '小尹', 'sdasdsa', '2424389790', '123456', '2313231', '231231232', 0, '2024-10-09 13:59:19', NULL, '420981200209145735', '2024-09-02 11:07:19', 0);
INSERT INTO `user` VALUES (9, 2, 3, '小刘', 'wdawfawf', '123456789', '123456', '2413412514', '415145134541345', 1, '2024-10-10 13:12:47', NULL, '429006200101012345', '2024-10-10 13:08:58', 0);
INSERT INTO `user` VALUES (10, 2, 3, '孙詩涵', 'rFsXHFbw6Q', '3650940237', '123456', '13409554007', 'shisu@gmail.com', 1, '2024-10-10 13:19:48', '2024-10-10 19:59:22', '429006200202022345', '2024-10-10 20:02:29', 0);
INSERT INTO `user` VALUES (11, 2, 2, '孔子韬', 'nIc5oScsId', '2292365197', '123456', '17055114993', 'kong609@icloud.com', 0, '2024-10-10 13:19:41', '2024-10-10 14:08:36', '420981199811113456', '2024-10-10 10:50:41', 0);
INSERT INTO `user` VALUES (12, 3, 2, '孟璐', 'hrUUWV1WeX', '7142459377', '123456', '209179825', 'meng08@gmail.com', 1, '2024-10-10 13:19:42', '2024-10-10 17:16:26', '429006200304154321', '2024-10-10 12:33:39', 0);
INSERT INTO `user` VALUES (13, 2, 4, '石秀英', 'lO9ClDLsFH', '7230554546', '123456', '18745291356', 'shixiuyi@gmail.com', 0, '2024-10-10 13:19:43', '2024-10-10 11:19:02', '429006199912312345', '2024-10-10 23:06:44', 0);
INSERT INTO `user` VALUES (14, 3, 4, '傅杰宏', 'WaHfvsKoSu', '2499060035', '123456', '17821895180', 'fujie1965@icloud.com', 0, '2024-10-10 13:19:44', '2024-10-10 12:22:54', '420981200112125432', '2024-10-10 03:29:30', 0);
INSERT INTO `user` VALUES (15, 1, 2, '丁睿', 'E95pKDEP1C', '2159543272', '123456', '17630954117', 'dingrui@icloud.com', 0, '2024-10-10 13:19:45', '2024-10-10 05:16:54', '429006200501014567', '2024-10-10 06:55:27', 0);
INSERT INTO `user` VALUES (16, 2, 3, '许子异', 'X1CeJTtqVw', '2400568755', '123456', '7552224408', 'xuz@qq.com', 0, '2024-10-10 13:19:46', '2024-10-10 00:47:00', '420981200302034567', '2024-10-10 20:38:55', 0);
INSERT INTO `user` VALUES (17, 1, 4, '杜岚', 'Pil3hWVFHU', '8343274171', '123456', '14028897654', 'du9@gmail.com', 1, '2024-10-13 19:17:10', NULL, '42050015160430350X', '2024-10-22 06:57:05', 0);
INSERT INTO `user` VALUES (18, 3, 1, '黎安琪', 'hziHivfRX1', '5059031458', '123456', '17121154167', 'lanqi53@gmail.com', 0, '2024-10-15 09:24:58', '2024-10-14 02:40:05', '420300619801123065', '2024-10-25 09:13:29', 0);
INSERT INTO `user` VALUES (19, 2, 2, '熊晓明', 'lgRZSzjpJE', '2362782888', '123456', '18675512404', 'xiongxiaoming@outlook.com', 1, '2024-10-10 04:47:10', NULL, '42070005340730514X', '2024-10-14 10:02:21', 0);
INSERT INTO `user` VALUES (20, 1, 5, '余云熙', 'QkUBeTuSkp', '9965816684', '123456', '17697578125', 'yunxiyu6@outlook.com', 1, '2024-10-12 13:23:21', NULL, '420300543307016954', '2024-10-28 09:36:15', 1);
INSERT INTO `user` VALUES (21, 1, 2, '黎震南', 'iOdKpBtyCG', '4768069124', '123456', '75543910632', 'zhennli@outlook.com', 0, '2024-10-13 08:27:15', '2024-10-17 18:12:15', '42100093041213099x', '2024-10-18 19:06:21', 0);
INSERT INTO `user` VALUES (22, 2, 4, '田晓明', '6QbnNsN1iv', '6402078993', '123456', '76904485003', 'xiati@gmail.com', 0, '2024-10-16 18:14:55', '2024-10-14 00:22:16', '420900562504301456', '2024-10-14 12:10:15', 0);
INSERT INTO `user` VALUES (23, 3, 2, '潘詩涵', 'Oc7B2kWlK7', '5424298375', '123456', '207497205', 'sp1213@outlook.com', 1, '2024-10-16 14:05:02', NULL, '420900456507131264', '2024-10-28 06:16:42', 0);
INSERT INTO `user` VALUES (24, 1, 3, '何睿', 'hN1Zw2XVab', '2496101822', '123456', '16092905117', 'ruihe@icloud.com', 0, '2024-10-10 20:45:38', '2024-10-15 13:50:03', '420200264810301342', '2024-10-17 09:45:15', 0);
INSERT INTO `user` VALUES (25, 2, 3, '周致远', 'rDnWLGjor6', '1846023948', '123456', '16862090976', 'zzhou@gmail.com', 0, '2024-10-17 09:00:10', '2024-10-15 02:18:36', '421100880509046830', '2024-10-25 00:04:03', 0);
INSERT INTO `user` VALUES (26, 2, 4, '秦晓明', 'V8CKwFq2jb', '3416924490', '123456', '18246558490', 'qinxiaoming7@qq.com', 0, '2024-10-15 09:48:58', '2024-10-11 09:59:00', '422800172811287636', '2024-10-10 01:50:15', 0);
INSERT INTO `user` VALUES (27, 3, 4, '卢嘉伦', 'H7OLn82bcd', '7835228227', '123456', '201594770', 'lujialun@icloud.com', 1, '2024-10-16 12:23:08', NULL, '42010027660425946x', '2024-10-30 08:00:59', 0);
INSERT INTO `user` VALUES (28, 3, 4, '任秀英', 'FIA5IAQeeu', '8460787294', '123456', '2130965909', 'renxiu904@gmail.com', 1, '2024-10-14 19:43:29', NULL, '42070098460709755x', '2024-10-23 19:28:06', 0);
INSERT INTO `user` VALUES (29, 2, 2, '阎子异', 'rQeF2RuHL2', '8592698351', '123456', '17217493750', 'ziyyan@outlook.com', 1, '2024-10-14 22:20:05', NULL, '420500363606171029', '2024-10-30 13:56:49', 0);
INSERT INTO `user` VALUES (30, 3, 4, '毛致远', 'brymae8Jfa', '2050120525', '123456', '16322760922', 'zhmao4@qq.com', 0, '2024-10-12 13:23:21', '2024-10-17 13:58:34', '42130011710931328X', '2024-10-29 18:01:35', 1);
INSERT INTO `user` VALUES (31, 1, 1, '向安琪', '0eN3L0E8jE', '6998215594', '123456', '16586252140', 'xiang6@icloud.com', 1, '2024-10-14 11:42:28', '2024-10-17 15:50:57', '421200309609238805', '2024-10-28 10:01:33', 0);
INSERT INTO `user` VALUES (32, 2, 4, '唐岚', 'BbMShO1LNa', '8394086470', '123456', '7691911840', 'tangla54@gmail.com', 0, '2024-10-11 19:15:31', '2024-10-13 14:43:09', '420600629510248470', '2024-10-24 11:38:30', 0);
INSERT INTO `user` VALUES (33, 2, 4, '阎宇宁', '3HERBmVl6h', '3901024271', '123456', '17002430036', 'yan5@gmail.com', 1, '2024-10-17 12:39:33', NULL, '42120046021030073X', '2024-10-23 15:49:12', 0);
INSERT INTO `user` VALUES (34, 2, 4, '魏震南', '9f7BQLc8wM', '7934297576', '123456', '18135339465', 'zwe@icloud.com', 0, '2024-10-12 13:30:42', '2024-10-12 04:13:13', '421300163509301930', '2024-10-21 01:04:39', 0);
INSERT INTO `user` VALUES (35, 2, 3, '姜睿', 'tJ8nBw0zt3', '4857479277', '123456', '282260646', 'jiangrui3@gmail.com', 1, '2024-10-13 19:55:53', NULL, '420600339910271400', '2024-10-17 11:24:47', 0);
INSERT INTO `user` VALUES (36, 3, 3, '罗致远', 'g9uBqd1xUB', '5441462525', '123456', '13457427987', 'zhl6@outlook.com', 0, '2024-10-10 19:20:19', NULL, '420300602109319964', '2024-10-28 21:49:01', 0);
INSERT INTO `user` VALUES (37, 3, 2, '韦云熙', 'iQTCbDZWQ9', '6815808561', '123456', '16424213424', 'weiyun@outlook.com', 1, '2024-10-12 18:16:04', '2024-10-10 13:56:59', '422800547312306206', '2024-10-15 18:13:06', 0);
INSERT INTO `user` VALUES (38, 2, 4, '莫睿', 'KBzkIkH5oq', '8099436400', '123456', '16527525269', 'mrui@gmail.com', 1, '2024-10-12 17:57:36', NULL, '421100106707308236', '2024-10-31 23:07:46', 0);
INSERT INTO `user` VALUES (39, 2, 3, '高杰宏', 'kaG2f529eQ', '1614011759', '123456', '1075565942', 'gaojiehong4@outlook.com', 1, '2024-10-14 19:12:43', NULL, '420300008312044610', '2024-10-15 09:28:05', 0);
INSERT INTO `user` VALUES (40, 1, 2, '韦宇宁', 'j3BmSzW3Oi', '3698018282', '123456', '19984261776', 'wyun@yahoo.com', 1, '2024-10-11 15:07:39', '2024-10-17 07:35:36', '420200011402166972', '2024-10-11 10:10:58', 0);
INSERT INTO `user` VALUES (41, 1, 4, '苏云熙', '2DHd3swYM1', '6479764317', '123456', '210354608', 'suyunxi4@gmail.com', 1, '2024-10-12 12:31:43', NULL, '420200127605223789', '2024-10-15 12:28:12', 0);
INSERT INTO `user` VALUES (42, 2, 4, '谢致远', '44QZDUvOQH', '2850347885', '123456', '7609839570', 'xzhiyuan45@gmail.com', 0, '2024-10-10 05:22:00', NULL, '421200279010018425', '2024-10-21 02:44:21', 0);
INSERT INTO `user` VALUES (43, 1, 2, '夏秀英', 'HCz0juZJkG', '5083759137', '123456', '104346573', 'xiuyingx@yahoo.com', 0, '2024-10-15 14:03:44', NULL, '420500136512312140', '2024-10-14 00:16:46', 0);
INSERT INTO `user` VALUES (44, 2, 2, '韦璐', 'hwBBzkg6HR', '9055355435', '123456', '16849321878', 'luwei@gmail.com', 0, '2024-10-10 12:24:12', NULL, '420100788103305401', '2024-10-15 23:41:09', 0);
INSERT INTO `user` VALUES (45, 2, 4, '熊岚', 'kpZxw9SPNC', '6884383641', '123456', '14368234387', 'lxiong@icloud.com', 1, '2024-10-15 07:47:27', '2024-10-15 06:49:20', '421100800409169703', '2024-10-28 07:24:06', 0);
INSERT INTO `user` VALUES (46, 3, 1, '汪云熙', 'Q4OorB19a6', '6793933605', '123456', '13454039180', 'wangyu@outlook.com', 1, '2024-10-14 12:10:05', NULL, '421200852102090791', '2024-10-14 08:54:05', 0);
INSERT INTO `user` VALUES (47, 2, 1, '陈璐', 'QJJkL79fKK', '4781147922', '123456', '2843502689', 'chl@icloud.com', 0, '2024-10-17 08:26:41', '2024-10-14 23:42:19', '421000802111302036', '2024-10-29 00:28:54', 0);
INSERT INTO `user` VALUES (48, 3, 2, '雷嘉伦', 'xYUlURTmz1', '7599216726', '123456', '7558026215', 'leijialun@gmail.com', 0, '2024-10-11 13:57:13', NULL, '421300534506309203', '2024-10-21 06:52:41', 0);
INSERT INTO `user` VALUES (49, 2, 4, '邱璐', 'zVCDR2wPLQ', '2256820285', '123456', '17069574292', 'qiu6@icloud.com', 0, '2024-10-17 16:31:54', '2024-10-11 07:20:20', '420700468008099715', '2024-10-28 12:59:24', 0);
INSERT INTO `user` VALUES (50, 3, 2, '孟詩涵', 'daiuTI1foO', '2665335417', '123456', '7600375934', 'meng1@qq.com', 1, '2024-10-14 00:38:05', '2024-10-10 14:24:29', '420700953809099103', '2024-10-30 03:34:13', 0);
INSERT INTO `user` VALUES (51, 1, 2, '孙詩涵', 'CwRxS3zC2H', '7817390963', '123456', '1071760150', 'shihan5@icloud.com', 0, '2024-10-11 23:35:35', '2024-10-17 12:07:57', '422800072810314580', '2024-10-21 07:31:41', 0);
INSERT INTO `user` VALUES (52, 2, 4, '钱子异', 'mOIPCVMdgN', '7624577405', '123456', '7691238606', 'ziyqian@gmail.com', 0, '2024-10-11 01:46:28', '2024-10-12 01:25:04', '420300038812308026', '2024-10-29 05:41:08', 0);
INSERT INTO `user` VALUES (53, 2, 3, '夏杰宏', 'kdPNczkLlu', '2544626053', '123456', '19216888999', 'jiehxi78@gmail.com', 0, '2024-10-10 22:46:12', NULL, '420500479010082151', '2024-10-29 18:17:23', 0);
INSERT INTO `user` VALUES (54, 2, 2, '史秀英', 'rbnTchsQnW', '4274187581', '123456', '13968600227', 'shixiu@yahoo.com', 1, '2024-10-17 15:01:22', '2024-10-14 11:15:05', '42030002880205237X', '2024-10-14 04:05:27', 0);
INSERT INTO `user` VALUES (55, 3, 3, '段云熙', '6rAxvdDFiz', '3872844675', '123456', '16627891221', 'duanyun@gmail.com', 1, '2024-10-17 20:19:22', '2024-10-17 20:21:02', '422800698202316469', '2024-10-21 03:19:06', 0);
INSERT INTO `user` VALUES (56, 1, 5, '傅云熙', 'RBeBlvYHcn', '2515530487', '123456', '15533416795', 'fuyunxi74@outlook.com', 0, '2024-10-10 18:25:51', NULL, '421200986908028502', '2024-10-21 01:08:29', 0);
INSERT INTO `user` VALUES (57, 2, 1, '叶安琪', 'TN3Y4uAt5U', '2903331331', '123456', '2101810575', 'yeanqi7@qq.com', 1, '2024-10-10 10:17:45', '2024-10-12 18:21:38', '420600573804082921', '2024-10-25 10:04:54', 0);
INSERT INTO `user` VALUES (58, 2, 4, '苏杰宏', 'ZGZfxApqMY', '6885937112', '123456', '17556143484', 'su213@gmail.com', 1, '2024-10-13 05:46:51', '2024-10-10 18:13:51', '420800326710257373', '2024-10-11 22:53:39', 0);
INSERT INTO `user` VALUES (59, 2, 5, '傅睿', 'da9F1kqsFi', '6965712559', '123456', '16644332670', 'rfu@icloud.com', 1, '2024-10-17 13:34:16', NULL, '42120065990601865x', '2024-10-11 08:39:35', 0);
INSERT INTO `user` VALUES (60, 3, 5, '廖云熙', 'VQd7237eg8', '2090481468', '123456', '1096573861', 'liaoyunxi3@gmail.com', 1, '2024-10-13 01:51:24', '2024-10-17 23:49:13', '421000088406195442', '2024-10-25 23:28:03', 0);
INSERT INTO `user` VALUES (61, 3, 5, '李震南', '222ohO5tgG', '4363575198', '123456', '2068918654', 'lizhen@outlook.com', 0, '2024-10-11 01:12:58', NULL, '420300473312319862', '2024-10-15 23:00:50', 0);
INSERT INTO `user` VALUES (62, 1, 3, '李安琪', 'cm5Fa4UU8R', '6326428084', '123456', '13066768697', 'anqi9@icloud.com', 1, '2024-10-16 18:42:57', '2024-10-13 06:51:54', '420200286701278344', '2024-10-18 11:39:47', 0);
INSERT INTO `user` VALUES (63, 2, 4, '贾詩涵', '1XlORSzoGC', '1253586512', '123456', '76984980056', 'jia226@gmail.com', 1, '2024-10-14 08:33:47', '2024-10-15 03:30:41', '421000848810259656', '2024-10-15 05:41:21', 0);
INSERT INTO `user` VALUES (64, 2, 2, '丁宇宁', 'iLGguGBFjf', '6402266811', '123456', '13693330865', 'yuningding@outlook.com', 1, '2024-10-11 19:11:49', NULL, '421200293308316067', '2024-10-28 18:12:30', 0);
INSERT INTO `user` VALUES (65, 2, 3, '杨杰宏', 'MxgRP0YN3Q', '7272198583', '123456', '13205353367', 'yanjiehong@gmail.com', 1, '2024-10-13 10:34:04', '2024-10-15 13:13:05', '42090017361031476X', '2024-10-14 22:30:00', 0);
INSERT INTO `user` VALUES (66, 2, 4, '林致远', 'fpJXYIZNAH', '8578617366', '123456', '19853189114', 'linzhi@outlook.com', 0, '2024-10-17 07:10:39', '2024-10-16 15:36:32', '42070096950324929x', '2024-10-25 20:23:34', 0);
INSERT INTO `user` VALUES (67, 2, 2, '程詩涵', 'tZLhPGy69L', '5343506852', '123456', '13938772011', 'shihacheng@gmail.com', 1, '2024-10-14 23:13:29', NULL, '421000127911220964', '2024-10-14 03:47:36', 0);
INSERT INTO `user` VALUES (68, 2, 2, '钱子韬', '2Ql8Og6o0T', '7587995732', '123456', '2844837780', 'zitqian55@icloud.com', 0, '2024-10-10 14:13:20', '2024-10-11 20:10:05', '42010005221207485X', '2024-10-24 21:01:18', 0);
INSERT INTO `user` VALUES (69, 1, 3, '汪岚', 'Obp61iD9TL', '7863430134', '123456', '14322820899', 'lanwan99@outlook.com', 0, '2024-10-17 14:45:46', NULL, '422800986812178843', '2024-10-14 14:08:48', 0);
INSERT INTO `user` VALUES (70, 2, 1, '段晓明', 'yFoIzzYtnj', '7437109855', '123456', '7558810514', 'xiaomduan10@icloud.com', 1, '2024-10-17 12:14:32', '2024-10-17 15:19:36', '42100052950930639X', '2024-10-10 12:14:38', 0);
INSERT INTO `user` VALUES (71, 1, 5, '袁子韬', 'imh56gjUra', '1995620425', '123456', '76999745383', 'zyua1206@yahoo.com', 0, '2024-10-11 06:23:10', NULL, '421300624604018703', '2024-10-25 09:18:20', 0);
INSERT INTO `user` VALUES (72, 1, 4, '潘宇宁', 'HnMT7cEwMH', '1376952885', '123456', '75529201754', 'pyuning@gmail.com', 1, '2024-10-11 18:19:15', NULL, '420700554011281840', '2024-10-23 20:44:46', 0);
INSERT INTO `user` VALUES (73, 2, 2, '孙晓明', '4wpnOYCLKx', '1322419277', '123456', '7551095488', 'sunxiaoming2@gmail.com', 0, '2024-10-16 07:30:40', '2024-10-16 17:32:40', '422800272406039819', '2024-10-24 14:20:26', 0);
INSERT INTO `user` VALUES (74, 2, 5, '贺璐', 'xx44Kqizqs', '9189106168', '123456', '13684350160', 'helu@gmail.com', 0, '2024-10-14 10:23:43', '2024-10-13 15:12:15', '420500077510204934', '2024-10-31 03:05:13', 0);
INSERT INTO `user` VALUES (75, 2, 5, '袁睿', 'kCl5bPPSx6', '7618099436', '123456', '18955910210', 'yuan1968@gmail.com', 1, '2024-10-16 08:36:45', '2024-10-11 19:16:59', '421000667712311773', '2024-10-14 01:53:18', 0);
INSERT INTO `user` VALUES (76, 3, 4, '杨云熙', 'Bn3611q0g9', '4079208055', '123456', '18002593770', 'yuy1@icloud.com', 0, '2024-10-13 16:42:09', NULL, '421200172502075395', '2024-10-24 22:45:52', 0);
INSERT INTO `user` VALUES (77, 3, 5, '杨致远', 'wHhvvCHn1v', '8730215536', '123456', '100981943', 'yangzhiyuan113@yahoo.com', 0, '2024-10-17 20:37:54', '2024-10-13 09:54:52', '420300343411304964', '2024-10-22 10:03:46', 0);
INSERT INTO `user` VALUES (78, 3, 2, '吕璐', 'SObAl2hYCd', '2263251291', '123456', '19657265286', 'lu1963@icloud.com', 0, '2024-10-17 10:50:55', '2024-10-16 11:57:42', '420200174106312031', '2024-10-24 03:23:39', 0);
INSERT INTO `user` VALUES (79, 3, 4, '蔡子异', 'EAhxm2Dv82', '5749160494', '123456', '17973043191', 'cai426@qq.com', 0, '2024-10-10 12:27:35', NULL, '42020010211231750X', '2024-10-14 20:11:22', 0);
INSERT INTO `user` VALUES (80, 3, 1, '王璐', 'U9nTaYDqpx', '3903319326', '123456', '2196629521', 'wanglu@gmail.com', 0, '2024-10-10 22:37:50', '2024-10-15 12:53:24', '422800008010179140', '2024-10-25 11:24:44', 0);
INSERT INTO `user` VALUES (81, 1, 5, '曹子异', 't6wo68de4M', '4375232144', '123456', '16160799737', 'ziyi6@gmail.com', 1, '2024-10-17 00:34:21', NULL, '420100848505315863', '2024-10-16 04:30:47', 0);
INSERT INTO `user` VALUES (82, 2, 4, '汪子韬', 'abSBA5iobe', '9213889003', '123456', '75571385270', 'wang79@yahoo.com', 1, '2024-10-13 01:45:43', '2024-10-17 13:27:50', '42020044191109173x', '2024-10-25 22:30:04', 0);
INSERT INTO `user` VALUES (83, 2, 5, '丁岚', 'h6zfoPDVRW', '6376026949', '123456', '19867703175', 'dinglan@icloud.com', 0, '2024-10-17 02:48:12', NULL, '42030026750615527x', '2024-10-23 12:04:42', 0);
INSERT INTO `user` VALUES (84, 1, 2, '莫杰宏', 'UiD1NPnyy4', '7408381302', '123456', '1040751900', 'jiemo02@outlook.com', 1, '2024-10-11 08:06:22', '2024-10-13 10:52:10', '421300605708214169', '2024-10-15 06:42:38', 0);
INSERT INTO `user` VALUES (85, 2, 4, '张秀英', '9GtgAiEpPA', '2212444339', '123456', '289475197', 'zhaxiuy@qq.com', 1, '2024-10-13 17:51:00', NULL, '420200724101093712', '2024-10-30 05:33:20', 0);
INSERT INTO `user` VALUES (86, 1, 3, '蒋晓明', 'soSeNQUDfD', '1910751003', '123456', '13258573290', 'xjiang@outlook.com', 1, '2024-10-17 21:52:48', '2024-10-10 00:01:14', '420200853403108290', '2024-10-18 18:39:26', 0);
INSERT INTO `user` VALUES (87, 3, 2, '傅杰宏', '83CRG4cHW2', '8555022556', '123456', '7555972709', 'fujiehong@gmail.com', 0, '2024-10-13 02:13:37', NULL, '421200263803058678', '2024-10-25 04:43:50', 0);
INSERT INTO `user` VALUES (88, 1, 1, '周岚', 'x9HAeB8anE', '4992522126', '123456', '13168746272', 'zhou76@icloud.com', 0, '2024-10-11 16:22:28', NULL, '421200945910023273', '2024-10-25 22:14:18', 0);
INSERT INTO `user` VALUES (89, 1, 3, '袁子韬', '9KovQ5WmKD', '3234739191', '123456', '17606953788', 'yuanzitao503@qq.com', 0, '2024-10-16 19:20:25', '2024-10-17 09:03:10', '420900337611269579', '2024-10-18 09:06:07', 0);
INSERT INTO `user` VALUES (90, 2, 2, '罗子异', 'CweSFSwVtg', '6369555555', '123456', '7694933726', 'luo99@gmail.com', 1, '2024-10-16 09:18:55', '2024-10-10 10:46:02', '420700387207315869', '2024-10-21 07:25:50', 0);
INSERT INTO `user` VALUES (91, 1, 4, '谢岚', 'wKC2k57LiH', '5939559401', '123456', '13609859996', 'lxi@gmail.com', 1, '2024-10-11 23:07:06', NULL, '420300663804077531', '2024-10-21 01:47:03', 0);
INSERT INTO `user` VALUES (92, 2, 4, '陈岚', 'RVZgsqQ80E', '3489623602', '123456', '7694605948', 'chelan@gmail.com', 1, '2024-10-13 09:51:55', NULL, '420500104506149482', '2024-10-21 15:02:13', 0);
INSERT INTO `user` VALUES (93, 2, 3, '何岚', '76K9XylWYx', '9858382376', '123456', '75555997291', 'lanhe@gmail.com', 0, '2024-10-11 13:45:12', '2024-10-11 16:08:36', '420900326012304571', '2024-10-25 23:24:45', 0);
INSERT INTO `user` VALUES (94, 3, 2, '严詩涵', 'qTfXc9l0SB', '7924939590', '123456', '101332516', 'shihan1972@gmail.com', 1, '2024-10-16 01:44:31', '2024-10-13 21:26:59', '420600547006064467', '2024-10-11 12:02:31', 0);
INSERT INTO `user` VALUES (95, 2, 2, '叶安琪', 'OfF2fq58s5', '4753221785', '123456', '76006057378', 'anqiye414@gmail.com', 1, '2024-10-17 03:36:14', NULL, '420300264007037582', '2024-10-24 17:41:19', 0);
INSERT INTO `user` VALUES (96, 1, 3, '曹宇宁', '96Z96Xo4uy', '8053767863', '123456', '15171166889', 'yuca@gmail.com', 1, '2024-10-14 09:47:11', '2024-10-17 22:57:02', '420300397510148036', '2024-10-17 21:43:53', 0);
INSERT INTO `user` VALUES (97, 2, 3, '贾秀英', 'HqtoYzOZG7', '8479159262', '123456', '2883331488', 'jiaxiu1973@outlook.com', 0, '2024-10-14 20:05:01', NULL, '421000860708252175', '2024-10-15 04:54:52', 0);
INSERT INTO `user` VALUES (98, 3, 3, '许睿', 'imzPtzxayM', '8163812606', '123456', '14317140808', 'xur@icloud.com', 1, '2024-10-11 15:17:50', '2024-10-16 03:05:37', '42070073141225826X', '2024-10-21 11:04:59', 0);
INSERT INTO `user` VALUES (99, 2, 3, '毛宇宁', 'jOMtdrsbv5', '1999632977', '123456', '201421331', 'yuningmao@yahoo.com', 1, '2024-10-17 07:51:41', NULL, '421200217005059912', '2024-10-28 14:57:26', 0);
INSERT INTO `user` VALUES (100, 2, 1, '邓宇宁', 'fDDfymlxTH', '1895929904', '123456', '1026832558', 'denyuni@icloud.com', 1, '2024-10-11 17:36:25', '2024-10-15 05:22:04', '420700409803107248', '2024-10-25 10:48:53', 0);
INSERT INTO `user` VALUES (101, 2, 3, '郑子韬', 'iLJ44YycKM', '3753207602', '123456', '13553077023', 'zhezitao1981@yahoo.com', 0, '2024-10-15 13:01:00', '2024-10-11 12:07:51', '420600586911248896', '2024-10-29 00:58:38', 0);
INSERT INTO `user` VALUES (102, 2, 2, '赵璐', 'xSUWrXBl90', '2994111524', '123456', '102725708', 'luz10@outlook.com', 0, '2024-10-12 02:21:58', NULL, '420100393905314689', '2024-10-21 15:40:55', 0);
INSERT INTO `user` VALUES (103, 2, 4, '夏致远', 'beHuZnOH40', '3383485109', '123456', '7691877527', 'xiazhiyuan@outlook.com', 1, '2024-10-12 16:40:44', '2024-10-17 02:29:36', '421300673212277109', '2024-10-21 05:09:28', 0);
INSERT INTO `user` VALUES (104, 1, 3, '贺璐', 'SpBw1F6IPT', '7490363228', '123456', '15726265152', 'helu4@qq.com', 1, '2024-10-10 08:05:15', NULL, '421000545111306176', '2024-10-23 17:31:53', 0);
INSERT INTO `user` VALUES (105, 1, 2, '郑宇宁', 'uqRDmjC6As', '1841083668', '123456', '15339031216', 'zhengy@outlook.com', 1, '2024-10-11 02:00:18', NULL, '421100339710247827', '2024-10-14 06:12:45', 0);
INSERT INTO `user` VALUES (106, 3, 4, '姚嘉伦', 'VVUngLppts', '4156869797', '123456', '2824660549', 'jy9@outlook.com', 1, '2024-10-10 06:27:35', '2024-10-13 03:07:40', '420900893411301957', '2024-10-17 04:18:58', 0);
INSERT INTO `user` VALUES (107, 2, 1, '龚杰宏', '05cZcbtroq', '1422022512', '123456', '13854980532', 'jgong@outlook.com', 1, '2024-10-17 08:42:27', '2024-10-12 20:11:23', '42050023670419663X', '2024-10-25 08:12:26', 0);
INSERT INTO `user` VALUES (108, 3, 2, '徐睿', 'AJkn3x40TJ', '9373464973', '123456', '14619557704', 'xurui65@gmail.com', 0, '2024-10-17 10:08:34', '2024-10-16 19:46:40', '420500692511042630', '2024-10-17 17:30:29', 0);
INSERT INTO `user` VALUES (109, 3, 5, '陆安琪', 'UMgYItenz5', '3102844819', '123456', '7551310016', 'lu122@icloud.com', 0, '2024-10-15 22:26:44', '2024-10-16 09:31:14', '421000664812019465', '2024-10-21 11:27:24', 0);
INSERT INTO `user` VALUES (110, 2, 1, '戴璐', 'gfzaWI3uXa', '7436557393', '123456', '76905573696', 'dailu9@qq.com', 0, '2024-10-10 14:09:52', '2024-10-15 01:18:05', '420300926211055049', '2024-10-25 22:10:26', 0);
INSERT INTO `user` VALUES (111, 1, 2, '于嘉伦', 'YY8zntackg', '5160117927', '123456', '17033441114', 'jialun4@icloud.com', 1, '2024-10-14 22:45:34', NULL, '421200990505118851', '2024-10-22 05:50:14', 0);
INSERT INTO `user` VALUES (112, 2, 4, '方晓明', 'jVih5BEhnV', '3893366860', '123456', '18410447609', 'xif1230@icloud.com', 1, '2024-10-15 23:29:52', '2024-10-17 07:30:03', '42110031411027933X', '2024-10-22 05:06:23', 0);
INSERT INTO `user` VALUES (113, 2, 3, '钱安琪', 'WyrfO4kd6N', '8500533562', '123456', '15968295812', 'qia@gmail.com', 1, '2024-10-13 18:09:20', '2024-10-10 04:13:22', '420800570712316564', '2024-10-10 05:40:16', 0);
INSERT INTO `user` VALUES (114, 2, 4, '陆震南', 'xXN6m6fX0e', '2933217310', '123456', '18073898044', 'zhenlu622@yahoo.com', 0, '2024-10-16 23:38:22', '2024-10-12 17:40:46', '421300457811217448', '2024-10-30 13:17:18', 0);
INSERT INTO `user` VALUES (115, 2, 3, '武致远', 'jcEeSVYhQS', '2839650022', '123456', '2130497999', 'wzhiyu97@outlook.com', 1, '2024-10-14 06:07:39', '2024-10-15 08:05:39', '420700669612102664', '2024-10-25 21:23:09', 0);
INSERT INTO `user` VALUES (116, 2, 4, '任宇宁', 'xrXdrbta4R', '2703434991', '123456', '200839437', 'renyu@gmail.com', 0, '2024-10-16 21:29:48', '2024-10-17 04:31:04', '422800157910196795', '2024-10-29 11:24:30', 0);
INSERT INTO `user` VALUES (200, 2, 4, '朱致远', 'jUK5e1cDXq', '3512612404', '123456', '2159365810', 'zzhiyuan@icloud.com', 1, '2024-10-14 09:28:48', NULL, '420100967711042854', '2024-10-21 02:04:53', 0);
INSERT INTO `user` VALUES (201, 2, 1, '方岚', 'TTRjhtBCCZ', '9361896445', '123456', '19274991732', 'fanglan@gmail.com', 1, '2024-10-13 06:02:26', NULL, '420500987012070811', '2024-10-23 04:31:18', 0);
INSERT INTO `user` VALUES (202, 3, 3, '罗子异', 'yoC0sNeY7w', '6226938094', '123456', '18933644737', 'zl5@icloud.com', 1, '2024-10-17 09:12:43', '2024-10-11 03:38:53', '420700964512023904', '2024-10-23 17:49:17', 0);
INSERT INTO `user` VALUES (203, 2, 4, '廖云熙', 'KgZeV1eGAJ', '6241760456', '123456', '7557937585', 'liao1@yahoo.com', 0, '2024-10-15 16:34:36', NULL, '420200973611271149', '2024-10-22 02:39:41', 0);
INSERT INTO `user` VALUES (204, 2, 2, '孙嘉伦', '3IWLMJD0IQ', '1631024108', '123456', '18243396754', 'sun922@outlook.com', 1, '2024-10-17 13:16:58', '2024-10-15 03:15:54', '420200668810319716', '2024-10-21 08:28:16', 0);
INSERT INTO `user` VALUES (205, 1, 4, '余詩涵', 'I0Y9kFEumd', '9796867558', '123456', '17636066432', 'yus@gmail.com', 0, '2024-10-15 06:05:51', '2024-10-11 00:55:35', '421000541112178211', '2024-10-14 18:11:45', 0);
INSERT INTO `user` VALUES (206, 2, 4, '熊杰宏', 'etMjxc1KXN', '4420512786', '123456', '14462013127', 'xiojiehong1954@outlook.com', 0, '2024-10-12 03:17:31', '2024-10-10 13:13:40', '42010007031209960x', '2024-10-24 06:08:00', 0);
INSERT INTO `user` VALUES (207, 2, 2, '蒋震南', 'E2ffKVkFvv', '5315890592', '123456', '218144598', 'zhennj513@icloud.com', 0, '2024-10-14 01:32:14', NULL, '42110047870514839X', '2024-10-18 11:46:27', 0);
INSERT INTO `user` VALUES (208, 2, 1, '蒋宇宁', 'AGMNJn0PaT', '6656430025', '123456', '17427995743', 'jiang55@gmail.com', 1, '2024-10-15 05:28:05', NULL, '420300032801208684', '2024-10-11 21:19:11', 0);
INSERT INTO `user` VALUES (209, 1, 3, '任睿', 'tHBsqbC85a', '8170921877', '123456', '15240762928', 'renrui@gmail.com', 0, '2024-10-14 08:01:59', NULL, '420800997705047383', '2024-10-10 19:14:26', 0);
INSERT INTO `user` VALUES (210, 3, 4, '叶岚', 'vPcxRUwCiP', '4572380150', '123456', '15921121193', 'lany613@yahoo.com', 0, '2024-10-16 14:13:19', NULL, '421100803809219864', '2024-10-30 06:55:30', 0);
INSERT INTO `user` VALUES (211, 1, 2, '孟宇宁', '6rWqv4ZVuR', '2110033829', '123456', '2175293924', 'yumeng1110@icloud.com', 1, '2024-10-16 11:39:46', '2024-10-12 10:29:49', '420200171501311167', '2024-10-25 01:30:59', 0);
INSERT INTO `user` VALUES (212, 2, 4, '丁子韬', 'LtN35kszxY', '8985826063', '123456', '19812589381', 'dingzitao@gmail.com', 0, '2024-10-13 08:30:22', NULL, '421100046411141234', '2024-10-16 11:59:21', 0);
INSERT INTO `user` VALUES (213, 1, 3, '萧杰宏', 'JAXc1wVBm8', '3165647264', '123456', '103669989', 'jiexiao@outlook.com', 0, '2024-10-11 01:24:37', '2024-10-16 01:48:32', '42130057741030407X', '2024-10-28 09:09:33', 0);
INSERT INTO `user` VALUES (214, 3, 2, '罗致远', 'BdMGlF0y3a', '9501445983', '123456', '14993127162', 'lzhiyuan514@icloud.com', 0, '2024-10-13 16:49:15', NULL, '420300119211312921', '2024-10-10 19:47:14', 0);
INSERT INTO `user` VALUES (215, 2, 2, '方嘉伦', 'rgWaSgipJK', '5983545320', '123456', '18275651817', 'jialun115@outlook.com', 0, '2024-10-10 00:42:27', NULL, '422800912409081879', '2024-10-14 09:23:23', 0);
INSERT INTO `user` VALUES (216, 2, 1, '崔璐', 'KBl0ziqYd8', '4986430868', '123456', '18318585997', 'culu@icloud.com', 1, '2024-10-16 13:18:04', NULL, '420600483302051979', '2024-10-16 11:44:25', 0);
INSERT INTO `user` VALUES (217, 1, 4, '贺子韬', 'aQXYtDxSRL', '1357839688', '123456', '2150000143', 'hezitao@yahoo.com', 1, '2024-10-10 05:31:55', NULL, '421100409010317140', '2024-10-21 08:36:29', 0);
INSERT INTO `user` VALUES (218, 2, 2, '戴秀英', 'cl7s2NtYXy', '7399848614', '123456', '13429011207', 'dai1980@outlook.com', 0, '2024-10-17 05:18:01', '2024-10-11 03:14:38', '421200758008073048', '2024-10-17 03:13:18', 0);
INSERT INTO `user` VALUES (219, 2, 4, '孟子异', 'qqNWOAKVyA', '2464306551', '123456', '15901856526', 'mengziyi@icloud.com', 0, '2024-10-11 06:45:19', '2024-10-11 08:10:32', '421200110907066129', '2024-10-31 15:07:49', 0);
INSERT INTO `user` VALUES (220, 1, 3, '魏云熙', 'XtOzwIvEIC', '3340733198', '123456', '7553321490', 'ywei@icloud.com', 0, '2024-10-16 23:45:37', '2024-10-13 07:37:01', '420600921310063734', '2024-10-21 09:32:42', 0);
INSERT INTO `user` VALUES (221, 2, 2, '陆晓明', 'fmzRoMAlDK', '8722587604', '123456', '17514254372', 'xiaomingl@outlook.com', 0, '2024-10-12 07:20:52', NULL, '421100471410208822', '2024-10-23 22:38:33', 0);
INSERT INTO `user` VALUES (222, 1, 3, '薛璐', 'uZohspTmcq', '7940036334', '123456', '76043646592', 'luxue@qq.com', 1, '2024-10-16 12:39:49', NULL, '420600307109304815', '2024-10-17 20:49:40', 0);
INSERT INTO `user` VALUES (223, 1, 3, '常岚', 'lXafOp7mhK', '4860640291', '123456', '17663694491', 'lanchan@gmail.com', 0, '2024-10-17 23:06:43', '2024-10-15 11:00:46', '42030062131007323X', '2024-10-29 12:55:01', 0);
INSERT INTO `user` VALUES (224, 2, 4, '邱璐', 'W5hHfZrAnG', '1874395624', '123456', '19746237834', 'qiulu@gmail.com', 1, '2024-10-13 14:21:36', NULL, '42280070340331547x', '2024-10-22 04:46:57', 0);
INSERT INTO `user` VALUES (225, 2, 3, '金璐', '8LCbi5LgMa', '1503581763', '123456', '216903303', 'jinlu1@yahoo.com', 0, '2024-10-17 06:25:31', NULL, '420700977612289654', '2024-10-25 06:04:43', 0);
INSERT INTO `user` VALUES (226, 2, 2, '韩宇宁', 'OZkorUXTJ3', '6079598092', '123456', '2031105333', 'yuning00@outlook.com', 1, '2024-10-15 19:01:28', '2024-10-13 03:46:11', '420200695406085003', '2024-10-15 04:22:54', 0);
INSERT INTO `user` VALUES (227, 2, 4, '贾璐', 'zwi5yq56on', '2145902554', '123456', '13288429363', 'jialu@gmail.com', 0, '2024-10-17 20:52:05', '2024-10-10 20:36:13', '422800382907303956', '2024-10-29 16:02:45', 0);
INSERT INTO `user` VALUES (228, 1, 3, '陆云熙', 'F8QpSu38mR', '9166244721', '123456', '109408948', 'yunlu325@icloud.com', 0, '2024-10-14 02:30:55', NULL, '42130081090216509x', '2024-10-17 08:24:26', 0);
INSERT INTO `user` VALUES (229, 3, 4, '钱杰宏', 'g70ln2xQqP', '6316670037', '123456', '100145301', 'jiehoqian@gmail.com', 1, '2024-10-12 01:27:31', NULL, '420700206303090116', '2024-10-21 14:06:59', 0);
INSERT INTO `user` VALUES (230, 2, 4, '邱震南', 'OacJEjjGy6', '8280802820', '123456', '76990015685', 'zhqiu4@gmail.com', 1, '2024-10-12 19:55:19', '2024-10-13 16:55:32', '42010004121127734X', '2024-10-31 06:57:49', 0);
INSERT INTO `user` VALUES (231, 3, 2, '范杰宏', '3JNRWixeax', '1850794193', '123456', '17548770759', 'fanjieh@outlook.com', 1, '2024-10-15 14:27:44', NULL, '42010070720529106x', '2024-10-28 09:32:21', 0);
INSERT INTO `user` VALUES (232, 3, 2, '冯子异', 'dokMJXwOol', '7860898292', '123456', '18749468653', 'fengziyi6@outlook.com', 0, '2024-10-15 04:42:33', NULL, '421000893811054106', '2024-10-14 17:46:01', 0);
INSERT INTO `user` VALUES (233, 2, 4, '刘子异', 'nHLi94a4fS', '5632524146', '123456', '203712348', 'liu8@outlook.com', 1, '2024-10-13 02:38:56', '2024-10-10 08:20:49', '420600052812319191', '2024-10-29 02:54:18', 0);
INSERT INTO `user` VALUES (234, 1, 3, '莫岚', 'OTDgzJ49L0', '6363358374', '123456', '13800563582', 'mola@gmail.com', 0, '2024-10-11 17:24:53', '2024-10-10 02:37:04', '421000649010314659', '2024-10-21 11:16:47', 0);
INSERT INTO `user` VALUES (235, 3, 4, '向詩涵', 'JDR4kefbsA', '6421577862', '123456', '13961408498', 'shixiang@qq.com', 1, '2024-10-10 11:48:11', NULL, '420900044301033343', '2024-10-21 23:24:57', 0);
INSERT INTO `user` VALUES (236, 2, 2, '曹秀英', 'bZqNIre9YK', '7386926642', '123456', '17679169971', 'xiuying44@gmail.com', 1, '2024-10-16 23:13:01', '2024-10-17 20:54:38', '421000827207032316', '2024-10-14 23:31:50', 0);
INSERT INTO `user` VALUES (237, 2, 3, '黎子异', 'T6PnkXVdm5', '6495269578', '123456', '18302075658', 'liz58@gmail.com', 1, '2024-10-17 19:19:47', NULL, '42050073561102450X', '2024-10-16 19:09:49', 0);
INSERT INTO `user` VALUES (238, 2, 4, '朱秀英', 'J896MZ8Dnf', '9183979521', '123456', '75550883537', 'zxiuying4@gmail.com', 1, '2024-10-10 10:40:32', '2024-10-15 18:29:19', '420600202812233958', '2024-10-25 13:51:59', 0);
INSERT INTO `user` VALUES (239, 1, 2, '陶嘉伦', '4KF6YrN66R', '7228544926', '123456', '7556563130', 'taojialun@gmail.com', 1, '2024-10-12 04:03:15', '2024-10-15 01:28:58', '420600263906142306', '2024-10-21 16:57:40', 0);
INSERT INTO `user` VALUES (240, 2, 4, '韦晓明', 'leFw7KrzGp', '5767759868', '123456', '2117736327', 'xiaoming1022@yahoo.com', 1, '2024-10-11 23:38:49', NULL, '420300973303313259', '2024-10-10 16:23:46', 0);
INSERT INTO `user` VALUES (241, 2, 3, '黄致远', 'MhIVWzA3qg', '7199288983', '123456', '19149158951', 'huang9@gmail.com', 1, '2024-10-17 12:15:16', NULL, '42130011611107322x', '2024-10-11 21:09:05', 0);
INSERT INTO `user` VALUES (242, 3, 2, '孟子异', 'j7DA0huzpo', '4430937897', '123456', '17362667901', 'mengz@icloud.com', 1, '2024-10-16 17:31:32', '2024-10-14 23:47:29', '42010062290101378x', '2024-10-15 22:28:16', 0);
INSERT INTO `user` VALUES (243, 3, 1, '罗子异', 's822LFNcZH', '8403459618', '123456', '15637216116', 'luoz12@gmail.com', 1, '2024-10-10 05:20:57', NULL, '420600749107316396', '2024-10-25 03:28:20', 0);
INSERT INTO `user` VALUES (244, 1, 2, '余杰宏', 'b7CaX9dMrl', '5990310996', '123456', '76021100000', 'yujiehong@icloud.com', 1, '2024-10-17 13:04:19', NULL, '420600966407254623', '2024-10-23 18:12:38', 0);
INSERT INTO `user` VALUES (245, 1, 3, '王云熙', 'SnW9gfamTm', '7563066356', '123456', '107080687', 'wangyunxi9@gmail.com', 1, '2024-10-10 08:19:44', '2024-10-16 12:42:43', '421200586912043710', '2024-10-10 12:30:51', 0);
INSERT INTO `user` VALUES (246, 2, 4, '袁子韬', 'MXxVqYfNqe', '6052633018', '123456', '76982987899', 'yuanzitao@gmail.com', 1, '2024-10-16 09:25:49', NULL, '420300719309175613', '2024-10-31 02:25:56', 0);
INSERT INTO `user` VALUES (247, 1, 4, '于璐', 'gdGNW2g4zf', '2586198217', '123456', '7692537047', 'luyu@gmail.com', 0, '2024-10-14 21:29:23', NULL, '422800663611024843', '2024-10-14 20:32:22', 0);
INSERT INTO `user` VALUES (248, 2, 2, '常晓明', 'od712PqjSC', '7957811949', '123456', '14171001208', 'changx707@qq.com', 0, '2024-10-16 21:50:04', NULL, '420300845905016113', '2024-10-14 19:04:23', 0);
INSERT INTO `user` VALUES (249, 1, 4, '常宇宁', 'kC3Vf1McEH', '9624856364', '123456', '16484997852', 'yuningchang@outlook.com', 1, '2024-10-10 18:41:02', '2024-10-10 19:38:52', '420300172411015620', '2024-10-17 14:19:42', 0);
INSERT INTO `user` VALUES (250, 3, 2, '顾杰宏', 'XiaHv4cEYJ', '4949078303', '123456', '17865936401', 'gujiehong1006@outlook.com', 0, '2024-10-14 21:19:31', '2024-10-15 12:26:15', '421100732704233236', '2024-10-11 02:01:19', 0);
INSERT INTO `user` VALUES (251, 1, 3, '陶睿', '88uW6DZTFe', '4267448622', '123456', '13327294535', 'taoru@qq.com', 1, '2024-10-11 22:46:45', NULL, '422800881704040245', '2024-10-23 12:14:27', 0);
INSERT INTO `user` VALUES (252, 2, 5, '冯璐', '4AYcqRfxi7', '2350330925', '123456', '13668875580', 'fengl1951@gmail.com', 1, '2024-10-11 19:16:18', NULL, '420500075408314505', '2024-10-29 21:07:35', 0);
INSERT INTO `user` VALUES (253, 3, 2, '卢震南', 'aDTZBGcEp0', '5303875113', '123456', '76034636255', 'zhennan68@outlook.com', 1, '2024-10-14 10:30:08', NULL, '420900648901058449', '2024-10-16 21:46:09', 0);
INSERT INTO `user` VALUES (254, 2, 2, '杨詩涵', 'f1g9YRwlNT', '3761007106', '123456', '17712584589', 'shihay46@gmail.com', 0, '2024-10-13 21:51:16', '2024-10-12 16:28:07', '420900803606027613', '2024-10-21 12:40:24', 0);
INSERT INTO `user` VALUES (255, 2, 1, '向璐', 'fSCMSOh4d4', '9523370789', '123456', '16840070755', 'xianlu@outlook.com', 1, '2024-10-14 13:22:13', '2024-10-14 05:01:09', '42280089251130127X', '2024-10-22 07:06:44', 0);
INSERT INTO `user` VALUES (256, 2, 2, '贺安琪', 'qpwhFTlbPE', '4961989992', '123456', '16931150058', 'anqihe@gmail.com', 1, '2024-10-15 05:45:16', '2024-10-14 04:32:32', '420900223710257399', '2024-10-16 09:10:11', 0);
INSERT INTO `user` VALUES (257, 2, 3, '宋子异', 'B1dBdfnEuh', '6645458944', '123456', '7602405304', 'songziyi@gmail.com', 1, '2024-10-14 13:49:05', NULL, '42010064761228842x', '2024-10-24 03:10:56', 0);
INSERT INTO `user` VALUES (258, 2, 3, '王致远', 'yyqf5oUhWk', '2096984124', '123456', '19410307622', 'wangzhiyuan@outlook.com', 1, '2024-10-10 14:48:02', NULL, '42010098351120312X', '2024-10-16 23:21:06', 0);
INSERT INTO `user` VALUES (259, 2, 2, '赵晓明', '2R0diK3yJY', '4748783443', '123456', '15966197664', 'xiaomingzha9@gmail.com', 0, '2024-10-10 12:56:04', NULL, '420800756205172226', '2024-10-16 14:05:12', 0);
INSERT INTO `user` VALUES (260, 2, 2, '范杰宏', 'bVcUvaI7FR', '9924411158', '123456', '17797472727', 'fan720@gmail.com', 1, '2024-10-16 16:21:00', NULL, '420600797212303206', '2024-10-14 09:31:56', 0);
INSERT INTO `user` VALUES (261, 1, 5, '严睿', 'I36DGrxDn6', '1701759186', '123456', '76999476398', 'rui213@outlook.com', 1, '2024-10-14 05:45:23', NULL, '42080089501130100x', '2024-10-28 08:56:23', 0);
INSERT INTO `user` VALUES (262, 2, 3, '丁杰宏', '3vcd0lrGeR', '4149563469', '123456', '13867178291', 'jiehong4@yahoo.com', 0, '2024-10-10 15:17:26', '2024-10-17 09:54:05', '420200428510092630', '2024-10-30 23:13:52', 0);
INSERT INTO `user` VALUES (263, 3, 1, '张子异', 'qjhqDVf1Z6', '4288916945', '123456', '19138412639', 'zhanziy508@gmail.com', 1, '2024-10-16 13:21:55', '2024-10-10 20:52:05', '42110034690131868X', '2024-10-14 23:37:53', 0);
INSERT INTO `user` VALUES (264, 2, 4, '苏秀英', 'pFWQ7CBLhe', '7004879101', '123456', '14143481567', 'suxiuying@gmail.com', 1, '2024-10-15 05:15:05', '2024-10-12 18:38:32', '421300476303300734', '2024-10-31 03:47:34', 0);
INSERT INTO `user` VALUES (265, 2, 2, '吴杰宏', 'welLvJlirG', '8877981517', '123456', '201767018', 'jiwu5@yahoo.com', 1, '2024-10-12 02:14:08', NULL, '420600044106300462', '2024-10-25 10:16:30', 0);
INSERT INTO `user` VALUES (266, 2, 3, '熊璐', '377MiLatNu', '4436742000', '123456', '19248649467', 'xiol@qq.com', 0, '2024-10-10 04:15:22', NULL, '42070029571005875x', '2024-10-14 18:23:46', 0);
INSERT INTO `user` VALUES (267, 2, 2, '魏云熙', 'zeTDO9j5mN', '7332708076', '123456', '17652798250', 'ywei9@icloud.com', 0, '2024-10-17 05:44:46', '2024-10-12 16:14:24', '420800817711258376', '2024-10-10 23:43:05', 0);
INSERT INTO `user` VALUES (268, 2, 2, '董子异', 'kqNrDXJJ2y', '3862745084', '123456', '7691112997', 'ziyd617@icloud.com', 0, '2024-10-16 17:10:15', '2024-10-14 02:51:42', '42030037301107377x', '2024-10-16 11:56:48', 0);
INSERT INTO `user` VALUES (269, 3, 3, '黎岚', '2n8PsLOrZg', '7783676465', '123456', '19065378728', 'lan5@gmail.com', 1, '2024-10-11 07:46:52', NULL, '421300506811305185', '2024-10-18 10:50:30', 0);
INSERT INTO `user` VALUES (270, 2, 3, '李秀英', 'mG6QnyI4ib', '8646062526', '123456', '2053936865', 'lixiuying8@gmail.com', 0, '2024-10-12 21:08:25', '2024-10-14 15:17:30', '421200520307305128', '2024-10-28 09:11:02', 0);
INSERT INTO `user` VALUES (271, 2, 4, '方璐', 'BTxHeaYbYL', '6308319577', '123456', '7691258351', 'lu723@gmail.com', 1, '2024-10-10 08:21:21', '2024-10-11 19:01:52', '420700910505203048', '2024-10-21 18:48:23', 0);
INSERT INTO `user` VALUES (272, 2, 1, '蒋詩涵', 'in1IUXxLdR', '6179961869', '123456', '75540012334', 'jishi5@qq.com', 1, '2024-10-15 23:48:11', '2024-10-16 07:20:42', '421200226509224840', '2024-10-18 02:56:13', 0);
INSERT INTO `user` VALUES (273, 2, 1, '吕晓明', 'l0UqOP0ZGK', '1328298842', '123456', '16750286527', 'xilu@outlook.com', 0, '2024-10-12 21:36:49', '2024-10-11 12:50:46', '420600025701193251', '2024-10-25 19:44:15', 0);
INSERT INTO `user` VALUES (274, 2, 1, '尹杰宏', 'PlHYyvSXmp', '5883506781', '123456', '13019860396', 'yjiehong1113@icloud.com', 1, '2024-10-14 00:36:14', '2024-10-11 18:14:41', '421200583910303022', '2024-10-14 19:10:52', 0);
INSERT INTO `user` VALUES (275, 2, 1, '汤晓明', 'wbU4ZJ4Myx', '1561382902', '123456', '2198791004', 'taxiaoming6@outlook.com', 1, '2024-10-10 22:41:03', NULL, '42060097880625180x', '2024-10-10 14:52:06', 0);
INSERT INTO `user` VALUES (276, 3, 4, '常璐', '7vkZZ7NK9l', '5073381477', '123456', '201070741', 'luchang@gmail.com', 1, '2024-10-12 03:05:15', NULL, '421300453911303016', '2024-10-18 10:02:54', 0);
INSERT INTO `user` VALUES (277, 3, 3, '吴云熙', 'EEIQLDVGft', '4130429470', '123456', '76903783561', 'yunxiwu@outlook.com', 1, '2024-10-13 22:41:33', '2024-10-15 15:05:39', '420600712210319908', '2024-10-25 05:04:20', 0);
INSERT INTO `user` VALUES (278, 3, 4, '范杰宏', 'L8DUfQVx11', '9078766133', '123456', '214497974', 'jiehong4@qq.com', 1, '2024-10-11 06:39:32', NULL, '42050042651231859x', '2024-10-29 21:05:21', 0);
INSERT INTO `user` VALUES (279, 2, 4, '田致远', 'rChbSGBL9R', '5699632738', '123456', '1006136090', 'tianz@icloud.com', 0, '2024-10-10 22:24:05', NULL, '422800109703028722', '2024-10-10 11:35:42', 0);
INSERT INTO `user` VALUES (280, 3, 3, '黄子异', 'Xnq3EybG3G', '5418872513', '123456', '7697255889', 'ziyihuang@icloud.com', 0, '2024-10-16 11:49:47', NULL, '420600449411308576', '2024-10-16 07:18:44', 0);
INSERT INTO `user` VALUES (281, 1, 3, '姜詩涵', '8gp4fo0GNN', '3924299105', '123456', '15572888022', 'jiangshi7@icloud.com', 0, '2024-10-13 17:16:33', NULL, '422800804510315467', '2024-10-28 05:08:35', 0);
INSERT INTO `user` VALUES (282, 2, 1, '韦璐', 'vz8lPDwwod', '8380410828', '123456', '2170666736', 'weilu14@outlook.com', 0, '2024-10-15 23:01:40', '2024-10-15 22:04:20', '42060098641124517x', '2024-10-21 02:16:05', 0);
INSERT INTO `user` VALUES (283, 3, 2, '夏震南', 'BYN5t5jkWn', '7901110671', '123456', '15682590715', 'zhex@gmail.com', 1, '2024-10-12 13:21:25', NULL, '421100406408319457', '2024-10-16 11:11:57', 0);
INSERT INTO `user` VALUES (284, 1, 4, '卢睿', 'st87dpo1X5', '3497137224', '123456', '1060144197', 'rulu55@outlook.com', 1, '2024-10-10 17:51:48', NULL, '421200449509312406', '2024-10-21 18:38:17', 0);
INSERT INTO `user` VALUES (285, 2, 3, '杜岚', 'pIq6HwO6Ca', '9593127800', '123456', '18582390956', 'dulan@yahoo.com', 1, '2024-10-11 09:20:47', '2024-10-16 18:37:36', '420300293208317037', '2024-10-18 15:56:29', 0);
INSERT INTO `user` VALUES (286, 3, 4, '贺致远', 'MLZz7ozPGt', '9521849112', '123456', '100053952', 'zhh@yahoo.com', 0, '2024-10-10 05:25:23', '2024-10-16 20:05:47', '42060061041018917X', '2024-10-22 08:06:23', 0);
INSERT INTO `user` VALUES (287, 2, 5, '郭晓明', 'wii7WvRKvl', '8273821090', '123456', '102549014', 'xiaominggu@outlook.com', 0, '2024-10-16 00:47:38', '2024-10-14 07:28:29', '420300507301260359', '2024-10-31 10:49:39', 0);
INSERT INTO `user` VALUES (288, 2, 5, '赵震南', 'kAmTOll8cF', '2090969443', '123456', '14334482097', 'zhaozhenn@gmail.com', 1, '2024-10-10 04:39:33', '2024-10-14 21:37:24', '420700460210315138', '2024-10-14 17:46:39', 0);
INSERT INTO `user` VALUES (289, 2, 4, '于嘉伦', 'FLqhseQ41c', '8034604367', '123456', '16494676633', 'yujialun@outlook.com', 1, '2024-10-12 05:41:23', NULL, '420500649911037650', '2024-10-21 20:59:00', 0);
INSERT INTO `user` VALUES (290, 1, 5, '许詩涵', 'RKkf3llf9J', '5531781162', '123456', '19595570510', 'xu83@gmail.com', 0, '2024-10-10 15:11:25', '2024-10-11 02:00:39', '420900699105302327', '2024-10-16 09:07:39', 0);
INSERT INTO `user` VALUES (291, 3, 4, '苏嘉伦', 'AVBQ3dAU9N', '7097964574', '123456', '7600285750', 'sujialun@outlook.com', 0, '2024-10-12 00:52:45', NULL, '420600963711175300', '2024-10-31 18:01:22', 0);
INSERT INTO `user` VALUES (292, 2, 4, '段子韬', 'CTBpoxudbH', '7643635145', '123456', '76975398292', 'zduan@icloud.com', 1, '2024-10-13 07:13:07', NULL, '421200268312236383', '2024-10-14 00:03:49', 0);
INSERT INTO `user` VALUES (293, 1, 2, '于岚', 'Uhcd1VnNIe', '1722634810', '123456', '13910211066', 'lan427@gmail.com', 0, '2024-10-15 19:15:06', NULL, '420900089111035468', '2024-10-24 20:34:10', 0);
INSERT INTO `user` VALUES (294, 2, 2, '石震南', '2ivjcu3NEM', '5887131289', '123456', '18423539511', 'shi00@gmail.com', 0, '2024-10-17 21:39:05', '2024-10-11 03:53:02', '42110029030630230X', '2024-10-25 00:15:34', 0);
INSERT INTO `user` VALUES (295, 2, 3, '钟安琪', 'AA0PO5XBe6', '9609465036', '123456', '2117039656', 'anqi16@outlook.com', 1, '2024-10-10 11:36:38', '2024-10-17 15:31:23', '42120071781127563x', '2024-10-21 01:35:27', 0);
INSERT INTO `user` VALUES (296, 3, 3, '金璐', 'GaEZNPcGjG', '5592942594', '123456', '15488691254', 'jin48@yahoo.com', 1, '2024-10-17 18:15:41', NULL, '421300415510196533', '2024-10-15 22:32:44', 0);
INSERT INTO `user` VALUES (297, 3, 4, '段詩涵', 'u5SYleFqR6', '8151733902', '123456', '18122710091', 'duashiha7@outlook.com', 1, '2024-10-12 14:09:04', NULL, '420800897901150089', '2024-10-17 10:01:26', 0);
INSERT INTO `user` VALUES (298, 3, 1, '尹璐', 'HAriYEGAFr', '3345464596', '123456', '76971522452', 'yinlu@qq.com', 0, '2024-10-17 21:22:51', NULL, '420800339109191683', '2024-10-10 18:55:11', 0);
INSERT INTO `user` VALUES (299, 2, 5, '范詩涵', 'rtCLLQOqoA', '6625063350', '123456', '7551531798', 'shihan71@icloud.com', 1, '2024-10-16 17:06:28', '2024-10-17 02:54:56', '421000249104231351', '2024-10-17 02:14:54', 0);

SET FOREIGN_KEY_CHECKS = 1;
