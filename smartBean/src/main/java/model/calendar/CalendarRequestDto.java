package model.calendar;

public class CalendarRequestDto {
    private int code;
    private String email;
    private String name;

    public CalendarRequestDto(int code, String email, String name) {
        this.code = code;
        this.email = email;
        this.name = name;
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
}
