CREATE TABLE calender (
	cal_no INT PRIMARY KEY AUTO_INCREMENT,
    user_email VARCHAR(50),
    FOREIGN KEY (user_email) REFERENCES `user`(user_email),
    cal_name VARCHAR(20) NOT NULL
);

INSERT INTO calender (user_email, cal_name) VALUES ("qortmdals120@gmail.com", "캘린더1");
INSERT INTO calender (user_email, cal_name) VALUES ("juntu09@gmail.com", "캘린더2");
INSERT INTO calender (user_email, cal_name) VALUES ("lokcdown@gmail.com", "캘린더3");

-- ALTER TABLE calender MODIFY COLUMN user_email varchar(50);
-- ALTER TABLE calender RENAME COLUMN calender_name TO cal_name;

SELECT * FROM calender;
SELECT * FROM calender WHERE user_email='qortmdals120@gmail.com';

UPDATE calender SET cal_name='NEW캘린더1' WHERE user_email='qortmdals120@gmail.com';

DELETE FROM calender WHERE user_email='qortmdals120@gmail.com';

DROP TABLE calender;