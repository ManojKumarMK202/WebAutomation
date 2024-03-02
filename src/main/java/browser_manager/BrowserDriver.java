package browser_manager;

import framework_constants.ConfigConstants;
import framework_constants.Constants;
import framework_constants.FilePath;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigFileManager;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Objects;

@Slf4j
public class BrowserDriver {

    private static ConfigFileManager configFileManager = ConfigFileManager.getInstance(FilePath.COMMON_DATA);
    private static ThreadLocal<String> webBrowser = new ThreadLocal<>();
//    private static WebDriver webDriver;
//    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//    public static WebDriver getDriver() {
//        return driver.get();
//    }
//    public static void setDriver(WebDriver driverInstance) {
//        driver.set(driverInstance);
//    }
//    /*  Sonar lint uses unload to remove functionality  */
//    public static void unload() {
//        driver.remove();
//    }Moved to BrowserDriverManagerClass

    public static void initDriver() throws Exception {
        /*Condition added so that no 2 instances cannot be launched */
//        if (webDriver == null) {
        /*
        webDriver becoz of this and thread count was not proper and it was static in initial
        if (Objects.isNull(webDriver)) {
        this block was not executed and script was using driver instance from browser driver manager
        so we use to get null pointer exception
        webDriver changed to BrowserDriverManager.getDriver()
        private static WebDriver webDriver; - this was removed
         */
        if (Objects.isNull(BrowserDriverManager.getDriver())) {
            WebDriver webDriver = null;
//            //Moved to browserManager.BrowserDriver
            String browser = (null == webBrowser.get()) ? "chrome" : webBrowser.get();
//            log.info("Browser was set to "+ browser);
//            if (browser.equalsIgnoreCase("chrome"))
//                webDriver = new ChromeDriver();
//            else if (browser.equalsIgnoreCase("firefox"))
//                webDriver = new FirefoxDriver();
//            else
//                webDriver = new ChromeDriver();
            /* In order to specify local or docker execution get local get remote driver methods created*/
            if(Boolean.parseBoolean(configFileManager.getProperty(ConfigConstants.GRID_EXECUTION)))
                webDriver = getRemoteWebDriver(browser);
            else
                webDriver = getLocalDriver(browser);
            BrowserDriverManager.setDriver(webDriver);
//                BrowserDriverManager.getDriver().get("https://www.google.com");  //becoz browser specific
            BrowserDriverManager.getDriver().get(configFileManager.getProperty(ConfigConstants.URL));
            BrowserDriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
            BrowserDriverManager.getDriver().manage().window().maximize();
        }
    }

    public static void quitDriver() {
//        if (webDriver != null) {
        if (Objects.nonNull(BrowserDriverManager.getDriver())) {
            BrowserDriverManager.getDriver().quit();
//            setDriver(null);
            BrowserDriverManager.unload();
        }
    }

    public static void setBrowser(String browser){
        webBrowser.set(browser);
    }

    private static WebDriver getLocalDriver(String browser) {
        WebDriver webDriver = null;
        log.info("Browser was set to "+ browser);
        if (browser.equalsIgnoreCase("chrome"))
            webDriver = new ChromeDriver();
        else if (browser.equalsIgnoreCase("firefox"))
            webDriver = new FirefoxDriver();
        else
            webDriver = new ChromeDriver();
        return webDriver;
    }

    private static WebDriver getRemoteWebDriver(String browser) throws Exception
    {
        Capabilities capabilities;
        if (browser.equalsIgnoreCase("chrome"))
            capabilities = new ChromeOptions();
        else
            capabilities = new FirefoxOptions();
        String host = System.getProperty("host", InetAddress.getLocalHost().getHostAddress());
        String remoteUrl = String.format(configFileManager.getProperty(ConfigConstants.GRID_URL), host);
        System.out.println(remoteUrl);
        return new RemoteWebDriver(new URL(remoteUrl), capabilities);
    }
}
