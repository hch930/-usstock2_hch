package org.hch.security.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hch.security.forum.model.Forum;
import org.hch.security.forum.model.ForumDto;
import org.hch.security.forum.service.ForumService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ForumController {
	private final ForumService forumService;

	@GetMapping("/generalForum")
	public String generalForum(Model model) {
		List<ForumDto> boardDtoList = forumService.getBoardList();
		model.addAttribute("board", boardDtoList);
		return "/forum/generalForum";
	}

	@GetMapping("/generalForum/{idx}")
	public String gDetail(@PathVariable("idx") long idx, Model model) {
		model.addAttribute("detail", forumService.detail(idx));
		return "/forum/generalForumDetail";
	}

	@GetMapping("/generalForum/generalForumInsert")
	public String gInsert(Model model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("writer", userDetails.getUsername());
		return "/forum/generalForumInsert";
	}
	
	@GetMapping("/generalForum/edit/{idx}")
	public String gUpdate(@PathVariable("idx") long idx, Model model) {
		model.addAttribute("detail", forumService.detail(idx));
		return "/forum/generalForumEdit";
	}

	@PostMapping("/generalForum/save")
	public String save(@Valid Forum forum, BindingResult errors, Model model,
			Authentication authentication) {
		if (errors.hasErrors()) {
			Map<String, String> validatorResult = forumService.validateHandling(errors);

			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			model.addAttribute("writer", userDetails.getUsername());
			return "/forum/generalForumInsert";
		}
		forumService.save(forum);
		return "redirect:/generalForum";
	}

	@PutMapping("/generalForum/edit/{idx}")
	public String update(@PathVariable Long idx,Forum forum) {
		forumService.update(idx,forum);
		return "redirect:/generalForum";
	}

	@DeleteMapping("/generalForum/delete/{id}")
	public String delete(@PathVariable Long id) {
		forumService.delete(id);
		return "redirect:/generalForum";
	}
}
