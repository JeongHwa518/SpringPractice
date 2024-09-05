package com.kosta.september.dao;

import java.util.List;
import java.util.Map;

import com.kosta.september.domain.BoardDto;
import com.kosta.september.domain.Searchitem;

public interface BoardDao {
	
	BoardDto select(Integer bno) throws Exception;
	List<BoardDto> selectPage(Map map) throws Exception;
	List<BoardDto> searchSelectPage(Searchitem sc) throws Exception;
	
	int count() throws Exception;
	int searchResultCnt(Searchitem sc) throws Exception;

	int deleteAll() throws Exception;

	int insert(BoardDto boardDto) throws Exception;
	
	int delete(Integer bno, String writer) throws Exception;
	
	int update(BoardDto boardDto) throws Exception;
	
	int increaseViewCnt(Integer bno) throws Exception;
}
