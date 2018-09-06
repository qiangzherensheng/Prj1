package bj.service;

import bj.dao.UserInfoDao;
import bj.entity.UserInfoEntity;

public class UserInfoService {
	UserInfoDao dao=new UserInfoDao();
	public int update(UserInfoEntity entity)throws Exception{
		return dao.update(entity);
	}
	/**
	 * 
	 * 
	 * @param entity
	 * @return -1表示用户名已经存在
	 * @throws Exception
	 */
	public int save(UserInfoEntity entity) throws Exception{
		UserInfoEntity entity1=dao.findByUserName(entity.getUserName());
		if(entity1!=null){
			return -1;
		}
		return dao.save(entity); 
	}
	public UserInfoEntity findByUserNameandUserPass(String userName,String userPass)throws Exception{
		return dao.findByUserNameandPassword(userName, userPass);
	}
	public UserInfoEntity findByUserName(String userName) throws Exception{
		return dao.findByUserName(userName);
	}
	public UserInfoEntity findById(int uid) throws Exception{
		return dao.findById(uid);
	}
}
