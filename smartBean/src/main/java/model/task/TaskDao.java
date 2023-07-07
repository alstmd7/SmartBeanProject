package model.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBManager;


public class TaskDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private TaskDao() {}
	private static TaskDao instance = new TaskDao();
	
	public static TaskDao getInstance() {
		return instance;
	}
	
	public TaskVo getTaskByName(String name) {
		TaskVo user = null;
		
		this.conn = DBManager.getConnection();
		
		String sql = "SELECT * FROM task WHERE `name`=?";
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, name);
			
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				int task_no = this.rs.getInt(1);
				int no = this.rs.getInt(3);
				
				user = new TaskVo(task_no, name, no);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt, this.rs);
		}
		return user;
	}
	
	public boolean createTask(TaskRequestDto taskDto) {
		TaskVo result = getTaskByName(taskDto.getName());
		
		if(result != null)
			return false;
		
		String name = taskDto.getName();
		int no = taskDto.getNo();
		
		boolean check = true;
		
		if(name != null && no != 0) {
			this.conn = DBManager.getConnection();
			
			if(this.conn != null) {
				String sql = "INSERT INTO task (`name`, `no`) VALUES (?, ?)";

				try {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, name);
					this.pstmt.setInt(2, no);
					
					this.pstmt.execute();

				} catch (Exception e) {
					e.printStackTrace();
					check = false;
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}
			} else {
				check = false;
			}
		} else {
			check = false;
		}
		
		return check;
	}
	
	public void updateTask(TaskRequestDto taskDto, String name) {
		this.conn = DBManager.getConnection();
		
		if(this.conn != null && taskDto.getName() != null && taskDto.getTask_no() != 0) {
			
			String sql = "UPDATE task SET `name`=? WHERE task_no=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, name);
				this.pstmt.setInt(2, taskDto.getTask_no());

				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
	}
	
	public boolean deleteTaskByName(String name) {
		this.conn = DBManager.getConnection();
		
		boolean check = true;
		
		if(this.conn != null) {
			String sql = "DELETE FROM task WHERE name=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, name);
				
				this.pstmt.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
				check = false;
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
			
		} else {
			check = false;
		}
		
		return check;
	}
}
