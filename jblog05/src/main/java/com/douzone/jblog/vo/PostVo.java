package com.douzone.jblog.vo;

import javax.validation.constraints.NotEmpty;

public class PostVo {
	private long no;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String contents;
	
	private long categoryNo;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(long categoryNo) {
		this.categoryNo = categoryNo;
	}
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", contents=" + contents + ", categoryNo=" + categoryNo + "]";
	}
	
	
}
