CREATE TABLE share_event (
	event_no INT,
    FOREIGN KEY (event_no) REFERENCES `event`(`no`),
	calender_no INT,
	FOREIGN KEY (calender_no) REFERENCES calender(`no`)
);

INSERT INTO share_event VALUES (1, 2);

SELECT * FROM share_event;
SELECT * FROM share_event WHERE event_no=1;

DELETE FROM share_event WHERE event_no=1;

DROP TABLE share_event;