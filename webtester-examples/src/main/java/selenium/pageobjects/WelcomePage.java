package selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class WelcomePage {

    @FindBy ( how = How.ID, using = "headline" )
    WebElement headline;

    WebDriver driver;

    public WelcomePage (WebDriver driver) {
        this.driver = driver;
        if ( ! "TestApp: Welcome".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the welcome page");
        }
    }

    public String getWelcomeMessage () {
        return headline.getText();
    }

}
