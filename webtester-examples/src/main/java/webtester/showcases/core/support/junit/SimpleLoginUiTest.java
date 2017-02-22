package webtester.showcases.core.support.junit;

import static info.novatec.testit.webtester.utils.Identifications.name;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;
import info.novatec.testit.webtester.junit.annotations.CreateUsing;
import info.novatec.testit.webtester.junit.annotations.EntryPoint;
import info.novatec.testit.webtester.junit.runner.WebTesterJUnitRunner;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.Form;
import utils.EntryPoints;
import webtester.showcases.core.pageobjects.LoginPage;
import webtester.showcases.core.pageobjects.WelcomePage;
import webtester.showcases.core.rules.CoreSampleApplicationResource;


@RunWith ( WebTesterJUnitRunner.class )
public class SimpleLoginUiTest {

    private static final String VALID_USERNAME = "username";
    private static final String VALID_PASSWORD = "123456";
    private static final String INVALID_USERNAME = "foo";
    private static final String INVALID_PASSWORD = "bar";

    @ClassRule
    public static ExternalResource demoApplication = new CoreSampleApplicationResource();

    @Resource
    @EntryPoint ( EntryPoints.LOGIN )
    @CreateUsing ( FirefoxFactory.class )
    private static Browser browser;

    private LoginPage loginPage;

    @Before
    public void initStartPage () {
        loginPage = browser.create(LoginPage.class);
    }

    @Test
    public void testValidLogin () {
        WelcomePage page = loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        assertThat(page.getWelcomeMessage(), is("Hello World!"));
    }

    @Test
    public void testInvalidLogin_Username () {
        LoginPage page = loginPage.loginExpectingError(INVALID_USERNAME, VALID_PASSWORD);
        assertThat(page.getErrorMessage(), is("Wrong Credentials!"));
    }

    @Test
    public void testInvalidLogin_Password () {
        LoginPage page = loginPage.loginExpectingError(VALID_USERNAME, INVALID_PASSWORD);
        assertThat(page.getErrorMessage(), is("Wrong Credentials!"));
    }

    @Test
    public void testInvalidLogin_Both () {
        LoginPage page = loginPage.loginExpectingError(INVALID_USERNAME, INVALID_PASSWORD);
        assertThat(page.getErrorMessage(), is("Wrong Credentials!"));
    }

    @Test
    public void testUsingAdHocPageObjects_IdentifierFirst () {

        Form form = browser.findBy(name("loginForm")).as(Form.class);

        form.find("#username").sendKeys(VALID_USERNAME);
        form.find("#password").sendKeys(VALID_PASSWORD);
        form.find("#login").as(Button.class).click();

        WelcomePage page = browser.create(WelcomePage.class);
        assertThat(page.getWelcomeMessage(), is("Hello World!"));

    }

    @Test
    public void testUsingAdHocPageObjects_TypeFirst () {

        Form form = browser.find(Form.class).by(name("loginForm"));

        form.find("#username").sendKeys(VALID_USERNAME);
        form.find("#password").sendKeys(VALID_PASSWORD);
        form.find("#login").as(Button.class).click();

        WelcomePage page = browser.create(WelcomePage.class);
        assertThat(page.getWelcomeMessage(), is("Hello World!"));

    }

}
