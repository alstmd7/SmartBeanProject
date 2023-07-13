package model.event;

import java.sql.Date;

public class EventVo {
	
	private int no;
    private int calendar_no;
    private int task_no;
    private String name;
	private String email;
    private String title;
    private String content;
    private Date start; // 수정된 부분
    private Date end; // 수정된 부분
    private String all_day;
    
    public EventVo(int no, int calendar_no, int task_no, String name, String email, String title, String content,
            Date start, Date end, String all_day) {
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

	public Date getStart() { // 수정된 부분
		return start;
	}

	public Date getEnd() { // 수정된 부분
		return end;
	}

	public String getAll_day() {
		return all_day;
	}
	
}
