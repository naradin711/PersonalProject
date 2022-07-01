package com.mall.dto;

import java.sql.Date;

public class FreeBoardDto {
	private int 	fbid; 
	private String 	cid; 
	private String 	aname; 
	private String 	fbtitle; 
	private String 	fbcontent; 
	private String 	fbphoto; 
	private Date 	fbrdate; 
    private String 	fbip;      
    private int 	fbgroup;  
    private int 	fbstep;   
    private int 	fbindent;  
    private String 	fbpw;
    
    public FreeBoardDto() { 
	}
    
    
    
    public FreeBoardDto(String fbtitle, String fbcontent, String fbphoto, String fbip, int fbid) {
		super();
		this.fbid = fbid;
		this.fbtitle = fbtitle;
		this.fbcontent = fbcontent;
		this.fbphoto = fbphoto;
		this.fbip = fbip;
	}



	public FreeBoardDto(int fbid, String cid, String fbtitle, String fbcontent, String fbphoto,
			String fbip, int fbgroup, int fbstep, int fbindent, String fbpw) {
		super();
		this.fbid = fbid;
		this.cid = cid;
		this.fbtitle = fbtitle;
		this.fbcontent = fbcontent;
		this.fbphoto = fbphoto;
		this.fbip = fbip;
		this.fbgroup = fbgroup;
		this.fbstep = fbstep;
		this.fbindent = fbindent;
		this.fbpw = fbpw;
	}
    
	public FreeBoardDto(int fbid, String cid, String aname, String fbtitle, String fbcontent, String fbphoto,
			String fbip, int fbgroup, int fbstep, int fbindent, String fbpw) {
		super();
		this.fbid = fbid;
		this.cid = cid;
		this.aname = aname;
		this.fbtitle = fbtitle;
		this.fbcontent = fbcontent;
		this.fbphoto = fbphoto;
		this.fbip = fbip;
		this.fbgroup = fbgroup;
		this.fbstep = fbstep;
		this.fbindent = fbindent;
		this.fbpw = fbpw;
	}



	public FreeBoardDto(int fbid, String cid, String aname, String fbtitle, String fbcontent, String fbphoto,
			Date fbrdate, String fbip, int fbgroup, int fbstep, int fbindent, String fbpw) {
		 
		this.fbid = fbid;
		this.cid = cid;
		this.aname = aname;
		this.fbtitle = fbtitle;
		this.fbcontent = fbcontent;
		this.fbphoto = fbphoto;
		this.fbrdate = fbrdate;
		this.fbip = fbip;
		this.fbgroup = fbgroup;
		this.fbstep = fbstep;
		this.fbindent = fbindent;
		this.fbpw = fbpw;
 
	}
	public FreeBoardDto( String cid, String fbtitle, String fbcontent, String fbphoto,
			             String fbip, String fbpw) { 
		this.cid = cid; 
		this.fbtitle = fbtitle;
		this.fbcontent = fbcontent;
		this.fbphoto = fbphoto; 
		this.fbip = fbip; 
		this.fbpw = fbpw;
 
	}
	public FreeBoardDto( String cid, String fbtitle, String fbcontent, String fbphoto,
            String fbip, int fbid, String fbpw) { 
			this.cid = cid; 
			this.fbtitle = fbtitle;
			this.fbcontent = fbcontent;
			this.fbphoto = fbphoto; 
			this.fbip = fbip;
			this.fbid = fbid; 
			this.fbpw = fbpw;

	}



	public FreeBoardDto(String fbtitle, String fbcontent, String fbphoto, int fbid, String fbpw) {
		 
		this.fbtitle = fbtitle;
		this.fbcontent = fbcontent;
		this.fbphoto = fbphoto; 
		this.fbid = fbid; 
		this.fbpw = fbpw;
	}
	
	public FreeBoardDto(int fbid, String cid, String aname, String fbtitle, String fbcontent, String fbphoto,
			String fbip, int fbgroup, String fbpw) { 
		this.cid = cid;
		this.aname = aname;
		this.fbid = fbid; 
		this.fbtitle = fbtitle;
		this.fbcontent = fbcontent;
		this.fbphoto = fbphoto;
		this.fbip = fbip;
		this.fbgroup = fbgroup; 
		this.fbpw = fbpw;
	}


	public int getFbid() {
		return fbid;
	}



	public void setFbid(int fbid) {
		this.fbid = fbid;
	}



	public String getCid() {
		return cid;
	}



	public void setCid(String cid) {
		this.cid = cid;
	}



	public String getAname() {
		return aname;
	}



	public void setAname(String aname) {
		this.aname = aname;
	}



	public String getFbtitle() {
		return fbtitle;
	}



	public void setFbtitle(String fbtitle) {
		this.fbtitle = fbtitle;
	}



	public String getFbcontent() {
		return fbcontent;
	}



	public void setFbcontent(String fbcontent) {
		this.fbcontent = fbcontent;
	}



	public String getFbphoto() {
		return fbphoto;
	}



	public void setFbphoto(String fbphoto) {
		this.fbphoto = fbphoto;
	}



	public Date getFbrdate() {
		return fbrdate;
	}



	public void setFbrdate(Date fbrdate) {
		this.fbrdate = fbrdate;
	}



	public String getFbip() {
		return fbip;
	}



	public void setFbip(String fbip) {
		this.fbip = fbip;
	}



	public int getFbgroup() {
		return fbgroup;
	}



	public void setFbgroup(int fbgroup) {
		this.fbgroup = fbgroup;
	}



	public int getFbstep() {
		return fbstep;
	}



	public void setFbstep(int fbstep) {
		this.fbstep = fbstep;
	}



	public int getFbindent() {
		return fbindent;
	}



	public void setFbindent(int fbindent) {
		this.fbindent = fbindent;
	}



	public String getFbpw() {
		return fbpw;
	}



	public void setFbpw(String fbpw) {
		this.fbpw = fbpw;
	}



	@Override
	public String toString() {
		return "FreeBoardDto [fbid=" + fbid + ", cid=" + cid + ", aname=" + aname + ", fbtitle=" + fbtitle
				+ ", fbcontent=" + fbcontent + ", fbphoto=" + fbphoto + ", fbrdate=" + fbrdate + ", fbip=" + fbip
				+ ", fbgroup=" + fbgroup + ", fbstep=" + fbstep + ", fbindent=" + fbindent + ", fbpw=" + fbpw + "]";
	}
	
	
    
    
}
