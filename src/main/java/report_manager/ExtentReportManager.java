package report_manager;

import browser_manager.BrowserDriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import framework_constants.FilePath;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.util.Objects;

public final class ExtentReportManager {

    private static ExtentReports extentReports;
    private ExtentReportManager() {}

    public static void initReports() {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(FilePath.REPORT_PATH+ "report.html");
            extentSparkReporter.config().setTheme(Theme.DARK);
            extentSparkReporter.config().setDocumentTitle("Automation Report");
            extentSparkReporter.config().setReportName("Report Demo");
            extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
            extentReports.attachReporter(extentSparkReporter);
        }
    }

    public static void flushReports() {
        if (Objects.nonNull(extentReports))
            extentReports.flush();
    }

    public static void createTest(String testcaseName) {
        ExtentTest test = extentReports.createTest(testcaseName);
        ExtentReportHelper.setExtentTestThreadLocal(test);
    }

    public static void updateResultInReport(Status status, String comment) {
        comment = comment == null ? "" : comment;
        ExtentReportHelper.getExtentTestThreadLocal().log(status, comment);
    }

    public synchronized static Status mapTestngStatusToExtentStatus(int status) {
        switch (status) {
            case ITestResult.SUCCESS:
                return Status.PASS;

            case ITestResult.FAILURE:
                return Status.FAIL;

            default:
                return Status.SKIP;
        }
    }

    public static String getBase64Image() {
        return ((TakesScreenshot) BrowserDriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
