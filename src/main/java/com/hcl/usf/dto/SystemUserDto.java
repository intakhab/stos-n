package com.hcl.usf.dto;
/***
 * This is system user by default login
 * @author intakhabalam.s
 *
 */
public class SystemUserDto {
	private String email="admin@hcl.com";
	private String username="STOS";
	private String userpass="admin";
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
