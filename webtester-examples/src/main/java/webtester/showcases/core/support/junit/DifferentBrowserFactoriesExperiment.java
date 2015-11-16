package webtester.showcases.core.support.junit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;
import info.novatec.testit.webtester.browser.factories.InternetExplorerFactory;
import info.novatec.testit.webtester.junit.annotations.CreateUsing;
import info.novatec.testit.webtester.junit.runner.WebTesterJUnitRunner;


@RunWith ( WebTesterJUnitRunner.class )
public class DifferentBrowserFactoriesExperiment {

    @Resource
    @CreateUsing ( FirefoxFactory.class )
    static Browser firefox;

    @Resource
    @CreateUsing ( InternetExplorerFactory.class )
    static Browser internetExplorer;

    @Test
    public void test () {
        firefox.open("http://www.google.de");
        internetExplorer.open("http://www.google.de");
    }

}
