package com.mall.dto;

import java.sql.Date;

public class ProductDto {
	private int pid;  
	private String pname;  
	private String ptype;  
	private String pcontent;  
	private String pphoto;  
	private int pprice; 
    private int phit;  
    private Date prdate;
    
    
    
	public ProductDto() {
		 
	}

	public ProductDto( String pname, String ptype, String pcontent, String pphoto, int pprice) {
		 
		this.pname = pname;
		this.ptype = ptype;
		this.pcontent = pcontent;
		this.pphoto = pphoto;
		this.pprice = pprice; 
	}



	public ProductDto( String pname, String ptype, String pcontent, String pphoto, int pprice, int pid) {
		 
		this.pname = pname;
		this.ptype = ptype;
		this.pcontent = pcontent;
		this.pphoto = pphoto;
		this.pprice = pprice;
		this.pid = pid;
	}



	public ProductDto(int pid, String pname, String ptype, String pcontent, String pphoto, int pprice) {

		this.pid = pid;
		this.pname = pname;
		this.ptype = ptype;
		this.pcontent = pcontent;
		this.pphoto = pphoto;
		this.pprice = pprice;
	}
	
	public ProductDto(int pid, String pname, String ptype, String pcontent, String pphoto, int pprice, Date prdate) {
		 
		this.pid = pid;
		this.pname = pname;
		this.ptype = ptype;
		this.pcontent = pcontent;
		this.pphoto = pphoto;
		this.pprice = pprice;
		this.prdate = prdate;
	}




	public ProductDto(int pid, String pname, String ptype, String pcontent, String pphoto, int pprice, int phit,
			Date prdate) {
		 
		this.pid = pid;
		this.pname = pname;
		this.ptype = ptype;
		this.pcontent = pcontent;
		this.pphoto = pphoto;
		this.pprice = pprice;
		this.phit = phit;
		this.prdate = prdate;
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




	public String getPtype() {
		return ptype;
	}




	public void setPtype(String ptype) {
		this.ptype = ptype;
	}




	public String getPcontent() {
		return pcontent;
	}




	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
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




	public int getPhit() {
		return phit;
	}




	public void setPhit(int phit) {
		this.phit = phit;
	}




	public Date getPrdate() {
		return prdate;
	}




	public void setPrdate(Date prdate) {
		this.prdate = prdate;
	}




	@Override
	public String toString() {
		return "ProductDto [pid=" + pid + ", pname=" + pname + ", ptype=" + ptype + ", pcontent=" + pcontent
				+ ", pphoto=" + pphoto + ", pprice=" + pprice + ", phit=" + phit + ", prdate=" + prdate + "]";
	}
}
