package learn.basics.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getH2Connection() {
		//H2 Embedded database.
		//Remember to add its driver to project class path
		//SQLException: No suitable driver found for jdbc:h2:~/test
		Connection conn = null;
		try {
			String jdbc = "jdbc:h2:~/test";
			String user = "sa";
			String pass = "111111";
			conn = DriverManager.getConnection(jdbc, user, pass);
			System.out.println("DB:Get Connection success :)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeH2Connection(Connection conn) {
		if (conn!= null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
