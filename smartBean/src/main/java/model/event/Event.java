package model.event;

public class Event {

	private String event_title;
	private String event_content;
	private String event_start;
	private String event_end;
	private String event_check;
	
	public Event(String event_title,String event_content,String event_start,String event_end,String event_check,String event) {
		this.event_title = event_title;
		this.event_content = event_content;
		this.event_start = event_start;
		this.event_end = event_end;
		this.event_check = event_check;
	}

	public String getEvent_title() {
		return event_title;
	}

	public String getEvent_content() {
		return event_content;
	}

	public String getEvent_start() {
		return event_start;
	}

	public String getEvent_end() {
		return event_end;
	}

	public String getEvent_check() {
		return event_check;
	}
	
	
}























