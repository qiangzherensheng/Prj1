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
		//下载
		/**
		 * 1.获取要下载文件的主键1
		 * 2.根据主键1到数据库中查找该主键对应的路径（C:/a.txt）
		 * 3.下载找到的文件
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
		//设置响应头为文件下载
		response.setContentType("application/x-msdownload");
		String fileName=path;
		response.addHeader("Content-Disposition","attachment;filename=\""+java.net.URLEncoder.encode(fileName,"utf-8")+"\"");
		//设置被下载的文件
		String filepath="E:/";
		File file=new File(path);
		//读取文件到缓冲区
		FileInputStream fis=new FileInputStream(file);
		BufferedInputStream bis=new BufferedInputStream(fis);
		byte []b=new byte[1024];
		//将缓冲区中的数据写入到输出流中
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
