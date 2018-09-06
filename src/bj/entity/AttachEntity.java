package bj.entity;

public class AttachEntity {
	private int id;
	private int kid;
	private String filename;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKid() {
		return kid;
	}
	public void setKid(int attach) {
		this.kid = attach;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public AttachEntity(int id) {
		super();
		this.id = id;
	}
	public AttachEntity(int id, int kid, String filename) {
		super();
		this.id = id;
		this.kid = kid;
		this.filename = filename;
	}
	public AttachEntity() {
		super();
	}
	
}
