package bj.dao;

import java.util.List;

import bj.dao.mapper.AttachMapper;
import bj.entity.AttachEntity;
import bj.entity.KnowledgeEntity;

public class AttachDao {
	DBHelper helper=new DBHelper();
	public int save(AttachEntity entity) throws Exception{
			int i=0;
				try {
					String sql="insert into k_attachment(kid,filename) values(?,?)";
					Object values[]=new Object[]{
						entity.getKid(),
						entity.getFilename()
						
					};
					i=helper.executeUpdate(sql, values);
				} catch (Exception e) {
					throw e;
					
				}finally{
					helper.close();
				}
				return i;
		}
	public AttachEntity findByKid(KnowledgeEntity en) throws Exception{
		AttachEntity attachEntity=null;
		try {
			String sql="SELECT * FROM k_attachment WHERE kid=?";
			List<AttachEntity> list=helper.executeQuery(new AttachMapper(), sql, en.getId());
			if(list!=null&list.size()>0){
				attachEntity=list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			attachEntity=null;
		}finally{
			helper.close();
		}
		return attachEntity;
	}
	/*public AttachEntity findByKid(KnowledgeEntity entity) throws Exception{
		AttachEntity attachEntity=null;
		try {
			String sql="SELECT * FROM k_attachment WHERE kid=?";
			ResultSet rs=helper.executeQuery(sql, entity.getId());
			if(rs.next()){
				attachEntity=new AttachEntity();
				attachEntity.setId(rs.getInt("id"));
				attachEntity.setKid(rs.getInt("kid"));
				attachEntity.setFilename(rs.getString("filename"));
			}
		} catch (Exception e) {
			throw e;
		}finally{
			helper.close();
		}
		return attachEntity;
	}*/
	public AttachEntity  findFileName(int id) throws Exception{
		AttachEntity entity=null;
		try {
			String sql="SELECT * FROM k_attachment WHERE id=?";
			List<AttachEntity> list=helper.executeQuery(new AttachMapper(), sql, id);
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
	/*public AttachEntity findFileName(int id) throws Exception{
		AttachEntity entity=null;
		try {
			String sql="SELECT * FROM k_attachment WHERE id=?";
			ResultSet rs=helper.executeQuery(sql, id);
			if(rs.next()){
				entity=new AttachEntity();
				entity.setId(rs.getInt("id"));
				entity.setKid(rs.getInt("kid"));
				entity.setFilename(rs.getString("filename"));
			}
		} catch (Exception e) {
			throw e;
		}finally{
			helper.close();
		}
		return entity;
	}*/
	}
		
