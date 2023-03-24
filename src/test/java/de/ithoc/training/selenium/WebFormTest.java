package de.ithoc.training.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class WebFormTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Create driver with the option to allow remote origins.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        // Navigate to this example website which is a form.
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        // Wait a little to ensure website has been loaded.
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    @AfterEach
    public void tearDown() {
        // Clean up session
        driver.quit();
    }


    @Test
    public void startSessionChrome() {

        assertNotNull(driver);
    }

    /**
     * To run this test case successfully we need internet access,
     * as we navigate to a website.
     */
    @Test
    public void readTitle() {
        // Read the title from this website.
        String title = driver.getTitle();

        assertEquals("Web form", title);
    }

    @Test
    public void findTextBox() {
        // Read a web element and check for text.
        WebElement textBox = driver.findElement(By.name("my-text"));

        assertEquals("", textBox.getText());
    }

    @Test
    public void findButton() {
        WebElement button = driver.findElement(By.cssSelector("button"));

        assertEquals("Submit", button.getText());
    }

    /**
     * Scenario: Input field and submit button
     *
     * Find the text box, put some text in and send the form
     * by pressing the button.
     */
    @Test
    public void inputAndSubmit() {
        WebElement textBox = driver.findElement(By.name("my-text"));
        textBox.sendKeys("Learning");

        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);
    }

}
