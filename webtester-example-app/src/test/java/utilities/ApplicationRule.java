package utilities;

import org.junit.rules.ExternalResource;

import samples.core.CoreSampleApplication;


public class ApplicationRule extends ExternalResource {

    private CoreSampleApplication application = new CoreSampleApplication();

    @Override
    protected void before() throws Throwable {
        application.start();
    }

    @Override
    protected void after() {
        application.stop();
    }

}
