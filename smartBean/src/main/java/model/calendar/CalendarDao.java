package model.calendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<CalendarVo> getAllCalendars() {
        ArrayList<CalendarVo> calendarList = new ArrayList<>();
        this.conn = null;
        this.pstmt = null;
        this.rs = null;

        try {
            this.conn = DBManager.getConnection();

            String sql = "SELECT * FROM calendar";
            this.pstmt = conn.prepareStatement(sql);

            this.rs = pstmt.executeQuery();

            while (rs.next()) {
                int no = rs.getInt("no");
                String email = rs.getString("email");
                String name = rs.getString("name");

                CalendarVo calendar = new CalendarVo(no, email, name);
                calendarList.add(calendar);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DBManager.close(this.conn, this.pstmt);
        }

        return calendarList;
    }

    public CalendarVo getCalendarByNo(int no) {
        CalendarVo calendar = null;

        try {
            this.conn = DBManager.getConnection();

            String sql = "SELECT * FROM calendar WHERE no = ?";
            
            this.pstmt = conn.prepareStatement(sql);
            this.pstmt.setInt(1, no);

            this.rs = this.pstmt.executeQuery();

            if (rs.next()) {
                String email = this.rs.getString("email");
                String name = rs.getString("name");

                calendar = new CalendarVo(no, email, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DBManager.close(this.conn, this.pstmt);
        }

        return calendar;
    }
    
    public boolean createCalendar(CalendarRequestDto calendarDto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            conn = DBManager.getConnection();

            if (conn != null) {
                String sql = "INSERT INTO calendar (email, name) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, calendarDto.getEmail());
                pstmt.setString(2, calendarDto.getName());

                int rowCount = pstmt.executeUpdate();
                result = rowCount > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }

        return result;
    }


    public void updateCalendarName(int no, String name) {
        this.conn = null;
        this.pstmt = null;

        try {
            this.conn = DBManager.getConnection();

            String sql = "UPDATE calendar SET name = ? WHERE no = ?";
            this.pstmt = conn.prepareStatement(sql);
            this.pstmt.setString(1, name);
            this.pstmt.setInt(2, no);

			this.pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DBManager.close(this.conn, this.pstmt);
        }
    }

    public boolean deleteCalendarByEmail(int no) {
        this.conn = null;
        this.pstmt = null;

        boolean check = true;
        try {
            this.conn = DBManager.getConnection();

            String sql = "DELETE FROM calendar WHERE no = ?";
            this.pstmt = conn.prepareStatement(sql);
            this.pstmt.setInt(1, no);
            
            int rowCount = pstmt.executeUpdate();
            check = rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            check = false;
        } finally {
        	DBManager.close(this.conn, this.pstmt);
        }
        return check;
    }
}
