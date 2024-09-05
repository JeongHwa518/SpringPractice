package com.kosta.september.service;

import java.util.List;
import java.util.Map;

import com.kosta.september.domain.BoardDto;
import com.kosta.september.domain.Searchitem;

public interface BoardService {
	
	List<BoardDto> getPage(Map map) throws Exception;
	int getCount() throws Exception;		// 검색과 무관하게 카운팅
	List<BoardDto> getSearchResultPage(Searchitem sc) throws Exception;
	int getSearchResultCount(Searchitem sc) throws Exception;		// 검색한 것에 대한 카운팅
	
	BoardDto read(Integer bno) throws Exception;
	int remove(Integer bno, String writer) throws Exception;
	int write(BoardDto boardDto) throws Exception;
	int modify(BoardDto boardDto) throws Exception;
			
}
