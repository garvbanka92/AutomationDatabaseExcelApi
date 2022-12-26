package businessLogic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import utilities.DatabaseOperations;
import utilities.ExcelReader;

public class SELECT {

	// DB Variables
	Connection conn = null;
	DatabaseOperations dbops = null;
	private String sqlstt = null;
	int numOfColsinDB = -1;

	// Excel Ops Variables
	String excelFilePath = null;
	ExcelReader excel = null;
	String sheetName = null;
	int ActualResultStartRowNum = -1;
	int numOfEmptyRowAfterExpectedResult = 3;
	int expectedResultStartRowNum = 5;

	public SELECT(Connection conn, String sqlstt, String excelFilePath, String sheetName) {
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

	public void putResultsToExcelAndHighlightDiff() throws SQLException {

		// Num of Expected Row including Header
		int numOfExpectedRowstoCompare = excel.getRowCount(sheetName) - expectedResultStartRowNum + 1;

		// Adding Empty Rows to Excel
		for (int i = 0; i < numOfEmptyRowAfterExpectedResult; i++) {
			excel.addRowInLast(sheetName);
		}

		excel.addRowInLast(sheetName, "Actual Result");

		// Adding Table Headers to the last Row
		if (ActualResultStartRowNum == -1) {
			ActualResultStartRowNum = excel.getRowCount(sheetName) + 1;
		}
		ArrayList<String> colSeq = dbops.getColSequence(sqlstt);
		numOfColsinDB = colSeq.size();
		for (int i = 0; i < numOfColsinDB; i++) {
			excel.setCellData(sheetName, i, ActualResultStartRowNum, colSeq.get(i));
		}

		// Adding Database Data to the Excel
		ArrayList<Hashtable<String, String>> DatabaseData = dbops.performSelectStatement(sqlstt);

		// Num of Rows in DB result
		int numOfActualRows = DatabaseData.size();
		Hashtable<String, String> rowdata = null;
		for (int rowNum = 0; rowNum < numOfActualRows; rowNum++) {
			rowdata = DatabaseData.get(rowNum);
			for (int colNum = 0; colNum < rowdata.size(); colNum++) {
				excel.setCellData(sheetName, colNum, ActualResultStartRowNum + rowNum + 1,
						rowdata.get(colSeq.get(colNum)));
			}
		}

		// Num of Actual Row including Header
		int numOfActualRowstoCompare = numOfActualRows + 1;

		// Validating the Expected vs Actual
		System.out.println(excel.compareTwoSetOfRows(sheetName, expectedResultStartRowNum, ActualResultStartRowNum,
				(numOfActualRowstoCompare > numOfExpectedRowstoCompare) ? numOfActualRowstoCompare
						: numOfExpectedRowstoCompare));
	}

}
