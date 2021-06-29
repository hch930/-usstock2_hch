package org.hch.security.controller;

import org.hch.security.board.model.Board;
import org.hch.security.board.model.BoardDto;
import org.hch.security.board.service.BoardService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class ForumController {
	private final BoardService boardService;
	
	@GetMapping("/generalForum")
	public String generalForum(Model model) {
		model.addAttribute("board", new Board());
		return "forum/generalForum";
	}
	
	@PostMapping("/save")
	public Board save(@RequestBody BoardDto dto) {
		return boardService.save(dto);
	}
	
	@PutMapping("/update/{id}")
	public void update(@PathVariable Long id, @RequestBody BoardDto dto) {
		boardService.update(id, dto);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		boardService.delete(id);
	}
}
