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
	private  final String loadtempPath="E:/tempFile";//���ڴ����ʱ�ļ���Ŀ¼
	private final int flushSize=1024*512;//��������С
	private final long fileSize=10*1024*1024;//�ļ�����С
	String filename="";
	
	/**
	 * �ڳ�ʼ���������ж��ϴ�ͷ���Ŀ¼�Ƿ���ڣ���������ڣ��򴴽�
	 */
	@Override
	public void init() throws ServletException {
		//������ʱ����ļ���·��
		File file=new File(loadtempPath);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//ʹ��application���ö����ȡ��ǰ��Ŀ�ڷ������е���ʵĿ¼
		//String path=req.getSession().getServletContext().getRealPath("/headerimages/");
		// �ж�from����enctype����ֵ�Ƿ�����Ϊmultipart/form-data
		String path=request.getSession().getServletContext().getRealPath("/attach/");
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			return ;
		}
		try {
			//�����ϴ� �ļ�����
			DiskFileItemFactory factory=new DiskFileItemFactory();
			//����JVM��������С
			factory.setSizeThreshold(flushSize);
			//���û�����Ŀ¼��һ����������С����jvm��������С��ʱ�����ݴ����Ӳ��Ŀ¼
			factory.setRepository(new File(loadtempPath));
			//����FileUpdate����
			ServletFileUpload upload=new ServletFileUpload(factory);
			//�����ļ���С
			upload.setFileSizeMax(fileSize);
			//����������Ԫ��ת����FileItem���󣬱�����List��
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
					//��ȡ�ļ�·��������
					String name=item.getName();
					long size=item.getSize();
					//����ļ��������ڣ��򲻴���
					if(name==null||name.equals("")&&size==0){
						continue;
					}
					//��ȡ�ļ���׺
					fileType=name.substring(name.lastIndexOf("."));
					//�������һ���ļ�����ֹ�ļ����ظ�
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
			//���ڻ���
			request.setAttribute("title", title);
			request.setAttribute("content", content);
			request.setAttribute("label", label);
			//������֤
			if(title==null||title.length()==0){
				request.setAttribute("message", "����д����");
				request.getRequestDispatcher("/logined/AddknowledgesServlet").forward(request, response);
				
			}
			if(label==null||label.length()==0){
				request.setAttribute("message", "����д��ǩ");
				request.getRequestDispatcher("/logined/AddknowledgesServlet").forward(request, response);
				
			}
			if(content==null || content.length()==0){
				request.setAttribute("message", "����д����");
				request.getRequestDispatcher("/logined/AddknowledgesServlet").forward(request, response);
				
			}
			//��װ�����
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
			oe.setOption("������"+title);
			KnowledgeService service=new KnowledgeService();
			//ת����ͼ
			if(service.save(knowledgeEntity,oe)>0){
				response.sendRedirect(request.getContextPath()+"/logined/MyknowledgeServlet");
			}else{
				request.setAttribute("message", "������æ�����Ժ�����");
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
		//����ʱ������ļ���
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmssms");
		return df.format(new Date());
	}

}
