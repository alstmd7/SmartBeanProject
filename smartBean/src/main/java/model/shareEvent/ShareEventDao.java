package model.shareEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.event.EventDao;
import model.event.EventVo;
import util.DBManager;

public class ShareEventDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ShareEventDao() {
	}

	private static ShareEventDao instance = new ShareEventDao();

	public static ShareEventDao getInstance() {
		return instance;
	}
	
	public ArrayList<Integer> getShareCalendarNoByEventNo(int no) {
		ArrayList<Integer> list = new ArrayList<Integer>();

		this.conn = DBManager.getConnection();

		String sql = "SELECT * from event_share WHERE event_no=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			
			while(this.rs.next()) {
				int calendar_no = this.rs.getInt(1);
				
				list.add(calendar_no);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt, this.rs);
		}
		
		return list;
	}
	
	public void CreateShareEvent(int no) {
		this.conn = DBManager.getConnection();
		
		ArrayList<EventVo> eventList = new ArrayList<EventVo>();
		
		EventDao eventDao = EventDao.getInstance();
		
		ArrayList<Integer> eventNoList = eventDao.getEventNoAll();
		
		int x = 0;
		for(int i = 0; i < eventNoList.size(); i++) {
			if(x < eventNoList.get(i)) {
				x = eventNoList.get(i);
			}
		}
		
		String sql = "INSERT INTO event_share VALUES(?, ?);";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);
			this.pstmt.setInt(2, x);

			this.pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt);
		}
		
	}
}
