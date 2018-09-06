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
		String introduce="�༭���ҽ��ܣ��ø������˽���";
		boolean valid=false;
		//��֤�û����Ƿ����
		if(userName==null||userName.trim().length()==0){
			request.setAttribute("userNameMsg", "����д�û���");
			valid=true;
		}
		//��֤�����Ƿ���4��20֮��
		if(userPass==null||userPass.length()<4||userPass.length()>20){
			request.setAttribute("userPassMsg", "���볤�ȱ�����4��20֮��");
			valid=true;
		}
		//��֤�������������Ƿ�һ��
		if(userPass1==null||!userPass1.equals(userPass)){
			request.setAttribute("userPass1Msg", "�������벻һ��");
			valid=true;
		}
		if(valid){
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return ;
		}
		//��װ����
		userPass = MD5.createPassword(userPass);
		UserInfoEntity entity=new UserInfoEntity(userName,userPass,nickName,headerImage,introduce);
		//����ҵ��
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
			request.setAttribute("userName", "�û����Ѿ���ռ��");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
}
