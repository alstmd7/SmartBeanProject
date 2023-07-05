CREATE TABLE `comment` (
	comment_no INT PRIMARY KEY AUTO_INCREMENT,
	user_email VARCHAR(50),
    FOREIGN KEY (user_email) REFERENCES `user`(user_email),
    event_no INT,
    FOREIGN KEY (event_no) REFERENCES `event`(event_no),
    comment_content VARCHAR(1000) NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    reg_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    p_no INT default 0
);

INSERT INTO `comment` (user_email, event_no, comment_content) VALUES ("qortmdals120@gmail.com", 1, "댓글1");

SELECT * FROM `comment`;
SELECT * FROM `comment` WHERE event_no=1;

DELETE FROM `comment` WHERE comment_no=1;

DROP TABLE `comment`;