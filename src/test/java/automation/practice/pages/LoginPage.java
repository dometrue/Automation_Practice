package automation.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
                .sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField))
                .sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage))
                .isDisplayed();
    }
}
