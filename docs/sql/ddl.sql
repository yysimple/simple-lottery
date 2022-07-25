create schema if not exists simple_lottery;

use
simple_lottery;

-- 活动配置
CREATE TABLE if not exists `activity`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `activity_id`     bigint(20) NOT NULL COMMENT '活动ID',
    `activity_name`   varchar(64) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '活动名称',
    `activity_desc`   varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '活动描述',
    `begin_date_time` datetime                           DEFAULT NULL COMMENT '开始时间',
    `end_date_time`   datetime                           DEFAULT NULL COMMENT '结束时间',
    `stock_count`     int(11) DEFAULT NULL COMMENT '库存',
    `take_count`      int(11) DEFAULT NULL COMMENT '每人可参与次数',
    `state`           tinyint(2) DEFAULT NULL COMMENT '活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启',
    `creator`         varchar(64) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '创建人',
    `create_time`     datetime                           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime                           DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_activity_id` (`activity_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='活动配置';

-- 奖品配置
create table if not exists award
(
    id           bigint(11) auto_increment comment '自增ID'
        primary key,
    awardId      bigint null comment '奖品ID',
    awardType    int(4) null comment '奖品类型（文字描述、兑换码、优惠券、实物奖品暂无）',
    awardCount   int null comment '奖品数量',
    awardName    varchar(64) null comment '奖品名称',
    awardContent varchar(128) null comment '奖品内容「文字描述、Key、码」',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null comment '更新时间'
) comment '奖品配置';

-- 策略配置
create table if not exists strategy
(
    id           bigint(11) auto_increment comment '自增ID'
        primary key,
    strategyId   bigint(11) not null comment '策略ID',
    strategyDesc varchar(128) null comment '策略描述',
    strategyMode int(4) null comment '策略方式「1:单项概率、2:总体概率」',
    grantType    int(4) null comment '发放奖品方式「1:即时、2:定时[含活动结束]、3:人工」',
    grantDate    datetime null comment '发放奖品时间',
    extInfo      varchar(128) null comment '扩展信息',
    createTime   datetime null comment '创建时间',
    updateTime   datetime null comment '修改时间',
    constraint strategy_strategyId_uindex
        unique (strategyId)
) comment '策略配置';

-- 策略明细
create table if not exists strategy_detail
(
    id         bigint(11) auto_increment comment '自增ID'
        primary key,
    strategyId bigint(11) not null comment '策略ID',
    awardId    bigint(11) null comment '奖品ID',
    awardCount int null comment '奖品数量',
    awardRate  decimal(5, 2) null comment '中奖概率',
    createTime datetime null comment '创建时间',
    updateTime datetime null comment '修改时间'
) comment '';

-- 另外两个库 --
create schema if not exists simple_lottery_001;
create schema if not exists simple_lottery_002;

use
simple_lottery_001;

-- 用户参与活动记录表
create table if not exists user_take_activity
(
    id           bigint(11) auto_increment comment '自增ID' primary key,
    uId          tinytext null,
    takeId       bigint null,
    activityId   bigint null,
    activityName tinytext null,
    takeDate     timestamp null,
    takeCount    int null,
    uuid         tinytext null,
    createTime   timestamp null,
    updateTime   timestamp null
) comment '用户参与活动记录表';

-- 用户活动参与次数表
create table if not exists user_take_activity_count
(
    id         bigint(11) auto_increment comment '自增ID' primary key,
    uId        tinytext null,
    activityId bigint null,
    totalCount int null,
    leftCount  int null,
    createTime timestamp null,
    updateTime timestamp null
) comment '用户活动参与次数表';

create table if not exists user_strategy_export_001
(
    id           bigint(11) auto_increment comment '自增ID' primary key,
    uId          mediumtext null,
    activityId   bigint null,
    orderId      bigint null,
    strategyId   bigint null,
    strategyType int null,
    grantType    int null,
    grantDate    timestamp null,
    grantState   int null,
    awardId      bigint null,
    awardType    int null,
    awardName    mediumtext null,
    awardContent mediumtext null,
    uuid         mediumtext null,
    createTime   timestamp null,
    updateTime   timestamp null
) comment '用户策略计算结果表';

create table if not exists user_strategy_export_002
(
    id           bigint(11) auto_increment comment '自增ID' primary key,
    uId          mediumtext null,
    activityId   bigint null,
    orderId      bigint null,
    strategyId   bigint null,
    strategyType int null,
    grantType    int null,
    grantDate    timestamp null,
    grantState   int null,
    awardId      bigint null,
    awardType    int null,
    awardName    mediumtext null,
    awardContent mediumtext null,
    uuid         mediumtext null,
    createTime   timestamp null,
    updateTime   timestamp null
) comment '用户策略计算结果表';

