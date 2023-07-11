package model.comment;

public class CommentRequestDto {
	private int no;
	private int code;
	private String name;
	private int event_no;
	private String content;
	private int create_at;
	private int reg_at;
	private int p_no;
	
	public CommentRequestDto(int no, int code, String name, int event_no, String content, int create_at, int reg_at,
			int p_no) {
		super();
		this.no = no;
		this.code = code;
		this.name = name;
		this.event_no = event_no;
		this.content = content;
		this.create_at = create_at;
		this.reg_at = reg_at;
		this.p_no = p_no;
	}
	
	public CommentRequestDto(int code, String name, int event_no, String content) {
		super();
		this.code = code;
		this.name = name;
		this.event_no = event_no;
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getEvent_no() {
		return event_no;
	}

	public String getContent() {
		return content;
	}

	public int getCreate_at() {
		return create_at;
	}

	public int getReg_at() {
		return reg_at;
	}

	public int getP_no() {
		return p_no;
	}
	
}
