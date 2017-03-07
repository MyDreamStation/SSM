package com.bjtu.zs.util.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
/**
 * @ClassName QuartzContextListener
 * @Description 关闭无法关闭的守护线程，解决内存泄漏问题
 * @author 曾双  631710518@qq.com
 * @Date 2017年3月7日13:48:14
 *
 */
public class QuartzContextListener implements ServletContextListener {

	static Logger logger = Logger.getLogger(QuartzContextListener.class.getName());

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
/*		WebApplicationContext webApplicationContext = (WebApplicationContext) arg0.getServletContext()
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		org.quartz.impl.StdScheduler startQuartz = (org.quartz.impl.StdScheduler) webApplicationContext
				.getBean("startQuertz");
		if (startQuartz != null) {
			startQuartz.shutdown();
		}*/
		shutdownCleanupThread();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	void shutdownCleanupThread() {
		try {
			AbandonedConnectionCleanupThread.shutdown();
			logger.info("守护线程AbandonedConnectionCleanupThread关闭成功！");
		} catch (InterruptedException e) {
			logger.warn("SEVERE problem cleaning up: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
