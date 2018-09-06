package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bj.entity.CatagoryEntity;
import bj.service.CatagoryService;


@WebServlet("/logined/AddknowledgesServlet")
public class AddknowledgesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AddknowledgesServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CatagoryService catagoryService=new CatagoryService();
		List<CatagoryEntity> list = null;
		try {
			list = catagoryService.findAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/logined/addknowledge.jsp").forward(request, response);
		
	}

}
