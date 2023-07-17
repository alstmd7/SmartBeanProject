package model.share;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.calendar.CalendarDao;
import model.calendar.CalendarVo;
import util.DBManager;

public class ShareDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private ShareDao() {
    }

    private static ShareDao instance = new ShareDao();

    public static ShareDao getInstance() {
        return instance;
    }

    public ArrayList<CalendarVo> getSharedCalendars(String email) {
        ArrayList<CalendarVo> sharedCalendars = new ArrayList<>();
        CalendarDao calendarDao = CalendarDao.getInstance();

        conn = DBManager.getConnection();

        if (conn != null) {
            String sql = "SELECT * FROM share_calendar WHERE email=?";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, email);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    int calendarNo = rs.getInt("no");
                    CalendarVo calendar = calendarDao.getCalendarByNo(calendarNo);
                    sharedCalendars.add(calendar);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBManager.close(conn, pstmt, rs);
            }
        }

        return sharedCalendars;
    }

    public boolean addSharedCalendar(String email, int calendarNo) {
        conn = DBManager.getConnection();
        if (conn != null) {
        	System.out.println("Connection is null"); // 추가한 코드
            String sql = "INSERT INTO share_calendar (email, `no`) VALUES (?, ?)";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, email);
                pstmt.setInt(2, calendarNo);
                pstmt.executeUpdate();
                return true; // 성공적으로 실행되었음을 반환
            } catch (SQLException e) {
                e.printStackTrace();
                return false; // 예외 발생시 실패했음을 반환
            } finally {
                DBManager.close(conn, pstmt);
            }
        } else {
            return false; // DB 접속 실패시 실패했음을 반환
        }
    }

}
