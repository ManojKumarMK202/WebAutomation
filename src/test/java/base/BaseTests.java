package base;

import Pages.HomePage;
import Pages.LoginPage;
import TestAnnotation.TestInfo;
import browser_manager.BrowserDriver;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
//import report_manager.ExtentReportManager;

import java.lang.reflect.Method;

@Slf4j
public class BaseTests {

    protected BaseTests () {
//        Only child class should be able to use testng annotation methods
    }

    @BeforeSuite(alwaysRun = true)
    protected void setUpBS () {
//        ExtentReportManager.initReports();
        /*
        *   Added alwaysRun = true because when groups added it ignores base
        *   So adding indication that it executes base 1st and move on group shuffled methods
        * */
        log.info("setUpBS executing");
    }

    @BeforeTest(alwaysRun = true)
    @Parameters({"browser"})
    protected void setUpBT(@Optional String browser) {
        log.info("setUpBT is executing");
        System.out.println(System.getProperty("browser")+"  "+System.getProperty("hub")+" "+System.getProperty("gridExecution"));
        String webBrowser = System.getProperty("browser")==null ? browser: System.getProperty("browser");
        BrowserDriver.setBrowser(webBrowser);
        BrowserDriver.setHub(System.getProperty("hub"));
    }

    @BeforeMethod(alwaysRun = true)
    protected void setUpBM() throws Exception{
//        webDriver = new ChromeDriver();   Moved to browserManager.BrowserDriver
//        webDriver.get("https://www.google.com");  becoz browser specific
        log.info("setUpBM executing");
        BrowserDriver.initDriver();

    }

    @AfterMethod(alwaysRun = true)
    protected void setUpAM() {
//        webDriver.quit();Moved to browserManager.BrowserDriver
        BrowserDriver.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    protected void setUpAS() {
//        ExtentReportManager.flushReports();
    }

    protected HomePage openHomePageWithValidCredentials(String userName, String password) {
        return new LoginPage().loginWithValidCredentials(userName, password);
    }

    protected String getTestCaseId(Class className, String testMethodName) {
        try {
            Method method = className.getMethod(testMethodName);
            TestInfo testinfo = (TestInfo) method.getAnnotation(TestInfo.class);
            if (testinfo != null) {
                return testinfo.testcaseID();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "No Testcase id found: " + testMethodName;
    }
}
