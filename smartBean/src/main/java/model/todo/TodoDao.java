package model.todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import util.DBManager;

public class TodoDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");

	private TodoDao() {}
	private static TodoDao instance = new TodoDao();
	
	public static TodoDao getInstance() {
		return instance;
	}
	
	public ArrayList<TodoVo> getTodoAll(String email, int target_at) {
		ArrayList<TodoVo> list = new ArrayList<TodoVo>();
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM todo WHERE email=? AND target_at=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, email);
				this.pstmt.setInt(2, target_at);
				
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					int no = this.rs.getInt(1);
					String content = this.rs.getString(3);
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
		int target_at = todo.getTarget_at();
		
		boolean check = false;
		
		if(email != null && content != null && target_at != 0) {
			this.conn = DBManager.getConnection();
			if(this.conn != null) {
					String sql = "INSERT INTO todo(`email`, content, target_at) VALUES (?, ?, Date(?))";
					
					try {
						this.pstmt = this.conn.prepareStatement(sql);
						this.pstmt.setString(1, email);
						this.pstmt.setString(2, content);
						this.pstmt.setInt(3, target_at);
						
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
	
	public boolean updateTodo(TodoRequestDto todoDto) {
		
		this.conn = DBManager.getConnection();
		
		boolean check = true;
		
		if(this.conn != null && todoDto.getContent() != null && todoDto.getCheck() != null && todoDto.getNo() != 0) {

			String sql = "UPDATE todo SET content=?, target_at=DATE(?), `check`=? WHERE `no`=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, todoDto.getContent());
				this.pstmt.setInt(2, todoDto.getTarget_at());
				this.pstmt.setString(3, todoDto.getCheck());
				this.pstmt.setInt(4, todoDto.getNo());

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
	
	public boolean deleteUserById(int no) {
		this.conn = DBManager.getConnection();
		
		boolean check = true;
		
		if(this.conn != null) {
			String sql = "DELETE FROM `event` WHERE `no`=?";
			
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
