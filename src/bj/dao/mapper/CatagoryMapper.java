package bj.dao.mapper;

import java.sql.ResultSet;

import bj.entity.CatagoryEntity;

public class CatagoryMapper implements IRowMapper<CatagoryEntity>{

	@Override
	public CatagoryEntity rowMapper(ResultSet rs) {
		CatagoryEntity entity=new CatagoryEntity();
		try {
			entity.setImg(rs.getString("img"));
			entity.setId(rs.getInt("id"));
			entity.setName(rs.getString("name"));
			entity.setDescription(rs.getString("description"));
		} catch (Exception e) {
			e.printStackTrace();
			entity=null;
		}
		return entity;
	}
	
	
}
