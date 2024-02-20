package listeners;

import framework_constants.ConfigConstants;
import framework_constants.FilePath;
import lombok.extern.slf4j.Slf4j;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import utils.ConfigFileManager;
import utils.ExcelFileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class MethodInterceptor implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        log.info("MethodInterceptor executing");
        List<IMethodInstance> result = new ArrayList<>();
        try {
            String sheetName = ConfigFileManager.getInstance(FilePath.COMMON_DATA).getProperty(ConfigConstants.EXECUTOR);
            log.info("Sheet name "+sheetName);
            List<Map<String, String>> list = ExcelFileManager.setASheetName(sheetName).getDataList();
            for (int i = 0; i< methods.size();i++) {
                for (int j = 0; j< list.size(); j++){
                    if (methods.get(i).getMethod().getMethodName().equalsIgnoreCase(list.get(j).get("TestCaseName")) && list.get(j).get("Executor").equalsIgnoreCase("yes")){
//                        methods.get(i).getMethod().setInvocationCount(1);
                        result.add(methods.get(i));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
