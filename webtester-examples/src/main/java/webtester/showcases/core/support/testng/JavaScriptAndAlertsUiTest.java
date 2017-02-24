package webtester.showcases.core.support.testng;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.browser.factories.FirefoxFactory;

import info.novatec.testit.webtester.testng.annotations.CreateUsing;
import info.novatec.testit.webtester.testng.listener.WebTesterTestNGListener;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.annotation.Resource;


@Listeners(WebTesterTestNGListener.class)
public class JavaScriptAndAlertsUiTest {

    /* (1) JavaScript execution in Selenium usually takes a lot of boiler plate
     * code to be executed efficiantly. We hid all of that behind the {@link
     * Browser}'s <code>#executeJavaScript(...)</code> API.
     * 
     * (2) Since pop up mesages, from the point of view of a test, usually only
     * require the decision between accepting or declining a question we also
     * provide easy one method handling of those dialogs with the
     * <code>acceptAlertIfVisible()</code> and
     * <code>declineAlertIfVisible()</code> methods. */

    @Resource
    @CreateUsing( FirefoxFactory.class )
    private static Browser browser;

    @Test
    public void openAlertMessageWithJavaScriptAndCloseIt () throws InterruptedException {
        String javaScript = "alert('Hello World!')";
        browser.executeJavaScript(javaScript);
        waitShortly();
        browser.acceptAlertIfVisible();
        waitShortly();
    }

    @Test
    public void ignoreNonExistingAlerts () {
        browser.acceptAlertIfVisible();
    }

    private void waitShortly () throws InterruptedException {
        Thread.sleep(1500);
    }

}
