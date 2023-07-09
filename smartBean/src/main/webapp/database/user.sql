CREATE TABLE `user`(
	`code` INT PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(50) UNIQUE NOT NULL,
    `password` VARCHAR(20) NOT NULL,
    `name` VARCHAR(20) NOT NULL
);

INSERT INTO `user`(email, `password`, `name`) VALUES ("qortmdals120@gmail.com", "123", "승민");

SELECT * FROM `user`;
SELECT * FROM `user` WHERE email='qortmdals120@gmail.com';

UPDATE `user` SET `password`='new123', `name`='new승민' WHERE email='qortmdals120@gmail.com';

DELETE FROM `user` WHERE email='qortmdals120@gmail.com';

DROP TABLE `user`;