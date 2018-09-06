package servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bj.entity.AttachEntity;
import bj.service.AttachService;

@WebServlet("/DownLoadServlet")
public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����
		/**
		 * 1.��ȡҪ�����ļ�������1
		 * 2.��������1�����ݿ��в��Ҹ�������Ӧ��·����C:/a.txt��
		 * 3.�����ҵ����ļ�
		 */
		int id=Integer.valueOf(request.getParameter("id"));
		AttachService attachService=new AttachService();
		AttachEntity entity = null;
		try {
			entity = attachService.findFileName(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String path=request.getSession().getServletContext().getRealPath("/attach/")+"\\"+entity.getFilename();
		//������ӦͷΪ�ļ�����
		response.setContentType("application/x-msdownload");
		String fileName=path;
		response.addHeader("Content-Disposition","attachment;filename=\""+java.net.URLEncoder.encode(fileName,"utf-8")+"\"");
		//���ñ����ص��ļ�
		String filepath="E:/";
		File file=new File(path);
		//��ȡ�ļ���������
		FileInputStream fis=new FileInputStream(file);
		BufferedInputStream bis=new BufferedInputStream(fis);
		byte []b=new byte[1024];
		//���������е�����д�뵽�������
		ServletOutputStream sos=response.getOutputStream();
		int len=0;
		while((len=bis.read(b))!=-1){
			sos.write(b);
		}
		sos.flush();
		sos.close();
		bis.close();
		fis.close();
	}

}
