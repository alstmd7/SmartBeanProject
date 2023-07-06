CREATE TABLE calender (
	`no` INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50),
    FOREIGN KEY (email) REFERENCES `user`(email),
    `name` VARCHAR(20) NOT NULL
);

INSERT INTO calender (email, `name`) VALUES ("qortmdals120@gmail.com", "캘린더1");
INSERT INTO calender (email, `name`) VALUES ("juntu09@gmail.com", "캘린더2");
INSERT INTO calender (email, `name`) VALUES ("lokcdown@gmail.com", "캘린더3");

-- ALTER TABLE calender MODIFY COLUMN user_email varchar(50);
-- ALTER TABLE calender RENAME COLUMN calender_name TO cal_name;

SELECT * FROM calender;
SELECT * FROM calender WHERE email='qortmdals120@gmail.com';

UPDATE calender SET `name`='NEW캘린더1' WHERE email='qortmdals120@gmail.com';

DELETE FROM calender WHERE email='qortmdals120@gmail.com';

DROP TABLE calender;