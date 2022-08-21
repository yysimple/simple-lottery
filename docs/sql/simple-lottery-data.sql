-- activity
INSERT INTO simple_lottery.activity (id, activity_id, activity_name, activity_desc, begin_date_time, end_date_time, stock_count, stock_surplus_count, take_count, strategy_id, state, creator, create_time, update_time) VALUES (1, 100001, '活动名', '测试活动', '2022-07-31 16:24:25', '2022-08-31 16:24:25', 100, 94, 10, 10001, 5, 'wcx', '2022-08-03 20:14:50', '2022-08-31 20:14:50');
INSERT INTO simple_lottery.activity (id, activity_id, activity_name, activity_desc, begin_date_time, end_date_time, stock_count, stock_surplus_count, take_count, strategy_id, state, creator, create_time, update_time) VALUES (4, 100002, '测试活动', '测试活动描述', '2022-08-02 08:20:28.025', '2022-08-31 08:20:28.025', 100, 91, 10, 10002, 5, 'wcx', '2022-08-01 16:20:29', '2022-08-31 16:20:29');

-- award
INSERT INTO simple_lottery.award (id, award_id, award_type, award_name, award_content, create_time, update_time) VALUES (1, '1', 1, 'IMac', 'Code', '2022-07-31 16:24:26', '2022-07-31 16:24:26');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_name, award_content, create_time, update_time) VALUES (2, '2', 1, 'iphone', 'Code', '2022-07-31 16:24:26', '2022-07-31 16:24:26');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_name, award_content, create_time, update_time) VALUES (3, '3', 1, 'ipad', 'Code', '2022-07-31 16:24:26', '2022-07-31 16:24:26');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_name, award_content, create_time, update_time) VALUES (4, '4', 1, 'AirPods', 'Code', '2022-07-31 16:24:26', '2022-07-31 16:24:26');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_name, award_content, create_time, update_time) VALUES (5, '5', 1, 'Book', 'Code', '2022-07-31 16:24:26', '2022-07-31 16:24:26');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_name, award_content, create_time, update_time) VALUES (6, '101', 1, '电脑', '请联系活动组织者 wcx', '2022-08-02 16:20:29', '2022-08-02 16:20:29');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_name, award_content, create_time, update_time) VALUES (7, '102', 1, '手机', '请联系活动组织者 wcx', '2022-08-02 16:20:29', '2022-08-02 16:20:29');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_name, award_content, create_time, update_time) VALUES (8, '103', 1, '平板', '请联系活动组织者 wcx', '2022-08-02 16:20:29', '2022-08-02 16:20:29');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_name, award_content, create_time, update_time) VALUES (9, '104', 1, '耳机', '请联系活动组织者 wcx', '2022-08-02 16:20:29', '2022-08-02 16:20:29');
INSERT INTO simple_lottery.award (id, award_id, award_type, award_name, award_content, create_time, update_time) VALUES (10, '105', 1, '数据线', '请联系活动组织者 wcx', '2022-08-02 16:20:29', '2022-08-02 16:20:29');

-- rule_tree
INSERT INTO simple_lottery.rule_tree (id, tree_name, tree_desc, tree_root_node_id, create_time, update_time) VALUES (2110081902, '抽奖活动规则树', '用于决策不同用户可参与的活动', 1, '2022-07-31 16:24:26', '2022-07-31 16:24:26');

-- rule_tree_node
INSERT INTO simple_lottery.rule_tree_node (id, tree_id, node_type, node_value, rule_key, rule_desc) VALUES (1, 2110081902, 1, null, 'userGender', '用户性别[男/女]');
INSERT INTO simple_lottery.rule_tree_node (id, tree_id, node_type, node_value, rule_key, rule_desc) VALUES (11, 2110081902, 1, null, 'userAge', '用户年龄');
INSERT INTO simple_lottery.rule_tree_node (id, tree_id, node_type, node_value, rule_key, rule_desc) VALUES (12, 2110081902, 1, null, 'userAge', '用户年龄');
INSERT INTO simple_lottery.rule_tree_node (id, tree_id, node_type, node_value, rule_key, rule_desc) VALUES (111, 2110081902, 2, '100001', null, null);
INSERT INTO simple_lottery.rule_tree_node (id, tree_id, node_type, node_value, rule_key, rule_desc) VALUES (112, 2110081902, 2, '100002', null, null);
INSERT INTO simple_lottery.rule_tree_node (id, tree_id, node_type, node_value, rule_key, rule_desc) VALUES (121, 2110081902, 2, '100001', null, null);
INSERT INTO simple_lottery.rule_tree_node (id, tree_id, node_type, node_value, rule_key, rule_desc) VALUES (122, 2110081902, 2, '100002', null, null);

