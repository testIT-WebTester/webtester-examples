package utilities.pageobjects;

import static info.novatec.testit.webtester.support.assertj.WebTesterAssertions.assertThat;

import javax.annotation.PostConstruct;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.Headline;
import info.novatec.testit.webtester.pageobjects.PageObject;


public class WelcomePage extends PageObject {

    @IdentifyUsing("headline")
    private Headline headline;

    @PostConstruct
    public void assertVisible(){
        assertThat(headline).isVisible();
    }

    /* getter */

    public Headline headline() {
        return headline;
    }

}
