package model.calendar;

//Java Bean 정의 (VO)
public class CalendarVo {
	private int no;
	private String email;
	private String name;
	
	
	public CalendarVo(int no, String email, String name) {
		super();
		this.no = no;
		this.email = email;
		this.name = name;
	}
	

	public int getNo() {
		return no;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("No: %d\nEmail: %s\nName: %s", this.no, this.email, this.name);
	}
	
}