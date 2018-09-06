package servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bj.entity.UserInfoEntity;
import bj.service.UserInfoService;
import bj.util.Keys;
import bj.util.MD5;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				  String userName=request.getParameter("userName");
				  String userPass=request.getParameter("userPass");
				  String remember=request.getParameter("remember");
				  String Code=request.getParameter("code");
				  if(userName==null||userName.trim().length()==0){
					  request.setAttribute("message", "请输入用户名");
					  request.setAttribute("userName", userName.trim());
					  request.getRequestDispatcher("login.jsp").forward(request, response);
					  return ;
				  }
				  if(userPass==null||userPass.trim().length()==0){
					  request.setAttribute("message", "请输入用户密码");
					  request.setAttribute("userPass", userPass.trim());
					  request.getRequestDispatcher("login.jsp").forward(request, response);
					  return ;
				  }
				if(Code==null||!(Code.equals(request.getSession().getAttribute("code")))){
					 request.setAttribute("message", "验证码输入错误");
					 request.setAttribute("code", Code.trim());
					 request.getRequestDispatcher("login.jsp").forward(request, response);
					 return;
				 } 
				  //调用业务
				  UserInfoService service =new UserInfoService();
				  userPass = MD5.createPassword(userPass);
				  UserInfoEntity entity=null;
				try {
					entity = service.findByUserNameandUserPass(userName, userPass);
				} catch (Exception e) {
					e.printStackTrace();
				}
				  if(entity==null){
					  request.setAttribute("message", "用户名或密码错误！");
					  request.setAttribute("userName", userName.trim());
					  request.getRequestDispatcher("login.jsp").forward(request, response);
				  }else{
					  //登录成功，记录用户登录状态
					  request.getSession().setAttribute(Keys.LOGINUSER,entity);
					  if(remember!=null&&remember.equals("yes")){
						  Cookie cookie=new Cookie(Keys.REMEMBER_LOGEN_NAME,URLEncoder.encode(userName));
						  cookie.setMaxAge(60*60*24*365*10);
						  cookie.setPath(request.getContextPath());
						  response.addCookie(cookie);
					  }
					  request.getRequestDispatcher("/IndexServlet").forward(request, response);
				  }
			}
	}

