package todoevent;

public class TodoRequestDto {

	
	private int no;
	private String content;
	private char check;
	
	public TodoRequestDto(int no,String content,char check) {
		super();
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

	public char getCheck() {
		return check;
	}
	
	

	public void setContent(String content) {
		this.content = content;
	}

	public void setCheck(char check) {
		this.check = check;
	}
	
	
	
	
}
