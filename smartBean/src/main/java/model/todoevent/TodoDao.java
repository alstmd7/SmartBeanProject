package model.todoevent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.user.UserDao;
import model.user.UserRequestDto;
import model.user.UserVo;
import util.DBManager;

public class TodoDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private TodoDao() {}
	private static TodoDao instance = new TodoDao();
	
	public static TodoDao getInstance() {
		return instance;
	}
	
	
	
	public TodoVo getUserById(int no) {
		TodoVo todo = null;
		
		this.conn = DBManager.getConnection();
		
		String sql = "SELECT * FROM todo WHERE `no`=?";
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);
			
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				String content = this.rs.getString(2);
				String check = this.rs.getString(3);
				
				todo = new TodoVo(no, content, check);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt, this.rs);
		}
		return todo;
	}
	
	
	
	
	
	
	
	
	
	
}
