/*
 Navicat Premium Data Transfer

 Source Server         : linux_zmw
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 192.168.52.130:3306
 Source Schema         : zmw

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 04/06/2019 17:25:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`mw_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('mw11011', '15655556666', '67dd43f3227f38679506d27c4f589d1c', 1);

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章列表标题',
  `title_picture` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章列表缩略图',
  `status` int(5) NULL DEFAULT NULL COMMENT '文章状态',
  `openness` int(5) NULL DEFAULT NULL,
  `reading_number` int(10) NULL DEFAULT NULL,
  `category_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `music` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `praise_number` int(20) NULL DEFAULT NULL,
  `release_time` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for collector
-- ----------------------------
DROP TABLE IF EXISTS `collector`;
CREATE TABLE `collector`  (
  `mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `article_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`mw_id`, `article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `c_key` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_value` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c_describe` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`c_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('activemq.brokerURL', 'tcp://192.168.52.130:61616', NULL);
INSERT INTO `config` VALUES ('activemq.password', '123', 'mq管理页面密码');
INSERT INTO `config` VALUES ('activemq.port', '8161', NULL);
INSERT INTO `config` VALUES ('activemq.user', 'admin', 'mq管理页面账户');
INSERT INTO `config` VALUES ('db.jdbcUrl', 'jdbc:mysql://192.168.52.130:3306/zmw?useUnicode=true&characterEncoding=utf-8&useSSL=false', NULL);
INSERT INTO `config` VALUES ('db.password', '123456', NULL);
INSERT INTO `config` VALUES ('db.user', 'root', NULL);
INSERT INTO `config` VALUES ('elasticsearch.address', '192.168.52.130:9200', '多个地址用逗号隔开');
INSERT INTO `config` VALUES ('fastdfs.anti_steal_token', 'false', NULL);
INSERT INTO `config` VALUES ('fastdfs.charset', 'UTF-8', NULL);
INSERT INTO `config` VALUES ('fastdfs.connect_timeout', '2000', NULL);
INSERT INTO `config` VALUES ('fastdfs.network_timeout', '5000', NULL);
INSERT INTO `config` VALUES ('fastdfs.secret_key', 'FastDFS1234567890', NULL);
INSERT INTO `config` VALUES ('fastdfs.tracker_http_port', '8888', NULL);
INSERT INTO `config` VALUES ('fastdfs.tracker_server', '192.168.52.128', '');
INSERT INTO `config` VALUES ('fastdfs.tracker_server_port', '22122', NULL);
INSERT INTO `config` VALUES ('front-web.dubbo-registry-address', '192.168.52.130:2181', 'dubbo注册中心的地址,多个地址用逗号隔开');
INSERT INTO `config` VALUES ('front-web.dubbo-registry-protocol', 'zookeeper', '前台dubbo使用注册中心协议');
INSERT INTO `config` VALUES ('front-web.qos.port', '33531', NULL);
INSERT INTO `config` VALUES ('manager-web.dubbo-registry-address', '192.168.52.130:2181', 'dubbo注册中心的地址,多个地址用逗号隔开');
INSERT INTO `config` VALUES ('manager-web.dubbo-registry-protocol', 'zookeeper', 'dubbo注册中心的协议');
INSERT INTO `config` VALUES ('manager-web.qos.port', '34333', NULL);
INSERT INTO `config` VALUES ('redisCluster.nodes', '127.0.0.1:7000,127.0.0.1:7001,127.0.0.1:7002,127.0.0.1:7003,127.0.0.1:7004,127.0.0.1:7005', 'redis集群地址包括端口，多个地址用逗号隔开');
INSERT INTO `config` VALUES ('redisCluster.password', '123', 'redis集群密码');
INSERT INTO `config` VALUES ('redisStandalone.host', '192.168.52.130', 'redis地址不包括端口');
INSERT INTO `config` VALUES ('redisStandalone.password', '123', NULL);
INSERT INTO `config` VALUES ('redisStandalone.port', '6379', NULL);
INSERT INTO `config` VALUES ('search.dubbo-registry-address', '192.168.52.130:2181', 'dubbo注册中心的地址,多个地址用逗号隔开');
INSERT INTO `config` VALUES ('search.dubbo-registry-protocol', 'zookeeper', 'dubbo注册中心的协议');
INSERT INTO `config` VALUES ('search.dubbo.protocol.name', 'dubbo', 'dubbo使用的协议');
INSERT INTO `config` VALUES ('search.dubbo.protocol.port', '20881', 'dubbo协议对外开放端口');
INSERT INTO `config` VALUES ('search.qos.port', '33333', NULL);
INSERT INTO `config` VALUES ('shiro.expireTime', '1800', 'session 过期时间单位为秒');
INSERT INTO `config` VALUES ('ucenter-web.dubbo-registry-address', '192.168.52.130:2181', 'dubbo注册中心的地址,多个地址用逗号隔开');
INSERT INTO `config` VALUES ('ucenter-web.dubbo-registry-protocol', 'zookeeper', 'dubbo注册中心的协议');
INSERT INTO `config` VALUES ('ucenter-web.qos.port', '33332', NULL);
INSERT INTO `config` VALUES ('user.dubbo-protocol.name', 'dubbo', 'dubbo使用的协议');
INSERT INTO `config` VALUES ('user.dubbo-protocol.port', '20880', 'dubbo协议对外开放端口');
INSERT INTO `config` VALUES ('user.dubbo-registry-address', '192.168.52.130:2181', 'dubbo注册中心的地址,多个地址用逗号隔开');
INSERT INTO `config` VALUES ('user.dubbo-registry-protocol', 'zookeeper', 'dubbo注册中心的协议');
INSERT INTO `config` VALUES ('user.qos.port', '33330', NULL);
INSERT INTO `config` VALUES ('xxl.job.accessToken', NULL, '访问令牌[选填]，非空则进行匹配校验');
INSERT INTO `config` VALUES ('xxl.job.admin.addresses', 'http://192.168.52.132:8280/xxl-job-admin', '执行器注册中心地址[选填]，为空则关闭自动注册');
INSERT INTO `config` VALUES ('xxl.job.executor.appName', 'zmw-user-job', '执行器AppName[选填]，为空则关闭自动注册');
INSERT INTO `config` VALUES ('xxl.job.executor.logPath', NULL, '执行器日志路径[选填]，为空则使用默认路径');
INSERT INTO `config` VALUES ('xxl.job.executor.logRetentionDays', '-1', '日志保存天数[选填]，值大于3时生效');
INSERT INTO `config` VALUES ('xxl.job.executor.port', '8888', '执行器端口号[选填]，小于等于0则自动获取');
INSERT INTO `config` VALUES ('xxl.job.ip', NULL, '执行器IP[选填]，为空则自动获取');

-- ----------------------------
-- Table structure for discuss
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss`  (
  `discuss_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `article_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `praise_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `discuss_time` bigint(30) NULL DEFAULT NULL,
  PRIMARY KEY (`discuss_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `host` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for letter
-- ----------------------------
DROP TABLE IF EXISTS `letter`;
CREATE TABLE `letter`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `to_mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_time` bigint(30) NULL DEFAULT NULL,
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_read` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for login_history
-- ----------------------------
DROP TABLE IF EXISTS `login_history`;
CREATE TABLE `login_history`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `login_equipment` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `browser` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_time` bigint(30) NULL DEFAULT NULL,
  `login_address` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_history
-- ----------------------------
INSERT INTO `login_history` VALUES ('1149793ab8ce41ae89b9f72d3dd2f630', 'mw11011', 'Windows', '192.168.52.1', '15655556666', 'Chrome-74.0.3729.169', 1559573090643, NULL);
INSERT INTO `login_history` VALUES ('530445bd823744d597bc07b8df64c052', 'mw11011', 'Windows', '192.168.52.1', '15655556666', 'Chrome-74.0.3729.169', 1559616755485, NULL);
INSERT INTO `login_history` VALUES ('5ea10a8b74ab4c988c79805fa54433cc', 'mw11011', 'Windows', '192.168.52.1', '15655556666', 'Chrome-74.0.3729.169', 1559573429030, NULL);
INSERT INTO `login_history` VALUES ('aa614ba6cb3e4c67b900b8f1a2ec25f3', 'mw11011', 'Windows', '192.168.52.1', '15655556666', 'Chrome-74.0.3729.169', 1559572840514, NULL);
INSERT INTO `login_history` VALUES ('d44e1f9b84514d52b6f498e2e95951bc', 'mw11011', 'Windows', '192.168.52.1', '15655556666', 'Chrome-74.0.3729.169', 1559617960439, NULL);
INSERT INTO `login_history` VALUES ('eb207fe6c08742a5ab52fc43d76d9d5c', 'mw11011', 'Windows', '192.168.52.1', '15655556666', 'Chrome-74.0.3729.169', 1559618153146, NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `describe` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'deleteArticle', '删除文章');
INSERT INTO `permission` VALUES (2, 'deleteDiscuss', '删除评论');
INSERT INTO `permission` VALUES (3, 'deleteReplyDiscuss', '删除回复评论');
INSERT INTO `permission` VALUES (4, 'deleteOpLog', '删除日志');
INSERT INTO `permission` VALUES (5, 'removeAllPermission', '移除某个用户所有权限');
INSERT INTO `permission` VALUES (6, 'removePermission', '移除用户某些权限');
INSERT INTO `permission` VALUES (7, 'addPermission', '为某个用户添加某些权限');
INSERT INTO `permission` VALUES (8, 'changeUserStatus', '更改用户状态');

-- ----------------------------
-- Table structure for r_discuss
-- ----------------------------
DROP TABLE IF EXISTS `r_discuss`;
CREATE TABLE `r_discuss`  (
  `r_discuss_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `r_mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复评论者的id',
  `r_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `discuss_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复评论者的id',
  `to_mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复目标人id',
  `r_praise_number` int(20) NULL DEFAULT NULL COMMENT '点赞数量',
  `r_discuss_time` bigint(30) NULL DEFAULT NULL,
  `to_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标人说的内容',
  PRIMARY KEY (`r_discuss_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(10) NOT NULL,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `describe` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '管理员');
INSERT INTO `role` VALUES (2, 'ordinary ', '普通用户');
INSERT INTO `role` VALUES (3, 'superAdmin', '超级管理员');
INSERT INTO `role` VALUES (4, 'vip', 'VIP用户');
INSERT INTO `role` VALUES (5, 'visitor', '游客');

-- ----------------------------
-- Table structure for statistics
-- ----------------------------
DROP TABLE IF EXISTS `statistics`;
CREATE TABLE `statistics`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_count` int(20) NULL DEFAULT NULL COMMENT '量',
  `s_date` date NULL DEFAULT NULL,
  `s_type` int(10) NULL DEFAULT NULL COMMENT '类型(1用户活跃率，2,新增用户，3总用户量)',
  `s_week` int(5) NULL DEFAULT NULL COMMENT '创建应用后的第几周',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_base_info
-- ----------------------------
DROP TABLE IF EXISTS `user_base_info`;
CREATE TABLE `user_base_info`  (
  `mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wechat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduction` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自我介绍',
  `header` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像图片路径',
  `alias` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cover` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面图片',
  PRIMARY KEY (`mw_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_base_info
-- ----------------------------
INSERT INTO `user_base_info` VALUES ('mw11011', '未知', NULL, NULL, '主人很懒什么都没有留下', 'http://192.168.52.128:8888/group1/M00/00/03/wKg0gFz1MX2ACSsjAApmFX3fkvA172.jpg', '用户mw11011', 'http://192.168.52.128:8888/group1/M00/00/03/wKg0gFz1MXuALtkAAALbjaulNdk019.jpg');

-- ----------------------------
-- Table structure for user_contact
-- ----------------------------
DROP TABLE IF EXISTS `user_contact`;
CREATE TABLE `user_contact`  (
  `mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `noticer_mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`mw_id`, `noticer_mw_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission`  (
  `permission_id` int(10) NOT NULL,
  `mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`permission_id`, `mw_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_permission
-- ----------------------------
INSERT INTO `user_permission` VALUES (1, 'mw11011');
INSERT INTO `user_permission` VALUES (2, 'mw11011');
INSERT INTO `user_permission` VALUES (3, 'mw11011');
INSERT INTO `user_permission` VALUES (4, 'mw11011');
INSERT INTO `user_permission` VALUES (5, 'mw11011');
INSERT INTO `user_permission` VALUES (6, 'mw11011');
INSERT INTO `user_permission` VALUES (7, 'mw11011');
INSERT INTO `user_permission` VALUES (8, 'mw11011');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `mw_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` int(10) NOT NULL,
  PRIMARY KEY (`mw_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('mw11011', 1);
INSERT INTO `user_role` VALUES ('mw11011', 2);
INSERT INTO `user_role` VALUES ('mw11011', 3);
INSERT INTO `user_role` VALUES ('mw11011', 4);
INSERT INTO `user_role` VALUES ('mw11011', 5);

SET FOREIGN_KEY_CHECKS = 1;
