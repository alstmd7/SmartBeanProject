package model.calendar;

// Java Bean 정의 (VO)
public class CalendarVo {
    private int no;
    private int code;
    private String email;
    private String name;
    private String owner;
    
    public CalendarVo(int no, int code, String email, String name) {
        this.no = no;
        this.code = code;
        this.email = email;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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
    
    public String getOwner() {
    	return email;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("No: %d\nCode: %d\nEmail: %s\nName: %s", this.no, this.code, this.email, this.name);
    }
}
