package com.jsp.ums.responseDto;

import lombok.Builder;
@Builder
public class UserResponse {

	
	private int userId;
	private String userName;
	private String userEmail;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public UserResponse(int userId, String userName, String userEmail) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
	}
	public UserResponse() {
		super();
	}
	
	
	
}
