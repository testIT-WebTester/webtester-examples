package webtester.showcases.core;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;
import info.novatec.testit.webtester.junit.annotations.CreateUsing;
import info.novatec.testit.webtester.junit.annotations.EntryPoint;
import info.novatec.testit.webtester.junit.runner.WebTesterJUnitRunner;
import utils.EntryPoints;
import webtester.showcases.core.pageobjects.LoginPage;
import webtester.showcases.core.rules.CoreSampleApplicationResource;


@RunWith ( WebTesterJUnitRunner.class )
public class BrowserNavigationUiTest {

    /* The navigation API of Selenium is encapsulated inside the class {@link
     * Navigation} accessible with webDriver.navigate(). We included the most
     * common navigation operations as part of the {@link Browser} API. */

    @Rule
    public ExternalResource demoApplication = new CoreSampleApplicationResource();

    @Resource
    @EntryPoint ( EntryPoints.LOGIN )
    @CreateUsing ( FirefoxFactory.class )
    private Browser browser;

    @Before
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
