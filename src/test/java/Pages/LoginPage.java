package Pages;

import enums.Waits;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import report_manager.ExtentReportHelper;
import report_manager.ExtentReportManager;

@Slf4j
public class LoginPage extends Page{

    @FindBy(name = "username")
    private WebElement userName_txt;

    @FindBy(name = "password")
    private WebElement password_txt;

//    @FindBy(xpath = "//button[@type='submit']")
//    private WebElement login_button;
    private static final By login_button = By.xpath("//button[@type='submit']");

    @Override
    public LoginPage waitForPageToLoad() {
        Assert.assertTrue(interaction.awaitForElement(login_button, Waits.VISIBILITY, 50000), "login button was not found");
        return this;
    }

    public LoginPage enterUserName(String userName) {
//        userName_txt.sendKeys(userName);
        Assert.assertTrue(interaction.typeInText(userName_txt, userName), "Failed to enter "+userName);
//        ExtentReportHelper.getExtentTestThreadLocal().pass("Username Entered");
        log.info("Username Entered");
        return this;
    }

    public LoginPage enterPassword(String password) {
//        password_txt.sendKeys(password);
        Assert.assertTrue(interaction.typeInText(password_txt, password), "Failed to enter "+password);
//        ExtentReportHelper.getExtentTestThreadLocal().pass("Password Entered");
        log.info("Password Entered");
        return this;
    }

    public LoginPage clickOnLoginButton(){
//        webDriver.findElement(login_button).click();
        interaction.waitForElementToAppearAndClick(login_button, "Login button");
//        ExtentReportHelper.getExtentTestThreadLocal().pass("Login button is clicked");
        log.info("Login button is clicked");
        return this;
    }

    public HomePage loginWithValidCredentials(String userName, String password) {
        /*
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Its not a good way to declare Thread.sleep and need to handle exceptions too
        use apache commons lang 3 or webdriver wait
         */
//        Uninterruptibles.sleepUninterruptibly(20, TimeUnit.SECONDS);
//        or
//        Assert.assertTrue(interaction.awaitForElement(userName_txt, Waits.VISIBILITY), "User name text field did not appear");
        enterUserName(userName);
        enterPassword(password);
        clickOnLoginButton();

        return new HomePage();
    }
}
