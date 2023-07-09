package model.task;

public class TaskRequestDto {
	private int no;
	private int calendar_no;
	private String name;
	
	public TaskRequestDto(int no, int calendar_no, String name) {
		super();
		this.no = no;
		this.calendar_no = calendar_no;
		this.name = name;
	}
    
	public TaskRequestDto(int calendar_no, String name) {
		super();
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

	public void setName(String name) {
		this.name = name;
	}
	
	

}
