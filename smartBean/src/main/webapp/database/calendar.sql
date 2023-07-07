CREATE TABLE calendar (
	`no` INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50),
    FOREIGN KEY (email) REFERENCES `user`(email),
    `name` VARCHAR(20) NOT NULL
);

INSERT INTO calendar (email, `name`) VALUES ("qortmdals120@gmail.com", "캘린더1");
INSERT INTO calendar (email, `name`) VALUES ("juntu09@gmail.com", "캘린더2");
INSERT INTO calendar (email, `name`) VALUES ("lokcdown@gmail.com", "캘린더3");

-- ALTER TABLE calender MODIFY COLUMN user_email varchar(50);
ALTER TABLE calendar RENAME TO calendar;

SELECT * FROM calendar;
SELECT * FROM calendar WHERE email='qortmdals120@gmail.com';

UPDATE calendar SET `name`='NEW캘린더1' WHERE email='qortmdals120@gmail.com';

DELETE FROM calendar WHERE email='qortmdals120@gmail.com';

DROP TABLE calendar;