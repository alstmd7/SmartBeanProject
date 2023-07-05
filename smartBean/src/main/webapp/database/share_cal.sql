CREATE TABLE share_cal (
	user_email VARCHAR(50),
    FOREIGN KEY (user_email) REFERENCES `user`(user_email),
    cal_no INT,
    FOREIGN KEY (cal_no) REFERENCES calender(cal_no)
);

INSERT INTO share_cal VALUES ("qortmdals120@gmail.com", 2);

SELECT * FROM share_cal;
SELECT * FROM share_cal WHERE user_email='qortmdals120@gmail.com';

DELETE FROM share_cal WHERE user_email='qortmdals120@gmail.com';