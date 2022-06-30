package com.mall.dto;

public class CartDto {
	private int cartid;        
	private String cid;         
	private String pname;         
	private String ptype;         
	private String pphoto;        
    private int pprice;
    
    
    
	public CartDto() {
	 
	}

	


	public CartDto(int cartid, String cid, String pname, String ptype, String pphoto, int pprice) {
	 
		this.cartid = cartid;
		this.cid = cid;
		this.pname = pname;
		this.ptype = ptype;
		this.pphoto = pphoto;
		this.pprice = pprice;
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




	@Override
	public String toString() {
		return "CartDto [cartid=" + cartid + ", cid=" + cid + ", pname=" + pname + ", ptype=" + ptype + ", pphoto="
				+ pphoto + ", pprice=" + pprice + "]";
	}
    
	
    
}
