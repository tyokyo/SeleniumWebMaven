package cn.bean;

public class FollowBean {
	@Override
	public String toString() {
		return "FollowBean [nickname=" + nickname + ", sioeyeid=[" + sioeyeid
				+ "], follow=" + follow + "]";
	}
	String nickname;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSioeyeid() {
		return sioeyeid;
	}
	public void setSioeyeid(String sioeyeid) {
		this.sioeyeid = sioeyeid;
	}
	String sioeyeid;
	String follow;
	public String getFollow() {
		return follow;
	}
	public void setFollow(String follow) {
		this.follow = follow;
	}
	
}
