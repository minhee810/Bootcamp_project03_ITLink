package com.team2.dao;

public class AdminDAO {
	
	private int a_NO = 0;
	private String a_Id = null;
	private String a_Pw = null;

	public AdminDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public AdminDAO(int a_NO, String a_ID, String a_PW) {
		this.a_NO = a_NO;
		this.a_Id = a_ID;
		this.a_Pw = a_PW;
	}

	public int getA_NO() {
		return a_NO;
	}

	public void setA_NO(int a_NO) {
		this.a_NO = a_NO;
	}

	public String getA_Id() {
		return a_Id;
	}

	public void setA_Id(String a_Id) {
		this.a_Id = a_Id;
	}

	public String getA_Pw() {
		return a_Pw;
	}

	public void setA_Pw(String a_Pw) {
		this.a_Pw = a_Pw;
	}

	@Override
	public String toString() {
		return "AdminDAO [a_NO=" + a_NO + ", a_Id=" + a_Id + ", a_Pw=" + a_Pw + "]";
	}

	

}
