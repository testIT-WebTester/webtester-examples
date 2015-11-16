package webtester.showcases.core;

import javax.annotation.Resource;

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
import webtester.showcases.core.pageobjects.RandomInteractionsPage;
import webtester.showcases.core.rules.CoreSampleApplicationResource;


@RunWith ( WebTesterJUnitRunner.class )
public class ScreenshotsUiTest {

    /* If you want to save a screenshot of the currently displayed page, you can
     * do so by calling the {@link Browser} <code>takeScreenshot(...)</code>
     * methods. */

    @Rule
    public ExternalResource demoApplication = new CoreSampleApplicationResource();

    @Resource
    @EntryPoint ( EntryPoints.RANDOM )
    @CreateUsing ( FirefoxFactory.class )
    private Browser browser;

    @Test
    public void takeScrenshot () {

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
