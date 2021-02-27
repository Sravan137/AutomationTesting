package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public static void main(String[] args) {
		getRowCount();
		getColCount();
		getCellDataString(0,0);
		getCellDataNumber(1,1);
	}
	
	public ExcelUtils(String excelPath, String sheetName) {
		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		}catch(Exception e) {
			System.out.println("message "+e.getMessage());
			System.out.println("Cause "+e.getCause());
			e.getStackTrace();
		}
	}

	public static int getRowCount() {
		int rowCount = 0;
		try {

			rowCount = sheet.getPhysicalNumberOfRows();
			System.out.println("No. of Rows - "+rowCount);
		}catch(Exception e) {
			System.out.println("message "+e.getMessage());
			System.out.println("Cause "+e.getCause());
			e.getStackTrace();
		}
		return rowCount;
	}
	
	public static int getColCount() {
		int colCount = 0;
		try {
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("No. of Columns - "+colCount);
		}catch(Exception e) {
			System.out.println("message "+e.getMessage());
			System.out.println("Cause "+e.getCause());
			e.getStackTrace();
		}
		return colCount;
	}

	public static String getCellDataString(int rowNum, int colNum) {
		String cellData = null, cellData1 = null;
		try {
			cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			//cellData1 = sheet.getRow(rowNum).getCell(colNum+1).getStringCellValue();
			//System.out.println("Cell Data - "+cellData+" | "+cellData1);
		}catch(Exception e) {
			System.out.println("message "+e.getMessage());
			System.out.println("Cause "+e.getCause());
			e.getStackTrace();
		}
		//return cellData+"|"+cellData1;
		return cellData;
	}

	public static String getCellDataNumber(int rowNum, int colNum) {
		String CellData = null;
		try {
			CellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			//System.out.println("Cell Data - "+CellData);
		}catch(Exception e) {
			System.out.println("message "+e.getMessage());
			System.out.println("Cause "+e.getCause());
			e.getStackTrace();
		}
		return CellData;
	}
}