package todoevent;

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
	
	
	
	
	public boolean createTodo(TodoRequestDto todoDto) {
		TodoVo result = getUserById(todoDto.getNo());
		
		if(result != null)
			return false;
		
		String email = todoDto.getNo();
		String password = todoDto.getContent();
		String name = todoDto.getCheck();
		
		boolean check = true;
		
		if(email != null && password != null && name != null) {
			this.conn = DBManager.getConnection();
			
			if(this.conn != null) {
				String sql = "INSERT INTO `user` VALUES (?, ?, ?);";

				try {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, email);
					this.pstmt.setString(2, password);
					this.pstmt.setString(3, name);

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
	
	
	
	
	
	
	
	
	
	
}
