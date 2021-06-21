package org.hch.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForumController {
	@GetMapping("/general")
	public String general() {
		return "/forum/generalForum";
	}
}
