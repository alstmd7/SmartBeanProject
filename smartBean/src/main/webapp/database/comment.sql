CREATE TABLE `comment` (
	`no` INT PRIMARY KEY AUTO_INCREMENT,
    `code` INT,
    FOREIGN KEY (`code`) REFERENCES `user`(`code`) ON DELETE CASCADE,
    `name` VARCHAR(20) NOT NULL,
    event_no INT,
    FOREIGN KEY (event_no) REFERENCES `event`(`no`) ON DELETE CASCADE,
    content VARCHAR(1000) NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    reg_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    p_no INT
);

INSERT INTO `comment` (`code`, `name`, event_no, content) VALUES (1, "승민", 1, "댓글내용2");
INSERT INTO `comment` (`code`, `name`, event_no, content, p_no) VALUES (1, "승민", 1, "댓글내용3", 1);

SELECT * FROM `comment`;
SELECT * FROM `comment` WHERE event_no=1;

UPDATE `comment` SET `content`="new댓글내용" WHERE `no`=2;

DELETE FROM `comment` WHERE no=1;

DROP TABLE `comment`;