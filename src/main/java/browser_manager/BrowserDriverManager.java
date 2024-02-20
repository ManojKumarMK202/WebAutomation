package browser_manager;

import org.openqa.selenium.WebDriver;

public class BrowserDriverManager {

    private BrowserDriverManager() {}

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    /*  Sonar lint uses unload to remove functionality  */
    public static void unload() {
        driver.remove();
    }
}
