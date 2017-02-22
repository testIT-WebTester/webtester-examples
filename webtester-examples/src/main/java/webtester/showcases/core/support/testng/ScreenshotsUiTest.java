package webtester.showcases.core.support.testng;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;

import info.novatec.testit.webtester.testng.annotations.CreateUsing;
import info.novatec.testit.webtester.testng.annotations.EntryPoint;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

import org.testng.annotations.*;

import samples.core.CoreSampleApplication;
import utils.EntryPoints;
import webtester.showcases.core.pageobjects.RandomInteractionsPage;

import javax.annotation.Resource;


@Listeners(WebTesterTestNGListener.class)
public class ScreenshotsUiTest {

    /* If you want to save a screenshot of the currently displayed page, you can
     * do so by calling the {@link Browser} <code>takeScreenshot(...)</code>
     * methods. */

    @Resource
    @EntryPoint( EntryPoints.RANDOM )
    @CreateUsing( FirefoxFactory.class )
    private Browser browser;

    private final CoreSampleApplication application = new CoreSampleApplication();

    @BeforeClass
    public void startApplication() {
        application.start();
    }

    @AfterClass
    public void stopApplication() {
        application.stop();
    }

    @Test
    public void takeScreenshot () {

        // default folder an naming
        browser.takeScreenshot();

        changePageContent();

        // custom folder and default naming
        browser.takeScreenshot("testartifacts/" + getClass().getSimpleName());

    }

    private void changePageContent () {
        RandomInteractionsPage page = browser.create(RandomInteractionsPage.class);
        for (int i = 0; i < 3; i ++ ) {
            page.clickRandomButton().setTextOfRandomTextField();
        }
    }
}
