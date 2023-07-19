package model.shareEvent;

public class ShareEventVo {
	private int calendar_no;
	private int event_no;
	
	public ShareEventVo(int calendar_no, int event_no) {
		super();
		this.calendar_no = calendar_no;
		this.event_no = event_no;
	}
	
	public int getCalendar_no() {
		return calendar_no;
	}
	public int getEvent_no() {
		return event_no;
	}
	
}
