package utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProvider {

	@Test(dataProvider="test1data")
	public void Test1(String firstname, String lastname, String username, String password) {
		System.out.println(firstname+" | "+lastname+" | "+username+" | "+password);
	}
	
	@DataProvider(name="test1data")
	public Object[][] getData(){
		String ProjectPath = System.getProperty("user.dir");
		String excelPath = ProjectPath+"/excel/loginCredentials.xlsx";
		Object data[][] = loginData(excelPath, "Sheet1");
		return data;
	}
	
	public static Object[][] loginData(String excelPath, String sheetName) {
		
		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
		
		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();
		
		Object data[][] = new Object[rowCount-1][colCount];
		
		for(int i = 1;i < rowCount;i++) {
			for(int j = 0; j < colCount;j++) {
				String cellData = excel.getCellDataString(i, j);
				//String cellData1 = excel.getCellDataNumber(i, j+1);
				//System.out.print(cellData+" | ");
				data[i-1][j] = cellData;
			}
			//System.out.println();
		}
	return data;
	}

}
