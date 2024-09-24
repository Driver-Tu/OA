/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80039 (8.0.39)
 Source Host           : localhost:3306
 Source Schema         : oa_fcfz

 Target Server Type    : MySQL
 Target Server Version : 80039 (8.0.39)
 File Encoding         : 65001

 Date: 23/09/2024 16:48:12
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
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `attendance_id` int NOT NULL AUTO_INCREMENT COMMENT '考勤id',
  `attendance_user_id` int NOT NULL COMMENT '考勤人id',
  `time_in` datetime NOT NULL COMMENT '上班打卡时间',
  `time_out` datetime NOT NULL COMMENT '下班打卡时间',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '打卡日期',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '打卡状态',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '打卡地址',
  `longitude` decimal(10, 7) NOT NULL COMMENT '经度',
  `latitude` decimal(10, 7) NOT NULL COMMENT '纬度',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '打卡类型',
  PRIMARY KEY (`attendance_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES (1, 5, '2024-09-03 08:41:47', '2024-08-05 17:01:52', '2024-09-17', '打卡成功', '420 Huanqu South Street 2nd Alley', 77.6335409, 69.0625049, '上班打卡');
INSERT INTO `attendance` VALUES (2, 1, '2024-08-09 08:19:28', '2024-08-01 17:05:22', '2024-08-16', '打卡成功', '429 Zhongshan 5th Rd, Zimaling Shangquan', 109.5064408, 119.8061905, '上班打卡');
INSERT INTO `attendance` VALUES (3, 3, '2024-09-13 08:06:09', '2024-08-05 17:14:44', '2024-08-13', '打卡成功', '376 FuXingMenNei Street, XiCheng District', 106.9276624, 161.9851216, '上班打卡');
INSERT INTO `attendance` VALUES (4, 1, '2024-08-13 08:28:08', '2024-09-02 17:58:04', '2024-09-09', '打卡成功', '329 Tianbei 1st Rd, Luohu District', 146.4651319, 66.6913419, '上班打卡');
INSERT INTO `attendance` VALUES (5, 4, '2024-08-01 08:01:29', '2024-08-09 17:23:25', '2024-09-20', '打卡成功', '360 Lefeng 6th Rd', 81.1618481, 127.9410014, '上班打卡');
INSERT INTO `attendance` VALUES (6, 4, '2024-08-26 08:50:27', '2024-08-22 17:27:35', '2024-08-13', '打卡成功', '392 Jingtian East 1st St, Futian District', 96.8346381, 11.3067834, '上班打卡');
INSERT INTO `attendance` VALUES (7, 1, '2024-08-09 08:48:14', '2024-09-18 17:22:24', '2024-09-19', '打卡成功', '845 Sanlitun Road, Chaoyang District', 86.5935156, 153.3822272, '上班打卡');
INSERT INTO `attendance` VALUES (8, 4, '2024-08-26 08:07:42', '2024-09-06 17:40:51', '2024-08-30', '打卡成功', '932 Shennan E Rd, Cai Wu Wei, Luohu District', 25.3954678, 79.1132734, '上班打卡');
INSERT INTO `attendance` VALUES (9, 3, '2024-08-09 08:05:11', '2024-09-11 17:20:46', '2024-09-10', '打卡成功', '765 Hongqiao Rd., Xu Hui District', 25.0824646, 144.0478361, '上班打卡');
INSERT INTO `attendance` VALUES (10, 5, '2024-08-19 08:28:07', '2024-09-06 17:43:04', '2024-09-23', '打卡成功', '87 028 County Rd, Yanqing District', 112.4412446, 157.7907014, '上班打卡');
INSERT INTO `attendance` VALUES (11, 6, '2024-09-17 08:55:12', '2024-09-23 17:03:17', '2024-09-05', '打卡成功', '90 4th Section  Renmin South Road, Jinjiang District', 12.9026051, 91.5010694, '上班打卡');
INSERT INTO `attendance` VALUES (12, 2, '2024-08-01 08:17:20', '2024-08-22 17:02:31', '2024-08-26', '打卡成功', '568 Jingtian East 1st St, Futian District', 41.7645808, 26.7719233, '上班打卡');
INSERT INTO `attendance` VALUES (13, 1, '2024-09-13 08:05:37', '2024-09-03 17:11:47', '2024-09-18', '打卡成功', '866 Dongtai 5th St', 38.4476450, 95.5756980, '上班打卡');
INSERT INTO `attendance` VALUES (14, 4, '2024-08-30 08:23:40', '2024-08-08 17:47:10', '2024-08-05', '打卡成功', '572 Kengmei 15th Alley', 77.8757391, 41.4895867, '上班打卡');
INSERT INTO `attendance` VALUES (15, 2, '2024-08-01 08:23:24', '2024-08-26 17:48:04', '2024-09-09', '打卡成功', '228 3rd Section Hongxing Road, Jinjiang District', 68.8714875, 26.9199649, '上班打卡');
INSERT INTO `attendance` VALUES (16, 5, '2024-09-20 08:55:01', '2024-08-12 17:48:07', '2024-08-02', '打卡成功', '252 Shanhu Rd', 59.3587571, 66.8129911, '上班打卡');
INSERT INTO `attendance` VALUES (17, 2, '2024-08-29 08:57:40', '2024-08-16 17:04:37', '2024-08-29', '打卡成功', '187 4th Section  Renmin South Road, Jinjiang District', 17.2223105, 159.0492926, '上班打卡');
INSERT INTO `attendance` VALUES (18, 4, '2024-08-09 08:21:56', '2024-09-18 17:45:52', '2024-08-28', '打卡成功', '204 Tianhe Road, Tianhe District', 105.2594544, 64.7691281, '上班打卡');
INSERT INTO `attendance` VALUES (19, 6, '2024-08-26 08:46:17', '2024-09-03 17:42:46', '2024-08-27', '打卡成功', '146 Kengmei 15th Alley', 41.0074979, 73.1170134, '上班打卡');
INSERT INTO `attendance` VALUES (20, 5, '2024-08-14 08:54:57', '2024-08-07 17:15:48', '2024-08-12', '打卡成功', '439 Lefeng 6th Rd', 121.0236805, 55.5571926, '上班打卡');
INSERT INTO `attendance` VALUES (21, 1, '2024-08-27 08:48:37', '2024-09-20 17:48:35', '2024-09-04', '打卡成功', '532 FuXingMenNei Street, XiCheng District', 141.3503616, 120.9582316, '上班打卡');
INSERT INTO `attendance` VALUES (22, 3, '2024-08-21 08:13:34', '2024-09-13 17:50:59', '2024-08-14', '打卡成功', '307 Zhongshan 5th Rd, Zimaling Shangquan', 65.8401460, 100.2312202, '上班打卡');
INSERT INTO `attendance` VALUES (23, 1, '2024-08-01 08:41:55', '2024-08-15 17:05:57', '2024-09-20', '打卡成功', '131 Hongqiao Rd., Xu Hui District', 51.7265595, 129.6229368, '上班打卡');
INSERT INTO `attendance` VALUES (24, 4, '2024-09-13 08:32:35', '2024-08-01 17:37:50', '2024-08-26', '打卡成功', '939 Qingshuihe 1st Rd, Luohu District', 33.6383107, 148.6069367, '上班打卡');
INSERT INTO `attendance` VALUES (25, 2, '2024-08-30 08:12:42', '2024-08-12 17:42:34', '2024-08-05', '打卡成功', '666 Yueliu Rd, Fangshan District', 39.7957305, 132.3747604, '上班打卡');
INSERT INTO `attendance` VALUES (26, 1, '2024-08-26 08:49:12', '2024-08-30 17:50:12', '2024-09-20', '打卡成功', '415 FuXingMenNei Street, XiCheng District', 14.8199105, 35.4643280, '上班打卡');
INSERT INTO `attendance` VALUES (27, 3, '2024-09-06 08:15:59', '2024-09-19 17:45:40', '2024-09-13', '打卡成功', '658 Jianxiang Rd, Pudong', 51.8705959, 1.8628758, '上班打卡');
INSERT INTO `attendance` VALUES (28, 2, '2024-09-13 08:30:41', '2024-08-19 17:21:39', '2024-08-26', '打卡成功', '122 68 Qinghe Middle St, Haidian District', 86.6094387, 162.1249165, '上班打卡');
INSERT INTO `attendance` VALUES (29, 3, '2024-08-21 08:03:03', '2024-09-06 17:13:28', '2024-08-30', '打卡成功', '695 2nd Zhongshan Road, Yuexiu District', 43.4907502, 107.9302196, '上班打卡');
INSERT INTO `attendance` VALUES (30, 5, '2024-08-01 08:17:23', '2024-09-16 17:26:23', '2024-08-26', '打卡成功', '919 Kengmei 15th Alley', 29.3935563, 32.1360471, '上班打卡');
INSERT INTO `attendance` VALUES (31, 5, '2024-09-19 08:04:27', '2024-09-09 17:50:48', '2024-09-04', '打卡成功', '312 Zhongshan 5th Rd, Zimaling Shangquan', 65.3112468, 80.8217662, '上班打卡');
INSERT INTO `attendance` VALUES (32, 1, '2024-09-09 08:43:01', '2024-08-28 17:51:16', '2024-09-09', '打卡成功', '815 Yueliu Rd, Fangshan District', 61.6257182, 150.1069768, '上班打卡');
INSERT INTO `attendance` VALUES (33, 3, '2024-09-06 08:02:41', '2024-08-05 17:16:08', '2024-09-06', '打卡成功', '366 Ganlan Rd, Pudong', 62.1344281, 147.7596823, '上班打卡');
INSERT INTO `attendance` VALUES (34, 5, '2024-09-18 08:21:08', '2024-08-26 17:51:56', '2024-08-26', '打卡成功', '836 Huanqu South Street 2nd Alley', 4.8847711, 165.1123625, '上班打卡');
INSERT INTO `attendance` VALUES (35, 2, '2024-09-23 08:02:21', '2024-08-30 17:30:19', '2024-09-18', '打卡成功', '920 68 Qinghe Middle St, Haidian District', 46.9480526, 157.3497721, '上班打卡');
INSERT INTO `attendance` VALUES (36, 6, '2024-08-30 08:57:18', '2024-09-05 17:00:22', '2024-09-11', '打卡成功', '817 Jiangnan West Road, Haizhu District', 47.9380326, 11.3854637, '上班打卡');
INSERT INTO `attendance` VALUES (37, 3, '2024-08-14 08:13:36', '2024-09-12 17:21:30', '2024-08-20', '打卡成功', '665 Kengmei 15th Alley', 67.6089181, 5.3337869, '上班打卡');
INSERT INTO `attendance` VALUES (38, 4, '2024-08-02 08:40:59', '2024-08-05 17:57:55', '2024-09-11', '打卡成功', '164 Tianhe Road, Tianhe District', 29.1516176, 162.6548746, '上班打卡');
INSERT INTO `attendance` VALUES (39, 2, '2024-09-04 08:47:35', '2024-08-30 17:47:46', '2024-09-02', '打卡成功', '92 4th Section  Renmin South Road, Jinjiang District', 93.5903297, 105.9594229, '上班打卡');
INSERT INTO `attendance` VALUES (40, 2, '2024-09-03 08:49:09', '2024-08-28 17:46:30', '2024-08-05', '打卡成功', '878 Jingtian East 1st St, Futian District', 116.3244693, 163.4133431, '上班打卡');
INSERT INTO `attendance` VALUES (41, 1, '2024-08-19 08:49:24', '2024-08-06 17:27:31', '2024-08-26', '打卡成功', '85 Shennan E Rd, Cai Wu Wei, Luohu District', 81.0236472, 58.7597882, '上班打卡');
INSERT INTO `attendance` VALUES (42, 5, '2024-08-06 08:05:10', '2024-08-30 17:57:24', '2024-09-16', '打卡成功', '844 NO.6, YuShuang Road, ChengHua Distric', 96.3894614, 25.5989712, '上班打卡');
INSERT INTO `attendance` VALUES (43, 3, '2024-08-19 08:12:46', '2024-08-02 17:30:30', '2024-08-26', '打卡成功', '56 Dongtai 5th St', 51.9456279, 105.8667783, '上班打卡');
INSERT INTO `attendance` VALUES (44, 1, '2024-09-04 08:14:35', '2024-09-06 17:40:29', '2024-09-13', '打卡成功', '762 68 Qinghe Middle St, Haidian District', 99.0112583, 109.1882698, '上班打卡');
INSERT INTO `attendance` VALUES (45, 1, '2024-08-01 08:11:10', '2024-08-30 17:52:49', '2024-09-20', '打卡成功', '443 Huaxia St, Jinghua Shangquan', 70.1292543, 120.4618034, '上班打卡');
INSERT INTO `attendance` VALUES (46, 3, '2024-09-16 08:11:36', '2024-08-26 17:02:59', '2024-08-23', '打卡成功', '537 68 Qinghe Middle St, Haidian District', 106.7969499, 45.1943675, '上班打卡');
INSERT INTO `attendance` VALUES (47, 6, '2024-08-28 08:32:23', '2024-08-06 17:14:39', '2024-08-19', '打卡成功', '36 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 10.3129886, 156.4527358, '上班打卡');
INSERT INTO `attendance` VALUES (48, 6, '2024-08-28 08:44:01', '2024-09-13 17:04:01', '2024-08-13', '打卡成功', '843 Xue Yuan Yi Xiang, Longgang', 34.5887683, 147.8083797, '上班打卡');
INSERT INTO `attendance` VALUES (49, 5, '2024-08-30 08:20:15', '2024-09-18 17:24:03', '2024-09-20', '打卡成功', 'No. 828, Shuangqing Rd, Chenghua District', 119.2512231, 141.5770233, '上班打卡');
INSERT INTO `attendance` VALUES (50, 1, '2024-09-12 08:30:23', '2024-09-06 17:02:18', '2024-08-30', '打卡成功', '498 Jiangnan West Road, Haizhu District', 94.7652966, 27.8165774, '上班打卡');
INSERT INTO `attendance` VALUES (51, 1, '2024-08-26 08:15:05', '2024-08-29 17:13:08', '2024-09-13', '打卡成功', '421 FuXingMenNei Street, XiCheng District', 38.5769896, 15.2452301, '上班打卡');
INSERT INTO `attendance` VALUES (52, 3, '2024-08-19 08:53:19', '2024-08-06 17:35:35', '2024-08-06', '打卡成功', '471 Sanlitun Road, Chaoyang District', 84.0121195, 154.5532130, '上班打卡');
INSERT INTO `attendance` VALUES (53, 5, '2024-09-04 08:48:37', '2024-09-11 17:21:35', '2024-09-18', '打卡成功', '794 2nd Zhongshan Road, Yuexiu District', 114.7871527, 121.7407495, '上班打卡');
INSERT INTO `attendance` VALUES (54, 1, '2024-08-30 08:00:15', '2024-08-30 17:40:12', '2024-09-20', '打卡成功', 'No. 913, Shuangqing Rd, Chenghua District', 8.3062274, 100.2906130, '上班打卡');
INSERT INTO `attendance` VALUES (55, 6, '2024-08-29 08:07:39', '2024-08-30 17:49:06', '2024-08-19', '打卡成功', '716 Binchuan Rd, Minhang District', 3.1622654, 6.2447018, '上班打卡');
INSERT INTO `attendance` VALUES (56, 2, '2024-08-30 08:41:37', '2024-08-27 17:29:23', '2024-08-16', '打卡成功', '53 Shennan Ave, Futian District', 53.8186008, 3.5327926, '上班打卡');
INSERT INTO `attendance` VALUES (57, 4, '2024-09-06 08:19:19', '2024-08-28 17:36:41', '2024-09-20', '打卡成功', '165 Binchuan Rd, Minhang District', 37.3963954, 88.7029058, '上班打卡');
INSERT INTO `attendance` VALUES (58, 4, '2024-08-21 08:18:07', '2024-08-19 17:45:27', '2024-08-26', '打卡成功', '346 Jiangnan West Road, Haizhu District', 146.2549247, 97.3558702, '上班打卡');
INSERT INTO `attendance` VALUES (59, 5, '2024-08-30 08:05:27', '2024-09-10 17:21:49', '2024-08-12', '打卡成功', '414 Shennan Ave, Futian District', 37.2925085, 112.8973972, '上班打卡');
INSERT INTO `attendance` VALUES (60, 4, '2024-08-26 08:08:54', '2024-08-26 17:02:34', '2024-08-19', '打卡成功', '254 3rd Section Hongxing Road, Jinjiang District', 78.9394616, 86.9746824, '上班打卡');
INSERT INTO `attendance` VALUES (61, 2, '2024-09-17 08:48:39', '2024-08-27 17:59:07', '2024-08-30', '打卡成功', 'No. 414, Shuangqing Rd, Chenghua District', 37.9751826, 102.2427120, '上班打卡');
INSERT INTO `attendance` VALUES (62, 5, '2024-08-19 08:05:06', '2024-08-05 17:58:37', '2024-09-09', '打卡成功', '749 NO.6, YuShuang Road, ChengHua Distric', 70.1497618, 37.2431152, '上班打卡');
INSERT INTO `attendance` VALUES (63, 2, '2024-08-05 08:14:15', '2024-09-05 17:32:38', '2024-08-08', '打卡成功', '164 Huaxia St, Jinghua Shangquan', 83.4072417, 38.5063464, '上班打卡');
INSERT INTO `attendance` VALUES (64, 5, '2024-09-18 08:42:54', '2024-09-13 17:35:01', '2024-09-18', '打卡成功', '437 Qingshuihe 1st Rd, Luohu District', 13.9007599, 1.6039417, '上班打卡');
INSERT INTO `attendance` VALUES (65, 5, '2024-09-05 08:36:56', '2024-08-26 17:09:56', '2024-08-08', '打卡成功', '120 Jiangnan West Road, Haizhu District', 35.6694305, 77.6771069, '上班打卡');
INSERT INTO `attendance` VALUES (66, 1, '2024-08-05 08:05:21', '2024-08-30 17:12:48', '2024-08-05', '打卡成功', '544 Jiangnan West Road, Haizhu District', 138.9219348, 99.5783118, '上班打卡');
INSERT INTO `attendance` VALUES (67, 2, '2024-09-13 08:24:27', '2024-09-06 17:11:22', '2024-08-20', '打卡成功', '649 Shennan Ave, Futian District', 34.1070477, 132.7565268, '上班打卡');
INSERT INTO `attendance` VALUES (68, 1, '2024-09-03 08:31:06', '2024-09-18 17:56:25', '2024-09-03', '打卡成功', '791 Yueliu Rd, Fangshan District', 8.8285316, 139.3691635, '上班打卡');
INSERT INTO `attendance` VALUES (69, 5, '2024-09-17 08:10:56', '2024-09-06 17:41:16', '2024-08-12', '打卡成功', '235 Shennan E Rd, Cai Wu Wei, Luohu District', 96.3681550, 47.8692485, '上班打卡');
INSERT INTO `attendance` VALUES (70, 4, '2024-08-08 08:44:17', '2024-09-19 17:29:30', '2024-08-30', '打卡成功', 'No.324, Dongsan Road, Erxianqiao, Chenghua District', 44.8552147, 39.9116579, '上班打卡');
INSERT INTO `attendance` VALUES (71, 1, '2024-08-08 08:11:12', '2024-08-05 17:58:33', '2024-08-28', '打卡成功', '183 Kengmei 15th Alley', 54.9787294, 154.9857417, '上班打卡');
INSERT INTO `attendance` VALUES (72, 5, '2024-08-22 08:04:14', '2024-09-20 17:24:24', '2024-08-07', '打卡成功', '299 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 102.1402054, 22.5568791, '上班打卡');
INSERT INTO `attendance` VALUES (73, 1, '2024-08-07 08:55:28', '2024-08-05 17:04:33', '2024-09-20', '打卡成功', '676 Zhongshan 5th Rd, Zimaling Shangquan', 142.1404289, 140.0605526, '上班打卡');
INSERT INTO `attendance` VALUES (74, 5, '2024-08-30 08:14:23', '2024-09-13 17:11:56', '2024-08-26', '打卡成功', '278 Xue Yuan Yi Xiang, Longgang', 55.8472542, 10.1533194, '上班打卡');
INSERT INTO `attendance` VALUES (75, 5, '2024-09-18 08:22:56', '2024-08-02 17:47:42', '2024-08-05', '打卡成功', '464 3rd Section Hongxing Road, Jinjiang District', 53.7623141, 17.6013154, '上班打卡');
INSERT INTO `attendance` VALUES (76, 3, '2024-09-18 08:45:41', '2024-08-13 17:38:13', '2024-09-06', '打卡成功', '799 Hongqiao Rd., Xu Hui District', 59.3807236, 59.2487791, '上班打卡');
INSERT INTO `attendance` VALUES (77, 6, '2024-09-13 08:59:18', '2024-09-13 17:05:04', '2024-08-26', '打卡成功', '481 Qingshuihe 1st Rd, Luohu District', 30.9839787, 124.3690736, '上班打卡');
INSERT INTO `attendance` VALUES (78, 2, '2024-08-12 08:19:09', '2024-08-12 17:57:22', '2024-08-30', '打卡成功', '887 Xiaoping E Rd, Baiyun ', 5.5840781, 86.5712016, '上班打卡');
INSERT INTO `attendance` VALUES (79, 1, '2024-08-15 08:07:04', '2024-09-20 17:00:40', '2024-08-12', '打卡成功', '634 NO.6, YuShuang Road, ChengHua Distric', 33.6695387, 14.9873400, '上班打卡');
INSERT INTO `attendance` VALUES (80, 6, '2024-08-30 08:57:17', '2024-09-09 17:24:41', '2024-08-26', '打卡成功', '731 W Ring Rd, Buji Town, Longgang', 36.1815918, 126.4505370, '上班打卡');
INSERT INTO `attendance` VALUES (81, 1, '2024-08-05 08:39:26', '2024-09-13 17:55:49', '2024-09-06', '打卡成功', '892 Dong Zhi Men, Dongcheng District', 117.3739414, 4.9570287, '上班打卡');
INSERT INTO `attendance` VALUES (82, 1, '2024-09-04 08:53:59', '2024-08-23 17:02:39', '2024-09-06', '打卡成功', '7 East Wangfujing Street, Dongcheng District ', 142.4412067, 56.6645744, '上班打卡');
INSERT INTO `attendance` VALUES (83, 2, '2024-09-16 08:56:19', '2024-09-13 17:09:36', '2024-08-05', '打卡成功', '85 Hongqiao Rd., Xu Hui District', 57.1425844, 10.9040465, '上班打卡');
INSERT INTO `attendance` VALUES (84, 2, '2024-08-14 08:37:22', '2024-08-26 17:16:17', '2024-08-05', '打卡成功', '267 NO.6, YuShuang Road, ChengHua Distric', 113.9568308, 93.3257879, '上班打卡');
INSERT INTO `attendance` VALUES (85, 3, '2024-09-20 08:31:16', '2024-08-29 17:48:24', '2024-08-01', '打卡成功', '429 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 53.6902063, 5.0244597, '上班打卡');
INSERT INTO `attendance` VALUES (86, 2, '2024-09-20 08:13:36', '2024-08-05 17:35:35', '2024-08-12', '打卡成功', '806 Hongqiao Rd., Xu Hui District', 128.2219607, 108.3259166, '上班打卡');
INSERT INTO `attendance` VALUES (87, 2, '2024-08-30 08:22:14', '2024-09-06 17:00:18', '2024-08-22', '打卡成功', '895 Yueliu Rd, Fangshan District', 118.0204449, 102.6870627, '上班打卡');
INSERT INTO `attendance` VALUES (88, 4, '2024-08-12 08:58:46', '2024-09-09 17:37:08', '2024-08-30', '打卡成功', '402 Tianhe Road, Tianhe District', 71.8239602, 146.4965648, '上班打卡');
INSERT INTO `attendance` VALUES (89, 5, '2024-08-29 08:02:20', '2024-08-30 17:11:06', '2024-08-27', '打卡成功', '79 Jiangnan West Road, Haizhu District', 128.8177012, 9.1896042, '上班打卡');
INSERT INTO `attendance` VALUES (90, 6, '2024-09-23 08:43:00', '2024-08-26 17:08:34', '2024-09-20', '打卡成功', '964 Tangyuan Street 5th Alley, Airport Road, Baiyun', 118.3295618, 71.2519109, '上班打卡');
INSERT INTO `attendance` VALUES (91, 1, '2024-08-13 08:35:14', '2024-09-12 17:08:25', '2024-09-20', '打卡成功', '867 2nd Zhongshan Road, Yuexiu District', 97.9643753, 107.3770118, '上班打卡');
INSERT INTO `attendance` VALUES (92, 4, '2024-09-20 08:15:47', '2024-09-06 17:07:39', '2024-08-26', '打卡成功', 'No.920, Dongsan Road, Erxianqiao, Chenghua District', 123.2775215, 53.8096661, '上班打卡');
INSERT INTO `attendance` VALUES (93, 4, '2024-08-19 08:10:05', '2024-08-01 17:33:27', '2024-08-26', '打卡成功', '337 Xue Yuan Yi Xiang, Longgang', 149.8995702, 82.8495420, '上班打卡');
INSERT INTO `attendance` VALUES (94, 4, '2024-08-06 08:11:00', '2024-09-06 17:24:12', '2024-08-12', '打卡成功', '432 Shanhu Rd', 63.6213606, 160.7956190, '上班打卡');
INSERT INTO `attendance` VALUES (95, 2, '2024-09-06 08:22:26', '2024-08-08 17:17:46', '2024-09-09', '打卡成功', '481 FuXingMenNei Street, XiCheng District', 67.5194292, 143.0908540, '上班打卡');
INSERT INTO `attendance` VALUES (96, 2, '2024-08-06 08:13:09', '2024-08-06 17:30:53', '2024-08-08', '打卡成功', '194 028 County Rd, Yanqing District', 52.5092904, 137.1252881, '上班打卡');
INSERT INTO `attendance` VALUES (97, 5, '2024-09-20 08:34:38', '2024-08-07 17:16:09', '2024-09-13', '打卡成功', '575 Shennan E Rd, Cai Wu Wei, Luohu District', 90.3851302, 158.3016461, '上班打卡');
INSERT INTO `attendance` VALUES (98, 5, '2024-08-19 08:48:19', '2024-08-13 17:24:36', '2024-09-20', '打卡成功', '107 Dongtai 5th St', 51.7418607, 93.4667433, '上班打卡');
INSERT INTO `attendance` VALUES (99, 1, '2024-08-12 08:26:02', '2024-08-26 17:34:36', '2024-08-30', '打卡成功', '475 2nd Zhongshan Road, Yuexiu District', 122.2992004, 50.4873994, '上班打卡');
INSERT INTO `attendance` VALUES (100, 1, '2024-08-29 08:40:00', '2024-09-18 17:45:09', '2024-09-13', '打卡成功', '734 4th Section  Renmin South Road, Jinjiang District', 98.6197415, 72.8967615, '上班打卡');
INSERT INTO `attendance` VALUES (101, 5, '2024-08-30 08:44:14', '2024-09-06 17:49:05', '2024-09-06', '打卡成功', '222 Sanlitun Road, Chaoyang District', 130.8493443, 5.7719041, '上班打卡');
INSERT INTO `attendance` VALUES (102, 3, '2024-08-21 08:36:39', '2024-09-10 17:37:41', '2024-08-21', '打卡成功', '414 FuXingMenNei Street, XiCheng District', 148.4063578, 57.2629939, '上班打卡');
INSERT INTO `attendance` VALUES (103, 3, '2024-09-10 08:43:24', '2024-08-12 17:43:06', '2024-08-05', '打卡成功', '919 Qingshuihe 1st Rd, Luohu District', 144.0535148, 85.8193393, '上班打卡');
INSERT INTO `attendance` VALUES (104, 6, '2024-08-07 08:08:14', '2024-08-15 17:07:24', '2024-08-20', '打卡成功', '38 Kengmei 15th Alley', 83.7336691, 143.9168893, '上班打卡');
INSERT INTO `attendance` VALUES (105, 1, '2024-09-23 08:07:48', '2024-08-12 17:54:10', '2024-09-23', '打卡成功', '235 Jianxiang Rd, Pudong', 58.7716238, 60.8732324, '上班打卡');
INSERT INTO `attendance` VALUES (106, 1, '2024-09-13 08:15:48', '2024-09-13 17:49:53', '2024-09-04', '打卡成功', 'No.836, Dongsan Road, Erxianqiao, Chenghua District', 64.4177081, 156.6955903, '上班打卡');
INSERT INTO `attendance` VALUES (107, 2, '2024-09-13 08:23:49', '2024-08-23 17:05:32', '2024-08-13', '打卡成功', '180 4th Section  Renmin South Road, Jinjiang District', 100.8598657, 106.3773538, '上班打卡');
INSERT INTO `attendance` VALUES (108, 1, '2024-08-19 08:50:03', '2024-08-30 17:03:34', '2024-09-06', '打卡成功', '765 Jianxiang Rd, Pudong', 119.1171672, 93.6376533, '上班打卡');
INSERT INTO `attendance` VALUES (109, 5, '2024-08-27 08:09:04', '2024-08-30 17:55:14', '2024-09-12', '打卡成功', '849 FuXingMenNei Street, XiCheng District', 46.4946510, 99.9400387, '上班打卡');
INSERT INTO `attendance` VALUES (110, 1, '2024-09-05 08:58:41', '2024-08-20 17:58:10', '2024-08-07', '打卡成功', '119 Huanqu South Street 2nd Alley', 125.8906602, 155.5710383, '上班打卡');
INSERT INTO `attendance` VALUES (111, 1, '2024-09-23 08:21:09', '2024-08-30 17:16:59', '2024-08-15', '打卡成功', '418 East Wangfujing Street, Dongcheng District ', 64.6796460, 0.8004915, '上班打卡');
INSERT INTO `attendance` VALUES (112, 1, '2024-09-18 08:45:24', '2024-08-07 17:56:20', '2024-08-26', '打卡成功', '540 W Ring Rd, Buji Town, Longgang', 54.7758932, 157.8299271, '上班打卡');
INSERT INTO `attendance` VALUES (113, 4, '2024-08-05 08:43:03', '2024-08-05 17:13:57', '2024-08-07', '打卡成功', '595 Jianxiang Rd, Pudong', 99.1120735, 111.2078635, '上班打卡');
INSERT INTO `attendance` VALUES (114, 4, '2024-09-13 08:31:13', '2024-09-13 17:01:03', '2024-09-12', '打卡成功', '63 Sanlitun Road, Chaoyang District', 84.8248224, 149.5549030, '上班打卡');
INSERT INTO `attendance` VALUES (115, 1, '2024-08-20 08:08:37', '2024-08-05 17:11:01', '2024-09-03', '打卡成功', '569 Dongtai 5th St', 131.7303037, 63.6077268, '上班打卡');
INSERT INTO `attendance` VALUES (116, 2, '2024-08-12 08:03:44', '2024-08-12 17:40:26', '2024-09-04', '打卡成功', '593 Sanlitun Road, Chaoyang District', 45.5356869, 3.2216338, '上班打卡');
INSERT INTO `attendance` VALUES (117, 4, '2024-09-20 08:32:11', '2024-09-16 17:39:04', '2024-09-06', '打卡成功', '182 Shennan Ave, Futian District', 81.2479809, 89.4142328, '上班打卡');
INSERT INTO `attendance` VALUES (118, 6, '2024-08-06 08:10:21', '2024-08-19 17:07:11', '2024-08-29', '打卡成功', '547 Jingtian East 1st St, Futian District', 110.5702084, 3.0144803, '上班打卡');
INSERT INTO `attendance` VALUES (119, 5, '2024-09-20 08:46:00', '2024-08-27 17:33:18', '2024-08-19', '打卡成功', '616 3rd Section Hongxing Road, Jinjiang District', 149.5786138, 69.2621281, '上班打卡');
INSERT INTO `attendance` VALUES (120, 1, '2024-08-30 08:20:42', '2024-09-16 17:47:53', '2024-09-06', '打卡成功', '727 Huaxia St, Jinghua Shangquan', 3.2430379, 58.5063062, '上班打卡');
INSERT INTO `attendance` VALUES (121, 1, '2024-09-18 08:47:38', '2024-09-13 17:13:52', '2024-09-06', '打卡成功', 'No.730, Dongsan Road, Erxianqiao, Chenghua District', 45.9772521, 71.6403278, '上班打卡');
INSERT INTO `attendance` VALUES (122, 3, '2024-09-06 08:39:59', '2024-08-13 17:01:07', '2024-09-20', '打卡成功', '955 Dongtai 5th St', 6.4456986, 74.8085006, '上班打卡');
INSERT INTO `attendance` VALUES (123, 3, '2024-09-12 08:32:56', '2024-09-13 17:16:31', '2024-09-10', '打卡成功', 'No. 493, Shuangqing Rd, Chenghua District', 22.6829274, 79.2580459, '上班打卡');
INSERT INTO `attendance` VALUES (124, 5, '2024-09-03 08:59:10', '2024-08-19 17:13:38', '2024-08-19', '打卡成功', '102 NO.6, YuShuang Road, ChengHua Distric', 114.3067623, 45.7169442, '上班打卡');
INSERT INTO `attendance` VALUES (125, 4, '2024-08-27 08:53:17', '2024-08-02 17:31:27', '2024-08-05', '打卡成功', '802 FuXingMenNei Street, XiCheng District', 25.5813082, 7.5227834, '上班打卡');
INSERT INTO `attendance` VALUES (126, 3, '2024-08-30 08:32:04', '2024-09-05 17:54:18', '2024-09-06', '打卡成功', '495 Jingtian East 1st St, Futian District', 24.6261784, 164.1589920, '上班打卡');
INSERT INTO `attendance` VALUES (127, 3, '2024-08-30 08:20:45', '2024-09-23 17:52:58', '2024-09-18', '打卡成功', '626 Xue Yuan Yi Xiang, Longgang', 28.9889831, 152.9183190, '上班打卡');
INSERT INTO `attendance` VALUES (128, 4, '2024-09-02 08:20:22', '2024-08-26 17:31:44', '2024-08-06', '打卡成功', '788 Xue Yuan Yi Xiang, Longgang', 78.2535510, 99.0520422, '上班打卡');
INSERT INTO `attendance` VALUES (129, 4, '2024-09-18 08:31:02', '2024-09-10 17:27:15', '2024-09-10', '打卡成功', '702 Shennan Ave, Futian District', 108.9519094, 0.3796205, '上班打卡');
INSERT INTO `attendance` VALUES (130, 4, '2024-08-30 08:35:58', '2024-08-19 17:42:49', '2024-09-18', '打卡成功', '814 Kengmei 15th Alley', 142.8956568, 18.3819754, '上班打卡');
INSERT INTO `attendance` VALUES (131, 1, '2024-09-09 08:40:06', '2024-08-27 17:25:00', '2024-08-13', '打卡成功', '126 Dongtai 5th St', 97.0882672, 146.4366990, '上班打卡');
INSERT INTO `attendance` VALUES (132, 1, '2024-08-21 08:00:48', '2024-08-19 17:58:20', '2024-08-30', '打卡成功', '208 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 133.4643384, 148.5442999, '上班打卡');
INSERT INTO `attendance` VALUES (133, 5, '2024-09-23 08:33:41', '2024-09-06 17:32:07', '2024-08-14', '打卡成功', '593 Jingtian East 1st St, Futian District', 6.9775513, 118.5910961, '上班打卡');
INSERT INTO `attendance` VALUES (134, 5, '2024-08-19 08:17:09', '2024-09-18 17:23:52', '2024-08-12', '打卡成功', '826 Middle Huaihai Road, Huangpu District', 71.9885709, 125.1068677, '上班打卡');
INSERT INTO `attendance` VALUES (135, 2, '2024-09-04 08:38:22', '2024-09-04 17:44:18', '2024-08-30', '打卡成功', '208 Huaxia St, Jinghua Shangquan', 49.3120739, 86.5301188, '上班打卡');
INSERT INTO `attendance` VALUES (136, 1, '2024-09-23 08:27:40', '2024-08-05 17:34:17', '2024-09-13', '打卡成功', '220 3rd Section Hongxing Road, Jinjiang District', 65.4548993, 93.6031336, '上班打卡');
INSERT INTO `attendance` VALUES (137, 5, '2024-09-20 08:19:58', '2024-08-21 17:09:30', '2024-08-30', '打卡成功', '226 W Ring Rd, Buji Town, Longgang', 60.6807528, 4.0400237, '上班打卡');
INSERT INTO `attendance` VALUES (138, 1, '2024-08-29 08:59:41', '2024-08-12 17:50:29', '2024-09-06', '打卡成功', '917 Binchuan Rd, Minhang District', 59.5023204, 95.2912139, '上班打卡');
INSERT INTO `attendance` VALUES (139, 1, '2024-09-03 08:41:58', '2024-09-13 17:49:08', '2024-08-12', '打卡成功', '962 Zhongshan 5th Rd, Zimaling Shangquan', 16.8250490, 4.4615425, '上班打卡');
INSERT INTO `attendance` VALUES (140, 3, '2024-08-19 08:23:40', '2024-08-02 17:45:21', '2024-08-19', '打卡成功', '328 68 Qinghe Middle St, Haidian District', 110.2314176, 1.6033322, '上班打卡');
INSERT INTO `attendance` VALUES (141, 1, '2024-09-06 08:14:00', '2024-08-01 17:31:55', '2024-09-04', '打卡成功', '208 Huaxia St, Jinghua Shangquan', 107.0578372, 95.2187018, '上班打卡');
INSERT INTO `attendance` VALUES (142, 3, '2024-09-12 08:57:46', '2024-08-16 17:53:17', '2024-08-06', '打卡成功', '223 Jingtian East 1st St, Futian District', 76.7753269, 103.7694881, '上班打卡');
INSERT INTO `attendance` VALUES (143, 5, '2024-09-06 08:53:06', '2024-09-11 17:48:56', '2024-09-12', '打卡成功', '59 Dong Zhi Men, Dongcheng District', 68.1319260, 89.1799676, '上班打卡');
INSERT INTO `attendance` VALUES (144, 5, '2024-08-19 08:38:40', '2024-09-23 17:32:48', '2024-09-13', '打卡成功', '608 Qingshuihe 1st Rd, Luohu District', 148.3873442, 17.2274133, '上班打卡');
INSERT INTO `attendance` VALUES (145, 5, '2024-08-22 08:12:48', '2024-09-05 17:11:04', '2024-08-01', '打卡成功', '364 Jingtian East 1st St, Futian District', 19.1989305, 90.6245306, '上班打卡');
INSERT INTO `attendance` VALUES (146, 2, '2024-08-30 08:38:04', '2024-08-08 17:10:22', '2024-08-30', '打卡成功', '857 West Chang\'an Avenue, Xicheng District', 105.8546979, 19.1743904, '上班打卡');
INSERT INTO `attendance` VALUES (147, 4, '2024-08-16 08:04:23', '2024-08-14 17:00:32', '2024-08-19', '打卡成功', '80 Dongtai 5th St', 130.6713518, 160.9970925, '上班打卡');
INSERT INTO `attendance` VALUES (148, 1, '2024-09-04 08:19:24', '2024-08-30 17:48:12', '2024-09-20', '打卡成功', '633 Ganlan Rd, Pudong', 48.5309724, 164.3603761, '上班打卡');
INSERT INTO `attendance` VALUES (149, 4, '2024-08-02 08:40:39', '2024-08-02 17:22:41', '2024-09-18', '打卡成功', '147 Jingtian East 1st St, Futian District', 115.4508199, 8.7422043, '上班打卡');
INSERT INTO `attendance` VALUES (150, 2, '2024-08-16 08:20:29', '2024-08-12 17:10:02', '2024-08-19', '打卡成功', '730 Lefeng 6th Rd', 98.1909358, 124.9420715, '上班打卡');
INSERT INTO `attendance` VALUES (151, 1, '2024-09-19 08:05:02', '2024-08-14 17:38:12', '2024-09-04', '打卡成功', '668 4th Section  Renmin South Road, Jinjiang District', 51.1996839, 103.9350472, '上班打卡');
INSERT INTO `attendance` VALUES (152, 1, '2024-09-13 08:57:43', '2024-08-01 17:37:08', '2024-09-23', '打卡成功', '314 2nd Zhongshan Road, Yuexiu District', 134.9690303, 105.3266073, '上班打卡');
INSERT INTO `attendance` VALUES (153, 5, '2024-08-20 08:17:22', '2024-09-20 17:14:22', '2024-09-04', '打卡成功', '231 Jiangnan West Road, Haizhu District', 25.9292235, 8.3723035, '上班打卡');
INSERT INTO `attendance` VALUES (154, 1, '2024-09-06 08:15:08', '2024-09-05 17:10:24', '2024-08-09', '打卡成功', '42 Sanlitun Road, Chaoyang District', 97.9737717, 54.0122200, '上班打卡');
INSERT INTO `attendance` VALUES (155, 1, '2024-08-21 08:50:50', '2024-08-07 17:25:06', '2024-09-16', '打卡成功', '243 Tianbei 1st Rd, Luohu District', 83.3584899, 138.5081311, '上班打卡');
INSERT INTO `attendance` VALUES (156, 1, '2024-08-02 08:02:07', '2024-09-10 17:56:30', '2024-08-30', '打卡成功', '178 Huanqu South Street 2nd Alley', 131.4243208, 148.4710980, '上班打卡');
INSERT INTO `attendance` VALUES (157, 4, '2024-09-20 08:01:50', '2024-08-19 17:07:51', '2024-08-05', '打卡成功', '545 Binchuan Rd, Minhang District', 112.1080209, 162.4982319, '上班打卡');
INSERT INTO `attendance` VALUES (158, 1, '2024-08-05 08:50:05', '2024-09-06 17:39:14', '2024-08-21', '打卡成功', '566 Dongtai 5th St', 29.3740507, 69.4357631, '上班打卡');
INSERT INTO `attendance` VALUES (159, 4, '2024-08-19 08:15:29', '2024-09-23 17:40:24', '2024-08-28', '打卡成功', '389 68 Qinghe Middle St, Haidian District', 4.4100946, 45.1345385, '上班打卡');
INSERT INTO `attendance` VALUES (160, 5, '2024-08-12 08:11:30', '2024-09-02 17:14:16', '2024-09-10', '打卡成功', '202 Tianbei 1st Rd, Luohu District', 45.7904787, 4.6937140, '上班打卡');
INSERT INTO `attendance` VALUES (161, 1, '2024-08-30 08:14:48', '2024-09-17 17:47:41', '2024-09-17', '打卡成功', '748 NO.6, YuShuang Road, ChengHua Distric', 21.9875134, 102.9556503, '上班打卡');
INSERT INTO `attendance` VALUES (162, 1, '2024-08-12 08:15:25', '2024-08-05 17:54:39', '2024-09-20', '打卡成功', '364 Yueliu Rd, Fangshan District', 24.6558897, 138.3307437, '上班打卡');
INSERT INTO `attendance` VALUES (163, 4, '2024-09-02 08:29:37', '2024-08-12 17:47:06', '2024-08-14', '打卡成功', '353 West Chang\'an Avenue, Xicheng District', 99.2552409, 83.9849916, '上班打卡');
INSERT INTO `attendance` VALUES (164, 5, '2024-08-22 08:24:55', '2024-08-30 17:17:09', '2024-08-05', '打卡成功', '893 68 Qinghe Middle St, Haidian District', 147.0491478, 25.4874167, '上班打卡');
INSERT INTO `attendance` VALUES (165, 4, '2024-08-05 08:55:39', '2024-08-23 17:05:31', '2024-08-26', '打卡成功', '94 NO.6, YuShuang Road, ChengHua Distric', 98.1773029, 157.5199043, '上班打卡');
INSERT INTO `attendance` VALUES (166, 1, '2024-09-13 08:52:12', '2024-09-10 17:44:27', '2024-08-15', '打卡成功', '242 Dongtai 5th St', 1.0969849, 47.5204025, '上班打卡');
INSERT INTO `attendance` VALUES (167, 2, '2024-09-06 08:53:05', '2024-08-08 17:48:20', '2024-08-14', '打卡成功', 'No.2, Dongsan Road, Erxianqiao, Chenghua District', 78.1819674, 52.7272344, '上班打卡');
INSERT INTO `attendance` VALUES (168, 5, '2024-08-30 08:52:33', '2024-08-22 17:10:03', '2024-08-07', '打卡成功', '287 2nd Zhongshan Road, Yuexiu District', 42.5750670, 35.2543483, '上班打卡');
INSERT INTO `attendance` VALUES (169, 5, '2024-08-07 08:26:10', '2024-08-15 17:05:19', '2024-09-16', '打卡成功', '875 Sanlitun Road, Chaoyang District', 53.5534935, 26.3692235, '上班打卡');
INSERT INTO `attendance` VALUES (170, 3, '2024-09-06 08:57:01', '2024-08-26 17:01:31', '2024-08-05', '打卡成功', '381 Jiangnan West Road, Haizhu District', 92.5898120, 83.6551905, '上班打卡');
INSERT INTO `attendance` VALUES (171, 1, '2024-09-10 08:37:47', '2024-09-19 17:01:16', '2024-08-19', '打卡成功', '347 Dongtai 5th St', 76.4750180, 153.2733929, '上班打卡');
INSERT INTO `attendance` VALUES (172, 2, '2024-08-19 08:20:32', '2024-09-20 17:43:58', '2024-08-08', '打卡成功', '19 Jiangnan West Road, Haizhu District', 30.2378324, 50.9492193, '上班打卡');
INSERT INTO `attendance` VALUES (173, 6, '2024-09-09 08:37:58', '2024-08-15 17:02:08', '2024-08-13', '打卡成功', '404 4th Section  Renmin South Road, Jinjiang District', 83.0213317, 133.1039144, '上班打卡');
INSERT INTO `attendance` VALUES (174, 5, '2024-08-05 08:42:28', '2024-08-12 17:03:32', '2024-08-06', '打卡成功', '802 Xue Yuan Yi Xiang, Longgang', 83.2614991, 147.7845306, '上班打卡');
INSERT INTO `attendance` VALUES (175, 3, '2024-08-06 08:19:20', '2024-09-04 17:37:09', '2024-09-19', '打卡成功', '255 4th Section  Renmin South Road, Jinjiang District', 41.4784091, 9.0065692, '上班打卡');
INSERT INTO `attendance` VALUES (176, 3, '2024-09-20 08:50:32', '2024-08-16 17:27:37', '2024-09-12', '打卡成功', '33 Yueliu Rd, Fangshan District', 120.9178203, 81.3020381, '上班打卡');
INSERT INTO `attendance` VALUES (177, 1, '2024-08-05 08:01:05', '2024-09-20 17:32:08', '2024-08-19', '打卡成功', '406 Xiaoping E Rd, Baiyun ', 143.7338504, 6.0470564, '上班打卡');
INSERT INTO `attendance` VALUES (178, 3, '2024-08-27 08:26:14', '2024-08-26 17:01:08', '2024-09-23', '打卡成功', '830 Tianbei 1st Rd, Luohu District', 71.1414715, 128.3016391, '上班打卡');
INSERT INTO `attendance` VALUES (179, 3, '2024-09-13 08:53:43', '2024-09-04 17:21:10', '2024-08-12', '打卡成功', '268 Shanhu Rd', 132.9183276, 106.3175508, '上班打卡');
INSERT INTO `attendance` VALUES (180, 1, '2024-08-19 08:21:26', '2024-08-30 17:35:34', '2024-08-01', '打卡成功', '866 028 County Rd, Yanqing District', 123.1071356, 144.6514584, '上班打卡');
INSERT INTO `attendance` VALUES (181, 1, '2024-08-12 08:22:01', '2024-08-30 17:24:54', '2024-08-26', '打卡成功', '989 NO.6, YuShuang Road, ChengHua Distric', 7.4085664, 97.8811518, '上班打卡');
INSERT INTO `attendance` VALUES (182, 1, '2024-08-16 08:16:07', '2024-08-19 17:24:03', '2024-09-05', '打卡成功', '891 3rd Section Hongxing Road, Jinjiang District', 128.3820666, 67.2769994, '上班打卡');
INSERT INTO `attendance` VALUES (183, 4, '2024-09-13 08:34:09', '2024-08-28 17:04:11', '2024-08-19', '打卡成功', '45 Tianhe Road, Tianhe District', 52.5061790, 112.0664854, '上班打卡');
INSERT INTO `attendance` VALUES (184, 4, '2024-09-12 08:19:09', '2024-08-01 17:48:28', '2024-08-14', '打卡成功', '695 Qingshuihe 1st Rd, Luohu District', 112.4620702, 72.9052687, '上班打卡');
INSERT INTO `attendance` VALUES (185, 3, '2024-09-19 08:22:29', '2024-09-20 17:42:53', '2024-09-17', '打卡成功', '393 Binchuan Rd, Minhang District', 125.5891073, 87.5415770, '上班打卡');
INSERT INTO `attendance` VALUES (186, 1, '2024-09-13 08:52:01', '2024-08-27 17:21:51', '2024-08-09', '打卡成功', '107 Dongtai 5th St', 148.0080606, 163.4531323, '上班打卡');
INSERT INTO `attendance` VALUES (187, 3, '2024-08-21 08:13:29', '2024-08-15 17:30:27', '2024-08-26', '打卡成功', '738 3rd Section Hongxing Road, Jinjiang District', 36.2315925, 110.5508987, '上班打卡');
INSERT INTO `attendance` VALUES (188, 2, '2024-08-21 08:49:54', '2024-08-05 17:24:32', '2024-09-03', '打卡成功', '712 Jianxiang Rd, Pudong', 25.1951758, 29.7297597, '上班打卡');
INSERT INTO `attendance` VALUES (189, 4, '2024-08-05 08:38:17', '2024-08-05 17:43:25', '2024-09-13', '打卡成功', '463 Binchuan Rd, Minhang District', 21.3463105, 41.1467720, '上班打卡');
INSERT INTO `attendance` VALUES (190, 4, '2024-08-05 08:02:34', '2024-09-06 17:17:55', '2024-08-26', '打卡成功', '918 Jianxiang Rd, Pudong', 73.5475563, 34.8997825, '上班打卡');
INSERT INTO `attendance` VALUES (191, 6, '2024-08-26 08:01:03', '2024-08-21 17:27:55', '2024-08-29', '打卡成功', '14 Lefeng 6th Rd', 7.3296447, 149.2481510, '上班打卡');
INSERT INTO `attendance` VALUES (192, 1, '2024-09-13 08:32:09', '2024-09-16 17:20:19', '2024-08-05', '打卡成功', '504 4th Section  Renmin South Road, Jinjiang District', 139.5496069, 146.5012997, '上班打卡');
INSERT INTO `attendance` VALUES (193, 1, '2024-08-21 08:15:06', '2024-09-03 17:40:57', '2024-09-20', '打卡成功', '467 68 Qinghe Middle St, Haidian District', 13.2714959, 114.9655653, '上班打卡');
INSERT INTO `attendance` VALUES (194, 1, '2024-08-16 08:21:36', '2024-08-12 17:03:32', '2024-08-05', '打卡成功', '789 Jingtian East 1st St, Futian District', 114.7686211, 70.3392715, '上班打卡');
INSERT INTO `attendance` VALUES (195, 4, '2024-09-10 08:09:09', '2024-08-30 17:43:50', '2024-09-02', '打卡成功', '686 Shennan E Rd, Cai Wu Wei, Luohu District', 87.4394003, 154.3888193, '上班打卡');
INSERT INTO `attendance` VALUES (196, 6, '2024-08-19 08:32:54', '2024-09-05 17:14:00', '2024-09-20', '打卡成功', '372 East Wangfujing Street, Dongcheng District ', 106.3444212, 123.6493968, '上班打卡');
INSERT INTO `attendance` VALUES (197, 5, '2024-08-27 08:22:55', '2024-08-19 17:10:34', '2024-08-08', '打卡成功', '979 W Ring Rd, Buji Town, Longgang', 95.7936806, 1.0326165, '上班打卡');
INSERT INTO `attendance` VALUES (198, 2, '2024-09-20 08:49:45', '2024-09-12 17:17:28', '2024-09-06', '打卡成功', '691 Huaxia St, Jinghua Shangquan', 75.4519187, 10.0037792, '上班打卡');
INSERT INTO `attendance` VALUES (199, 6, '2024-08-02 08:28:51', '2024-08-28 17:05:21', '2024-08-26', '打卡成功', '604 Xiaoping E Rd, Baiyun ', 38.8560722, 98.5301054, '上班打卡');
INSERT INTO `attendance` VALUES (200, 4, '2024-08-16 08:14:57', '2024-09-06 17:43:56', '2024-09-05', '打卡成功', '132 Huanqu South Street 2nd Alley', 126.5182562, 94.9279240, '上班打卡');
INSERT INTO `attendance` VALUES (201, 4, '2024-08-28 08:09:00', '2024-09-19 17:09:12', '2024-09-19', '打卡成功', '404 Huanqu South Street 2nd Alley', 130.5789016, 21.6678422, '上班打卡');
INSERT INTO `attendance` VALUES (202, 4, '2024-08-28 08:12:08', '2024-09-20 17:51:30', '2024-09-03', '打卡成功', '737 Qingshuihe 1st Rd, Luohu District', 5.7532468, 107.7583560, '上班打卡');
INSERT INTO `attendance` VALUES (203, 3, '2024-08-19 08:53:20', '2024-09-06 17:57:36', '2024-08-05', '打卡成功', '934 Xue Yuan Yi Xiang, Longgang', 130.9819685, 26.3619495, '上班打卡');
INSERT INTO `attendance` VALUES (204, 1, '2024-09-06 08:26:48', '2024-09-03 17:18:37', '2024-08-28', '打卡成功', '506 2nd Zhongshan Road, Yuexiu District', 78.7608656, 71.3629059, '上班打卡');
INSERT INTO `attendance` VALUES (205, 3, '2024-08-13 08:32:52', '2024-08-20 17:01:26', '2024-09-04', '打卡成功', '488 Tianbei 1st Rd, Luohu District', 57.7330642, 95.3366310, '上班打卡');
INSERT INTO `attendance` VALUES (206, 6, '2024-08-27 08:07:32', '2024-09-18 17:02:02', '2024-09-11', '打卡成功', 'No.150, Dongsan Road, Erxianqiao, Chenghua District', 114.4384912, 30.4946947, '上班打卡');
INSERT INTO `attendance` VALUES (207, 1, '2024-09-06 08:43:29', '2024-08-30 17:34:31', '2024-08-19', '打卡成功', '299 3rd Section Hongxing Road, Jinjiang District', 89.4934010, 115.2651670, '上班打卡');
INSERT INTO `attendance` VALUES (208, 1, '2024-08-02 08:59:12', '2024-09-13 17:50:38', '2024-09-11', '打卡成功', '737 2nd Zhongshan Road, Yuexiu District', 127.3831210, 0.7523136, '上班打卡');
INSERT INTO `attendance` VALUES (209, 1, '2024-08-05 08:09:41', '2024-08-12 17:55:30', '2024-08-05', '打卡成功', '492 Tangyuan Street 5th Alley, Airport Road, Baiyun', 141.5408038, 110.0127061, '上班打卡');
INSERT INTO `attendance` VALUES (210, 4, '2024-08-05 08:50:12', '2024-09-11 17:54:44', '2024-09-05', '打卡成功', '971 Zhongshan 5th Rd, Zimaling Shangquan', 138.8792985, 101.0545428, '上班打卡');
INSERT INTO `attendance` VALUES (211, 5, '2024-08-19 08:11:08', '2024-09-19 17:11:23', '2024-08-07', '打卡成功', '701 Jingtian East 1st St, Futian District', 122.5830078, 121.0101547, '上班打卡');
INSERT INTO `attendance` VALUES (212, 1, '2024-08-05 08:03:43', '2024-08-12 17:50:43', '2024-09-17', '打卡成功', '255 Jianxiang Rd, Pudong', 122.2651923, 58.0198962, '上班打卡');
INSERT INTO `attendance` VALUES (213, 3, '2024-08-05 08:05:20', '2024-09-09 17:28:51', '2024-08-16', '打卡成功', '287 Huanqu South Street 2nd Alley', 6.8884439, 165.2970146, '上班打卡');
INSERT INTO `attendance` VALUES (214, 2, '2024-08-26 08:59:02', '2024-08-19 17:39:09', '2024-08-16', '打卡成功', '951 2nd Zhongshan Road, Yuexiu District', 138.2122456, 23.1475023, '上班打卡');
INSERT INTO `attendance` VALUES (215, 5, '2024-09-17 08:52:12', '2024-09-05 17:57:54', '2024-09-13', '打卡成功', '421 Shennan Ave, Futian District', 72.1206940, 151.2414674, '上班打卡');
INSERT INTO `attendance` VALUES (216, 3, '2024-09-16 08:34:39', '2024-09-20 17:22:51', '2024-08-14', '打卡成功', '126 Shanhu Rd', 91.4215766, 64.6268059, '上班打卡');
INSERT INTO `attendance` VALUES (217, 3, '2024-08-19 08:25:21', '2024-09-19 17:47:29', '2024-08-13', '打卡成功', '525 Qingshuihe 1st Rd, Luohu District', 66.3590627, 125.8401035, '上班打卡');
INSERT INTO `attendance` VALUES (218, 5, '2024-08-05 08:53:06', '2024-08-08 17:34:02', '2024-08-28', '打卡成功', '251 68 Qinghe Middle St, Haidian District', 129.4310321, 26.9876566, '上班打卡');
INSERT INTO `attendance` VALUES (219, 4, '2024-09-18 08:35:30', '2024-09-06 17:04:07', '2024-08-29', '打卡成功', '427 4th Section  Renmin South Road, Jinjiang District', 120.4888106, 149.4678314, '上班打卡');
INSERT INTO `attendance` VALUES (220, 6, '2024-09-02 08:04:17', '2024-09-16 17:47:39', '2024-08-02', '打卡成功', '420 W Ring Rd, Buji Town, Longgang', 16.3595302, 1.8950300, '上班打卡');
INSERT INTO `attendance` VALUES (221, 5, '2024-09-10 08:32:04', '2024-09-13 17:11:46', '2024-09-09', '打卡成功', '569 028 County Rd, Yanqing District', 75.6424795, 84.5125327, '上班打卡');
INSERT INTO `attendance` VALUES (222, 1, '2024-08-26 08:44:42', '2024-08-19 17:31:30', '2024-08-20', '打卡成功', '522 Tianbei 1st Rd, Luohu District', 59.7780903, 12.8988071, '上班打卡');
INSERT INTO `attendance` VALUES (223, 4, '2024-08-26 08:37:16', '2024-08-20 17:07:39', '2024-09-03', '打卡成功', '289 Kengmei 15th Alley', 10.5850749, 102.4929012, '上班打卡');
INSERT INTO `attendance` VALUES (224, 3, '2024-09-03 08:32:53', '2024-08-07 17:40:35', '2024-09-19', '打卡成功', '506 W Ring Rd, Buji Town, Longgang', 93.1527028, 144.8372577, '上班打卡');
INSERT INTO `attendance` VALUES (225, 3, '2024-09-10 08:39:49', '2024-08-28 17:06:50', '2024-08-05', '打卡成功', '144 Yueliu Rd, Fangshan District', 120.7883334, 105.1772894, '上班打卡');
INSERT INTO `attendance` VALUES (226, 3, '2024-08-05 08:26:54', '2024-08-30 17:50:49', '2024-08-16', '打卡成功', '120 Huanqu South Street 2nd Alley', 56.8786692, 79.7633013, '上班打卡');
INSERT INTO `attendance` VALUES (227, 1, '2024-09-04 08:21:24', '2024-08-30 17:04:54', '2024-08-01', '打卡成功', '576 East Wangfujing Street, Dongcheng District ', 86.2708515, 66.6355982, '上班打卡');
INSERT INTO `attendance` VALUES (228, 2, '2024-08-08 08:53:34', '2024-09-17 17:31:59', '2024-08-15', '打卡成功', '826 Ganlan Rd, Pudong', 16.0638781, 49.6520381, '上班打卡');
INSERT INTO `attendance` VALUES (229, 4, '2024-08-01 08:53:02', '2024-09-12 17:37:16', '2024-09-12', '打卡成功', '8 Sanlitun Road, Chaoyang District', 102.9683432, 88.2776362, '上班打卡');
INSERT INTO `attendance` VALUES (230, 3, '2024-09-12 08:51:25', '2024-08-12 17:06:46', '2024-09-23', '打卡成功', 'No. 505, Shuangqing Rd, Chenghua District', 149.4072787, 18.3955919, '上班打卡');
INSERT INTO `attendance` VALUES (231, 5, '2024-08-12 08:19:27', '2024-09-20 17:15:26', '2024-09-13', '打卡成功', '79 Xiaoping E Rd, Baiyun ', 86.5775203, 43.5962385, '上班打卡');
INSERT INTO `attendance` VALUES (232, 5, '2024-08-12 08:37:46', '2024-09-06 17:42:17', '2024-09-12', '打卡成功', '831 Dongtai 5th St', 74.3794826, 45.6692448, '上班打卡');
INSERT INTO `attendance` VALUES (233, 4, '2024-08-02 08:35:05', '2024-08-28 17:05:03', '2024-08-13', '打卡成功', '540 Sanlitun Road, Chaoyang District', 111.3254054, 159.6169545, '上班打卡');
INSERT INTO `attendance` VALUES (234, 4, '2024-09-16 08:35:17', '2024-08-26 17:51:56', '2024-09-17', '打卡成功', '778 Shanhu Rd', 28.2323846, 72.9706234, '上班打卡');
INSERT INTO `attendance` VALUES (235, 2, '2024-09-06 08:19:23', '2024-08-12 17:09:11', '2024-08-30', '打卡成功', '126 Huaxia St, Jinghua Shangquan', 106.8284409, 123.5022482, '上班打卡');
INSERT INTO `attendance` VALUES (236, 3, '2024-08-19 08:13:43', '2024-09-13 17:14:59', '2024-08-22', '打卡成功', '240 Xue Yuan Yi Xiang, Longgang', 27.4206074, 117.3165587, '上班打卡');
INSERT INTO `attendance` VALUES (237, 6, '2024-08-16 08:26:49', '2024-09-20 17:42:11', '2024-08-29', '打卡成功', '73 Xue Yuan Yi Xiang, Longgang', 70.0824707, 111.2477140, '上班打卡');
INSERT INTO `attendance` VALUES (238, 6, '2024-09-03 08:58:44', '2024-08-13 17:09:42', '2024-09-13', '打卡成功', '429 Tangyuan Street 5th Alley, Airport Road, Baiyun', 26.5109478, 129.4526023, '上班打卡');
INSERT INTO `attendance` VALUES (239, 1, '2024-09-23 08:03:34', '2024-08-19 17:10:57', '2024-08-13', '打卡成功', '504 Jingtian East 1st St, Futian District', 114.2471118, 60.1110627, '上班打卡');
INSERT INTO `attendance` VALUES (240, 4, '2024-09-19 08:52:57', '2024-08-26 17:24:13', '2024-08-23', '打卡成功', '603 Huaxia St, Jinghua Shangquan', 99.2443266, 83.6262625, '上班打卡');
INSERT INTO `attendance` VALUES (241, 1, '2024-08-06 08:41:49', '2024-08-28 17:49:12', '2024-09-03', '打卡成功', '38 Binchuan Rd, Minhang District', 117.1826248, 116.9787336, '上班打卡');
INSERT INTO `attendance` VALUES (242, 3, '2024-08-09 08:11:12', '2024-09-05 17:54:54', '2024-08-28', '打卡成功', '930 028 County Rd, Yanqing District', 67.4171751, 82.7137622, '上班打卡');
INSERT INTO `attendance` VALUES (243, 4, '2024-08-06 08:50:14', '2024-08-15 17:11:56', '2024-08-30', '打卡成功', '627 Binchuan Rd, Minhang District', 63.5323379, 93.0075931, '上班打卡');
INSERT INTO `attendance` VALUES (244, 3, '2024-09-03 08:58:55', '2024-08-22 17:49:48', '2024-08-21', '打卡成功', 'No. 75, Shuangqing Rd, Chenghua District', 84.3611875, 0.0851146, '上班打卡');
INSERT INTO `attendance` VALUES (245, 2, '2024-08-28 08:34:23', '2024-08-09 17:28:35', '2024-09-11', '打卡成功', '726 Middle Huaihai Road, Huangpu District', 23.0056254, 147.2811219, '上班打卡');
INSERT INTO `attendance` VALUES (246, 5, '2024-08-07 08:05:25', '2024-08-02 17:00:36', '2024-09-09', '打卡成功', 'No.428, Dongsan Road, Erxianqiao, Chenghua District', 77.1074230, 145.9442333, '上班打卡');
INSERT INTO `attendance` VALUES (247, 2, '2024-08-05 08:22:57', '2024-08-12 17:59:07', '2024-08-09', '打卡成功', '577 Ganlan Rd, Pudong', 81.7668393, 34.9802562, '上班打卡');
INSERT INTO `attendance` VALUES (248, 1, '2024-08-30 08:10:16', '2024-08-22 17:19:57', '2024-08-26', '打卡成功', '965 Jiangnan West Road, Haizhu District', 74.6331344, 141.8263654, '上班打卡');
INSERT INTO `attendance` VALUES (249, 6, '2024-08-27 08:47:05', '2024-08-29 17:01:38', '2024-08-08', '打卡成功', '849 Dong Zhi Men, Dongcheng District', 65.3231645, 6.9833721, '上班打卡');
INSERT INTO `attendance` VALUES (250, 5, '2024-09-06 08:47:15', '2024-08-21 17:28:47', '2024-09-17', '打卡成功', '6 Jingtian East 1st St, Futian District', 67.8041907, 80.3636779, '上班打卡');
INSERT INTO `attendance` VALUES (251, 1, '2024-08-20 08:45:58', '2024-09-05 17:27:40', '2024-08-19', '打卡成功', '729 Ganlan Rd, Pudong', 52.0797765, 12.8082447, '上班打卡');
INSERT INTO `attendance` VALUES (252, 4, '2024-08-06 08:28:20', '2024-08-28 17:03:44', '2024-09-06', '打卡成功', '242 68 Qinghe Middle St, Haidian District', 141.8400259, 37.6285682, '上班打卡');
INSERT INTO `attendance` VALUES (253, 4, '2024-08-05 08:28:24', '2024-09-13 17:26:28', '2024-08-30', '打卡成功', '594 West Chang\'an Avenue, Xicheng District', 91.6783214, 9.2300240, '上班打卡');
INSERT INTO `attendance` VALUES (254, 4, '2024-09-13 08:19:55', '2024-08-06 17:09:31', '2024-08-16', '打卡成功', '944 Middle Huaihai Road, Huangpu District', 81.7500366, 4.0909533, '上班打卡');
INSERT INTO `attendance` VALUES (255, 4, '2024-09-20 08:22:29', '2024-08-26 17:55:15', '2024-09-04', '打卡成功', '465 Dongtai 5th St', 58.2829580, 122.5792151, '上班打卡');
INSERT INTO `attendance` VALUES (256, 4, '2024-08-05 08:41:30', '2024-09-06 17:21:42', '2024-09-04', '打卡成功', '177 Hongqiao Rd., Xu Hui District', 18.6701056, 61.6853063, '上班打卡');
INSERT INTO `attendance` VALUES (257, 4, '2024-08-05 08:35:26', '2024-08-30 17:45:59', '2024-09-03', '打卡成功', '992 68 Qinghe Middle St, Haidian District', 131.9116114, 75.4887945, '上班打卡');
INSERT INTO `attendance` VALUES (258, 1, '2024-08-29 08:01:09', '2024-08-19 17:35:42', '2024-08-20', '打卡成功', '282 Qingshuihe 1st Rd, Luohu District', 99.4584799, 100.3725603, '上班打卡');
INSERT INTO `attendance` VALUES (259, 2, '2024-09-06 08:03:29', '2024-09-09 17:36:53', '2024-09-12', '打卡成功', '131 Binchuan Rd, Minhang District', 87.4781693, 34.7477265, '上班打卡');
INSERT INTO `attendance` VALUES (260, 2, '2024-09-20 08:28:27', '2024-08-08 17:35:48', '2024-08-29', '打卡成功', '241 Yueliu Rd, Fangshan District', 40.1526622, 88.4641327, '上班打卡');
INSERT INTO `attendance` VALUES (261, 4, '2024-08-21 08:57:20', '2024-09-04 17:32:59', '2024-08-21', '打卡成功', '227 Tianhe Road, Tianhe District', 37.3323784, 148.3424382, '上班打卡');
INSERT INTO `attendance` VALUES (262, 6, '2024-09-04 08:28:57', '2024-08-15 17:18:33', '2024-08-01', '打卡成功', '821 FuXingMenNei Street, XiCheng District', 53.0660932, 77.4182945, '上班打卡');
INSERT INTO `attendance` VALUES (263, 3, '2024-09-11 08:02:28', '2024-08-05 17:58:05', '2024-09-17', '打卡成功', '770 Xiaoping E Rd, Baiyun ', 43.1896905, 163.6036416, '上班打卡');
INSERT INTO `attendance` VALUES (264, 6, '2024-09-12 08:30:20', '2024-09-09 17:25:21', '2024-08-21', '打卡成功', '526 Shennan E Rd, Cai Wu Wei, Luohu District', 76.8281669, 29.0327768, '上班打卡');
INSERT INTO `attendance` VALUES (265, 5, '2024-08-28 08:23:36', '2024-08-30 17:02:49', '2024-08-21', '打卡成功', '961 Zhongshan 5th Rd, Zimaling Shangquan', 83.0047062, 146.8434624, '上班打卡');
INSERT INTO `attendance` VALUES (266, 1, '2024-09-03 08:14:41', '2024-09-13 17:32:04', '2024-09-06', '打卡成功', '770 Jingtian East 1st St, Futian District', 101.2924986, 38.8961131, '上班打卡');
INSERT INTO `attendance` VALUES (267, 1, '2024-09-20 08:27:00', '2024-08-15 17:09:57', '2024-08-19', '打卡成功', '165 Huanqu South Street 2nd Alley', 102.5293544, 7.9881646, '上班打卡');
INSERT INTO `attendance` VALUES (268, 1, '2024-08-23 08:39:39', '2024-09-05 17:51:38', '2024-09-20', '打卡成功', '845 FuXingMenNei Street, XiCheng District', 20.2323250, 56.9331739, '上班打卡');
INSERT INTO `attendance` VALUES (269, 1, '2024-09-16 08:04:07', '2024-08-05 17:40:15', '2024-09-13', '打卡成功', '832 NO.6, YuShuang Road, ChengHua Distric', 82.3994188, 114.8357638, '上班打卡');
INSERT INTO `attendance` VALUES (270, 5, '2024-08-12 08:22:35', '2024-09-11 17:40:55', '2024-08-13', '打卡成功', '179 Tianhe Road, Tianhe District', 10.6519465, 102.4230433, '上班打卡');
INSERT INTO `attendance` VALUES (271, 4, '2024-08-20 08:56:38', '2024-09-02 17:58:25', '2024-09-02', '打卡成功', '257 West Chang\'an Avenue, Xicheng District', 93.3103288, 41.5305442, '上班打卡');
INSERT INTO `attendance` VALUES (272, 1, '2024-08-06 08:51:57', '2024-09-12 17:15:42', '2024-08-05', '打卡成功', '787 W Ring Rd, Buji Town, Longgang', 70.2838660, 97.6983343, '上班打卡');
INSERT INTO `attendance` VALUES (273, 6, '2024-08-12 08:46:36', '2024-09-16 17:53:37', '2024-09-20', '打卡成功', '490 FuXingMenNei Street, XiCheng District', 80.7648494, 108.8443611, '上班打卡');
INSERT INTO `attendance` VALUES (274, 2, '2024-08-20 08:56:06', '2024-09-06 17:03:07', '2024-09-12', '打卡成功', '866 Middle Huaihai Road, Huangpu District', 44.8162616, 47.5478014, '上班打卡');
INSERT INTO `attendance` VALUES (275, 5, '2024-08-19 08:03:35', '2024-08-28 17:34:03', '2024-08-06', '打卡成功', '331 Xue Yuan Yi Xiang, Longgang', 71.6277119, 43.4960150, '上班打卡');
INSERT INTO `attendance` VALUES (276, 3, '2024-08-27 08:31:46', '2024-08-14 17:49:02', '2024-08-30', '打卡成功', '269 Xiaoping E Rd, Baiyun ', 71.4199116, 75.6375591, '上班打卡');
INSERT INTO `attendance` VALUES (277, 1, '2024-08-07 08:15:15', '2024-08-30 17:44:57', '2024-08-05', '打卡成功', '477 East Wangfujing Street, Dongcheng District ', 6.4564516, 33.7327788, '上班打卡');
INSERT INTO `attendance` VALUES (278, 1, '2024-08-12 08:59:54', '2024-09-06 17:31:05', '2024-08-19', '打卡成功', '55 Dong Zhi Men, Dongcheng District', 2.2044270, 40.8505547, '上班打卡');
INSERT INTO `attendance` VALUES (279, 6, '2024-09-03 08:45:55', '2024-08-28 17:07:39', '2024-08-16', '打卡成功', '700 Xiaoping E Rd, Baiyun ', 134.9379600, 142.1387012, '上班打卡');
INSERT INTO `attendance` VALUES (280, 5, '2024-09-16 08:41:47', '2024-08-26 17:11:28', '2024-09-13', '打卡成功', '640 Hongqiao Rd., Xu Hui District', 84.0479288, 3.3446191, '上班打卡');
INSERT INTO `attendance` VALUES (281, 2, '2024-09-05 08:32:29', '2024-08-26 17:31:18', '2024-08-21', '打卡成功', '295 Binchuan Rd, Minhang District', 77.8875987, 24.3658355, '上班打卡');
INSERT INTO `attendance` VALUES (282, 1, '2024-09-13 08:45:43', '2024-08-05 17:40:23', '2024-08-01', '打卡成功', '28 Dong Zhi Men, Dongcheng District', 5.5163228, 28.6017115, '上班打卡');
INSERT INTO `attendance` VALUES (283, 3, '2024-08-19 08:54:27', '2024-08-19 17:04:24', '2024-08-08', '打卡成功', '527 West Chang\'an Avenue, Xicheng District', 58.6059756, 90.5959752, '上班打卡');
INSERT INTO `attendance` VALUES (284, 6, '2024-08-15 08:42:58', '2024-08-22 17:41:23', '2024-09-17', '打卡成功', '217 FuXingMenNei Street, XiCheng District', 148.4573305, 65.5039074, '上班打卡');
INSERT INTO `attendance` VALUES (285, 1, '2024-09-10 08:10:56', '2024-09-20 17:29:51', '2024-08-19', '打卡成功', '146 Huanqu South Street 2nd Alley', 9.1394588, 139.3400981, '上班打卡');
INSERT INTO `attendance` VALUES (286, 3, '2024-09-20 08:20:59', '2024-09-09 17:59:09', '2024-09-16', '打卡成功', '272 3rd Section Hongxing Road, Jinjiang District', 42.1798122, 113.7535576, '上班打卡');
INSERT INTO `attendance` VALUES (287, 1, '2024-09-23 08:53:22', '2024-09-09 17:42:19', '2024-09-20', '打卡成功', '658 Dong Zhi Men, Dongcheng District', 28.1425107, 81.4084455, '上班打卡');
INSERT INTO `attendance` VALUES (288, 3, '2024-08-19 08:43:39', '2024-08-19 17:16:10', '2024-08-19', '打卡成功', '582 Lefeng 6th Rd', 10.7027877, 161.3069213, '上班打卡');
INSERT INTO `attendance` VALUES (289, 5, '2024-09-20 08:08:39', '2024-08-02 17:58:10', '2024-09-20', '打卡成功', '917 Middle Huaihai Road, Huangpu District', 134.4697666, 163.4389249, '上班打卡');
INSERT INTO `attendance` VALUES (290, 2, '2024-08-12 08:37:49', '2024-09-18 17:46:30', '2024-08-19', '打卡成功', '787 Tianbei 1st Rd, Luohu District', 14.4667920, 0.1703526, '上班打卡');
INSERT INTO `attendance` VALUES (291, 5, '2024-08-07 08:30:10', '2024-08-26 17:50:45', '2024-08-30', '打卡成功', '131 Tianhe Road, Tianhe District', 27.0422110, 162.1087630, '上班打卡');
INSERT INTO `attendance` VALUES (292, 2, '2024-09-12 08:07:25', '2024-08-19 17:57:36', '2024-08-26', '打卡成功', '574 Dongtai 5th St', 72.6537917, 148.7621122, '上班打卡');
INSERT INTO `attendance` VALUES (293, 1, '2024-08-27 08:50:03', '2024-09-13 17:03:10', '2024-09-18', '打卡成功', '415 Tianhe Road, Tianhe District', 13.2366601, 50.0841141, '上班打卡');
INSERT INTO `attendance` VALUES (294, 1, '2024-08-02 08:29:42', '2024-09-03 17:57:17', '2024-08-12', '打卡成功', '557 68 Qinghe Middle St, Haidian District', 54.7788420, 10.2772870, '上班打卡');
INSERT INTO `attendance` VALUES (295, 1, '2024-09-20 08:41:22', '2024-09-16 17:56:34', '2024-09-23', '打卡成功', '974 3rd Section Hongxing Road, Jinjiang District', 89.3382121, 39.3291023, '上班打卡');
INSERT INTO `attendance` VALUES (296, 2, '2024-08-30 08:36:27', '2024-08-19 17:33:44', '2024-09-13', '打卡成功', '433 NO.6, YuShuang Road, ChengHua Distric', 34.6659479, 59.4538695, '上班打卡');
INSERT INTO `attendance` VALUES (297, 1, '2024-08-16 08:47:33', '2024-09-04 17:04:55', '2024-08-26', '打卡成功', '832 NO.6, YuShuang Road, ChengHua Distric', 89.5976471, 27.6871427, '上班打卡');
INSERT INTO `attendance` VALUES (298, 4, '2024-08-06 08:40:11', '2024-08-05 17:04:14', '2024-09-10', '打卡成功', '65 Yueliu Rd, Fangshan District', 116.6509454, 77.4639525, '上班打卡');
INSERT INTO `attendance` VALUES (299, 3, '2024-08-01 08:24:24', '2024-09-05 17:04:49', '2024-08-19', '打卡成功', '699 028 County Rd, Yanqing District', 113.7804610, 135.8367426, '上班打卡');
INSERT INTO `attendance` VALUES (300, 5, '2024-08-20 08:14:41', '2024-09-20 17:07:36', '2024-09-06', '打卡成功', '243 Ganlan Rd, Pudong', 145.3619096, 35.9698323, '上班打卡');
INSERT INTO `attendance` VALUES (301, 1, '2024-08-07 08:48:43', '2024-08-19 17:56:01', '2024-09-03', '打卡成功', '288 Zhongshan 5th Rd, Zimaling Shangquan', 36.7851387, 32.4657044, '上班打卡');
INSERT INTO `attendance` VALUES (302, 5, '2024-09-10 08:44:59', '2024-08-16 17:24:27', '2024-08-19', '打卡成功', '746 Shennan Ave, Futian District', 83.9253360, 162.6924560, '上班打卡');
INSERT INTO `attendance` VALUES (303, 4, '2024-09-06 08:11:29', '2024-09-06 17:31:31', '2024-08-01', '打卡成功', '27 68 Qinghe Middle St, Haidian District', 74.1195455, 59.3491057, '上班打卡');
INSERT INTO `attendance` VALUES (304, 2, '2024-09-20 08:20:41', '2024-09-06 17:19:13', '2024-09-05', '打卡成功', '310 Dongtai 5th St', 90.7605383, 86.0844546, '上班打卡');
INSERT INTO `attendance` VALUES (305, 5, '2024-08-02 08:47:05', '2024-09-03 17:15:09', '2024-09-12', '打卡成功', '117 4th Section  Renmin South Road, Jinjiang District', 72.6465406, 13.6179691, '上班打卡');
INSERT INTO `attendance` VALUES (306, 1, '2024-08-23 08:29:37', '2024-09-12 17:47:55', '2024-09-04', '打卡成功', 'No. 25, Shuangqing Rd, Chenghua District', 147.2372976, 105.2733885, '上班打卡');
INSERT INTO `attendance` VALUES (307, 3, '2024-09-04 08:26:27', '2024-08-16 17:04:32', '2024-08-05', '打卡成功', '895 3rd Section Hongxing Road, Jinjiang District', 101.5863450, 157.6908548, '上班打卡');
INSERT INTO `attendance` VALUES (308, 4, '2024-08-22 08:30:37', '2024-08-27 17:13:28', '2024-09-09', '打卡成功', '225 W Ring Rd, Buji Town, Longgang', 41.3171315, 110.7547464, '上班打卡');
INSERT INTO `attendance` VALUES (309, 1, '2024-08-23 08:24:24', '2024-08-21 17:26:23', '2024-08-30', '打卡成功', '393 NO.6, YuShuang Road, ChengHua Distric', 99.7659841, 153.6835920, '上班打卡');
INSERT INTO `attendance` VALUES (310, 4, '2024-09-20 08:44:40', '2024-08-13 17:04:18', '2024-08-05', '打卡成功', '199 Tangyuan Street 5th Alley, Airport Road, Baiyun', 79.6401615, 44.1611024, '上班打卡');
INSERT INTO `attendance` VALUES (311, 2, '2024-08-05 08:09:05', '2024-09-04 17:01:01', '2024-08-02', '打卡成功', '616 Jiangnan West Road, Haizhu District', 63.2250071, 42.9993995, '上班打卡');
INSERT INTO `attendance` VALUES (312, 1, '2024-08-19 08:40:31', '2024-09-13 17:02:37', '2024-09-02', '打卡成功', '86 Huaxia St, Jinghua Shangquan', 11.1289655, 105.1016303, '上班打卡');
INSERT INTO `attendance` VALUES (313, 6, '2024-08-01 08:56:47', '2024-08-15 17:44:43', '2024-09-05', '打卡成功', '527 Shanhu Rd', 102.7642291, 2.6341198, '上班打卡');
INSERT INTO `attendance` VALUES (314, 4, '2024-08-07 08:27:05', '2024-09-23 17:17:22', '2024-09-13', '打卡成功', '177 Jingtian East 1st St, Futian District', 61.5720733, 52.6336132, '上班打卡');
INSERT INTO `attendance` VALUES (315, 3, '2024-09-12 08:15:18', '2024-08-30 17:49:42', '2024-08-02', '打卡成功', '565 Jianxiang Rd, Pudong', 27.9307048, 3.5238396, '上班打卡');
INSERT INTO `attendance` VALUES (316, 4, '2024-09-12 08:05:56', '2024-09-06 17:49:46', '2024-09-13', '打卡成功', '804 Hongqiao Rd., Xu Hui District', 7.9050017, 63.3203695, '上班打卡');
INSERT INTO `attendance` VALUES (317, 1, '2024-08-12 08:24:42', '2024-08-26 17:22:28', '2024-08-26', '打卡成功', '918 Sanlitun Road, Chaoyang District', 84.7750197, 46.4686575, '上班打卡');
INSERT INTO `attendance` VALUES (318, 6, '2024-09-13 08:53:25', '2024-09-13 17:20:14', '2024-08-26', '打卡成功', '347 FuXingMenNei Street, XiCheng District', 46.4840798, 165.7167237, '上班打卡');
INSERT INTO `attendance` VALUES (319, 4, '2024-08-21 08:55:01', '2024-09-13 17:03:20', '2024-09-18', '打卡成功', '315 Kengmei 15th Alley', 46.3931691, 23.3648648, '上班打卡');
INSERT INTO `attendance` VALUES (320, 1, '2024-08-19 08:44:48', '2024-09-04 17:52:21', '2024-08-14', '打卡成功', '371 Hongqiao Rd., Xu Hui District', 99.0577332, 35.6438800, '上班打卡');
INSERT INTO `attendance` VALUES (321, 5, '2024-09-13 08:51:00', '2024-09-20 17:40:01', '2024-09-03', '打卡成功', '14 West Chang\'an Avenue, Xicheng District', 82.2900656, 26.6866494, '上班打卡');
INSERT INTO `attendance` VALUES (322, 1, '2024-09-20 08:25:16', '2024-09-23 17:06:21', '2024-08-06', '打卡成功', '229 Tianhe Road, Tianhe District', 74.8772610, 46.4493478, '上班打卡');
INSERT INTO `attendance` VALUES (323, 1, '2024-08-12 08:42:21', '2024-08-06 17:33:10', '2024-08-21', '打卡成功', '531 Shennan E Rd, Cai Wu Wei, Luohu District', 7.0185591, 89.5458102, '上班打卡');
INSERT INTO `attendance` VALUES (324, 2, '2024-08-08 08:52:52', '2024-08-22 17:34:45', '2024-09-10', '打卡成功', '540 Jiangnan West Road, Haizhu District', 98.8163137, 61.5236379, '上班打卡');
INSERT INTO `attendance` VALUES (325, 4, '2024-08-12 08:43:00', '2024-08-26 17:20:42', '2024-09-16', '打卡成功', '673 Yueliu Rd, Fangshan District', 80.2293406, 13.8002488, '上班打卡');
INSERT INTO `attendance` VALUES (326, 1, '2024-08-15 08:29:53', '2024-08-06 17:40:48', '2024-09-19', '打卡成功', '194 2nd Zhongshan Road, Yuexiu District', 138.7723742, 103.5798350, '上班打卡');
INSERT INTO `attendance` VALUES (327, 6, '2024-08-05 08:00:43', '2024-08-22 17:34:44', '2024-09-02', '打卡成功', '501 Ganlan Rd, Pudong', 62.9044385, 107.5536598, '上班打卡');
INSERT INTO `attendance` VALUES (328, 1, '2024-08-05 08:11:48', '2024-08-30 17:24:16', '2024-09-06', '打卡成功', '349 Kengmei 15th Alley', 24.5936659, 39.4139145, '上班打卡');
INSERT INTO `attendance` VALUES (329, 3, '2024-08-26 08:58:56', '2024-08-26 17:14:49', '2024-08-08', '打卡成功', '824 Qingshuihe 1st Rd, Luohu District', 106.7017979, 143.2135486, '上班打卡');
INSERT INTO `attendance` VALUES (330, 6, '2024-08-15 08:38:48', '2024-08-30 17:05:40', '2024-08-29', '打卡成功', '745 Tangyuan Street 5th Alley, Airport Road, Baiyun', 0.7064629, 21.2864968, '上班打卡');
INSERT INTO `attendance` VALUES (331, 2, '2024-08-15 08:55:24', '2024-09-05 17:29:19', '2024-08-13', '打卡成功', '422 Shanhu Rd', 79.5335209, 145.6137848, '上班打卡');
INSERT INTO `attendance` VALUES (332, 1, '2024-09-20 08:02:32', '2024-08-30 17:31:20', '2024-09-12', '打卡成功', '463 2nd Zhongshan Road, Yuexiu District', 68.6962574, 116.9471677, '上班打卡');
INSERT INTO `attendance` VALUES (333, 3, '2024-09-03 08:49:53', '2024-09-10 17:05:59', '2024-09-13', '打卡成功', '172 Shennan Ave, Futian District', 87.0223365, 162.6574569, '上班打卡');
INSERT INTO `attendance` VALUES (334, 5, '2024-09-10 08:48:22', '2024-08-12 17:24:05', '2024-08-21', '打卡成功', '439 Lefeng 6th Rd', 84.0981301, 101.7689281, '上班打卡');
INSERT INTO `attendance` VALUES (335, 2, '2024-09-02 08:33:07', '2024-08-30 17:40:43', '2024-08-23', '打卡成功', '358 Kengmei 15th Alley', 60.4466218, 110.7505350, '上班打卡');
INSERT INTO `attendance` VALUES (336, 6, '2024-08-19 08:43:09', '2024-08-13 17:03:50', '2024-08-06', '打卡成功', '438 Tangyuan Street 5th Alley, Airport Road, Baiyun', 31.6174345, 157.8407543, '上班打卡');
INSERT INTO `attendance` VALUES (337, 4, '2024-08-23 08:58:45', '2024-09-09 17:09:21', '2024-09-13', '打卡成功', '482 Xiaoping E Rd, Baiyun ', 49.6056062, 18.9946039, '上班打卡');
INSERT INTO `attendance` VALUES (338, 1, '2024-08-12 08:33:45', '2024-08-19 17:36:43', '2024-08-12', '打卡成功', '65 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 56.1253447, 155.4617943, '上班打卡');
INSERT INTO `attendance` VALUES (339, 1, '2024-08-12 08:02:25', '2024-08-19 17:17:26', '2024-08-12', '打卡成功', '409 Dong Zhi Men, Dongcheng District', 50.1765456, 127.5968178, '上班打卡');
INSERT INTO `attendance` VALUES (340, 1, '2024-09-18 08:57:00', '2024-09-18 17:21:06', '2024-09-06', '打卡成功', '215 Zhongshan 5th Rd, Zimaling Shangquan', 23.8226459, 8.9578182, '上班打卡');
INSERT INTO `attendance` VALUES (341, 4, '2024-08-26 08:05:41', '2024-08-26 17:37:08', '2024-08-22', '打卡成功', '421 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 127.0485970, 16.1358897, '上班打卡');
INSERT INTO `attendance` VALUES (342, 3, '2024-08-13 08:34:23', '2024-09-06 17:02:18', '2024-08-12', '打卡成功', '101 Tianhe Road, Tianhe District', 53.3692819, 10.1912229, '上班打卡');
INSERT INTO `attendance` VALUES (343, 1, '2024-09-06 08:44:35', '2024-09-18 17:08:10', '2024-08-12', '打卡成功', '626 Qingshuihe 1st Rd, Luohu District', 34.5813702, 135.2165788, '上班打卡');
INSERT INTO `attendance` VALUES (344, 4, '2024-08-26 08:56:47', '2024-08-16 17:20:53', '2024-09-03', '打卡成功', '454 028 County Rd, Yanqing District', 19.1044055, 159.0138234, '上班打卡');
INSERT INTO `attendance` VALUES (345, 1, '2024-09-20 08:05:16', '2024-08-13 17:40:07', '2024-08-12', '打卡成功', '165 Lefeng 6th Rd', 106.6212247, 46.8600763, '上班打卡');
INSERT INTO `attendance` VALUES (346, 5, '2024-09-13 08:10:25', '2024-08-05 17:18:19', '2024-09-20', '打卡成功', '632 4th Section  Renmin South Road, Jinjiang District', 137.5600448, 3.4703687, '上班打卡');
INSERT INTO `attendance` VALUES (347, 4, '2024-08-09 08:09:21', '2024-08-12 17:19:11', '2024-09-06', '打卡成功', '527 Yueliu Rd, Fangshan District', 63.7949352, 148.4221252, '上班打卡');
INSERT INTO `attendance` VALUES (348, 6, '2024-09-02 08:26:22', '2024-09-11 17:21:16', '2024-09-20', '打卡成功', '552 Hongqiao Rd., Xu Hui District', 51.5326982, 123.4463389, '上班打卡');
INSERT INTO `attendance` VALUES (349, 1, '2024-08-27 08:35:32', '2024-08-07 17:29:39', '2024-09-04', '打卡成功', '44 Dong Zhi Men, Dongcheng District', 56.1546836, 6.8596446, '上班打卡');
INSERT INTO `attendance` VALUES (350, 1, '2024-08-26 08:25:30', '2024-08-26 17:53:06', '2024-09-09', '打卡成功', '180 Shennan Ave, Futian District', 115.4730757, 29.2412888, '上班打卡');
INSERT INTO `attendance` VALUES (351, 1, '2024-08-19 08:59:48', '2024-09-09 17:06:10', '2024-09-03', '打卡成功', '981 FuXingMenNei Street, XiCheng District', 142.6998135, 56.8483590, '上班打卡');
INSERT INTO `attendance` VALUES (352, 2, '2024-08-05 08:43:57', '2024-09-06 17:16:50', '2024-09-17', '打卡成功', '965 Xue Yuan Yi Xiang, Longgang', 72.5968226, 77.3164179, '上班打卡');
INSERT INTO `attendance` VALUES (353, 2, '2024-08-14 08:07:32', '2024-09-06 17:22:12', '2024-09-17', '打卡成功', '62 W Ring Rd, Buji Town, Longgang', 28.7391799, 9.4296889, '上班打卡');
INSERT INTO `attendance` VALUES (354, 1, '2024-09-20 08:34:59', '2024-09-13 17:59:52', '2024-08-20', '打卡成功', 'No. 483, Shuangqing Rd, Chenghua District', 81.1747031, 19.8748592, '上班打卡');
INSERT INTO `attendance` VALUES (355, 4, '2024-08-06 08:55:44', '2024-08-28 17:25:09', '2024-08-26', '打卡成功', '111 Sanlitun Road, Chaoyang District', 19.6293992, 122.3343805, '上班打卡');
INSERT INTO `attendance` VALUES (356, 2, '2024-08-26 08:59:48', '2024-08-21 17:42:48', '2024-09-13', '打卡成功', '893 Yueliu Rd, Fangshan District', 73.9568233, 136.4804915, '上班打卡');
INSERT INTO `attendance` VALUES (357, 1, '2024-08-20 08:24:47', '2024-09-20 17:31:37', '2024-08-30', '打卡成功', '985 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 138.1430051, 35.0068733, '上班打卡');
INSERT INTO `attendance` VALUES (358, 3, '2024-09-10 08:17:58', '2024-08-02 17:18:20', '2024-09-23', '打卡成功', '631 Binchuan Rd, Minhang District', 20.5201690, 32.4530986, '上班打卡');
INSERT INTO `attendance` VALUES (359, 5, '2024-08-30 08:02:34', '2024-09-05 17:56:00', '2024-08-30', '打卡成功', '526 Xiaoping E Rd, Baiyun ', 130.9293264, 113.7598082, '上班打卡');
INSERT INTO `attendance` VALUES (360, 5, '2024-09-12 08:25:25', '2024-08-16 17:30:22', '2024-09-20', '打卡成功', '273 Dongtai 5th St', 104.8707790, 162.2452841, '上班打卡');
INSERT INTO `attendance` VALUES (361, 3, '2024-09-06 08:13:26', '2024-09-13 17:12:28', '2024-08-19', '打卡成功', '732 Middle Huaihai Road, Huangpu District', 66.1935136, 76.5406675, '上班打卡');
INSERT INTO `attendance` VALUES (362, 5, '2024-09-23 08:31:31', '2024-08-21 17:14:22', '2024-08-14', '打卡成功', '489 Qingshuihe 1st Rd, Luohu District', 78.1982581, 75.2456096, '上班打卡');
INSERT INTO `attendance` VALUES (363, 2, '2024-09-18 08:21:50', '2024-09-13 17:05:01', '2024-08-19', '打卡成功', '31 Huanqu South Street 2nd Alley', 106.9736472, 98.5559201, '上班打卡');
INSERT INTO `attendance` VALUES (364, 2, '2024-09-05 08:18:01', '2024-08-20 17:03:46', '2024-08-19', '打卡成功', '852 Ganlan Rd, Pudong', 6.1572727, 145.0797794, '上班打卡');
INSERT INTO `attendance` VALUES (365, 1, '2024-08-21 08:21:41', '2024-08-30 17:41:33', '2024-08-05', '打卡成功', '564 Qingshuihe 1st Rd, Luohu District', 67.6628512, 119.6718407, '上班打卡');
INSERT INTO `attendance` VALUES (366, 2, '2024-09-19 08:58:51', '2024-09-13 17:00:44', '2024-08-27', '打卡成功', '362 Xiaoping E Rd, Baiyun ', 53.6921052, 88.3876018, '上班打卡');
INSERT INTO `attendance` VALUES (367, 3, '2024-08-07 08:50:02', '2024-08-06 17:39:30', '2024-09-20', '打卡成功', '590 028 County Rd, Yanqing District', 144.7160890, 153.5196509, '上班打卡');
INSERT INTO `attendance` VALUES (368, 5, '2024-08-30 08:27:04', '2024-09-23 17:00:14', '2024-08-16', '打卡成功', '6 Shennan Ave, Futian District', 49.4026631, 17.8280277, '上班打卡');
INSERT INTO `attendance` VALUES (369, 1, '2024-08-09 08:59:59', '2024-08-29 17:28:40', '2024-08-16', '打卡成功', '832 Jingtian East 1st St, Futian District', 115.5318663, 76.0004205, '上班打卡');
INSERT INTO `attendance` VALUES (370, 2, '2024-09-19 08:23:01', '2024-09-20 17:03:40', '2024-09-16', '打卡成功', '740 Sanlitun Road, Chaoyang District', 68.4097848, 7.3309092, '上班打卡');
INSERT INTO `attendance` VALUES (371, 1, '2024-09-12 08:20:13', '2024-09-06 17:12:50', '2024-08-01', '打卡成功', '57 68 Qinghe Middle St, Haidian District', 13.7604096, 142.3189686, '上班打卡');
INSERT INTO `attendance` VALUES (372, 2, '2024-08-07 08:04:21', '2024-08-19 17:40:01', '2024-08-19', '打卡成功', '301 West Chang\'an Avenue, Xicheng District', 35.1372079, 74.3756406, '上班打卡');
INSERT INTO `attendance` VALUES (373, 2, '2024-09-13 08:49:44', '2024-08-22 17:36:24', '2024-08-29', '打卡成功', '669 Qingshuihe 1st Rd, Luohu District', 120.5057833, 52.0588777, '上班打卡');
INSERT INTO `attendance` VALUES (374, 3, '2024-08-12 08:05:43', '2024-08-05 17:51:57', '2024-08-05', '打卡成功', '93 Dongtai 5th St', 119.3189334, 18.6622433, '上班打卡');
INSERT INTO `attendance` VALUES (375, 1, '2024-08-13 08:52:49', '2024-08-13 17:50:03', '2024-09-02', '打卡成功', '924 Shennan Ave, Futian District', 44.6271007, 152.8329772, '上班打卡');
INSERT INTO `attendance` VALUES (376, 4, '2024-08-26 08:49:36', '2024-08-22 17:29:56', '2024-09-20', '打卡成功', '942 3rd Section Hongxing Road, Jinjiang District', 116.3817443, 32.1767994, '上班打卡');
INSERT INTO `attendance` VALUES (377, 2, '2024-09-10 08:09:14', '2024-09-05 17:31:16', '2024-09-13', '打卡成功', '254 Shanhu Rd', 101.9683073, 126.7505874, '上班打卡');
INSERT INTO `attendance` VALUES (378, 6, '2024-08-09 08:07:14', '2024-09-13 17:50:44', '2024-08-19', '打卡成功', '400 68 Qinghe Middle St, Haidian District', 42.5737416, 17.1876723, '上班打卡');
INSERT INTO `attendance` VALUES (379, 3, '2024-08-26 08:18:53', '2024-09-18 17:34:02', '2024-08-22', '打卡成功', '613 Dongtai 5th St', 47.4672498, 46.3377444, '上班打卡');
INSERT INTO `attendance` VALUES (380, 1, '2024-08-13 08:00:12', '2024-08-08 17:39:56', '2024-08-26', '打卡成功', '408 2nd Zhongshan Road, Yuexiu District', 41.0716139, 36.0882332, '上班打卡');
INSERT INTO `attendance` VALUES (381, 1, '2024-08-22 08:01:04', '2024-09-03 17:26:27', '2024-08-06', '打卡成功', '444 4th Section  Renmin South Road, Jinjiang District', 72.6406280, 150.5889859, '上班打卡');
INSERT INTO `attendance` VALUES (382, 4, '2024-08-30 08:16:33', '2024-09-04 17:22:59', '2024-09-06', '打卡成功', '31 Shanhu Rd', 109.1735162, 138.7610217, '上班打卡');
INSERT INTO `attendance` VALUES (383, 6, '2024-09-06 08:47:04', '2024-09-20 17:03:21', '2024-09-17', '打卡成功', 'No. 247, Shuangqing Rd, Chenghua District', 78.0785175, 91.2063283, '上班打卡');
INSERT INTO `attendance` VALUES (384, 4, '2024-09-13 08:16:00', '2024-08-30 17:26:11', '2024-09-06', '打卡成功', 'No. 517, Shuangqing Rd, Chenghua District', 62.5833025, 143.0739397, '上班打卡');
INSERT INTO `attendance` VALUES (385, 4, '2024-09-20 08:56:37', '2024-08-02 17:07:29', '2024-08-05', '打卡成功', '418 Lefeng 6th Rd', 146.6389092, 40.5748515, '上班打卡');
INSERT INTO `attendance` VALUES (386, 4, '2024-09-10 08:41:39', '2024-08-14 17:19:08', '2024-08-01', '打卡成功', '559 68 Qinghe Middle St, Haidian District', 64.3628047, 114.5833256, '上班打卡');
INSERT INTO `attendance` VALUES (387, 4, '2024-08-13 08:07:46', '2024-08-06 17:18:54', '2024-08-26', '打卡成功', '41 Jingtian East 1st St, Futian District', 134.0275161, 128.8502107, '上班打卡');
INSERT INTO `attendance` VALUES (388, 4, '2024-09-06 08:29:25', '2024-08-12 17:57:58', '2024-08-20', '打卡成功', '451 Xue Yuan Yi Xiang, Longgang', 46.6407002, 111.0531037, '上班打卡');
INSERT INTO `attendance` VALUES (389, 1, '2024-09-13 08:46:23', '2024-08-23 17:47:22', '2024-09-05', '打卡成功', '766 Zhongshan 5th Rd, Zimaling Shangquan', 42.2156558, 68.7740136, '上班打卡');
INSERT INTO `attendance` VALUES (390, 2, '2024-08-27 08:32:30', '2024-08-09 17:22:49', '2024-08-30', '打卡成功', '710 028 County Rd, Yanqing District', 85.0415964, 155.4202364, '上班打卡');
INSERT INTO `attendance` VALUES (391, 5, '2024-08-19 08:46:50', '2024-09-17 17:37:13', '2024-08-19', '打卡成功', 'No.375, Dongsan Road, Erxianqiao, Chenghua District', 46.7579263, 68.9013410, '上班打卡');
INSERT INTO `attendance` VALUES (392, 5, '2024-08-15 08:32:25', '2024-08-02 17:39:44', '2024-08-08', '打卡成功', '122 Hongqiao Rd., Xu Hui District', 72.6590560, 124.3162282, '上班打卡');
INSERT INTO `attendance` VALUES (393, 3, '2024-08-12 08:03:22', '2024-09-16 17:11:19', '2024-08-20', '打卡成功', '521 Jingtian East 1st St, Futian District', 120.0758104, 104.4585946, '上班打卡');
INSERT INTO `attendance` VALUES (394, 5, '2024-08-30 08:34:47', '2024-09-02 17:23:27', '2024-09-18', '打卡成功', '402 Xue Yuan Yi Xiang, Longgang', 82.3152143, 8.1866012, '上班打卡');
INSERT INTO `attendance` VALUES (395, 2, '2024-08-30 08:01:03', '2024-08-19 17:04:34', '2024-08-05', '打卡成功', '504 Xue Yuan Yi Xiang, Longgang', 40.5048994, 106.1236412, '上班打卡');
INSERT INTO `attendance` VALUES (396, 2, '2024-08-16 08:21:10', '2024-09-17 17:51:45', '2024-08-12', '打卡成功', '507 Tangyuan Street 5th Alley, Airport Road, Baiyun', 14.3817246, 53.2448187, '上班打卡');
INSERT INTO `attendance` VALUES (397, 1, '2024-09-05 08:13:30', '2024-08-30 17:28:44', '2024-09-11', '打卡成功', '261 East Wangfujing Street, Dongcheng District ', 72.2582699, 73.5075947, '上班打卡');
INSERT INTO `attendance` VALUES (398, 2, '2024-09-19 08:17:57', '2024-08-23 17:12:09', '2024-08-19', '打卡成功', '396 Shennan E Rd, Cai Wu Wei, Luohu District', 88.8493317, 79.8429402, '上班打卡');
INSERT INTO `attendance` VALUES (399, 3, '2024-09-18 08:45:13', '2024-08-08 17:04:54', '2024-09-18', '打卡成功', '422 NO.6, YuShuang Road, ChengHua Distric', 108.7198008, 6.4699507, '上班打卡');
INSERT INTO `attendance` VALUES (400, 4, '2024-08-13 08:11:57', '2024-08-06 17:36:23', '2024-09-02', '打卡成功', '174 Sanlitun Road, Chaoyang District', 101.5199198, 55.0148127, '上班打卡');
INSERT INTO `attendance` VALUES (401, 1, '2024-08-23 08:15:07', '2024-08-30 17:28:22', '2024-09-23', '打卡成功', '384 Shennan E Rd, Cai Wu Wei, Luohu District', 51.2916814, 96.8393210, '上班打卡');
INSERT INTO `attendance` VALUES (402, 5, '2024-09-13 08:08:09', '2024-08-26 17:55:19', '2024-09-06', '打卡成功', '93 3rd Section Hongxing Road, Jinjiang District', 15.1186495, 12.5498918, '上班打卡');
INSERT INTO `attendance` VALUES (403, 1, '2024-08-15 08:51:19', '2024-08-22 17:25:37', '2024-08-15', '打卡成功', '389 2nd Zhongshan Road, Yuexiu District', 131.6980378, 2.6079733, '上班打卡');
INSERT INTO `attendance` VALUES (404, 5, '2024-08-19 08:02:44', '2024-08-28 17:29:19', '2024-09-13', '打卡成功', '429 Jianxiang Rd, Pudong', 87.2379272, 125.0544889, '上班打卡');
INSERT INTO `attendance` VALUES (405, 5, '2024-09-17 08:38:06', '2024-08-12 17:14:41', '2024-08-19', '打卡成功', '502 Zhongshan 5th Rd, Zimaling Shangquan', 123.4551441, 35.2229356, '上班打卡');
INSERT INTO `attendance` VALUES (406, 1, '2024-08-19 08:36:10', '2024-08-27 17:25:06', '2024-09-09', '打卡成功', '216 Jianxiang Rd, Pudong', 28.4936840, 114.0131217, '上班打卡');
INSERT INTO `attendance` VALUES (407, 5, '2024-08-12 08:49:33', '2024-08-12 17:27:57', '2024-08-27', '打卡成功', '45 Dong Zhi Men, Dongcheng District', 21.0656245, 71.8404596, '上班打卡');
INSERT INTO `attendance` VALUES (408, 2, '2024-08-13 08:56:36', '2024-08-28 17:38:28', '2024-08-21', '打卡成功', '72 Kengmei 15th Alley', 101.0233066, 130.2259092, '上班打卡');
INSERT INTO `attendance` VALUES (409, 2, '2024-09-16 08:20:51', '2024-08-05 17:44:33', '2024-08-05', '打卡成功', '549 Jianxiang Rd, Pudong', 20.7057461, 68.0154095, '上班打卡');
INSERT INTO `attendance` VALUES (410, 2, '2024-09-11 08:58:28', '2024-09-10 17:55:05', '2024-08-23', '打卡成功', '503 FuXingMenNei Street, XiCheng District', 94.4236684, 79.4431439, '上班打卡');
INSERT INTO `attendance` VALUES (411, 3, '2024-08-19 08:50:06', '2024-09-23 17:56:52', '2024-09-03', '打卡成功', '422 Tianhe Road, Tianhe District', 143.7822676, 65.6374781, '上班打卡');
INSERT INTO `attendance` VALUES (412, 1, '2024-09-19 08:23:30', '2024-09-18 17:40:06', '2024-08-19', '打卡成功', '769 Tianhe Road, Tianhe District', 55.7041125, 56.6081425, '上班打卡');
INSERT INTO `attendance` VALUES (413, 1, '2024-09-19 08:50:54', '2024-09-04 17:45:26', '2024-08-05', '打卡成功', '175 Huaxia St, Jinghua Shangquan', 51.3229913, 22.6980221, '上班打卡');
INSERT INTO `attendance` VALUES (414, 6, '2024-09-06 08:33:24', '2024-08-26 17:25:32', '2024-09-20', '打卡成功', '94 Zhongshan 5th Rd, Zimaling Shangquan', 35.8267910, 69.5884133, '上班打卡');
INSERT INTO `attendance` VALUES (415, 2, '2024-09-03 08:00:41', '2024-09-03 17:51:23', '2024-08-21', '打卡成功', '148 Jiangnan West Road, Haizhu District', 76.7615853, 11.0668121, '上班打卡');
INSERT INTO `attendance` VALUES (416, 4, '2024-08-05 08:27:53', '2024-08-26 17:30:50', '2024-09-20', '打卡成功', '776 Huanqu South Street 2nd Alley', 50.7926508, 30.4133149, '上班打卡');
INSERT INTO `attendance` VALUES (417, 3, '2024-08-21 08:39:30', '2024-08-30 17:20:38', '2024-08-05', '打卡成功', '359 W Ring Rd, Buji Town, Longgang', 103.6513751, 34.8564402, '上班打卡');
INSERT INTO `attendance` VALUES (418, 4, '2024-08-05 08:00:05', '2024-08-20 17:51:07', '2024-08-19', '打卡成功', '350 Shennan E Rd, Cai Wu Wei, Luohu District', 114.7746003, 145.4288229, '上班打卡');
INSERT INTO `attendance` VALUES (419, 2, '2024-08-26 08:43:10', '2024-08-30 17:41:28', '2024-09-17', '打卡成功', '794 West Chang\'an Avenue, Xicheng District', 128.7865477, 108.5836639, '上班打卡');
INSERT INTO `attendance` VALUES (420, 3, '2024-09-02 08:44:15', '2024-08-05 17:27:00', '2024-08-30', '打卡成功', '962 Binchuan Rd, Minhang District', 65.9235827, 114.1342775, '上班打卡');
INSERT INTO `attendance` VALUES (421, 3, '2024-08-30 08:57:42', '2024-09-06 17:24:43', '2024-08-19', '打卡成功', '528 Huaxia St, Jinghua Shangquan', 85.4077181, 162.5442822, '上班打卡');
INSERT INTO `attendance` VALUES (422, 5, '2024-08-29 08:38:27', '2024-09-20 17:23:30', '2024-08-12', '打卡成功', '264 Hongqiao Rd., Xu Hui District', 4.2609037, 142.2532395, '上班打卡');
INSERT INTO `attendance` VALUES (423, 4, '2024-08-28 08:41:51', '2024-09-03 17:38:42', '2024-09-03', '打卡成功', '590 Sanlitun Road, Chaoyang District', 24.6496400, 30.4570467, '上班打卡');
INSERT INTO `attendance` VALUES (424, 6, '2024-08-07 08:53:44', '2024-08-02 17:08:12', '2024-08-01', '打卡成功', 'No. 654, Shuangqing Rd, Chenghua District', 128.4781351, 131.7942951, '上班打卡');
INSERT INTO `attendance` VALUES (425, 5, '2024-09-20 08:08:53', '2024-09-17 17:58:50', '2024-08-12', '打卡成功', '392 Shennan Ave, Futian District', 139.9354927, 56.8439780, '上班打卡');
INSERT INTO `attendance` VALUES (426, 1, '2024-08-01 08:28:49', '2024-08-26 17:05:44', '2024-08-15', '打卡成功', '546 FuXingMenNei Street, XiCheng District', 119.6652836, 71.0309506, '上班打卡');
INSERT INTO `attendance` VALUES (427, 1, '2024-08-12 08:59:07', '2024-09-20 17:20:08', '2024-08-07', '打卡成功', '168 Sanlitun Road, Chaoyang District', 143.8128009, 118.5321405, '上班打卡');
INSERT INTO `attendance` VALUES (428, 1, '2024-08-13 08:54:05', '2024-09-16 17:26:46', '2024-08-01', '打卡成功', '983 Middle Huaihai Road, Huangpu District', 113.4436177, 165.8984015, '上班打卡');
INSERT INTO `attendance` VALUES (429, 2, '2024-08-29 08:10:01', '2024-08-12 17:34:18', '2024-08-12', '打卡成功', '127 Tianhe Road, Tianhe District', 10.8935050, 126.4287168, '上班打卡');
INSERT INTO `attendance` VALUES (430, 5, '2024-08-26 08:49:57', '2024-08-13 17:54:26', '2024-08-29', '打卡成功', '798 West Chang\'an Avenue, Xicheng District', 53.7246877, 98.5131247, '上班打卡');
INSERT INTO `attendance` VALUES (431, 5, '2024-08-02 08:17:16', '2024-08-26 17:12:25', '2024-08-30', '打卡成功', '713 NO.6, YuShuang Road, ChengHua Distric', 133.5219656, 1.8186665, '上班打卡');
INSERT INTO `attendance` VALUES (432, 6, '2024-08-22 08:02:46', '2024-08-30 17:33:48', '2024-09-20', '打卡成功', '728 Tangyuan Street 5th Alley, Airport Road, Baiyun', 92.7234825, 54.2769584, '上班打卡');
INSERT INTO `attendance` VALUES (433, 3, '2024-09-10 08:58:39', '2024-09-06 17:09:14', '2024-09-06', '打卡成功', '951 Kengmei 15th Alley', 124.3653182, 106.8663811, '上班打卡');
INSERT INTO `attendance` VALUES (434, 1, '2024-08-05 08:37:34', '2024-09-11 17:33:37', '2024-09-05', '打卡成功', '6 Lefeng 6th Rd', 133.0808799, 99.5330853, '上班打卡');
INSERT INTO `attendance` VALUES (435, 4, '2024-08-07 08:30:35', '2024-09-09 17:53:04', '2024-08-29', '打卡成功', '2 FuXingMenNei Street, XiCheng District', 98.8924354, 63.2925884, '上班打卡');
INSERT INTO `attendance` VALUES (436, 1, '2024-08-05 08:17:56', '2024-08-21 17:42:38', '2024-08-30', '打卡成功', '145 68 Qinghe Middle St, Haidian District', 138.9629871, 137.4903038, '上班打卡');
INSERT INTO `attendance` VALUES (437, 1, '2024-08-19 08:38:37', '2024-08-26 17:22:27', '2024-09-16', '打卡成功', '297 Xiaoping E Rd, Baiyun ', 144.6093877, 12.0278468, '上班打卡');
INSERT INTO `attendance` VALUES (438, 5, '2024-09-13 08:48:45', '2024-08-21 17:58:20', '2024-08-29', '打卡成功', '510 Dong Zhi Men, Dongcheng District', 147.7080567, 37.2065023, '上班打卡');
INSERT INTO `attendance` VALUES (439, 2, '2024-08-30 08:41:20', '2024-08-19 17:07:43', '2024-08-16', '打卡成功', '279 4th Section  Renmin South Road, Jinjiang District', 136.2537716, 137.7771022, '上班打卡');
INSERT INTO `attendance` VALUES (440, 1, '2024-08-09 08:27:05', '2024-08-15 17:01:02', '2024-09-06', '打卡成功', '727 3rd Section Hongxing Road, Jinjiang District', 48.4063784, 154.8982140, '上班打卡');
INSERT INTO `attendance` VALUES (441, 5, '2024-08-20 08:00:54', '2024-09-20 17:23:03', '2024-08-27', '打卡成功', '171 Xiaoping E Rd, Baiyun ', 63.2194630, 104.8698056, '上班打卡');
INSERT INTO `attendance` VALUES (442, 1, '2024-08-16 08:06:23', '2024-09-23 17:05:37', '2024-09-23', '打卡成功', '815 Sanlitun Road, Chaoyang District', 102.8642341, 3.0737921, '上班打卡');
INSERT INTO `attendance` VALUES (443, 5, '2024-08-07 08:47:13', '2024-09-20 17:51:02', '2024-08-21', '打卡成功', '441 Zhongshan 5th Rd, Zimaling Shangquan', 40.9380339, 125.6926841, '上班打卡');
INSERT INTO `attendance` VALUES (444, 4, '2024-08-30 08:10:37', '2024-08-01 17:31:33', '2024-09-20', '打卡成功', '944 Shennan E Rd, Cai Wu Wei, Luohu District', 58.8549483, 147.9368524, '上班打卡');
INSERT INTO `attendance` VALUES (445, 1, '2024-08-23 08:11:12', '2024-08-23 17:57:32', '2024-08-13', '打卡成功', '199 Middle Huaihai Road, Huangpu District', 1.6448733, 69.5238885, '上班打卡');
INSERT INTO `attendance` VALUES (446, 1, '2024-08-19 08:32:04', '2024-09-17 17:20:45', '2024-09-10', '打卡成功', '144 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 12.9384707, 147.4684317, '上班打卡');
INSERT INTO `attendance` VALUES (447, 3, '2024-08-28 08:41:53', '2024-08-02 17:30:24', '2024-09-09', '打卡成功', '146 Jingtian East 1st St, Futian District', 7.8690967, 51.1196003, '上班打卡');
INSERT INTO `attendance` VALUES (448, 2, '2024-08-28 08:49:00', '2024-08-05 17:58:45', '2024-08-23', '打卡成功', '433 Huanqu South Street 2nd Alley', 83.8818477, 123.6281632, '上班打卡');
INSERT INTO `attendance` VALUES (449, 4, '2024-08-05 08:53:40', '2024-09-06 17:42:17', '2024-09-20', '打卡成功', '595 Middle Huaihai Road, Huangpu District', 28.3800662, 18.5652297, '上班打卡');
INSERT INTO `attendance` VALUES (450, 1, '2024-09-20 08:54:43', '2024-09-13 17:48:47', '2024-09-06', '打卡成功', '930 Tianhe Road, Tianhe District', 145.8144275, 18.8363392, '上班打卡');
INSERT INTO `attendance` VALUES (451, 4, '2024-08-21 08:34:58', '2024-08-02 17:16:14', '2024-09-18', '打卡成功', '12 Huaxia St, Jinghua Shangquan', 4.2365960, 78.3778536, '上班打卡');
INSERT INTO `attendance` VALUES (452, 1, '2024-08-27 08:17:41', '2024-08-26 17:09:37', '2024-08-09', '打卡成功', '714 Tangyuan Street 5th Alley, Airport Road, Baiyun', 110.5853961, 101.8696377, '上班打卡');
INSERT INTO `attendance` VALUES (453, 1, '2024-09-11 08:17:46', '2024-09-13 17:07:32', '2024-08-13', '打卡成功', '312 Tianhe Road, Tianhe District', 77.0465451, 146.6805219, '上班打卡');
INSERT INTO `attendance` VALUES (454, 6, '2024-09-11 08:10:56', '2024-08-09 17:12:28', '2024-09-20', '打卡成功', '875 4th Section  Renmin South Road, Jinjiang District', 89.0267116, 150.4070303, '上班打卡');
INSERT INTO `attendance` VALUES (455, 3, '2024-08-02 08:12:41', '2024-08-30 17:41:32', '2024-08-27', '打卡成功', '620 Zhongshan 5th Rd, Zimaling Shangquan', 6.5411751, 62.0941274, '上班打卡');
INSERT INTO `attendance` VALUES (456, 1, '2024-08-19 08:26:49', '2024-08-06 17:30:01', '2024-09-23', '打卡成功', '761 Tianbei 1st Rd, Luohu District', 101.1453174, 56.0754363, '上班打卡');
INSERT INTO `attendance` VALUES (457, 1, '2024-08-05 08:38:43', '2024-09-13 17:36:24', '2024-09-19', '打卡成功', '294 Binchuan Rd, Minhang District', 142.1198462, 127.6544258, '上班打卡');
INSERT INTO `attendance` VALUES (458, 1, '2024-09-06 08:56:41', '2024-09-18 17:43:31', '2024-08-02', '打卡成功', '348 Tangyuan Street 5th Alley, Airport Road, Baiyun', 116.7378746, 82.9328149, '上班打卡');
INSERT INTO `attendance` VALUES (459, 2, '2024-09-18 08:51:34', '2024-09-06 17:24:25', '2024-09-06', '打卡成功', '347 East Wangfujing Street, Dongcheng District ', 94.4558304, 121.7430890, '上班打卡');
INSERT INTO `attendance` VALUES (460, 6, '2024-09-06 08:52:04', '2024-08-19 17:07:07', '2024-08-19', '打卡成功', '307 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 54.4864559, 8.8249685, '上班打卡');
INSERT INTO `attendance` VALUES (461, 4, '2024-09-17 08:25:55', '2024-08-22 17:12:00', '2024-08-12', '打卡成功', '690 Huaxia St, Jinghua Shangquan', 117.1990704, 135.0235681, '上班打卡');
INSERT INTO `attendance` VALUES (462, 5, '2024-09-11 08:12:21', '2024-08-05 17:57:35', '2024-09-09', '打卡成功', '826 Dong Zhi Men, Dongcheng District', 113.1047653, 133.9084838, '上班打卡');
INSERT INTO `attendance` VALUES (463, 1, '2024-09-10 08:10:58', '2024-08-16 17:56:13', '2024-09-10', '打卡成功', '126 Tangyuan Street 5th Alley, Airport Road, Baiyun', 117.6198875, 87.5416301, '上班打卡');
INSERT INTO `attendance` VALUES (464, 1, '2024-09-20 08:31:23', '2024-08-06 17:21:09', '2024-09-04', '打卡成功', '138 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 126.8054694, 3.8627699, '上班打卡');
INSERT INTO `attendance` VALUES (465, 1, '2024-09-09 08:50:15', '2024-08-21 17:14:57', '2024-08-08', '打卡成功', '349 FuXingMenNei Street, XiCheng District', 32.1077894, 157.7308446, '上班打卡');
INSERT INTO `attendance` VALUES (466, 1, '2024-08-02 08:47:04', '2024-09-19 17:57:26', '2024-09-05', '打卡成功', '551 2nd Zhongshan Road, Yuexiu District', 27.7713714, 161.7672092, '上班打卡');
INSERT INTO `attendance` VALUES (467, 5, '2024-08-19 08:59:22', '2024-09-20 17:25:01', '2024-08-19', '打卡成功', '326 FuXingMenNei Street, XiCheng District', 136.7486684, 96.4402023, '上班打卡');
INSERT INTO `attendance` VALUES (468, 2, '2024-09-20 08:18:01', '2024-08-12 17:14:20', '2024-08-21', '打卡成功', '562 Hongqiao Rd., Xu Hui District', 2.7465674, 71.3354178, '上班打卡');
INSERT INTO `attendance` VALUES (469, 4, '2024-09-06 08:04:33', '2024-09-17 17:41:20', '2024-08-23', '打卡成功', '487 Sanlitun Road, Chaoyang District', 0.8471632, 15.4004987, '上班打卡');
INSERT INTO `attendance` VALUES (470, 4, '2024-08-27 08:25:05', '2024-09-06 17:30:06', '2024-09-13', '打卡成功', 'No.473, Dongsan Road, Erxianqiao, Chenghua District', 127.5977567, 115.4762171, '上班打卡');
INSERT INTO `attendance` VALUES (471, 2, '2024-09-09 08:44:52', '2024-09-06 17:27:53', '2024-08-05', '打卡成功', '108 Tianhe Road, Tianhe District', 18.9017574, 41.8841187, '上班打卡');
INSERT INTO `attendance` VALUES (472, 4, '2024-08-30 08:33:52', '2024-08-22 17:39:02', '2024-09-20', '打卡成功', '477 Jiangnan West Road, Haizhu District', 112.4727702, 103.1658571, '上班打卡');
INSERT INTO `attendance` VALUES (473, 1, '2024-09-12 08:32:03', '2024-09-20 17:01:27', '2024-08-12', '打卡成功', '444 028 County Rd, Yanqing District', 144.9710432, 156.4791310, '上班打卡');
INSERT INTO `attendance` VALUES (474, 4, '2024-09-23 08:30:00', '2024-09-06 17:44:32', '2024-09-17', '打卡成功', '569 FuXingMenNei Street, XiCheng District', 80.9501431, 103.4281705, '上班打卡');
INSERT INTO `attendance` VALUES (475, 3, '2024-09-13 08:29:22', '2024-09-16 17:51:54', '2024-09-12', '打卡成功', '365 Dong Zhi Men, Dongcheng District', 23.5634643, 99.2660918, '上班打卡');
INSERT INTO `attendance` VALUES (476, 3, '2024-09-04 08:06:32', '2024-08-26 17:25:02', '2024-08-19', '打卡成功', '203 West Chang\'an Avenue, Xicheng District', 98.5760855, 55.4175275, '上班打卡');
INSERT INTO `attendance` VALUES (477, 4, '2024-08-21 08:30:44', '2024-08-13 17:09:22', '2024-09-02', '打卡成功', '694 028 County Rd, Yanqing District', 84.7253962, 23.2806111, '上班打卡');
INSERT INTO `attendance` VALUES (478, 1, '2024-08-01 08:44:40', '2024-09-13 17:51:29', '2024-08-05', '打卡成功', '472 Jianxiang Rd, Pudong', 70.1349403, 120.7526306, '上班打卡');
INSERT INTO `attendance` VALUES (479, 6, '2024-09-10 08:59:18', '2024-08-19 17:11:36', '2024-09-10', '打卡成功', '727 Dong Zhi Men, Dongcheng District', 103.9631286, 67.5634156, '上班打卡');
INSERT INTO `attendance` VALUES (480, 5, '2024-08-05 08:44:34', '2024-09-06 17:25:36', '2024-08-27', '打卡成功', '708 West Chang\'an Avenue, Xicheng District', 5.1899312, 46.5701104, '上班打卡');
INSERT INTO `attendance` VALUES (481, 1, '2024-08-14 08:00:25', '2024-08-13 17:22:58', '2024-08-19', '打卡成功', '536 Dong Zhi Men, Dongcheng District', 102.8146164, 64.4404060, '上班打卡');
INSERT INTO `attendance` VALUES (482, 3, '2024-08-26 08:59:14', '2024-08-19 17:08:13', '2024-08-26', '打卡成功', '545 Shennan E Rd, Cai Wu Wei, Luohu District', 29.4478614, 103.6427242, '上班打卡');
INSERT INTO `attendance` VALUES (483, 4, '2024-09-11 08:33:27', '2024-08-29 17:54:20', '2024-09-04', '打卡成功', '847 Huanqu South Street 2nd Alley', 105.8092232, 113.7528090, '上班打卡');
INSERT INTO `attendance` VALUES (484, 5, '2024-08-16 08:01:02', '2024-08-12 17:38:10', '2024-08-29', '打卡成功', '443 Dong Zhi Men, Dongcheng District', 0.8534047, 71.8325735, '上班打卡');
INSERT INTO `attendance` VALUES (485, 6, '2024-08-05 08:16:58', '2024-08-23 17:51:58', '2024-08-19', '打卡成功', '972 Jiangnan West Road, Haizhu District', 75.5501752, 127.8116684, '上班打卡');
INSERT INTO `attendance` VALUES (486, 2, '2024-08-05 08:28:04', '2024-09-04 17:43:54', '2024-09-04', '打卡成功', '947 Kengmei 15th Alley', 60.4802132, 30.9612891, '上班打卡');
INSERT INTO `attendance` VALUES (487, 2, '2024-08-26 08:59:10', '2024-08-28 17:02:34', '2024-08-07', '打卡成功', '701 Tianbei 1st Rd, Luohu District', 101.3100801, 136.8572800, '上班打卡');
INSERT INTO `attendance` VALUES (488, 1, '2024-08-07 08:23:15', '2024-08-19 17:49:53', '2024-08-19', '打卡成功', '696 Hongqiao Rd., Xu Hui District', 20.7887825, 138.1770738, '上班打卡');
INSERT INTO `attendance` VALUES (489, 2, '2024-08-19 08:50:35', '2024-08-05 17:56:53', '2024-08-22', '打卡成功', '659 Jingtian East 1st St, Futian District', 129.6123688, 24.1927500, '上班打卡');
INSERT INTO `attendance` VALUES (490, 5, '2024-09-06 08:38:45', '2024-08-16 17:04:12', '2024-09-20', '打卡成功', '170 Jingtian East 1st St, Futian District', 69.9003447, 1.5367460, '上班打卡');
INSERT INTO `attendance` VALUES (491, 4, '2024-09-06 08:29:19', '2024-08-12 17:39:30', '2024-08-26', '打卡成功', '692 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 30.9720593, 113.6100483, '上班打卡');
INSERT INTO `attendance` VALUES (492, 2, '2024-08-09 08:57:57', '2024-08-30 17:31:01', '2024-08-28', '打卡成功', '70 Xue Yuan Yi Xiang, Longgang', 117.6839749, 90.4986501, '上班打卡');
INSERT INTO `attendance` VALUES (493, 5, '2024-09-16 08:32:34', '2024-08-14 17:14:09', '2024-08-01', '打卡成功', '271 028 County Rd, Yanqing District', 29.4895765, 37.3558306, '上班打卡');
INSERT INTO `attendance` VALUES (494, 6, '2024-08-27 08:36:04', '2024-09-06 17:36:01', '2024-09-03', '打卡成功', '17 Jianxiang Rd, Pudong', 135.1011272, 6.3219387, '上班打卡');
INSERT INTO `attendance` VALUES (495, 3, '2024-08-28 08:15:57', '2024-09-20 17:32:57', '2024-09-17', '打卡成功', '97 Zhongshan 5th Rd, Zimaling Shangquan', 78.0275146, 21.5594836, '上班打卡');
INSERT INTO `attendance` VALUES (496, 1, '2024-08-01 08:01:38', '2024-08-27 17:53:42', '2024-08-12', '打卡成功', '860 Binchuan Rd, Minhang District', 17.2993153, 70.5968551, '上班打卡');
INSERT INTO `attendance` VALUES (497, 6, '2024-09-20 08:19:44', '2024-09-05 17:29:55', '2024-08-23', '打卡成功', '693 Hongqiao Rd., Xu Hui District', 5.2555976, 28.0845323, '上班打卡');
INSERT INTO `attendance` VALUES (498, 4, '2024-08-27 08:50:46', '2024-08-19 17:52:54', '2024-08-06', '打卡成功', '427 Dong Zhi Men, Dongcheng District', 57.3757281, 74.9683355, '上班打卡');
INSERT INTO `attendance` VALUES (499, 3, '2024-08-07 08:38:27', '2024-08-21 17:51:06', '2024-08-19', '打卡成功', '708 Tangyuan Street 5th Alley, Airport Road, Baiyun', 90.9167621, 41.7698837, '上班打卡');
INSERT INTO `attendance` VALUES (500, 5, '2024-08-02 08:08:49', '2024-08-23 17:24:53', '2024-08-02', '打卡成功', '137 Ganlan Rd, Pudong', 103.3696207, 72.6736523, '上班打卡');
INSERT INTO `attendance` VALUES (501, 5, '2024-08-30 08:51:09', '2024-09-19 17:52:58', '2024-09-02', '打卡失败', '815 Zhongshan 5th Rd, Zimaling Shangquan', 56.2341315, 160.8632352, '上班打卡');
INSERT INTO `attendance` VALUES (502, 2, '2024-08-29 08:06:45', '2024-08-05 17:22:41', '2024-09-06', '打卡失败', '339 Jingtian East 1st St, Futian District', 30.7501171, 91.5274098, '上班打卡');
INSERT INTO `attendance` VALUES (503, 4, '2024-09-13 08:51:34', '2024-08-19 17:07:38', '2024-08-19', '打卡失败', '154 W Ring Rd, Buji Town, Longgang', 37.0137130, 82.1531401, '上班打卡');
INSERT INTO `attendance` VALUES (504, 1, '2024-08-30 08:13:06', '2024-09-20 17:53:25', '2024-09-13', '打卡失败', '362 Shennan E Rd, Cai Wu Wei, Luohu District', 118.9516040, 124.7745991, '上班打卡');
INSERT INTO `attendance` VALUES (505, 1, '2024-08-15 08:18:52', '2024-09-23 17:43:04', '2024-08-28', '打卡失败', '603 Qingshuihe 1st Rd, Luohu District', 140.8343300, 133.9437827, '上班打卡');
INSERT INTO `attendance` VALUES (506, 2, '2024-08-01 08:57:10', '2024-08-12 17:09:44', '2024-09-05', '打卡失败', '379 W Ring Rd, Buji Town, Longgang', 127.3469434, 131.8310198, '上班打卡');
INSERT INTO `attendance` VALUES (507, 5, '2024-09-13 08:44:06', '2024-09-03 17:50:53', '2024-09-17', '打卡失败', '861 Dong Zhi Men, Dongcheng District', 6.5266868, 66.8223042, '上班打卡');
INSERT INTO `attendance` VALUES (508, 1, '2024-08-07 08:47:24', '2024-09-20 17:12:02', '2024-08-07', '打卡失败', '984 FuXingMenNei Street, XiCheng District', 104.9522894, 75.5161221, '上班打卡');
INSERT INTO `attendance` VALUES (509, 3, '2024-08-30 08:01:24', '2024-08-23 17:43:53', '2024-09-12', '打卡失败', '709 Shennan E Rd, Cai Wu Wei, Luohu District', 8.6807916, 89.7257026, '上班打卡');
INSERT INTO `attendance` VALUES (510, 5, '2024-08-29 08:38:38', '2024-08-09 17:52:13', '2024-09-19', '打卡失败', '66 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 107.1164190, 155.4484153, '上班打卡');
INSERT INTO `attendance` VALUES (511, 5, '2024-08-26 08:37:55', '2024-08-23 17:20:06', '2024-08-28', '打卡失败', '183 Binchuan Rd, Minhang District', 12.7803273, 23.0731221, '上班打卡');
INSERT INTO `attendance` VALUES (512, 6, '2024-09-12 08:57:14', '2024-09-20 17:02:03', '2024-08-02', '打卡失败', '857 Huanqu South Street 2nd Alley', 34.0413877, 98.1897190, '上班打卡');
INSERT INTO `attendance` VALUES (513, 3, '2024-08-05 08:07:39', '2024-08-21 17:57:47', '2024-08-29', '打卡失败', '688 4th Section  Renmin South Road, Jinjiang District', 71.1317162, 145.6737689, '上班打卡');
INSERT INTO `attendance` VALUES (514, 1, '2024-09-18 08:38:32', '2024-08-30 17:07:52', '2024-08-16', '打卡失败', '866 3rd Section Hongxing Road, Jinjiang District', 88.7754558, 57.4220488, '上班打卡');
INSERT INTO `attendance` VALUES (515, 1, '2024-08-30 08:53:37', '2024-08-26 17:20:00', '2024-09-04', '打卡失败', '817 3rd Section Hongxing Road, Jinjiang District', 30.1698781, 69.4909966, '上班打卡');
INSERT INTO `attendance` VALUES (516, 5, '2024-08-12 08:49:34', '2024-08-19 17:22:34', '2024-09-04', '打卡失败', '846 Jianxiang Rd, Pudong', 104.8757994, 102.2125021, '上班打卡');
INSERT INTO `attendance` VALUES (517, 4, '2024-08-14 08:52:31', '2024-08-05 17:36:12', '2024-08-05', '打卡失败', '189 W Ring Rd, Buji Town, Longgang', 110.1344963, 55.8131946, '上班打卡');
INSERT INTO `attendance` VALUES (518, 1, '2024-08-08 08:53:33', '2024-08-27 17:48:21', '2024-08-26', '打卡失败', '727 Jiangnan West Road, Haizhu District', 51.6656178, 95.3894485, '上班打卡');
INSERT INTO `attendance` VALUES (519, 3, '2024-08-19 08:42:30', '2024-08-26 17:57:27', '2024-09-06', '打卡失败', '624 4th Section  Renmin South Road, Jinjiang District', 112.0430550, 51.4034316, '上班打卡');
INSERT INTO `attendance` VALUES (520, 5, '2024-08-08 08:15:58', '2024-08-27 17:39:24', '2024-09-17', '打卡失败', '192 West Chang\'an Avenue, Xicheng District', 101.8646658, 119.6793730, '上班打卡');
INSERT INTO `attendance` VALUES (521, 3, '2024-08-20 08:27:26', '2024-08-21 17:42:18', '2024-08-16', '打卡失败', '742 FuXingMenNei Street, XiCheng District', 14.8181570, 110.0343006, '上班打卡');
INSERT INTO `attendance` VALUES (522, 5, '2024-08-30 08:14:52', '2024-09-19 17:34:19', '2024-09-06', '打卡失败', '430 Shennan E Rd, Cai Wu Wei, Luohu District', 81.2148870, 1.7019297, '上班打卡');
INSERT INTO `attendance` VALUES (523, 1, '2024-09-18 08:23:15', '2024-09-16 17:31:22', '2024-09-18', '打卡失败', '492 Shennan E Rd, Cai Wu Wei, Luohu District', 115.2001207, 10.3276353, '上班打卡');
INSERT INTO `attendance` VALUES (524, 2, '2024-08-12 08:55:14', '2024-08-27 17:25:26', '2024-08-19', '打卡失败', '777 2nd Zhongshan Road, Yuexiu District', 147.6142295, 75.6198925, '上班打卡');
INSERT INTO `attendance` VALUES (525, 4, '2024-09-18 08:17:05', '2024-09-13 17:00:30', '2024-08-15', '打卡失败', '573 Tianbei 1st Rd, Luohu District', 82.5809413, 133.8702543, '上班打卡');
INSERT INTO `attendance` VALUES (526, 6, '2024-08-26 08:15:51', '2024-08-19 17:51:48', '2024-08-26', '打卡失败', '847 Jiangnan West Road, Haizhu District', 42.2301202, 127.2173707, '上班打卡');
INSERT INTO `attendance` VALUES (527, 3, '2024-08-09 08:06:57', '2024-08-01 17:23:22', '2024-08-19', '打卡失败', '981 Kengmei 15th Alley', 89.6634834, 138.6969356, '上班打卡');
INSERT INTO `attendance` VALUES (528, 5, '2024-08-14 08:56:15', '2024-09-10 17:44:28', '2024-09-05', '打卡失败', '859 Ganlan Rd, Pudong', 106.5589392, 161.4858114, '上班打卡');
INSERT INTO `attendance` VALUES (529, 1, '2024-08-30 08:05:45', '2024-08-06 17:56:24', '2024-09-06', '打卡失败', '929 Jiangnan West Road, Haizhu District', 140.4645581, 156.8824688, '上班打卡');
INSERT INTO `attendance` VALUES (530, 5, '2024-09-05 08:44:54', '2024-08-30 17:26:13', '2024-08-09', '打卡失败', '142 Qingshuihe 1st Rd, Luohu District', 100.4206327, 64.9103900, '上班打卡');
INSERT INTO `attendance` VALUES (531, 1, '2024-08-19 08:14:49', '2024-08-22 17:38:17', '2024-09-16', '打卡失败', '160 68 Qinghe Middle St, Haidian District', 88.0057357, 157.5208261, '上班打卡');
INSERT INTO `attendance` VALUES (532, 6, '2024-08-28 08:41:25', '2024-09-12 17:43:56', '2024-08-26', '打卡失败', 'No. 134, Shuangqing Rd, Chenghua District', 42.7974571, 22.0122498, '上班打卡');
INSERT INTO `attendance` VALUES (533, 1, '2024-08-21 08:26:19', '2024-09-19 17:13:30', '2024-08-27', '打卡失败', '30 W Ring Rd, Buji Town, Longgang', 89.0156371, 7.7272257, '上班打卡');
INSERT INTO `attendance` VALUES (534, 3, '2024-09-06 08:03:22', '2024-08-08 17:51:53', '2024-08-13', '打卡失败', '336 68 Qinghe Middle St, Haidian District', 149.2517268, 105.2878962, '上班打卡');
INSERT INTO `attendance` VALUES (535, 1, '2024-08-16 08:52:53', '2024-08-09 17:43:50', '2024-09-18', '打卡失败', '152 Tangyuan Street 5th Alley, Airport Road, Baiyun', 95.0406354, 22.8369380, '上班打卡');
INSERT INTO `attendance` VALUES (536, 5, '2024-08-05 08:08:22', '2024-08-06 17:16:26', '2024-08-01', '打卡失败', '396 Shanhu Rd', 144.2368776, 103.5563724, '上班打卡');
INSERT INTO `attendance` VALUES (537, 5, '2024-09-20 08:42:24', '2024-08-30 17:50:07', '2024-08-09', '打卡失败', '950 Zhongshan 5th Rd, Zimaling Shangquan', 143.3865724, 107.5935118, '上班打卡');
INSERT INTO `attendance` VALUES (538, 4, '2024-08-07 08:28:01', '2024-08-26 17:34:16', '2024-09-19', '打卡失败', '721 Sanlitun Road, Chaoyang District', 17.4615007, 15.6569960, '上班打卡');
INSERT INTO `attendance` VALUES (539, 1, '2024-08-19 08:17:12', '2024-08-30 17:42:14', '2024-09-13', '打卡失败', '750 Hongqiao Rd., Xu Hui District', 52.2118678, 0.5305053, '上班打卡');
INSERT INTO `attendance` VALUES (540, 5, '2024-09-11 08:41:27', '2024-08-26 17:26:06', '2024-08-13', '打卡失败', '321 Tianbei 1st Rd, Luohu District', 88.7111592, 80.6383321, '上班打卡');
INSERT INTO `attendance` VALUES (541, 3, '2024-08-29 08:42:06', '2024-09-19 17:37:36', '2024-09-20', '打卡失败', '175 4th Section  Renmin South Road, Jinjiang District', 54.5953826, 49.2782561, '上班打卡');
INSERT INTO `attendance` VALUES (542, 3, '2024-08-21 08:10:26', '2024-08-06 17:35:39', '2024-08-05', '打卡失败', '531 Shanhu Rd', 144.2972071, 53.4811769, '上班打卡');
INSERT INTO `attendance` VALUES (543, 5, '2024-09-20 08:08:19', '2024-08-30 17:43:15', '2024-09-06', '打卡失败', '960 Binchuan Rd, Minhang District', 89.1197257, 24.9086117, '上班打卡');
INSERT INTO `attendance` VALUES (544, 1, '2024-08-30 08:07:06', '2024-08-26 17:34:31', '2024-08-05', '打卡失败', '695 Jingtian East 1st St, Futian District', 57.8554869, 139.4196730, '上班打卡');
INSERT INTO `attendance` VALUES (545, 5, '2024-08-30 08:54:09', '2024-09-09 17:11:28', '2024-08-26', '打卡失败', '987 Huanqu South Street 2nd Alley', 52.7085185, 83.2289479, '上班打卡');
INSERT INTO `attendance` VALUES (546, 5, '2024-08-19 08:20:13', '2024-08-05 17:39:49', '2024-09-02', '打卡失败', 'No.482, Dongsan Road, Erxianqiao, Chenghua District', 119.8326747, 79.6060393, '上班打卡');
INSERT INTO `attendance` VALUES (547, 2, '2024-08-05 08:27:13', '2024-08-22 17:39:44', '2024-09-20', '打卡失败', '820 Yueliu Rd, Fangshan District', 78.3175353, 118.8381325, '上班打卡');
INSERT INTO `attendance` VALUES (548, 2, '2024-08-01 08:22:22', '2024-09-18 17:54:42', '2024-08-16', '打卡失败', '901 Xiaoping E Rd, Baiyun ', 24.4548523, 115.9595722, '上班打卡');
INSERT INTO `attendance` VALUES (549, 1, '2024-09-06 08:57:05', '2024-09-06 17:11:19', '2024-09-06', '打卡失败', '68 Dongtai 5th St', 54.0779397, 107.9968854, '上班打卡');
INSERT INTO `attendance` VALUES (550, 5, '2024-09-02 08:14:50', '2024-08-26 17:35:09', '2024-08-08', '打卡失败', '750 Yueliu Rd, Fangshan District', 121.6176662, 154.6541597, '上班打卡');
INSERT INTO `attendance` VALUES (551, 2, '2024-09-10 08:41:21', '2024-08-12 17:57:58', '2024-08-06', '打卡失败', '253 East Wangfujing Street, Dongcheng District ', 21.2402443, 114.3023228, '上班打卡');
INSERT INTO `attendance` VALUES (552, 1, '2024-08-26 08:23:24', '2024-08-30 17:32:31', '2024-08-26', '打卡失败', '74 Hongqiao Rd., Xu Hui District', 6.4420946, 77.6405399, '上班打卡');
INSERT INTO `attendance` VALUES (553, 1, '2024-09-19 08:22:11', '2024-09-06 17:30:38', '2024-08-14', '打卡失败', '674 68 Qinghe Middle St, Haidian District', 63.1757189, 139.4478007, '上班打卡');
INSERT INTO `attendance` VALUES (554, 5, '2024-08-15 08:26:16', '2024-09-03 17:22:21', '2024-09-09', '打卡失败', '916 Shanhu Rd', 131.9169557, 130.9365505, '上班打卡');
INSERT INTO `attendance` VALUES (555, 5, '2024-08-26 08:45:10', '2024-09-05 17:40:02', '2024-09-20', '打卡失败', '96 2nd Zhongshan Road, Yuexiu District', 142.5953019, 46.7657931, '上班打卡');
INSERT INTO `attendance` VALUES (556, 2, '2024-09-13 08:32:49', '2024-08-27 17:54:21', '2024-08-15', '打卡失败', '524 Ganlan Rd, Pudong', 129.3016787, 149.2946516, '上班打卡');
INSERT INTO `attendance` VALUES (557, 1, '2024-08-19 08:52:21', '2024-09-17 17:41:07', '2024-08-29', '打卡失败', '595 Lefeng 6th Rd', 51.9322892, 13.5015947, '上班打卡');
INSERT INTO `attendance` VALUES (558, 3, '2024-09-20 08:59:25', '2024-08-05 17:57:12', '2024-09-06', '打卡失败', 'No. 624, Shuangqing Rd, Chenghua District', 24.7815587, 109.8027864, '上班打卡');
INSERT INTO `attendance` VALUES (559, 1, '2024-08-28 08:44:41', '2024-09-17 17:28:39', '2024-08-26', '打卡失败', '138 028 County Rd, Yanqing District', 103.3738387, 68.8926815, '上班打卡');
INSERT INTO `attendance` VALUES (560, 4, '2024-08-30 08:22:03', '2024-08-05 17:58:20', '2024-08-06', '打卡失败', '602 Shanhu Rd', 22.7764772, 67.6610684, '上班打卡');
INSERT INTO `attendance` VALUES (561, 5, '2024-08-28 08:50:54', '2024-08-12 17:56:42', '2024-08-16', '打卡失败', '34 Dongtai 5th St', 36.9715602, 14.2965655, '上班打卡');
INSERT INTO `attendance` VALUES (562, 2, '2024-08-19 08:40:58', '2024-08-09 17:56:46', '2024-08-15', '打卡失败', '917 Shanhu Rd', 35.7491111, 91.8680600, '上班打卡');
INSERT INTO `attendance` VALUES (563, 4, '2024-08-26 08:11:00', '2024-08-29 17:31:51', '2024-09-03', '打卡失败', '277 Tianhe Road, Tianhe District', 52.0632629, 160.6540985, '上班打卡');
INSERT INTO `attendance` VALUES (564, 3, '2024-09-10 08:07:12', '2024-09-09 17:37:41', '2024-08-21', '打卡失败', '780 028 County Rd, Yanqing District', 85.9636411, 129.2997699, '上班打卡');
INSERT INTO `attendance` VALUES (565, 1, '2024-08-29 08:43:35', '2024-08-01 17:21:34', '2024-08-27', '打卡失败', 'No.377, Dongsan Road, Erxianqiao, Chenghua District', 120.6775540, 134.1732719, '上班打卡');
INSERT INTO `attendance` VALUES (566, 3, '2024-08-08 08:53:41', '2024-09-20 17:43:37', '2024-09-16', '打卡失败', '845 W Ring Rd, Buji Town, Longgang', 78.7202788, 83.3485951, '上班打卡');
INSERT INTO `attendance` VALUES (567, 1, '2024-08-30 08:18:07', '2024-08-20 17:37:01', '2024-08-30', '打卡失败', '539 Yueliu Rd, Fangshan District', 81.2237971, 70.0355378, '上班打卡');
INSERT INTO `attendance` VALUES (568, 1, '2024-09-20 08:09:53', '2024-08-19 17:13:45', '2024-09-20', '打卡失败', '764 Jingtian East 1st St, Futian District', 3.4596291, 13.8057879, '上班打卡');
INSERT INTO `attendance` VALUES (569, 2, '2024-08-05 08:58:09', '2024-09-17 17:02:20', '2024-08-29', '打卡失败', '85 Shanhu Rd', 100.9062889, 109.4799648, '上班打卡');
INSERT INTO `attendance` VALUES (570, 4, '2024-08-19 08:25:45', '2024-08-06 17:16:40', '2024-08-06', '打卡失败', '734 NO.6, YuShuang Road, ChengHua Distric', 86.2583197, 117.0662317, '上班打卡');
INSERT INTO `attendance` VALUES (571, 1, '2024-08-02 08:45:48', '2024-08-13 17:36:45', '2024-09-13', '打卡失败', '748 Hongqiao Rd., Xu Hui District', 119.1653349, 96.9195617, '上班打卡');
INSERT INTO `attendance` VALUES (572, 4, '2024-08-30 08:47:44', '2024-08-05 17:40:13', '2024-08-12', '打卡失败', '296 Tangyuan Street 5th Alley, Airport Road, Baiyun', 104.0013661, 74.5661986, '上班打卡');
INSERT INTO `attendance` VALUES (573, 4, '2024-08-30 08:38:05', '2024-08-14 17:01:32', '2024-08-16', '打卡失败', '427 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 107.7834547, 15.8615948, '上班打卡');
INSERT INTO `attendance` VALUES (574, 3, '2024-09-16 08:21:16', '2024-08-02 17:57:37', '2024-09-06', '打卡失败', '714 Ganlan Rd, Pudong', 71.4700310, 124.3962029, '上班打卡');
INSERT INTO `attendance` VALUES (575, 1, '2024-08-29 08:32:07', '2024-09-13 17:27:07', '2024-08-12', '打卡失败', '883 Tangyuan Street 5th Alley, Airport Road, Baiyun', 130.2170049, 53.0615086, '上班打卡');
INSERT INTO `attendance` VALUES (576, 5, '2024-09-23 08:00:08', '2024-09-06 17:14:34', '2024-08-26', '打卡失败', '851 Tianhe Road, Tianhe District', 62.2493917, 121.7959657, '上班打卡');
INSERT INTO `attendance` VALUES (577, 1, '2024-09-06 08:20:17', '2024-08-08 17:52:52', '2024-09-13', '打卡失败', '917 Huaxia St, Jinghua Shangquan', 44.4191752, 105.0091686, '上班打卡');
INSERT INTO `attendance` VALUES (578, 2, '2024-08-06 08:34:52', '2024-09-12 17:41:02', '2024-08-30', '打卡失败', '932 Tangyuan Street 5th Alley, Airport Road, Baiyun', 145.8698184, 0.0317856, '上班打卡');
INSERT INTO `attendance` VALUES (579, 2, '2024-08-05 08:22:14', '2024-08-26 17:06:58', '2024-09-12', '打卡失败', '724 4th Section  Renmin South Road, Jinjiang District', 129.4565945, 142.2261512, '上班打卡');
INSERT INTO `attendance` VALUES (580, 1, '2024-09-09 08:47:51', '2024-09-06 17:24:12', '2024-09-06', '打卡失败', '418 Qingshuihe 1st Rd, Luohu District', 110.8493970, 11.0628221, '上班打卡');
INSERT INTO `attendance` VALUES (581, 4, '2024-08-19 08:11:00', '2024-09-13 17:56:24', '2024-09-17', '打卡失败', '7 Hongqiao Rd., Xu Hui District', 100.8512231, 88.1567753, '上班打卡');
INSERT INTO `attendance` VALUES (582, 1, '2024-08-28 08:37:52', '2024-08-09 17:35:11', '2024-09-16', '打卡失败', 'No. 544, Shuangqing Rd, Chenghua District', 49.0799141, 59.2174266, '上班打卡');
INSERT INTO `attendance` VALUES (583, 4, '2024-08-09 08:37:20', '2024-08-28 17:04:34', '2024-09-13', '打卡失败', '180 W Ring Rd, Buji Town, Longgang', 84.6375223, 75.9213345, '上班打卡');
INSERT INTO `attendance` VALUES (584, 2, '2024-09-17 08:22:54', '2024-09-20 17:17:41', '2024-08-21', '打卡失败', 'No. 650, Shuangqing Rd, Chenghua District', 106.0222714, 64.9830051, '上班打卡');
INSERT INTO `attendance` VALUES (585, 3, '2024-08-27 08:51:01', '2024-08-30 17:58:03', '2024-09-13', '打卡失败', '67 Dong Zhi Men, Dongcheng District', 134.3886906, 109.9640390, '上班打卡');
INSERT INTO `attendance` VALUES (586, 2, '2024-08-30 08:58:13', '2024-08-01 17:29:39', '2024-08-02', '打卡失败', '103 Ganlan Rd, Pudong', 25.8139842, 86.5914333, '上班打卡');
INSERT INTO `attendance` VALUES (587, 4, '2024-08-30 08:50:33', '2024-09-19 17:06:00', '2024-08-30', '打卡失败', '129 Shennan E Rd, Cai Wu Wei, Luohu District', 33.5659101, 35.6322522, '上班打卡');
INSERT INTO `attendance` VALUES (588, 1, '2024-09-23 08:54:49', '2024-09-16 17:22:47', '2024-09-11', '打卡失败', '988 Sanlitun Road, Chaoyang District', 49.4040364, 69.8366379, '上班打卡');
INSERT INTO `attendance` VALUES (589, 2, '2024-09-06 08:46:05', '2024-08-07 17:44:04', '2024-09-11', '打卡失败', '63 3rd Section Hongxing Road, Jinjiang District', 123.3546023, 123.8734239, '上班打卡');
INSERT INTO `attendance` VALUES (590, 6, '2024-09-13 08:16:17', '2024-08-12 17:25:29', '2024-08-19', '打卡失败', '28 Tianhe Road, Tianhe District', 46.3210274, 14.2275030, '上班打卡');
INSERT INTO `attendance` VALUES (591, 2, '2024-08-06 08:24:38', '2024-08-09 17:53:36', '2024-08-26', '打卡失败', '645 NO.6, YuShuang Road, ChengHua Distric', 38.5682415, 150.5501516, '上班打卡');
INSERT INTO `attendance` VALUES (592, 2, '2024-09-06 08:50:51', '2024-09-23 17:25:57', '2024-09-11', '打卡失败', '90 Hongqiao Rd., Xu Hui District', 101.4886509, 21.3875968, '上班打卡');
INSERT INTO `attendance` VALUES (593, 4, '2024-08-19 08:36:26', '2024-09-06 17:56:35', '2024-09-06', '打卡失败', '284 West Chang\'an Avenue, Xicheng District', 99.9169159, 130.8623355, '上班打卡');
INSERT INTO `attendance` VALUES (594, 4, '2024-08-13 08:20:56', '2024-08-13 17:09:14', '2024-08-26', '打卡失败', '30 Yueliu Rd, Fangshan District', 40.3824578, 148.2508960, '上班打卡');
INSERT INTO `attendance` VALUES (595, 1, '2024-08-26 08:02:54', '2024-08-21 17:14:23', '2024-08-09', '打卡失败', '255 Jianxiang Rd, Pudong', 40.9365025, 20.1803338, '上班打卡');
INSERT INTO `attendance` VALUES (596, 6, '2024-08-02 08:28:23', '2024-09-20 17:23:47', '2024-08-12', '打卡失败', '634 West Chang\'an Avenue, Xicheng District', 46.6531344, 51.7890441, '上班打卡');
INSERT INTO `attendance` VALUES (597, 2, '2024-08-13 08:12:52', '2024-08-19 17:16:40', '2024-09-10', '打卡失败', '753 Huanqu South Street 2nd Alley', 43.4174553, 84.4519779, '上班打卡');
INSERT INTO `attendance` VALUES (598, 1, '2024-08-02 08:25:11', '2024-08-20 17:58:58', '2024-08-15', '打卡失败', '599 Binchuan Rd, Minhang District', 58.5981702, 39.3444657, '上班打卡');
INSERT INTO `attendance` VALUES (599, 4, '2024-09-09 08:35:30', '2024-09-17 17:54:09', '2024-09-11', '打卡失败', '955 Dong Zhi Men, Dongcheng District', 142.0106130, 38.1411476, '上班打卡');
INSERT INTO `attendance` VALUES (600, 2, '2024-09-20 08:05:08', '2024-08-01 17:15:59', '2024-09-06', '打卡失败', '214 FuXingMenNei Street, XiCheng District', 62.3497405, 56.8609609, '上班打卡');
INSERT INTO `attendance` VALUES (601, 2, '2024-08-21 08:04:40', '2024-08-21 17:56:15', '2024-09-12', '打卡失败', 'No. 596, Shuangqing Rd, Chenghua District', 88.7741879, 79.2635795, '上班打卡');
INSERT INTO `attendance` VALUES (602, 4, '2024-08-23 08:14:17', '2024-09-03 17:15:53', '2024-09-17', '打卡失败', '826 Jiangnan West Road, Haizhu District', 7.3337598, 98.8994007, '上班打卡');
INSERT INTO `attendance` VALUES (603, 3, '2024-09-18 08:55:44', '2024-09-20 17:41:45', '2024-08-19', '打卡失败', '226 2nd Zhongshan Road, Yuexiu District', 58.1497641, 164.8176013, '上班打卡');
INSERT INTO `attendance` VALUES (604, 5, '2024-08-02 08:41:14', '2024-08-08 17:24:24', '2024-08-02', '打卡失败', '890 Dongtai 5th St', 101.9873427, 119.0086608, '上班打卡');
INSERT INTO `attendance` VALUES (605, 1, '2024-08-30 08:04:50', '2024-08-06 17:47:52', '2024-08-14', '打卡失败', '100 Kengmei 15th Alley', 20.4405093, 119.2900679, '上班打卡');
INSERT INTO `attendance` VALUES (606, 5, '2024-08-23 08:57:51', '2024-08-05 17:04:53', '2024-09-09', '打卡失败', '227 Jiangnan West Road, Haizhu District', 98.1697571, 128.4707147, '上班打卡');
INSERT INTO `attendance` VALUES (607, 4, '2024-09-20 08:33:32', '2024-08-05 17:38:20', '2024-08-07', '打卡失败', '13 Jingtian East 1st St, Futian District', 33.1767495, 92.8671519, '上班打卡');
INSERT INTO `attendance` VALUES (608, 3, '2024-08-14 08:39:56', '2024-09-06 17:18:49', '2024-08-19', '打卡失败', '981 028 County Rd, Yanqing District', 92.7557284, 103.3899179, '上班打卡');
INSERT INTO `attendance` VALUES (609, 2, '2024-09-06 08:41:38', '2024-09-20 17:17:22', '2024-08-20', '打卡失败', '141 Jingtian East 1st St, Futian District', 63.0548016, 136.0427993, '上班打卡');
INSERT INTO `attendance` VALUES (610, 6, '2024-09-05 08:20:49', '2024-08-05 17:11:48', '2024-08-28', '打卡失败', '57 Lefeng 6th Rd', 7.4144937, 155.7999299, '上班打卡');
INSERT INTO `attendance` VALUES (611, 2, '2024-08-30 08:30:43', '2024-09-23 17:01:48', '2024-08-26', '打卡失败', '352 Kengmei 15th Alley', 141.2559599, 134.1902065, '上班打卡');
INSERT INTO `attendance` VALUES (612, 6, '2024-08-12 08:15:18', '2024-09-06 17:51:27', '2024-08-02', '打卡失败', '170 Hongqiao Rd., Xu Hui District', 78.9915748, 82.4923731, '上班打卡');
INSERT INTO `attendance` VALUES (613, 3, '2024-08-28 08:24:54', '2024-09-13 17:08:43', '2024-08-12', '打卡失败', '821 Middle Huaihai Road, Huangpu District', 90.5117148, 28.0130302, '上班打卡');
INSERT INTO `attendance` VALUES (614, 5, '2024-08-13 08:15:32', '2024-08-12 17:08:51', '2024-08-27', '打卡失败', '675 Shennan E Rd, Cai Wu Wei, Luohu District', 11.4178635, 0.8551381, '上班打卡');
INSERT INTO `attendance` VALUES (615, 1, '2024-08-13 08:22:27', '2024-08-19 17:11:16', '2024-09-20', '打卡失败', '194 Shennan Ave, Futian District', 145.9255169, 3.9428737, '上班打卡');
INSERT INTO `attendance` VALUES (616, 4, '2024-08-05 08:03:45', '2024-09-06 17:39:10', '2024-08-05', '打卡失败', 'No.610, Dongsan Road, Erxianqiao, Chenghua District', 32.5583634, 99.4881899, '上班打卡');
INSERT INTO `attendance` VALUES (617, 2, '2024-09-06 08:10:41', '2024-08-20 17:20:59', '2024-08-26', '打卡失败', '997 Middle Huaihai Road, Huangpu District', 135.4337750, 40.9815324, '上班打卡');
INSERT INTO `attendance` VALUES (618, 2, '2024-08-12 08:06:33', '2024-08-05 17:20:04', '2024-08-29', '打卡失败', '715 East Wangfujing Street, Dongcheng District ', 67.6298321, 133.3308771, '上班打卡');
INSERT INTO `attendance` VALUES (619, 2, '2024-09-13 08:34:20', '2024-08-12 17:08:25', '2024-08-12', '打卡失败', 'No. 979, Shuangqing Rd, Chenghua District', 4.9627448, 93.5949941, '上班打卡');
INSERT INTO `attendance` VALUES (620, 5, '2024-09-20 08:57:49', '2024-09-18 17:14:35', '2024-08-05', '打卡失败', '666 Tianbei 1st Rd, Luohu District', 24.5235521, 55.1366541, '上班打卡');
INSERT INTO `attendance` VALUES (621, 5, '2024-09-13 08:34:52', '2024-08-29 17:39:18', '2024-08-30', '打卡失败', '992 Ganlan Rd, Pudong', 21.5253482, 49.2592062, '上班打卡');
INSERT INTO `attendance` VALUES (622, 3, '2024-08-29 08:34:50', '2024-09-13 17:42:55', '2024-08-30', '打卡失败', '113 2nd Zhongshan Road, Yuexiu District', 68.9212739, 1.7653183, '上班打卡');
INSERT INTO `attendance` VALUES (623, 5, '2024-09-06 08:29:45', '2024-08-05 17:56:17', '2024-09-20', '打卡失败', '529 Dongtai 5th St', 18.9703941, 22.5346997, '上班打卡');
INSERT INTO `attendance` VALUES (624, 1, '2024-08-19 08:16:57', '2024-08-26 17:41:07', '2024-08-12', '打卡失败', '387 Jianxiang Rd, Pudong', 27.4470990, 80.5078267, '上班打卡');
INSERT INTO `attendance` VALUES (625, 6, '2024-09-20 08:04:18', '2024-09-11 17:21:52', '2024-08-22', '打卡失败', '940 Dong Zhi Men, Dongcheng District', 16.8750421, 105.6371434, '上班打卡');
INSERT INTO `attendance` VALUES (626, 1, '2024-08-30 08:57:57', '2024-09-18 17:31:51', '2024-08-12', '打卡失败', '220 2nd Zhongshan Road, Yuexiu District', 104.7767638, 44.9078732, '上班打卡');
INSERT INTO `attendance` VALUES (627, 2, '2024-08-19 08:31:42', '2024-09-02 17:44:46', '2024-08-15', '打卡失败', '74 Kengmei 15th Alley', 103.3986552, 100.4916499, '上班打卡');
INSERT INTO `attendance` VALUES (628, 5, '2024-09-02 08:37:41', '2024-08-05 17:50:49', '2024-09-20', '打卡失败', '444 Shanhu Rd', 24.2053347, 128.7077031, '上班打卡');
INSERT INTO `attendance` VALUES (629, 1, '2024-08-28 08:54:18', '2024-09-23 17:21:36', '2024-09-13', '打卡失败', '338 NO.6, YuShuang Road, ChengHua Distric', 77.3766032, 13.9023090, '上班打卡');
INSERT INTO `attendance` VALUES (630, 5, '2024-09-03 08:52:10', '2024-08-13 17:28:19', '2024-08-14', '打卡失败', '920 Dong Zhi Men, Dongcheng District', 124.0926093, 164.7536711, '上班打卡');
INSERT INTO `attendance` VALUES (631, 1, '2024-08-12 08:52:59', '2024-08-05 17:52:56', '2024-08-29', '打卡失败', '904 Shennan E Rd, Cai Wu Wei, Luohu District', 94.7420248, 109.0172936, '上班打卡');
INSERT INTO `attendance` VALUES (632, 3, '2024-08-13 08:03:09', '2024-08-30 17:57:18', '2024-08-30', '打卡失败', '569 FuXingMenNei Street, XiCheng District', 82.7686639, 77.2911972, '上班打卡');
INSERT INTO `attendance` VALUES (633, 2, '2024-08-27 08:50:27', '2024-08-12 17:48:18', '2024-08-30', '打卡失败', '310 Xue Yuan Yi Xiang, Longgang', 147.8852304, 75.3466810, '上班打卡');
INSERT INTO `attendance` VALUES (634, 2, '2024-08-09 08:16:51', '2024-08-14 17:29:25', '2024-08-14', '打卡失败', '586 Tangyuan Street 5th Alley, Airport Road, Baiyun', 118.3035403, 8.4750689, '上班打卡');
INSERT INTO `attendance` VALUES (635, 3, '2024-09-06 08:16:56', '2024-08-15 17:05:33', '2024-08-30', '打卡失败', '365 Hongqiao Rd., Xu Hui District', 114.4331183, 91.0256014, '上班打卡');
INSERT INTO `attendance` VALUES (636, 5, '2024-09-18 08:37:05', '2024-08-05 17:35:38', '2024-08-07', '打卡失败', '81 Huaxia St, Jinghua Shangquan', 94.0991316, 3.8184555, '上班打卡');
INSERT INTO `attendance` VALUES (637, 1, '2024-08-29 08:27:29', '2024-08-30 17:35:39', '2024-08-05', '打卡失败', 'No.616, Dongsan Road, Erxianqiao, Chenghua District', 38.6591581, 17.4355776, '上班打卡');
INSERT INTO `attendance` VALUES (638, 2, '2024-08-19 08:06:38', '2024-08-26 17:59:08', '2024-08-19', '打卡失败', '695 W Ring Rd, Buji Town, Longgang', 63.4778459, 68.9336773, '上班打卡');
INSERT INTO `attendance` VALUES (639, 5, '2024-09-13 08:20:08', '2024-08-26 17:07:21', '2024-09-18', '打卡失败', '798 Jiangnan West Road, Haizhu District', 140.9106595, 104.1715832, '上班打卡');
INSERT INTO `attendance` VALUES (640, 3, '2024-09-20 08:40:11', '2024-08-19 17:56:45', '2024-08-23', '打卡失败', '400 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 70.7459188, 96.5116057, '上班打卡');
INSERT INTO `attendance` VALUES (641, 3, '2024-09-10 08:40:55', '2024-09-13 17:46:36', '2024-09-20', '打卡失败', 'No. 307, Shuangqing Rd, Chenghua District', 28.4198671, 3.4149572, '上班打卡');
INSERT INTO `attendance` VALUES (642, 5, '2024-08-22 08:24:01', '2024-09-16 17:28:52', '2024-09-09', '打卡失败', '195 Huanqu South Street 2nd Alley', 73.4202981, 84.7953376, '上班打卡');
INSERT INTO `attendance` VALUES (643, 1, '2024-08-20 08:31:28', '2024-09-16 17:29:03', '2024-08-30', '打卡失败', '850 Xue Yuan Yi Xiang, Longgang', 82.5300165, 116.0763997, '上班打卡');
INSERT INTO `attendance` VALUES (644, 2, '2024-09-06 08:06:03', '2024-08-28 17:27:18', '2024-08-12', '打卡失败', '590 East Wangfujing Street, Dongcheng District ', 127.6741235, 131.2381728, '上班打卡');
INSERT INTO `attendance` VALUES (645, 2, '2024-09-06 08:19:25', '2024-08-26 17:51:54', '2024-09-06', '打卡失败', '315 Zhongshan 5th Rd, Zimaling Shangquan', 137.2822526, 14.1732229, '上班打卡');
INSERT INTO `attendance` VALUES (646, 2, '2024-08-05 08:22:52', '2024-09-06 17:07:58', '2024-08-14', '打卡失败', '133 Zhongshan 5th Rd, Zimaling Shangquan', 134.4406322, 92.6343811, '上班打卡');
INSERT INTO `attendance` VALUES (647, 4, '2024-09-16 08:15:00', '2024-08-19 17:17:07', '2024-08-09', '打卡失败', '295 Sanlitun Road, Chaoyang District', 22.8039574, 41.9085354, '上班打卡');
INSERT INTO `attendance` VALUES (648, 3, '2024-08-30 08:13:00', '2024-09-13 17:14:45', '2024-08-29', '打卡失败', '959 Hongqiao Rd., Xu Hui District', 0.5237238, 126.4152678, '上班打卡');
INSERT INTO `attendance` VALUES (649, 4, '2024-09-23 08:17:07', '2024-09-06 17:26:12', '2024-08-26', '打卡失败', '102 Kengmei 15th Alley', 107.7503743, 151.9924247, '上班打卡');
INSERT INTO `attendance` VALUES (650, 6, '2024-08-21 08:44:58', '2024-08-13 17:50:10', '2024-08-12', '打卡失败', '269 028 County Rd, Yanqing District', 73.6316729, 24.1849520, '上班打卡');
INSERT INTO `attendance` VALUES (651, 3, '2024-08-30 08:09:38', '2024-09-16 17:52:21', '2024-09-13', '打卡失败', '469 Middle Huaihai Road, Huangpu District', 36.1891115, 69.2786528, '上班打卡');
INSERT INTO `attendance` VALUES (652, 2, '2024-08-05 08:35:53', '2024-08-21 17:44:06', '2024-08-09', '打卡失败', '194 4th Section  Renmin South Road, Jinjiang District', 80.4438577, 117.2052786, '上班打卡');
INSERT INTO `attendance` VALUES (653, 4, '2024-08-19 08:23:43', '2024-08-21 17:21:19', '2024-09-03', '打卡失败', '845 Tianhe Road, Tianhe District', 51.8312705, 129.9020909, '上班打卡');
INSERT INTO `attendance` VALUES (654, 2, '2024-09-23 08:32:47', '2024-08-26 17:19:14', '2024-08-09', '打卡失败', '651 68 Qinghe Middle St, Haidian District', 54.2600814, 110.7698456, '上班打卡');
INSERT INTO `attendance` VALUES (655, 3, '2024-09-02 08:30:12', '2024-09-06 17:22:31', '2024-09-11', '打卡失败', '394 3rd Section Hongxing Road, Jinjiang District', 22.5661493, 2.3978817, '上班打卡');
INSERT INTO `attendance` VALUES (656, 5, '2024-08-22 08:33:02', '2024-08-30 17:18:56', '2024-08-30', '打卡失败', '973 Huaxia St, Jinghua Shangquan', 114.6705556, 101.7856054, '上班打卡');
INSERT INTO `attendance` VALUES (657, 3, '2024-08-01 08:18:02', '2024-08-05 17:42:55', '2024-09-18', '打卡失败', '33 2nd Zhongshan Road, Yuexiu District', 80.4509287, 154.4385989, '上班打卡');
INSERT INTO `attendance` VALUES (658, 2, '2024-08-06 08:44:09', '2024-09-10 17:53:38', '2024-08-19', '打卡失败', '641 Hongqiao Rd., Xu Hui District', 111.4353223, 92.2136369, '上班打卡');
INSERT INTO `attendance` VALUES (659, 1, '2024-08-12 08:56:08', '2024-09-02 17:03:32', '2024-08-30', '打卡失败', '813 Yueliu Rd, Fangshan District', 59.2593834, 27.8778915, '上班打卡');
INSERT INTO `attendance` VALUES (660, 2, '2024-08-01 08:38:23', '2024-08-19 17:04:20', '2024-08-12', '打卡失败', '614 Xue Yuan Yi Xiang, Longgang', 116.0444392, 132.3500911, '上班打卡');
INSERT INTO `attendance` VALUES (661, 6, '2024-09-18 08:24:36', '2024-09-20 17:06:56', '2024-08-16', '打卡失败', '674 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 90.1286524, 23.6458156, '上班打卡');
INSERT INTO `attendance` VALUES (662, 2, '2024-09-06 08:05:06', '2024-09-20 17:43:12', '2024-09-02', '打卡失败', '180 NO.6, YuShuang Road, ChengHua Distric', 125.3568944, 81.0898034, '上班打卡');
INSERT INTO `attendance` VALUES (663, 4, '2024-08-22 08:59:39', '2024-08-05 17:33:26', '2024-09-10', '打卡失败', '927 Binchuan Rd, Minhang District', 137.3201169, 23.9496248, '上班打卡');
INSERT INTO `attendance` VALUES (664, 3, '2024-09-16 08:11:40', '2024-09-19 17:52:25', '2024-08-12', '打卡失败', '350 Sanlitun Road, Chaoyang District', 116.8403712, 95.9282365, '上班打卡');
INSERT INTO `attendance` VALUES (665, 4, '2024-08-09 08:40:14', '2024-08-05 17:11:41', '2024-08-27', '打卡失败', '646 Huaxia St, Jinghua Shangquan', 113.0865176, 162.9785479, '上班打卡');
INSERT INTO `attendance` VALUES (666, 4, '2024-08-07 08:36:23', '2024-08-05 17:46:56', '2024-09-06', '打卡失败', '653 Yueliu Rd, Fangshan District', 8.3298385, 115.9706606, '上班打卡');
INSERT INTO `attendance` VALUES (667, 4, '2024-08-26 08:56:32', '2024-08-29 17:30:24', '2024-08-12', '打卡失败', '651 Hongqiao Rd., Xu Hui District', 139.6175976, 165.7308141, '上班打卡');
INSERT INTO `attendance` VALUES (668, 4, '2024-08-22 08:10:03', '2024-08-07 17:13:23', '2024-09-19', '打卡失败', '944 W Ring Rd, Buji Town, Longgang', 69.7992079, 74.0891051, '上班打卡');
INSERT INTO `attendance` VALUES (669, 1, '2024-08-26 08:33:28', '2024-09-13 17:00:34', '2024-08-07', '打卡失败', '852 FuXingMenNei Street, XiCheng District', 125.7388945, 9.2517274, '上班打卡');
INSERT INTO `attendance` VALUES (670, 6, '2024-08-19 08:36:35', '2024-08-30 17:38:45', '2024-09-16', '打卡失败', '363 Shanhu Rd', 139.4512758, 136.9035865, '上班打卡');
INSERT INTO `attendance` VALUES (671, 6, '2024-09-20 08:34:57', '2024-08-16 17:25:18', '2024-08-30', '打卡失败', '290 Binchuan Rd, Minhang District', 91.1906180, 82.7190320, '上班打卡');
INSERT INTO `attendance` VALUES (672, 4, '2024-08-09 08:05:37', '2024-08-26 17:27:25', '2024-09-02', '打卡失败', '210 Kengmei 15th Alley', 67.9827187, 24.0161567, '上班打卡');
INSERT INTO `attendance` VALUES (673, 5, '2024-08-26 08:29:09', '2024-08-19 17:39:25', '2024-09-03', '打卡失败', '630 Tianhe Road, Tianhe District', 117.2523410, 134.3906676, '上班打卡');
INSERT INTO `attendance` VALUES (674, 4, '2024-08-02 08:49:10', '2024-08-13 17:04:44', '2024-08-29', '打卡失败', '534 Tangyuan Street 5th Alley, Airport Road, Baiyun', 55.9617576, 16.0699296, '上班打卡');
INSERT INTO `attendance` VALUES (675, 1, '2024-09-09 08:24:14', '2024-08-23 17:34:16', '2024-09-04', '打卡失败', '533 Lefeng 6th Rd', 70.6351185, 127.9931531, '上班打卡');
INSERT INTO `attendance` VALUES (676, 4, '2024-09-13 08:35:58', '2024-09-04 17:00:59', '2024-08-19', '打卡失败', '37 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 62.7091516, 53.7429938, '上班打卡');
INSERT INTO `attendance` VALUES (677, 5, '2024-08-05 08:20:53', '2024-09-20 17:40:32', '2024-08-26', '打卡失败', '213 Shennan E Rd, Cai Wu Wei, Luohu District', 82.4663104, 81.4904460, '上班打卡');
INSERT INTO `attendance` VALUES (678, 1, '2024-08-12 08:25:36', '2024-09-09 17:50:47', '2024-08-02', '打卡失败', '45 Ganlan Rd, Pudong', 100.9974110, 90.7700279, '上班打卡');
INSERT INTO `attendance` VALUES (679, 4, '2024-08-05 08:01:00', '2024-08-30 17:57:03', '2024-08-09', '打卡失败', '684 Binchuan Rd, Minhang District', 133.8129154, 44.7792861, '上班打卡');
INSERT INTO `attendance` VALUES (680, 3, '2024-08-29 08:16:02', '2024-09-05 17:28:43', '2024-08-08', '打卡失败', '467 Middle Huaihai Road, Huangpu District', 70.5045216, 126.2414460, '上班打卡');
INSERT INTO `attendance` VALUES (681, 3, '2024-09-05 08:03:52', '2024-09-02 17:38:58', '2024-08-01', '打卡失败', '999 Huanqu South Street 2nd Alley', 63.7368225, 137.4436644, '上班打卡');
INSERT INTO `attendance` VALUES (682, 4, '2024-08-15 08:01:03', '2024-09-20 17:10:49', '2024-08-21', '打卡失败', 'No. 922, Shuangqing Rd, Chenghua District', 10.1199005, 9.5825634, '上班打卡');
INSERT INTO `attendance` VALUES (683, 3, '2024-08-08 08:47:46', '2024-08-30 17:06:31', '2024-09-16', '打卡失败', '668 West Chang\'an Avenue, Xicheng District', 56.5838630, 36.8401180, '上班打卡');
INSERT INTO `attendance` VALUES (684, 5, '2024-08-22 08:33:09', '2024-08-21 17:17:33', '2024-09-05', '打卡失败', '515 Huaxia St, Jinghua Shangquan', 49.9414372, 34.4637415, '上班打卡');
INSERT INTO `attendance` VALUES (685, 3, '2024-08-20 08:44:38', '2024-08-29 17:35:28', '2024-09-10', '打卡失败', '617 East Wangfujing Street, Dongcheng District ', 117.2438703, 66.5093324, '上班打卡');
INSERT INTO `attendance` VALUES (686, 3, '2024-08-12 08:31:33', '2024-08-26 17:35:06', '2024-09-04', '打卡失败', '172 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 53.5587063, 97.5239504, '上班打卡');
INSERT INTO `attendance` VALUES (687, 6, '2024-08-27 08:33:08', '2024-08-05 17:07:59', '2024-08-29', '打卡失败', '605 3rd Section Hongxing Road, Jinjiang District', 13.0395800, 26.3881394, '上班打卡');
INSERT INTO `attendance` VALUES (688, 6, '2024-09-17 08:27:33', '2024-08-06 17:29:57', '2024-09-06', '打卡失败', '789 Huaxia St, Jinghua Shangquan', 140.1398538, 122.9282638, '上班打卡');
INSERT INTO `attendance` VALUES (689, 5, '2024-08-22 08:53:10', '2024-08-23 17:13:36', '2024-08-20', '打卡失败', '55 Shennan Ave, Futian District', 83.1862321, 56.7363104, '上班打卡');
INSERT INTO `attendance` VALUES (690, 1, '2024-08-23 08:29:54', '2024-08-06 17:45:50', '2024-08-15', '打卡失败', '225 Middle Huaihai Road, Huangpu District', 63.4849615, 118.3102981, '上班打卡');
INSERT INTO `attendance` VALUES (691, 2, '2024-09-11 08:22:55', '2024-08-22 17:15:40', '2024-09-20', '打卡失败', '928 Sanlitun Road, Chaoyang District', 119.6010241, 36.5581181, '上班打卡');
INSERT INTO `attendance` VALUES (692, 1, '2024-08-28 08:27:21', '2024-09-20 17:52:08', '2024-09-02', '打卡失败', '980 Huanqu South Street 2nd Alley', 55.7704613, 25.4589355, '上班打卡');
INSERT INTO `attendance` VALUES (693, 5, '2024-09-18 08:05:10', '2024-09-06 17:48:36', '2024-08-15', '打卡失败', '430 2nd Zhongshan Road, Yuexiu District', 138.6294859, 132.6412434, '上班打卡');
INSERT INTO `attendance` VALUES (694, 3, '2024-08-13 08:42:03', '2024-08-05 17:55:53', '2024-08-27', '打卡失败', '738 Yueliu Rd, Fangshan District', 97.9837056, 157.2630092, '上班打卡');
INSERT INTO `attendance` VALUES (695, 2, '2024-09-20 08:03:38', '2024-09-23 17:39:33', '2024-09-11', '打卡失败', '568 NO.6, YuShuang Road, ChengHua Distric', 0.2900481, 38.5638710, '上班打卡');
INSERT INTO `attendance` VALUES (696, 3, '2024-08-12 08:43:08', '2024-08-15 17:44:29', '2024-09-03', '打卡失败', '969 Dongtai 5th St', 16.3444758, 148.4111529, '上班打卡');
INSERT INTO `attendance` VALUES (697, 5, '2024-08-02 08:17:37', '2024-08-14 17:34:54', '2024-08-30', '打卡失败', '35 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 40.2355692, 84.5976481, '上班打卡');
INSERT INTO `attendance` VALUES (698, 1, '2024-08-16 08:42:34', '2024-08-27 17:40:45', '2024-09-20', '打卡失败', '48 4th Section  Renmin South Road, Jinjiang District', 55.2403703, 63.2893379, '上班打卡');
INSERT INTO `attendance` VALUES (699, 4, '2024-08-26 08:15:50', '2024-09-09 17:14:26', '2024-09-06', '打卡失败', '553 4th Section  Renmin South Road, Jinjiang District', 44.9754791, 84.5920006, '上班打卡');
INSERT INTO `attendance` VALUES (700, 6, '2024-08-26 08:05:19', '2024-09-20 17:07:00', '2024-08-26', '打卡失败', '836 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 70.0622176, 9.7101438, '上班打卡');
INSERT INTO `attendance` VALUES (701, 4, '2024-09-16 08:48:54', '2024-08-26 17:32:18', '2024-09-06', '打卡失败', '176 Kengmei 15th Alley', 114.1186144, 159.3624527, '上班打卡');
INSERT INTO `attendance` VALUES (702, 6, '2024-08-08 08:13:48', '2024-09-20 17:39:17', '2024-09-20', '打卡失败', '940 Lefeng 6th Rd', 70.6822428, 52.1721591, '上班打卡');
INSERT INTO `attendance` VALUES (703, 6, '2024-09-04 08:37:16', '2024-09-13 17:58:16', '2024-09-04', '打卡失败', '433 Jianxiang Rd, Pudong', 137.9789446, 62.9440218, '上班打卡');
INSERT INTO `attendance` VALUES (704, 5, '2024-08-28 08:29:55', '2024-08-26 17:47:29', '2024-09-05', '打卡失败', '281 Xiaoping E Rd, Baiyun ', 55.0370194, 63.3901220, '上班打卡');
INSERT INTO `attendance` VALUES (705, 2, '2024-09-13 08:37:02', '2024-08-13 17:08:34', '2024-09-23', '打卡失败', '236 Binchuan Rd, Minhang District', 6.6873618, 8.9127062, '上班打卡');
INSERT INTO `attendance` VALUES (706, 3, '2024-08-20 08:01:48', '2024-08-12 17:31:19', '2024-08-22', '打卡失败', 'No. 80, Shuangqing Rd, Chenghua District', 102.2446142, 88.9592851, '上班打卡');
INSERT INTO `attendance` VALUES (707, 6, '2024-08-13 08:06:25', '2024-08-12 17:11:35', '2024-08-27', '打卡失败', 'No. 836, Shuangqing Rd, Chenghua District', 117.6880075, 70.2873138, '上班打卡');
INSERT INTO `attendance` VALUES (708, 4, '2024-09-17 08:06:07', '2024-08-26 17:42:12', '2024-08-05', '打卡失败', '476 Jingtian East 1st St, Futian District', 70.9257210, 30.0917688, '上班打卡');
INSERT INTO `attendance` VALUES (709, 4, '2024-08-13 08:56:39', '2024-08-01 17:20:34', '2024-08-22', '打卡失败', '331 Hongqiao Rd., Xu Hui District', 100.0622164, 138.8234315, '上班打卡');
INSERT INTO `attendance` VALUES (710, 5, '2024-08-01 08:29:22', '2024-09-06 17:39:12', '2024-09-06', '打卡失败', '997 4th Section  Renmin South Road, Jinjiang District', 52.3715430, 163.5080200, '上班打卡');
INSERT INTO `attendance` VALUES (711, 3, '2024-09-19 08:08:04', '2024-09-20 17:07:51', '2024-08-16', '打卡失败', '115 Yueliu Rd, Fangshan District', 99.4102186, 0.1511266, '上班打卡');
INSERT INTO `attendance` VALUES (712, 5, '2024-09-17 08:45:42', '2024-09-13 17:56:12', '2024-08-08', '打卡失败', '92 Tangyuan Street 5th Alley, Airport Road, Baiyun', 29.0956871, 110.8484310, '上班打卡');
INSERT INTO `attendance` VALUES (713, 1, '2024-09-10 08:26:31', '2024-08-13 17:59:07', '2024-08-14', '打卡失败', '400 Xue Yuan Yi Xiang, Longgang', 115.5333823, 117.5781181, '上班打卡');
INSERT INTO `attendance` VALUES (714, 3, '2024-08-12 08:16:18', '2024-08-07 17:44:56', '2024-08-12', '打卡失败', '730 68 Qinghe Middle St, Haidian District', 101.2031747, 65.0489225, '上班打卡');
INSERT INTO `attendance` VALUES (715, 1, '2024-09-20 08:17:25', '2024-08-06 17:05:10', '2024-09-13', '打卡失败', '627 3rd Section Hongxing Road, Jinjiang District', 81.2813612, 4.0293324, '上班打卡');
INSERT INTO `attendance` VALUES (716, 6, '2024-08-27 08:39:45', '2024-08-30 17:25:52', '2024-09-20', '打卡失败', '895 Zhongshan 5th Rd, Zimaling Shangquan', 70.6849234, 142.9431388, '上班打卡');
INSERT INTO `attendance` VALUES (717, 3, '2024-08-12 08:11:00', '2024-08-15 17:52:25', '2024-09-20', '打卡失败', '721 Hongqiao Rd., Xu Hui District', 36.6890277, 122.9804909, '上班打卡');
INSERT INTO `attendance` VALUES (718, 1, '2024-08-28 08:21:21', '2024-08-28 17:34:26', '2024-08-26', '打卡失败', '465 Shennan Ave, Futian District', 54.9468159, 73.8557109, '上班打卡');
INSERT INTO `attendance` VALUES (719, 6, '2024-08-15 08:11:19', '2024-08-19 17:29:20', '2024-09-06', '打卡失败', '885 68 Qinghe Middle St, Haidian District', 50.3734305, 119.9871264, '上班打卡');
INSERT INTO `attendance` VALUES (720, 1, '2024-08-30 08:26:13', '2024-09-18 17:13:47', '2024-08-20', '打卡失败', '320 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 79.1863252, 99.4303559, '上班打卡');
INSERT INTO `attendance` VALUES (721, 4, '2024-08-01 08:00:21', '2024-08-01 17:23:01', '2024-09-23', '打卡失败', '507 028 County Rd, Yanqing District', 137.3971408, 147.2861519, '上班打卡');
INSERT INTO `attendance` VALUES (722, 3, '2024-08-12 08:11:47', '2024-09-13 17:09:57', '2024-08-06', '打卡失败', '824 NO.6, YuShuang Road, ChengHua Distric', 72.9664396, 40.9292922, '上班打卡');
INSERT INTO `attendance` VALUES (723, 5, '2024-09-09 08:08:55', '2024-08-15 17:34:39', '2024-08-26', '打卡失败', '423 Qingshuihe 1st Rd, Luohu District', 92.1599233, 31.9617916, '上班打卡');
INSERT INTO `attendance` VALUES (724, 5, '2024-08-06 08:00:10', '2024-08-13 17:59:08', '2024-08-06', '打卡失败', '463 Hongqiao Rd., Xu Hui District', 67.6037380, 158.8374241, '上班打卡');
INSERT INTO `attendance` VALUES (725, 1, '2024-09-23 08:11:36', '2024-08-19 17:29:27', '2024-09-19', '打卡失败', '260 Tangyuan Street 5th Alley, Airport Road, Baiyun', 74.9214773, 151.7671076, '上班打卡');
INSERT INTO `attendance` VALUES (726, 6, '2024-08-30 08:29:44', '2024-08-22 17:39:16', '2024-08-20', '打卡失败', '429 Hongqiao Rd., Xu Hui District', 134.8458505, 60.0651314, '上班打卡');
INSERT INTO `attendance` VALUES (727, 1, '2024-08-26 08:38:55', '2024-08-06 17:25:01', '2024-08-02', '打卡失败', '690 Xue Yuan Yi Xiang, Longgang', 114.6218429, 110.1975256, '上班打卡');
INSERT INTO `attendance` VALUES (728, 1, '2024-09-16 08:34:41', '2024-08-30 17:40:05', '2024-08-05', '打卡失败', '178 NO.6, YuShuang Road, ChengHua Distric', 7.0998247, 142.0844029, '上班打卡');
INSERT INTO `attendance` VALUES (729, 4, '2024-08-27 08:26:50', '2024-09-12 17:20:12', '2024-08-22', '打卡失败', '337 028 County Rd, Yanqing District', 92.7023495, 58.2966849, '上班打卡');
INSERT INTO `attendance` VALUES (730, 3, '2024-08-22 08:18:39', '2024-08-29 17:11:15', '2024-08-05', '打卡失败', '870 Sanlitun Road, Chaoyang District', 58.0547887, 12.9914011, '上班打卡');
INSERT INTO `attendance` VALUES (731, 5, '2024-08-12 08:23:13', '2024-09-10 17:41:52', '2024-08-23', '打卡失败', '730 Jiangnan West Road, Haizhu District', 32.8540023, 123.3753571, '上班打卡');
INSERT INTO `attendance` VALUES (732, 4, '2024-08-26 08:56:42', '2024-08-19 17:06:06', '2024-08-26', '打卡失败', '189 Jiangnan West Road, Haizhu District', 111.0313750, 146.7921866, '上班打卡');
INSERT INTO `attendance` VALUES (733, 4, '2024-09-20 08:07:31', '2024-09-16 17:20:56', '2024-09-03', '打卡失败', '67 Ganlan Rd, Pudong', 49.0430784, 165.0128042, '上班打卡');
INSERT INTO `attendance` VALUES (734, 4, '2024-08-21 08:00:46', '2024-08-22 17:41:49', '2024-08-23', '打卡失败', '222 Jingtian East 1st St, Futian District', 103.2750170, 156.9875936, '上班打卡');
INSERT INTO `attendance` VALUES (735, 1, '2024-08-08 08:26:27', '2024-09-16 17:25:15', '2024-08-15', '打卡失败', '286 Sanlitun Road, Chaoyang District', 43.6845205, 18.4178403, '上班打卡');
INSERT INTO `attendance` VALUES (736, 4, '2024-09-23 08:49:24', '2024-08-05 17:57:47', '2024-08-23', '打卡失败', '287 Xue Yuan Yi Xiang, Longgang', 63.1307354, 162.4385865, '上班打卡');
INSERT INTO `attendance` VALUES (737, 1, '2024-09-10 08:06:58', '2024-09-23 17:47:30', '2024-08-26', '打卡失败', '298 3rd Section Hongxing Road, Jinjiang District', 8.3156820, 138.2064138, '上班打卡');
INSERT INTO `attendance` VALUES (738, 2, '2024-08-19 08:16:15', '2024-08-19 17:01:10', '2024-08-14', '打卡失败', '109 Yueliu Rd, Fangshan District', 47.6114875, 71.5281060, '上班打卡');
INSERT INTO `attendance` VALUES (739, 2, '2024-09-10 08:45:32', '2024-09-13 17:01:32', '2024-08-27', '打卡失败', '150 028 County Rd, Yanqing District', 142.7760987, 65.2268131, '上班打卡');
INSERT INTO `attendance` VALUES (740, 3, '2024-09-20 08:58:57', '2024-09-16 17:12:09', '2024-08-01', '打卡失败', '60 Dongtai 5th St', 110.9962793, 158.0350983, '上班打卡');
INSERT INTO `attendance` VALUES (741, 6, '2024-09-18 08:40:42', '2024-08-30 17:28:49', '2024-08-27', '打卡失败', '768 Ganlan Rd, Pudong', 105.2209204, 12.2860683, '上班打卡');
INSERT INTO `attendance` VALUES (742, 4, '2024-08-05 08:19:08', '2024-09-02 17:45:13', '2024-08-23', '打卡失败', '815 W Ring Rd, Buji Town, Longgang', 106.4228233, 76.2167154, '上班打卡');
INSERT INTO `attendance` VALUES (743, 1, '2024-08-26 08:29:28', '2024-08-23 17:41:16', '2024-09-02', '打卡失败', '238 East Wangfujing Street, Dongcheng District ', 40.1367037, 63.2099593, '上班打卡');
INSERT INTO `attendance` VALUES (744, 4, '2024-09-20 08:27:30', '2024-08-02 17:12:41', '2024-08-14', '打卡失败', '979 Shanhu Rd', 116.0977561, 92.1596706, '上班打卡');
INSERT INTO `attendance` VALUES (745, 1, '2024-09-03 08:31:51', '2024-09-06 17:45:49', '2024-09-10', '打卡失败', '801 Tangyuan Street 5th Alley, Airport Road, Baiyun', 82.5752952, 105.2828100, '上班打卡');
INSERT INTO `attendance` VALUES (746, 3, '2024-09-03 08:38:56', '2024-08-26 17:25:15', '2024-09-12', '打卡失败', '942 4th Section  Renmin South Road, Jinjiang District', 11.2137973, 16.8744832, '上班打卡');
INSERT INTO `attendance` VALUES (747, 1, '2024-09-13 08:20:21', '2024-08-30 17:01:19', '2024-08-19', '打卡失败', '836 Lefeng 6th Rd', 51.2186549, 126.2849773, '上班打卡');
INSERT INTO `attendance` VALUES (748, 1, '2024-08-16 08:23:58', '2024-08-21 17:12:54', '2024-08-05', '打卡失败', '289 West Chang\'an Avenue, Xicheng District', 138.9503325, 104.9036694, '上班打卡');
INSERT INTO `attendance` VALUES (749, 4, '2024-08-22 08:59:32', '2024-08-14 17:14:48', '2024-09-13', '打卡失败', '884 Hongqiao Rd., Xu Hui District', 9.8849939, 157.9075522, '上班打卡');
INSERT INTO `attendance` VALUES (750, 3, '2024-09-20 08:23:31', '2024-09-23 17:34:57', '2024-08-28', '打卡失败', '145 Xue Yuan Yi Xiang, Longgang', 114.3237751, 67.5985853, '上班打卡');
INSERT INTO `attendance` VALUES (751, 3, '2024-08-26 08:53:32', '2024-08-20 17:54:07', '2024-09-19', '打卡失败', '861 Xiaoping E Rd, Baiyun ', 49.8362861, 145.3846285, '上班打卡');
INSERT INTO `attendance` VALUES (752, 1, '2024-09-20 08:12:29', '2024-08-26 17:49:58', '2024-09-06', '打卡失败', '810 Middle Huaihai Road, Huangpu District', 9.9271440, 87.5117962, '上班打卡');
INSERT INTO `attendance` VALUES (753, 1, '2024-08-14 08:29:04', '2024-09-18 17:46:48', '2024-09-03', '打卡失败', '486 Lefeng 6th Rd', 104.5118469, 83.5341609, '上班打卡');
INSERT INTO `attendance` VALUES (754, 5, '2024-09-20 08:44:16', '2024-08-21 17:10:46', '2024-08-30', '打卡失败', '965 Jingtian East 1st St, Futian District', 16.5507275, 25.1258219, '上班打卡');
INSERT INTO `attendance` VALUES (755, 5, '2024-09-20 08:43:01', '2024-08-30 17:49:09', '2024-08-21', '打卡失败', '978 FuXingMenNei Street, XiCheng District', 39.8497448, 71.7076513, '上班打卡');
INSERT INTO `attendance` VALUES (756, 1, '2024-08-30 08:43:21', '2024-09-12 17:27:16', '2024-08-16', '打卡失败', '115 Tianbei 1st Rd, Luohu District', 30.0650499, 116.1882530, '上班打卡');
INSERT INTO `attendance` VALUES (757, 1, '2024-08-16 08:19:49', '2024-09-03 17:50:38', '2024-08-20', '打卡失败', '4 Huanqu South Street 2nd Alley', 49.7102285, 117.1453888, '上班打卡');
INSERT INTO `attendance` VALUES (758, 1, '2024-09-20 08:20:57', '2024-08-20 17:08:51', '2024-08-05', '打卡失败', '311 Yueliu Rd, Fangshan District', 1.6043216, 113.5828483, '上班打卡');
INSERT INTO `attendance` VALUES (759, 3, '2024-08-22 08:24:42', '2024-08-30 17:18:11', '2024-08-02', '打卡失败', '135 West Chang\'an Avenue, Xicheng District', 49.3258952, 81.7877257, '上班打卡');
INSERT INTO `attendance` VALUES (760, 6, '2024-09-04 08:52:12', '2024-09-09 17:54:47', '2024-08-13', '打卡失败', '766 Middle Huaihai Road, Huangpu District', 105.2947143, 20.0234376, '上班打卡');
INSERT INTO `attendance` VALUES (761, 3, '2024-09-16 08:39:29', '2024-08-23 17:03:28', '2024-09-11', '打卡失败', '49 FuXingMenNei Street, XiCheng District', 94.7905647, 153.2662920, '上班打卡');
INSERT INTO `attendance` VALUES (762, 2, '2024-08-20 08:05:05', '2024-08-14 17:25:25', '2024-08-30', '打卡失败', '14 Tianbei 1st Rd, Luohu District', 77.0268997, 23.2201554, '上班打卡');
INSERT INTO `attendance` VALUES (763, 4, '2024-08-27 08:27:04', '2024-08-05 17:53:50', '2024-09-06', '打卡失败', '264 Dong Zhi Men, Dongcheng District', 21.2158394, 51.3367432, '上班打卡');
INSERT INTO `attendance` VALUES (764, 1, '2024-09-05 08:44:50', '2024-09-20 17:41:57', '2024-09-20', '打卡失败', 'No. 33, Shuangqing Rd, Chenghua District', 137.6646829, 1.6361764, '上班打卡');
INSERT INTO `attendance` VALUES (765, 3, '2024-08-14 08:40:09', '2024-08-09 17:28:16', '2024-08-05', '打卡失败', '358 Qingshuihe 1st Rd, Luohu District', 108.5985274, 47.4857552, '上班打卡');
INSERT INTO `attendance` VALUES (766, 4, '2024-09-03 08:18:25', '2024-09-13 17:42:51', '2024-09-13', '打卡失败', '78 Qingshuihe 1st Rd, Luohu District', 116.8134482, 27.0910386, '上班打卡');
INSERT INTO `attendance` VALUES (767, 4, '2024-09-09 08:46:20', '2024-08-22 17:44:47', '2024-08-22', '打卡失败', '98 Kengmei 15th Alley', 80.4014171, 3.4913908, '上班打卡');
INSERT INTO `attendance` VALUES (768, 1, '2024-08-05 08:50:45', '2024-08-12 17:09:12', '2024-09-13', '打卡失败', '285 West Chang\'an Avenue, Xicheng District', 101.2570355, 68.5783056, '上班打卡');
INSERT INTO `attendance` VALUES (769, 4, '2024-08-19 08:16:46', '2024-08-22 17:40:42', '2024-08-16', '打卡失败', 'No.449, Dongsan Road, Erxianqiao, Chenghua District', 1.3273482, 164.4267501, '上班打卡');
INSERT INTO `attendance` VALUES (770, 6, '2024-08-29 08:34:19', '2024-09-10 17:52:27', '2024-08-27', '打卡失败', '360 Shennan E Rd, Cai Wu Wei, Luohu District', 94.7174520, 126.0394008, '上班打卡');
INSERT INTO `attendance` VALUES (771, 4, '2024-08-30 08:57:30', '2024-08-19 17:27:12', '2024-08-16', '打卡失败', '520 4th Section  Renmin South Road, Jinjiang District', 123.8489776, 31.0784347, '上班打卡');
INSERT INTO `attendance` VALUES (772, 1, '2024-08-28 08:00:31', '2024-09-20 17:59:41', '2024-08-12', '打卡失败', '516 Jianxiang Rd, Pudong', 73.0801771, 59.1671555, '上班打卡');
INSERT INTO `attendance` VALUES (773, 2, '2024-08-06 08:27:14', '2024-09-20 17:16:34', '2024-08-05', '打卡失败', '265 Binchuan Rd, Minhang District', 92.4433766, 111.5112856, '上班打卡');
INSERT INTO `attendance` VALUES (774, 1, '2024-08-09 08:19:08', '2024-09-02 17:27:15', '2024-08-27', '打卡失败', '411 FuXingMenNei Street, XiCheng District', 32.0130386, 51.6718882, '上班打卡');
INSERT INTO `attendance` VALUES (775, 6, '2024-09-18 08:47:13', '2024-08-26 17:50:20', '2024-09-06', '打卡失败', '955 East Wangfujing Street, Dongcheng District ', 124.8567402, 20.3188176, '上班打卡');
INSERT INTO `attendance` VALUES (776, 1, '2024-09-10 08:05:10', '2024-08-08 17:14:14', '2024-09-12', '打卡失败', '81 Huanqu South Street 2nd Alley', 84.0736505, 150.8686390, '上班打卡');
INSERT INTO `attendance` VALUES (777, 1, '2024-09-20 08:47:08', '2024-08-29 17:11:03', '2024-08-21', '打卡失败', '509 Sanlitun Road, Chaoyang District', 47.3306039, 34.9718048, '上班打卡');
INSERT INTO `attendance` VALUES (778, 4, '2024-08-28 08:46:16', '2024-08-05 17:43:04', '2024-08-15', '打卡失败', '192 Middle Huaihai Road, Huangpu District', 35.6165513, 67.0700631, '上班打卡');
INSERT INTO `attendance` VALUES (779, 4, '2024-09-13 08:13:06', '2024-08-26 17:29:44', '2024-08-12', '打卡失败', '824 W Ring Rd, Buji Town, Longgang', 120.2386428, 126.3226125, '上班打卡');
INSERT INTO `attendance` VALUES (780, 3, '2024-08-16 08:15:45', '2024-08-23 17:58:42', '2024-09-11', '打卡失败', 'No. 393, Shuangqing Rd, Chenghua District', 124.3909620, 149.4263177, '上班打卡');
INSERT INTO `attendance` VALUES (781, 6, '2024-08-19 08:50:42', '2024-09-19 17:46:11', '2024-08-14', '打卡失败', '510 3rd Section Hongxing Road, Jinjiang District', 104.9756779, 92.6328705, '上班打卡');
INSERT INTO `attendance` VALUES (782, 6, '2024-09-02 08:43:54', '2024-08-08 17:22:08', '2024-08-28', '打卡失败', '982 Jingtian East 1st St, Futian District', 86.6029356, 45.0238152, '上班打卡');
INSERT INTO `attendance` VALUES (783, 4, '2024-09-03 08:38:13', '2024-08-19 17:51:13', '2024-08-23', '打卡失败', '614 Yueliu Rd, Fangshan District', 98.3438544, 23.8555714, '上班打卡');
INSERT INTO `attendance` VALUES (784, 5, '2024-08-12 08:54:07', '2024-09-06 17:20:24', '2024-08-01', '打卡失败', '728 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 96.8934297, 118.9243440, '上班打卡');
INSERT INTO `attendance` VALUES (785, 5, '2024-09-13 08:44:40', '2024-08-19 17:48:43', '2024-08-29', '打卡失败', 'No. 121, Shuangqing Rd, Chenghua District', 13.4006631, 54.1265258, '上班打卡');
INSERT INTO `attendance` VALUES (786, 5, '2024-08-08 08:35:46', '2024-09-09 17:29:43', '2024-09-06', '打卡失败', '292 Tangyuan Street 5th Alley, Airport Road, Baiyun', 145.6745645, 60.2271312, '上班打卡');
INSERT INTO `attendance` VALUES (787, 4, '2024-09-20 08:31:16', '2024-08-05 17:28:22', '2024-09-23', '打卡失败', 'No.680, Dongsan Road, Erxianqiao, Chenghua District', 6.1321082, 154.2554429, '上班打卡');
INSERT INTO `attendance` VALUES (788, 1, '2024-08-20 08:31:28', '2024-08-02 17:32:13', '2024-09-12', '打卡失败', '997 3rd Section Hongxing Road, Jinjiang District', 124.5853952, 13.8894786, '上班打卡');
INSERT INTO `attendance` VALUES (789, 1, '2024-08-27 08:18:11', '2024-09-05 17:20:23', '2024-09-04', '打卡失败', '509 Zhongshan 5th Rd, Zimaling Shangquan', 84.5023538, 94.7402519, '上班打卡');
INSERT INTO `attendance` VALUES (790, 1, '2024-08-20 08:21:01', '2024-08-19 17:08:31', '2024-08-13', '打卡失败', '845 Jiangnan West Road, Haizhu District', 6.1636586, 118.1297699, '上班打卡');
INSERT INTO `attendance` VALUES (791, 1, '2024-09-09 08:22:08', '2024-09-05 17:18:16', '2024-08-15', '打卡失败', '720 Tianbei 1st Rd, Luohu District', 42.7357905, 108.8730954, '上班打卡');
INSERT INTO `attendance` VALUES (792, 1, '2024-08-20 08:49:33', '2024-08-19 17:00:58', '2024-09-10', '打卡失败', '259 Shennan E Rd, Cai Wu Wei, Luohu District', 43.7839608, 96.0045244, '上班打卡');
INSERT INTO `attendance` VALUES (793, 3, '2024-08-28 08:49:40', '2024-09-19 17:29:29', '2024-08-30', '打卡失败', '314 Tianhe Road, Tianhe District', 8.2041430, 98.3401735, '上班打卡');
INSERT INTO `attendance` VALUES (794, 3, '2024-09-04 08:30:14', '2024-08-19 17:15:56', '2024-09-06', '打卡失败', '461 3rd Section Hongxing Road, Jinjiang District', 42.7073060, 61.1314422, '上班打卡');
INSERT INTO `attendance` VALUES (795, 5, '2024-09-20 08:43:01', '2024-08-05 17:49:53', '2024-08-26', '打卡失败', '703 2nd Zhongshan Road, Yuexiu District', 51.5460825, 148.1252251, '上班打卡');
INSERT INTO `attendance` VALUES (796, 3, '2024-08-06 08:32:37', '2024-09-03 17:20:44', '2024-08-28', '打卡失败', '361 Qingshuihe 1st Rd, Luohu District', 13.0646414, 26.9805815, '上班打卡');
INSERT INTO `attendance` VALUES (797, 5, '2024-08-05 08:52:58', '2024-08-19 17:13:38', '2024-09-06', '打卡失败', '650 Qingshuihe 1st Rd, Luohu District', 16.5913239, 43.8155842, '上班打卡');
INSERT INTO `attendance` VALUES (798, 4, '2024-09-13 08:27:54', '2024-08-19 17:15:18', '2024-08-21', '打卡失败', '293 Yueliu Rd, Fangshan District', 115.7532073, 85.4155906, '上班打卡');
INSERT INTO `attendance` VALUES (799, 5, '2024-08-14 08:12:15', '2024-09-20 17:04:20', '2024-09-02', '打卡失败', '809 Shennan Ave, Futian District', 17.4315149, 71.2252020, '上班打卡');
INSERT INTO `attendance` VALUES (800, 2, '2024-08-28 08:42:59', '2024-09-23 17:27:45', '2024-08-28', '打卡失败', '507 Tangyuan Street 5th Alley, Airport Road, Baiyun', 138.6151773, 111.8092732, '上班打卡');
INSERT INTO `attendance` VALUES (801, 5, '2024-08-01 08:22:18', '2024-08-19 17:44:33', '2024-09-18', '打卡失败', '428 Xue Yuan Yi Xiang, Longgang', 75.6947686, 68.4247008, '上班打卡');
INSERT INTO `attendance` VALUES (802, 2, '2024-09-13 08:52:42', '2024-08-30 17:23:56', '2024-09-20', '打卡失败', '484 Sanlitun Road, Chaoyang District', 114.2264920, 48.1970950, '上班打卡');
INSERT INTO `attendance` VALUES (803, 4, '2024-09-02 08:15:34', '2024-08-15 17:54:47', '2024-09-10', '打卡失败', '527 Sanlitun Road, Chaoyang District', 45.7407575, 55.4173100, '上班打卡');
INSERT INTO `attendance` VALUES (804, 4, '2024-08-07 08:41:35', '2024-09-19 17:48:23', '2024-08-30', '打卡失败', '496 Kengmei 15th Alley', 40.9726683, 75.2249484, '上班打卡');
INSERT INTO `attendance` VALUES (805, 3, '2024-08-29 08:20:16', '2024-08-28 17:56:33', '2024-08-30', '打卡失败', 'No.854, Dongsan Road, Erxianqiao, Chenghua District', 27.0158335, 126.0419351, '上班打卡');
INSERT INTO `attendance` VALUES (806, 1, '2024-09-17 08:33:07', '2024-08-09 17:24:14', '2024-08-12', '打卡失败', '356 Zhongshan 5th Rd, Zimaling Shangquan', 110.3232588, 30.7149547, '上班打卡');
INSERT INTO `attendance` VALUES (807, 5, '2024-08-12 08:33:21', '2024-09-13 17:43:42', '2024-08-19', '打卡失败', '106 Sanlitun Road, Chaoyang District', 125.1735291, 29.1971965, '上班打卡');
INSERT INTO `attendance` VALUES (808, 6, '2024-09-02 08:31:46', '2024-08-19 17:27:26', '2024-09-02', '打卡失败', '355 Jianxiang Rd, Pudong', 55.6829915, 31.6077777, '上班打卡');
INSERT INTO `attendance` VALUES (809, 1, '2024-09-06 08:34:05', '2024-08-05 17:13:06', '2024-09-09', '打卡失败', '874 Lefeng 6th Rd', 65.8914992, 36.5013908, '上班打卡');
INSERT INTO `attendance` VALUES (810, 2, '2024-08-14 08:31:53', '2024-08-05 17:19:22', '2024-08-20', '打卡失败', '921 W Ring Rd, Buji Town, Longgang', 109.8953237, 98.5724165, '上班打卡');
INSERT INTO `attendance` VALUES (811, 2, '2024-09-20 08:07:12', '2024-08-19 17:28:13', '2024-08-12', '打卡失败', '136 3rd Section Hongxing Road, Jinjiang District', 68.3910696, 13.6719651, '上班打卡');
INSERT INTO `attendance` VALUES (812, 4, '2024-08-15 08:51:51', '2024-09-12 17:11:01', '2024-08-23', '打卡失败', '899 Kengmei 15th Alley', 3.7747541, 141.7584821, '上班打卡');
INSERT INTO `attendance` VALUES (813, 3, '2024-09-16 08:56:25', '2024-08-07 17:31:29', '2024-08-19', '打卡失败', '48 Shennan Ave, Futian District', 91.2399148, 118.8054547, '上班打卡');
INSERT INTO `attendance` VALUES (814, 3, '2024-08-08 08:28:34', '2024-09-05 18:00:00', '2024-08-02', '打卡失败', '465 Kengmei 15th Alley', 80.2718757, 97.4187340, '上班打卡');
INSERT INTO `attendance` VALUES (815, 1, '2024-09-02 08:03:51', '2024-08-26 17:37:05', '2024-09-20', '打卡失败', '902 NO.6, YuShuang Road, ChengHua Distric', 113.7087063, 88.1158862, '上班打卡');
INSERT INTO `attendance` VALUES (816, 1, '2024-09-20 08:32:41', '2024-08-26 17:24:57', '2024-08-12', '打卡失败', '846 Shennan Ave, Futian District', 18.1122701, 23.1247781, '上班打卡');
INSERT INTO `attendance` VALUES (817, 1, '2024-08-09 08:34:43', '2024-09-06 17:44:25', '2024-09-20', '打卡失败', '228 FuXingMenNei Street, XiCheng District', 83.5693961, 162.6861971, '上班打卡');
INSERT INTO `attendance` VALUES (818, 5, '2024-09-04 08:29:41', '2024-08-14 17:10:38', '2024-09-13', '打卡失败', '799 Kengmei 15th Alley', 111.4657701, 11.6763514, '上班打卡');
INSERT INTO `attendance` VALUES (819, 1, '2024-09-06 08:15:35', '2024-08-19 17:48:03', '2024-09-06', '打卡失败', '380 NO.6, YuShuang Road, ChengHua Distric', 102.8011512, 128.5460204, '上班打卡');
INSERT INTO `attendance` VALUES (820, 4, '2024-08-22 08:49:33', '2024-09-20 17:02:13', '2024-09-06', '打卡失败', '585 Kengmei 15th Alley', 28.8210167, 140.4976285, '上班打卡');
INSERT INTO `attendance` VALUES (821, 3, '2024-09-18 08:15:25', '2024-09-13 17:28:06', '2024-09-20', '打卡失败', '762 Shennan Ave, Futian District', 108.2470518, 115.7290911, '上班打卡');
INSERT INTO `attendance` VALUES (822, 1, '2024-09-11 08:37:41', '2024-08-19 17:41:21', '2024-08-15', '打卡失败', '806 Binchuan Rd, Minhang District', 147.8574858, 111.5612328, '上班打卡');
INSERT INTO `attendance` VALUES (823, 1, '2024-09-09 08:11:21', '2024-09-13 17:41:31', '2024-08-30', '打卡失败', '933 FuXingMenNei Street, XiCheng District', 75.9965253, 11.3735095, '上班打卡');
INSERT INTO `attendance` VALUES (824, 2, '2024-08-01 08:41:18', '2024-09-04 17:44:48', '2024-08-12', '打卡失败', '703 68 Qinghe Middle St, Haidian District', 23.5100372, 128.3389012, '上班打卡');
INSERT INTO `attendance` VALUES (825, 1, '2024-08-26 08:37:53', '2024-08-05 17:48:59', '2024-08-19', '打卡失败', '393 Tianhe Road, Tianhe District', 44.7997272, 12.3117468, '上班打卡');
INSERT INTO `attendance` VALUES (826, 1, '2024-08-28 08:11:25', '2024-09-11 17:20:21', '2024-09-13', '打卡失败', '99 Hongqiao Rd., Xu Hui District', 55.8487091, 116.0109954, '上班打卡');
INSERT INTO `attendance` VALUES (827, 2, '2024-09-11 08:44:21', '2024-09-03 17:42:08', '2024-08-23', '打卡失败', '617 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 81.3801072, 155.4637013, '上班打卡');
INSERT INTO `attendance` VALUES (828, 5, '2024-09-12 08:31:53', '2024-09-16 17:00:53', '2024-08-19', '打卡失败', '11 Tangyuan Street 5th Alley, Airport Road, Baiyun', 41.0138093, 90.9537756, '上班打卡');
INSERT INTO `attendance` VALUES (829, 5, '2024-08-13 08:36:06', '2024-09-03 17:12:45', '2024-09-03', '打卡失败', '103 3rd Section Hongxing Road, Jinjiang District', 50.6891355, 133.0821386, '上班打卡');
INSERT INTO `attendance` VALUES (830, 6, '2024-09-06 08:24:17', '2024-09-13 17:49:36', '2024-08-02', '打卡失败', '586 Hongqiao Rd., Xu Hui District', 67.2881073, 97.5046616, '上班打卡');
INSERT INTO `attendance` VALUES (831, 6, '2024-08-02 08:55:38', '2024-08-26 17:50:55', '2024-09-06', '打卡失败', '279 Middle Huaihai Road, Huangpu District', 101.2264461, 4.9257009, '上班打卡');
INSERT INTO `attendance` VALUES (832, 1, '2024-08-22 08:48:14', '2024-09-13 17:17:53', '2024-09-09', '打卡失败', 'No.407, Dongsan Road, Erxianqiao, Chenghua District', 68.4663919, 141.2596898, '上班打卡');
INSERT INTO `attendance` VALUES (833, 6, '2024-08-01 08:20:07', '2024-09-05 17:22:10', '2024-08-14', '打卡失败', '873 West Chang\'an Avenue, Xicheng District', 37.4059791, 37.5064620, '上班打卡');
INSERT INTO `attendance` VALUES (834, 5, '2024-08-12 08:15:15', '2024-08-30 17:28:01', '2024-08-16', '打卡失败', '250 Shanhu Rd', 80.1595900, 11.6981679, '上班打卡');
INSERT INTO `attendance` VALUES (835, 5, '2024-09-20 08:16:13', '2024-08-30 17:54:22', '2024-08-05', '打卡失败', '909 Xiaoping E Rd, Baiyun ', 140.9699616, 50.8276252, '上班打卡');
INSERT INTO `attendance` VALUES (836, 1, '2024-08-08 08:45:45', '2024-09-20 17:24:01', '2024-09-06', '打卡失败', '776 NO.6, YuShuang Road, ChengHua Distric', 133.7465534, 36.9053635, '上班打卡');
INSERT INTO `attendance` VALUES (837, 3, '2024-09-09 08:06:56', '2024-08-23 17:29:00', '2024-08-13', '打卡失败', '416 2nd Zhongshan Road, Yuexiu District', 115.9040614, 29.2707240, '上班打卡');
INSERT INTO `attendance` VALUES (838, 1, '2024-09-13 08:21:51', '2024-08-09 17:24:33', '2024-08-08', '打卡失败', '495 NO.6, YuShuang Road, ChengHua Distric', 81.1924374, 52.5388157, '上班打卡');
INSERT INTO `attendance` VALUES (839, 6, '2024-08-05 08:25:34', '2024-09-04 17:31:38', '2024-09-04', '打卡失败', '118 East Wangfujing Street, Dongcheng District ', 134.3223847, 163.8638452, '上班打卡');
INSERT INTO `attendance` VALUES (840, 4, '2024-08-26 08:02:03', '2024-09-16 17:17:53', '2024-08-30', '打卡失败', '404 Hongqiao Rd., Xu Hui District', 77.4368337, 11.7023083, '上班打卡');
INSERT INTO `attendance` VALUES (841, 3, '2024-08-12 08:31:55', '2024-08-20 17:28:02', '2024-08-27', '打卡失败', '25 West Chang\'an Avenue, Xicheng District', 103.7952668, 53.1451739, '上班打卡');
INSERT INTO `attendance` VALUES (842, 4, '2024-09-05 08:36:12', '2024-08-26 17:37:29', '2024-08-07', '打卡失败', '867 Qingshuihe 1st Rd, Luohu District', 58.7490912, 157.0826494, '上班打卡');
INSERT INTO `attendance` VALUES (843, 1, '2024-08-23 08:04:30', '2024-08-12 17:40:15', '2024-09-09', '打卡失败', '329 Tianhe Road, Tianhe District', 144.1053830, 154.6896680, '上班打卡');
INSERT INTO `attendance` VALUES (844, 5, '2024-09-20 08:22:24', '2024-08-19 17:00:52', '2024-08-05', '打卡失败', '139 Zhongshan 5th Rd, Zimaling Shangquan', 6.8149839, 115.2844895, '上班打卡');
INSERT INTO `attendance` VALUES (845, 5, '2024-08-16 08:24:29', '2024-09-06 17:09:27', '2024-09-20', '打卡失败', '662 Zhongshan 5th Rd, Zimaling Shangquan', 84.4996897, 63.9489159, '上班打卡');
INSERT INTO `attendance` VALUES (846, 5, '2024-08-23 08:34:27', '2024-08-26 17:24:05', '2024-09-03', '打卡失败', '467 028 County Rd, Yanqing District', 23.7189202, 36.0233627, '上班打卡');
INSERT INTO `attendance` VALUES (847, 1, '2024-09-20 08:30:34', '2024-08-12 17:33:33', '2024-08-28', '打卡失败', '995 East Wangfujing Street, Dongcheng District ', 144.3307288, 97.6800414, '上班打卡');
INSERT INTO `attendance` VALUES (848, 5, '2024-08-26 08:38:57', '2024-08-13 17:01:31', '2024-09-09', '打卡失败', 'No.787, Dongsan Road, Erxianqiao, Chenghua District', 7.0174882, 16.1016423, '上班打卡');
INSERT INTO `attendance` VALUES (849, 1, '2024-09-16 08:39:19', '2024-08-26 17:13:59', '2024-09-13', '打卡失败', '666 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 96.4032460, 85.4436918, '上班打卡');
INSERT INTO `attendance` VALUES (850, 2, '2024-09-10 08:33:56', '2024-08-12 17:57:03', '2024-08-20', '打卡失败', '592 Binchuan Rd, Minhang District', 14.1029031, 122.8248442, '上班打卡');
INSERT INTO `attendance` VALUES (851, 4, '2024-09-03 08:13:57', '2024-09-16 17:27:33', '2024-09-06', '打卡失败', '920 Jianxiang Rd, Pudong', 39.4757905, 68.0396407, '上班打卡');
INSERT INTO `attendance` VALUES (852, 3, '2024-09-20 08:11:10', '2024-09-19 17:46:50', '2024-08-16', '打卡失败', '785 Shennan Ave, Futian District', 35.7805021, 154.9674612, '上班打卡');
INSERT INTO `attendance` VALUES (853, 4, '2024-08-27 08:11:50', '2024-08-27 17:45:24', '2024-09-20', '打卡失败', '894 3rd Section Hongxing Road, Jinjiang District', 94.2162929, 65.3327591, '上班打卡');
INSERT INTO `attendance` VALUES (854, 3, '2024-09-06 08:12:23', '2024-08-15 17:15:52', '2024-09-05', '打卡失败', '563 Xue Yuan Yi Xiang, Longgang', 35.0198259, 93.0866486, '上班打卡');
INSERT INTO `attendance` VALUES (855, 4, '2024-08-12 08:52:11', '2024-08-07 17:36:07', '2024-08-07', '打卡失败', '886 Shennan E Rd, Cai Wu Wei, Luohu District', 140.7348807, 103.0921433, '上班打卡');
INSERT INTO `attendance` VALUES (856, 1, '2024-08-06 08:55:55', '2024-08-05 17:44:30', '2024-09-13', '打卡失败', '121 Xiaoping E Rd, Baiyun ', 29.5583572, 58.9602881, '上班打卡');
INSERT INTO `attendance` VALUES (857, 1, '2024-08-26 08:21:25', '2024-09-13 17:54:06', '2024-09-02', '打卡失败', '798 028 County Rd, Yanqing District', 90.3701717, 90.2220848, '上班打卡');
INSERT INTO `attendance` VALUES (858, 4, '2024-09-13 08:04:26', '2024-08-05 17:01:50', '2024-08-09', '打卡失败', '254 Shennan E Rd, Cai Wu Wei, Luohu District', 95.5827162, 79.9879491, '上班打卡');
INSERT INTO `attendance` VALUES (859, 4, '2024-09-13 08:56:46', '2024-08-22 17:21:06', '2024-08-19', '打卡失败', '139 Qingshuihe 1st Rd, Luohu District', 94.4526213, 124.6549713, '上班打卡');
INSERT INTO `attendance` VALUES (860, 1, '2024-08-05 08:39:23', '2024-08-26 17:45:40', '2024-08-21', '打卡失败', '153 FuXingMenNei Street, XiCheng District', 110.2366810, 40.1436225, '上班打卡');
INSERT INTO `attendance` VALUES (861, 2, '2024-08-08 08:23:02', '2024-08-26 17:41:14', '2024-09-09', '打卡失败', 'No. 480, Shuangqing Rd, Chenghua District', 140.5123917, 44.9322389, '上班打卡');
INSERT INTO `attendance` VALUES (862, 2, '2024-08-26 08:42:42', '2024-08-30 17:23:10', '2024-09-09', '打卡失败', '102 Qingshuihe 1st Rd, Luohu District', 45.4824178, 38.4765748, '上班打卡');
INSERT INTO `attendance` VALUES (863, 2, '2024-08-21 08:58:51', '2024-08-15 17:30:28', '2024-08-20', '打卡失败', '568 Yueliu Rd, Fangshan District', 112.3045017, 10.0631385, '上班打卡');
INSERT INTO `attendance` VALUES (864, 2, '2024-09-19 08:45:23', '2024-09-09 17:58:10', '2024-08-19', '打卡失败', '289 Xiaoping E Rd, Baiyun ', 61.3316469, 47.9239738, '上班打卡');
INSERT INTO `attendance` VALUES (865, 1, '2024-08-05 08:16:41', '2024-08-13 17:37:32', '2024-09-20', '打卡失败', '366 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 122.2949724, 136.9319978, '上班打卡');
INSERT INTO `attendance` VALUES (866, 1, '2024-09-20 08:59:08', '2024-09-12 17:34:17', '2024-08-15', '打卡失败', '177 Dongtai 5th St', 81.3592124, 101.8656567, '上班打卡');
INSERT INTO `attendance` VALUES (867, 5, '2024-09-11 08:57:37', '2024-09-05 17:04:56', '2024-08-26', '打卡失败', '787 Ganlan Rd, Pudong', 111.0542941, 143.7725823, '上班打卡');
INSERT INTO `attendance` VALUES (868, 5, '2024-08-26 08:03:11', '2024-08-20 17:37:58', '2024-09-09', '打卡失败', '397 Xue Yuan Yi Xiang, Longgang', 109.9900803, 2.0740441, '上班打卡');
INSERT INTO `attendance` VALUES (869, 6, '2024-09-20 08:41:17', '2024-09-06 17:32:12', '2024-08-27', '打卡失败', '76 Huaxia St, Jinghua Shangquan', 64.2137605, 46.3533972, '上班打卡');
INSERT INTO `attendance` VALUES (870, 3, '2024-08-26 08:48:40', '2024-08-27 17:45:20', '2024-09-18', '打卡失败', '757 Hongqiao Rd., Xu Hui District', 4.4218035, 108.6382757, '上班打卡');
INSERT INTO `attendance` VALUES (871, 4, '2024-08-22 08:58:44', '2024-09-04 17:56:57', '2024-09-16', '打卡失败', '17 Tianhe Road, Tianhe District', 9.2322096, 75.4273020, '上班打卡');
INSERT INTO `attendance` VALUES (872, 2, '2024-08-02 08:16:38', '2024-08-15 17:18:12', '2024-08-16', '打卡失败', '898 Dongtai 5th St', 148.2634071, 147.5065833, '上班打卡');
INSERT INTO `attendance` VALUES (873, 3, '2024-08-30 08:13:42', '2024-09-09 17:13:50', '2024-08-30', '打卡失败', '901 Xiaoping E Rd, Baiyun ', 132.7348321, 93.4905489, '上班打卡');
INSERT INTO `attendance` VALUES (874, 1, '2024-08-14 08:48:02', '2024-09-04 17:13:29', '2024-08-09', '打卡失败', '621 Shanhu Rd', 33.9197865, 9.6445703, '上班打卡');
INSERT INTO `attendance` VALUES (875, 6, '2024-08-26 08:06:26', '2024-09-13 17:46:49', '2024-08-28', '打卡失败', '973 Middle Huaihai Road, Huangpu District', 77.0329777, 101.0319551, '上班打卡');
INSERT INTO `attendance` VALUES (876, 1, '2024-09-18 08:08:07', '2024-08-19 17:27:46', '2024-09-09', '打卡失败', '709 Kengmei 15th Alley', 110.9732190, 51.1172145, '上班打卡');
INSERT INTO `attendance` VALUES (877, 4, '2024-08-05 08:40:00', '2024-08-26 17:44:42', '2024-08-14', '打卡失败', 'No. 453, Shuangqing Rd, Chenghua District', 30.9601129, 72.1567712, '上班打卡');
INSERT INTO `attendance` VALUES (878, 6, '2024-09-19 08:01:00', '2024-09-10 17:04:06', '2024-08-09', '打卡失败', '226 Sanlitun Road, Chaoyang District', 62.0955273, 153.9989406, '上班打卡');
INSERT INTO `attendance` VALUES (879, 3, '2024-09-20 08:37:28', '2024-08-07 17:39:42', '2024-08-14', '打卡失败', '405 Qingshuihe 1st Rd, Luohu District', 53.9100479, 100.7311707, '上班打卡');
INSERT INTO `attendance` VALUES (880, 2, '2024-08-28 08:10:27', '2024-08-08 17:37:31', '2024-08-26', '打卡失败', '152 Yueliu Rd, Fangshan District', 131.7247465, 24.7909975, '上班打卡');
INSERT INTO `attendance` VALUES (881, 2, '2024-09-17 08:46:24', '2024-09-13 17:18:32', '2024-08-13', '打卡失败', '56 Huanqu South Street 2nd Alley', 38.1879251, 120.8785289, '上班打卡');
INSERT INTO `attendance` VALUES (882, 3, '2024-09-06 08:16:48', '2024-09-13 17:45:31', '2024-08-07', '打卡失败', '387 Binchuan Rd, Minhang District', 72.8911400, 43.2462300, '上班打卡');
INSERT INTO `attendance` VALUES (883, 4, '2024-09-09 08:38:12', '2024-08-22 17:43:03', '2024-08-08', '打卡失败', '114 Shanhu Rd', 105.8792588, 79.1660465, '上班打卡');
INSERT INTO `attendance` VALUES (884, 2, '2024-08-08 08:18:59', '2024-09-10 17:01:22', '2024-08-30', '打卡失败', '376 Shanhu Rd', 56.8370065, 73.0179387, '上班打卡');
INSERT INTO `attendance` VALUES (885, 2, '2024-08-13 08:20:42', '2024-08-26 17:04:35', '2024-08-12', '打卡失败', '163 3rd Section Hongxing Road, Jinjiang District', 40.8902744, 42.0540451, '上班打卡');
INSERT INTO `attendance` VALUES (886, 4, '2024-09-10 08:01:56', '2024-08-07 17:06:57', '2024-08-20', '打卡失败', '241 FuXingMenNei Street, XiCheng District', 77.6877208, 42.3492562, '上班打卡');
INSERT INTO `attendance` VALUES (887, 2, '2024-09-09 08:58:33', '2024-08-26 17:40:50', '2024-08-26', '打卡失败', '200 68 Qinghe Middle St, Haidian District', 51.7376664, 92.1840366, '上班打卡');
INSERT INTO `attendance` VALUES (888, 4, '2024-09-20 08:11:35', '2024-09-13 17:35:13', '2024-08-12', '打卡失败', '253 Jiangnan West Road, Haizhu District', 67.4159534, 79.9122569, '上班打卡');
INSERT INTO `attendance` VALUES (889, 1, '2024-08-09 08:30:36', '2024-08-29 17:56:15', '2024-09-20', '打卡失败', '805 W Ring Rd, Buji Town, Longgang', 88.1373615, 93.5065534, '上班打卡');
INSERT INTO `attendance` VALUES (890, 5, '2024-08-02 08:11:25', '2024-09-12 17:03:45', '2024-09-13', '打卡失败', 'No.404, Dongsan Road, Erxianqiao, Chenghua District', 67.0248748, 49.5974793, '上班打卡');
INSERT INTO `attendance` VALUES (891, 6, '2024-09-13 08:14:18', '2024-09-03 17:58:51', '2024-08-23', '打卡失败', '515 4th Section  Renmin South Road, Jinjiang District', 137.6721957, 10.9970942, '上班打卡');
INSERT INTO `attendance` VALUES (892, 2, '2024-08-05 08:03:23', '2024-09-13 17:10:21', '2024-08-26', '打卡失败', '758 Dong Zhi Men, Dongcheng District', 103.0150588, 145.6282402, '上班打卡');
INSERT INTO `attendance` VALUES (893, 3, '2024-09-23 08:28:37', '2024-08-14 17:47:12', '2024-09-23', '打卡失败', '384 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 29.0228557, 49.4288663, '上班打卡');
INSERT INTO `attendance` VALUES (894, 5, '2024-08-14 08:32:44', '2024-08-19 17:36:41', '2024-08-14', '打卡失败', '836 FuXingMenNei Street, XiCheng District', 111.0555318, 51.5101904, '上班打卡');
INSERT INTO `attendance` VALUES (895, 2, '2024-09-19 08:09:40', '2024-08-26 17:30:58', '2024-08-26', '打卡失败', '339 Jianxiang Rd, Pudong', 108.1308368, 146.7265504, '上班打卡');
INSERT INTO `attendance` VALUES (896, 4, '2024-08-05 08:19:29', '2024-09-19 17:53:00', '2024-09-13', '打卡失败', '269 FuXingMenNei Street, XiCheng District', 110.0276642, 129.3091148, '上班打卡');
INSERT INTO `attendance` VALUES (897, 4, '2024-08-06 08:24:27', '2024-09-20 17:59:41', '2024-08-16', '打卡失败', '53 NO.6, YuShuang Road, ChengHua Distric', 42.6909456, 108.3068478, '上班打卡');
INSERT INTO `attendance` VALUES (898, 5, '2024-08-05 08:18:29', '2024-08-12 17:53:35', '2024-08-28', '打卡失败', '197 Hongqiao Rd., Xu Hui District', 43.3765933, 106.6301863, '上班打卡');
INSERT INTO `attendance` VALUES (899, 3, '2024-08-20 08:19:26', '2024-08-02 17:51:44', '2024-09-16', '打卡失败', 'No. 669, Shuangqing Rd, Chenghua District', 85.3855615, 113.8148097, '上班打卡');
INSERT INTO `attendance` VALUES (900, 2, '2024-08-19 08:56:27', '2024-08-12 17:41:08', '2024-08-28', '打卡失败', '384 Tianhe Road, Tianhe District', 139.5719524, 57.8101970, '上班打卡');
INSERT INTO `attendance` VALUES (901, 2, '2024-09-18 08:11:49', '2024-08-13 17:40:04', '2024-08-05', '打卡失败', '876 Dongtai 5th St', 49.4939950, 156.3706457, '上班打卡');
INSERT INTO `attendance` VALUES (902, 4, '2024-08-12 08:25:53', '2024-08-08 17:39:59', '2024-09-20', '打卡失败', '784 W Ring Rd, Buji Town, Longgang', 101.8773402, 60.6235557, '上班打卡');
INSERT INTO `attendance` VALUES (903, 1, '2024-09-06 08:08:23', '2024-08-27 17:29:23', '2024-08-02', '打卡失败', '985 Dongtai 5th St', 25.3404700, 48.0046609, '上班打卡');
INSERT INTO `attendance` VALUES (904, 1, '2024-08-08 08:46:30', '2024-09-13 17:30:29', '2024-08-20', '打卡失败', '648 Zhongshan 5th Rd, Zimaling Shangquan', 73.1196754, 154.7662360, '上班打卡');
INSERT INTO `attendance` VALUES (905, 1, '2024-08-26 08:53:19', '2024-08-27 17:53:46', '2024-09-02', '打卡失败', '306 Middle Huaihai Road, Huangpu District', 0.2129977, 98.1076355, '上班打卡');
INSERT INTO `attendance` VALUES (906, 4, '2024-08-12 08:51:20', '2024-08-05 17:27:08', '2024-08-29', '打卡失败', '603 2nd Zhongshan Road, Yuexiu District', 129.3165670, 78.0934951, '上班打卡');
INSERT INTO `attendance` VALUES (907, 1, '2024-09-20 08:47:09', '2024-09-13 17:28:35', '2024-08-26', '打卡失败', '537 Sanlitun Road, Chaoyang District', 96.7893036, 126.0285517, '上班打卡');
INSERT INTO `attendance` VALUES (908, 5, '2024-08-07 08:37:21', '2024-09-23 17:40:06', '2024-09-04', '打卡失败', '873 East Wangfujing Street, Dongcheng District ', 57.8031565, 138.8765973, '上班打卡');
INSERT INTO `attendance` VALUES (909, 4, '2024-09-13 08:59:03', '2024-09-13 17:10:27', '2024-09-06', '打卡失败', '237 FuXingMenNei Street, XiCheng District', 45.0897477, 136.7642405, '上班打卡');
INSERT INTO `attendance` VALUES (910, 3, '2024-08-26 08:03:09', '2024-09-03 17:59:36', '2024-09-06', '打卡失败', '284 4th Section  Renmin South Road, Jinjiang District', 56.0377837, 113.8869085, '上班打卡');
INSERT INTO `attendance` VALUES (911, 2, '2024-08-27 08:17:38', '2024-09-03 17:31:56', '2024-08-02', '打卡失败', '397 Kengmei 15th Alley', 25.0183986, 90.1990035, '上班打卡');
INSERT INTO `attendance` VALUES (912, 1, '2024-08-15 08:12:04', '2024-08-13 17:43:46', '2024-08-09', '打卡失败', '722 Ganlan Rd, Pudong', 24.0657035, 107.6802697, '上班打卡');
INSERT INTO `attendance` VALUES (913, 4, '2024-08-08 08:56:25', '2024-09-05 17:45:20', '2024-08-19', '打卡失败', '953 028 County Rd, Yanqing District', 12.0665248, 36.5004226, '上班打卡');
INSERT INTO `attendance` VALUES (914, 1, '2024-08-16 08:36:41', '2024-08-14 17:08:57', '2024-09-10', '打卡失败', '561 Zhongshan 5th Rd, Zimaling Shangquan', 140.5915706, 113.4917632, '上班打卡');
INSERT INTO `attendance` VALUES (915, 1, '2024-09-10 08:42:10', '2024-08-19 17:14:16', '2024-08-14', '打卡失败', '368 Yueliu Rd, Fangshan District', 23.5795131, 92.0068901, '上班打卡');
INSERT INTO `attendance` VALUES (916, 2, '2024-08-06 08:17:38', '2024-08-16 17:19:33', '2024-08-19', '打卡失败', '626 Sanlitun Road, Chaoyang District', 57.5850442, 82.4305515, '上班打卡');
INSERT INTO `attendance` VALUES (917, 3, '2024-08-14 08:53:52', '2024-08-12 17:19:07', '2024-08-09', '打卡失败', '231 Dongtai 5th St', 113.4652407, 22.3667081, '上班打卡');
INSERT INTO `attendance` VALUES (918, 1, '2024-08-16 08:29:52', '2024-08-06 17:12:48', '2024-08-26', '打卡失败', 'No. 925, Shuangqing Rd, Chenghua District', 61.3151631, 73.8459729, '上班打卡');
INSERT INTO `attendance` VALUES (919, 4, '2024-09-19 08:09:57', '2024-08-09 17:19:10', '2024-08-01', '打卡失败', '969 Middle Huaihai Road, Huangpu District', 88.9981386, 62.2121811, '上班打卡');
INSERT INTO `attendance` VALUES (920, 2, '2024-08-27 08:10:22', '2024-08-30 17:58:52', '2024-08-22', '打卡失败', '809 Jingtian East 1st St, Futian District', 60.6343565, 67.3290534, '上班打卡');
INSERT INTO `attendance` VALUES (921, 3, '2024-08-05 08:26:13', '2024-09-20 17:54:56', '2024-08-14', '打卡失败', '227 Middle Huaihai Road, Huangpu District', 35.7919194, 40.4554280, '上班打卡');
INSERT INTO `attendance` VALUES (922, 6, '2024-09-20 08:57:24', '2024-09-10 17:11:44', '2024-08-05', '打卡失败', '828 68 Qinghe Middle St, Haidian District', 111.2793297, 132.9669349, '上班打卡');
INSERT INTO `attendance` VALUES (923, 4, '2024-09-16 08:50:11', '2024-08-20 17:04:57', '2024-09-10', '打卡失败', '514 Dong Zhi Men, Dongcheng District', 59.6286685, 152.4124648, '上班打卡');
INSERT INTO `attendance` VALUES (924, 2, '2024-08-15 08:32:57', '2024-08-19 17:35:28', '2024-09-06', '打卡失败', '335 Tianbei 1st Rd, Luohu District', 106.1164232, 158.1111968, '上班打卡');
INSERT INTO `attendance` VALUES (925, 3, '2024-09-12 08:15:01', '2024-08-27 17:20:59', '2024-09-20', '打卡失败', '674 NO.6, YuShuang Road, ChengHua Distric', 145.8240738, 97.9972328, '上班打卡');
INSERT INTO `attendance` VALUES (926, 1, '2024-09-17 08:33:21', '2024-08-12 17:13:00', '2024-08-09', '打卡失败', '390 Huaxia St, Jinghua Shangquan', 145.0498939, 101.7762937, '上班打卡');
INSERT INTO `attendance` VALUES (927, 6, '2024-08-26 08:38:13', '2024-08-26 17:00:02', '2024-08-30', '打卡失败', '800 Binchuan Rd, Minhang District', 21.2319920, 22.6102608, '上班打卡');
INSERT INTO `attendance` VALUES (928, 1, '2024-08-23 08:54:54', '2024-08-20 17:42:26', '2024-09-03', '打卡失败', '607 East Wangfujing Street, Dongcheng District ', 117.3913767, 115.0822635, '上班打卡');
INSERT INTO `attendance` VALUES (929, 3, '2024-08-05 08:25:58', '2024-08-14 17:00:08', '2024-09-13', '打卡失败', '933 NO.6, YuShuang Road, ChengHua Distric', 17.5106869, 6.9629575, '上班打卡');
INSERT INTO `attendance` VALUES (930, 5, '2024-08-16 08:01:35', '2024-09-18 17:29:03', '2024-09-10', '打卡失败', '248 Tianbei 1st Rd, Luohu District', 18.7423000, 136.0440676, '上班打卡');
INSERT INTO `attendance` VALUES (931, 4, '2024-09-06 08:31:59', '2024-08-19 17:18:39', '2024-08-13', '打卡失败', '986 Yueliu Rd, Fangshan District', 2.3815887, 151.0541788, '上班打卡');
INSERT INTO `attendance` VALUES (932, 4, '2024-09-10 08:30:54', '2024-08-06 17:59:39', '2024-09-10', '打卡失败', '413 FuXingMenNei Street, XiCheng District', 46.1712183, 99.3265624, '上班打卡');
INSERT INTO `attendance` VALUES (933, 2, '2024-08-12 08:40:42', '2024-08-15 17:13:40', '2024-08-19', '打卡失败', '293 Hongqiao Rd., Xu Hui District', 136.1600835, 139.2054986, '上班打卡');
INSERT INTO `attendance` VALUES (934, 4, '2024-08-21 08:32:54', '2024-08-07 17:13:07', '2024-09-19', '打卡失败', '778 Tianhe Road, Tianhe District', 1.5728522, 58.0547651, '上班打卡');
INSERT INTO `attendance` VALUES (935, 5, '2024-08-28 08:42:19', '2024-08-13 17:36:04', '2024-08-19', '打卡失败', '674 Sanlitun Road, Chaoyang District', 103.3782782, 84.3102913, '上班打卡');
INSERT INTO `attendance` VALUES (936, 1, '2024-08-27 08:32:34', '2024-09-06 17:13:10', '2024-09-19', '打卡失败', '13 Jingtian East 1st St, Futian District', 0.0816136, 23.4260154, '上班打卡');
INSERT INTO `attendance` VALUES (937, 1, '2024-08-12 08:43:58', '2024-08-26 17:26:47', '2024-09-03', '打卡失败', '694 W Ring Rd, Buji Town, Longgang', 112.0734222, 159.4829182, '上班打卡');
INSERT INTO `attendance` VALUES (938, 2, '2024-08-23 08:39:52', '2024-08-19 17:55:49', '2024-08-28', '打卡失败', 'No.27, Dongsan Road, Erxianqiao, Chenghua District', 108.2699804, 118.1440179, '上班打卡');
INSERT INTO `attendance` VALUES (939, 3, '2024-08-22 08:13:22', '2024-09-20 17:26:08', '2024-08-01', '打卡失败', '921 Tianhe Road, Tianhe District', 45.1545830, 62.8121650, '上班打卡');
INSERT INTO `attendance` VALUES (940, 1, '2024-09-13 08:37:38', '2024-08-22 17:57:09', '2024-08-16', '打卡失败', '268 Ganlan Rd, Pudong', 128.4471476, 81.6841655, '上班打卡');
INSERT INTO `attendance` VALUES (941, 2, '2024-08-12 08:22:36', '2024-09-17 17:05:50', '2024-09-04', '打卡失败', '440 Middle Huaihai Road, Huangpu District', 148.8976619, 162.1694778, '上班打卡');
INSERT INTO `attendance` VALUES (942, 1, '2024-09-20 08:45:28', '2024-08-14 17:31:14', '2024-08-01', '打卡失败', '228 Sanlitun Road, Chaoyang District', 38.8646146, 75.8627858, '上班打卡');
INSERT INTO `attendance` VALUES (943, 3, '2024-09-13 08:28:50', '2024-08-07 17:38:37', '2024-09-13', '打卡失败', '782 NO.6, YuShuang Road, ChengHua Distric', 28.6223597, 6.0100680, '上班打卡');
INSERT INTO `attendance` VALUES (944, 2, '2024-08-15 08:18:57', '2024-08-19 17:36:07', '2024-09-13', '打卡失败', '628 Jianxiang Rd, Pudong', 58.6801977, 159.3604162, '上班打卡');
INSERT INTO `attendance` VALUES (945, 4, '2024-08-09 08:55:47', '2024-08-02 17:47:00', '2024-09-04', '打卡失败', '125 West Chang\'an Avenue, Xicheng District', 90.5765902, 28.1026045, '上班打卡');
INSERT INTO `attendance` VALUES (946, 2, '2024-09-12 08:46:06', '2024-09-10 17:42:03', '2024-09-17', '打卡失败', 'No.12, Dongsan Road, Erxianqiao, Chenghua District', 119.1218673, 121.4936626, '上班打卡');
INSERT INTO `attendance` VALUES (947, 6, '2024-08-26 08:01:09', '2024-08-19 17:25:17', '2024-09-06', '打卡失败', '414 Jingtian East 1st St, Futian District', 73.7715109, 121.5977134, '上班打卡');
INSERT INTO `attendance` VALUES (948, 2, '2024-08-15 08:10:22', '2024-08-07 17:29:31', '2024-08-13', '打卡失败', '899 West Chang\'an Avenue, Xicheng District', 13.0498754, 63.3662364, '上班打卡');
INSERT INTO `attendance` VALUES (949, 3, '2024-08-28 08:30:56', '2024-08-19 17:26:09', '2024-08-30', '打卡失败', '245 Binchuan Rd, Minhang District', 40.4571113, 68.6953822, '上班打卡');
INSERT INTO `attendance` VALUES (950, 2, '2024-09-06 08:41:49', '2024-09-13 17:55:57', '2024-08-12', '打卡失败', '670 Shanhu Rd', 127.9391422, 14.7878286, '上班打卡');
INSERT INTO `attendance` VALUES (951, 1, '2024-08-26 08:27:13', '2024-08-26 17:34:11', '2024-09-11', '打卡失败', '29 Dongtai 5th St', 52.8439931, 146.1954361, '上班打卡');
INSERT INTO `attendance` VALUES (952, 3, '2024-08-22 08:35:02', '2024-08-30 17:39:28', '2024-08-19', '打卡失败', '593 4th Section  Renmin South Road, Jinjiang District', 55.2881279, 96.5268983, '上班打卡');
INSERT INTO `attendance` VALUES (953, 5, '2024-09-18 08:40:21', '2024-08-27 17:15:25', '2024-08-26', '打卡失败', '839 Hongqiao Rd., Xu Hui District', 30.2190010, 158.2779505, '上班打卡');
INSERT INTO `attendance` VALUES (954, 1, '2024-08-12 08:50:05', '2024-08-15 17:37:09', '2024-08-05', '打卡失败', '716 4th Section  Renmin South Road, Jinjiang District', 1.8911326, 49.5411422, '上班打卡');
INSERT INTO `attendance` VALUES (955, 1, '2024-08-01 08:44:25', '2024-08-15 17:46:32', '2024-09-11', '打卡失败', '293 2nd Zhongshan Road, Yuexiu District', 89.9164832, 24.3218218, '上班打卡');
INSERT INTO `attendance` VALUES (956, 3, '2024-08-26 08:20:51', '2024-08-30 17:50:56', '2024-09-19', '打卡失败', '685 Shennan Ave, Futian District', 25.1452643, 101.3115403, '上班打卡');
INSERT INTO `attendance` VALUES (957, 5, '2024-08-14 08:08:00', '2024-08-28 17:40:57', '2024-09-06', '打卡失败', '289 Jingtian East 1st St, Futian District', 58.7781766, 72.4552641, '上班打卡');
INSERT INTO `attendance` VALUES (958, 1, '2024-08-26 08:21:33', '2024-08-05 17:19:40', '2024-08-15', '打卡失败', '726 Lefeng 6th Rd', 124.7505262, 44.9313616, '上班打卡');
INSERT INTO `attendance` VALUES (959, 5, '2024-09-06 08:07:50', '2024-08-30 17:42:51', '2024-08-30', '打卡失败', '584 Ganlan Rd, Pudong', 58.1822015, 103.2064225, '上班打卡');
INSERT INTO `attendance` VALUES (960, 4, '2024-08-16 08:56:49', '2024-09-04 17:56:50', '2024-08-14', '打卡失败', 'No.987, Dongsan Road, Erxianqiao, Chenghua District', 45.8937477, 100.3084887, '上班打卡');
INSERT INTO `attendance` VALUES (961, 1, '2024-09-06 08:37:29', '2024-08-07 17:15:42', '2024-08-26', '打卡失败', '172 Middle Huaihai Road, Huangpu District', 99.6116923, 146.8177960, '上班打卡');
INSERT INTO `attendance` VALUES (962, 4, '2024-09-11 08:55:22', '2024-09-11 17:34:52', '2024-09-05', '打卡失败', '566 Huaxia St, Jinghua Shangquan', 111.0069711, 62.2872000, '上班打卡');
INSERT INTO `attendance` VALUES (963, 1, '2024-09-13 08:17:03', '2024-08-02 17:25:07', '2024-09-04', '打卡失败', '312 Ganlan Rd, Pudong', 112.3303253, 96.2003334, '上班打卡');
INSERT INTO `attendance` VALUES (964, 1, '2024-09-13 08:49:37', '2024-08-12 17:52:19', '2024-09-19', '打卡失败', '539 East Wangfujing Street, Dongcheng District ', 82.8588371, 27.7815355, '上班打卡');
INSERT INTO `attendance` VALUES (965, 2, '2024-08-19 08:16:41', '2024-09-13 17:14:35', '2024-09-10', '打卡失败', '638 Jianxiang Rd, Pudong', 139.7549076, 44.0658151, '上班打卡');
INSERT INTO `attendance` VALUES (966, 1, '2024-09-16 08:03:34', '2024-08-06 17:11:24', '2024-09-13', '打卡失败', '911 W Ring Rd, Buji Town, Longgang', 62.6424262, 123.4944112, '上班打卡');
INSERT INTO `attendance` VALUES (967, 3, '2024-09-18 08:11:12', '2024-09-17 17:00:55', '2024-09-13', '打卡失败', '159 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 79.4801304, 53.3384468, '上班打卡');
INSERT INTO `attendance` VALUES (968, 1, '2024-08-26 08:11:41', '2024-09-20 17:28:20', '2024-09-04', '打卡失败', '128 Tianhe Road, Tianhe District', 46.7531658, 80.9920352, '上班打卡');
INSERT INTO `attendance` VALUES (969, 6, '2024-09-06 08:04:02', '2024-08-09 17:59:28', '2024-08-05', '打卡失败', '316 Dongtai 5th St', 74.2850277, 130.3894454, '上班打卡');
INSERT INTO `attendance` VALUES (970, 2, '2024-08-15 08:42:31', '2024-08-21 17:07:55', '2024-08-30', '打卡失败', '113 Shanhu Rd', 140.3184214, 101.1709371, '上班打卡');
INSERT INTO `attendance` VALUES (971, 4, '2024-09-20 08:34:06', '2024-08-13 17:23:20', '2024-09-20', '打卡失败', '964 Tianhe Road, Tianhe District', 59.5488848, 33.6461809, '上班打卡');
INSERT INTO `attendance` VALUES (972, 1, '2024-08-07 08:09:15', '2024-08-30 17:41:37', '2024-09-20', '打卡失败', '749 East Wangfujing Street, Dongcheng District ', 67.7062664, 73.3683961, '上班打卡');
INSERT INTO `attendance` VALUES (973, 4, '2024-08-30 08:32:05', '2024-08-14 17:36:16', '2024-09-20', '打卡失败', '163 Qingshuihe 1st Rd, Luohu District', 26.9572371, 63.5535161, '上班打卡');
INSERT INTO `attendance` VALUES (974, 1, '2024-08-19 08:27:47', '2024-08-30 17:23:20', '2024-09-11', '打卡失败', '485 Zhongshan 5th Rd, Zimaling Shangquan', 148.0996645, 102.7462783, '上班打卡');
INSERT INTO `attendance` VALUES (975, 1, '2024-08-27 08:40:36', '2024-08-21 17:58:52', '2024-08-21', '打卡失败', '384 2nd Zhongshan Road, Yuexiu District', 89.9109683, 130.8308464, '上班打卡');
INSERT INTO `attendance` VALUES (976, 6, '2024-09-06 08:14:48', '2024-09-09 17:32:04', '2024-09-06', '打卡失败', '562 Zhongshan 5th Rd, Zimaling Shangquan', 79.1748478, 83.8277611, '上班打卡');
INSERT INTO `attendance` VALUES (977, 4, '2024-08-01 08:30:15', '2024-09-13 17:03:20', '2024-09-09', '打卡失败', '539 Tianhe Road, Tianhe District', 40.5623993, 20.1641904, '上班打卡');
INSERT INTO `attendance` VALUES (978, 1, '2024-09-02 08:47:21', '2024-08-05 17:23:04', '2024-08-27', '打卡失败', '874 Hongqiao Rd., Xu Hui District', 79.9807207, 89.5017090, '上班打卡');
INSERT INTO `attendance` VALUES (979, 4, '2024-08-09 08:04:58', '2024-08-26 17:51:43', '2024-09-12', '打卡失败', '985 Dong Zhi Men, Dongcheng District', 15.8977291, 87.8464811, '上班打卡');
INSERT INTO `attendance` VALUES (980, 3, '2024-09-16 08:00:14', '2024-09-09 17:23:51', '2024-08-08', '打卡失败', '529 Zhongshan 5th Rd, Zimaling Shangquan', 72.0382314, 107.8257530, '上班打卡');
INSERT INTO `attendance` VALUES (981, 1, '2024-09-12 08:11:46', '2024-09-20 17:09:56', '2024-08-30', '打卡失败', '331 FuXingMenNei Street, XiCheng District', 94.5247069, 86.7508977, '上班打卡');
INSERT INTO `attendance` VALUES (982, 1, '2024-08-22 08:12:26', '2024-08-19 17:36:30', '2024-08-13', '打卡失败', '623 Jianxiang Rd, Pudong', 142.6711825, 43.7338105, '上班打卡');
INSERT INTO `attendance` VALUES (983, 3, '2024-08-12 08:54:58', '2024-08-27 17:11:27', '2024-08-12', '打卡失败', '216 Daxin S Rd, Daxin Shangquan, Tianhe Qu', 58.6490447, 75.0178026, '上班打卡');
INSERT INTO `attendance` VALUES (984, 6, '2024-08-28 08:10:08', '2024-08-30 17:43:53', '2024-08-16', '打卡失败', '55 Middle Huaihai Road, Huangpu District', 52.9062301, 141.5771960, '上班打卡');
INSERT INTO `attendance` VALUES (985, 4, '2024-09-06 08:58:28', '2024-09-20 17:10:23', '2024-08-05', '打卡失败', '84 Shanhu Rd', 118.2057316, 154.3970100, '上班打卡');
INSERT INTO `attendance` VALUES (986, 2, '2024-09-06 08:50:44', '2024-09-06 17:55:34', '2024-08-09', '打卡失败', '666 Shennan Ave, Futian District', 43.3047587, 1.7494328, '上班打卡');
INSERT INTO `attendance` VALUES (987, 4, '2024-08-19 08:36:23', '2024-08-13 17:03:20', '2024-08-29', '打卡失败', '892 Shanhu Rd', 89.3515877, 3.9107149, '上班打卡');
INSERT INTO `attendance` VALUES (988, 2, '2024-09-09 08:54:21', '2024-09-23 17:28:51', '2024-08-27', '打卡失败', '565 Yueliu Rd, Fangshan District', 125.3920169, 24.2214173, '上班打卡');
INSERT INTO `attendance` VALUES (989, 3, '2024-08-22 08:52:22', '2024-09-09 17:23:59', '2024-09-11', '打卡失败', 'No.791, Dongsan Road, Erxianqiao, Chenghua District', 149.5446732, 127.7308535, '上班打卡');
INSERT INTO `attendance` VALUES (990, 5, '2024-09-02 08:43:04', '2024-09-13 17:00:59', '2024-08-21', '打卡失败', 'No. 72, Shuangqing Rd, Chenghua District', 114.8345093, 81.7274696, '上班打卡');
INSERT INTO `attendance` VALUES (991, 5, '2024-08-30 08:18:26', '2024-08-29 17:46:38', '2024-09-06', '打卡失败', '117 Jiangnan West Road, Haizhu District', 66.8170325, 70.2288933, '上班打卡');
INSERT INTO `attendance` VALUES (992, 5, '2024-08-19 08:10:18', '2024-09-12 17:31:28', '2024-08-07', '打卡失败', '86 Binchuan Rd, Minhang District', 104.0370543, 160.8804974, '上班打卡');
INSERT INTO `attendance` VALUES (993, 5, '2024-08-26 08:27:52', '2024-08-19 17:44:58', '2024-08-27', '打卡失败', '248 Huanqu South Street 2nd Alley', 74.3356709, 10.7231028, '上班打卡');
INSERT INTO `attendance` VALUES (994, 3, '2024-09-03 08:46:53', '2024-08-12 17:29:32', '2024-09-13', '打卡失败', '790 Lefeng 6th Rd', 133.7327389, 117.7298173, '上班打卡');
INSERT INTO `attendance` VALUES (995, 3, '2024-09-11 08:40:35', '2024-08-16 17:31:29', '2024-08-12', '打卡失败', '39 4th Section  Renmin South Road, Jinjiang District', 72.9564177, 35.9741621, '上班打卡');
INSERT INTO `attendance` VALUES (996, 4, '2024-09-20 08:07:47', '2024-08-09 17:42:23', '2024-08-30', '打卡失败', 'No. 179, Shuangqing Rd, Chenghua District', 6.1406677, 14.0224554, '上班打卡');
INSERT INTO `attendance` VALUES (997, 1, '2024-08-29 08:37:05', '2024-09-06 17:07:47', '2024-08-16', '打卡失败', '234 Dongtai 5th St', 125.2264066, 66.0889739, '上班打卡');
INSERT INTO `attendance` VALUES (998, 1, '2024-08-30 08:21:10', '2024-09-06 17:51:36', '2024-08-26', '打卡失败', '192 3rd Section Hongxing Road, Jinjiang District', 18.1533968, 132.8806259, '上班打卡');
INSERT INTO `attendance` VALUES (999, 4, '2024-09-05 08:51:22', '2024-09-17 17:44:44', '2024-09-04', '打卡失败', '80 Binchuan Rd, Minhang District', 126.6816482, 54.9084436, '上班打卡');
INSERT INTO `attendance` VALUES (1000, 2, '2024-08-19 08:47:18', '2024-09-23 17:03:23', '2024-08-26', '打卡失败', '900 Shennan E Rd, Cai Wu Wei, Luohu District', 80.0303868, 95.2166375, '上班打卡');

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
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 1, 1, '梁震南', '1yeWOPS3Kd', '5938189848', '123456', '192-0151-2897', 'liangzhenn8@qq.com', 0, '2024-09-10 15:13:01', '2024-09-10 15:13:01');
INSERT INTO `user` VALUES (2, 2, 2, '萧璐', 'q8fX9ms3FS', '0640707652', '123456', '155-6121-6220', 'luxiao@qq.com', 0, '2024-09-10 11:07:21', NULL);
INSERT INTO `user` VALUES (3, 2, 3, '何子异', '3StKs8VbER', '9917916138', '123456', '760-771-3163', 'ziyih@qq.com', 0, '2024-09-10 16:39:59', NULL);
INSERT INTO `user` VALUES (4, 2, 2, 'sadas', 'sadas', 'sdad', 'sdas', 'asdas', 'asdas', 1, '2024-09-03 16:26:01', NULL);
INSERT INTO `user` VALUES (5, 2, 5, '曹岚', 'EiwJyvOFW4', '6418582407', '123456', '28-460-1738', 'lancao@qq.com', 0, '2024-09-10 11:07:28', NULL);
INSERT INTO `user` VALUES (6, 3, 2, '黄岚', 'SHhufLveRR', '5855972111', '123456', '760-6340-3641', 'huang12@qq.com', 1, '2024-09-10 11:07:31', NULL);
INSERT INTO `user` VALUES (7, 2, 2, '小涂', 'ssss', '20221107040', '123456', NULL, NULL, 1, '2024-09-10 16:46:42', NULL);
INSERT INTO `user` VALUES (8, 2, 2, '小尹', 'sdasdsa', '2424389790', '123456', '2313231', '231231232', 1, '2024-09-19 17:05:21', NULL);

SET FOREIGN_KEY_CHECKS = 1;
