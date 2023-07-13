CREATE TABLE `event` (
  `no` int NOT NULL AUTO_INCREMENT,
  `calendar_no` int DEFAULT NULL,
  `task_no` int DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `title` varchar(20) NOT NULL,
  `content` varchar(400) DEFAULT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `all_day` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`no`),
  KEY `calendar_no` (`calendar_no`),
  KEY `task_no` (`task_no`),
  KEY `email` (`email`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`calendar_no`) REFERENCES `calendar` (`no`) ON DELETE CASCADE,
  CONSTRAINT `event_ibfk_2` FOREIGN KEY (`task_no`) REFERENCES `task` (`no`) ON DELETE SET NULL,
  CONSTRAINT `event_ibfk_3` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE SET NULL
);