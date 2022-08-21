create schema if not exists simple_lottery;

use simple_lottery;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`
(
    `id`                  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `activity_id`         bigint(20) NOT NULL COMMENT '活动ID',
    `activity_name`       varchar(64) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '活动名称',
    `activity_desc`       varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '活动描述',
    `begin_date_time`     datetime(3)                        DEFAULT NULL COMMENT '开始时间',
    `end_date_time`       datetime(3)                        DEFAULT NULL COMMENT '结束时间',
    `stock_count`         int(11)                            DEFAULT NULL COMMENT '库存',
    `stock_surplus_count` int(11)                            DEFAULT NULL COMMENT '库存剩余',
    `take_count`          int(11)                            DEFAULT NULL COMMENT '每人可参与次数',
    `strategy_id`         bigint(11)                         DEFAULT NULL COMMENT '抽奖策略ID',
    `state`               tinyint(2)                         DEFAULT NULL COMMENT '活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启',
    `creator`             varchar(64) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '创建人',
    `create_time`         timestamp                          DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         timestamp                          DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_activity_id` (`activity_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='活动配置';

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award`
(
    `id`            bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `award_id`      varchar(64) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '奖品ID',
    `award_type`    tinyint(4)                         DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
    `award_name`    varchar(64) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '奖品名称',
    `award_content` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
    `create_time`   timestamp                          DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                          DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_award_id` (`award_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='奖品配置';

-- ----------------------------
-- Table structure for rule_tree
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree`;
CREATE TABLE `rule_tree`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tree_name`         varchar(64)  DEFAULT NULL COMMENT '规则树Id',
    `tree_desc`         varchar(128) DEFAULT NULL COMMENT '规则树描述',
    `tree_root_node_id` bigint(20)   DEFAULT NULL COMMENT '规则树根ID',
    `create_time`       timestamp    DEFAULT NULL COMMENT '创建时间',
    `update_time`       timestamp    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2110081903
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for rule_tree_node
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree_node`;
CREATE TABLE `rule_tree_node`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tree_id`    int(2)      DEFAULT NULL COMMENT '规则树ID',
    `node_type`  int(2)      DEFAULT NULL COMMENT '节点类型；1子叶、2果实',
    `node_value` varchar(32) DEFAULT NULL COMMENT '节点值[nodeType=2]；果实值',
    `rule_key`   varchar(16) DEFAULT NULL COMMENT '规则Key',
    `rule_desc`  varchar(32) DEFAULT NULL COMMENT '规则描述',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 123
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for rule_tree_node_line
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree_node_line`;
CREATE TABLE `rule_tree_node_line`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tree_id`          bigint(20)  DEFAULT NULL COMMENT '规则树ID',
    `node_id_from`     bigint(20)  DEFAULT NULL COMMENT '节点From',
    `node_id_to`       bigint(20)  DEFAULT NULL COMMENT '节点To',
    `rule_limit_type`  int(2)      DEFAULT NULL COMMENT '限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围];7:果实',
    `rule_limit_value` varchar(32) DEFAULT NULL COMMENT '限定值',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for strategy
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy`
(
    `id`            bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `strategy_id`   bigint(11) NOT NULL COMMENT '策略ID',
    `strategy_desc` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '策略描述',
    `strategy_mode` tinyint(2)                         DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
    `grant_type`    tinyint(2)                         DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
    `grant_date`    datetime(3)                        DEFAULT NULL COMMENT '发放奖品时间',
    `ext_info`      varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '扩展信息',
    `create_time`   timestamp                          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `strategy_strategyId_uindex` (`strategy_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='策略配置';

-- ----------------------------
-- Table structure for strategy_detail
-- ----------------------------
DROP TABLE IF EXISTS `strategy_detail`;
CREATE TABLE `strategy_detail`
(
    `id`                  bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `strategy_id`         bigint(11) NOT NULL COMMENT '策略ID',
    `award_id`            varchar(64) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '奖品ID',
    `award_name`          varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '奖品描述',
    `award_count`         int(11)                            DEFAULT NULL COMMENT '奖品库存',
    `award_surplus_count` int(11)                            DEFAULT '0' COMMENT '奖品剩余库存',
    `award_rate`          decimal(5, 2)                      DEFAULT NULL COMMENT '中奖概率',
    `create_time`         timestamp                          DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         timestamp                          DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='策略明细';

SET FOREIGN_KEY_CHECKS = 1;


-- 另外两个库 --
create schema if not exists simple_lottery_01;
create schema if not exists simple_lottery_02;

use simple_lottery_01;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_strategy_export_004
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_004`;
CREATE TABLE `user_strategy_export_004`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `u_id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户ID',
    `activity_id`   bigint(20)                                             DEFAULT NULL COMMENT '活动ID',
    `order_id`      bigint(32)                                             DEFAULT NULL COMMENT '订单ID',
    `strategy_id`   bigint(20)                                             DEFAULT NULL COMMENT '策略ID',
    `strategy_mode` tinyint(2)                                             DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
    `grant_type`    tinyint(2)                                             DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
    `grant_date`    datetime                                               DEFAULT NULL COMMENT '发奖时间',
    `grant_state`   tinyint(4)                                             DEFAULT NULL COMMENT '发奖状态',
    `award_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '发奖ID',
    `award_type`    tinyint(2)                                             DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
    `award_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '奖品名称',
    `award_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
    `uuid`          varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
    `mq_state`      tinyint(4)                                             DEFAULT NULL COMMENT '消息发送状态（0未发送、1发送成功、2发送失败）',
    `create_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户策略计算结果表';

-- ----------------------------
-- Records of user_strategy_export_000
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_strategy_export_001
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_001`;
CREATE TABLE `user_strategy_export_001`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `u_id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户ID',
    `activity_id`   bigint(20)                                             DEFAULT NULL COMMENT '活动ID',
    `order_id`      bigint(32)                                             DEFAULT NULL COMMENT '订单ID',
    `strategy_id`   bigint(20)                                             DEFAULT NULL COMMENT '策略ID',
    `strategy_mode` tinyint(2)                                             DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
    `grant_type`    tinyint(2)                                             DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
    `grant_date`    datetime                                               DEFAULT NULL COMMENT '发奖时间',
    `grant_state`   tinyint(4)                                             DEFAULT NULL COMMENT '发奖状态',
    `award_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '发奖ID',
    `award_type`    tinyint(2)                                             DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
    `award_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '奖品名称',
    `award_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
    `uuid`          varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
    `mq_state`      tinyint(4)                                             DEFAULT NULL COMMENT '消息发送状态（0未发送、1发送成功、2发送失败）',
    `create_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户策略计算结果表';

-- ----------------------------
-- Records of user_strategy_export_001
-- ----------------------------
BEGIN;

COMMIT;

-- ----------------------------
-- Table structure for user_strategy_export_002
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_002`;
CREATE TABLE `user_strategy_export_002`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `u_id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户ID',
    `activity_id`   bigint(20)                                             DEFAULT NULL COMMENT '活动ID',
    `order_id`      bigint(32)                                             DEFAULT NULL COMMENT '订单ID',
    `strategy_id`   bigint(20)                                             DEFAULT NULL COMMENT '策略ID',
    `strategy_mode` tinyint(2)                                             DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
    `grant_type`    tinyint(2)                                             DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
    `grant_date`    datetime                                               DEFAULT NULL COMMENT '发奖时间',
    `grant_state`   tinyint(4)                                             DEFAULT NULL COMMENT '发奖状态',
    `award_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '发奖ID',
    `award_type`    tinyint(2)                                             DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
    `award_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '奖品名称',
    `award_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
    `uuid`          varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
    `mq_state`      tinyint(4)                                             DEFAULT NULL COMMENT '消息发送状态（0未发送、1发送成功、2发送失败）',
    `create_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户策略计算结果表';

-- ----------------------------
-- Records of user_strategy_export_002
-- ----------------------------
BEGIN;

COMMIT;

-- ----------------------------
-- Table structure for user_strategy_export_003
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_003`;
CREATE TABLE `user_strategy_export_003`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `u_id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户ID',
    `activity_id`   bigint(20)                                             DEFAULT NULL COMMENT '活动ID',
    `order_id`      bigint(32)                                             DEFAULT NULL COMMENT '订单ID',
    `strategy_id`   bigint(20)                                             DEFAULT NULL COMMENT '策略ID',
    `strategy_mode` tinyint(2)                                             DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
    `grant_type`    tinyint(2)                                             DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
    `grant_date`    datetime                                               DEFAULT NULL COMMENT '发奖时间',
    `grant_state`   tinyint(4)                                             DEFAULT NULL COMMENT '发奖状态',
    `award_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '发奖ID',
    `award_type`    tinyint(2)                                             DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
    `award_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '奖品名称',
    `award_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
    `uuid`          varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
    `mq_state`      tinyint(4)                                             DEFAULT NULL COMMENT '消息发送状态（0未发送、1发送成功、2发送失败）',
    `create_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户策略计算结果表';

-- ----------------------------
-- Records of user_strategy_export_003
-- ----------------------------
BEGIN;

COMMIT;

-- ----------------------------
-- Table structure for user_take_activity
-- ----------------------------
DROP TABLE IF EXISTS `user_take_activity`;
CREATE TABLE `user_take_activity`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `u_id`          varchar(32) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户ID',
    `take_id`       bigint(20)                       DEFAULT NULL COMMENT '活动领取ID',
    `activity_id`   bigint(20)                       DEFAULT NULL COMMENT '活动ID',
    `activity_name` varchar(64) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '活动名称',
    `take_date`     datetime                         DEFAULT NULL COMMENT '活动领取时间',
    `take_count`    int(11)                          DEFAULT NULL COMMENT '领取次数',
    `strategy_id`   int(11)                          DEFAULT NULL COMMENT '抽奖策略ID',
    `state`         tinyint(2)                       DEFAULT NULL COMMENT '活动单使用状态 0未使用、1已使用',
    `uuid`          varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
    `create_time`   timestamp                         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                         DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE COMMENT '防重ID'
) ENGINE = InnoDB
  AUTO_INCREMENT = 28
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='用户参与活动记录表';

-- ----------------------------
-- Records of user_take_activity
-- ----------------------------
BEGIN;

COMMIT;

-- ----------------------------
-- Table structure for user_take_activity_count
-- ----------------------------
DROP TABLE IF EXISTS `user_take_activity_count`;
CREATE TABLE `user_take_activity_count`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `u_id`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
    `activity_id` bigint(20)                                            DEFAULT NULL COMMENT '活动ID',
    `total_count` int(11)                                               DEFAULT NULL COMMENT '可领取次数',
    `left_count`  int(11)                                               DEFAULT NULL COMMENT '已领取次数',
    `create_time` timestamp                                              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                              DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_uId_activityId` (`u_id`, `activity_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户活动参与次数表';

-- ----------------------------
-- Records of user_take_activity_count
-- ----------------------------
BEGIN;

COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

-- 第二个库
use simple_lottery_02;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_strategy_export_004
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_004`;
CREATE TABLE `user_strategy_export_004`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `u_id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户ID',
    `activity_id`   bigint(20)                                             DEFAULT NULL COMMENT '活动ID',
    `order_id`      bigint(32)                                             DEFAULT NULL COMMENT '订单ID',
    `strategy_id`   bigint(20)                                             DEFAULT NULL COMMENT '策略ID',
    `strategy_mode` tinyint(2)                                             DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
    `grant_type`    tinyint(2)                                             DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
    `grant_date`    datetime                                               DEFAULT NULL COMMENT '发奖时间',
    `grant_state`   tinyint(4)                                             DEFAULT NULL COMMENT '发奖状态',
    `award_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '发奖ID',
    `award_type`    tinyint(2)                                             DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
    `award_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '奖品名称',
    `award_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
    `uuid`          varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
    `mq_state`      tinyint(4)                                             DEFAULT NULL COMMENT '消息发送状态（0未发送、1发送成功、2发送失败）',
    `create_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户策略计算结果表';

-- ----------------------------
-- Records of user_strategy_export_000
-- ----------------------------
BEGIN;

COMMIT;

-- ----------------------------
-- Table structure for user_strategy_export_001
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_001`;
CREATE TABLE `user_strategy_export_001`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `u_id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户ID',
    `activity_id`   bigint(20)                                             DEFAULT NULL COMMENT '活动ID',
    `order_id`      bigint(32)                                             DEFAULT NULL COMMENT '订单ID',
    `strategy_id`   bigint(20)                                             DEFAULT NULL COMMENT '策略ID',
    `strategy_mode` tinyint(2)                                             DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
    `grant_type`    tinyint(2)                                             DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
    `grant_date`    datetime                                               DEFAULT NULL COMMENT '发奖时间',
    `grant_state`   tinyint(4)                                             DEFAULT NULL COMMENT '发奖状态',
    `award_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '发奖ID',
    `award_type`    tinyint(2)                                             DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
    `award_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '奖品名称',
    `award_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
    `uuid`          varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
    `mq_state`      tinyint(4)                                             DEFAULT NULL COMMENT '消息发送状态（0未发送、1发送成功、2发送失败）',
    `create_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户策略计算结果表';

-- ----------------------------
-- Records of user_strategy_export_001
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_strategy_export_002
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_002`;
CREATE TABLE `user_strategy_export_002`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `u_id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户ID',
    `activity_id`   bigint(20)                                             DEFAULT NULL COMMENT '活动ID',
    `order_id`      bigint(32)                                             DEFAULT NULL COMMENT '订单ID',
    `strategy_id`   bigint(20)                                             DEFAULT NULL COMMENT '策略ID',
    `strategy_mode` tinyint(2)                                             DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
    `grant_type`    tinyint(2)                                             DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
    `grant_date`    datetime                                               DEFAULT NULL COMMENT '发奖时间',
    `grant_state`   tinyint(4)                                             DEFAULT NULL COMMENT '发奖状态',
    `award_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '发奖ID',
    `award_type`    tinyint(2)                                             DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
    `award_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '奖品名称',
    `award_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
    `uuid`          varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
    `mq_state`      tinyint(4)                                             DEFAULT NULL COMMENT '消息发送状态（0未发送、1发送成功、2发送失败）',
    `create_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户策略计算结果表';

-- ----------------------------
-- Records of user_strategy_export_002
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_strategy_export_003
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_003`;
CREATE TABLE `user_strategy_export_003`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `u_id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户ID',
    `activity_id`   bigint(20)                                             DEFAULT NULL COMMENT '活动ID',
    `order_id`      bigint(32)                                             DEFAULT NULL COMMENT '订单ID',
    `strategy_id`   bigint(20)                                             DEFAULT NULL COMMENT '策略ID',
    `strategy_mode` tinyint(2)                                             DEFAULT NULL COMMENT '策略方式（1:单项概率、2:总体概率）',
    `grant_type`    tinyint(2)                                             DEFAULT NULL COMMENT '发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）',
    `grant_date`    datetime                                               DEFAULT NULL COMMENT '发奖时间',
    `grant_state`   tinyint(4)                                             DEFAULT NULL COMMENT '发奖状态',
    `award_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '发奖ID',
    `award_type`    tinyint(2)                                             DEFAULT NULL COMMENT '奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）',
    `award_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '奖品名称',
    `award_content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
    `uuid`          varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
    `mq_state`      tinyint(4)                                             DEFAULT NULL COMMENT '消息发送状态（0未发送、1发送成功、2发送失败）',
    `create_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                                               DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户策略计算结果表';

-- ----------------------------
-- Records of user_strategy_export_003
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_take_activity
-- ----------------------------
DROP TABLE IF EXISTS `user_take_activity`;
CREATE TABLE `user_take_activity`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `u_id`          varchar(32) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户ID',
    `take_id`       bigint(20)                       DEFAULT NULL COMMENT '活动领取ID',
    `activity_id`   bigint(20)                       DEFAULT NULL COMMENT '活动ID',
    `activity_name` varchar(64) COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '活动名称',
    `take_date`     datetime                         DEFAULT NULL COMMENT '活动领取时间',
    `take_count`    int(11)                          DEFAULT NULL COMMENT '领取次数',
    `strategy_id`   int(11)                          DEFAULT NULL COMMENT '抽奖策略ID',
    `state`         tinyint(2)                       DEFAULT NULL COMMENT '活动单使用状态 0未使用、1已使用',
    `uuid`          varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '防重ID',
    `create_time`   timestamp                         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                         DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE COMMENT '防重ID'
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='用户参与活动记录表';

-- ----------------------------
-- Records of user_take_activity
-- ----------------------------
BEGIN;

COMMIT;

-- ----------------------------
-- Table structure for user_take_activity_count
-- ----------------------------
DROP TABLE IF EXISTS `user_take_activity_count`;
CREATE TABLE `user_take_activity_count`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `u_id`        varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
    `activity_id` bigint(20)                                            DEFAULT NULL COMMENT '活动ID',
    `total_count` int(11)                                               DEFAULT NULL COMMENT '可领取次数',
    `left_count`  int(11)                                               DEFAULT NULL COMMENT '已领取次数',
    `create_time` timestamp                                              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                              DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_uId_activityId` (`u_id`, `activity_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户活动参与次数表';

-- ----------------------------
-- Records of user_take_activity_count
-- ----------------------------
BEGIN;

COMMIT;

SET FOREIGN_KEY_CHECKS = 1;








