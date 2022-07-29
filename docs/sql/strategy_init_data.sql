use simple_lottery;
# 策略表
INSERT INTO simple_lottery.strategy (id, strategy_id, strategy_desc, strategy_mode, grant_type, grant_date, ext_info, create_time, update_time) VALUES (1, 1001, '测试策略1', 1, 1, '2022-07-29 17:29:35', '11111', '2022-07-29 17:29:42', '2022-07-29 17:29:44');
INSERT INTO simple_lottery.strategy (id, strategy_id, strategy_desc, strategy_mode, grant_type, grant_date, ext_info, create_time, update_time) VALUES (2, 1002, '测试策略2', 2, 1, '2022-07-29 17:30:17', '22222', '2022-07-29 17:30:21', '2022-07-29 17:30:24');

# 奖品表
INSERT INTO simple_lottery.award (id, award_id, award_type, award_count, award_name, award_content, create_time, update_time) VALUES (1, 101, 1, 10, 'IMac', '一等奖：IMac', '2022-07-29 21:19:52', '2022-07-29 21:19:53');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_count, award_name, award_content, create_time, update_time) VALUES (2, 102, 1, 20, 'iphone', '二等奖：iphone', '2022-07-29 21:20:18', '2022-07-29 21:20:20');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_count, award_name, award_content, create_time, update_time) VALUES (3, 103, 1, 50, 'ipad', '三等奖：ipad', '2022-07-29 21:20:48', '2022-07-29 21:20:50');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_count, award_name, award_content, create_time, update_time) VALUES (4, 104, 1, 100, 'AirPods', '四等奖：AirPods', '2022-07-29 21:21:15', '2022-07-29 21:21:17');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_count, award_name, award_content, create_time, update_time) VALUES (5, 105, 1, 500, 'Book', '五等奖：Book', '2022-07-29 21:21:45', '2022-07-29 21:21:46');

# 策略详细表
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_count, award_rate, award_surplus_count, create_time, update_time) VALUES (1, 1001, 101, 10, 0.05, 1, '2022-07-29 21:27:11', '2022-07-29 21:27:13');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_count, award_rate, award_surplus_count, create_time, update_time) VALUES (2, 1001, 102, 20, 0.15, 0, '2022-07-29 21:27:41', '2022-07-29 21:27:43');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_count, award_rate, award_surplus_count, create_time, update_time) VALUES (3, 1001, 103, 50, 0.20, 0, '2022-07-29 21:28:05', '2022-07-29 21:28:06');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_count, award_rate, award_surplus_count, create_time, update_time) VALUES (4, 1001, 104, 100, 0.25, 82, '2022-07-29 21:28:32', '2022-07-29 21:28:34');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_count, award_rate, award_surplus_count, create_time, update_time) VALUES (5, 1001, 105, 500, 0.35, 395, '2022-07-29 21:28:58', '2022-07-29 21:29:00');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_count, award_rate, award_surplus_count, create_time, update_time) VALUES (6, 1002, 101, 10, 0.05, 1, '2022-07-29 21:27:11', '2022-07-29 21:27:13');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_count, award_rate, award_surplus_count, create_time, update_time) VALUES (7, 1002, 102, 20, 0.15, 0, '2022-07-29 21:27:41', '2022-07-29 21:27:43');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_count, award_rate, award_surplus_count, create_time, update_time) VALUES (8, 1002, 103, 50, 0.20, 0, '2022-07-29 21:28:05', '2022-07-29 21:28:06');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_count, award_rate, award_surplus_count, create_time, update_time) VALUES (9, 1002, 104, 100, 0.25, 82, '2022-07-29 21:28:32', '2022-07-29 21:28:34');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_count, award_rate, award_surplus_count, create_time, update_time) VALUES (10, 1002, 105, 500, 0.35, 395, '2022-07-29 21:28:58', '2022-07-29 21:29:00');