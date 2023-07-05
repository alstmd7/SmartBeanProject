CREATE TABLE `event` (
	event_no INT PRIMARY KEY AUTO_INCREMENT,
	event_title VARCHAR(20),
    FOREIGN KEY (event_title) REFERENCES task_event(event_title),
	user_email VARCHAR(50),
    FOREIGN KEY (user_email) REFERENCES `user`(user_email),
    event_content VARCHAR(400),
    event_start DATE NOT NULL,
    event_end DATE NOT NULL,
    all_day CHAR(1) DEFAULT 0,
    event_check CHAR(1)
);

-- ALTER TABLE `event` RENAME COLUMN event_name TO event_title;

INSERT INTO `event` (event_title, user_email, event_content, event_start, event_end) 
VALUES ("회의1", "qortmdals120@gmail.com", "회의상세내용", DATE('2023-07-05 10:55:00'), DATE('2023-07-06 12:00:00'));

SELECT * FROM `event`;
SELECT * FROM `event` WHERE user_email='qortmdals120@gmail.com';

DELETE FROM `event` WHERE event_no=1;

DROP TABLE `event`;