-- rule_tree_node_line
INSERT INTO simple_lottery.rule_tree_node_line (id, tree_id, node_id_from, node_id_to, rule_limit_type, rule_limit_value) VALUES (1, 2110081902, 1, 11, 1, 'man');
INSERT INTO simple_lottery.rule_tree_node_line (id, tree_id, node_id_from, node_id_to, rule_limit_type, rule_limit_value) VALUES (2, 2110081902, 1, 12, 1, 'woman');
INSERT INTO simple_lottery.rule_tree_node_line (id, tree_id, node_id_from, node_id_to, rule_limit_type, rule_limit_value) VALUES (3, 2110081902, 11, 111, 3, '25');
INSERT INTO simple_lottery.rule_tree_node_line (id, tree_id, node_id_from, node_id_to, rule_limit_type, rule_limit_value) VALUES (4, 2110081902, 11, 112, 4, '25');
INSERT INTO simple_lottery.rule_tree_node_line (id, tree_id, node_id_from, node_id_to, rule_limit_type, rule_limit_value) VALUES (5, 2110081902, 12, 121, 3, '25');
INSERT INTO simple_lottery.rule_tree_node_line (id, tree_id, node_id_from, node_id_to, rule_limit_type, rule_limit_value) VALUES (6, 2110081902, 12, 122, 4, '25');

-- strategy
INSERT INTO simple_lottery.strategy (id, strategy_id, strategy_desc, strategy_mode, grant_type, grant_date, ext_info, create_time, update_time) VALUES (1, 10001, 'test', 2, 1, null, '', '2022-07-31 16:24:27', '2022-07-31 16:24:27');
INSERT INTO simple_lottery.strategy (id, strategy_id, strategy_desc, strategy_mode, grant_type, grant_date, ext_info, create_time, update_time) VALUES (2, 10002, '抽奖策略', 1, 1, '2022-08-02 08:20:28.025', '', '2022-08-02 16:20:29', '2022-08-02 16:20:29');

-- strategy_detail
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_name, award_count, award_surplus_count, award_rate, create_time, update_time) VALUES (1, 10001, '1', 'IMac', 10, 10, 0.05, '2022-07-31 16:24:27', '2022-07-31 16:24:27');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_name, award_count, award_surplus_count, award_rate, create_time, update_time) VALUES (2, 10001, '2', 'iphone', 20, 16, 0.15, '2022-07-31 16:24:27', '2022-07-31 16:24:27');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_name, award_count, award_surplus_count, award_rate, create_time, update_time) VALUES (3, 10001, '3', 'ipad', 50, 42, 0.20, '2022-07-31 16:24:27', '2022-07-31 16:24:27');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_name, award_count, award_surplus_count, award_rate, create_time, update_time) VALUES (4, 10001, '4', 'AirPods', 100, 67, 0.25, '2022-07-31 16:24:27', '2022-07-31 16:24:27');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_name, award_count, award_surplus_count, award_rate, create_time, update_time) VALUES (5, 10001, '5', 'Book', 500, 389, 0.35, '2022-07-31 16:24:27', '2022-07-31 16:24:27');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_name, award_count, award_surplus_count, award_rate, create_time, update_time) VALUES (6, 10002, '101', '一等奖', 10, 10, 0.05, '2022-08-02 16:20:29', '2022-08-02 16:20:29');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_name, award_count, award_surplus_count, award_rate, create_time, update_time) VALUES (7, 10002, '102', '二等奖', 20, 20, 0.15, '2022-08-02 16:20:29', '2022-08-02 16:20:29');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_name, award_count, award_surplus_count, award_rate, create_time, update_time) VALUES (8, 10002, '103', '三等奖', 50, 49, 0.20, '2022-08-02 16:20:29', '2022-08-02 16:20:29');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_name, award_count, award_surplus_count, award_rate, create_time, update_time) VALUES (9, 10002, '104', '四等奖', 100, 99, 0.25, '2022-08-02 16:20:29', '2022-08-02 16:20:29');
INSERT INTO simple_lottery.strategy_detail (id, strategy_id, award_id, award_name, award_count, award_surplus_count, award_rate, create_time, update_time) VALUES (10, 10002, '105', '五等奖', 500, 499, 0.35, '2022-08-02 16:20:29', '2022-08-02 16:20:29');