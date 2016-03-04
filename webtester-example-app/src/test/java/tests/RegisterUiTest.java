package tests;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;

import javax.annotation.Resource;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import samples.core.model.Registration;
import samples.core.model.enums.Gender;
import utilities.ApplicationRule;
import utilities.TestData;
import utilities.pageobjects.RegisterPage;
import utilities.pageobjects.WelcomePage;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;
import info.novatec.testit.webtester.junit.annotations.CreateUsing;
import info.novatec.testit.webtester.junit.runner.WebTesterJUnitRunner;


@RunWith(WebTesterJUnitRunner.class)
public class RegisterUiTest {

    @ClassRule
    public static ApplicationRule application = new ApplicationRule();

    @Resource
    @CreateUsing(FirefoxFactory.class)
    private static Browser browser;

    @Test
    public final void registeringNewUserWithAllRequiredData(){
        Registration registration = TestData.newValidRegistration().build();
        WelcomePage page = openRegisterPage().register(registration);
        assertThat(page.headline()).hasVisibleText("Hello World!");
    }

    @Test
    public final void registeringNewUserWithAllData(){

        Registration registration = TestData.newValidRegistration()
            .withUsername("user")
            .withPasswords("password")
            .with(Gender.FEMALE)
            .withNewsletter()
            .asNonRobot()
            .build();

        WelcomePage page = openRegisterPage().register(registration);
        assertThat(page.headline()).hasVisibleText("Hello World!");

    }

    @Test
    public final void registeringUserWithoutNameDisplaysError(){

        Registration registration = TestData.newValidRegistration()
            .withoutUsername()
            .build();

        RegisterPage page = openRegisterPage().registerExpectingError(registration);

        assertThat(page.errorMessage()).isVisible();
        assertThat(page.errorMessage()).hasVisibleText("username is required!");

    }

    @Test
    public final void registeringUserWithoutPasswordDisplaysError(){

        Registration registration = TestData.newValidRegistration()
            .withoutPassword()
            .build();

        RegisterPage page = openRegisterPage().registerExpectingError(registration);

        assertThat(page.errorMessage()).isVisible();
        assertThat(page.errorMessage()).hasVisibleText("password is required!");

    }

    @Test
    public final void registeringUserWithoutRepeatPasswordDisplaysError(){

        Registration registration = TestData.newValidRegistration()
            .withoutRepeatPassword()
            .build();

        RegisterPage page = openRegisterPage().registerExpectingError(registration);

        assertThat(page.errorMessage()).isVisible();
        assertThat(page.errorMessage()).hasVisibleText("repeat password is required!");

    }

    @Test
    public final void registeringUserWithUnequalPasswordsDisplaysError(){

        Registration registration = TestData.newValidRegistration()
            .withPassword("password1")
            .withRepeatPassword("password2")
            .build();

        RegisterPage page = openRegisterPage().registerExpectingError(registration);

        assertThat(page.errorMessage()).isVisible();
        assertThat(page.errorMessage()).hasVisibleText("passwords need to be equal!");

    }

    @Test
    public final void registeringRobotUserDisplaysError() {

        Registration registration = TestData.newValidRegistration()
            .asRobot()
            .build();

        RegisterPage page = openRegisterPage().registerExpectingError(registration);

        assertThat(page.errorMessage()).isVisible();
        assertThat(page.errorMessage()).hasVisibleText("no robots allowed!");

    }

    private RegisterPage openRegisterPage() {
        String entryPoint = browser.getConfiguration().getStringProperty("entrypoint.register");
        return browser.open(entryPoint, RegisterPage.class);
    }

}
