package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String connection = "jdbc:mysql://localhost:3306/library";
		String user = "root";
		String password = "1499020";

		Class.forName(driver);
		Connection con = DriverManager
				.getConnection(connection, user, password);
		con.setAutoCommit(false);

		return con;
	}

}
