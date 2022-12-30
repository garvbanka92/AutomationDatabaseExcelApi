package rough;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import utilities.ExcelReader;

public class copyExcel {

	public static void main(String[] args) throws IOException {
		ExcelReader excel;
		Date date = new Date();
		System.setProperty("Test_Date", date.toString().replace(":", "_").replace(" ", "_"));

		String srcLocation = "F:\\Ashish_EclipseAndWorkspace\\TestFiles\\TestCases\\";
		String destFileLocation = srcLocation + System.getProperty("Test_Date") + "_Results\\";

		// Iterating all files and finding xlsx files
		File f = new File(srcLocation);
		String[] files = f.list();
		String configSheetName = "Sheet3";
		for (int i = 0; i < files.length; i++) {
			try {
				if (files[i].substring(files[i].lastIndexOf(".")).equals(".xlsx")) {
					excel = new ExcelReader(srcLocation + files[i]);
					System.out.println(excel.getMaxColumnCount(configSheetName));
					for (int colNum = 2; colNum < excel.getMaxColumnCount(configSheetName); colNum++) {
						if (!excel.getCellData(configSheetName, colNum, 5).equals(""))
							FileUtils.copyFile(new File(srcLocation + files[i]), new File(
									destFileLocation + excel.getCellData(configSheetName, colNum, 5) + "_" + files[i]));
					}
				}
			} catch (Exception e) {

			}
		}
	}
}
