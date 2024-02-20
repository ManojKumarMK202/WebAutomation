package framework_constants;

import java.io.File;

public interface FilePath {
    /*
    public static final
    No need of private
    */
    String COMMON_DATA = System.getProperty("user.dir") + File.separator +"src"+ File.separator +"test"+ File.separator +"resources"+ File.separator +"config"+ File.separator +"config.properties";
    String JSON_DATA = System.getProperty("user.dir") + File.separator +"src"+ File.separator +"test"+ File.separator +"resources"+ File.separator +"config"+ File.separator +"config.json";
    String EXCEL_DATA = System.getProperty("user.dir") + File.separator +"src"+ File.separator +"test"+ File.separator +"resources"+ File.separator +"config"+ File.separator +"ExcelData.xlsx";

    String REPORT_PATH = System.getProperty("user.dir") + File.separator +"Reports"+ File.separator;

}
