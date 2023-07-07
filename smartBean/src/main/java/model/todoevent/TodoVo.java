package model.todoevent;

public class TodoVo {

	private int no;
	private String content;
	private String check;
	
	public TodoVo(int no,String content,String check) {
		
		this.no = no;
		this.content = content;
		this.check = check;
	}

	public int getNo() {
		return no;
	}

	public String getContent() {
		return content;
	}

	public String getCheck() {
		return check;
	}
	
	
	
	
	
	
	
	
	
}
