package com.douzone.jblog.vo;

import javax.validation.constraints.NotEmpty;

public class CategoryVo {
	private long no;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	private String blogId;
	private int postCount;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", blogId=" + blogId
				+ ", postCount=" + postCount + "]";
	}
	
	
}
