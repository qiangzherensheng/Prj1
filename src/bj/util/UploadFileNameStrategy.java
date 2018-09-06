package bj.util;

import java.util.UUID;

public class UploadFileNameStrategy {
	/**
	 * 获取上传文件名
	 */
	public static synchronized String getFileName(){
		return UUID.randomUUID().toString();
	}
}
