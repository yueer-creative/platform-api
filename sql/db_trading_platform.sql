-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_account` int(11) NOT NULL,
  `admin_pwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of admin
-- ----------------------------
LOCK TABLES `admin` WRITE;
INSERT INTO `admin` VALUES ('1', '123456', '123456');
UNLOCK TABLES;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` int(11) DEFAULT NULL COMMENT '以手机号作为登录账号',
  `user_pwd` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `user_date` datetime DEFAULT NULL,
  `user_status` tinyint(4) DEFAULT '0' COMMENT '账号是否冻结，默认0未冻结',
  `user_pic` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `user_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `user_isDelete` tinyint(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '0: 正常; 1: 删除'
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES
('1', '12345678910', '123456', 'yueer', '2021-03-09 10:41:59', '0', '', '', 0),
('2', '13896978413', 'abcdefg', 'flymetothemoon', '2021-03-09 10:45:59', '0', '', '', 0),
('3', '18192481092', '123456', '我们飞', '2021-03-18 19:57:17', '0', '', '', 0),
('4', '16751244255', '123456', 'Colar', '2020-12-08 13:20:50', '0', '', '', 0),
('5', '19362825277', '123456', 'asdfasdfr', '2021-02-07 22:36:20', '0', '', '', 0),
('6', '12345678910', '123456', 'aaaar', '2021-01-27 12:36:44', '0', '', '', 0),
('7', '12345678910', '123456', 'bbb', '2021-01-27 12:36:44', '0', '', '', 0),
('8', '12345678910', '123456', 'ccc', '2021-01-28 04:50:08', '0', '', '', 0),
('9', '12345678910', '123456', 'dddd', '2021-02-26 23:39:59', '0', '', '', 0),
('10','12345678910', '123456', 'eeee', '2021-03-13 12:36:44', '0', '', '', 0),
('11','12345678910', '123456', 'ffff', '2021-01-12 11:32:03', '0', '', '', 0);


UNLOCK TABLES;

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `goods_category_id` int(11) DEFAULT NULL COMMENT '商品分类，外键',
  `goods_time` datetime DEFAULT NULL,
  `goods_realPrice` float(11,2) DEFAULT NULL, 
  `goods_price` float(11,2) DEFAULT NULL, 
  `goods_desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL, 
  `goods_seller_id` int(11)  DEFAULT NULL COMMENT '卖家，外键',
  `goods_buyer_id` int(11)  DEFAULT NULL COMMENT '买家，外键',
  `goods_img` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `goods_status` tinyint(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '0: 还未通过审核; 1: 已通过审核',
  `is_del` tinyint(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '0: 正常; 1: 删除'
  PRIMARY KEY (`goods_id`),
  KEY `goods_category_id` (`goods_category_id`),
  KEY `goods_user_id` (`goods_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_seller_id` int(11) NOT NULL COMMENT '卖家，外键',
  `order_buyer_id` int(11) NOT NULL COMMENT '买家，外键',
  `order_goods_id` int(11) NOT NULL,
  `order_num` int(25) DEFAULT NULL,
  `order_price` float(11,2) DEFAULT NULL,
  `order_status` tinyint(4) DEFAULT '1' COMMENT '订单状态 1待发货 2待收货 3已完成',
  `order_time` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of t_order
-- ----------------------------


-- ----------------------------
-- Table structure for `msg`
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `msg_id` int(11) NOT NULL AUTO_INCREMENT,
  `msg_user_id` int(11) DEFAULT NULL COMMENT '用户，外键',
  `msg_content` text DEFAULT NULL,
  `msg_time` datetime DEFAULT NULL COMMENT '推送的时间',
  `msg_status` tinyint(4) COMMENT '状态，0未读，1已读',
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of msg
-- ----------------------------

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `t_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of category
-- ----------------------------
LOCK TABLES `category` WRITE;
INSERT INTO `category` VALUES
('1', '图书教材'),
('2', '数码产品'),
('3', '美妆衣物'),
('4', '运动棋牌'),
('5', '票劵小物'),
('6', '文具办公'),
('7', '校园代步');
UNLOCK TABLES;