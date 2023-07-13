CREATE TABLE share_calendar (
	email VARCHAR(50),
    FOREIGN KEY (email) REFERENCES `user`(email) ON DELETE CASCADE,
    `no` INT,
    FOREIGN KEY (`no`) REFERENCES calendar(`no`) ON DELETE CASCADE
);

INSERT INTO share_calendar VALUES ("test2@gmail.com", 3);

SELECT * FROM share_calendar;
SELECT * FROM share_calendar WHERE email='qortmdals120@gmail.com';
SELECT * FROM share_calendar WHERE `no`=3;

DELETE FROM share_calendar WHERE email='qortmdals120@gmail.com';

DROP TABLE share_calendar;