package bj.tags;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 自定义标签
 * 第一步：定义类，继承TagSupport,重写doStartTag方法
 * 第二步：告诉tomcat我有一个自定义的标签，需要在web-inf目录下放一个tld的标签描述文件
 * 第三步：在jsp界面上使用标签
 * */
public class PageTag extends TagSupport {
	//属性必须提供get set方法
	private int pageIndex;
	private int recordCount;
	private int pageSize=5;
	private int pageCount;
	private String actionName;
	private String className;
	private String classText;
	
	//private String condition;//condition=key=java&name=lala&id=12
	@Override
	public int doStartTag() throws JspException {
		 JspWriter out = pageContext.getOut();
		 try {
			out.print("<div style='text-align:center;height:30px;line-height:30px;'>");
			out.print("<form action=\"" + actionName + "\">");
			out.print("&nbsp;<a class=\""+ className +"\" href=\"" + actionName + "?pageIndex=1\">|&lt;</a>&nbsp;");
			out.print("&nbsp;<a class=\""+ className +"\" href=\"" + actionName + "?pageIndex=" + (pageIndex < 1 ? 1 : pageIndex - 1) + "\">&lt;&lt;</a>&nbsp;");
			out.print("<span class=\""+ classText +"\"> 第 " + pageIndex + " 页 / 共 " + getPageCount() +" 页/ 共 "+getRecordCount()+ " / 条 </span>&nbsp;");
			out.print("&nbsp;<a class=\""+ className +"\" href=\"" + actionName + "?pageIndex=" + (pageIndex + 1 > getPageCount() ? pageIndex : pageIndex + 1) + "\">&gt;&gt;</a>&nbsp;");
			out.print("&nbsp;<a class=\""+ className +"\" href=\"" + actionName + "?pageIndex=" + getPageCount() + "\">&gt;|</a>&nbsp;");
			out.print("&nbsp;<input   type=\"text\" style=\"width:50px; height:20px;border:1px solid gray;\" name=\"pageIndex\" style=\"width:20px\" value=\"" + pageIndex + "\" />");
			out.print("<input type=\"submit\" style=\"width:30px; height:22px; border:1px solid gray; border-left:none;\" value=\"Go\"/>");
			out.print("</form>");
			out.print("</div>");
//			out.print("<a class=\""+className+"\" href=\""+actionName+"?pageIndex=1\">第一页</a>");
//			out.print("<a class=\""+className+"\"  href=\""+actionName+"?pageIndex="+(pageIndex -1)+"\">上一页</a>");
//			out.print("<a class=\""+className+"\"  href=\""+actionName+"?pageIndex="+(pageIndex +1)+"\">下一页</a>");
//			out.print("<a class=\""+className+"\"  href=\""+actionName+"?pageIndex="+getPageCount()+"\">最后页</a>");
//			out.print("<form action=\""+ actionName +"\" method=\"get\"><input type=\"text\" name=\"pageIndex\" value=\""+pageIndex+"\" style=\"width:20px;\"><input type=\"submit\" value=\"GO\"></form>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return super.doStartTag();
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return (recordCount-1)/pageSize +1;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassText() {
		return classText;
	}
	public void setClassText(String classText) {
		this.classText = classText;
	}
}
