create table todo(
`no` INT PRIMARY KEY AUTO_INCREMENT,
`email` VARCHAR(50),
FOREIGN KEY (`email`) REFERENCES `user`(`email`) ON DELETE CASCADE,
content VARCHAR(100) NOT NULL,
target_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`check` CHAR(1) NOT NULL DEFAULT 0
);
 
-- #5. 테이블에 데이터 삽입하기(insert)
INSERT INTO todo(`email`, content) VALUES ("qortmdals120@gmail.com", 'todo1');

-- #4. 테이블 만들어졌는지 확인하기
SELECT * FROM todo;
SELECT * FROM todo WHERE email="qortmdals120@gmail.com";

UPDATE todo SET content='NEW내용', target_at=DATE('2023-07-11'), `check`=1 WHERE `no`=1;

DELETE FROM todo WHERE `no`=1;

DROP TABLE todo;