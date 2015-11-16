package webtester.showcases.core.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.PasswordField;
import info.novatec.testit.webtester.pageobjects.TextField;


public class LoginPage extends PageObject {

    @IdentifyUsing ( "username" )
    TextField usernameField;

    @IdentifyUsing ( "password" )
    PasswordField passwordField;

    @IdentifyUsing ( "login" )
    Button loginButton;

    @IdentifyUsing ( "errorMessage" )
    PageObject errorMessage;

    @PostConstruct
    void assertThatCorrectPageIsDisplayed () {
        assertThat(getBrowser().getPageTitle(), is("TestApp: Login"));
    }

    /* workflows */

    public WelcomePage loginWithValidUser () {
        return login("username", "123456");
    }

    public WelcomePage login (String username, String password) {
        return setUsername(username).setPassword(password).clickLogin();
    }

    public LoginPage loginExpectingError (String username, String password) {
        return setUsername(username).setPassword(password).clickExpectingError();
    }

    /* actions */

    public LoginPage setUsername (String username) {
        this.usernameField.setText(username);
        return this;
    }

    public LoginPage setPassword (String password) {
        this.passwordField.setText(password);
        return this;
    }

    public WelcomePage clickLogin () {
        this.loginButton.click();
        return create(WelcomePage.class);
    }

    public LoginPage clickExpectingError () {
        this.loginButton.click();
        return create(LoginPage.class);
    }

    /* getter */

    public String getErrorMessage () {
        return errorMessage.getVisibleText();
    }

}
