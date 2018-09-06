package bj.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {

	/**
	 * 在application创建时调用（启动tomcat时创建）
	 * */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext application = event.getServletContext();
		application.setAttribute("onLineNum", 0);
	}

	/**
	 * application销毁时调用该方法(停止tomcat时销毁)
	 * */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext application = event.getServletContext();
		application.removeAttribute("onLineNum");
	}
	
}
