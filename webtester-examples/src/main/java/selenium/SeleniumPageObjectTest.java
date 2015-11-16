package selenium;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import selenium.pageobjects.LoginPage;
import selenium.pageobjects.WelcomePage;


public class SeleniumPageObjectTest extends AbstractSeleniumTest {

    private static final String VALID_USERNAME = "username";
    private static final String VALID_PASSWORD = "123456";
    private static final String INVALID_USERNAME = "foo";
    private static final String INVALID_PASSWORD = "bar";

    private LoginPage startPage;

    @Before
    public void initStartPage () {
        startPage = PageFactory.initElements(webDriver, LoginPage.class);
    }

    @Test
    public void testValidLogin () {
        WelcomePage page = startPage.login(VALID_USERNAME, VALID_PASSWORD);
        assertThat(page.getWelcomeMessage(), is("Hello World!"));
    }

    @Test
    public void testInvalidLogin_Username () {
        LoginPage page = startPage.loginExpectingError(INVALID_USERNAME, VALID_PASSWORD);
        assertThat(page.getErrorMessage(), is("Wrong Credentials!"));
    }

    @Test
    public void testInvalidLogin_Password () {
        LoginPage page = startPage.loginExpectingError(VALID_USERNAME, INVALID_PASSWORD);
        assertThat(page.getErrorMessage(), is("Wrong Credentials!"));
    }

    @Test
    public void testInvalidLogin_Both () {
        LoginPage page = startPage.loginExpectingError(INVALID_USERNAME, INVALID_PASSWORD);
        assertThat(page.getErrorMessage(), is("Wrong Credentials!"));
    }

}
