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
	
	public UserVo getUserByEmail(String email) {
		UserVo user = null;
		
		this.conn = DBManager.getConnection();
		
		String sql = "SELECT * FROM `user` WHERE email=?";
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, email);
			
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				int code = this.rs.getInt(1);
				String password = this.rs.getString(3);
				String name = this.rs.getString(4);
				
				user = new UserVo(code, email, password, name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt, this.rs);
		}
		return user;
	}
	
	public boolean createUser(UserRequestDto userDto) {
		UserVo result = getUserByEmail(userDto.getEmail());
		
		if(result != null)
			return false;
		
		String email = userDto.getEmail();
		String password = userDto.getPassword();
		String name = userDto.getName();
		
		boolean check = true;
		
		if(email != null && password != null && name != null) {
			this.conn = DBManager.getConnection();
			
			if(this.conn != null) {
				String sql = "INSERT INTO `user`(email, `password`, `name`) VALUES (?, ?, ?)";

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
	
	public void updateUser(UserVo user, String new_password) {
		this.conn = DBManager.getConnection();
		
		if(this.conn != null && user.getEmail() != null && user.getPassword() != null && user.getName() != null) {
			
			if(new_password != "") {
				String sql = "UPDATE `user` SET `password`=?, `name`=? WHERE email=?";
				try {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, new_password);
					this.pstmt.setString(2, user.getName());
					this.pstmt.setString(3, user.getEmail());
					this.pstmt.execute();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}
				
			} else {
				
				String sql = "UPDATE `user` SET `name`=? WHERE email=?";
				try {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, user.getName());
					this.pstmt.setString(2, user.getEmail());
					this.pstmt.execute();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}
			}
			
		}
	}
	
	public void deleteUserByEmail(String email) {
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "DELETE FROM `user` WHERE email=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, email);
				
				this.pstmt.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt);
			}
			
		}
	}
	
}
