package tests;

import TestAnnotation.TestInfo;
import base.BaseTests;
//import browser_manager.BrowserDriverManager;
//import framework_constants.FilePath;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.testng.Assert;
import org.testng.annotations.Test;
//import report_manager.ExtentReportManager;
//import utils.ConfigFileManager;
import utils.ExcelFileManager;
//import utils.JSONFileManager;

import java.util.Map;

public final class HomePageTests extends BaseTests {

    private HomePageTests() {
//        No one should create object of this class
//        final class -> no one shoul extend
    }
    /*

    @Test
    public void test1() {
//        BrowserDriverManager.getDriver().findElement(By.name("q")).sendKeys("Automation", Keys.ENTER);
        JSONFileManager jsonFileManager = JSONFileManager.getInstance(FilePath.JSON_DATA);
        System.out.println(jsonFileManager.getProperty("platform"));
    }

    @Test
    public void test2() {
        BrowserDriverManager.getDriver().findElement(By.name("q")).sendKeys("Selenium", Keys.ENTER);
    }

    @Test
    public void test3() {
//        WebDriver webDriver = new ChromeDriver();
//        webDriver.get("https://www.google.com");  Moved to base test
        BrowserDriverManager.getDriver().findElement(By.name("q")).sendKeys("Manoj TestYantra", Keys.ENTER);
//        webDriver.quit(); Moved to base test
    }

     */

    @Test(groups = {"regression"})
    @TestInfo(testcaseID = "TC_02")
    public void testHome() {
//        ExtentReportManager.createTest("Login Test");
        String testcaseId = getTestCaseId(HomePageTests.class, "testHome");
        Map<String, String> data = ExcelFileManager.setASheetName("LoginData").getData(testcaseId);
        openHomePageWithValidCredentials(data.get("UN"), data.get("PWD"));
//        Assert.assertTrue(Boolean.FALSE); To check retry analyser
    }

}
