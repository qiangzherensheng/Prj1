package bj.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bj.dao.mapper.CatagoryMapper;
import bj.entity.CatagoryEntity;

public class CatagoryDao {
	DBHelper helper=new DBHelper();
	public List<CatagoryEntity> findAll() throws Exception{
		CatagoryEntity entity=new CatagoryEntity();
		List<CatagoryEntity> list=new ArrayList<CatagoryEntity>();
		try {
			String sql="select * from k_catagory ";
			list=helper.executeQuery(new CatagoryMapper(), sql);
			if(list!=null&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					entity=list.get(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity=null;
		}finally{
			helper.close();
		}
		return list;
	}
	/*public List<CatagoryEntity> findAll() throws Exception{
		List<CatagoryEntity> list=new ArrayList<CatagoryEntity>();
		try {
			String sql="select * from k_catagory ";
			ResultSet rs=helper.executeQuery(sql);
			while(rs.next()){
				CatagoryEntity entity=new CatagoryEntity();
				entity.setImg(rs.getString("img"));
				entity.setId(rs.getInt("id"));
				entity.setName(rs.getString("name"));
				entity.setDescription(rs.getString("description"));
				list.add(entity);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			helper.close();
		}
		return list;*/
	
	public CatagoryEntity findById(int cid) throws Exception{
		CatagoryEntity entity=null;
		try {
			String sql="select * from k_catagory where id=?";
			List<CatagoryEntity> list=helper.executeQuery(new CatagoryMapper(), sql,cid);
			if(list!=null&list.size()>0){
				entity=list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity=null;
		}finally{
			helper.close();
		}
		return entity;
	}
	/*public CatagoryEntity findById(int cid) throws Exception{
		CatagoryEntity entity=null;
		try {
			String sql="select * from k_catagory where id=?";
			ResultSet rs=helper.executeQuery(sql, cid);
			if(rs.next()){
				entity=new CatagoryEntity();
				entity.setDescription(rs.getString("description"));
				entity.setImg(rs.getString("img"));
				entity.setId(rs.getInt("id"));
				entity.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			throw e;
		}finally{
			helper.close();
		}
		return entity;
	}*/
	
}
