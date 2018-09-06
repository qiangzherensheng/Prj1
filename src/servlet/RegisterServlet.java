package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bj.entity.UserInfoEntity;
import bj.service.UserInfoService;
import bj.util.MD5;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String userPass=request.getParameter("userPass");
		String userPass1=request.getParameter("userPass1");
		String nickName=userName;
		String headerImage="1.jpg";
		String introduce="编辑自我介绍，让更多人了解你";
		boolean valid=false;
		//验证用户名是否存在
		if(userName==null||userName.trim().length()==0){
			request.setAttribute("userNameMsg", "请填写用户名");
			valid=true;
		}
		//验证密码是否在4到20之间
		if(userPass==null||userPass.length()<4||userPass.length()>20){
			request.setAttribute("userPassMsg", "密码长度必须在4到20之间");
			valid=true;
		}
		//验证两次输入密码是否一致
		if(userPass1==null||!userPass1.equals(userPass)){
			request.setAttribute("userPass1Msg", "两次密码不一致");
			valid=true;
		}
		if(valid){
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return ;
		}
		//封装数据
		userPass = MD5.createPassword(userPass);
		UserInfoEntity entity=new UserInfoEntity(userName,userPass,nickName,headerImage,introduce);
		//调用业务
		UserInfoService service=new UserInfoService();
		int i=0;
		try {
			i = service.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(i>0){
		request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			request.setAttribute("userNmae", userName);
			request.setAttribute("userName", "用户名已经别占用");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
}
