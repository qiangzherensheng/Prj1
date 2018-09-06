package bj.dao;

import java.util.Calendar;
import java.util.Date;

public class test {
	public static void main(String[] args) {
		 Date now = new Date();
	     Calendar c = Calendar.getInstance();
	     c.setTime(now);

	     c.set(Calendar.DAY_OF_YEAR,1);
	     System.out.println(c.getTime()); // 第一天
	     c.add(Calendar.YEAR, 1);
	     c.set(Calendar.DAY_OF_YEAR, -1);
	     System.out.println(c.getTime()); // 最后一天
	}
	
}
