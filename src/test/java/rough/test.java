package rough;

import java.sql.Connection;
import java.sql.SQLException;

import base.baseTest;
import businessLogic.INESRT_UPDATE_DELETE;
import businessLogic.SELECT;
import utilities.DatabaseConnection;
import utilities.DatabaseOperations;

public class test extends baseTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Connection conn = DatabaseConnection.getConnection("jdbc:mysql://db4free.net:3306/testing1992", "abanka1992", "Ashu@1992");
		String excelFilePath = "F:\\Ashish_EclipseAndWorkspace\\TestFiles\\testFile.xlsx";
		String sqlstt = "Select * from table_name;";
		String sheetName = "Sheet2";
		
		
//		SELECT test = new SELECT(conn, sqlstt, excelFilePath, sheetName);
//		test.putResultsToExcelAndHighlightDiff();
		
		INESRT_UPDATE_DELETE test1 = new INESRT_UPDATE_DELETE(conn, sqlstt, excelFilePath, sheetName);
		test1.insertUpdateDelete();
	}

}
