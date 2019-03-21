package org.cloudCorp.model;

import java.util.Date;

public class Friend {

	private int friendId;
	private int userInfoAId;
	private UserInfo userInfoA ;
	private int userInfoBId;
	private UserInfo userInfoB ;
	private String status;
	private Date startTime;

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public int getUserInfoAId() {
		return userInfoAId;
	}

	public void setUserInfoAId(int userInfoAId) {
		this.userInfoAId = userInfoAId;
	}

	public int getUserInfoBId() {
		return userInfoBId;
	}

	public void setUserInfoBId(int userInfoBId) {
		this.userInfoBId = userInfoBId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public UserInfo getUserInfoA() {
		return userInfoA;
	}

	public void setUserInfoA(UserInfo userInfoA) {
		this.userInfoA = userInfoA;
	}

	public UserInfo getUserInfoB() {
		return userInfoB;
	}

	public void setUserInfoB(UserInfo userInfoB) {
		this.userInfoB = userInfoB;
	}
	
	

}
