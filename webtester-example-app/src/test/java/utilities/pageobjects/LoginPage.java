package utilities.pageobjects;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;

import javax.annotation.PostConstruct;

import samples.core.model.User;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.Div;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.PasswordField;
import info.novatec.testit.webtester.pageobjects.TextField;


public class LoginPage extends PageObject {

    @IdentifyUsing("username")
    private TextField username;
    @IdentifyUsing("password")
    private PasswordField password;
    @IdentifyUsing("login")
    private Button login;

    @IdentifyUsing("errorMessage")
    private Div errorMessage;

    @PostConstruct
    public void assertVisible(){
        assertThat(username).isVisible();
        assertThat(password).isVisible();
        assertThat(login).isVisible();
    }

    /* workflow */

    public WelcomePage login(User user){
        return fillFields(user).clickLogin();
    }

    public LoginPage loginExpectingError(User user){
        return fillFields(user).clickLoginExpectingError();
    }

    private LoginPage fillFields(User user) {
        return setUsername(user.getName()).setPassword(user.getPassword());
    }

    /* action */

    public LoginPage setUsername(String name) {
        this.username.setText(name);
        return this;
    }

    public LoginPage setPassword(String password) {
        this.password.setText(password);
        return this;
    }

    /* navigation */

    public WelcomePage clickLogin() {
        this.login.click();
        return create(WelcomePage.class);
    }

    public LoginPage clickLoginExpectingError() {
        this.login.click();
        return create(LoginPage.class);
    }

    /* getter */

    public Div errorMessage() {
        return errorMessage;
    }

}
