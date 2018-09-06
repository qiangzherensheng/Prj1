package servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import bj.entity.KnowledgeEntity;
import bj.entity.OptionlogEntity;
import bj.entity.UserInfoEntity;
import bj.service.KnowledgeService;
import bj.entity.AttachEntity;
import bj.service.AttachService;
import bj.util.Keys;

@WebServlet("/DoAddKnowledge")
public class DoAddKnowledge extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  final String loadtempPath="E:/tempFile";//用于存放临时文件的目录
	private final int flushSize=1024*512;//缓冲区大小
	private final long fileSize=10*1024*1024;//文件最大大小
	String filename="";
	
	/**
	 * 在初始化方法中判断上传头像的目录是否存在，如果不存在，则创建
	 */
	@Override
	public void init() throws ServletException {
		//创建临时存放文件的路径
		File file=new File(loadtempPath);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//使用application内置对象获取当前项目在服务器中的真实目录
		//String path=req.getSession().getServletContext().getRealPath("/headerimages/");
		// 判断from表单的enctype属性值是否设置为multipart/form-data
		String path=request.getSession().getServletContext().getRealPath("/attach/");
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			return ;
		}
		try {
			//定义上传 文件工厂
			DiskFileItemFactory factory=new DiskFileItemFactory();
			//设置JVM缓冲区大小
			factory.setSizeThreshold(flushSize);
			//设置缓冲区目录，一旦缓冲区大小超过jvm缓冲区大小的时候，数据存放在硬盘目录
			factory.setRepository(new File(loadtempPath));
			//创建FileUpdate对象
			ServletFileUpload upload=new ServletFileUpload(factory);
			//设置文件大小
			upload.setFileSizeMax(fileSize);
			//将表单中所有元素转换成FileItem对象，保存在List中
			List<FileItem> items=upload.parseRequest(request);
			Iterator<FileItem> iter=items.iterator();
			
			String title=null;
			String content=null;
			Date pubDate=new Date();
			int cid=0;
			String label=null;
			int readCount=0;
			int ding=0;
			int cai=0;
			int uid=((UserInfoEntity)request.getSession().getAttribute(Keys.LOGINUSER)).getId();
			int state=0;
			int stateUid=0;
			Date stateDate=pubDate;
			String stateContent="";
			String fileType=null;
			
			while(iter.hasNext()){
				FileItem item=iter.next();
				if(!item.isFormField()){
					//获取文件路径及名称
					String name=item.getName();
					long size=item.getSize();
					//如果文件名不存在，则不处理
					if(name==null||name.equals("")&&size==0){
						continue;
					}
					//获取文件后缀
					fileType=name.substring(name.lastIndexOf("."));
					//随机产生一个文件名防止文件名重复
					 filename=getFileName();
					item.write(new File(path+"/"+filename+fileType));
				}else{
					if(item.getFieldName().equals("title")){
						title = item.getString("UTF-8");
					}
					if(item.getFieldName().equals("name")){
						cid =Integer.valueOf(item.getString("UTF-8"));
					}
					if(item.getFieldName().equals("label")){
						label = item.getString("UTF-8");
					}
					if(item.getFieldName().equals("content")){
						content = item.getString("UTF-8");
					}
				}
			}
			//用于回显
			request.setAttribute("title", title);
			request.setAttribute("content", content);
			request.setAttribute("label", label);
			//数据验证
			if(title==null||title.length()==0){
				request.setAttribute("message", "请填写标题");
				request.getRequestDispatcher("/logined/AddknowledgesServlet").forward(request, response);
				
			}
			if(label==null||label.length()==0){
				request.setAttribute("message", "请填写标签");
				request.getRequestDispatcher("/logined/AddknowledgesServlet").forward(request, response);
				
			}
			if(content==null || content.length()==0){
				request.setAttribute("message", "请填写内容");
				request.getRequestDispatcher("/logined/AddknowledgesServlet").forward(request, response);
				
			}
			//封装并添加
			KnowledgeEntity knowledgeEntity=new KnowledgeEntity();
			knowledgeEntity.setCai(cai);
			knowledgeEntity.setCid(cid);
			knowledgeEntity.setContent(content.replace(" ", "&nbsp").replace("\r\n", "<br/>"));
			knowledgeEntity.setDing(ding);
			knowledgeEntity.setLabel(label);
			knowledgeEntity.setPubDate(pubDate);
			knowledgeEntity.setReadCount(readCount);
			knowledgeEntity.setState(state);
			knowledgeEntity.setStateContent(stateContent);
			knowledgeEntity.setStateDate(stateDate);
			knowledgeEntity.setStateUid(stateUid);
			knowledgeEntity.setTitle(title);
			knowledgeEntity.setUid(uid);
			OptionlogEntity oe=new OptionlogEntity();
			oe.setUserid(uid);
			oe.setOptionDate(pubDate);
			oe.setOption("发表了"+title);
			KnowledgeService service=new KnowledgeService();
			//转向到视图
			if(service.save(knowledgeEntity,oe)>0){
				response.sendRedirect(request.getContextPath()+"/logined/MyknowledgeServlet");
			}else{
				request.setAttribute("message", "服务器忙，请稍后在试");
				request.getRequestDispatcher("addknowledge.jsp").forward(request, response);
			}
			AttachEntity entity=new AttachEntity();
			
		//	KnowledgeService service1=new KnowledgeService();
			int e=service.findById1();
			//e.getId();
			entity.setKid(e);
			entity.setFilename(filename+fileType);
			AttachService attachService=new AttachService();
			attachService.save(entity);
		}catch(Exception e){
		 e.printStackTrace();
		}
	}

	private String getFileName(){
		//根据时间产生文件名
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmssms");
		return df.format(new Date());
	}

}
