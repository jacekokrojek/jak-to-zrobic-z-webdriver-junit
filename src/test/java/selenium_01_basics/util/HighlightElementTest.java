package selenium_01_basics.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class HighlightElementTest {

    private WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass
    public void setUp() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.indexOf("win") >= 0) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        }
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
    }

    private void highlightElement(WebElement element, int duration) throws
            InterruptedException {
        String original_style = element.getAttribute("style");
        js.executeScript(
                "arguments[0].setAttribute(arguments[1], arguments[2])",
                element,
                "style",
                "border: 2px solid red; border-style: dashed;");
        if (duration > 0) {
            Thread.sleep(duration * 1000);
            js.executeScript(
                    "arguments[0].setAttribute(arguments[1], arguments[2])",
                    element,
                    "style",
                    original_style);
        }
    }

    @Test
    public void highlightElementTest() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/large");
        WebElement element = driver.findElement(By.id("sibling-2.3"));
        highlightElement(element, 3);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}