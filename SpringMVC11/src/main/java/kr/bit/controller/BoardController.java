package kr.bit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.bit.entity.Board;
import kr.bit.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	// 리스트 
	@RequestMapping("/list")
	public String list(Model model) {
		List<Board> list = boardService.getList();
		model.addAttribute("list", list); // ${list}
		return "board/list"; // WEB-INF/board/list.jsp
	}

	// 등록화면 
	@GetMapping("/register")
	public String register() {
		return "register"; // WEB-INF/board/register.jsp
	}
	
	// 등록 
	@PostMapping("/register")
	public String register(Board vo) {
		boardService.register(vo);
		return "redirect:/board/list";
	}
	
	// 상세
	@GetMapping("/get")
	public @ResponseBody Board get(Long idx) {
		Board vo = boardService.get(idx);
		return vo;
	}
	
	// 삭제
	@GetMapping("/remove")
	public String remove(Long idx) {
		boardService.delete(idx); // 삭제성공
		return "redirect:/board/list";
	}
	
	// 수정
	@PostMapping("/modify")
	public String modify(Board vo) {
		boardService.update(vo);
		return "redirect:/board/list";
	}
	
}
