package lv.acodemy;

import io.github.bonigarcia.wdm.WebDriverManager;
import lv.acodemy.page_object.InventoryPage;
import lv.acodemy.page_object.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static lv.acodemy.constants.Generic.SAUCE_URL;

public class TestSauceDemo {

    ChromeDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeMethod
    public void initialize() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // --headless parameter is needed to run it on Ubuntu (without GUI)
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.get(SAUCE_URL);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Test
    public void authorizeTest() {
        loginPage.authorize("standard_user", "secret_sauce");
        Assert.assertEquals(inventoryPage.getTitleElement().getText(), "PRODUCTS");
    }

    @Test
    public void openProductTest() {
        loginPage.authorize("standard_user", "secret_sauce");
        Assert.assertEquals(inventoryPage.getTitleElement().getText(), "PRODUCTS");
        inventoryPage.clickOnProductByLabel("Sauce Labs Bolt T-Shirt");
        System.out.println();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}