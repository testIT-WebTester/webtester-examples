package webtester.showcases.core.support.junit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
import webtester.showcases.core.pageobjects.SearchWidget;
import webtester.showcases.core.pageobjects.WidgetsPage;
import webtester.showcases.core.rules.CoreSampleApplicationResource;


@RunWith ( WebTesterJUnitRunner.class )
public class NestedPageObjectsAndWidgetsUiTest {

    @Rule
    public ExternalResource demoApplication = new CoreSampleApplicationResource();

    @Resource
    @EntryPoint ( EntryPoints.WIDGETS )
    @CreateUsing ( FirefoxFactory.class )
    private Browser browser;

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
