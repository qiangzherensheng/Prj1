package bj.dao.mapper;

import java.sql.ResultSet;

import bj.entity.UserInfoEntity;

public class UserInfoMapper implements IRowMapper<UserInfoEntity> {
 
	@Override
	public UserInfoEntity rowMapper(ResultSet rs) {
		UserInfoEntity entity=new UserInfoEntity();
		try {
			entity.setId(rs.getInt("id"));
			entity.setUserName(rs.getString("userName"));
			entity.setUserPass(rs.getString("userPass"));
			entity.setHeaderImage(rs.getString("headerImage"));
			entity.setNickName(rs.getString("nickName"));
			entity.setIntroduce(rs.getString("introduce"));
		} catch (Exception e) {
			e.printStackTrace();
			entity=null;
		}
		return entity;
	}
	
}
