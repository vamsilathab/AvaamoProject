package dataProvider;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

//all the inputs can be passed from an excel instead of hardcoding in the scripts.
public class ExcelDataProvider {
	public XSSFWorkbook wb;	
	File f;
	
	public ExcelDataProvider(){
		String sheetName = "";
		try(
			FileInputStream fis = new FileInputStream(new File("--Use input file path--"));
			XSSFWorkbook wbb = new XSSFWorkbook(fis);
			) {
			 Sheet sh = wbb.getSheet(sheetName);
		} 
		catch (Exception e) {
			System.out.println("Error details: "+e.getMessage());
		}		
	}
	
	public  String getSheetData(int sheetIndex, int row, int column) {
		String data = wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();
		return data;
	}
	
	public  String getData(String sheetName, int row, int column) {
		String data = wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		return data;
	}
		
}
