package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bj.entity.CatagoryEntity;
import bj.entity.KnowledgeEntity;
import bj.service.CatagoryService;
import bj.service.KnowledgeService;

@WebServlet("/logined/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.valueOf(request.getParameter("id"));
	    KnowledgeService knowService =new KnowledgeService();
	    KnowledgeEntity entity=null;
	    try {
	    	entity=knowService.findById(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    request.setAttribute("entity", entity);
	    request.getRequestDispatcher("/logined/edit.jsp");
		
	    
	    List<CatagoryEntity> list=null;
		try {
			  CatagoryService catagoryService=new CatagoryService();
			 list=catagoryService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		  request.setAttribute("list", list);
		  request.getRequestDispatcher("/logined/edit.jsp").forward(request, response);
	}

}
