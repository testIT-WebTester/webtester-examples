package tests.testng;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;
import info.novatec.testit.webtester.testng.annotations.CreateUsing;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import samples.core.CoreSampleApplication;
import samples.core.model.Registration;
import samples.core.model.enums.Gender;

import utilities.ApplicationRule;
import utilities.TestData;
import utilities.pageobjects.RegisterPage;
import utilities.pageobjects.WelcomePage;

import javax.annotation.Resource;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;

@Listeners(WebTesterTestNGListener.class)
public class RegisterUiTest {

    private CoreSampleApplication application = new CoreSampleApplication();

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
