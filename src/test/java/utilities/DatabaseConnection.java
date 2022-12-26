package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static Connection connection;

	private DatabaseConnection() {

	}

	public static Connection getConnection(String url, String username, String password) throws SQLException {

		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, username, password);
				System.out.println("Connection Successful..!!!");
			} catch (ClassNotFoundException ex) {
				System.out.println("Database Connection Creation Failed : " + ex.getMessage());
			}
		}

		return connection;
	}
}