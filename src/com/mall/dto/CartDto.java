package com.mall.dto;

public class CartDto {
	private int cartid;        
	private String cid;
	private int pid;
	private String pname;     
	private String pphoto;        
    private int pprice;
    
    
    
	public CartDto() {
	 
	}



	public CartDto(int cartid, String cid, int pid, String pname, String pphoto, int pprice) {
		 
		this.cartid = cartid;
		this.cid = cid;
		this.pid = pid;
		this.pname = pname;
		this.pphoto = pphoto;
		this.pprice = pprice;
	}



	public CartDto(String cid, int pid, String pname, String pphoto, int pprice) {
		super();
		this.cid = cid;
		this.pid = pid;
		this.pname = pname;
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
		return "CartDto [cartid=" + cartid + ", cid=" + cid + ", pid=" + pid + ", pname=" + pname + ", pphoto=" + pphoto
				+ ", pprice=" + pprice + "]";
	}
	
	
	 
    
	
    
}
