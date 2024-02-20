package report_manager;

import com.aventstack.extentreports.ExtentTest;

public class ExtentReportHelper {

    private ExtentReportHelper() {}

    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static ExtentTest getExtentTestThreadLocal() {
        return extentTestThreadLocal.get();
    }

    public static void setExtentTestThreadLocal(ExtentTest extentTest) {
        extentTestThreadLocal.set(extentTest);
    }

}
