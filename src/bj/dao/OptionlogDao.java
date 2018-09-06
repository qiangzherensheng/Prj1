package bj.dao;

import bj.entity.OptionlogEntity;
import bj.util.MyDateFormat;

public class OptionlogDao {
	DBHelper helper=new DBHelper();
	public int save(OptionlogEntity entity) throws Exception{
		int i=0;
		try {
			String sql="INSERT INTO optionlog(`userId`,optionDate,`option`)VALUES(?,?,?)";
			i=helper.executeUpdate(sql,entity.getUserid(),MyDateFormat.formatDate(entity.getOptionDate(), "yyyy-MM-dd HH:mm:ss"),entity.getOption());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			 helper.close();
		}
		return i;
	}
}
