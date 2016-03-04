package utilities.pageobjects;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;

import javax.annotation.PostConstruct;

import samples.core.model.Registration;
import samples.core.model.enums.Gender;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.Checkbox;
import info.novatec.testit.webtester.pageobjects.Div;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.PasswordField;
import info.novatec.testit.webtester.pageobjects.RadioButton;
import info.novatec.testit.webtester.pageobjects.Select;
import info.novatec.testit.webtester.pageobjects.TextField;


public class RegisterPage extends PageObject {

    @IdentifyUsing("username")
    private TextField username;
    @IdentifyUsing("password")
    private PasswordField password;
    @IdentifyUsing("repeatPassword")
    private PasswordField repeatPassword;

    @IdentifyUsing("gender")
    private Select gender;
    @IdentifyUsing("newsletter")
    private Checkbox newsletter;

    @IdentifyUsing("robot:yes")
    private RadioButton robotYes;
    @IdentifyUsing("robot:no")
    private RadioButton robotNo;

    @IdentifyUsing("register")
    private Button register;

    @IdentifyUsing("errorMessage")
    private Div errorMessage;

    @PostConstruct
    public void assertVisible() {
        assertThat(username).isVisible();
        assertThat(password).isVisible();
        assertThat(repeatPassword).isVisible();
    }

    /* workflow */

    public WelcomePage register(Registration registration) {
        return fillFields(registration).clickRegister();
    }

    public RegisterPage registerExpectingError(Registration registration) {
        return fillFields(registration).clickRegisterExpectingError();
    }

    private RegisterPage fillFields(Registration registration) {
        return setUsername(registration.getUsername())
            .setPassword(registration.getPassword())
            .setRepeatPassword(registration.getRepeatPassword())
            .select(registration.getGender())
            .setNewsletter(registration.getNewsletter())
            .setRobot(registration.getRobot());
    }

    /* action */

    public RegisterPage setUsername(String name) {
        this.username.setText(name);
        return this;
    }

    public RegisterPage setPassword(String password) {
        this.password.setText(password);
        return this;
    }

    public RegisterPage setRepeatPassword(String password) {
        this.repeatPassword.setText(password);
        return this;
    }

    public RegisterPage select(Gender gender) {
        this.gender.selectByValue(gender.getValue());
        return this;
    }

    public RegisterPage setNewsletter(boolean selection) {
        this.newsletter.setSelection(selection);
        return this;
    }

    private RegisterPage setRobot(boolean robot) {
        if(robot){
            return selectRobotYes();
        }
        return selectRobotNo();
    }

    public RegisterPage selectRobotYes() {
        this.robotYes.select();
        return this;
    }

    public RegisterPage selectRobotNo() {
        this.robotNo.select();
        return this;
    }

    /* navigation */

    public WelcomePage clickRegister() {
        this.register.click();
        return create(WelcomePage.class);
    }

    public RegisterPage clickRegisterExpectingError() {
        this.register.click();
        return create(RegisterPage.class);
    }

    /* getter */

    public Div errorMessage() {
        return errorMessage;
    }

}
