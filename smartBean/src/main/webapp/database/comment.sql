CREATE TABLE `comment` (
	no INT PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(50),
    FOREIGN KEY (email) REFERENCES `user`(email),
    event_no INT,
    FOREIGN KEY (event_no) REFERENCES `event`(`no`),
    content VARCHAR(1000) NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    reg_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    p_no INT default 0
);

INSERT INTO `comment` (email, event_no, content) VALUES ("qortmdals120@gmail.com", 1, "댓글내용");

SELECT * FROM `comment`;
SELECT * FROM `comment` WHERE event_no=1;

DELETE FROM `comment` WHERE no=1;

DROP TABLE `comment`;