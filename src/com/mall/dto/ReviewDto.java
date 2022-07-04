package com.mall.dto;

import java.sql.Date;

public class ReviewDto {
	private int rbid;     
	private String cid;
	private int pid;
	private String rbtitle;   
	private String rbcontent;   
	private String rbphoto;  
	private Date rbrdate;  
    private String rbip;     
    private String rbpw;
    
    
    
	public ReviewDto() {
		 
	}



	public ReviewDto(int rbid, String cid, int pid, String rbtitle, String rbcontent, String rbphoto, Date rbrdate,
			String rbip, String rbpw) {
		 
		this.rbid = rbid;
		this.cid = cid;
		this.pid = pid;
		this.rbtitle = rbtitle;
		this.rbcontent = rbcontent;
		this.rbphoto = rbphoto;
		this.rbrdate = rbrdate;
		this.rbip = rbip;
		this.rbpw = rbpw;
	}
	public ReviewDto(String cid, int pid, String rbtitle, String rbcontent, String rbphoto,
			String rbip, String rbpw) {
		  
		this.cid = cid;
		this.pid = pid;
		this.rbtitle = rbtitle;
		this.rbcontent = rbcontent;
		this.rbphoto = rbphoto;
		this.rbip = rbip;
		this.rbpw = rbpw;
	}
	public ReviewDto(String rbtitle, String rbcontent, String rbphoto,
			String rbip,int rbid, String rbpw) {
		   
		this.rbtitle = rbtitle;
		this.rbcontent = rbcontent;
		this.rbphoto = rbphoto;
		this.rbip = rbip;
		this.rbid = rbid;
		this.rbpw = rbpw;
	}




	public int getRbid() {
		return rbid;
	}



	public void setRbid(int rbid) {
		this.rbid = rbid;
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



	public String getRbtitle() {
		return rbtitle;
	}



	public void setRbtitle(String rbtitle) {
		this.rbtitle = rbtitle;
	}



	public String getRbcontent() {
		return rbcontent;
	}



	public void setRbcontent(String rbcontent) {
		this.rbcontent = rbcontent;
	}



	public String getRbphoto() {
		return rbphoto;
	}



	public void setRbphoto(String rbphoto) {
		this.rbphoto = rbphoto;
	}



	public Date getRbrdate() {
		return rbrdate;
	}



	public void setRbrdate(Date rbrdate) {
		this.rbrdate = rbrdate;
	}



	public String getRbip() {
		return rbip;
	}



	public void setRbip(String rbip) {
		this.rbip = rbip;
	}



	public String getRbpw() {
		return rbpw;
	}



	public void setRbpw(String rbpw) {
		this.rbpw = rbpw;
	}



	@Override
	public String toString() {
		return "ReviewDto [rbid=" + rbid + ", cid=" + cid + ", pid=" + pid + ", rbtitle=" + rbtitle + ", rbcontent="
				+ rbcontent + ", rbphoto=" + rbphoto + ", rbrdate=" + rbrdate + ", rbip=" + rbip + ", rbpw=" + rbpw
				+ "]";
	}
	
	
	
    
    
}
