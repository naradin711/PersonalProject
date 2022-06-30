package com.mall.dto;

import java.sql.Date;

public class CustomerDto {
	private String cid;  
	private String cpw;  
	private String cname;  
	private String cemail;  
	private String ctel;  
	private String caddress;  
	private Date cbirth ;
	private Date crdate ;
	
	
	
	public CustomerDto() {
		super();
	}
	
	


	public CustomerDto(String cid, String cpw, String cname, String cemail, String ctel, String caddress, Date cbirth) {
		super();
		this.cid = cid;
		this.cpw = cpw;
		this.cname = cname;
		this.cemail = cemail;
		this.ctel = ctel;
		this.caddress = caddress;
		this.cbirth = cbirth;
	}




	public CustomerDto(String cid, String cpw, String cname, String cemail, String ctel, String caddress, Date cbirth,
			Date crdate) {
		super();
		this.cid = cid;
		this.cpw = cpw;
		this.cname = cname;
		this.cemail = cemail;
		this.ctel = ctel;
		this.caddress = caddress;
		this.cbirth = cbirth;
		this.crdate = crdate;
	}




	public String getCid() {
		return cid;
	}




	public void setCid(String cid) {
		this.cid = cid;
	}




	public String getCpw() {
		return cpw;
	}




	public void setCpw(String cpw) {
		this.cpw = cpw;
	}




	public String getCname() {
		return cname;
	}




	public void setCname(String cname) {
		this.cname = cname;
	}




	public String getCemail() {
		return cemail;
	}




	public void setCemail(String cemail) {
		this.cemail = cemail;
	}




	public String getCtel() {
		return ctel;
	}




	public void setCtel(String ctel) {
		this.ctel = ctel;
	}




	public String getCaddress() {
		return caddress;
	}




	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}




	public Date getCbirth() {
		return cbirth;
	}




	public void setCbirth(Date cbirth) {
		this.cbirth = cbirth;
	}




	public Date getCrdate() {
		return crdate;
	}




	public void setCrdate(Date crdate) {
		this.crdate = crdate;
	}




	@Override
	public String toString() {
		return "CustomerDto [cid=" + cid + ", cpw=" + cpw + ", cname=" + cname + ", cemail=" + cemail + ", ctel=" + ctel
				+ ", caddress=" + caddress + ", cbirth=" + cbirth + "]";
	}
	
	
	
}
