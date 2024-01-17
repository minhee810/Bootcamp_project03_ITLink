package com.team2.dao;


	// Board table 
public class BoardDAO {
	
	private int b_No = 0;
	private String b_Title = null;
	private String b_Link = null;
	private String b_Category = null;
	private String reg_date = null;
	private int b_hit = 0;
	private int b_like = 0;
			
	public BoardDAO() {
		
	}
	
	public BoardDAO(int b_No, String b_Title, String b_Link, String b_Category, 
			String reg_date, int b_hit, int b_like ) {
		this.b_No = b_No;
		this.b_Title = b_Title;
		this.b_Link = b_Link;
		this.b_Category = b_Category;
		this.reg_date = reg_date;
		this.b_hit = b_hit;
		this.b_like = b_like;

	}
	
	

	public BoardDAO(int b_No, String b_Title, String reg_date, int b_hit, int b_like) {
		super();
		this.b_No = b_No;
		this.b_Title = b_Title;
		this.reg_date = reg_date;
		this.b_hit = b_hit;
		this.b_like = b_like;
	}

	public int getB_No() {
		return b_No;
	}

	public void setB_No(int b_No) {
		this.b_No = b_No;
	}

	public String getB_Title() {
		return b_Title;
	}

	public void setB_Title(String b_Title) {
		this.b_Title = b_Title;
	}

	public String getB_Link() {
		return b_Link;
	}

	public void setB_Link(String b_Link) {
		this.b_Link = b_Link;
	}

	public String getB_Category() {
		return b_Category;
	}

	public void setB_Category(String b_Category) {
		this.b_Category = b_Category;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getB_hit() {
		return b_hit;
	}

	public void setB_hit(int b_hit) {
		this.b_hit = b_hit;
	}

	public int getB_like() {
		return b_like;
	}

	public void setB_like(int b_like) {
		this.b_like = b_like;
	}

	@Override
	public String toString() {
		return "BoardDAO [b_No=" + b_No + ", b_Title=" + b_Title + ", b_Link=" + b_Link + ", b_Category=" + b_Category
				+ ", reg_date=" + reg_date + ", b_hit=" + b_hit + ", b_like=" + b_like + "]";
	}


	

}
