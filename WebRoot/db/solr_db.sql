/*
MySQL Data Transfer
Source Host: localhost
Source Database: javaqq
Target Host: localhost
Target Database: javaqq
Date: 2010-3-24 14:10:47
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `iid` int(11) NOT NULL auto_increment,
  `title` char(255) NOT NULL,
  `price` double(10,0) default NULL,
  `num` int(10) default NULL,
  `desc` char(255) default NULL,
  `cid` int(10) default NULL,
  `props` char(255) default NULL,
  `user_id` int(10) default NULL,
  `location` char(255) default NULL,
  PRIMARY KEY  (`iid`,`title`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for itemcat
-- ----------------------------
DROP TABLE IF EXISTS `itemcat`;
CREATE TABLE `itemcat` (
  `cid` int(8) NOT NULL auto_increment,
  `name` char(32) NOT NULL,
  `status` double(1,0) default NULL,
  `sort_order` int(8) default NULL,
  PRIMARY KEY  (`cid`,`name`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for orderform
-- ----------------------------
DROP TABLE IF EXISTS `orderform`;
CREATE TABLE `orderform` (
  `id` int(8) NOT NULL auto_increment,
  `oid` bigint(64) default '0',
  `iid` int(8) default NULL,
  `title` varchar(255) default NULL,
  `price` double(8,0) default NULL,
  `num` int(8) default NULL,
  `type` char(8) default NULL,
  `created` timestamp NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `user_id` int(8) default NULL,
  `timeout_action_time` date default NULL,
  `sell_type` double(2,0) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for trade
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
  `tid` int(14) NOT NULL auto_increment,
  `oid` bigint(64) default NULL,
  `created` timestamp NULL default '0000-00-00 00:00:00',
  `total_fee` decimal(8,0) default NULL,
  `payment` decimal(8,0) default NULL,
  `status` char(14) default NULL,
  `buyer_nick` char(32) default NULL,
  `has_invoice` double(1,0) default NULL,
  PRIMARY KEY  (`tid`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(8) unsigned NOT NULL auto_increment,
  `username` char(32) NOT NULL default '',
  `password` char(32) default '',
  `nick` char(32) default '',
  `sex` char(2) default NULL,
  `location` char(32) default NULL,
  `created` timestamp NULL default '0000-00-00 00:00:00',
  `last_visit` timestamp NULL default '0000-00-00 00:00:00' on update CURRENT_TIMESTAMP,
  `visit_times` int(8) unsigned default NULL,
  `birthday` date default NULL,
  `type` char(2) default NULL,
  `status` char(2) default NULL,
  `zip` char(8) default NULL,
  `address` varchar(32) default NULL,
  `city` char(32) default NULL,
  `state` char(2) default NULL,
  `country` char(32) default NULL,
  `district` char(32) default NULL,
  PRIMARY KEY  (`user_id`,`username`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `item` VALUES ('1', '移动手机充值卡', '99', '3662', null, '1', null, null, null);
INSERT INTO `item` VALUES ('2', '联通手机充值卡', '99', '2488', null, '1', null, null, null);
INSERT INTO `item` VALUES ('3', '电信/小灵通/固定电话充值卡', '20', '2000', null, '1', null, null, null);
INSERT INTO `item` VALUES ('4', '联想Y550 A-PSE(F)(灰)', '6599', '491', null, '2', null, null, null);
INSERT INTO `item` VALUES ('5', '阳光保险：阳光伴你行', '88', '0', null, '11', null, null, null);
INSERT INTO `item` VALUES ('6', '阳光保险：阳光伴你行', '25', '0', null, '11', null, null, null);
INSERT INTO `item` VALUES ('7', '阳光保险：阳光伴你行', '20', '25', null, '6', null, null, null);
INSERT INTO `itemcat` VALUES ('1', '虚拟', null, null);
INSERT INTO `itemcat` VALUES ('2', '数码', null, null);
INSERT INTO `itemcat` VALUES ('3', '美容', null, null);
INSERT INTO `itemcat` VALUES ('4', '服装', null, null);
INSERT INTO `itemcat` VALUES ('5', '配饰', null, null);
INSERT INTO `itemcat` VALUES ('6', '母婴', null, null);
INSERT INTO `itemcat` VALUES ('7', '家具', null, null);
INSERT INTO `itemcat` VALUES ('8', '食品', null, null);
INSERT INTO `itemcat` VALUES ('9', '文体', null, null);
INSERT INTO `itemcat` VALUES ('10', '服务', null, null);
INSERT INTO `itemcat` VALUES ('11', '保险', null, null);
INSERT INTO `orderform` VALUES ('1', '20100000000000', '5', '阳光保险：阳光伴你行', '88', '0', null, '2010-03-22 10:37:56', null, null, null);
INSERT INTO `orderform` VALUES ('2', '20100320233436', '4', '联想Y550 A-PSE(F)(灰)', '6599', '1', null, '2010-03-20 23:34:36', null, null, null);
INSERT INTO `orderform` VALUES ('3', '20100320233521', '4', '联想Y550 A-PSE(F)(灰)', '6599', '2', null, '2010-03-20 23:35:21', null, null, null);
INSERT INTO `orderform` VALUES ('4', '20100320233806', '4', '联想Y550 A-PSE(F)(灰)', '6599', '1', null, '2010-03-20 23:38:06', null, null, null);
INSERT INTO `orderform` VALUES ('5', '20100320234019', '2', '联通手机充值卡', '99', '1', null, '2010-03-20 23:40:19', null, null, null);
INSERT INTO `orderform` VALUES ('6', '20100320234019', '2', '联通手机充值卡', '99', '2', null, '2010-03-20 23:40:52', null, null, null);
INSERT INTO `orderform` VALUES ('7', '20100320234118', '4', '联想Y550 A-PSE(F)(灰)', '6599', '3', null, '2010-03-20 23:41:18', null, null, null);
INSERT INTO `orderform` VALUES ('8', '20100321081747', '3', '电信/小灵通/固定电话充值卡', '20', '2', null, '2010-03-21 08:17:47', null, null, null);
INSERT INTO `orderform` VALUES ('9', '20100321081852', '3', '电信/小灵通/固定电话充值卡', '20', '1', null, '2010-03-21 08:18:52', null, null, null);
INSERT INTO `orderform` VALUES ('10', '20100321081934', '3', '电信/小灵通/固定电话充值卡', '20', '1', null, '2010-03-21 08:19:34', null, null, null);
INSERT INTO `orderform` VALUES ('11', '20100321082118', '4', '联想Y550 A-PSE(F)(灰)', '6599', '1', null, '2010-03-21 08:21:18', null, null, null);
INSERT INTO `orderform` VALUES ('12', '20100321082513', '3', '电信/小灵通/固定电话充值卡', '20', '3', null, '2010-03-21 08:25:13', null, null, null);
INSERT INTO `orderform` VALUES ('13', '20100321082705', '2', '联通手机充值卡', '99', '1', null, '2010-03-21 08:27:05', null, null, null);
INSERT INTO `orderform` VALUES ('14', '20100321082915', '3', '电信/小灵通/固定电话充值卡', '20', '1', null, '2010-03-21 08:29:15', null, null, null);
INSERT INTO `orderform` VALUES ('15', '20100321083823', '2', '联通手机充值卡', '80', '1', null, '2010-03-22 12:45:03', null, null, null);
INSERT INTO `orderform` VALUES ('16', '20100321084007', '3', '电信/小灵通/固定电话充值卡', '20', '1', null, '2010-03-21 08:40:07', null, null, null);
INSERT INTO `orderform` VALUES ('17', '20100321084137', '3', '电信/小灵通/固定电话充值卡', '20', '1', null, '2010-03-21 08:41:37', null, null, null);
INSERT INTO `orderform` VALUES ('18', '20100321084628', '2', '联通手机充值卡', '99', '2', null, '2010-03-21 08:46:28', null, null, null);
INSERT INTO `orderform` VALUES ('19', '20100321085110', '3', '电信/小灵通/固定电话充值卡', '20', '2', null, '2010-03-21 08:51:10', null, null, null);
INSERT INTO `orderform` VALUES ('20', '20100321085406', '2', '联通手机充值卡', '99', '9', null, '2010-03-21 08:54:06', null, null, null);
INSERT INTO `orderform` VALUES ('21', '20100321100919', '2', '联通手机充值卡', '99', '1', null, '2010-03-21 10:09:29', null, null, null);
INSERT INTO `orderform` VALUES ('22', '20100321101034', '1', '移动手机充值卡', '99', '1', null, '2010-03-21 10:10:34', null, null, null);
INSERT INTO `orderform` VALUES ('23', '20100321101820', '3', '电信/小灵通/固定电话充值卡', '20', '2', null, '2010-03-21 10:18:20', null, null, null);
INSERT INTO `orderform` VALUES ('24', '20100321101924', '1', '移动手机充值卡', '99', '3', null, '2010-03-21 10:19:24', null, null, null);
INSERT INTO `orderform` VALUES ('25', '20100321102144', '3', '电信/小灵通/固定电话充值卡', '20', '2', null, '2010-03-21 10:21:44', null, null, null);
INSERT INTO `orderform` VALUES ('26', '20100321102331', '2', '联通手机充值卡', '99', '2', null, '2010-03-21 10:24:00', null, null, null);
INSERT INTO `orderform` VALUES ('27', '20100321103247', '1', '移动手机充值卡', '99', '9', null, '2010-03-21 10:32:54', null, null, null);
INSERT INTO `orderform` VALUES ('28', '20100321103405', '3', '电信/小灵通/固定电话充值卡', '20', '2', null, '2010-03-21 10:34:05', null, null, null);
INSERT INTO `orderform` VALUES ('29', '20100321103405', '4', '联想Y550 A-PSE(F)(灰)', '6599', '5', null, '2010-03-21 10:34:19', null, null, null);
INSERT INTO `orderform` VALUES ('30', '20100321104750', '2', '联通手机充值卡', '99', '2', null, '2010-03-21 10:47:50', null, null, null);
INSERT INTO `orderform` VALUES ('31', '20100321104750', '4', '联想Y550 A-PSE(F)(灰)', '6599', '3', null, '2010-03-21 10:48:57', null, null, null);
INSERT INTO `orderform` VALUES ('32', '20100321105149', '1', '移动手机充值卡', '99', '1', null, '2010-03-21 10:51:49', null, null, null);
INSERT INTO `orderform` VALUES ('33', '20100321105149', '2', '联通手机充值卡', '99', '21', null, '2010-03-21 10:52:10', null, null, null);
INSERT INTO `orderform` VALUES ('34', '20100321105359', '1', '移动手机充值卡', '99', '3', null, '2010-03-21 10:53:59', null, null, null);
INSERT INTO `orderform` VALUES ('35', '20100321105359', '2', '联通手机充值卡', '99', '2', null, '2010-03-21 10:54:08', null, null, null);
INSERT INTO `orderform` VALUES ('36', '20100321105359', '4', '联想Y550 A-PSE(F)(灰)', '6599', '5', null, '2010-03-21 10:54:44', null, null, null);
INSERT INTO `orderform` VALUES ('37', '20100321105514', '1', '移动手机充值卡', '99', '7', null, '2010-03-21 10:55:14', null, null, null);
INSERT INTO `orderform` VALUES ('38', '20100321105514', '4', '联想Y550 A-PSE(F)(灰)', '6599', '4', null, '2010-03-21 10:55:26', null, null, null);
INSERT INTO `orderform` VALUES ('39', '20100321130110', '1', '移动手机充值卡', '99', '38', null, '2010-03-21 13:01:10', null, null, null);
INSERT INTO `orderform` VALUES ('40', '20100321130130', '1', '移动手机充值卡', '99', '32', null, '2010-03-21 13:01:30', null, null, null);
INSERT INTO `orderform` VALUES ('41', '20100321223801', '1', '移动手机充值卡', '99', '32', null, '2010-03-21 22:38:01', null, null, null);
INSERT INTO `orderform` VALUES ('42', '20100321223935', '1', '移动手机充值卡', '99', '37', null, '2010-03-21 22:39:35', null, null, null);
INSERT INTO `orderform` VALUES ('43', '20100321223935', '3', '电信/小灵通/固定电话充值卡', '20', '21', null, '2010-03-21 22:40:11', null, null, null);
INSERT INTO `orderform` VALUES ('44', '20100322101915', '1', '移动手机充值卡', '99', '31', null, '2010-03-22 10:19:15', null, null, null);
INSERT INTO `orderform` VALUES ('45', '20100322140607', '1', '移动手机充值卡', '199', '35', null, '2010-03-22 14:06:07', null, null, null);
INSERT INTO `orderform` VALUES ('46', '20100322140607', '4', '联想Y550 A-PSE(F)(灰)', '7599', '9', null, '2010-03-22 14:06:46', null, null, null);
INSERT INTO `orderform` VALUES ('47', '20100322140800', '1', '移动手机充值卡', '399', '3', null, '2010-03-22 14:09:30', null, null, null);
INSERT INTO `orderform` VALUES ('48', '20100323163238', '4', '联想Y550 A-PSE(F)(灰)', '7599', '31', null, '2010-03-23 16:32:48', null, null, null);
INSERT INTO `orderform` VALUES ('49', '20100323163416', '2', '联通手机充值卡', '999', '7', null, '2010-03-23 16:34:31', null, null, null);
INSERT INTO `orderform` VALUES ('50', '20100323164502', '3', '电信/小灵通/固定电话充值卡', '20', '1', null, '2010-03-23 16:45:02', null, null, null);
INSERT INTO `orderform` VALUES ('51', '20100323165240', '4', '联想Y550 A-PSE(F)(灰)', '6599', '5', null, '2010-03-23 16:52:40', null, null, null);
INSERT INTO `orderform` VALUES ('52', '20100323165240', '2', '联通手机充值卡', '99', '2', null, '2010-03-23 16:52:48', null, null, null);
INSERT INTO `orderform` VALUES ('53', '20100323165804', '4', '联想Y550 A-PSE(F)(灰)', '9599', '4', null, '2010-03-23 16:58:04', null, null, null);
INSERT INTO `trade` VALUES ('1', '20100320233259', '2010-03-23 17:08:51', '0', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('2', '20100320233436', '2010-03-23 20:55:45', '6599', '6599', 'normal', null, null);
INSERT INTO `trade` VALUES ('3', '20100320233521', '2010-03-23 21:04:05', '13198', '3198', 'normal', null, null);
INSERT INTO `trade` VALUES ('4', '20100320233806', '2010-03-23 21:34:24', '6599', '6599', 'normal', null, null);
INSERT INTO `trade` VALUES ('5', '20100320234019', '2010-03-23 21:39:26', '297', '97', 'normal', null, null);
INSERT INTO `trade` VALUES ('6', '20100320234118', '2010-03-23 17:12:17', '19797', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('7', '20100321081747', '2010-03-23 17:12:20', '40', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('8', '20100321081852', '2010-03-23 17:12:22', '20', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('9', '20100321081934', '2010-03-23 17:12:26', '20', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('10', '20100321082118', '2010-03-23 17:12:27', '6599', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('11', '20100321082513', '2010-03-23 17:18:36', '60', '0', 'delete', null, null);
INSERT INTO `trade` VALUES ('12', '20100321082705', '2010-03-23 17:18:40', '99', '0', 'delete', null, null);
INSERT INTO `trade` VALUES ('13', '20100321082915', '2010-03-23 17:19:07', '20', '0', 'delete', null, null);
INSERT INTO `trade` VALUES ('14', '20100321083823', '2010-03-23 17:18:41', '80', '0', 'delete', null, null);
INSERT INTO `trade` VALUES ('15', '20100321084007', '2010-03-23 17:19:09', '20', '0', 'delete', null, null);
INSERT INTO `trade` VALUES ('16', '20100321084137', '2010-03-23 17:19:10', '20', '0', 'delete', null, null);
INSERT INTO `trade` VALUES ('17', '20100321084628', '2010-03-23 17:18:44', '198', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('18', '20100321085110', '2010-03-23 17:18:45', '40', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('19', '20100321085406', '2010-03-23 17:18:47', '891', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('20', '20100321100919', '2010-03-23 17:18:50', '99', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('21', '20100321101034', '2010-03-23 17:18:53', '99', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('22', '20100321101820', '2010-03-23 17:18:54', '40', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('23', '20100321101924', '2010-03-23 17:18:56', '297', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('24', '20100321102144', '2010-03-23 17:18:59', '40', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('25', '20100321102331', '2010-03-23 17:19:00', '198', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('26', '20100321103247', '2010-03-23 17:19:02', '891', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('27', '20100321103405', '2010-03-23 17:21:11', '33035', '0', 'delete', null, null);
INSERT INTO `trade` VALUES ('28', '20100321104750', '2010-03-23 17:20:43', '19995', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('29', '20100321105149', '2010-03-23 17:20:45', '2178', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('30', '20100321105359', '2010-03-24 13:54:43', '33490', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('31', '20100321105514', '2010-03-23 17:20:46', '27089', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('32', '20100321130110', '2010-03-23 17:20:47', '3762', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('33', '20100321130130', '2010-03-23 17:20:40', '3168', '0', 'delete', null, null);
INSERT INTO `trade` VALUES ('34', '20100321223935', '2010-03-23 17:20:49', '4083', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('35', '20100322101915', '2010-03-23 17:20:37', '3069', '0', 'delete', null, null);
INSERT INTO `trade` VALUES ('36', '20100322140607', '2010-03-23 17:20:39', '438191', '0', 'delete', null, null);
INSERT INTO `trade` VALUES ('37', '20100322140800', '2010-03-23 17:20:33', '597', '0', 'delete', null, null);
INSERT INTO `trade` VALUES ('38', '20100323163238', '2010-03-23 17:20:35', '335619', '0', 'delete', null, null);
INSERT INTO `trade` VALUES ('39', '20100323163416', '2010-03-23 20:18:18', '3506493', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('40', '20100323164502', '2010-03-24 09:10:57', '20', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('41', '20100323165240', '2010-03-24 09:11:01', '33193', '0', 'normal', null, null);
INSERT INTO `trade` VALUES ('42', '20100323165804', '2010-03-23 20:13:41', '533508', '0', 'normal', null, null);
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '楝煎緬', '男', '桂林', '2010-03-18 00:00:00', '2010-03-24 14:00:52', '33', null, 'A', null, null, null, '桂林', '广西', '中国', null);
INSERT INTO `user` VALUES ('2', 'administrator@qq.com', 'admin', 'admin', null, null, '2010-03-19 09:08:06', '2010-03-22 22:50:11', '1', null, 'A', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('3', 'admin@qq.com', 'admin', 'admin', null, null, '2010-03-19 09:11:36', '2010-03-22 22:50:13', '1', null, 'A', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('4', '腾讯', '', '', null, null, '2010-03-23 22:37:15', '2010-03-23 22:37:15', '1', null, 'C', null, '518057', '深圳市南山区高新科技园中区一路腾讯大厦 ', '深圳', '广东', 'null', null);
INSERT INTO `user` VALUES ('5', '', '', '', null, null, '2010-03-24 06:56:01', '2010-03-24 06:56:01', '1', null, 'C', null, '', '', '', '', '', null);
