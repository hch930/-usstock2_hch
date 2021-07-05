package org.hch.security.controller;

import org.hch.security.board.model.Board;
import org.hch.security.board.model.BoardDto;
import org.hch.security.board.service.BoardService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ForumDataController {
	private final BoardService boardService;
		
	@PostMapping("/generalForum/save")
	public RedirectView save(@ModelAttribute BoardDto dto) {
		boardService.save(dto);
		return new RedirectView("/generalForum");
	}
	
	@PutMapping("/generalForum/update/{id}")
	public void update(@PathVariable Long id, @RequestBody BoardDto dto) {
		boardService.update(id, dto);
	}
	
	@DeleteMapping("/generalForum/delete/{id}")
	public void delete(@PathVariable Long id) {
		boardService.delete(id);
	}
}
