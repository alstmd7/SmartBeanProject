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
	
	public TaskVo getTaskByName(int calendar_no, String name) {
		TaskVo task = null;
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM task WHERE calendar_no=? AND `name`=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, calendar_no);
				this.pstmt.setString(2, name);
				
				this.rs = this.pstmt.executeQuery();
				
				if(this.rs.next()) {
					int no = this.rs.getInt(1);
					
					task = new TaskVo(no, calendar_no, name);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		
		return task;
	}
	
	public ArrayList<String> getTaskAll(int calendar_no) {
		ArrayList<String> task = new ArrayList<String>();
		
		this.conn = DBManager.getConnection();
		
		String sql = "SELECT `name` FROM task WHERE calendar_no=?";
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, calendar_no);
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				String name = this.rs.getString(1);
				
				task.add(name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt, this.rs);
		}
		
		return task;
	}
	
	public boolean createTask(int calendar_no, String name) {
		
		TaskVo result = getTaskByName(calendar_no, name);
		
		if(result != null)
			return false;
		
		boolean check = true;
		
		if(calendar_no != 0 && name != null) {
			this.conn = DBManager.getConnection();
			
			if(this.conn != null) {
				String sql = "INSERT INTO task (calendar_no, `name`) VALUES (?, ?)";

				try {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setInt(1, calendar_no);
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
		
		if(this.conn != null && taskDto.getNo() != 0) {
			String sql = "UPDATE task SET `name`=? WHERE `no`=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, name);
				this.pstmt.setInt(2, taskDto.getNo());

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
	
	public boolean deleteTaskByName(int calendar_no, String name) {
		
		this.conn = DBManager.getConnection();

		boolean check = true;

		if (this.conn != null) {
			String sql = "DELETE FROM task WHERE calendar_no=? AND name=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, calendar_no);
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
