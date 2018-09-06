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
			//控制器调用模型获取数据，并将数据保存到请求范围中，然后通知视图到请求范围中获取数据并显示
			KnowledgeService service =new KnowledgeService();
			//获取总记录数
			int recordCount=service.findCountByUserid(((UserInfoEntity)request.getSession().getAttribute(Keys.LOGINUSER)).getId());
			PageUtil pageUtil=new PageUtil();
			pageUtil.setRecordCount(recordCount);
			//获取当前页面
			String pageIndex=request.getParameter("pageIndex");
			//验证页码有效性
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
