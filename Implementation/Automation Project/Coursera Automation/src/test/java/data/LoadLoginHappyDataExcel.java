package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoadLoginHappyDataExcel {
	FileInputStream stream = null;
	
	public FileInputStream getExcelFile() {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\Excel\\LoginHappyData.xlsx";
		
		try {
			stream = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return stream;
	}
	
	public Object[][] getExcelData() throws IOException{
		stream = getExcelFile();
		
		XSSFWorkbook workBook = new XSSFWorkbook(stream);
		XSSFSheet loginSheet = workBook.getSheetAt(0);
		
		int nRows = loginSheet.getLastRowNum() + 1;
		int nCols = 2;
		
		Object[][] loginData = new Object[nRows][nCols];
		
		for(int i = 0 ; i < nRows; ++i) {
			for(int j = 0 ; j < nCols; ++j) {
				XSSFRow row =  loginSheet.getRow(i);
				loginData[i][j] = row.getCell(j).toString();
			}
		}
		
		return loginData;
	}
}
