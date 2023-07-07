package model.task;

public class TaskRequestDto {
	private int task_no;
	private String name;
    private int no;
    
	public TaskRequestDto(int task_no, String name, int no) {
		super();
		this.task_no = task_no;
		this.name = name;
		this.no = no;
	}

	public int getTask_no() {
		return task_no;
	}

	public String getName() {
		return name;
	}

	public int getNo() {
		return no;
	}

	public void setName(String name) {
		this.name = name;
	}

}
