package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bj.entity.KnowledgeEntity;
import bj.entity.UserInfoEntity;
import bj.service.KnowledgeService;
import bj.util.Keys;
import bj.util.PageUtil;

@WebServlet("/logined/MyknowledgeServlet")
public class MyknowledgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MyknowledgeServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//����������ģ�ͻ�ȡ���ݣ��������ݱ��浽����Χ�У�Ȼ��֪ͨ��ͼ������Χ�л�ȡ���ݲ���ʾ
			KnowledgeService service =new KnowledgeService();
			//��ȡ�ܼ�¼��
			int recordCount=service.findCountByUserid(((UserInfoEntity)request.getSession().getAttribute(Keys.LOGINUSER)).getId());
			PageUtil pageUtil=new PageUtil();
			pageUtil.setRecordCount(recordCount);
			//��ȡ��ǰҳ��
			String pageIndex=request.getParameter("pageIndex");
			//��֤ҳ����Ч��
			if(pageIndex!=null&&!pageIndex.equals("")){
				int tempIndex=1;
				try {
					tempIndex=Integer.valueOf(pageIndex);
				} catch (Exception e) {
				}if(tempIndex<1){
						tempIndex=1;
					}
				if(tempIndex>pageUtil.getPageCount()){
						tempIndex=pageUtil.getPageCount();
					}
					pageUtil.setPageIndex(tempIndex);
			}
					request.setAttribute("page", pageUtil);
					
			List<KnowledgeEntity> list = service.findByUserId(((UserInfoEntity)request.getSession().getAttribute(Keys.LOGINUSER)).getId(),pageUtil.getPageIndex(),pageUtil.getPageSize());
			request.setAttribute("key", list);
			request.getRequestDispatcher("/logined/myknowledge.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
