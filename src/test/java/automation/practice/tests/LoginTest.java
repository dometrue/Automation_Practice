package automation.practice.tests;

import automation.practice.base.BaseTest;
import automation.practice.pages.InventoryPage;
import automation.practice.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * LoginTest class
 * -----------------
 * Contains all login-related test cases.
 * Inherits BaseTest to automatically get WebDriver setup/teardown.
 */

public class LoginTest extends BaseTest {

    @Test(priority = 1, groups = "ui")
    public void userCanLoginWithValidCredentials() {

        // Navigate to login page first
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);

        Assert.assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "Inventory page is not displayed after login"
        );
    }

    @Test(priority = 2, groups = "ui")
    public void loginFailsWithInvalidPassword() {

        // Navigate to login page first
        driver.get("https://www.saucedemo.com/");

        // 1️⃣ Attempt login with wrong password
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("standard_user", "wrong_password");

        // 2️⃣ Verify error message is displayed
        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message is not displayed for invalid login"
        );
    }
}
