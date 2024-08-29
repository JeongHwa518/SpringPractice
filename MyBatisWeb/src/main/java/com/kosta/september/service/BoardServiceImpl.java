package com.kosta.september.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.september.dao.BoardDao;
import com.kosta.september.domain.BoardDto;

@Service		//빈 등록
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;

	@Override
	public List<BoardDto> getPage(Map map) throws Exception {
		return boardDao.selectPage(map);
	}

	@Override
	public int getCount() throws Exception {
		return boardDao.count();
	}

	@Override
	public BoardDto read(Integer bno) throws Exception {
		BoardDto boardDto = boardDao.select(bno);
		// 조회수 증가(비즈니스 로직처리)
		boardDao.increaseViewCnt(bno);
		return boardDto;
	}

	@Override
	public int remove(Integer bno, String writer) throws Exception {
		return boardDao.delete(bno, writer);
	}

	@Override
	public int write(BoardDto boardDto) throws Exception {
		return boardDao.insert(boardDto);
		//throw new Exception();  무조건 예외처리 시키는것 (오류뜨게 하는 것)
	}

	@Override
	public int modify(BoardDto boardDto) throws Exception {
		return boardDao.update(boardDto);
		//throw new Exception();
	}
	
}
