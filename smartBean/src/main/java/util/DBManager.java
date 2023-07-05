package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	public static Connection getConnection() {
		
		Connection conn = null;
		
		String url = "jdbc:mysql://database-1.cxn76w0hm0x8.ap-northeast-2.rds.amazonaws.com:3306/team3WebProject?serverTimeZone=UTC";
		String username = "admin";
		String password = "G9$3AClTAxavItr5gO9+";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("DB연동 성공 >>>");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("DB연동 실패 >>>");
		}
		return conn;
	}
}