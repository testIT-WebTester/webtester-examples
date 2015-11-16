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
import webtester.showcases.core.pageobjects.LoginPage;
import webtester.showcases.core.rules.CoreSampleApplicationResource;


@RunWith ( WebTesterJUnitRunner.class )
public class SourceCodeUiTest {

    /* If you want to save the current HTML source code of a displayed page, you
     * can do so by calling the {@link Browser} saveSourceCode(...) methods. */

    @Rule
    public ExternalResource demoApplication = new CoreSampleApplicationResource();

    @Resource
    @EntryPoint ( EntryPoints.LOGIN )
    @CreateUsing ( FirefoxFactory.class )
    private Browser browser;

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
