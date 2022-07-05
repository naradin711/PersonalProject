package com.mall.dto;

import java.sql.Date;

public class OrderListDto {
	private int odid;     
	private String cid;
	private String odtitle; 
	private String odaddress;      
    private int odprice;           
    private String odcall;  
    private Date oddate;
    
    
    
	public OrderListDto() { 
	}

	public OrderListDto(String cid, String odaddress, int odprice) {
		this.cid = cid;
		this.odaddress = odaddress;
		this.odprice = odprice; 
	}
	
	public OrderListDto(String cid, String odtitle, String odaddress, int odprice) {
		this.cid = cid;
		this.odtitle = odtitle;
		this.odaddress = odaddress;
		this.odprice = odprice;
	}
	
	public OrderListDto(int odid, String cid, String odtitle, String odaddress, int odprice, String odcall,
			Date oddate) {
		 
		this.odid = odid;
		this.cid = cid;
		this.odtitle = odtitle;
		this.odaddress = odaddress;
		this.odprice = odprice;
		this.odcall = odcall;
		this.oddate = oddate;
	}

	public int getOdid() {
		return odid;
	}

	public void setOdid(int odid) {
		this.odid = odid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getOdtitle() {
		return odtitle;
	}

	public void setOdtitle(String odtitle) {
		this.odtitle = odtitle;
	}

	public String getOdaddress() {
		return odaddress;
	}

	public void setOdaddress(String odaddress) {
		this.odaddress = odaddress;
	}

	public int getOdprice() {
		return odprice;
	}

	public void setOdprice(int odprice) {
		this.odprice = odprice;
	}

	public String getOdcall() {
		return odcall;
	}

	public void setOdcall(String odcall) {
		this.odcall = odcall;
	}

	public Date getOddate() {
		return oddate;
	}

	public void setOddate(Date oddate) {
		this.oddate = oddate;
	}

	@Override
	public String toString() {
		return "OrderListDto [odid=" + odid + ", cid=" + cid + ", odtitle=" + odtitle + ", odaddress=" + odaddress
				+ ", odprice=" + odprice + ", odcall=" + odcall + ", oddate=" + oddate + "]";
	}
	
	
	
	
	 
	
    
	
    
}
