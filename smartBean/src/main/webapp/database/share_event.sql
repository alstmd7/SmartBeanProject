CREATE TABLE event_share (
	calendar_no INT,
    FOREIGN KEY (calendar_no) REFERENCES calendar(`no`) ON DELETE CASCADE,
    event_no INT,
    FOREIGN KEY (event_no) REFERENCES `event`(`no`) ON DELETE CASCADE
);

SELECT * from event_share WHERE event_no='30';

INSERT INTO event_share VALUES(3, 30);