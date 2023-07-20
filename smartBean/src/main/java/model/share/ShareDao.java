package model.share;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.shareEvent.ShareEventVo;
import model.todo.TodoVo;
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

    public ArrayList<Integer> getShareCalendarNo(String email) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        conn = DBManager.getConnection();

        if (conn != null) {
            String sql = "SELECT * FROM share_calendar WHERE email=?";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, email);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    int no = this.rs.getInt(2);

                    list.add(no);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBManager.close(conn, pstmt, rs);
            }
        }

        return list;
    }

    public boolean addSharedCalendar(String email, int calendarNo) {
        conn = DBManager.getConnection();
        
        boolean check = true;
        if (conn != null) {
        	System.out.println("Connection is null"); // 추가한 코드
            String sql = "INSERT INTO share_calendar (email, `no`) VALUES (?, ?)";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, email);
                pstmt.setInt(2, calendarNo);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                check = false; // 예외 발생시 실패했음을 반환
            } finally {
                DBManager.close(conn, pstmt);
            }
        } else {
            check = false; // DB 접속 실패시 실패했음을 반환
        }
        
        return check;
    }
    
    public ShareEventVo SharedCheck(int calendarNo, int eventNo) {
    	ShareEventVo shareEventVo = null;
    	
        conn = DBManager.getConnection();
        
        boolean check = true;
        if (conn != null) {
        	System.out.println("Connection is null"); // 추가한 코드
            String sql = "SELECT * from event_share where calendar_no=? AND event_no=?";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, calendarNo);
                pstmt.setInt(2, eventNo);
                pstmt.executeQuery();
                
                if(this.rs.next()) {
                	int calendarNum = this.rs.getInt(1);
    				int eventNoNum = this.rs.getInt(2);
    				
    				shareEventVo = new ShareEventVo(calendarNum, eventNoNum);
    			}
            } catch (SQLException e) {
                e.printStackTrace();
                check = false; // 예외 발생시 실패했음을 반환
            } finally {
                DBManager.close(conn, pstmt);
            }
        } else {
            check = false; // DB 접속 실패시 실패했음을 반환
        }
        
        return shareEventVo;
    }

}
