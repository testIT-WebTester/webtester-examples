package selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    @FindBy ( how = How.ID, using = "username" )
    WebElement usernameField;
    @FindBy ( how = How.ID, using = "password" )
    WebElement passwordField;
    @FindBy ( how = How.ID, using = "login" )
    WebElement loginButton;
    @FindBy ( how = How.ID, using = "errorMessage" )
    WebElement errorMessage;

    WebDriver driver;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
        if ( ! "TestApp: Login".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the login page");
        }
    }

    /* workflows */

    public WelcomePage login (String username, String password) {
        return setUsername(username).setPassword(password).clickLogin();
    }

    public LoginPage loginExpectingError (String username, String password) {
        return setUsername(username).setPassword(password).clickExpectingError();
    }

    /* actions */

    public LoginPage setUsername (String username) {
        this.usernameField.sendKeys(username);
        return this;
    }

    public LoginPage setPassword (String password) {
        this.passwordField.sendKeys(password);
        return this;
    }

    public WelcomePage clickLogin () {
        this.loginButton.click();
        return PageFactory.initElements(driver, WelcomePage.class);
    }

    public LoginPage clickExpectingError () {
        this.loginButton.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }

    /* getter */

    public String getErrorMessage () {
        return errorMessage.getText();
    }

}
