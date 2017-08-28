/*
Navicat MySQL Data Transfer

Source Server         : 192.168.60.81
Source Server Version : 50629
Source Host           : 192.168.60.81:3306
Source Database       : grain

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-08-28 09:52:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for grain_group
-- ----------------------------
DROP TABLE IF EXISTS `grain_group`;
CREATE TABLE `grain_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `group_level` int(11) DEFAULT NULL COMMENT '等级',
  `parent_id` int(11) DEFAULT NULL COMMENT '父Id',
  `sort_num` int(4) DEFAULT NULL COMMENT '�����\nԽСԽ��ǰ\nint��4��',
  PRIMARY KEY (`group_id`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grain_group
-- ----------------------------
INSERT INTO `grain_group` VALUES ('1', '济南召会', '0531', '159357', '10', null, '1');
INSERT INTO `grain_group` VALUES ('8', '百花小排', '0531010202', '125491', '40', '16', null);
INSERT INTO `grain_group` VALUES ('2', '一大区', '053101', '125480', '20', '1', '2');
INSERT INTO `grain_group` VALUES ('4', '二大区', '053102', '225480', '20', '1', '3');
INSERT INTO `grain_group` VALUES ('5', '三大区', '053103', '325480', '20', '1', '4');
INSERT INTO `grain_group` VALUES ('6', '四大区', '053104', '425480', '20', '1', '5');
INSERT INTO `grain_group` VALUES ('7', '五大区', '053105', '525480', '20', '1', '6');
INSERT INTO `grain_group` VALUES ('9', '锡安小排', '0531010101', '125492', '40', '15', null);
INSERT INTO `grain_group` VALUES ('10', '以琳小排', '0531010201', '125493', '40', '16', null);
INSERT INTO `grain_group` VALUES ('11', '中海小排', '0531010301', '125494', '40', '17', null);
INSERT INTO `grain_group` VALUES ('12', '华山小排', '0531010401', '125495', '40', '18', null);
INSERT INTO `grain_group` VALUES ('13', '汉裕小排', '0531010302', '125496', '40', '16', null);
INSERT INTO `grain_group` VALUES ('15', '1小区', '05310101', '125481', '30', '2', null);
INSERT INTO `grain_group` VALUES ('16', '2小区', '05310102', '125482', '30', '2', null);
INSERT INTO `grain_group` VALUES ('17', '3小区', '05310103', '125483', '30', '2', null);
INSERT INTO `grain_group` VALUES ('18', '4小区', '05310104', '125484', '30', '2', null);
INSERT INTO `grain_group` VALUES ('20', '转山小排', '0531030501', '325491', '40', '19', null);
INSERT INTO `grain_group` VALUES ('19', '5小区', '05310305', '325481', '30', '5', null);
INSERT INTO `grain_group` VALUES ('23', '泉兴小排', '0531020101', '225480', '40', '24', null);
INSERT INTO `grain_group` VALUES ('24', '1小区', '05310201', '225480', '30', '4', null);
INSERT INTO `grain_group` VALUES ('25', '张家小排', '0531020102', '225480', '40', '24', null);
INSERT INTO `grain_group` VALUES ('26', '2小区', '05310202', '225480', '30', '4', null);
INSERT INTO `grain_group` VALUES ('27', '3小区', '05310203', '225480', '30', '4', null);
INSERT INTO `grain_group` VALUES ('28', '黄冈小排', '0531020201', '225480', '40', '26', null);
INSERT INTO `grain_group` VALUES ('29', '花都小排', '0531020301', '225480', '40', '27', null);
INSERT INTO `grain_group` VALUES ('30', '1小区', '05311201', '325480', '30', '46', null);
INSERT INTO `grain_group` VALUES ('31', '2小区', '05310302', '325480', '30', '5', null);
INSERT INTO `grain_group` VALUES ('32', '3小区', '05310303', '325480', '30', '5', null);
INSERT INTO `grain_group` VALUES ('33', '4小区', '05310304', '325480', '30', '5', null);
INSERT INTO `grain_group` VALUES ('34', '济大小排', '0531030101', '325480', '40', '30', null);
INSERT INTO `grain_group` VALUES ('35', '学仕小排', '0531030201', '325480', '40', '31', null);
INSERT INTO `grain_group` VALUES ('36', '历山小排', '0531030301', '325480', '40', '32', null);
INSERT INTO `grain_group` VALUES ('37', '伟东小排', '0531030401', '325480', '40', '33', null);
INSERT INTO `grain_group` VALUES ('38', '1小区', '05310401', '425480', '30', '6', null);
INSERT INTO `grain_group` VALUES ('39', '2小区', '05310402', '425480', '30', '6', null);
INSERT INTO `grain_group` VALUES ('46', '十二大区', '053112', '525480', '20', '1', '7');
INSERT INTO `grain_group` VALUES ('40', '英才小排', '0531040101', '425480', '40', '38', null);
INSERT INTO `grain_group` VALUES ('41', '诺贝尔小排', '0531040201', '425480', '40', '39', null);
INSERT INTO `grain_group` VALUES ('42', '1小区', '05310501', '525480', '30', '7', null);
INSERT INTO `grain_group` VALUES ('43', '师艺小排', '0531050101', '525480', '40', '42', null);
INSERT INTO `grain_group` VALUES ('44', '齐鲁小排', '0531050102', '525480', '40', '42', null);
INSERT INTO `grain_group` VALUES ('45', '交管小排', '0531050103', '525480', '40', '42', null);

-- ----------------------------
-- Table structure for grain_li_yue
-- ----------------------------
DROP TABLE IF EXISTS `grain_li_yue`;
CREATE TABLE `grain_li_yue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `li_yue_id` varchar(50) DEFAULT NULL,
  `meeting_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_li_yue_type` int(3) DEFAULT NULL,
  `remark` varchar(500) NOT NULL,
  `sort_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grain_li_yue
-- ----------------------------
INSERT INTO `grain_li_yue` VALUES ('1', '1503655299067', '4', '1', '1', '', '1');

-- ----------------------------
-- Table structure for grain_meeting
-- ----------------------------
DROP TABLE IF EXISTS `grain_meeting`;
CREATE TABLE `grain_meeting` (
  `meeting_id` int(11) NOT NULL AUTO_INCREMENT,
  `meeting_name` varchar(255) DEFAULT NULL,
  `day_of_week` varchar(1) DEFAULT NULL,
  `rate` int(3) DEFAULT NULL,
  `valid_flag` int(1) DEFAULT NULL,
  `liyue_flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`meeting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grain_meeting
-- ----------------------------
INSERT INTO `grain_meeting` VALUES ('1', '祷告', '3', '7', '1', null);
INSERT INTO `grain_meeting` VALUES ('2', '小排', '6', '7', '1', null);
INSERT INTO `grain_meeting` VALUES ('3', '主日', '1', '7', '1', null);
INSERT INTO `grain_meeting` VALUES ('4', '滴灌', null, '1', '1', '1');

-- ----------------------------
-- Table structure for grain_meeting_attend
-- ----------------------------
DROP TABLE IF EXISTS `grain_meeting_attend`;
CREATE TABLE `grain_meeting_attend` (
  `meeting_attend_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `meeting_id` int(11) DEFAULT NULL,
  `attend_time` datetime DEFAULT NULL,
  `speak_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`meeting_attend_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grain_meeting_attend
-- ----------------------------
INSERT INTO `grain_meeting_attend` VALUES ('29', '6', '3', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('37', '17', '3', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('38', '16', '3', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('39', '6', '3', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('44', '7', '2', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('45', '8', '2', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('46', '5', '4', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('47', '1', '1', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('48', '17', '1', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('49', '8', '1', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('50', '4', '2', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('51', '17', '2', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('52', '4', '1', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('53', '4', '3', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('54', '3', '2', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('57', '21', '3', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('58', '3', '3', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('59', '7', '1', '2017-08-13 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('60', '1', '5', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('61', '1', '2', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('62', '7', '3', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('63', '7', '1', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('64', '1', '1', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('65', '1', '4', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('66', '1', '3', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('67', '15', '4', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('68', '25', '1', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('70', '3', '3', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('71', '4', '1', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('72', '25', '2', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('73', '3', '2', '2017-08-20 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('74', '1', '1', '2017-08-27 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('75', '7', '2', '2017-08-27 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('76', '15', '2', '2017-08-27 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('77', '21', '3', '2017-08-27 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('78', '17', '3', '2017-08-27 00:00:00', null);
INSERT INTO `grain_meeting_attend` VALUES ('79', '16', '3', '2017-08-27 00:00:00', null);

-- ----------------------------
-- Table structure for grain_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `grain_operate_log`;
CREATE TABLE `grain_operate_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `page_id` int(11) NOT NULL COMMENT '菜单ID',
  `operate_user_id` int(11) DEFAULT NULL COMMENT '操作人ID',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operate_content` varchar(4000) DEFAULT NULL COMMENT '操作内容',
  `operate_type` int(1) DEFAULT NULL COMMENT '操作类型\r\nint(1)\r\n\r\n1：查看\r\n2：增加\r\n3：修改\r\n4：删除',
  `operate_ip` varchar(50) DEFAULT NULL COMMENT '操作人（客户端）的IP地址\r\n\r\n例如：192.168.60.221',
  `sys_url_port` varchar(100) DEFAULT NULL COMMENT '服务器的URL和端口号\r\n\r\n例如：http://192.168.60.91:8080/workorder',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grain_operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for grain_user
-- ----------------------------
DROP TABLE IF EXISTS `grain_user`;
CREATE TABLE `grain_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `group_id` int(11) DEFAULT NULL,
  `group_code` varchar(50) DEFAULT NULL,
  `age` int(1) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL COMMENT '性别（0：男；1:女）\r\n\r\nint（1）',
  `shoujin_flag` int(1) DEFAULT NULL,
  `shoujin_local_flag` int(1) DEFAULT NULL,
  `shoujin_time` datetime DEFAULT NULL COMMENT '入职时间\r\n\r\n年-月-日 时:分:秒',
  `remark` varchar(200) DEFAULT NULL,
  `sort_num` int(4) DEFAULT NULL COMMENT '排序号\r\n\r\n越小越靠前\r\n\r\nint（4）',
  PRIMARY KEY (`user_id`),
  KEY `index_user_user_id` (`user_id`) USING BTREE,
  KEY `index_user_name` (`name`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grain_user
-- ----------------------------
INSERT INTO `grain_user` VALUES ('1', '望天弟兄', '9', '0531010101', '4', '1', '1', '0', '2009-11-01 16:33:47', 'a', null);
INSERT INTO `grain_user` VALUES ('2', '燕顺', '10', '0531010201', '4', '1', '1', '1', '2017-08-28 09:24:05', 'ad', null);
INSERT INTO `grain_user` VALUES ('3', '孝斌弟兄', '20', '0531030101', '3', '1', '1', '0', '2012-08-21 00:00:00', '', null);
INSERT INTO `grain_user` VALUES ('4', '灵引弟兄', '43', '0531050101', null, '1', '1', null, null, null, null);
INSERT INTO `grain_user` VALUES ('5', '贞营弟兄', '10', '0531010201', null, '1', '1', '1', '2015-11-25 14:44:37', null, null);
INSERT INTO `grain_user` VALUES ('6', '云梅姊妹', '10', '0531010201', null, '0', '1', '1', '2016-10-23 13:33:33', null, null);
INSERT INTO `grain_user` VALUES ('7', '小丹姊妹', '9', '0531010101', '4', '0', '1', '1', '2001-11-01 16:33:47', '12', null);
INSERT INTO `grain_user` VALUES ('8', '史姊妹', '10', '0531010201', '4', '0', '1', '0', '2013-08-17 00:00:00', '', null);
INSERT INTO `grain_user` VALUES ('15', '良栋', '9', '0531010101', '3', '1', '1', '1', '2017-08-28 09:24:06', '', null);
INSERT INTO `grain_user` VALUES ('16', '福音朋友3测试', '9', '0531010101', '4', '1', '1', '1', '2017-08-26 12:10:04', '各个', null);
INSERT INTO `grain_user` VALUES ('17', '烈辉弟兄', '13', '0531010302', null, '1', '1', '1', '2016-12-31 11:06:24', '', null);
INSERT INTO `grain_user` VALUES ('21', '福音朋友2', '41', '0531040201', '3', '1', '1', '1', '2017-08-23 09:20:01', '阿斯顿发送到', null);
INSERT INTO `grain_user` VALUES ('24', '斯蒂芬', '44', '0531050102', '4', '1', '1', '1', '2017-08-21 09:33:06', '3', null);
INSERT INTO `grain_user` VALUES ('25', '某某弟兄', '9', '0531010101', '5', '1', '1', '1', '2014-08-07 00:00:00', '', null);

-- ----------------------------
-- Function structure for queryChildrenGroup
-- ----------------------------
DROP FUNCTION IF EXISTS `queryChildrenGroup`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `queryChildrenGroup`(groupId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='-1';
SET sTempChd = CAST(groupId AS CHAR);

WHILE sTempChd IS NOT NULL DO
SET sTemp= CONCAT(sTemp,',',sTempChd);
SELECT GROUP_CONCAT(group_id) INTO sTempChd FROM grain_group WHERE FIND_IN_SET(parent_id,sTempChd)>0;
END WHILE;
RETURN sTemp;
END
;;
DELIMITER ;
