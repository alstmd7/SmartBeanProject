-- task 테이블 생성
CREATE TABLE task (
	`no` INT PRIMARY KEY AUTO_INCREMENT,
    calendar_no INT,
    FOREIGN KEY (calendar_no) REFERENCES calendar(`no`) ON DELETE CASCADE,
	`name` VARCHAR(20) NOT NULL
);

-- task 테이블에 값 삽입
INSERT INTO task (calendar_no, `name`) VALUES (3, "휴가");

-- task 테이블 확인
SELECT * FROM task;
SELECT * FROM task WHERE calendar_no=3 AND `name`="휴가";
SELECT `name` FROM task WHERE calendar_no=3;

-- task 테이블의 칼럼 값 변경
UPDATE task SET `name`='NEW휴가' WHERE `no`=1;

-- task name이 'new회의'면 모두 삭제
DELETE FROM task WHERE calendar_no=7 AND name='휴가';

-- task 테이블 제거
DROP TABLE task;