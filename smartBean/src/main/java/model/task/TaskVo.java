package model.task;

public class TaskVo {
	private int no;
	private int calendar_no;
	private String name;
	
	public TaskVo(int no, int calendar_no, String name) {
		super();
		this.no = no;
		this.calendar_no = calendar_no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public int getCalendar_no() {
		return calendar_no;
	}

	public String getName() {
		return name;
	}
    
}
