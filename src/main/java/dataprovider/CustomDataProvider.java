package dataprovider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {

    @DataProvider(name = "logincred")
    public Object [][]  readdata(){
        Object [][] arr = ExcelReader.readExcel("login");
        return arr;
    }

    @DataProvider(name = "addanddelete")
    public Object [][]  readdataforaddproduct(){
        Object [][] arr = ExcelReader.readExcel("addanddelete");
        return arr;
    }
}
