package selenium_02_page_object_pattern.tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import selenium_02_page_object_pattern.pageobjects.Login;

public class TestLogin {

    private static WebDriver driver;
    private static Login login;

    @Before
    public void setUp() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.indexOf("win") >= 0) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        }

        driver = new ChromeDriver();
        login = new Login(driver);
    }

    @Test
    public void whenLoggedInWithCorrectCredentials_thenSuccessVisible(){
        login.with("tomsmith", "SuperSecretPassword!");
        assertTrue( login.successMessagePresent());
    }

    /**
     * Fill the content of the test below
     */
    @Test
    public void whenLoggedInWithIncorrectCredentials_thenInvalidPasswordVisible() {
        login.with("tomsmith", "SuperSecretPasswod!");

        for (char ch: login.getErrorMessage().toCharArray()) {
            System.out.print((int)ch + ",");
        }
        String expected = "Your password is invalid!\n" + (char)215;
        System.out.println("");
        for (char ch: expected.toCharArray()) {
            System.out.print((int)ch + ",");
        }
        assertEquals(expected, login.getErrorMessage());
    }

    @Test
    public void incorrectCredentialsLoginTest() {
        login.with("tomsmit", "SuperSecretPasswod!!");

        for (char ch: login.getErrorMessage().toCharArray()) {
            System.out.print((int)ch + ",");
        }
        String expected = "Your username is invalid!\n" + (char)215;
        System.out.println("");
        for (char ch: expected.toCharArray()) {
            System.out.print((int)ch + ",");
        }
        assertEquals(expected, login.getErrorMessage());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
