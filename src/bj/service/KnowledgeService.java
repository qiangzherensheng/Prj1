package bj.service;

import java.util.List;

import bj.dao.CatagoryDao;
import bj.dao.KnowledgeDao;
import bj.dao.UserInfoDao;
import bj.entity.CatagoryEntity;
import bj.entity.KnowledgeEntity;
import bj.entity.OptionlogEntity;
import bj.entity.UserInfoEntity;

public class KnowledgeService {
	private KnowledgeDao dao=new KnowledgeDao();
	public int save(KnowledgeEntity entity,OptionlogEntity oe) throws Exception{
		return dao.save(entity,oe);
	}
	public int update(KnowledgeEntity entity) throws Exception{
		return dao.update(entity);
	}
	public List<KnowledgeEntity> findByUserId(int id,int pageIndex,int pageSize) throws Exception{
		List<KnowledgeEntity> entitys=dao.findByUserId(id,pageIndex,pageSize);
		if(entitys!=null&&entitys.size()>0){
			CatagoryDao catagoryDao = new CatagoryDao();
			UserInfoDao userInfoDao =new UserInfoDao();
			for (int i = 0; i < entitys.size(); i++) {
				CatagoryEntity catagoryEntity=catagoryDao.findById(entitys.get(i).getCid());
				entitys.get(i).setCatagoryEntity(catagoryEntity);
				UserInfoEntity userInfoEntity=userInfoDao.findById(entitys.get(i).getUid());
				entitys.get(i).setUserInfoEntity(userInfoEntity);
			}
		}
		return entitys;
	}
	public KnowledgeEntity findById(int id) throws Exception{
		KnowledgeEntity entity=dao.findById(id);
		CatagoryEntity catagoryEntity=new CatagoryDao().findById(entity.getCid());
		entity.setCatagoryEntity(catagoryEntity);
		return entity;
	}
	public int findById1() throws Exception{
		return dao.findById1();
	}
	public int delete(int id) throws Exception{
		return dao.delete(id);
	}
	public int findCountByUserid(int id) throws Exception {
		return dao.findCountByUserid(id);
	}
	public List<KnowledgeEntity> findAllContent() throws Exception {
		List<KnowledgeEntity> entitys=dao.findAllContent();
		if(entitys!=null&&entitys.size()>0){
			CatagoryDao catagoryDao = new CatagoryDao();
			UserInfoDao userInfoDao =new UserInfoDao();
			for (int i = 0; i < entitys.size(); i++) {
				CatagoryEntity catagoryEntity=catagoryDao.findById(entitys.get(i).getCid());
				entitys.get(i).setCatagoryEntity(catagoryEntity);
				UserInfoEntity userInfoEntity=userInfoDao.findById(entitys.get(i).getUid());
				entitys.get(i).setUserInfoEntity(userInfoEntity);
			}
		}
		return entitys;
	}
}
