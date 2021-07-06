package org.hch.security.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hch.security.board.model.Board;
import org.hch.security.board.model.BoardDto;
import org.hch.security.board.service.BoardService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

	@GetMapping("/generalForum/{idx}")
	public String gDetail(@PathVariable("idx") long idx, Model model) {
		model.addAttribute("detail", boardService.detail(idx));
		return "/forum/generalForumDetail";
	}

	@GetMapping("/generalForum/generalForumInsert")
	public String gInsert(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("writer", userDetails.getUsername());
		return "/forum/generalForumInsert";
	}

	@PostMapping("/generalForum/save")
	public String save(@ModelAttribute @Valid Board board, BindingResult errors, Model model,
			Authentication authentication) {
		if (errors.hasErrors()) {
			Map<String, String> validatorResult = boardService.validateHandling(errors);

			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			model.addAttribute("writer", userDetails.getUsername());
			return "/forum/generalForumInsert";
		}
		boardService.save(board);
		return "redirect:/generalForum";
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
