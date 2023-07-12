package model.task;

public class TaskRequestDto {
	private int no;
	private String email;
	private String name;
	
	public TaskRequestDto(String email, String name) {
		super();
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
