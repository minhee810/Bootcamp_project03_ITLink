package com.team2.dao;

public class UsersDAO {
	private int u_No = 0;
	private String u_id = null;
	private String u_pw = null;
	private String u_name = null;
	private String uWriter = null;
	
	
	public UsersDAO() {
	}
	
	
	public UsersDAO(int u_No, String u_id, String u_pw, String u_name) {
		this.u_No = u_No;
		this.u_id = u_id;
		this.u_pw = u_pw;
		this.u_name = u_name;
	}


	public UsersDAO(int u_No, String u_id, String u_pw, String u_name, String uWriter) {
	
		this.u_No = u_No;
		this.u_id = u_id;
		this.u_pw = u_pw;
		this.u_name = u_name;
		this.uWriter = uWriter;
	}
	
	public UsersDAO(String u_id, String u_pw) {
		this.u_id = u_id;
		this.u_pw = u_pw;
	}


	public int getU_No() {
		return u_No;
	}


	public void setU_No(int u_No) {
		this.u_No = u_No;
	}


	public String getU_id() {
		return u_id;
	}


	public void setU_id(String u_id) {
		this.u_id = u_id;
	}


	public String getU_pw() {
		return u_pw;
	}


	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}


	public String getU_name() {
		return u_name;
	}


	public void setU_name(String u_name) {
		this.u_name = u_name;
	}


	public String getuWriter() {
		return uWriter;
	}


	public void setuWriter(String uWriter) {
		this.uWriter = uWriter;
	}


	@Override
	public String toString() {
		return "UsersDAO [u_No=" + u_No + ", u_id=" + u_id + ", u_pw=" + u_pw + ", u_name=" + u_name + ", uWriter="
				+ uWriter + "]";
	}
	
	
}
