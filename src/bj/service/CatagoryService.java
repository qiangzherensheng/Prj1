package bj.service;

import java.util.List;

import bj.dao.CatagoryDao;
import bj.entity.CatagoryEntity;

public class CatagoryService {
	private CatagoryDao dao=new CatagoryDao();
	public List<CatagoryEntity> findAll() throws Exception{
		return dao.findAll();
	}
	public CatagoryEntity findById(int cid) throws Exception{
		return dao.findById(cid);
	}
}
