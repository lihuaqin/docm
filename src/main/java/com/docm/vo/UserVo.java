package com.docm.vo;

import java.util.List;

/*
 * @ Copyright (c) Create by JASON  Date:2018-04-14  All rights reserved.
 *
 * @ class description：用户信息实体
 *
 */
public class UserVo extends BaseVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 836024724337567883L;
	private String userName;
	private String password;
	private String address;
	private String telphone;
	private String role;
	private int status;
	
	private List<String> roles;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	
	
	
	

}


