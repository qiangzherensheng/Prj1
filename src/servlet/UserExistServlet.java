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

@WebServlet("/UserExistServlet")
public class UserExistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserExistServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��֤�û����Ƿ����
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		String userName=request.getParameter("userName");
		//System.out.println(userName);
		UserInfoService service=new UserInfoService();
		PrintWriter out=response.getWriter();
				try {
					
					UserInfoEntity e=service.findByUserName(userName);
					if(e!=null){
						out.print("�û����Ѿ�����");
						//System.out.println("�û����Ѿ�����");
					}else{
						out.print("�û�������ʹ��");
						//System.out.println("�û�������ʹ��");
					}
				} catch (Exception e) {
					out.print("ϵͳ����");
					e.printStackTrace();
				}finally{
					out.flush();
					out.close();
				}
	}

}
