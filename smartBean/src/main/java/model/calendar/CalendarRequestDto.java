package model.calendar;

public class CalendarRequestDto {
	private int no;
    private int code;
    private String email;
    private String name;
    private int p_code;

    public CalendarRequestDto(int code, String email, String name, int p_code) {
		super();
		this.code = code;
		this.email = email;
		this.name = name;
		this.p_code = p_code;
	}

	public CalendarRequestDto(int code, String email, String name) {
        this.code = code;
        this.email = email;
        this.name = name;
    }
    
    public CalendarRequestDto(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getP_code() {
        return p_code;
    }
}
