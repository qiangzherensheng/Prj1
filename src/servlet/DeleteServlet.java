package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bj.service.KnowledgeService;

@WebServlet("/logined/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.valueOf(request.getParameter("id"));
	  	KnowledgeService service=new KnowledgeService();
	  	try {
			int i=service.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  	request.getRequestDispatcher("/logined/MyknowledgeServlet").forward(request, response);
	}

}
