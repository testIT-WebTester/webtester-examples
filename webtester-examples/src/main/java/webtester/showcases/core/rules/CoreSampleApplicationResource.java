package webtester.showcases.core.rules;

import org.junit.rules.ExternalResource;

import samples.core.CoreSampleApplication;


public class CoreSampleApplicationResource extends ExternalResource {

    private final CoreSampleApplication application;

    public CoreSampleApplicationResource () {
        application = new CoreSampleApplication();
    }

    @Override
    protected void before () {
        application.start();
    }

    @Override
    protected void after () {
        application.stop();
    }

}
