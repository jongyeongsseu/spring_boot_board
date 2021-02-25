package edu.bit.ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.bit.ex.vo.BoardVO;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		BoardVO board = new BoardVO();
		
		board.setbContent("컨텐츠");
		board.setbTitle("타이틀");
		board.setbName("홍길동");
		
		model.addAttribute("board", board);
		
		return "thymeleaf/index";
	}
}
