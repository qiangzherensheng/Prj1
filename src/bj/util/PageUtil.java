package bj.util;

/**
 * ��ҳ������
 * */
public class PageUtil {
	private int pageIndex=1;//��ǰҳ��Ĭ��Ϊ��һҳ
	private int pageSize= 5;//ÿҳ��¼����
	private int recordCount ;//��¼������
	private int pageCount;//��ҳ��
	
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
		//�����ܼ�¼����ÿҳ��¼���������ҳ������ҳ����ֻ����
		pageCount = (recordCount-1)/pageSize+1;
		return pageCount;
		//return recordCount % pageSize ==0?recordCount / pageSize : recordCount / pageSize +1;
	}
}
