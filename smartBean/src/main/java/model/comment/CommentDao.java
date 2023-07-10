package model.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import util.DBManager;

public class CommentDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");

	private CommentDao() {}
	private static CommentDao instance = new CommentDao();
	
	public static CommentDao getInstance() {
		return instance;
	}
	
	public ArrayList<CommentVo> getCommentAll(int event_no) {
		ArrayList<CommentVo> list = new ArrayList<CommentVo>();
		
		this.conn = DBManager.getConnection();
		
		if(this.conn != null) {
			String sql = "SELECT * FROM `comment` WHERE event_no=?";
			
			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, event_no);
				
				this.rs = this.pstmt.executeQuery();
				
				while(this.rs.next()) {
					int no = this.rs.getInt(1);
					int code = this.rs.getInt(2);
					String name = this.rs.getString(3);
					String content = this.rs.getString(5);
					Date create_at = this.rs.getDate(6);
					int create_atNum = Integer.parseInt(sdf.format(create_at));
					Date reg_at = this.rs.getDate(7);
					int reg_atNum = Integer.parseInt(sdf.format(reg_at));
					int p_no = this.rs.getInt(8);
					
					CommentVo comment = new CommentVo(no, code, name, event_no, content, create_atNum, reg_atNum, p_no);
					list.add(comment);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}
		
		return list;
	}
	
	public boolean createComment(CommentRequestDto commentDto) {
		
		int code = commentDto.getCode();
		String name = commentDto.getName();
		int event_no = commentDto.getEvent_no();
		String content = commentDto.getContent();
		int p_no = commentDto.getP_no();
		
		boolean check = true;
		
		if(code != 0 && name != null && event_no != 0 && content != null) {
			this.conn = DBManager.getConnection();
			
			if(this.conn != null) {
				
				if(p_no != 0) {
					String sql = "INSERT INTO `comment` (`code`, `name`, event_no, content) VALUES (?, ?, ?, ?)";
					
					try {
						this.pstmt = this.conn.prepareStatement(sql);
						this.pstmt.setInt(1, code);
						this.pstmt.setString(2, name);
						this.pstmt.setInt(3, event_no);
						this.pstmt.setString(4, content);
						
						this.pstmt.execute();
						
					} catch (Exception e) {
						e.printStackTrace();
						check = false;
					} finally {
						DBManager.close(this.conn, this.pstmt);
					}
					
				} else {
					String sql = "INSERT INTO `comment` (`code`, `name`, event_no, content, p_no) VALUES (?, ?, ?, ?, ?)";
					
					try {
						this.pstmt = this.conn.prepareStatement(sql);
						this.pstmt.setInt(1, code);
						this.pstmt.setString(2, name);
						this.pstmt.setInt(3, event_no);
						this.pstmt.setString(4, content);
						this.pstmt.setInt(5, p_no);
						
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
		} else {
			check = false;
		}
		
		return check;
	}
	
	public boolean updateComment(CommentRequestDto commentDto) {
		
		this.conn = DBManager.getConnection();
		
		boolean check = true;
		
		if(this.conn != null && commentDto.getContent() != null && commentDto.getNo() != 0) {

			String sql = "UPDATE `comment` SET `content`=? WHERE `no`=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, commentDto.getContent());
				this.pstmt.setInt(2, commentDto.getNo());

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
	
	public boolean deleteCommentByNo(int no) {
		this.conn = DBManager.getConnection();
		
		boolean check = true;
		
		if(this.conn != null) {
			String sql = "DELETE FROM `comment` WHERE no=?";
			
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
