package demo2;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class AttachReport1 {

    static ExtentReports reports;
    static WebDriver driver = new ChromeDriver();


    @BeforeSuite
    public void setUp() throws IOException {
        reports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("index.html");
        /* From XML File*/
        final File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\demo2\\extentConfig.xml");
        sparkReporter.loadXMLConfig(file);
        reports.attachReporter(sparkReporter);
    }

    @Test
    public void attachingLogo() throws IOException {
        ExtentTest extentTest1 = reports.createTest("Home Test");
        extentTest1.pass("Login test started successfully");
        extentTest1.pass("URL is loaded");
        driver.get("https://www.google.com/");
//        extentTest1.pass("Value entered", MediaEntityBuilder.createScreenCaptureFromPath(getScreenShot()).build());
//        extentTest1.pass("Value entered", MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShotOfBase64()).build());
        extentTest1.pass("Value entered", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
        driver.quit();



    }

    @AfterSuite
    public void setAfterSuite() throws IOException {
        reports.flush();
        Desktop.getDesktop().browse(new File("index.html").toURI());
    }

    public String getScreenShot() throws IOException {
        /*  This is not supported becoz image attached to the report refers to local    */
        /*  When this sent to others image will be broken   */
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"\\Screenshot\\image.png";
        FileUtils.copyFile(source, new File(path));
        return path;
    }

    public String getScreenShotOfBase64() throws IOException {
        /*  This will take local storage where we need to delete local file */
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"\\Screenshot\\image.png";
        FileUtils.copyFile(source, new File(path));
        byte [] imageInBytes = IOUtils.toByteArray(new FileInputStream(path));
        return Base64.getEncoder().encodeToString(imageInBytes);
    }

    public String getBase64Image() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
    }
}
