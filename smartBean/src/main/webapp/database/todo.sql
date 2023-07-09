create table todo(
`no` INT PRIMARY KEY AUTO_INCREMENT,
`code` INT,
FOREIGN KEY (`code`) REFERENCES `user`(`code`) ON DELETE CASCADE,
content VARCHAR(50) NOT NULL,
`check` CHAR(1) NOT NULL DEFAULT 0
);
 
-- #5. 테이블에 데이터 삽입하기(insert)
INSERT INTO todo(`code`, content) VALUES (1, 'todo1');

-- #4. 테이블 만들어졌는지 확인하기
SELECT * FROM todo;
SELECT * FROM todo WHERE `code`=1;

UPDATE todo SET content='NEW내용', `check`=1 WHERE `no`=1;

DELETE FROM todo WHERE `no`=1;

DROP TABLE todo;