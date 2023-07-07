CREATE TABLE share_calendar (
	email VARCHAR(50),
    FOREIGN KEY (email) REFERENCES `user`(email),
    `no` INT,
    FOREIGN KEY (`no`) REFERENCES calendar(`no`)
);

-- ALTER TABLE share_calender RENAME TO share_calendar;
INSERT INTO share_calendar VALUES ("qortmdals120@gmail.com", 1);
INSERT INTO share_calendar VALUES ("juntu09@gmail.com", 1);
INSERT INTO share_calendar VALUES ("lokcdown@gmail.com", 1);

SELECT * FROM share_calendar;
SELECT * FROM share_calendar WHERE email='qortmdals120@gmail.com';

DELETE FROM share_calendar WHERE email='qortmdals120@gmail.com';

DROP TABLE share_calendar;