package tests;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;

import javax.annotation.Resource;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import utilities.TestData;
import utilities.pageobjects.LoginPage;
import utilities.pageobjects.WelcomePage;
import utilities.ApplicationRule;
import samples.core.model.User;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;
import info.novatec.testit.webtester.junit.annotations.ConfigurationValue;
import info.novatec.testit.webtester.junit.annotations.CreateUsing;
import info.novatec.testit.webtester.junit.runner.WebTesterJUnitRunner;


@RunWith(WebTesterJUnitRunner.class)
public class LoginUiTest {

    @ClassRule
    public static ApplicationRule application = new ApplicationRule();

    @ConfigurationValue("entrypoint.login")
    private static String loginUrl;

    @Resource
    @CreateUsing(FirefoxFactory.class)
    private static Browser browser;

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
