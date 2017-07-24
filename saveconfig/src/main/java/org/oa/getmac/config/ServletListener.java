package org.oa.getmac.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {

	private ScheduledExecutorService scheduler;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		scheduler = Executors.newSingleThreadScheduledExecutor();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		scheduler.shutdownNow();
	}
}