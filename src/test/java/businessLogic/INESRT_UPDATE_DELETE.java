package businessLogic;

import java.sql.Connection;
import java.sql.SQLException;

import utilities.DatabaseOperations;
import utilities.ExcelReader;

public class INESRT_UPDATE_DELETE {

	// DB Variables
	Connection conn = null;
	DatabaseOperations dbops = null;
	private String sqlstt = null;
	int recordsAffected = 0;

	// Excel Ops Variables
	String excelFilePath = null;
	ExcelReader excel = null;
	String sheetName = null;
	int numberOfPhysicalRows = -1;

	public INESRT_UPDATE_DELETE(Connection conn, String sqlstt, String excelFilePath, String sheetName) {
		this.conn = conn;
		this.sqlstt = sqlstt;
		this.excelFilePath = excelFilePath;
		this.sheetName = sheetName;

		if (excel == null) {
			excel = new ExcelReader(excelFilePath);
		}

		if (dbops == null) {
			dbops = new DatabaseOperations(conn);
		}
	}

	public void insertUpdateDelete() throws SQLException {
		// Getting all the RowCount from Excel
		numberOfPhysicalRows = excel.getRowCount(sheetName);
		
		//Iterating all the ExcelRow
		for (int i = 1; i <= numberOfPhysicalRows; i++) {
			sqlstt = excel.getCellData(sheetName, 0, i);
			System.out.println(sqlstt);
			try {
			recordsAffected = dbops.performInsertUpdateDeleteStatement(sqlstt);
			}
			catch(Exception e){
				recordsAffected = -1;
			}
			excel.setCellData(sheetName, 1, i, String.valueOf(recordsAffected));
		}
	}
}
