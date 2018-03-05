package cn.bean;

public class WatchBean {
	@Override
	public String toString() {
		return "WatchBean [nickname=" + nickname + ", sioeyeid=" + sioeyeid
				+ ", videocount=" + videocount + ", followcount=" + followcount
				+ ", fanscount=" + fanscount + ", zancount=" + zancount
				+ ", location=" + location + ", hobby=" + hobby
				+ ", signature=" + signature + "]";
	}
	private String nickname;
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
	public int getVideocount() {
		return videocount;
	}
	public void setVideocount(int videocount) {
		this.videocount = videocount;
	}
	public int getFollowcount() {
		return followcount;
	}
	public void setFollowcount(int followcount) {
		this.followcount = followcount;
	}
	public int getFanscount() {
		return fanscount;
	}
	public void setFanscount(int fanscount) {
		this.fanscount = fanscount;
	}
	public int getZancount() {
		return zancount;
	}
	public void setZancount(int zancount) {
		this.zancount = zancount;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	private String sioeyeid;
	private int videocount;
	private int followcount;
	private int fanscount;
	private int zancount;
	private String location;
	private String hobby;
	private String signature;
}
