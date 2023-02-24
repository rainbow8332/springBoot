package kr.bit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.bit.entity.Board;
import kr.bit.service.BoardService;

@Controller  // POJO
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	// 리스트 
	@GetMapping("/list")
	public String getList(Model model) {
		
		List<Board> list = boardService.getList();
		//객체 바인딩
		model.addAttribute("list", list);
		
		return "board/list";
	}
	
	// 등록 
	@GetMapping("/register")
	public String register() {
		
		return "board/register";
	}
	
	// 로그인 후 글 등록 
	@PostMapping("/register")
	public String register(Board vo, RedirectAttributes rttr) { // 파라미터 수집 <-- 한글 인코딩 필요 
									// RedirectAttributes : 컨트롤러가 redirect 할때 controller의 값을 jsp로 한번만 보낼수 있음, 1회성 세션
		boardService.register(vo); // 게시물 등록(vo -> idx, boardGroup)
		System.out.println(vo);
		rttr.addFlashAttribute("result",vo.getIdx()); // ${result}
		return "redirect:/board/list";
	}
	
	// 상세 
	@GetMapping("/get")
	public String get(@RequestParam("idx") int idx, Model model) {
		// @RequestParam : 넘어오는 파라미터 이름과 idx 이름이 같은경우 생략가능 
		Board vo = boardService.get(idx);
		model.addAttribute("vo", vo);
		return "board/get"; // WEB-INF/views/board/get.jsp <-- spring/appServlet/servlet-context.xml에 설정함 
	}
	
	// 수정 페이지
	@GetMapping("/modify")
	public String modify(@RequestParam("idx") int idx, Model model) {
		Board vo = boardService.get(idx);
		model.addAttribute("vo", vo);
		
		return "board/modify";
	}
	
	// 수정 
	@PostMapping("/modify")
	public String modify(Board vo) { // 파라미터 수집을 위해 vo를 받아줌 
		// update 
		boardService.modify(vo); // 1.수정 후 
		return "redirect:/board/list"; // 2. 리스트 페이지로 이동 
		
	}
	
	// 삭제 
	@GetMapping("/remove")
	public String remove(int idx) {
		boardService.remove(idx);
		return "redirect:/board/list";
	}
	
	// 답글 
	@GetMapping("/reply")
	public String reply(int idx, Model model) {
		Board vo = boardService.get(idx);
		model.addAttribute("vo", vo);
		return "board/reply"; // /WEB-INF/views/board/reply.jsp
	}
	@PostMapping("/reply")
	public String reply(Board vo) {
		// 답글에 필요한 처리....
		boardService.replyProcess(vo); // 답글 저장됨
		return "redirect:/board/list";
	}
	
}







