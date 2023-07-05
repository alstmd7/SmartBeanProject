CREATE TABLE `user`(
	user_email VARCHAR(50) PRIMARY KEY,
    user_password VARCHAR(20) NOT NULL,
    user_name VARCHAR(20) NOT NULL
);

-- ALTER TABLE `user` MODIFY COLUMN user_email varchar(50);

INSERT INTO `user` VALUES ("qortmdals120@gmail.com", "123", "승민");
INSERT INTO `user` VALUES ("juntu09@gmail.com", "234", "희수");
INSERT INTO `user` VALUES ("lokcdown@gmail.com", "345", "재원");

UPDATE `user` SET user_name='민승' WHERE user_email='qortmdals120@gmail.com';

DELETE FROM `user` WHERE user_email='qortmdals120@gmail.com';

SELECT * FROM `user`;
SELECT * FROM `user` WHERE user_email='qortmdals120@gmail.com';

DROP TABLE `user`;