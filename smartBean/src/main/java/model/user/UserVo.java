package model.user;

public class UserVo {
	private String email;
    private String password;
    private String name;
    
	public UserVo(String email, String password, String name) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
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
