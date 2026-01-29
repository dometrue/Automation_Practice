package automation.practice.tests;

import automation.practice.base.BaseTest;
import automation.practice.pages.InventoryPage;
import automation.practice.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1, groups = "ui")
    public void userCanLoginWithValidCredentials() {
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
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message is not displayed for invalid login"
        );
    }
}
