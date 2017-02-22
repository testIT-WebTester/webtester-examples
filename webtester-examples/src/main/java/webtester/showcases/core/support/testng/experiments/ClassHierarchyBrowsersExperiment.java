package webtester.showcases.core.support.testng.experiments;

import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners (WebTesterTestNGListener.class)
public class ClassHierarchyBrowsersExperiment extends AbstractHierarchicalBrowserExperiment {

    @Test
    public void test1 () throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    public void test2 () throws InterruptedException {
        Thread.sleep(1000);
    }

}
