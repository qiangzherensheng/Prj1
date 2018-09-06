package bj.util;

/**
 * 分页辅助类
 * */
public class PageUtil {
	private int pageIndex=1;//当前页，默认为第一页
	private int pageSize= 5;//每页记录数量
	private int recordCount ;//记录总数量
	private int pageCount;//总页数
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageCount() {
		//根据总记录数和每页记录数计算出总页数，总页数是只读的
		pageCount = (recordCount-1)/pageSize+1;
		return pageCount;
		//return recordCount % pageSize ==0?recordCount / pageSize : recordCount / pageSize +1;
	}
}
