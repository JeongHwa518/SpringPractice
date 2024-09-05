package com.kosta.september.domain;

public class PageResolver {
	private Searchitem sc;
	private int totalCnt;				// 게시물 총 건수
	//private int pageSize = 10;   // 한 페이지당 게시물 건수
	public final int NAV_SIZE = 10; 	// page navigation size
	
	private int totalPage;			// 전체 페이지 갯수
	//private int page;					// 현재 페이지
	
	private int beginPage;			// 화면에 보여줄 첫 페이지
	private int endPage;				// 화면에 보여줄 마지막 페이지
	private boolean showNext = false;			// 이후를 보여줄지 여부 (endPage == totalPage showNext는 false)
	private boolean showPrev = false;			// 이전을 보여줄지 여부 (beginPage == 1 이 아니면 showPrev는 true)
	
	
	public PageResolver(int totalCnt, Integer page) {
		this(totalCnt, new Searchitem(page, 10));     //10은 pageSize임 (아래 생성자 호출)
	}
	
	public PageResolver(int totalCnt, Integer pageSize, Integer page) {
		//super();
		this(totalCnt, new Searchitem(page, pageSize));
		}
	
	public PageResolver(int totalCnt, Searchitem sc) {
		this.totalCnt = totalCnt;
		this.sc = sc;	
		doPaging(totalCnt, sc);
	}
	
	public void doPaging(int totalCnt, Searchitem sc) {
		//this.totalPage = (int)Math.ceil(totalCnt / (double)pageSize);				// 전체 페이지 갯수를 일반화
		this.totalPage = totalCnt / sc.getPageSize() + (totalCnt % sc.getPageSize() == 0 ? 0 : 1);				// 나누어 떨이지지 않으면 한페이지가 더필요하므로 1추가
		this.sc.setPage(Math.min(sc.getPage(), totalPage));			// page가 totalPage보다 크지 않음
		this.beginPage = (this.sc.getPage()-1) / NAV_SIZE * NAV_SIZE + 1;							// 첫 페이지 숫자(beginPage) 
		this.endPage = Math.min(this.beginPage + this.NAV_SIZE -1, totalPage);		// 마지막 페이지 숫자
		this.showPrev = beginPage != 1; 		//첫 페이지일때만 showPrev가 나오지 않게 함
		this.showNext = endPage != totalPage;		//마지막 페이지가 전체페이지갯수와 일치하지 않을때까지 showNext 나타냄
	}
	
	public void print() {
		System.out.println("page = " + sc.getPage());
		System.out.println(showPrev ? "PREV " : "");
		
		for(int i = beginPage; i <= endPage; i++) {
			System.out.println(i + " ");
		}
		System.out.println(showNext ? " NEXT" : "");
	}

	public Searchitem getSc() {
		return sc;
	}

	public void setSc(Searchitem sc) {
		this.sc = sc;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
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

	@Override
	public String toString() {
		return "PageResolver [sc=" + sc + ", totalCnt=" + totalCnt + ", totalPage=" + totalPage + ", beginPage="
				+ beginPage + ", endPage=" + endPage + ", showNext=" + showNext + ", showPrev=" + showPrev + "]";
	}
	
	
}
