import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static lv.acodemy.constants.Generic.GOOGLE_URL;
import static org.testng.Assert.assertEquals;

public class TestChrome {

    ChromeDriver driver;

    @BeforeMethod
    public void initialize() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // --headless parameter is needed to run it on Ubuntu (without GUI)
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void chromeTest() {
        driver.get(GOOGLE_URL);

        WebElement acceptButton = driver.findElement(By.xpath("//button//div[contains(text(), 'Accept all')]"));
        acceptButton.click();

        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("acodemy");

        searchField.sendKeys(Keys.ENTER);

        assertEquals(driver.getTitle(), "acodemy - Google Search");
    }
}