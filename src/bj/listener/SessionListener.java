package bj.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		ServletContext application = event.getSession().getServletContext();
		Integer num =(Integer) application.getAttribute("onLineNum");
		if(num!=null){
			int i = num;
			i++;
			application.setAttribute("onLineNum", i);
		}else{
			application.setAttribute("onLineNum", 1);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		ServletContext application = event.getSession().getServletContext();
		Integer num = (Integer) application.getAttribute("onLineNum");
		if(num!=null){
			int i = num;
			i--;
			application.setAttribute("onLineNum", i);
		}
	}

}
