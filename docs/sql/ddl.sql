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
-- Records of activity
-- ----------------------------
BEGIN;
INSERT INTO `activity`
VALUES (1, 100001, '活动名', '测试活动', now(), now(), 100, 94, 10, 10001, 5, 'wcx',
        '2021-08-08 20:14:50', '2021-08-08 20:14:50');
INSERT INTO `activity`
VALUES (3, 100002, '活动名02', '测试活动', now(), now(), 100, 100, 10, 10001, 5, 'wcx',
        now(), now());
COMMIT;

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
-- Records of award
-- ----------------------------
BEGIN;
INSERT INTO `award`
VALUES (1, '1', 1, 'IMac', 'Code', now(), now());
INSERT INTO `award`
VALUES (2, '2', 1, 'iphone', 'Code', now(), now());
INSERT INTO `award`
VALUES (3, '3', 1, 'ipad', 'Code', now(), now());
INSERT INTO `award`
VALUES (4, '4', 1, 'AirPods', 'Code', now(), now());
INSERT INTO `award`
VALUES (5, '5', 1, 'Book', 'Code', now(), now());
COMMIT;

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
-- Records of rule_tree
-- ----------------------------
BEGIN;
INSERT INTO `rule_tree`
VALUES (2110081902, '抽奖活动规则树', '用于决策不同用户可参与的活动', 1, now(), now());
COMMIT;

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
-- Records of rule_tree_node
-- ----------------------------
BEGIN;
INSERT INTO `rule_tree_node`
VALUES (1, 2110081902, 1, NULL, 'userGender', '用户性别[男/女]');
INSERT INTO `rule_tree_node`
VALUES (11, 2110081902, 1, NULL, 'userAge', '用户年龄');
INSERT INTO `rule_tree_node`
VALUES (12, 2110081902, 1, NULL, 'userAge', '用户年龄');
INSERT INTO `rule_tree_node`
VALUES (111, 2110081902, 2, '100001', NULL, NULL);
INSERT INTO `rule_tree_node`
VALUES (112, 2110081902, 2, '100002', NULL, NULL);
INSERT INTO `rule_tree_node`
VALUES (121, 2110081902, 2, '100003', NULL, NULL);
INSERT INTO `rule_tree_node`
VALUES (122, 2110081902, 2, '100004', NULL, NULL);
COMMIT;

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
-- Records of rule_tree_node_line
-- ----------------------------
BEGIN;
INSERT INTO `rule_tree_node_line`
VALUES (1, 2110081902, 1, 11, 1, 'man');
INSERT INTO `rule_tree_node_line`
VALUES (2, 2110081902, 1, 12, 1, 'woman');
INSERT INTO `rule_tree_node_line`
VALUES (3, 2110081902, 11, 111, 3, '25');
INSERT INTO `rule_tree_node_line`
VALUES (4, 2110081902, 11, 112, 4, '25');
INSERT INTO `rule_tree_node_line`
VALUES (5, 2110081902, 12, 121, 3, '25');
INSERT INTO `rule_tree_node_line`
VALUES (6, 2110081902, 12, 122, 4, '25');
COMMIT;

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
-- Records of strategy
-- ----------------------------
BEGIN;
INSERT INTO `strategy`
VALUES (1, 10001, 'test', 2, 1, NULL, '', now(), now());
COMMIT;

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

-- ----------------------------
-- Records of strategy_detail
-- ----------------------------
BEGIN;
INSERT INTO `strategy_detail`
VALUES (1, 10001, '1', 'IMac', 10, 0, 0.05, now(), now());
INSERT INTO `strategy_detail`
VALUES (2, 10001, '2', 'iphone', 20, 19, 0.15, now(), now());
INSERT INTO `strategy_detail`
VALUES (3, 10001, '3', 'ipad', 50, 43, 0.20, now(), now());
INSERT INTO `strategy_detail`
VALUES (4, 10001, '4', 'AirPods', 100, 70, 0.25, now(), now());
INSERT INTO `strategy_detail`
VALUES (5, 10001, '5', 'Book', 500, 389, 0.35, now(), now());
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;


-- 另外两个库 --
create schema if not exists simple_lottery_01;
create schema if not exists simple_lottery_02;

use
    simple_lottery_01;

-- 用户参与活动记录表
create table if not exists user_take_activity
(
    id            bigint(11) auto_increment comment '自增ID' primary key,
    u_id          tinytext  null,
    take_id       bigint    null,
    activity_id   bigint    null,
    activity_name tinytext  null,
    take_date     timestamp null,
    take_count    int       null,
    uuid          tinytext  null,
    create_time   timestamp null,
    update_time   timestamp null
) comment '用户参与活动记录表';

-- 用户活动参与次数表
create table if not exists user_take_activity_count
(
    id          bigint(11) auto_increment comment '自增ID' primary key,
    u_id        tinytext  null,
    activity_id bigint    null,
    total_count int       null,
    left_count  int       null,
    create_time timestamp null,
    update_time timestamp null
) comment '用户活动参与次数表';

create table if not exists user_strategy_export_001
(
    id            bigint(11) auto_increment comment '自增ID' primary key,
    u_id          mediumtext null,
    activity_id   bigint     null,
    order_id      bigint     null,
    strategy_id   bigint     null,
    strategy_type int        null,
    grant_type    int        null,
    grant_date    timestamp  null,
    grant_state   int        null,
    award_id      bigint     null,
    award_type    int        null,
    award_name    mediumtext null,
    award_content mediumtext null,
    uuid          mediumtext null,
    create_time   timestamp  null,
    update_time   timestamp  null
) comment '用户策略计算结果表';

