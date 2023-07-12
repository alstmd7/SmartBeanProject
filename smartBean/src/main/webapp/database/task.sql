-- task 테이블 생성
CREATE TABLE task (
	`no` INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50),
    FOREIGN KEY (email) REFERENCES `user`(email) ON DELETE CASCADE,
	`name` VARCHAR(20) NOT NULL
);

-- ALTER  TABLE  task  DROP  INDEX  `name`;

-- task 테이블에 값 삽입
INSERT INTO task (email, `name`) VALUES ("qortmdals120@gmail.com", "휴가");

-- task 테이블 확인
SELECT * FROM task WHERE email="qortmdals120@gmail.com";
SELECT `name` FROM task WHERE email="qortmdals120@gmail.com";

-- task 테이블의 칼럼 값 변경
UPDATE task SET `name`='NEW휴가' WHERE email="qortmdals120@gmail.com" AND `name`='휴가';

-- task name 삭제 
DELETE FROM task WHERE email="qortmdals120@gmail.com" AND `name`='NEW휴가';

-- task 테이블 제거
DROP TABLE task;