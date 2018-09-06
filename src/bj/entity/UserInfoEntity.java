package bj.entity;

public class UserInfoEntity {
	private int id;
	private String userName;
	private String userPass;
	private String nickName;
	private String headerImage="1.jpg";
	private String introduce="编辑自我介绍，让更多人了解你";
	
	public UserInfoEntity(String userName, String userPass, String nickName, String headerImage, String introduce) {
		super();
		this.userName = userName;
		this.userPass = userPass;
		this.nickName = nickName;
		this.headerImage = headerImage;
		this.introduce = introduce;
	}
	
	public UserInfoEntity(String userName, String userPass, String nickName, String introduce) {
		super();
		this.userName = userName;
		this.userPass = userPass;
		this.nickName = nickName;
		this.introduce = introduce;
	}

	public UserInfoEntity(int id, String userName, String userPass, String nickName, String introduce) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPass = userPass;
		this.nickName = nickName;
		this.introduce = introduce;
	}

	public UserInfoEntity() {
		super();
	}
	public UserInfoEntity(int id, String userName, String userPass, String nickName, String headerImage,
			String introduce) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPass = userPass;
		this.nickName = nickName;
		this.headerImage = headerImage;
		this.introduce = introduce;
	}
	public UserInfoEntity(String userName, String userPass) {
		super();
		this.userName = userName;
		this.userPass = userPass;
	}
	public UserInfoEntity(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeaderImage() {
		return headerImage;
	}
	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
}
