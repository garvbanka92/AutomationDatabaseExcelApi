package base;

import java.sql.Connection;
import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.DatabaseConnection;

public class baseTest {

	public static Connection conn = null;

	@BeforeSuite
	public void setUp() throws SQLException {

		if (conn == null) {
			conn = DatabaseConnection.getConnection("jdbc:mysql://db4free.net:3306/testing1992", "abanka1992",
					"Ashu@1992");
		}

	}

	@AfterSuite
	public void tearDown() throws SQLException {

		if ((conn == null || conn.isClosed())) {
			conn.close();
		}

	}

}
