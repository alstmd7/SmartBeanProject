CREATE TABLE calendar (
	`no` INT PRIMARY KEY AUTO_INCREMENT,
    `code` INT,
    FOREIGN KEY (`code`) REFERENCES `user`(`code`) ON DELETE SET NULL,
    email VARCHAR(50) NOT NULL,
    `name` VARCHAR(20) NOT NULL
);

INSERT INTO calendar (`code`, email, `name`) VALUES (1, "qortmdals120@gmail.com", "캘린더1");

SELECT * FROM calendar;
SELECT * FROM calendar WHERE email="qortmdals120@gmail.com";
SELECT * FROM calendar WHERE p_code=7;

UPDATE calendar SET `name`='NEW캘린더1' WHERE no=2;
UPDATE calendar SET `email`='new@gmail.com' WHERE no=2;


DROP TABLE calendar;ELETE FROM calendar WHERE no=2;