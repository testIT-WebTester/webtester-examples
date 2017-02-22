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


@Listeners(WebTesterTestNGListener.class)
public class SourceCodeUiTest {

    /* If you want to save the current HTML source code of a displayed page, you
     * can do so by calling the {@link Browser} saveSourceCode(...) methods. */


    @Resource
    @EntryPoint( EntryPoints.LOGIN )
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
    public void saveSourceCode () {

        // default folder and naming
        browser.saveSourceCode();

        changePageContent();

        // custom folder and default naming
        browser.saveSourceCode("testartifacts/" + getClass().getSimpleName());

    }

    private void changePageContent () {
        browser.create(LoginPage.class).loginWithValidUser();
    }

}
