package tests;

//import Pages.LoginPage;
import TestAnnotation.TestInfo;
import base.BaseTests;
//import browser_manager.BrowserDriverManager;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
//import report_manager.ExtentReportManager;
import utils.ExcelFileManager;

//import java.io.IOException;
import java.util.Map;

public final class LoginTests extends BaseTests {

    private LoginTests (){
        //No one should create object
        //final class -> No one should extend this class
    }

    /*
    @Test
    public void test1() {
        BrowserDriverManager.getDriver().findElement(By.name("q")).sendKeys("Automation", Keys.ENTER);
    }

    @Test
    public void test2() {
        BrowserDriverManager.getDriver().findElement(By.name("q")).sendKeys("Selenium", Keys.ENTER);
    }

    @Test
    public void test3() {
//        WebDriver webDriver = new ChromeDriver();
//        webDriver.get("https://www.google.com"); Moved to base test
        BrowserDriverManager.getDriver().findElement(By.name("q")).sendKeys("Manoj TestYantra", Keys.ENTER);
//        webDriver.quit();Moved to base test
    }

     */
    @Test(groups = {"regression"})
    @TestInfo(testcaseID = "TC_01")
    public void test4()  {
//        ExtentReportManager.createTest("Login Test");
        String testcaseId = getTestCaseId(LoginTests.class, "test4");
        Map<String, String> data = ExcelFileManager.setASheetName("LoginData").getData(testcaseId);
        openHomePageWithValidCredentials(data.get("UN"), data.get("PWD"));
    }


}
