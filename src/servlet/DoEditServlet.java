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
		//�޸�����ʱ��Ҫ�Ȳ�ѯ���ݣ�����ѯ�������ݱ��浽ʵ������С���Ҫ�޸ĵ��������¸�ֵ�����ʵ����󴫵ݸ�ҵ����ҵ����¡�
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
