package webtester.showcases.core.support.junit.Experiments;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;
import info.novatec.testit.webtester.junit.annotations.CreateUsing;
import info.novatec.testit.webtester.junit.annotations.EntryPoint;
import info.novatec.testit.webtester.junit.runner.WebTesterJUnitRunner;


@RunWith ( WebTesterJUnitRunner.class )
public class DifferentBrowserFieldModifiersExperiment {

    @Resource
    static Browser preInitializedBrowser = new FirefoxFactory().createBrowser();

    @Resource
    @EntryPoint ( "http://www.google.com" )
    @CreateUsing ( FirefoxFactory.class )
    static Browser classScopedBrowser;

    @Resource
    @EntryPoint ( "http://www.bing.com" )
    @CreateUsing ( FirefoxFactory.class )
    Browser testScopedBrowser;

    Browser nonResourceBrowser;

    @Test
    public void test1 () {

        assertThat(preInitializedBrowser, is(not(nullValue())));
        assertThat(classScopedBrowser, is(not(nullValue())));
        assertThat(testScopedBrowser, is(not(nullValue())));

        assertThat(nonResourceBrowser, is(nullValue()));

    }

    @Test
    public void test2 () throws InterruptedException {
        Thread.sleep(1000);
    }

}
