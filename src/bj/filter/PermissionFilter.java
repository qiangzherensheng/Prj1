package bj.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bj.entity.UserInfoEntity;
import bj.util.Keys;

public class PermissionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		UserInfoEntity entity = (UserInfoEntity) req.getSession().getAttribute(Keys.LOGINUSER);
		if(entity!=null){
			chain.doFilter(request, response);
		}else{
			req.setAttribute("message","没有权限");
			req.getRequestDispatcher("/login.jsp").forward(req, (HttpServletResponse)response);
		}
	}

	@Override
	public void destroy() {
		
	}

}
