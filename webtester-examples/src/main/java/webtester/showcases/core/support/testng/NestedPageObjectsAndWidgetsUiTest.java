package webtester.showcases.core.support.testng;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;

import info.novatec.testit.webtester.testng.annotations.CreateUsing;
import info.novatec.testit.webtester.testng.annotations.EntryPoint;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

import org.testng.annotations.*;

import samples.core.CoreSampleApplication;
import utils.EntryPoints;
import webtester.showcases.core.pageobjects.SearchWidget;
import webtester.showcases.core.pageobjects.WidgetsPage;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@Listeners(WebTesterTestNGListener.class)
public class NestedPageObjectsAndWidgetsUiTest {

    @Resource
    @EntryPoint( EntryPoints.WIDGETS )
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
    public void demonstrateThatWidgetsCanBeInteractedWithIndependentlyFromOneAnother () {

        WidgetsPage widgetPage = browser.create(WidgetsPage.class);

        SearchWidget firstSearchWidget = widgetPage.getFirstSearchWidget();
        SearchWidget secondSearchWidget = widgetPage.getSecondSearchWidget();

        firstSearchWidget.setQueryText("first query");
        secondSearchWidget.setQueryText("second query");

        assertThat(firstSearchWidget.getQueryText(), is("first query"));
        assertThat(secondSearchWidget.getQueryText(), is("second query"));

    }

}
