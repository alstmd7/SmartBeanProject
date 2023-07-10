package model.todo;

public class TodoRequestDto {
	private int no;
	private String email;
	private String content;
	private int target_at;
	private String check;
	
	public TodoRequestDto(int no, String email, String content, int target_at, String check) {
		super();
		this.no = no;
		this.email = email;
		this.content = content;
		this.target_at = target_at;
		this.check = check;
	}
	
	public int getNo() {
		return no;
	}
	public String getEmail() {
		return email;
	}
	public String getContent() {
		return content;
	}
	public int getTarget_at() {
		return target_at;
	}
	public String getCheck() {
		return check;
	}
}
