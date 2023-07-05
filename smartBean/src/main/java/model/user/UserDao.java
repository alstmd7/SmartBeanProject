package model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBManager;

public class UserDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private UserDao() {}
	private static UserDao instance = new UserDao();
	
	public static UserDao getInstance() {
		return instance;
	}
	
	public UserVo getUserById(String user_email) {
		UserVo user = null;
		
		this.conn = DBManager.getConnection();
		
		String sql = "SELECT * FROM `user` WHERE user_email=?";
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user_email);
			
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				String user_password = this.rs.getString(2);
				String user_name = this.rs.getString(3);
				
				user = new UserVo(user_email, user_password, user_name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt, this.rs);
		}
		return user;
	}
	
	public boolean createUser(UserRequestDto userDto) {
		UserVo result = getUserById(userDto.getUser_email());
		
		if(result != null)
			return false;
		
		String user_email = userDto.getUser_email();
		String password = userDto.getUser_password();
		String user_name = userDto.getUser_name();
		
		boolean check = true;
		
		if(user_email != null && password != null && user_name != null) {
			this.conn = DBManager.getConnection();
			
			if(this.conn != null) {
				String sql = "INSERT INTO `user` VALUES (?, ?, ?)";

				try {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, user_email);
					this.pstmt.setString(2, password);
					this.pstmt.setString(3, user_name);

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
	
	public void updateUser(UserRequestDto userDto, String name, String password) {
		this.conn = DBManager.getConnection();
		
		if(this.conn != null && userDto.getUser_email() != null && userDto.getUser_password() != null && userDto.getUser_name() != null) {
			
			String sql = "UPDATE `user` SET user_name=?, user_password=? WHERE user_email=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, name);
				this.pstmt.setString(2, password);
				this.pstmt.setString(3, userDto.getUser_email());

				this.pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
		}
	}
}
