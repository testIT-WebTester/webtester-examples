package webtester.showcases.core.support.testng.experiments;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;

import info.novatec.testit.webtester.testng.annotations.CreateUsing;
import info.novatec.testit.webtester.testng.annotations.EntryPoint;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Listeners (WebTesterTestNGListener.class)
public class DifferentBrowserFieldModifiersExperiment {

    @Resource
    static Browser preInitializedBrowser = new FirefoxFactory().createBrowser();

    @Resource
    @EntryPoint( "http://www.google.com" )
    @CreateUsing( FirefoxFactory.class )
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
