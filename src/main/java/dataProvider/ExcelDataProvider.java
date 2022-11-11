package dataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class ExcelDataProvider {
	
	public static String[][] readTestData(String file, String sheetName) {
        String[][] data = null;
        try (
                InputStream fis = Files.newInputStream(Paths.get(file));
                Workbook wb = WorkbookFactory.create(fis)
        ) {
            Sheet sh = wb.getSheet(sheetName);
            Cell cell;
            int rowCnt = 0;
            if (sh != null) {
                rowCnt = sh.getLastRowNum();               
                int colCnt = sh.getRow(0).getLastCellNum();
                data = new String[rowCnt][colCnt];
                for (int i = 0; i < rowCnt; i++) {
                    for (int j = 0; j < colCnt; j++) {
                        cell = sh.getRow(i + 1).getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);  
                        cell.setCellType(Cell.CELL_TYPE_STRING);

                            data[i][j] = cell.getStringCellValue();

                    }
                }
            }
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
