package de.ithoc.training.linkz;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {

    @Test
    public void registerSuccessfully() {

        // Create web driver instance using remote origins.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        // Navigate to website under test.
        String url = "http://localhost:8080/register";
        driver.get(url);

        // Prepare test data (here username and password).
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);


        // Run test scenario

        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.sendKeys(uuid);

        WebElement password1Element = driver.findElement(By.id("password1"));
        password1Element.sendKeys(uuid);

        WebElement password2Element = driver.findElement(By.id("password2"));
        password2Element.sendKeys(uuid);

        WebElement buttonElement = driver.findElement(By.tagName("button"));
        buttonElement.click();


        // Check expected results (URL and message text).

        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:8080/?registered=true", currentUrl);

        WebElement messageElement = driver.findElement(By.className("message-body"));
        assertEquals("You are now registered. Unfortnuately, there is no login yet...", messageElement.getText());

        driver.quit();
    }


    @Test
    public void registerPasswordTooShort() {

        // Create web driver instance using remote origins.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        // Navigate to website under test.
        String url = "http://localhost:8080/register";
        driver.get(url);

        // Prepare test data (here username and password).
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);


        // Run test scenario

        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.sendKeys(uuid);
        String password = uuid.substring(uuid.length() - 4);

        WebElement password1Element = driver.findElement(By.id("password1"));
        password1Element.sendKeys(password);

        WebElement password2Element = driver.findElement(By.id("password2"));
        password2Element.sendKeys(password);

        WebElement buttonElement = driver.findElement(By.tagName("button"));
        buttonElement.click();


        // Check expected results (URL and message text).

        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:8080/register", currentUrl);

        driver.quit();
    }


    @Test
    public void registerPasswordsMismatch() {

        // Create web driver instance using remote origins.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        // Navigate to website under test.
        String url = "http://localhost:8080/register";
        driver.get(url);

        // Prepare test data (here username and password).
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);


        // Run test scenario

        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.sendKeys(uuid);
        String password1 = uuid.substring(uuid.length() - 5);
        String password2 = uuid.substring(uuid.length() - 6);

        WebElement password1Element = driver.findElement(By.id("password1"));
        password1Element.sendKeys(password1);

        WebElement password2Element = driver.findElement(By.id("password2"));
        password2Element.sendKeys(password2);

        WebElement buttonElement = driver.findElement(By.tagName("button"));
        buttonElement.click();


        // Check expected results (URL and message text).

        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:8080/register", currentUrl);

        WebElement errorMessage = driver.findElement(By.xpath("//p[@class='help is-danger']"));
        assertEquals("Does not match", errorMessage.getText());

        driver.quit();
    }

}
