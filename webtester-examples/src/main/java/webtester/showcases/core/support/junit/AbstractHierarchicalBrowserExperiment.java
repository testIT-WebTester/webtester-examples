package webtester.showcases.core.support.junit;

import javax.annotation.Resource;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;
import info.novatec.testit.webtester.junit.annotations.CreateUsing;
import info.novatec.testit.webtester.junit.annotations.EntryPoint;


public abstract class AbstractHierarchicalBrowserExperiment {

    @Resource
    @EntryPoint ( "http://www.wikipedia.com" )
    @CreateUsing ( FirefoxFactory.class )
    protected static Browser preInitializedBrowser = new FirefoxFactory().createBrowser();

    @Resource
    @EntryPoint ( "http://www.google.com" )
    @CreateUsing ( FirefoxFactory.class )
    protected static Browser classScopedBrowser;

    @Resource
    @EntryPoint ( "http://www.bing.com" )
    @CreateUsing ( FirefoxFactory.class )
    protected Browser testScopedBrowser;

}
