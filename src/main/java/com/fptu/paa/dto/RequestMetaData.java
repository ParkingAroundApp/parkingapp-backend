package com.fptu.paa.dto;

public class RequestMetaData {
	private String pageSize;
	private String bookmark;
	
	public RequestMetaData() {
		
	}
	
	public RequestMetaData(String pageSize, String bookmark) {
		super();
		this.pageSize = pageSize;
		this.bookmark = bookmark;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getBookmark() {
		return bookmark;
	}
	public void setBookmark(String bookmark) {
		this.bookmark = bookmark;
	}
	
	
}
