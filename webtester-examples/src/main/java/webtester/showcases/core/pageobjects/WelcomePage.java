package webtester.showcases.core.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.enumerations.Method;
import info.novatec.testit.webtester.pageobjects.Headline;
import info.novatec.testit.webtester.pageobjects.Link;
import info.novatec.testit.webtester.pageobjects.PageObject;


public class WelcomePage extends PageObject {

    @IdentifyUsing ( "headline" )
    Headline headline;

    @IdentifyUsing ( method = Method.XPATH, value = ".//a[starts-with(@id, 'msg:')]" )
    List<Link> messages;

    @PostConstruct
    void assertThatCorrectPageIsDisplayed () {
        assertThat(getBrowser().getPageTitle(), is("TestApp: Welcome"));
    }

    public String getWelcomeMessage () {
        return headline.getVisibleText();
    }

    public List<String> getMessageTexts () {
        List<String> messageTexts = new LinkedList<>();
        for (PageObject message : messages) {
            messageTexts.add(message.getVisibleText());
        }
        return messageTexts;
    }

}
