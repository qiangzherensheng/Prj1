package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bj.entity.UserInfoEntity;
import bj.service.UserInfoService;
import bj.util.Keys;
import bj.util.UploadFileNameStrategy;


@WebServlet("/SetHeaderImage")
public class SetHeaderImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  final String tempPath="E:/tempFile";//用于存放临时文件的目录
	private final int flushSize=1024*512;//缓冲区大小
	private final long fileSize=10*1024*1024;//文件最大大小
	/**
	 * 在初始化方法中判断上传头像的目录是否存在，如果不存在，则创建
	 */
	@Override
	public void init() throws ServletException {
		//创建临时存放文件的路径
		File file=new File(tempPath);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//使用application内置对象获取当前项目在服务器中的真实目录
		String path=req.getSession().getServletContext().getRealPath("/headerimages/");
		// 判断from表单的enctype属性值是否设置为multipart/form-data
		boolean isMultipart=ServletFileUpload.isMultipartContent(req);
		if(!isMultipart){
			return ;
		}
		try {
			//定义上传 文件工厂
			DiskFileItemFactory factory=new DiskFileItemFactory();
			//设置JVM缓冲区大小
			factory.setSizeThreshold(flushSize);
			//设置缓冲区目录，一旦缓冲区大小超过jvm缓冲区大小的时候，数据存放在硬盘目录
			factory.setRepository(new File(tempPath));
			//创建FileUpdate对象
			ServletFileUpload upload=new ServletFileUpload(factory);
			//设置文件大小
			upload.setFileSizeMax(fileSize);
			//将表单中所有元素转换成FileItem对象，保存在List中
			List<FileItem> items=upload.parseRequest(req);
			Iterator<FileItem> iter=items.iterator();
			while(iter.hasNext()){
				FileItem item=iter.next();
				if(item.isFormField()){
					System.out.println(item.getString("utf-8"));//request.getParameter("name")
				}
				else{
					//上传文件到服务器的目录下
					String ext=item.getName().substring(item.getName().lastIndexOf(".") );
					String fileName=UploadFileNameStrategy.getFileName();
					//获取头文件，验证上传文件安全
					InputStream fs=item.getInputStream();
					int i1=fs.read();//读取头文件
					int i2=fs.read();//读取头文件
					String fileHead=String.valueOf(i1)+String.valueOf(i2);
					//jsp：6037 png:13780 jpg:255216 gif:7173
					if(fileHead.equals("7137")||fileHead.equals("13780")||fileHead.equals("255216")){
					item.write(new File(path,fileName+ext));
					//更改数据库中头像的文件名
					UserInfoEntity entity=(UserInfoEntity)req.getSession().getAttribute(Keys.LOGINUSER);
					entity.setHeaderImage(fileName+ext);
					UserInfoService service=new UserInfoService();
					service.update(entity);
					//重定向到首页
					resp.sendRedirect(req.getContextPath()+"/index.jsp");
					}
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
