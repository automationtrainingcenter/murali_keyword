package ebanking.keyword_driven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	Workbook book;
	Sheet sheet;

	private String getFilePath(String folderName, String fileName) {
		return System.getProperty("user.dir") + File.separator + folderName + File.separator + fileName;
	}

	// open excel file
	public void openExcel(String folderName, String fileName, String sheetName) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(getFilePath(folderName, fileName));
			if (fileName.endsWith("xls")) {
				book = new HSSFWorkbook(fis);
			} else if (fileName.endsWith("xlsx")) {
				book = new XSSFWorkbook(fis);
			} else {
				throw new RuntimeException("invalid file format... Please check your file format");
			}
			sheet = book.getSheet(sheetName);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// retrieve number rows
	public int getRows() {
		return sheet.getLastRowNum();
	}

	// retrieve number columns
	public int getColumns() {
		return sheet.getRow(0).getLastCellNum();
	}

	// retrieve each cell data
	public String getCellData(int rnum, int cnum) {
		String data = "";
		Cell cell = sheet.getRow(rnum).getCell(cnum);
		CellType cellType;
		try {
			cellType = cell.getCellType();
			switch (cellType) {
			case STRING:
				data = cell.getStringCellValue();
				break;
			case NUMERIC:
				double cellValue = cell.getNumericCellValue();
				int i = (int) cellValue;
				data = Integer.toString(i);
			case BLANK:
				data = "";
				break;
			default:
				data = "";
				break;
			}
		} catch (NullPointerException e) {
			
		}
		return data;
		
	}

	// close the excel
	public void closeExcel() {
		try {
			book.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		ExcelHelper excel = new ExcelHelper();
		excel.openExcel("resources", "testcases.xlsx", "teststeps");
		int nor = excel.getRows();
		int noc = excel.getColumns();
		System.out.println(nor + " " + noc);
		for (int i = 1; i <= nor; i++) {
			for (int j = 0; j < noc; j++) {
				System.out.print(excel.getCellData(i, j)+"\t");
			}
			System.out.println();
		}
		excel.closeExcel();
	}

}
