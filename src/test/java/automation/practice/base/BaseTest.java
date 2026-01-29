package automation.practice.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * BaseTest class
 * -----------------
 * This is the parent class for all UI tests.
 * It initializes the WebDriver and WebDriverWait before each test method
 * and quits the driver after the test to clean up resources.
 */
public class BaseTest {

    protected WebDriver driver;      // Shared WebDriver instance
    protected WebDriverWait wait;    // Shared WebDriverWait for explicit waits

    @BeforeMethod
    public void setUp() {
        // 1️⃣ Setup ChromeDriver automatically (works locally & in Jenkins)
        WebDriverManager.chromedriver().setup();

        // 2️⃣ Chrome options for headless mode in Jenkins/CI
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");      // Headless mode
        options.addArguments("--disable-gpu");       // Recommended for CI
        options.addArguments("--no-sandbox");        // Stability in CI
        options.addArguments("--disable-dev-shm-usage"); // Avoid memory issues in containers

        // 3️⃣ Initialize driver and maximize window
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // 4️⃣ Initialize WebDriverWait (shared across all page objects)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Clean up after each test
        }
    }
}
