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
	private  final String tempPath="E:/tempFile";//���ڴ����ʱ�ļ���Ŀ¼
	private final int flushSize=1024*512;//��������С
	private final long fileSize=10*1024*1024;//�ļ�����С
	/**
	 * �ڳ�ʼ���������ж��ϴ�ͷ���Ŀ¼�Ƿ���ڣ���������ڣ��򴴽�
	 */
	@Override
	public void init() throws ServletException {
		//������ʱ����ļ���·��
		File file=new File(tempPath);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ʹ��application���ö����ȡ��ǰ��Ŀ�ڷ������е���ʵĿ¼
		String path=req.getSession().getServletContext().getRealPath("/headerimages/");
		// �ж�from����enctype����ֵ�Ƿ�����Ϊmultipart/form-data
		boolean isMultipart=ServletFileUpload.isMultipartContent(req);
		if(!isMultipart){
			return ;
		}
		try {
			//�����ϴ� �ļ�����
			DiskFileItemFactory factory=new DiskFileItemFactory();
			//����JVM��������С
			factory.setSizeThreshold(flushSize);
			//���û�����Ŀ¼��һ����������С����jvm��������С��ʱ�����ݴ����Ӳ��Ŀ¼
			factory.setRepository(new File(tempPath));
			//����FileUpdate����
			ServletFileUpload upload=new ServletFileUpload(factory);
			//�����ļ���С
			upload.setFileSizeMax(fileSize);
			//����������Ԫ��ת����FileItem���󣬱�����List��
			List<FileItem> items=upload.parseRequest(req);
			Iterator<FileItem> iter=items.iterator();
			while(iter.hasNext()){
				FileItem item=iter.next();
				if(item.isFormField()){
					System.out.println(item.getString("utf-8"));//request.getParameter("name")
				}
				else{
					//�ϴ��ļ�����������Ŀ¼��
					String ext=item.getName().substring(item.getName().lastIndexOf(".") );
					String fileName=UploadFileNameStrategy.getFileName();
					//��ȡͷ�ļ�����֤�ϴ��ļ���ȫ
					InputStream fs=item.getInputStream();
					int i1=fs.read();//��ȡͷ�ļ�
					int i2=fs.read();//��ȡͷ�ļ�
					String fileHead=String.valueOf(i1)+String.valueOf(i2);
					//jsp��6037 png:13780 jpg:255216 gif:7173
					if(fileHead.equals("7137")||fileHead.equals("13780")||fileHead.equals("255216")){
					item.write(new File(path,fileName+ext));
					//�������ݿ���ͷ����ļ���
					UserInfoEntity entity=(UserInfoEntity)req.getSession().getAttribute(Keys.LOGINUSER);
					entity.setHeaderImage(fileName+ext);
					UserInfoService service=new UserInfoService();
					service.update(entity);
					//�ض�����ҳ
					resp.sendRedirect(req.getContextPath()+"/index.jsp");
					}
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
