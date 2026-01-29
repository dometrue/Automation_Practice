package automation.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * InventoryPage class
 * -----------------
 * Represents the page after successful login.
 * Provides methods to validate page state and interact with inventory.
 */
public class InventoryPage {

    private WebDriver driver;

    // Locator for identifying the inventory page
    private By inventoryContainer = By.id("inventory_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Validate that inventory page is displayed
    public boolean isInventoryPageDisplayed() {
        return driver.findElement(inventoryContainer).isDisplayed();
    }
}
