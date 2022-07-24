create schema simple_lottery;

use simple_lottery;

CREATE TABLE `activity`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `activity_id`     bigint(20) NOT NULL COMMENT '活动ID',
    `activity_name`   varchar(64) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '活动名称',
    `activity_desc`   varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '活动描述',
    `begin_date_time` datetime                           DEFAULT NULL COMMENT '开始时间',
    `end_date_time`   datetime                           DEFAULT NULL COMMENT '结束时间',
    `stock_count`     int(11)                            DEFAULT NULL COMMENT '库存',
    `take_count`      int(11)                            DEFAULT NULL COMMENT '每人可参与次数',
    `state`           tinyint(2)                         DEFAULT NULL COMMENT '活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启',
    `creator`         varchar(64) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '创建人',
    `create_time`     datetime                           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime                           DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_activity_id` (`activity_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='活动配置';
