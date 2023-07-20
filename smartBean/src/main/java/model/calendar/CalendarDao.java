package model.calendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.share.ShareDao;
import model.user.UserVo;
import util.DBManager;

public class CalendarDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private CalendarDao() {
	}

	private static CalendarDao instance = new CalendarDao();

	public static CalendarDao getInstance() {
		return instance;
	}

	public CalendarVo getCalendarByName(String name) {
		CalendarVo calendar = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM calendar WHERE name = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int no = rs.getInt("no");
				int code = rs.getInt("code");
				String email = rs.getString("email");

				calendar = new CalendarVo(no, code, email, name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return calendar;
	}
	
	public ArrayList<CalendarVo> getSharedCalendars(String email) {
		ArrayList<CalendarVo> calendarList = new ArrayList<>();
		
		ShareDao shareDao = ShareDao.getInstance();
		ArrayList<Integer> listCal = shareDao.getShareCalendarNo(email);
		
		this.conn = DBManager.getConnection();
		
		String sql = "SELECT * FROM calendar";

		try {
			this.pstmt = this.conn.prepareStatement(sql);

			this.rs = this.pstmt.executeQuery();

			while (this.rs.next()) {

				int no = this.rs.getInt("no");
				int code = this.rs.getInt("code");
				String name = this.rs.getString("name");
				int p_code = this.rs.getInt("p_code");
				
				for(int i=0; i<listCal.size(); i++) {
					if(listCal.get(i) == no) {
						CalendarVo calendar = new CalendarVo(no, code, email, name, p_code);
						calendarList.add(calendar);
					}
				}				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt, this.rs);
		}

		return calendarList;
	}

	public ArrayList<CalendarVo> getAllCalendars(String email) {
		ArrayList<CalendarVo> calendarList = new ArrayList<>();
		
		this.conn = DBManager.getConnection();
		
		String sql = "SELECT * FROM calendar WHERE email=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, email);

			this.rs = this.pstmt.executeQuery();

			while (this.rs.next()) {
				int no = this.rs.getInt("no");
				int code = this.rs.getInt("code");
				String name = this.rs.getString("name");
				int p_code = this.rs.getInt("p_code");

				CalendarVo calendar = new CalendarVo(no, code, email, name, p_code);
				calendarList.add(calendar);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(this.conn, this.pstmt, this.rs);
		}

		return calendarList;
	}

	public ArrayList<CalendarVo> getUserCalendars(String email) {
		ArrayList<CalendarVo> userCalendars = new ArrayList<>();
		conn = null;
		pstmt = null;
		rs = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM calendar WHERE email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");

				CalendarVo calendar = new CalendarVo(no, 0, email, name);
				userCalendars.add(calendar);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return userCalendars;
	}

	public CalendarVo getCalendarByNo(int no) {
	    CalendarVo calendar = null;

	    try {
	        conn = DBManager.getConnection();

	        String sql = "SELECT * FROM calendar WHERE no = ?";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, no);

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            int code = rs.getInt("code");
	            String email = rs.getString("email");
	            String name = rs.getString("name");
	            int p_code = rs.getInt("p_code");

	            calendar = new CalendarVo(no, code, email, name, p_code);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.close(conn, pstmt, rs);
	    }

	    return calendar;
	}


	private int getUserCodeByEmail(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int userCode = -1;

		try {
			conn = DBManager.getConnection();

			if (conn != null) {
				String sql = "SELECT code FROM user WHERE email = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					userCode = rs.getInt("code");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return userCode;
	}

	public boolean createCalendar(CalendarRequestDto calendarDto) {
		// 이메일에 해당하는 user의 code 찾기 -> 이메일 유저가 없으면 -1 반환
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = DBManager.getConnection();

			if (conn != null) {
				
				if(calendarDto.getP_code() == 0) {
					String sql = "INSERT INTO calendar (code, email, `name`) VALUES (?, ?, ?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, calendarDto.getCode());
					pstmt.setString(2, calendarDto.getEmail());
					pstmt.setString(3, calendarDto.getName());

					int rowCount = pstmt.executeUpdate();
					result = rowCount > 0;
				} else {
					String sql = "INSERT INTO calendar (code, email, `name`, p_code) VALUES (?, ?, ?, ?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, calendarDto.getCode());
					pstmt.setString(2, calendarDto.getEmail());
					pstmt.setString(3, calendarDto.getName());
					pstmt.setInt(4, calendarDto.getP_code());

					int rowCount = pstmt.executeUpdate();
					result = rowCount > 0;
				}

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return result;
	}

	public boolean updateCalendarName(int no, String name) {
		conn = null;
		pstmt = null;
		boolean result = false;

		try {
			conn = DBManager.getConnection();

			String sql = "UPDATE calendar SET name = ? WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	public boolean deleteCalendarByNo(int no) {
		conn = null;
		pstmt = null;
		boolean result = false;

		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM calendar WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			int rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return result;
	}

	public void deleteEventFromCalendar(int calendarId, int eventNo) {
		conn = null;
		pstmt = null;

		try {
			conn = DBManager.getConnection();

			String sql = "DELETE FROM `event` WHERE calendar_no = ? AND no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, calendarId);
			pstmt.setInt(2, eventNo);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}

}