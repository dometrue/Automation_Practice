package automation.practice.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // Run headless if -Dheadless=true is passed, otherwise normal
        if (System.getProperty("headless", "false").equalsIgnoreCase("true")) {
            options.addArguments("--headless=new");
        }

        // CI stability flags
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to login page
        driver.get("https://www.saucedemo.com/"); // your login page URL
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
