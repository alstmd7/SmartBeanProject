CREATE TABLE share_calender (
	email VARCHAR(50),
    FOREIGN KEY (email) REFERENCES `user`(email),
    `no` INT,
    FOREIGN KEY (`no`) REFERENCES calender(`no`)
);

INSERT INTO share_calender VALUES ("qortmdals120@gmail.com", 2);

SELECT * FROM share_calender;
SELECT * FROM share_calender WHERE email='qortmdals120@gmail.com';

DELETE FROM share_calender WHERE email='qortmdals120@gmail.com';

DROP TABLE share_calender;