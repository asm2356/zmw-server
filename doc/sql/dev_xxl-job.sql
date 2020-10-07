/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 127.0.0.1:3306
 Source Schema         : xxl-job

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 04/06/2019 17:25:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xxl_job_qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_blob_triggers`;
CREATE TABLE `xxl_job_qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_calendars`;
CREATE TABLE `xxl_job_qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_cron_triggers`;
CREATE TABLE `xxl_job_qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_cron_triggers
-- ----------------------------
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('getSchedulerFactoryBean', '10', '1', '0 1 0 ? * * *', 'Asia/Shanghai');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('getSchedulerFactoryBean', '11', '1', '/30 * * * * ? ', 'Asia/Shanghai');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('getSchedulerFactoryBean', '12', '1', '0 0 * * * ? *', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for xxl_job_qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_fired_triggers`;
CREATE TABLE `xxl_job_qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_fired_triggers
-- ----------------------------
INSERT INTO `xxl_job_qrtz_fired_triggers` VALUES ('getSchedulerFactoryBean', 'azhao15599238747221559923874811', '11', '1', 'azhao1559923874722', 1781367150054, 1781367180000, 5, 'ACQUIRED', NULL, NULL, '0', '0');

-- ----------------------------
-- Table structure for xxl_job_qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_job_details`;
CREATE TABLE `xxl_job_qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_job_details
-- ----------------------------
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('getSchedulerFactoryBean', '10', '1', NULL, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('getSchedulerFactoryBean', '11', '1', NULL, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('getSchedulerFactoryBean', '12', '1', NULL, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for xxl_job_qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_locks`;
CREATE TABLE `xxl_job_qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_locks
-- ----------------------------
INSERT INTO `xxl_job_qrtz_locks` VALUES ('getSchedulerFactoryBean', 'STATE_ACCESS');
INSERT INTO `xxl_job_qrtz_locks` VALUES ('getSchedulerFactoryBean', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for xxl_job_qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_paused_trigger_grps`;
CREATE TABLE `xxl_job_qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_scheduler_state`;
CREATE TABLE `xxl_job_qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_scheduler_state
-- ----------------------------
INSERT INTO `xxl_job_qrtz_scheduler_state` VALUES ('getSchedulerFactoryBean', 'azhao1559923874722', 1781367156410, 5000);

-- ----------------------------
-- Table structure for xxl_job_qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_simple_triggers`;
CREATE TABLE `xxl_job_qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_simprop_triggers`;
CREATE TABLE `xxl_job_qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_group`;
CREATE TABLE `xxl_job_qrtz_trigger_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '执行器名称',
  `order` tinyint(4) NOT NULL DEFAULT 0 COMMENT '排序',
  `address_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_group
-- ----------------------------
INSERT INTO `xxl_job_qrtz_trigger_group` VALUES (1, 'zmw-user-job', 'zmw-user-job', 1, 0, NULL);

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_info`;
CREATE TABLE `xxl_job_qrtz_trigger_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_cron` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务执行CRON',
  `job_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `author` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报警邮件',
  `executor_route_strategy` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `glue_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_info
-- ----------------------------
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (10, 1, '0 1 0 ? * * *', '统计任务，每天0点执行', '2018-12-23 15:51:51', '2019-06-11 00:02:56', 'zgzhao2', '', 'RANDOM', 'statisticsJob', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2018-12-23 15:51:51', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (11, 1, '/30 * * * * ? ', '文章阅读量，点赞，缓存刷新数据库,30秒执行一次', '2019-01-08 15:45:51', '2019-05-31 01:21:19', 'zgzhao', '', 'ROUND', 'articleJob', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2019-01-08 15:45:51', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES (12, 1, '0 0 * * * ? *', '每小时进行统计一次', '2019-03-21 18:14:27', '2019-06-03 08:33:12', 'zgzhao', '', 'ROUND', 'requestJob', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2019-03-21 18:14:27', '');

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_log`;
CREATE TABLE `xxl_job_qrtz_trigger_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT 0 COMMENT '失败重试次数',
  `trigger_time` datetime(0) NULL DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '调度-日志',
  `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '执行-日志',
  `alarm_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `I_trigger_time`(`trigger_time`) USING BTREE,
  INDEX `I_handle_code`(`handle_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_log
-- ----------------------------
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (82, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2019-06-13 00:03:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2019-06-13 00:03:00', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (83, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2019-06-13 00:03:30', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2019-06-13 00:03:31', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (84, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2019-06-13 00:04:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2019-06-13 00:04:00', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (85, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2019-06-13 00:59:26', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：500<br>msg：com.xxl.rpc.util.XxlRpcException: org.eclipse.jetty.io.EofException\r\n	at org.eclipse.jetty.io.ChannelEndPoint.flush(ChannelEndPoint.java:192)\r\n	at org.eclipse.jetty.io.WriteFlusher.flush(WriteFlusher.java:408)\r\n	at org.eclipse.jetty.io.WriteFlusher.write(WriteFlusher.java:302)\r\n	at org.eclipse.jetty.io.AbstractEndPoint.write(AbstractEndPoint.java:129)\r\n	at org.eclipse.jetty.client.http.HttpSenderOverHTTP.sendHeaders(HttpSenderOverHTTP.java:108)\r\n	at org.eclipse.jetty.client.HttpSender.send(HttpSender.java:204)\r\n	at org.eclipse.jetty.client.http.HttpChannelOverHTTP.send(HttpChannelOverHTTP.java:79)\r\n	at org.eclipse.jetty.client.http.HttpConnectionOverHTTP.send(HttpConnectionOverHTTP.java:124)\r\n	at org.eclipse.jetty.client.http.HttpConnectionOverHTTP.access$300(HttpConnectionOverHTTP.java:40)\r\n	at org.eclipse.jetty.client.http.HttpConnectionOverHTTP$Delegate.send(HttpConnectionOverHTTP.java:269)\r\n	at org.eclipse.jetty.client.http.HttpConnectionOverHTTP.send(HttpConnectionOverHTTP.java:94)\r\n	at org.eclipse.jetty.client.http.HttpDestinationOverHTTP.send(HttpDestinationOverHTTP.java:36)\r\n	at org.eclipse.jetty.client.http.HttpDestinationOverHTTP.send(HttpDestinationOverHTTP.java:26)\r\n	at org.eclipse.jetty.client.PoolingHttpDestination.process(PoolingHttpDestination.java:153)\r\n	at org.eclipse.jetty.client.PoolingHttpDestination.send(PoolingHttpDestination.java:85)\r\n	at org.eclipse.jetty.client.PoolingHttpDestination.send(PoolingHttpDestination.java:76)\r\n	at org.eclipse.jetty.client.HttpDestination.send(HttpDestination.java:191)\r\n	at org.eclipse.jetty.client.HttpDestination.send(HttpDestination.java:172)\r\n	at org.eclipse.jetty.client.HttpClient.send(HttpClient.java:527)\r\n	at org.eclipse.jetty.client.HttpRequest.send(HttpRequest.java:694)\r\n	at org.eclipse.jetty.client.HttpRequest.send(HttpRequest.java:678)\r\n	at com.xxl.rpc.remoting.net.impl.jetty.client.JettyClient.postRequestAsync(JettyClient.java:62)\r\n	at com.xxl.rpc.remoting.net.impl.jetty.client.JettyClient.asyncSend(JettyClient.java:31)\r\n	at com.xxl.rpc.remoting.invoker.reference.XxlRpcReferenceBean$1.invoke(XxlRpcReferenceBean.java:156)\r\n	at com.sun.proxy.$Proxy81.run(Unknown Source)\r\n	at com.xxl.job.admin.core.trigger.XxlJobTrigger.runExecutor(XxlJobTrigger.java:188)\r\n	at com.xxl.job.admin.core.trigger.XxlJobTrigger.processTrigger(XxlJobTrigger.java:141)\r\n	at com.xxl.job.admin.core.trigger.XxlJobTrigger.trigger(XxlJobTrigger.java:75)\r\n	at com.xxl.job.admin.core.thread.JobTriggerPoolHelper$1.run(JobTriggerPoolHelper.java:35)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n	at java.lang.Thread.run(Thread.java:748)\r\nCaused by: java.nio.channels.ClosedChannelException\r\n	at sun.nio.ch.SocketChannelImpl.ensureWriteOpen(SocketChannelImpl.java:270)\r\n	at sun.nio.ch.SocketChannelImpl.write(SocketChannelImpl.java:494)\r\n	at org.eclipse.jetty.io.ChannelEndPoint.flush(ChannelEndPoint.java:172)\r\n	... 31 more\r\n\r\n	at com.xxl.rpc.remoting.invoker.reference.XxlRpcReferenceBean$1.invoke(XxlRpcReferenceBean.java:161)\r\n	at com.sun.proxy.$Proxy81.run(Unknown Source)\r\n	at com.xxl.job.admin.core.trigger.XxlJobTrigger.runExecutor(XxlJobTrigger.java:188)\r\n	at com.xxl.job.admin.core.trigger.XxlJobTrigger.processTrigger(XxlJobTrigger.java:141)\r\n	at com.xxl.job.admin.core.trigger.XxlJobTrigger.trigger(XxlJobTrigger.java:75)\r\n	at com.xxl.job.admin.core.thread.JobTriggerPoolHelper$1.run(JobTriggerPoolHelper.java:35)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n	at java.lang.Thread.run(Thread.java:748)\r\n', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (86, 1, 12, '192.168.52.1:8888', 'requestJob', '', NULL, 0, '2019-06-13 01:00:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2019-06-13 01:00:22', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (87, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2019-06-13 01:00:30', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2019-06-13 01:00:30', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (88, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2026-06-13 23:59:53', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2026-06-13 23:59:53', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (89, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:00:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (90, 1, 12, NULL, 'requestJob', '', NULL, 0, '2026-06-14 00:00:02', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (91, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2026-06-14 00:00:30', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2026-06-14 00:00:30', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (92, 1, 10, '192.168.52.1:8888', 'statisticsJob', '', NULL, 0, '2026-06-14 00:01:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：随机<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2026-06-14 00:01:12', 500, 'java.lang.NumberFormatException: For input string: \"null\"\r\n	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)\r\n	at java.lang.Integer.parseInt(Integer.java:580)\r\n	at java.lang.Integer.parseInt(Integer.java:615)\r\n	at com.iflytek.manager.service.job.StatisticsJob.execute(StatisticsJob.java:52)\r\n	at com.xxl.job.core.thread.JobThread.run(JobThread.java:152)\r\n', 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (93, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2026-06-14 00:01:01', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2026-06-14 00:01:12', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (94, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2026-06-14 00:01:30', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2026-06-14 00:01:30', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (95, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2026-06-14 00:02:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2026-06-14 00:02:00', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (96, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2026-06-14 00:02:30', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2026-06-14 00:02:30', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (97, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2026-06-14 00:03:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2026-06-14 00:03:00', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (98, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2026-06-14 00:03:30', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2026-06-14 00:03:30', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (99, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2026-06-14 00:04:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2026-06-14 00:04:00', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (100, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2026-06-14 00:04:30', 200, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：200<br>msg：null', '2026-06-14 00:04:31', 200, '', 0);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (101, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2026-06-14 00:05:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：500<br>msg：com.xxl.rpc.util.XxlRpcException: java.net.ConnectException: Connection refused: no further information\r\n	at sun.nio.ch.SocketChannelImpl.checkConnect(Native Method)\r\n	at sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:717)\r\n	at org.eclipse.jetty.io.SelectorManager.finishConnect(SelectorManager.java:341)\r\n	at org.eclipse.jetty.io.SelectorManager$ManagedSelector.processConnect(SelectorManager.java:676)\r\n	at org.eclipse.jetty.io.SelectorManager$ManagedSelector.processKey(SelectorManager.java:645)\r\n	at org.eclipse.jetty.io.SelectorManager$ManagedSelector.select(SelectorManager.java:612)\r\n	at org.eclipse.jetty.io.SelectorManager$ManagedSelector.run(SelectorManager.java:550)\r\n	at org.eclipse.jetty.util.thread.NonBlockingThread.run(NonBlockingThread.java:52)\r\n	at org.eclipse.jetty.util.thread.QueuedThreadPool.runJob(QueuedThreadPool.java:635)\r\n	at org.eclipse.jetty.util.thread.QueuedThreadPool$3.run(QueuedThreadPool.java:555)\r\n	at java.lang.Thread.run(Thread.java:748)\r\n\r\n	at com.xxl.rpc.remoting.invoker.reference.XxlRpcReferenceBean$1.invoke(XxlRpcReferenceBean.java:161)\r\n	at com.sun.proxy.$Proxy81.run(Unknown Source)\r\n	at com.xxl.job.admin.core.trigger.XxlJobTrigger.runExecutor(XxlJobTrigger.java:188)\r\n	at com.xxl.job.admin.core.trigger.XxlJobTrigger.processTrigger(XxlJobTrigger.java:141)\r\n	at com.xxl.job.admin.core.trigger.XxlJobTrigger.trigger(XxlJobTrigger.java:75)\r\n	at com.xxl.job.admin.core.thread.JobTriggerPoolHelper$1.run(JobTriggerPoolHelper.java:35)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n	at java.lang.Thread.run(Thread.java:748)\r\n', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (102, 1, 11, '192.168.52.1:8888', 'articleJob', '', NULL, 0, '2026-06-14 00:05:30', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：[192.168.52.1:8888]<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.52.1:8888<br>code：500<br>msg：com.xxl.rpc.util.XxlRpcException: java.net.ConnectException: Connection refused: no further information\r\n	at sun.nio.ch.SocketChannelImpl.checkConnect(Native Method)\r\n	at sun.nio.ch.SocketChannelImpl.finishConnect(SocketChannelImpl.java:717)\r\n	at org.eclipse.jetty.io.SelectorManager.finishConnect(SelectorManager.java:341)\r\n	at org.eclipse.jetty.io.SelectorManager$ManagedSelector.processConnect(SelectorManager.java:676)\r\n	at org.eclipse.jetty.io.SelectorManager$ManagedSelector.processKey(SelectorManager.java:645)\r\n	at org.eclipse.jetty.io.SelectorManager$ManagedSelector.select(SelectorManager.java:612)\r\n	at org.eclipse.jetty.io.SelectorManager$ManagedSelector.run(SelectorManager.java:550)\r\n	at org.eclipse.jetty.util.thread.NonBlockingThread.run(NonBlockingThread.java:52)\r\n	at org.eclipse.jetty.util.thread.QueuedThreadPool.runJob(QueuedThreadPool.java:635)\r\n	at org.eclipse.jetty.util.thread.QueuedThreadPool$3.run(QueuedThreadPool.java:555)\r\n	at java.lang.Thread.run(Thread.java:748)\r\n\r\n	at com.xxl.rpc.remoting.invoker.reference.XxlRpcReferenceBean$1.invoke(XxlRpcReferenceBean.java:161)\r\n	at com.sun.proxy.$Proxy81.run(Unknown Source)\r\n	at com.xxl.job.admin.core.trigger.XxlJobTrigger.runExecutor(XxlJobTrigger.java:188)\r\n	at com.xxl.job.admin.core.trigger.XxlJobTrigger.processTrigger(XxlJobTrigger.java:141)\r\n	at com.xxl.job.admin.core.trigger.XxlJobTrigger.trigger(XxlJobTrigger.java:75)\r\n	at com.xxl.job.admin.core.thread.JobTriggerPoolHelper$1.run(JobTriggerPoolHelper.java:35)\r\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n	at java.lang.Thread.run(Thread.java:748)\r\n', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (103, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:06:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (104, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:06:30', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (105, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:07:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (106, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:07:30', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (107, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:08:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (108, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:08:30', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (109, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:09:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (110, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:09:30', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (111, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:10:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (112, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:10:30', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (113, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:11:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (114, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:11:30', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (115, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:12:00', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES (116, 1, 11, NULL, 'articleJob', '', NULL, 0, '2026-06-14 00:12:30', 500, '任务触发类型：Cron触发<br>调度机器：192.168.52.1<br>执行器-注册方式：自动注册<br>执行器-地址列表：null<br>路由策略：轮询<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>调度失败：执行器地址为空<br><br>', NULL, 0, NULL, 1);

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_logglue`;
CREATE TABLE `xxl_job_qrtz_trigger_logglue`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` timestamp(0) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_registry`;
CREATE TABLE `xxl_job_qrtz_trigger_registry`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `registry_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `registry_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_triggers`;
CREATE TABLE `xxl_job_qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  CONSTRAINT `xxl_job_qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `xxl_job_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_qrtz_triggers
-- ----------------------------
INSERT INTO `xxl_job_qrtz_triggers` VALUES ('getSchedulerFactoryBean', '10', '1', '10', '1', NULL, 1781452860000, 1781366460000, 5, 'WAITING', 'CRON', 1560009882000, 0, NULL, 2, '');
INSERT INTO `xxl_job_qrtz_triggers` VALUES ('getSchedulerFactoryBean', '11', '1', '11', '1', NULL, 1781367180000, 1781367150000, 5, 'ACQUIRED', 'CRON', 1560009878000, 0, NULL, 2, '');
INSERT INTO `xxl_job_qrtz_triggers` VALUES ('getSchedulerFactoryBean', '12', '1', '12', '1', NULL, 1781370000000, 1781366400000, 5, 'WAITING', 'CRON', 1560009874000, 0, NULL, 2, '');

SET FOREIGN_KEY_CHECKS = 1;
