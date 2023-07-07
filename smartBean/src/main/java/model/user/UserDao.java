
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
				String password = this.rs.getString(2);
				String name = this.rs.getString(3);
				
				user = new UserVo(email, password, name);
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
				String sql = "INSERT INTO `user` VALUES (?, ?, ?)";

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
	
	public boolean updateUser(UserRequestDto userDto, String new_password) {
		this.conn = DBManager.getConnection();
		
		boolean check = true;
		if(this.conn != null && userDto.getEmail() != null && userDto.getPassword() != null && userDto.getName() != null) {
			
			if(new_password != "") {
				String sql = "UPDATE `user` SET `password`=?, `name`=? WHERE email=?";

				try {
					this.pstmt = this.conn.prepareStatement(sql);
					this.pstmt.setString(1, new_password);
					this.pstmt.setString(2, userDto.getName());
					this.pstmt.setString(3, userDto.getEmail());

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
					this.pstmt.setString(1, userDto.getName());
					this.pstmt.setString(2, userDto.getEmail());

					this.pstmt.execute();

				} catch (Exception e) {
					e.printStackTrace();
					check = false;
				} finally {
					DBManager.close(this.conn, this.pstmt);
				}
				
			}
		} else {
			check = false;
		}
		
		return check;
	}
	
	public boolean deleteUserByEmail(String email) {
		this.conn = DBManager.getConnection();
		
		boolean check = true;
		// 삭제 시 외래키 연결 문제
		if(this.conn != null) {
			String sql = "DELETE FROM `user` WHERE email=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, email);
				
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
