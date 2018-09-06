package bj.dao;

import java.util.List;

import bj.dao.mapper.UserInfoMapper;
import bj.entity.UserInfoEntity;

public class UserInfoDao {
		DBHelper helper=new DBHelper();
		public int update(UserInfoEntity entity)throws Exception{
			int a=0;
			try {
				String sql="UPDATE userinfo set userPass=?,nickName=?,headerImage=?,introduce=? where userName=?";
				a=helper.executeUpdate(sql,entity.getUserPass(),entity.getNickName(),entity.getHeaderImage(),entity.getIntroduce(),entity.getUserName());
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}finally{
				helper.close();
			}
			return a;
		}
		public int save(UserInfoEntity entity) throws Exception{
			int i=0;
			try {
				String sql="insert into userinfo (userName,userPass, nickName,headerImage,introduce) values(?,?,?,?,?) ";
				i=helper.executeUpdate(sql, entity.getUserName(),entity.getUserPass(),entity.getNickName(),entity.getHeaderImage(),entity.getIntroduce());
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}finally{
				helper.close();
			}
			return i;
		}
		public UserInfoEntity findByUserName(String userName) throws Exception{
			UserInfoEntity entity =null;
			try {
				String sql="select * from userinfo where userName=?";
				List<UserInfoEntity> list=helper.executeQuery(new UserInfoMapper(), sql, userName);
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
		
		/*public UserInfoEntity findByUserName(String userName) throws Exception{
			UserInfoEntity entity =null;
			try {
				String sql="select * from userinfo where userName=?";
				ResultSet rs=helper.executeQuery(sql, userName);
				if(rs.next()){
					entity=new UserInfoEntity();
					entity.setId(rs.getInt("id"));
					entity.setUserName(rs.getString("userName"));
					entity.setUserPass(rs.getString("userPass"));
					entity.setNickName(rs.getString("nickName"));
					entity.setHeaderImage(rs.getString("headerImage"));
					entity.setIntroduce(rs.getString("introduce"));
				}
			} catch (Exception e) {
				throw e;
			}finally{
				helper.close();
			}
			return entity;
		}*/
		public UserInfoEntity findByUserNameandPassword(String userName,String userPass) throws Exception{
			UserInfoEntity entity = null;
			try {
				String sql = "select * from userinfo where userName=? and userPass=?";
				Object values[]=new Object[]{userName,userPass};
				List<UserInfoEntity> list=helper.executeQuery(new UserInfoMapper(), sql, values);
				if(list!=null&list.size()>0){
					entity=list.get(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				helper.close();
			}
			return entity;
		}
		/*public UserInfoEntity findByUserNameandPassword(String userName,String userPass) throws Exception{
			UserInfoEntity entity =null;
			try {
				String sql="select * from userinfo where userName=? and userPass=?";
				ResultSet rs=helper.executeQuery(sql, userName,userPass);
				if(rs.next()){
					entity=new UserInfoEntity();
					entity.setId(rs.getInt("id"));
					entity.setUserName(rs.getString("userName"));
					entity.setUserPass(rs.getString("userPass"));
					entity.setNickName(rs.getString("nickName"));
					entity.setHeaderImage(rs.getString("headerImage"));
					entity.setIntroduce(rs.getString("introduce"));
				}
			} catch (Exception e) {
				throw e;
			}finally{
				helper.close();
			}
			return entity;
		}*/
		public UserInfoEntity findById(int uid) throws Exception{
			UserInfoEntity entity = null;
			try {
				String sql = "select * from userinfo where id=?";
				List<UserInfoEntity> list=helper.executeQuery(new UserInfoMapper(), sql, uid);
				if(list!=null&list.size()>0){
					entity=list.get(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				helper.close();
			}
			return entity;
		}
		/*public UserInfoEntity findById(int uid) throws Exception{
			UserInfoEntity entity = null;
			try {
				String sql = "select * from userinfo where id=?";
				ResultSet rs = helper.executeQuery(sql, uid);
				if(rs.next()){
					entity =new UserInfoEntity();
					entity.setId(rs.getInt("id"));
					entity.setUserName(rs.getString("userName"));
					entity.setUserPass(rs.getString("userPass"));
					entity.setHeaderImage(rs.getString("headerImage"));
					entity.setNickName(rs.getString("nickName"));
					entity.setIntroduce(rs.getString("introduce"));
				}
			} catch (Exception e) {
				throw e;
			} finally {
				helper.close();
			}
			return entity;
		}*/
}
