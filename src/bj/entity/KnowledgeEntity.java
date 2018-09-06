package bj.entity;

import java.util.Date;

public class KnowledgeEntity {
	private int id;
	private String title;
	private String content;
	private Date pubDate;
	private int cid;
	private String label;
	private int readCount;
	private int ding;
	private int cai;
	private int uid;
	private int state;
	private int stateUid;
	private Date stateDate;
	private String stateContent;
	//在1对多的关系中，将一方实体作为多方的属性
	private CatagoryEntity catagoryEntity =new CatagoryEntity();
	private UserInfoEntity userInfoEntity =new UserInfoEntity();
	
	
	
	public CatagoryEntity getCatagoryEntity() {
		return catagoryEntity;
	}
	public void setCatagoryEntity(CatagoryEntity catagoryEntity) {
		this.catagoryEntity = catagoryEntity;
	}
	public UserInfoEntity getUserInfoEntity() {
		return userInfoEntity;
	}
	public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
		this.userInfoEntity = userInfoEntity;
	}
	public KnowledgeEntity() {
		super();
	}
	public KnowledgeEntity(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getDing() {
		return ding;
	}
	public void setDing(int ding) {
		this.ding = ding;
	}
	public int getCai() {
		return cai;
	}
	public void setCai(int cai) {
		this.cai = cai;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getStateUid() {
		return stateUid;
	}
	public void setStateUid(int stateUid) {
		this.stateUid = stateUid;
	}
	public Date getStateDate() {
		return stateDate;
	}
	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}
	public String getStateContent() {
		return stateContent;
	}
	public void setStateContent(String stateContent) {
		this.stateContent = stateContent;
	}
	
	
	
}
