package webtester.showcases.core.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.PageObject;


public class WidgetsPage extends PageObject {

    /* This page defines two widgets of the custom class SearchWidget both can
     * be interacted with independently from each other. */

    @IdentifyUsing ( "fooSearchWidget" )
    SearchWidget firstSearchWidget;

    @IdentifyUsing ( "barSearchWidget" )
    SearchWidget secondSearchWidget;

    @PostConstruct
    void assertThatCorrectPageIsDisplayed () {
        assertThat(getBrowser().getPageTitle(), is("TestApp: Widgets"));
    }

    public SearchWidget getFirstSearchWidget () {
        return firstSearchWidget;
    }

    public SearchWidget getSecondSearchWidget () {
        return secondSearchWidget;
    }

}