create table if not exists user_strategy_export_002
(
    id            bigint(11) auto_increment comment '自增ID' primary key,
    u_id          mediumtext null,
    activity_id   bigint     null,
    order_id      bigint     null,
    strategy_id   bigint     null,
    strategy_type int        null,
    grant_type    int        null,
    grant_date    timestamp  null,
    grant_state   int        null,
    award_id      bigint     null,
    award_type    int        null,
    award_name    mediumtext null,
    award_content mediumtext null,
    uuid          mediumtext null,
    create_time   timestamp  null,
    update_time   timestamp  null
) comment '用户策略计算结果表';

create table if not exists user_strategy_export_003
(
    id            bigint(11) auto_increment comment '自增ID' primary key,
    u_id          mediumtext null,
    activity_id   bigint     null,
    order_id      bigint     null,
    strategy_id   bigint     null,
    strategy_type int        null,
    grant_type    int        null,
    grant_date    timestamp  null,
    grant_state   int        null,
    award_id      bigint     null,
    award_type    int        null,
    award_name    mediumtext null,
    award_content mediumtext null,
    uuid          mediumtext null,
    create_time   timestamp  null,
    update_time   timestamp  null
) comment '用户策略计算结果表';

create table if not exists user_strategy_export_004
(
    id            bigint(11) auto_increment comment '自增ID' primary key,
    u_id          mediumtext null,
    activity_id   bigint     null,
    order_id      bigint     null,
    strategy_id   bigint     null,
    strategy_type int        null,
    grant_type    int        null,
    grant_date    timestamp  null,
    grant_state   int        null,
    award_id      bigint     null,
    award_type    int        null,
    award_name    mediumtext null,
    award_content mediumtext null,
    uuid          mediumtext null,
    create_time   timestamp  null,
    update_time   timestamp  null
) comment '用户策略计算结果表';

-- 第二个库
use
    simple_lottery_02;

-- 用户参与活动记录表
create table if not exists user_take_activity
(
    id            bigint(11) auto_increment comment '自增ID' primary key,
    u_id          tinytext  null,
    take_id       bigint    null,
    activity_id   bigint    null,
    activity_name tinytext  null,
    take_date     timestamp null,
    take_count    int       null,
    uuid          tinytext  null,
    create_time   timestamp null,
    update_time   timestamp null
) comment '用户参与活动记录表';

-- 用户活动参与次数表
create table if not exists user_take_activity_count
(
    id          bigint(11) auto_increment comment '自增ID' primary key,
    u_id        tinytext  null,
    activity_id bigint    null,
    total_count int       null,
    left_count  int       null,
    create_time timestamp null,
    update_time timestamp null
) comment '用户活动参与次数表';

create table if not exists user_strategy_export_001
(
    id            bigint(11) auto_increment comment '自增ID' primary key,
    u_id          mediumtext null,
    activity_id   bigint     null,
    order_id      bigint     null,
    strategy_id   bigint     null,
    strategy_type int        null,
    grant_type    int        null,
    grant_date    timestamp  null,
    grant_state   int        null,
    award_id      bigint     null,
    award_type    int        null,
    award_name    mediumtext null,
    award_content mediumtext null,
    uuid          mediumtext null,
    create_time   timestamp  null,
    update_time   timestamp  null
) comment '用户策略计算结果表';

create table if not exists user_strategy_export_002
(
    id            bigint(11) auto_increment comment '自增ID' primary key,
    u_id          mediumtext null,
    activity_id   bigint     null,
    order_id      bigint     null,
    strategy_id   bigint     null,
    strategy_type int        null,
    grant_type    int        null,
    grant_date    timestamp  null,
    grant_state   int        null,
    award_id      bigint     null,
    award_type    int        null,
    award_name    mediumtext null,
    award_content mediumtext null,
    uuid          mediumtext null,
    create_time   timestamp  null,
    update_time   timestamp  null
) comment '用户策略计算结果表';

create table if not exists user_strategy_export_003
(
    id            bigint(11) auto_increment comment '自增ID' primary key,
    u_id          mediumtext null,
    activity_id   bigint     null,
    order_id      bigint     null,
    strategy_id   bigint     null,
    strategy_type int        null,
    grant_type    int        null,
    grant_date    timestamp  null,
    grant_state   int        null,
    award_id      bigint     null,
    award_type    int        null,
    award_name    mediumtext null,
    award_content mediumtext null,
    uuid          mediumtext null,
    create_time   timestamp  null,
    update_time   timestamp  null
) comment '用户策略计算结果表';

create table if not exists user_strategy_export_004
(
    id            bigint(11) auto_increment comment '自增ID' primary key,
    u_id          mediumtext null,
    activity_id   bigint     null,
    order_id      bigint     null,
    strategy_id   bigint     null,
    strategy_type int        null,
    grant_type    int        null,
    grant_date    timestamp  null,
    grant_state   int        null,
    award_id      bigint     null,
    award_type    int        null,
    award_name    mediumtext null,
    award_content mediumtext null,
    uuid          mediumtext null,
    create_time   timestamp  null,
    update_time   timestamp  null
) comment '用户策略计算结果表';








