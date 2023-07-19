package model.event;

public class EventRequestDto {

	private int no;
    private int calendar_no;
    private int task_no;
    private String name;
	private String email;
    private String title;
    private String content;
    private String start;
    private String end;
    private String all_day;
    
	public EventRequestDto(int no, int calendar_no, int task_no, String name, String email, String title,
			String content, String start, String end, String all_day) {
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
	
	// 이벤트 드랍하자마자 쓸거
	public EventRequestDto(int calendar_no, int task_no, String name, String email, String title,
			 String start, String end) {
		super();
		this.calendar_no = calendar_no;
		this.task_no = task_no;
		this.name = name;
		this.email = email;
		this.title = title;
		this.start = start;
		this.end = end;
	}
	
	public EventRequestDto(int calendar_no, int task_no, String name, String email, String title,
			String content, String start, String end, String all_day) {
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
			String content, String start, String end, String all_day) {
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

	public String getStart() {
		return start;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setCalendar_no(int calendar_no) {
		this.calendar_no = calendar_no;
	}

	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnd() {
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

	public void setStart(String start) {
		this.start = start;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public void setAll_day(String all_day) {
		this.all_day = all_day;
	}
		
}
