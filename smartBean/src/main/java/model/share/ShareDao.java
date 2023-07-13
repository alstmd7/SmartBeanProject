package model.share;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.calendar.CalendarDao;
import model.calendar.CalendarVo;
import util.DBManager;

public class ShareDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ShareDao() {}
	private static ShareDao instance = new ShareDao();
	
	public static ShareDao getInstance() {
		return instance;
	}
	
	public ArrayList<CalendarVo> getShareCalendar(String email) {
		ArrayList<CalendarVo> share = new ArrayList<CalendarVo>();
		
		CalendarDao calendarDao = CalendarDao.getInstance();
		
		this.conn = DBManager.getConnection();
		
		String sql = "SELECT * FROM share_calendar WHERE email=?";
		
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, email);
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				int no = this.rs.getInt(2);
				
				CalendarVo calendarVo = calendarDao.getCalendarByNo(no);
				share.add(calendarVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt, this.rs);
		}
		
		return share;
	}
}
