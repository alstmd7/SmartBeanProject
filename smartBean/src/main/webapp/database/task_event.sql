CREATE TABLE task_event (
	event_title VARCHAR(20) PRIMARY KEY
);

INSERT INTO task_event VALUES ("회의1");
SELECT * FROM task_event;

DROP TABLE task_event;