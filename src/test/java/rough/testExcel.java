package rough;

import utilities.ExcelReader;

public class testExcel {
	
	public static void main(String[] args) {
		
		ExcelReader excel = new ExcelReader("F:\\\\Ashish_EclipseAndWorkspace\\\\TestFiles\\\\testFile.xlsx");
		excel.replaceAllInSheet("Sheet1", "dateCol1", "dateCol");
	}

}
