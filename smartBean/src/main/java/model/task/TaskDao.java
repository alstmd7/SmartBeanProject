package model.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	public TaskVo getTaskByName(String email, String name) {
		TaskVo task = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM task WHERE email=? AND `name`=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, email);
				this.pstmt.setString(2, name);
				
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()) {
					int no = this.rs.getInt(1);
					
					task = new TaskVo(no, email, name);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		
		return task;
	}
	
	public ArrayList<TaskVo> getTaskAll(String email) {
		ArrayList<TaskVo> list = new ArrayList<TaskVo>();
		
		this.conn = DBManager.getConnection();
		
		String sql = "SELECT `name` FROM task WHERE email=?";
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, email);
			this.rs = this.pstmt.executeQuery();
			
			while(this.rs.next()) {
				int no = this.rs.getInt(1);
				String name = this.rs.getString(3);
				
				TaskVo taskVo = new TaskVo(no, email, name);
				
				list.add(taskVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt, this.rs);
		}
		
		return list;
	}
	
	public boolean createTask(String email, String name) {
		
		TaskVo result = getTaskByName(email, name);
		
		if(result != null)
			return false;
		
		boolean check = true;
		
		if(email != null && name != null) {
			this.conn = DBManager.getConnection();
			
			if(this.conn != null) {
				String sql = "INSERT INTO task (email, `name`) VALUES (?, ?)";

				try {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, email);
					this.pstmt.setString(2, name);
					
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
	
	public boolean updateTask(TaskRequestDto taskDto, String name) {
		this.conn = DBManager.getConnection();
		
		boolean check = true;
		
		if(this.conn != null) {
			String sql = "UPDATE task SET `name`=? WHERE email=? AND `name`=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, name);
				this.pstmt.setString(2, taskDto.getEmail());
				this.pstmt.setString(3, taskDto.getName());

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
	
	public boolean deleteTaskByName(String email, String name) {
		
		this.conn = DBManager.getConnection();

		boolean check = true;

		if (this.conn != null) {
			String sql = "DELETE FROM task WHERE email=? AND `name`=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, email);
				this.pstmt.setString(2, name);

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
