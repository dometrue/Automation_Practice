package automation.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators are defined once and reused
    // This makes maintenance easier if the UI changes
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    // Constructor receives WebDriver and WebDriverWait
    // This allows test classes to control browser and wait logic centrally
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Wait until username field is visible before typing
    // Prevents NoSuchElementException on slow page loads
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
                .sendKeys(username);
    }

    // Same explicit wait strategy for password field
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField))
                .sendKeys(password);
    }

    // Wait until login button is clickable before clicking
    // Prevents ElementNotInteractableException
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }

    // Business-level action
    // Combines multiple low-level actions into one meaningful step
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Validates that error message is displayed
    // Uses explicit wait to handle UI rendering delays
    public boolean isErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage))
                .isDisplayed();
    }
}
