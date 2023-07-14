package model.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import util.DBManager;

public class EventDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");

	private EventDao() {
	}

	private static EventDao instance = new EventDao();

	public static EventDao getInstance() {
		return instance;
	}

	public boolean createEvent(EventRequestDto eventDto) {
	    int calendar_no = eventDto.getCalendar_no();
	    int task_no = eventDto.getTask_no();
	    String name = eventDto.getName();
	    String email = eventDto.getEmail();
	    String title = eventDto.getTitle();
	    String content = eventDto.getContent();
	    String start = eventDto.getStart();
	    String end = eventDto.getEnd();
	    String all_day = eventDto.getAll_day();

	    boolean check = true;

	    if (calendar_no != 0 && task_no != 0 && name != null && email != null && title != null && content != null
	            && start != null && end != null && all_day != null) {
	        this.conn = DBManager.getConnection();
	        if (this.conn != null) {
	            String sql = "INSERT INTO `event` (calendar_no, task_no, `name`, email, title, content, `start`, `end`)"
	                    + " SELECT ?, ?, ?, ?, ?, ?, DATE(?), DATE(?)"
	                    + " WHERE NOT EXISTS (SELECT 1 FROM `event` WHERE calendar_no = ? AND task_no = ? AND `name` = ? AND email = ? AND title = ? AND `start` = DATE(?) AND `end` = DATE(?) AND all_day = ?)";

	            try {
	                this.pstmt = this.conn.prepareStatement(sql);
	                this.pstmt.setInt(1, calendar_no);
	                this.pstmt.setInt(2, task_no);
	                this.pstmt.setString(3, name);
	                this.pstmt.setString(4, email);
	                this.pstmt.setString(5, title);
	                this.pstmt.setString(6, content);
	                this.pstmt.setString(7, start);
	                this.pstmt.setString(8, end);
	                this.pstmt.setInt(9, calendar_no);
	                this.pstmt.setInt(10, task_no);
	                this.pstmt.setString(11, name);
	                this.pstmt.setString(12, email);
	                this.pstmt.setString(13, title);
	                this.pstmt.setString(14, start);
	                this.pstmt.setString(15, end);
	                this.pstmt.setString(16, all_day);

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

	public ArrayList<EventVo> getEventAll() {
		ArrayList<EventVo> list = new ArrayList<EventVo>();

		this.conn = DBManager.getConnection();

		if (this.conn != null) {
			String sql = "SELECT * FROM `event`";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();

				while (this.rs.next()) {
					int no = this.rs.getInt(1);
					int calendar_no = this.rs.getInt(2);
					int task_no = this.rs.getInt(3);
					String name = this.rs.getString(4);
					String email = this.rs.getString(5);
					String title = this.rs.getString(6);
					String content = this.rs.getString(7);
					String start = sdf.format(this.rs.getDate(8));
					String end = sdf.format(this.rs.getDate(9));
					String all_day = this.rs.getString(10);

					EventVo event = new EventVo(no, calendar_no, task_no, name, email, title, content, start, end,
							all_day);
					list.add(event);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(this.conn, this.pstmt, this.rs);
			}
		}

		return list;
	}

	public boolean updateEvent(EventRequestDto eventDto) {

		this.conn = DBManager.getConnection();

		boolean check = true;

		if (this.conn != null && eventDto.getName() != null && eventDto.getTitle() != null
				&& eventDto.getContent() != null && eventDto.getStart() != null && eventDto.getEnd() != null
				&& eventDto.getAll_day() != null) {

			String sql = "UPDATE `event` SET `name`=?, title=?, content=?, "
					+ "`start`=DATE(?), `end`= DATE(?), all_day=? WHERE `no`=?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setString(1, eventDto.getName());
				this.pstmt.setString(2, eventDto.getTitle());
				this.pstmt.setString(3, eventDto.getContent());
				this.pstmt.setString(4, eventDto.getStart());
				this.pstmt.setString(5, eventDto.getEnd());
				this.pstmt.setString(6, eventDto.getAll_day());
				this.pstmt.setInt(7, eventDto.getNo());

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

		if (this.conn != null) {
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

	public boolean deleteEvent(int eventNo) {
		this.conn = DBManager.getConnection();
		boolean check = true;

		if (this.conn != null) {
			String sql = "DELETE FROM `event` WHERE `no` = ?";

			try {
				this.pstmt = this.conn.prepareStatement(sql);
				this.pstmt.setInt(1, eventNo);
				this.pstmt.execute();
			} catch (SQLException e) {
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
	
	public EventVo getEventById(int eventNo) {
	    EventVo event = null;
	    conn = DBManager.getConnection();

	    if (conn != null) {
	        String sql = "SELECT * FROM `event` WHERE `no` = ?";

	        try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, eventNo);

	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                int calendarId = rs.getInt("calendar_no");
	                String title = rs.getString("title");
	                String start = rs.getString("start");
	                String end = rs.getString("end");

	                event = new EventVo(eventNo, calendarId, title, start, end);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBManager.close(conn, pstmt, rs);
	        }
	    }

	    return event;
	}

	

}