CREATE TABLE `event` (
	`no` INT PRIMARY KEY AUTO_INCREMENT,
    calendar_no INT,
    FOREIGN KEY (calendar_no) REFERENCES calendar(`no`) ON DELETE CASCADE,
    task_no INT,
    FOREIGN KEY (task_no) REFERENCES task(`no`) ON DELETE SET NULL,
	`name` VARCHAR(20) NOT NULL,
	email VARCHAR(50),
    FOREIGN KEY (email) REFERENCES `user`(email) ON DELETE SET NULL,
    title VARCHAR(20) NOT NULL,
    content VARCHAR(400),
    `start` DATE NOT NULL,
    `end` DATE NOT NULL,
    all_day CHAR(1) NOT NULL DEFAULT 0
);

INSERT INTO `event` (calendar_no, task_no, `name`, email, title, content, `start`, `end`) 
VALUES (3, 2, "휴가", "qortmdals120@gmail.com", "제목", "내용", DATE('2023-07-05 09:00'), DATE('2023-07-06 12:00'));

SELECT * FROM `event` WHERE calendar_no=3;

UPDATE `event` SET title='NEW제목', content="NEW내용", 
`start`=DATE('2023-07-05 10:00'), `end`= DATE('2023-07-06 13:00'), all_day=1 WHERE `no`=1;

DELETE FROM `event` WHERE `no`=1;

DROP TABLE `event`;