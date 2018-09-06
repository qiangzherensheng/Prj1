package bj.dao.mapper;

import java.sql.ResultSet;

import bj.entity.KnowledgeEntity;

public class KnowledgeMapper implements IRowMapper<KnowledgeEntity>{

	@Override
	public KnowledgeEntity rowMapper(ResultSet rs) {
		KnowledgeEntity e=new KnowledgeEntity();
		try {
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
		} catch (Exception e2) {
			e2.printStackTrace();
			e=null;
		}
		return e;
	}
	
}
