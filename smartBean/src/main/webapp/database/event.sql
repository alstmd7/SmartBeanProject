CREATE TABLE `event` (
	`no` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(20),
    FOREIGN KEY (`name`) REFERENCES task(`name`),
	email VARCHAR(50),
    FOREIGN KEY (email) REFERENCES `user`(email),
    title VARCHAR(20),
    content VARCHAR(400),
    `start` DATE NOT NULL,
    `end` DATE NOT NULL,
    all_day CHAR(1) NOT NULL DEFAULT 0
);

INSERT INTO `event` (`name`, email, title, content, `start`, `end`) 
VALUES ("회의", "qortmdals120@gmail.com", "회의제목", "회의내용", DATE('2023-07-05 10:55:00'), DATE('2023-07-06 12:00:00'));

INSERT INTO `event` (`name`, email, title, content, `start`, `end`) 
VALUES ("회의", "lokcdown@gmail.com", "회의제목", "회의내용", DATE('2023-07-05 10:55:00'), DATE('2023-07-06 12:00:00'));

INSERT INTO `event` (`name`, email, title, content, `start`, `end`) 
VALUES ("휴가", "qortmdals120@gmail.com", "회의제목", "회의내용", DATE('2023-07-05 10:55:00'), DATE('2023-07-06 12:00:00'));

SELECT * FROM `event`;
SELECT * FROM `event` WHERE email='qortmdals120@gmail.com';

UPDATE `event` SET title='NEW회의제목', content="NEW회의내용", 
`start`=DATE('2023-07-05 15:55:00'), `end`= DATE('2023-07-07 12:00:00'), all_day=1 WHERE `no`=1;

DELETE FROM `event` WHERE `no`=1;

DROP TABLE `event`;