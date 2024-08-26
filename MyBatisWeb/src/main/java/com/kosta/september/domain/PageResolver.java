package com.kosta.september.domain;

public class PageResolver {
	private int totalCnt;				// 게시물 총 건수
	private int pageSize = 10;   // 한 페이지당 게시물 건수
	public final int NAV_SIZE = 10; 	// page navigation size
	
	private int totalPage;			// 전체 페이지 갯수
	private int page;					// 현재 페이지
	
	private int beginPage;			// 화면에 보여줄 첫 페이지
	private int endPage;				// 화면에 보여줄 마지막 페이지
	private boolean showNext = false;			// 이후를 보여줄지 여부 (endPage == totalPage showNext는 false)
	private boolean showPrev = false;			// 이전을 보여줄지 여부 (beginPage == 1 이 아니면 showPrev는 true)
	
	
	public PageResolver(int totalCnt, int page) {
		this(totalCnt, page, 10);     //10은 pageSize임
	}
	
	public PageResolver(int totalCnt, int pageSize, int page) {
		//super();
		this.totalCnt = totalCnt;
		this.pageSize = pageSize;
		this.page = page;
	
		this.totalPage = (int)Math.ceil(totalCnt / (double)pageSize);				// 전체 페이지 갯수를 일반화
		this.beginPage = (page-1) / NAV_SIZE * NAV_SIZE + 1;							// 첫 페이지 숫자(beginPage) 
		this.endPage = Math.min(this.beginPage + this.NAV_SIZE -1, totalPage);		// 마지막 페이지 숫자
		this.showPrev = beginPage != 1; 		//첫 페이지일때만 showPrev가 나오지 않게 함
		this.showNext = endPage != totalPage;		//마지막 페이지가 전체페이지갯수와 일치하지 않을때까지 showNext 나타냄
		}
	public void print() {
		System.out.println("page = " + page);
		System.out.println(showPrev ? "PREV" : "");
		
		for(int i = beginPage; i < endPage; i++) {
			System.out.println(i + " ");
		}
		System.out.println(showNext ? "NEXT" : "");
		
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isShowNext() {
		return showNext;
	}

	public void setShowNext(boolean showNext) {
		this.showNext = showNext;
	}

	public boolean isShowPrev() {
		return showPrev;
	}

	public void setShowPrev(boolean showPrev) {
		this.showPrev = showPrev;
	}

	public int getNAV_SIZE() {
		return NAV_SIZE;
	}
	
	
}
