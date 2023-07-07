CREATE TABLE share_event (
	event_no INT,
    FOREIGN KEY (event_no) REFERENCES `event`(`no`),
	calendar_no INT,
	FOREIGN KEY (calendar_no) REFERENCES calendar(`no`)
);

INSERT INTO share_event VALUES (1, 2);

SELECT * FROM share_event;
SELECT * FROM share_event WHERE event_no=1;

DELETE FROM share_event WHERE event_no=1;

DROP TABLE share_event;