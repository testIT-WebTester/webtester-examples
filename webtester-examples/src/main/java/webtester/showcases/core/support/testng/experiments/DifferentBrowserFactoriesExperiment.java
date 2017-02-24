package webtester.showcases.core.support.testng.experiments;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;
import info.novatec.testit.webtester.browser.factories.InternetExplorerFactory;

import info.novatec.testit.webtester.testng.annotations.CreateUsing;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.annotation.Resource;


@Listeners (WebTesterTestNGListener.class)
public class DifferentBrowserFactoriesExperiment {

    @Resource
    @CreateUsing ( InternetExplorerFactory.class )
    static Browser internetExplorer;

    @Resource
    @CreateUsing( FirefoxFactory.class )
    static Browser firefox;

    @Test
    public void test () {
        internetExplorer.open("http://www.google.de");
        firefox.open("http://www.google.de");
    }

}
