package base;

import actions.WebElementInteraction;
import browser_manager.BrowserDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseUtils {

    protected WebDriver webDriver;
    protected WebElementInteraction interaction;

    public BaseUtils() {
        webDriver = BrowserDriverManager.getDriver();
        PageFactory.initElements(webDriver, this);
        interaction = new WebElementInteraction();
    }
}
