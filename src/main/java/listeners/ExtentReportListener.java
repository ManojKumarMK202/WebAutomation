package listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import lombok.extern.slf4j.Slf4j;
import org.testng.*;
import report_manager.ExtentReportHelper;
import report_manager.ExtentReportManager;

import java.util.Arrays;

@Slf4j
public final class ExtentReportListener implements ISuiteListener, IInvokedMethodListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentReportManager.initReports();
        log.info("Report execution started");
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReportManager.flushReports();
        log.info("Report execution closed");
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
//            ExtentReportHelper.startTest(method.getTestMethod().getMethodName());
//            ExtentReportHelper.getTest().assignCategory(testResult.getTestClass().getRealClass().getSimpleName());
            ExtentReportManager.createTest(method.getTestMethod().getMethodName());
            ExtentReportHelper.getExtentTestThreadLocal().assignCategory(testResult.getTestClass().getRealClass().getSimpleName());
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE || testResult.getStatus() == ITestResult.SKIP) {
            String getMethodName = testResult.getMethod().getConstructorOrMethod().getName();
            System.out.println(testResult.getTestClass().getName());
            String getClassName = testResult.getTestClass().getName().split("\\.")[1];
            log.error("Test class name : " + getClassName + " Test method name : " + getMethodName + " got failed");
            log.error(Arrays.toString(testResult.getThrowable().getStackTrace()));
            ExtentReportHelper.getExtentTestThreadLocal().pass("Value entered", MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReportManager.getBase64Image()).build());
        }
    }
}
