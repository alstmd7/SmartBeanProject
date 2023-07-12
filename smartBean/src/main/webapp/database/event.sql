
SELECT * FROM `event` WHERE calendar_no=3;
SELECT * FROM `event` WHERE `no`=1;


DELETE FROM `event` WHERE `event_code`=1;

DROP TABLE `event`;

CREATE TABLE `event` (
  `event_code` int NOT NULL AUTO_INCREMENT,
  `event_name` varchar(50) DEFAULT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `allday` tinyint(1) NOT NULL,
  PRIMARY KEY (`event_code`)
);