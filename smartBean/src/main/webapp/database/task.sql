-- task 테이블 생성
CREATE TABLE task (
	task_no INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(20) NOT NULL unique,
    `no` INT,
    FOREIGN KEY (`no`) REFERENCES calendar(`no`)
);

-- task 테이블에 값 삽입
INSERT INTO task (`name`, `no`) VALUES ("회의", 1);
INSERT INTO task (`name`, `no`) VALUES ("휴가", 1);
INSERT INTO task (`name`, `no`) VALUES ("업무", 1);

-- task 테이블 확인
SELECT * FROM task WHERE `name`="회의";
SELECT * FROM task;

-- task 테이블의 칼럼 값 변경
UPDATE task SET `name`='NEW회의' WHERE task_no=1;

-- task name이 'new회의'면 모두 삭제
DELETE FROM task WHERE name='NEW회의';

-- task 테이블 제거
DROP TABLE task;