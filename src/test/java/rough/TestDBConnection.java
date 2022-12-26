package rough;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.baseTest;
import utilities.DatabaseOperations;
import utilities.ExcelReader;

public class TestDBConnection extends baseTest {

	DatabaseOperations dbops = new DatabaseOperations(conn);;
	String sqlstt = "Select * from testing1992.table_name";
	ExcelReader excel = new ExcelReader("F:\\Ashish_EclipseAndWorkspace\\TestFiles\\testFile.xlsx");
	int lastRowCountinExcel = -1;
	int numOfColsinDB = -1;
	
	@Test(dataProvider = "dp1")
	public void test1(Hashtable<String, String> t) throws SQLException {
		ArrayList<String> colSeq = dbops.getColSequence(sqlstt);
		numOfColsinDB = colSeq.size();
		lastRowCountinExcel = excel.getRowCount("Sheet1");

		
		Iterator<String> colSeqIterator = colSeq.iterator();
		colSeq.size();
		while (colSeqIterator.hasNext()) {

			System.out.println(t.get(colSeqIterator.next()));

		}

	}

	@DataProvider
	public Object[][] dp1() throws SQLException {
//		return dbops.performSelectStatement(sqlstt);
		return null;
	}

}
