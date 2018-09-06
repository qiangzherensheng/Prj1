package bj.service;

import bj.dao.AttachDao;
import bj.entity.AttachEntity;
import bj.entity.KnowledgeEntity;

public class AttachService {
	private AttachDao dao=new AttachDao();
	public int save(AttachEntity entity) throws Exception{
		return dao.save(entity);
	}
	public AttachEntity findByKid(KnowledgeEntity en)throws Exception{
		return dao.findByKid(en);
	}
	public AttachEntity findFileName(int id) throws Exception{
		return dao.findFileName(id);
	}
}
