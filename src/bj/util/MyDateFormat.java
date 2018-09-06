package bj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateFormat {
	public static String formatDate(Date date,String patten){
		return new SimpleDateFormat(patten).format(date);
	}
}
