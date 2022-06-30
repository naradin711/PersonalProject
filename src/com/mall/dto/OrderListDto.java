package com.mall.dto;

import java.sql.Date;

public class OrderListDto {
	private int odid;            
	private int cartid;          
	private String cid;
	private String caddress; 
	private String pname;        
	private String ptype;         
	private String pphoto;        
    private int pprice;           
    private String odcall;  
    private Date oddate;
    
    
    
	public OrderListDto() { 
	}

	public OrderListDto(int odid, int cartid, String cid, String caddress, String pname, String ptype, String pphoto,
			int pprice,  Date oddate) {
		 
		this.odid = odid;
		this.cartid = cartid;
		this.cid = cid;
		this.caddress = caddress;
		this.pname = pname;
		this.ptype = ptype;
		this.pphoto = pphoto;
		this.pprice = pprice; 
		this.oddate = oddate;
	}

	public OrderListDto(int odid, int cartid, String cid, String caddress, String pname, String ptype, String pphoto,
			int pprice, String odcall, Date oddate) {
		 
		this.odid = odid;
		this.cartid = cartid;
		this.cid = cid;
		this.caddress = caddress;
		this.pname = pname;
		this.ptype = ptype;
		this.pphoto = pphoto;
		this.pprice = pprice;
		this.odcall = odcall;
		this.oddate = oddate;
	}



	public int getOdid() {
		return odid;
	}



	public void setOdid(int odid) {
		this.odid = odid;
	}



	public int getCartid() {
		return cartid;
	}



	public void setCartid(int cartid) {
		this.cartid = cartid;
	}



	public String getCid() {
		return cid;
	}



	public void setCid(String cid) {
		this.cid = cid;
	}



	public String getCaddress() {
		return caddress;
	}



	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}



	public String getPname() {
		return pname;
	}



	public void setPname(String pname) {
		this.pname = pname;
	}



	public String getPtype() {
		return ptype;
	}



	public void setPtype(String ptype) {
		this.ptype = ptype;
	}



	public String getPphoto() {
		return pphoto;
	}



	public void setPphoto(String pphoto) {
		this.pphoto = pphoto;
	}



	public int getPprice() {
		return pprice;
	}



	public void setPprice(int pprice) {
		this.pprice = pprice;
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
		return "OrderListDto [odid=" + odid + ", cartid=" + cartid + ", cid=" + cid + ", caddress=" + caddress
				+ ", pname=" + pname + ", ptype=" + ptype + ", pphoto=" + pphoto + ", pprice=" + pprice + ", odcall="
				+ odcall + ", oddate=" + oddate + "]";
	}
	
	
	
	 
	
    
	
    
}
