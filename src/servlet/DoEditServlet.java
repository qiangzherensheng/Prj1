package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bj.entity.KnowledgeEntity;
import bj.service.KnowledgeService;
@WebServlet("/logined/DoEditServlet")
public class DoEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DoEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.valueOf(request.getParameter("id"));
		int cid=Integer.valueOf(request.getParameter("name"));
		String title=request.getParameter("title");
		String label=request.getParameter("label");
		String content=request.getParameter("content");
		//修改数据时，要先查询数据，将查询到的数据保存到实体对象中。需要修改的属性重新赋值，最后将实体对象传递给业务，由业务更新。
		KnowledgeService service=new KnowledgeService();
		KnowledgeEntity entity = null;
		try {
			entity = service.findById(id);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		entity.setId(id);
		entity.setTitle(title);
		entity.setLabel(label);
		entity.setContent(content.replace(" ", "&nbsp;").replace("\r\n", "<br/>"));
		entity.setCid(cid);
		try {
			service.update(entity);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		request.setAttribute("entity", entity);
		request.getRequestDispatcher("/logined/MyknowledgeServlet").forward(request, response);
	}
}
