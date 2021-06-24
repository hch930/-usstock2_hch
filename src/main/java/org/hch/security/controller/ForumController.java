package org.hch.security.controller;

import org.hch.security.board.repository.BoardRepository;
import org.hch.security.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class ForumController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/general")
	public String general(@PageableDefault Pageable pageable, Model model) {
		model.addAttribute("boardList", boardService.findBoardList(pageable));
		return "/forum/generalForum";
	}
}
