package bj.util;

import java.util.UUID;

public class UploadFileNameStrategy {
	/**
	 * ��ȡ�ϴ��ļ���
	 */
	public static synchronized String getFileName(){
		return UUID.randomUUID().toString();
	}
}
