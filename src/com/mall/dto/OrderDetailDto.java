package com.mall.dto;

import java.sql.Date;

public class OrderDetailDto {
	private int oddid;     
	private String cid; 
	private int pid;
	private String pname; 
    private int pprice;
    
    
    
	public OrderDetailDto() { 
	}

	public OrderDetailDto(String cid, int pid, String pname, int pprice) {
		  
		this.cid = cid;
		this.pid = pid;
		this.pname = pname;
		this.pprice = pprice;
	}


	public OrderDetailDto(int oddid, String cid, int pid, String pname, int pprice) {
		 
		this.oddid = oddid;
		this.cid = cid;
		this.pid = pid;
		this.pname = pname;
		this.pprice = pprice;
	}

	public int getOddid() {
		return oddid;
	}

	public void setOddid(int oddid) {
		this.oddid = oddid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	@Override
	public String toString() {
		return "OrderDetailDto [oddid=" + oddid + ", cid=" + cid + ", pid=" + pid + ", pname=" + pname + ", pprice="
				+ pprice + "]";
	}

	 
	
	
	
	
	 
	
    
	
    
}