create table if not exists user_strategy_export_003
(
    id           bigint(11) auto_increment comment '自增ID' primary key,
    uId          mediumtext null,
    activityId   bigint null,
    orderId      bigint null,
    strategyId   bigint null,
    strategyType int null,
    grantType    int null,
    grantDate    timestamp null,
    grantState   int null,
    awardId      bigint null,
    awardType    int null,
    awardName    mediumtext null,
    awardContent mediumtext null,
    uuid         mediumtext null,
    createTime   timestamp null,
    updateTime   timestamp null
) comment '用户策略计算结果表';

create table if not exists user_strategy_export_004
(
    id           bigint(11) auto_increment comment '自增ID' primary key,
    uId          mediumtext null,
    activityId   bigint null,
    orderId      bigint null,
    strategyId   bigint null,
    strategyType int null,
    grantType    int null,
    grantDate    timestamp null,
    grantState   int null,
    awardId      bigint null,
    awardType    int null,
    awardName    mediumtext null,
    awardContent mediumtext null,
    uuid         mediumtext null,
    createTime   timestamp null,
    updateTime   timestamp null
) comment '用户策略计算结果表';

-- 第二个库
use
simple_lottery_002;

-- 用户参与活动记录表
create table if not exists user_take_activity
(
    id           bigint(11) auto_increment comment '自增ID' primary key,
    uId          tinytext null,
    takeId       bigint null,
    activityId   bigint null,
    activityName tinytext null,
    takeDate     timestamp null,
    takeCount    int null,
    uuid         tinytext null,
    createTime   timestamp null,
    updateTime   timestamp null
) comment '用户参与活动记录表';

-- 用户活动参与次数表
create table if not exists user_take_activity_count
(
    id         bigint(11) auto_increment comment '自增ID' primary key,
    uId        tinytext null,
    activityId bigint null,
    totalCount int null,
    leftCount  int null,
    createTime timestamp null,
    updateTime timestamp null
) comment '用户活动参与次数表';

create table if not exists user_strategy_export_001(
    id           bigint(11) auto_increment comment '自增ID' primary key,
    uId          mediumtext null,
    activityId   bigint null,
    orderId      bigint null,
    strategyId   bigint null,
    strategyType int null,
    grantType    int null,
    grantDate    timestamp null,
    grantState   int null,
    awardId      bigint null,
    awardType    int null,
    awardName    mediumtext null,
    awardContent mediumtext null,
    uuid         mediumtext null,
    createTime   timestamp null,
    updateTime   timestamp null
) comment '用户策略计算结果表';

create table if not exists user_strategy_export_002
(
    id           bigint(11) auto_increment comment '自增ID' primary key,
    uId          mediumtext null,
    activityId   bigint null,
    orderId      bigint null,
    strategyId   bigint null,
    strategyType int null,
    grantType    int null,
    grantDate    timestamp null,
    grantState   int null,
    awardId      bigint null,
    awardType    int null,
    awardName    mediumtext null,
    awardContent mediumtext null,
    uuid         mediumtext null,
    createTime   timestamp null,
    updateTime   timestamp null
) comment '用户策略计算结果表';

create table if not exists user_strategy_export_003
(
    id           bigint(11) auto_increment comment '自增ID' primary key,
    uId          mediumtext null,
    activityId   bigint null,
    orderId      bigint null,
    strategyId   bigint null,
    strategyType int null,
    grantType    int null,
    grantDate    timestamp null,
    grantState   int null,
    awardId      bigint null,
    awardType    int null,
    awardName    mediumtext null,
    awardContent mediumtext null,
    uuid         mediumtext null,
    createTime   timestamp null,
    updateTime   timestamp null
) comment '用户策略计算结果表';

create table if not exists user_strategy_export_004
(
    id           bigint(11) auto_increment comment '自增ID' primary key,
    uId          mediumtext null,
    activityId   bigint null,
    orderId      bigint null,
    strategyId   bigint null,
    strategyType int null,
    grantType    int null,
    grantDate    timestamp null,
    grantState   int null,
    awardId      bigint null,
    awardType    int null,
    awardName    mediumtext null,
    awardContent mediumtext null,
    uuid         mediumtext null,
    createTime   timestamp null,
    updateTime   timestamp null
) comment '用户策略计算结果表';








