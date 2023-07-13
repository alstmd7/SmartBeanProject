package model.share;

public class ShareVo {
	private String email;
	private int no;
	private String name;
	
	public ShareVo(String email, int no) {
		super();
		this.email = email;
		this.no = no;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getNo() {
		return no;
	}
}
