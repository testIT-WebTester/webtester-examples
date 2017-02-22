package webtester.showcases.core.support.testng;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.api.events.EventListener;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;
import info.novatec.testit.webtester.eventsystem.EventSystem;
import info.novatec.testit.webtester.eventsystem.events.pageobject.ClickedEvent;
import info.novatec.testit.webtester.eventsystem.events.pageobject.TextSetEvent;

import info.novatec.testit.webtester.testng.annotations.CreateUsing;
import info.novatec.testit.webtester.testng.annotations.EntryPoint;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.annotations.*;

import samples.core.CoreSampleApplication;
import utils.EntryPoints;
import webtester.showcases.core.pageobjects.RandomInteractionsPage;

import javax.annotation.Resource;


@Listeners(WebTesterTestNGListener.class)
public class EventSystemUiTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventSystemUiTest.class);

    /* You can register {@link EventListener} objects at the {@link
     * EventSystem}. The listener's {@link EventListener#eventOccurred(Event)
     * eventOccurred(..)} method will be invoked every time an {@link Event} is
     * fired by the framework. An event can be anything from a click of a button
     * or the setting of a text value to an occurring exception.
     * 
     * If an {@link EventListener} can be instantiated with a default
     * constructor you can also activate it in the {@link
     * WebTesterConfiguration} by setting its fully qualified class name in the
     * 'testit-webtester.properties' file. */


    @Resource
    @EntryPoint( EntryPoints.RANDOM )
    @CreateUsing( FirefoxFactory.class )
    private Browser browser;

    private final CoreSampleApplication application = new CoreSampleApplication();

    @BeforeClass
    public void startApplication() {
        application.start();
    }

    @AfterClass
    public void stopApplication() {
        application.stop();
    }

    @BeforeMethod
    public void registerEventListeners () {
        EventSystem.clearListeners();
        EventSystem.registerListener(new EventListener() {

            @Override
            public void eventOccurred (Event event) {
                if (event instanceof ClickedEvent) {
                    LOGGER.info(event.getEventMessage());
                }
            }

        });
        EventSystem.registerListener(new EventListener() {

            @Override
            public void eventOccurred (Event event) {
                if (event instanceof TextSetEvent) {
                    LOGGER.info(event.getEventMessage());
                }
            }

        });
    }

    @AfterMethod
    public void clearEventListeners () {
        EventSystem.clearListeners();
    }

    @Test
    public void demonstrateEventsWithRandomActions () {
        for (int i = 0; i < 25; i ++ ) {
            browser.create(RandomInteractionsPage.class).clickRandomButton().setTextOfRandomTextField();
        }
    }

}
