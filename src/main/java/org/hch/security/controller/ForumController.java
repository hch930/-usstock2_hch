package org.hch.security.controller;

import org.hch.security.board.model.Board;
import org.hch.security.board.model.BoardDto;
import org.hch.security.board.service.BoardService;
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
	
	@PostMapping("/save")
	public Board save(@RequestBody BoardDto dto) {
		return boardService.save(dto);
	}
	
	@PutMapping("/update/{id}")
	public void update(@PathVariable Long id, @RequestBody BoardDto dto) {
		boardService.update(id, dto);
	}
}
