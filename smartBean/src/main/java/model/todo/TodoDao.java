package model.todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import util.DBManager;

public class TodoDao {
	

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

	private TodoDao() {}
	private static TodoDao instance = new TodoDao();
	
	public static TodoDao getInstance() {
		return instance;
	}

	public void checkTodo(int check, int no) {

		this.conn = DBManager.getConnection();

		String sql = "UPDATE todo SET `check`=? WHERE `no`=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, check);
			this.pstmt.setInt(2, no);

			this.pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt);
		}
	}
	
	public TodoVo getTodoByNo(int no) {
		TodoVo todo = null;
		
		this.conn = DBManager.getConnection();
		
		String sql = "SELECT * FROM todo WHERE `no`=?";
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);
			
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				String email = this.rs.getString(2);
				String content = this.rs.getString(3);
				String target_at = sdf.format(this.rs.getDate(4));
				String check = this.rs.getString(5);
				
				todo = new TodoVo(no, email, content, target_at, check);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt, this.rs);
		}
		return todo;
	}
	
	public ArrayList<TodoVo> getTodoAll(String email) {
		ArrayList<TodoVo> list = new ArrayList<TodoVo>();
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM todo WHERE email=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, email);
				
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					int no = this.rs.getInt(1);
					String content = this.rs.getString(3);
					String target_at = sdf.format(this.rs.getDate(4));
					String check = this.rs.getString(5);
					TodoVo todo = new TodoVo(no, email, content, target_at, check);
					list.add(todo);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		
		return list;
	}
	
	public boolean createTodo(TodoRequestDto todo) {
		
		String email = todo.getEmail();
		String content = todo.getContent();
		String target_at = todo.getTarget_at();
		
		boolean check = false;
		
		if(email != null && content != null && target_at != null) {
			this.conn = DBManager.getConnection();
			if(this.conn != null) {
					String sql = "INSERT INTO todo(`email`, content, target_at) VALUES (?, ?, Date(?))";
					
					try {
						this.pstmt = this.conn.prepareStatement(sql);
						this.pstmt.setString(1, email);
						this.pstmt.setString(2, content);
						this.pstmt.setString(3, target_at);
						
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
	
	public void updateTodo(TodoRequestDto todoDto) {
		
		this.conn = DBManager.getConnection();

		String sql = "UPDATE todo SET content=?, target_at=DATE(?) WHERE `no`=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, todoDto.getContent());
			this.pstmt.setString(2, todoDto.getTarget_at());
			this.pstmt.setInt(3, todoDto.getNo());

			this.pstmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt);
		}

	}
	
	public boolean deleteTodoNo(int no) {
		this.conn = DBManager.getConnection();
		
		boolean check = true;
		
		if(this.conn != null) {
			String sql = "DELETE FROM todo WHERE `no`=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, no);
				
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
