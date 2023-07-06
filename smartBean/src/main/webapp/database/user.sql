CREATE TABLE `user`(
	email VARCHAR(50) PRIMARY KEY,
    `password` VARCHAR(20) NOT NULL,
    `name` VARCHAR(20) NOT NULL
);

-- ALTER TABLE `user` MODIFY COLUMN user_email varchar(50);

INSERT INTO `user` VALUES ("qortmdals120@gmail.com", "123", "승민");
INSERT INTO `user` VALUES ("juntu09@gmail.com", "234", "희수");
INSERT INTO `user` VALUES ("lokcdown@gmail.com", "345", "재원");

UPDATE `user` SET `password`='new123', `name`='new승민' WHERE email='qortmdals120@gmail.com';

DELETE FROM `user` WHERE email='qortmdals120@gmail.com' AND `password`='new123';

SELECT * FROM `user`;
SELECT * FROM `user` WHERE email='qortmdals120@gmail.com';

DROP TABLE `user`;