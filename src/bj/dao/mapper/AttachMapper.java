package bj.dao.mapper;

import java.sql.ResultSet;

import bj.entity.AttachEntity;

public class AttachMapper implements IRowMapper<AttachEntity>{

	@Override
	public AttachEntity rowMapper(ResultSet rs) {
		AttachEntity attachEntity= new AttachEntity();
		try {
			attachEntity.setId(rs.getInt("id"));
			attachEntity.setKid(rs.getInt("kid"));
			attachEntity.setFilename(rs.getString("filename"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attachEntity;
	}

}
