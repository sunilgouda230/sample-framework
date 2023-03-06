package dataprovider;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    protected static Object[][] arr = null;
    public static XSSFWorkbook wb;
    public static DataFormatter formatter;

    /*
           XSSFWorkbook wb
           XSSFSheet sh1
           XSSFRow r1
           XSSFCell c1
       */

    public static Object[][] readExcel(String sheetname)  {

        System.out.println("******** Excel Loaded from Local******");
        try{
             wb = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"/testdata/testdata.xlsx")));
            XSSFSheet sh = wb.getSheet(sheetname);
            int rows = wb.getSheet(sheetname).getPhysicalNumberOfRows();
            int cols = sh.getRow(0).getPhysicalNumberOfCells();
            arr = new Object[rows-1][cols];

            for (int i = 1; i < rows; i++){
                for (int j =0; j < cols; j++){
                   arr[i-1][j] = getdata(sheetname,i,j);

                }
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        return arr;
    }

    public static String getdata(String sheetname, int row, int col){
        String data = "";
        XSSFCell cell = wb.getSheet(sheetname).getRow(row).getCell(col);
        formatter = new DataFormatter();

        if (cell.getCellType() == CellType.BLANK){
            data = "";

        } else if (cell.getCellType() == CellType.STRING){
            data = cell.getStringCellValue();

        } else if (cell.getCellType() == CellType.NUMERIC){
            data = formatter.formatCellValue(cell);
        }

        return data;
    }
}
