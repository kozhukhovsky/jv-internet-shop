package mate.academy.internetshop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import mate.academy.internetshop.lib.Injector;
import org.apache.log4j.Logger;

@WebListener
public class InjectInitializer implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(InjectInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            logger.info("Dependency injection started...");
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            logger.error("Dependency injection failed " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
