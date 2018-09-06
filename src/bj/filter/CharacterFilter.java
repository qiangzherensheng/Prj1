package bj.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterFilter implements Filter {
	private String encode = "utf-8";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encode = filterConfig.getInitParameter("encode");
		if(encode==null){
			encode = "utf-8";
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if(req.getMethod().equalsIgnoreCase("POST")){
			req.setCharacterEncoding(encode);
			res.setCharacterEncoding(encode);
		}else{
			//?id=1&name=kaka&hobby=爬山&hobby=登山&hobby=上上
			Map<String, String[]> maps=req.getParameterMap();
			Set<String> keys = maps.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				String key = it.next();
				String values[]= maps.get(key);
				for (int i = 0; i < values.length; i++) {
					values[i] =new String( values[i].getBytes("iso-8859-1"),encode);
				}
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
