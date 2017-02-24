package webtester.showcases.core.support.junit.Experiments;

import org.junit.Test;
import org.junit.runner.RunWith;

import info.novatec.testit.webtester.junit.runner.WebTesterJUnitRunner;


@RunWith ( WebTesterJUnitRunner.class )
public class ClassHirarchyBrowsersExperiment extends AbstractHierarchicalBrowserExperiment {

    @Test
    public void test1 () throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    public void test2 () throws InterruptedException {
        Thread.sleep(1000);
    }

}
