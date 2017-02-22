package tests.testng;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;

import info.novatec.testit.webtester.testng.annotations.ConfigurationValue;
import info.novatec.testit.webtester.testng.annotations.CreateUsing;
import info.novatec.testit.webtester.testng.annotations.Primary;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import samples.core.CoreSampleApplication;
import samples.core.model.User;
import utilities.TestData;
import utilities.pageobjects.LoginPage;
import utilities.pageobjects.WelcomePage;

import javax.annotation.Resource;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;

@Listeners(WebTesterTestNGListener.class)
public class LoginUITest {

    private CoreSampleApplication application = new CoreSampleApplication();

    @ConfigurationValue("entrypoint.login")
    private static String loginUrl;

    @Primary
    @Resource
    @CreateUsing(FirefoxFactory.class)
    private static Browser browser;

    @BeforeMethod
    public void startApplication() {
        application.start();
    }

    @AfterMethod
    public void stopApplication() {
        application.stop();
    }

    @Test
    public final void existingUserWithCorrectPasswordCanLogIn(){
        User user = TestData.newValidUser().build();
        WelcomePage page = openLoginPage().login(user);
        assertThat(page.headline()).hasVisibleText("Hello World!");
    }

    @Test
    public final void existingUserWithWrongPasswordCantLogIn(){

        User user = TestData.newValidUser()
                .withPassword("wrong")
                .build();

        LoginPage page = openLoginPage().loginExpectingError(user);

        assertThat(page.errorMessage()).isVisible();
        assertThat(page.errorMessage()).hasVisibleText("Wrong Credentials!");

    }

    @Test
    public final void nonExistingUserCantLogIn(){

        User user = TestData.newValidUser()
                .withUsername("unknown")
                .build();

        LoginPage page = openLoginPage().loginExpectingError(user);

        assertThat(page.errorMessage()).isVisible();
        assertThat(page.errorMessage()).hasVisibleText("Wrong Credentials!");

    }

    private LoginPage openLoginPage() {
        return browser.open(loginUrl, LoginPage.class);
    }
}
