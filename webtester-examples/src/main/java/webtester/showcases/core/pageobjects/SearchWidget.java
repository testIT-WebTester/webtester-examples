package webtester.showcases.core.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.PageObject;
import info.novatec.testit.webtester.pageobjects.TextField;


public class SearchWidget extends PageObject {

    /* This widget is used twice on the widget page but has only been defined
     * once. Since the search context of page objects is limited when they are
     * nested into each other the query text field and search button can still
     * be uniquely identified even if just the last part of the ID is provided. */

    @IdentifyUsing ( method = Method.ID_ENDS_WITH, value = "query" )
    TextField queryField;

    @IdentifyUsing ( method = Method.ID_ENDS_WITH, value = "search" )
    Button searchButton;

    @PostConstruct
    void assertThatCorrectWidgetIsDisplayed () {
        assertThat(queryField.isVisible(), is(true));
        assertThat(searchButton.isVisible(), is(true));
    }

    public String getQueryText () {
        return queryField.getText();
    }

    public SearchWidget setQueryText (String query) {
        queryField.setText(query);
        return this;
    }

    public SearchWidget clickSearchExpectingNoResults () {
        searchButton.click();
        return create(SearchWidget.class);
    }

    public SearchWidget searchWithoutResult (String query) {
        return setQueryText(query).clickSearchExpectingNoResults();
    }

}
