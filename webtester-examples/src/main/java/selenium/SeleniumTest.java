package selenium;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.openqa.selenium.By;


public class SeleniumTest extends AbstractSeleniumTest {

    private static final String VALID_USERNAME = "username";
    private static final String VALID_PASSWORD = "123456";
    private static final String INVALID_USERNAME = "foo";
    private static final String INVALID_PASSWORD = "bar";

    @Test
    public void testValidLogin () {

        executeLogin(VALID_USERNAME, VALID_PASSWORD);

        String headline = webDriver.findElement(By.id("headline")).getText();
        assertThat(headline, is("Hello World!"));

    }

    @Test
    public void testInvalidLogin_Username () {
        executeLogin(INVALID_USERNAME, VALID_PASSWORD);
        assertThatErrorMessageIsDisplayed();
    }

    @Test
    public void testInvalidLogin_Password () {
        executeLogin(VALID_USERNAME, INVALID_PASSWORD);
        assertThatErrorMessageIsDisplayed();
    }

    @Test
    public void testInvalidLogin_Both () {
        executeLogin(INVALID_USERNAME, INVALID_PASSWORD);
        assertThatErrorMessageIsDisplayed();
    }

    /* utilities */

    private void executeLogin (String username, String password) {
        webDriver.findElement(By.id("username")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.id("login")).click();
    }

    private void assertThatErrorMessageIsDisplayed () {
        String errorMessage = webDriver.findElement(By.id("errorMessage")).getText();
        assertThat(errorMessage, is("Wrong Credentials!"));
    }

}
