package selenium;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.EntryPoints;
import webtester.showcases.core.rules.CoreSampleApplicationResource;


public abstract class AbstractSeleniumTest {

    protected static WebDriver webDriver;

    @ClassRule
    public static ExternalResource demoApplication = new CoreSampleApplicationResource();

    @BeforeClass
    public static void initializeBrowser () {
        webDriver = new FirefoxDriver();
    }

    @Before
    public void navigateToLoginScreen () {
        webDriver.get(EntryPoints.LOGIN);
    }

    @AfterClass
    public static void closeBrowser () {
        webDriver.quit();
    }

}
