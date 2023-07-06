create table todo(
`no` INT PRIMARY KEY AUTO_INCREMENT,
content VARCHAR(50) NOT NULL,
`check` CHAR(1) NOT NULL DEFAULT 0
);
 
-- #4. 테이블 만들어졌는지 확인하기
select * from todo;

-- #5. 테이블에 데이터 삽입하기(insert)
insert into todo(content) values ('내용1');

UPDATE todo SET content='내용2', `check`= 1 WHERE `no` = 1;

DELETE FROM todo WHERE `no` = 1 ;