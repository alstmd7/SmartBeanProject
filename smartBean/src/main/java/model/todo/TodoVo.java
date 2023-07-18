package model.todo;

public class TodoVo {
	private int no;
	private String email;
	private String content;
	private String target_at;
	private String check;
	
	public TodoVo(int no, String email, String content, String target_at, String check) {
		super();
		this.no = no;
		this.email = email;
		this.content = content;
		this.target_at = target_at;
		this.check = check;
	}
	
	public TodoVo(int no, String content, String target_at, String check) {
		super();
		this.no = no;
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
	public String getTarget_at() {
		return target_at;
	}
	public String getCheck() {
		return check;
	}
	
}
