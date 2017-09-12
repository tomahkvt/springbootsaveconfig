-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.13 - MySQL Community Server (GPL)
-- Операционная система:         Win32
-- HeidiSQL Версия:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных saveconfig
CREATE DATABASE IF NOT EXISTS `saveconfig` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `saveconfig`;

-- Дамп структуры для таблица saveconfig.commands
CREATE TABLE IF NOT EXISTS `commands` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id_template` int(11) NOT NULL DEFAULT '0',
  `command_order` int(11) NOT NULL DEFAULT '0',
  `command` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `wait_for` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `wait_time` int(11) NOT NULL DEFAULT '0',
  `save` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.device
CREATE TABLE IF NOT EXISTS `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_passw` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `username1` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password1` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `username2` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password2` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `username3` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password3` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `username4` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password4` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `promt1` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `promt2` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `promt3` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `promt4` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `switch_enable` bit(1) DEFAULT NULL,
  `id_device_type` int(11) DEFAULT NULL,
  `id_template` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t947fw5848gwaomf1c2q0dper` (`id_device_type`),
  KEY `FK_device_template` (`id_template`),
  CONSTRAINT `device_ibfk_1` FOREIGN KEY (`id_device_type`) REFERENCES `device_type` (`id`),
  CONSTRAINT `FK_device_template` FOREIGN KEY (`id_template`) REFERENCES `template` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.device_groups
CREATE TABLE IF NOT EXISTS `device_groups` (
  `devicegroupid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `deviceid` int(11) unsigned NOT NULL,
  `groupid` int(11) unsigned NOT NULL,
  PRIMARY KEY (`devicegroupid`),
  UNIQUE KEY `hosts_groups_1` (`deviceid`,`groupid`),
  KEY `hosts_groups_2` (`groupid`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.device_type
CREATE TABLE IF NOT EXISTS `device_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.globalparam
CREATE TABLE IF NOT EXISTS `globalparam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paramkey` varchar(50) DEFAULT NULL,
  `paramvalue` varchar(1000) DEFAULT NULL,
  UNIQUE KEY `Индекс 1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.groups
CREATE TABLE IF NOT EXISTS `groups` (
  `groupid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`groupid`),
  KEY `groups_1` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.more
CREATE TABLE IF NOT EXISTS `more` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id_template` int(11) NOT NULL DEFAULT '0',
  `more` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `more_do` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `isdelete` bit(1) NOT NULL DEFAULT b'0',
  `more_delete` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.role
CREATE TABLE IF NOT EXISTS `role` (
  `id_role` int(11) NOT NULL,
  `role` varchar(50) DEFAULT NULL,
  `role_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.schedule
CREATE TABLE IF NOT EXISTS `schedule` (
  `scheduleid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `taskid` int(11) DEFAULT NULL,
  `day_of_week` int(11) DEFAULT NULL,
  `hour` int(11) DEFAULT NULL,
  `minute` int(11) DEFAULT NULL,
  PRIMARY KEY (`scheduleid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.tasks
CREATE TABLE IF NOT EXISTS `tasks` (
  `taskid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `taskname` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`taskid`),
  KEY `groups_1` (`taskname`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.tasks_complited
CREATE TABLE IF NOT EXISTS `tasks_complited` (
  `taskcompid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `taskid` int(11) unsigned DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `status` varchar(50) NOT NULL DEFAULT '',
  `starttime` datetime NOT NULL,
  `stoptime` datetime NOT NULL,
  PRIMARY KEY (`taskcompid`)
) ENGINE=InnoDB AUTO_INCREMENT=566 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.tasks_complited_loging
CREATE TABLE IF NOT EXISTS `tasks_complited_loging` (
  `idtaskcomplog` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `taskcompid` int(11) DEFAULT NULL,
  `comment` varchar(100) NOT NULL DEFAULT '',
  `status` varchar(100) NOT NULL DEFAULT '',
  `timeinsert` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idtaskcomplog`)
) ENGINE=InnoDB AUTO_INCREMENT=908 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.tasks_device
CREATE TABLE IF NOT EXISTS `tasks_device` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `taskid` int(11) unsigned DEFAULT NULL,
  `device_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.tasks_group
CREATE TABLE IF NOT EXISTS `tasks_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `taskid` int(11) unsigned DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.template
CREATE TABLE IF NOT EXISTS `template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_terminal_server` int(11) DEFAULT NULL,
  `template_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_template_terminal_server` (`id_terminal_server`),
  CONSTRAINT `FK_template_terminal_server` FOREIGN KEY (`id_terminal_server`) REFERENCES `terminal_server` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.terminal_server
CREATE TABLE IF NOT EXISTS `terminal_server` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_ip` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_username1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_passw1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_promt1` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_username2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_passw2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_promt2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.users
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
-- Дамп структуры для таблица saveconfig.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
