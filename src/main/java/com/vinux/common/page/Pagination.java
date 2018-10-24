package com.vinux.common.page;

import java.io.Serializable;
import java.util.List;

public class Pagination<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//count=true, pageNum=1, pageSize=1, startRow=0, endRow=1, total=2, pages=2, reasonable=true, pageSizeZero=false
	private int pageNum;
	private int pageSize;
	private long total;
	private int pages;
	private List<T> list;
	
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public boolean hasNextPage() {
		if(pageNum >= pages) {
			return false;
		}else {
			return true;
		}
	}
	
	
}
