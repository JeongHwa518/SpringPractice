package com.kosta.september.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kosta.september.domain.BoardDto;
import com.kosta.september.domain.PageResolver;
import com.kosta.september.domain.Searchitem;
import com.kosta.september.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@PostMapping("/modify")
	public String modify(BoardDto boardDto, Integer page, Integer pageSize, RedirectAttributes rattr, Model m, HttpSession session) {
		String writer = (String)session.getAttribute("id");
		boardDto.setWriter(writer);
		
		try {
			if(boardService.modify(boardDto) != 1) {
				throw new Exception("Modify Failed");
				}
				rattr.addAttribute("page", page);
				rattr.addAttribute("pageSize", pageSize);
				rattr.addFlashAttribute("msg", "MOD_OK");
				return "redirect:/board/list";
						
		} catch(Exception e) {
			e.printStackTrace();
			m.addAttribute(boardDto);    		//키, 값 동일할 경우 키 생략 가능
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
			m.addAttribute("msg", "MOD_ERR");
			return "board";
		}
	}
	
	@PostMapping("/write")
	public String write(BoardDto boardDto, RedirectAttributes rattr, Model m, HttpSession session) {
		String writer = (String)session.getAttribute("id");
		boardDto.setWriter(writer);
			
		try {
			if(boardService.write(boardDto) != 1)   //1이 아닐경우, 문제발생한것임
				throw new Exception("Write Failed");
			
			rattr.addFlashAttribute("msg", "WRT_OK");
			return "redirect:/board/list";
			
		} catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("mode", "new"); 					// 글쓰기 모드
			m.addAttribute("boardDto", boardDto);		// 등록하려던 내용을 보여줘야 함 (저장) 
			m.addAttribute("msg", "WRT_ERR");
			return "board";
		}
	}
	
	@GetMapping("/write")
	public String write(Model m) {
		m.addAttribute("mode", "new");		// board.jsp 읽기와 쓰기에 사용. 쓰기에 사용할때는 mode=new
		return "board";
	}	
	
	@PostMapping("/remove")		
	public String remove(Integer bno, Integer page, Integer pageSize, RedirectAttributes rattr, HttpSession session) {  //RedirectAttributes -> 알림창 한번만 뜨게해줌
		String writer = (String) session.getAttribute("id");
		String msg = "DEL_OK";
		
		try {
			if(boardService.remove(bno, writer) != 1)
				throw new Exception("Delete failed.");
		} catch (Exception e) {
			e.printStackTrace();
			msg = "DEL_ERR";
		}
		rattr.addAttribute("page", page);
		rattr.addAttribute("pageSize", pageSize);
		//rattr.addAttribute("msg", msg);
		rattr.addFlashAttribute("msg", msg);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/read")
	public String read(Integer bno, Searchitem sc, Model m) {
		
		try {
			BoardDto boardDto = boardService.read(bno);
			//m.addAttribute("boardDto", boardDto);
			m.addAttribute(boardDto);			//키값이 리턴타입과 똑같으면서 소문자로 쓴경우 키값 생략 가능
//			m.addAttribute("page", page);
//			m.addAttribute("pageSize", pageSize);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/board/list";
		}
		
		return "board";
	}

	@GetMapping("/list")
	public String list(@ModelAttribute Searchitem sc, Model m, HttpServletRequest request) {	
		//@ModelAttribute가 생략(안적어도 자동 추가됨 Searchitem의 4개의 파라미터를 추가한다는 뜻)
		// 로그인 안했으면 로그인 화면으로 이동 
		if(!loginCheck(request))
			return "redirect:/login/login?toURL="+request.getRequestURL();
		
		 try {
			 
//			 if(page==null) page = 1;
//			 if(pageSize == null) pageSize = 10;
			 
			int totalCnt = boardService.getSearchResultCount(sc);
			m.addAttribute("totalCnt", totalCnt);
			
			PageResolver pageResolver = new PageResolver(totalCnt, sc);
			m.addAttribute("pr", pageResolver);
			
//			Map map = new HashMap();			// 파라미터로 키,값을 넣을 필요가 없음 (Searchitem에 다 포함돼있음)
//			map.put("offset", (page-1)*pageSize);
//			map.put("pageSize", pageSize);
			 			 
			List<BoardDto> list  = boardService.getSearchResultPage(sc);
			m.addAttribute("list", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "boardList";		// 로그인 한 상태이면 게시판 목록 화면으로 이동
	}

	private boolean loginCheck(HttpServletRequest request) {
		//세션을 얻어서
		HttpSession session = request.getSession();
		//세션에 id가 있는지 확인, 있으면 true반환
		return session.getAttribute("id") != null;		//true 반환, null이면 false반환
	}
}



