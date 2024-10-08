package com.kosta.september.domain;

import static java.util.Objects.requireNonNullElse;

import org.springframework.web.util.UriComponentsBuilder;

import com.google.protobuf.AbstractMessage.Builder;

import static java.lang.Math.*;

public class Searchitem {
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final int MIN_PAGE_SIZE = 5;
	public static final int MAX_PAGE_SIZE  = 50;
	
	private int page = 1;				// 현재 페이지
	private int pageSize = DEFAULT_PAGE_SIZE;		// 한 페이지당 게시물 건수
	private String option = "";
	private String keyword = "";
	
	public Searchitem() {}

	public Searchitem(int page, int pageSize) {		
		//super();
		this(page, pageSize, "", "");				// 아래의 생성자를 호출함
	}

	public Searchitem(int page, int pageSize, String option, String keyword) {
		//super();
		this.page = page;
		this.pageSize = pageSize;
		this.option = option;
		this.keyword = keyword;
	}
	
	public String getQueryString() {
		return getQueryString(page);
	}
	
	// 페이지 지정해 주는 경우
	// ?page=10&pageSize=10&option=A&keyword=title
	private String getQueryString(int page) {
		return UriComponentsBuilder.newInstance()				// 싱글톤패턴
				.queryParam("page", page)	
				.queryParam("pageSize", pageSize)
				.queryParam("option", option)
				.queryParam("keyword", keyword)
				.build().toString();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = requireNonNullElse(pageSize, DEFAULT_PAGE_SIZE);
		// MIN_PAGE_SIZE <=  pageSize  <= MAX_PAGE_SIZE
		this.pageSize = max(MIN_PAGE_SIZE, min(this.pageSize, MAX_PAGE_SIZE));
	}
	
	public Integer getOffset() {
		int result = (page-1) * pageSize;
		if(result < 0) result = 0;   // 페이지가 0보다 적을 수는 없으므로 0으로 지정
		return result;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "Searchitem [page=" + page + ", pageSize=" + pageSize + ", option=" + option + ", keyword=" + keyword
				+ "]";
	}
	
}
