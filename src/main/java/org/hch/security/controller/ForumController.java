package org.hch.security.controller;

import java.util.List;
import org.hch.security.board.model.BoardDto;
import org.hch.security.board.service.BoardService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ForumController {
	private final BoardService boardService;
	
	@GetMapping("/generalForum")
	public String generalForum(Model model) {
		List<BoardDto> boardDtoList = boardService.getBoardList();	
		model.addAttribute("board", boardDtoList);
		return "/forum/generalForum";
	}
	
	@GetMapping("/generalForum/generalForumInsert")
	public String gInsert(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		model.addAttribute("writer",userDetails.getUsername());
		return "/forum/generalForumInsert";
	}
}
