package com.framgia.bean;

/**
 * 
 * @version 06/06/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
public class PagingImage {
	private int noOfPage;
	private int noOfRecord;
	private int currentPage;
	private int nextPage;
	private int prePage;

	public PagingImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PagingImage(int noRecord, int noOfPage, int currentPage, int nextPage, int prePage) {
		super();
		this.noOfPage = noOfPage;
		this.noOfRecord = noRecord;
		this.currentPage = currentPage;
		this.nextPage = nextPage;
		this.prePage = prePage;
	}

	public int getNoOfPage() {
		return noOfPage;
	}

	public void setNoOfPage(int noOfPage) {
		this.noOfPage = noOfPage;
	}

	public int getNoOfRecord() {
		return noOfRecord;
	}

	public void setNoOfRecord(int noOfRecord) {
		this.noOfRecord = noOfRecord;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

}