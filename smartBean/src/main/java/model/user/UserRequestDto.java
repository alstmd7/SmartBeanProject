package model.user;

public class UserRequestDto {
	private String email;
    private String password;
    private String name;
    
    public UserRequestDto(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}
    
	public UserRequestDto(String email, String password, String name) {
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

	public void SetPassword(String password) {
		this.password = password;
	}
    
	public void setName(String name) {
		this.name = name;
	}
}
