package automation.practice.tests;

import automation.practice.base.BaseTest;
import automation.practice.pages.InventoryPage;
import automation.practice.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1, groups = "ui")
    public void userCanLoginWithValidCredentials() {

        // Page Object is initialized with driver and wait
        // Keeps test logic clean and readable
        LoginPage loginPage = new LoginPage(driver, wait);

        // Business action instead of low-level Selenium calls
        loginPage.login("standard_user", "secret_sauce");

        // Validation is done via another Page Object
        InventoryPage inventoryPage = new InventoryPage(driver);

        // TestNG assertion
        // Verifies expected outcome
        Assert.assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "Inventory page is not displayed after login"
        );
    }

    @Test(priority = 2, groups = "ui")
    public void loginFailsWithInvalidPassword() {

        LoginPage loginPage = new LoginPage(driver, wait);

        // Invalid login attempt
        loginPage.login("standard_user", "wrong_password");

        // Assertion verifies application behavior
        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message is not displayed for invalid login"
        );
    }
}
