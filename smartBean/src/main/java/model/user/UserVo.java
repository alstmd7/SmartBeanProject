package model.user;

public class UserVo {
	private int code;
	private String email;
    private String password;
    private String name;
    
	public UserVo(int code, String email, String password, String name) {
		super();
		this.code = code;
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}	
	
}
