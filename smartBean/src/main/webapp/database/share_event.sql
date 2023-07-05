CREATE TABLE share_event (
	cal_no INT,
	FOREIGN KEY (cal_no) REFERENCES calender(cal_no),
    event_no INT,
    FOREIGN KEY (event_no) REFERENCES `event`(event_no)
);

INSERT INTO share_event VALUES (2, 1);

SELECT * FROM share_event;
SELECT * FROM share_event WHERE event_no=1;

DELETE FROM share_event WHERE event_no=1;

DROP TABLE share_event;