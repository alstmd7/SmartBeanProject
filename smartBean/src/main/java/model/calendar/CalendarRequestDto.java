package model.calendar;

public class CalendarRequestDto {
	private int no;
	private String email;
	private String name;
	
	
	public CalendarRequestDto(int no, String email, String name) {
		super();
		this.no = no;		// auto
		this.email = email;
		this.name = name;
	}

	public CalendarRequestDto(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}
	
	public CalendarRequestDto(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}



	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
}