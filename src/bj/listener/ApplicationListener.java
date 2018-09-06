package bj.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {

	/**
	 * ��application����ʱ���ã�����tomcatʱ������
	 * */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext application = event.getServletContext();
		application.setAttribute("onLineNum", 0);
	}

	/**
	 * application����ʱ���ø÷���(ֹͣtomcatʱ����)
	 * */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext application = event.getServletContext();
		application.removeAttribute("onLineNum");
	}
	
}
