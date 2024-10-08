package com.kosta.september.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.september.domain.BoardDto;
import com.kosta.september.domain.Searchitem;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession session;
	private static String namespace = "com.kosta.september.dao.BoardMapper.";    //boardMapper.xml 의 <sql> namespace 주소 복사 끝에 '.' 붙이기
	
	@Override
	public BoardDto select(Integer bno) throws Exception {
		
		return session.selectOne(namespace + "select", bno);
	}

	@Override
	public int deleteAll() throws Exception {
		return session.delete(namespace + "deleteAll");
		
	}

	@Override
	public int insert(BoardDto dto) throws Exception {
		return session.insert(namespace + "insert", dto);
	}

	@Override
	public int delete(Integer bno, String writer) throws Exception {
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("writer", writer);
		return session.delete(namespace + "delete", map);
	}

	@Override
	public int update(BoardDto boardDto) throws Exception {
		return session.update(namespace + "update", boardDto);
	}

	@Override
	public int count() throws Exception {
		return session.selectOne(namespace + "count");
	}

	@Override
	public List<BoardDto> selectPage(Map map) throws Exception {
		return session.selectList(namespace + "selectPage", map);
	}

	@Override
	public int increaseViewCnt(Integer bno) throws Exception {
		
		return session.update(namespace + "increaseViewCnt", bno);
	}

	@Override
	public List<BoardDto> searchSelectPage(Searchitem sc) throws Exception {
		return session.selectList(namespace + "searchSelectPage", sc);
	}

	@Override
	public int searchResultCnt(Searchitem sc) throws Exception {
		return session.selectOne(namespace + "searchResultCnt", sc);   // mapper에 있는 select 쿼리문 호출
	}


}

