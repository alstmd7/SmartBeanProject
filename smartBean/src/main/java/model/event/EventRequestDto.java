package model.event;

public class EventRequestDto {

	private int no;
    private int calendar_no;
    private int task_no;
    private String name;
	private String email;
    private String title;
    private String content;
    private int start;
    private int end;
    private String all_day;
    
	public EventRequestDto(int no, int calendar_no, int task_no, String name, String email, String title,
			String content, int start, int end, String all_day) {
		super();
		this.no = no;
		this.calendar_no = calendar_no;
		this.task_no = task_no;
		this.name = name;
		this.email = email;
		this.title = title;
		this.content = content;
		this.start = start;
		this.end = end;
		this.all_day = all_day;
	}
	
	public EventRequestDto(int calendar_no, int task_no, String name, String email, String title,
			String content, int start, int end, String all_day) {
		super();
		this.calendar_no = calendar_no;
		this.task_no = task_no;
		this.name = name;
		this.email = email;
		this.title = title;
		this.content = content;
		this.start = start;
		this.end = end;
		this.all_day = all_day;
	}
	
	public EventRequestDto(String name, String title,
			String content, int start, int end, String all_day) {
		super();
		this.name = name;
		this.title = title;
		this.content = content;
		this.start = start;
		this.end = end;
		this.all_day = all_day;
	}

	public int getNo() {
		return no;
	}

	public int getCalendar_no() {
		return calendar_no;
	}

	public int getTask_no() {
		return task_no;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public String getAll_day() {
		return all_day;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setAll_day(String all_day) {
		this.all_day = all_day;
	}
		
}
