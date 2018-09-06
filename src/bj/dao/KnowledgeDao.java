package bj.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bj.dao.mapper.KnowledgeMapper;
import bj.entity.KnowledgeEntity;
import bj.entity.OptionlogEntity;
import bj.util.MyDateFormat;

public class KnowledgeDao {
	DBHelper helper=new DBHelper();
	//定义记录日志对象log
	static Logger log=Logger.getLogger(KnowledgeDao.class.getName());
	public int update(KnowledgeEntity entity) throws Exception{
		int i=0;
		try {
			String sql="UPDATE k_nowledge SET title=?,content=?,pubDate=?,cid=?,label=?,readCount=?,ding=?,cai=?,uid=?,state=?,stateUid=?, stateDate=?,stateContent=? where id=?";
			Object values[]=new Object[]{
					entity.getTitle(),
					entity.getContent(),
					entity.getPubDate(),
					entity.getCid(),
					entity.getLabel(),
					entity.getReadCount(),
					entity.getDing(),
					entity.getCai(),
					entity.getUid(),
					entity.getState(),
					entity.getStateUid(),
					entity.getStateDate(),
					entity.getStateContent(),
					entity.getId()
			};
			i=helper.executeUpdate(sql, values);
		}catch (Exception e) {
			throw e;
		}finally{
			helper.close();
		}
		return i;
	}
	
	
	public int save(KnowledgeEntity entity,OptionlogEntity oe) throws Exception{
		int i=0;
		//开始事务
		helper.beginTrans();
		try {
			log.info("用户准备保存 一条知识条目");
			String sql1="INSERT INTO k_nowledge(title,content,pubDate,cid,label,readCount,ding,cai,uid,state,stateUid,stateDate) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			Object values[]=new Object[]{
					entity.getTitle(),
					entity.getContent(),
					MyDateFormat.formatDate(entity.getPubDate(),"yyyy-MM-dd HH:mm:ss"),
					entity.getCid(),
					entity.getLabel(),
					entity.getReadCount(),
					entity.getDing(),
					entity.getCai(),
					entity.getUid(),
					entity.getState(),
					entity.getStateUid(),
					MyDateFormat.formatDate(entity.getPubDate(), "yyyy-MM-dd HH:mm:ss")
					
			};
			i+=helper.executeUpdate(sql1, values);
			//执行事务的第二条语句
			String sql2="INSERT INTO optionlog(`userId`,optionDate,`option`)VALUES(?,?,?)";
			i+=helper.executeUpdate(sql2, oe.getUserid(),MyDateFormat.formatDate(oe.getOptionDate(), "yyyy-MM-dd HH:mm:ss"),oe.getOption());
			//提交事务
			helper.commit();
			log.info("用户保存成功");
		} catch (Exception e) {
			//回滚事务
			helper.rollback();
			log.error("用户保存失败，原因是"+e.getMessage());
			throw e;
		}finally{
			helper.close();
		}
		return i;
	}
	//查找并分页
	public List<KnowledgeEntity> findByUserId(int id,int pageIndex,int pageSize) throws Exception{
		KnowledgeEntity entity=new KnowledgeEntity();
		List<KnowledgeEntity> list =new ArrayList<KnowledgeEntity>();
		try {
			String sql = "SELECT * FROM k_nowledge WHERE uid=? LIMIT ?,?";
			Object values[] =new Object[]{id,(pageIndex-1)*pageSize,pageSize};
			list=helper.executeQuery(new KnowledgeMapper(), sql, values);
			if(list!=null&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					entity=list.get(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			helper.close();
		}
		return list;
	}
	/*public List<KnowledgeEntity> findByUserId(int id,int pageIndex,int pageSize) throws Exception{
		List<KnowledgeEntity> list =new ArrayList<KnowledgeEntity>();
		
		try {
			String sql = "SELECT * FROM k_nowledge WHERE uid=? LIMIT ?,?";
			Object values[] =new Object[]{id,(pageIndex-1)*pageSize,pageSize};
			ResultSet rs = helper.executeQuery(sql,values);
			while(rs.next()){
				KnowledgeEntity e =new KnowledgeEntity();
				e.setCai(rs.getInt("cai"));
				e.setCid(rs.getInt("cid"));
				e.setContent(rs.getString("content"));
				e.setDing(rs.getInt("ding"));
				e.setId(rs.getInt("id"));
				e.setLabel(rs.getString("label"));
				e.setPubDate(rs.getTimestamp("pubDate"));
				e.setReadCount(rs.getInt("readCount"));
				e.setState(rs.getInt("state"));
				e.setStateContent(rs.getString("stateContent"));
				e.setStateDate(rs.getTimestamp("stateDate"));
				e.setStateUid(rs.getInt("stateUid"));
				e.setTitle(rs.getString("title"));
				e.setUid(rs.getInt("uid"));
				list.add(e);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			helper.close();
		}
		return list;
	}*/
	 public int findById1() throws Exception{
		int  e =0;
		try {
			String sql="SELECT MAX(id) AS maxId FROM k_nowledge";
			e= helper.executeScalar(sql);
		} catch (Exception e2) {
			e2.printStackTrace();
		}finally{
			helper.close();
		}
		return e;
	}
	/*public KnowledgeEntity findById1() throws Exception{
		KnowledgeEntity e =null;
		try {
			String sql="SELECT MAX(id) AS maxId FROM k_nowledge";
			ResultSet rs=helper.executeQuery(sql);
			if(rs.next()){
				e=new KnowledgeEntity();
				e.setId(rs.getInt("maxId"));
			}
		} catch (Exception e2) {
			throw e2;
		}finally{
			helper.close();
		}
		return e;
	}*/
	public KnowledgeEntity findById(int id)throws Exception{
		KnowledgeEntity e=null;
		try {
			String sql = "select * from k_nowledge where id=?";
			List<KnowledgeEntity> list=helper.executeQuery(new KnowledgeMapper(), sql, id);
			if(list!=null&list.size()>0){
				e=list.get(0);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}finally{
			helper.close();
		}
		return e;
	}
	/*public KnowledgeEntity findById(int id) throws Exception{
		KnowledgeEntity e =null;
		try {
			String sql = "select * from k_nowledge where id=?";
			ResultSet rs = helper.executeQuery(sql, id);
			if(rs.next()){
				e =new KnowledgeEntity();
				e.setCai(rs.getInt("cai"));
				e.setCid(rs.getInt("cid"));
				e.setContent(rs.getString("content"));
				e.setDing(rs.getInt("ding"));
				e.setId(rs.getInt("id"));
				e.setLabel(rs.getString("label"));
				e.setPubDate(rs.getTimestamp("pubDate"));
				e.setReadCount(rs.getInt("readCount"));
				e.setState(rs.getInt("state"));
				e.setStateContent(rs.getString("stateContent"));
				e.setStateDate(rs.getTimestamp("stateDate"));
				e.setStateUid(rs.getInt("stateUid"));
				e.setTitle(rs.getString("title"));
				e.setUid(rs.getInt("uid"));
			}
		} catch (Exception ex) {
			throw ex;
		}finally{
			helper.close();
		}
		return e;
	}*/
	public int delete(int id) throws Exception{
		int i=0;
		try {
			String sql = "delete from k_nowledge where id=?";
			i =  helper.executeUpdate(sql, id);
		} catch (Exception ex) {
			throw ex;
		}finally{
			helper.close();
		}
		return i;
	}
	
	public int findCountByUserid(int id) throws Exception  {
		int i=0;
		try {
			String sql ="select count(*) from k_nowledge where uid=?";
			i =  helper.executeScalar(sql, id);
		} catch (Exception ex) {
			throw ex;
		}finally{
			helper.close();
		}
		return i;
		}
	//查询知识库中所有内容
	public List<KnowledgeEntity> findAllContent()throws Exception {
		KnowledgeEntity entity=new KnowledgeEntity();
		List<KnowledgeEntity> list=new ArrayList<KnowledgeEntity>();
		log.info("开始查找所有内容");
		try {
			String sql="SELECT * FROM k_nowledge";
			list=helper.executeQuery(new KnowledgeMapper(), sql);
			if(list!=null&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					entity=list.get(i);
				}
			}
			log.info("查找所有内容成功");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			helper.close();
		}
		return list;
		
		
	}
}
