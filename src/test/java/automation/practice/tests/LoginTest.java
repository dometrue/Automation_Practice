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

        // 1️⃣ Navigate to the login page (important for Jenkins/CI)
        driver.get("https://www.saucedemo.com/");

        // 2️⃣ Initialize LoginPage object with driver & wait
        LoginPage loginPage = new LoginPage(driver, wait);

        // 3️⃣ Perform login with valid credentials
        loginPage.login("standard_user", "secret_sauce");

        // 4️⃣ Initialize InventoryPage and validate user is logged in
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "Inventory page is not displayed after login"
        );
    }

    @Test(priority = 2, groups = "ui")
    public void loginFailsWithInvalidPassword() {

        // 1️⃣ Navigate to the login page (important for Jenkins/CI)
        driver.get("https://www.saucedemo.com/");

        // 2️⃣ Initialize LoginPage object
        LoginPage loginPage = new LoginPage(driver, wait);

        // 3️⃣ Attempt login with invalid password
        loginPage.login("standard_user", "wrong_password");

        // 4️⃣ Verify that the error message is displayed
        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message is not displayed for invalid login"
        );
    }
}
