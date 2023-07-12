package model.task;

public class TaskVo {
	private int no;
	private String email;
	private String name;
	
	public TaskVo(int no, String email, String name) {
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
	
}
