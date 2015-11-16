package samples.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class CoreSampleApplication {

    private ConfigurableApplicationContext context;

    public void start () {
        if (context == null) {
            context = SpringApplication.run(CoreSampleApplication.class);
        }
    }

    public void stop () {
        if (context != null) {
            context.close();
            context = null;
        }
    }

    public static void main (String[] args) {
        CoreSampleApplication application = new CoreSampleApplication();
        application.start();
    }

}
