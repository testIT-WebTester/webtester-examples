package webtester.showcases.core.support.testng;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;

import info.novatec.testit.webtester.testng.annotations.CreateUsing;
import info.novatec.testit.webtester.testng.annotations.EntryPoint;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

import org.testng.annotations.*;

import samples.core.CoreSampleApplication;
import utils.EntryPoints;
import webtester.showcases.core.pageobjects.LoginPage;

import javax.annotation.Resource;


@Listeners (WebTesterTestNGListener.class)
public class BrowserNavigationUiTest {

    /* The navigation API of Selenium is encapsulated inside the class {@link
     * Navigation} accessible with webDriver.navigate(). We included the most
     * common navigation operations as part of the {@link Browser} API. */

    private final CoreSampleApplication application = new CoreSampleApplication();

    @BeforeClass
    public void startApplication() {
        application.start();
    }

    @AfterClass
    public void stopApplication() {
        application.stop();
    }

    @Resource
    @EntryPoint( EntryPoints.LOGIN )
    @CreateUsing( FirefoxFactory.class )
    private Browser browser;

    @BeforeMethod
    public void initStartPage () {
        browser.create(LoginPage.class).loginWithValidUser();
    }

    @Test
    public void navigateBackAndForward () throws InterruptedException {
        waitShortly();
        browser.navigateBackwards();
        waitShortly();
        browser.navigateForwards();
        waitShortly();
    }

    private void waitShortly () throws InterruptedException {
        Thread.sleep(1000);
    }

}
