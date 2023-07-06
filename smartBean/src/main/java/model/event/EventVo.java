package model.event;

public class EventVo {
	
	private int no;
	private String name;
	private String email;
	private String title ;
	private String content; 
	private String start ;
	private String end; 
	private char all_day; 
	
	public EventVo(int no,String name, String email, String title,String content,String start,String end,char all_day) {
		super();
		this.no = no;
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

	public String getEnd() {
		return end;
	}

	public char getAll_day() {
		return all_day;
	}

	
	
}
