package org.hch.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.hch.auth.domain.Member;
import org.hch.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@GetMapping(value = {"/login"})
	public String login() {
		return "/auth/login";
	}
	
	@PostMapping(value = {"/login"})
	public String login(Model model) {
		return "/auth/login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("member", new Member());
		return "/auth/register";
	}
	
	@PostMapping("/register")
	public String createNewUSer(Model model, @Valid Member member, BindingResult bindingResult) {
		try {
			Member userExists = userService.getUserByUsername(member.getUsername());
			if(userExists != null) {
				bindingResult.rejectValue("username", "error.user", "이미 생성된 유저입니다.");
				
			if(!member.getPassword().equals(member.getConfirmPassword())) {
				bindingResult.rejectValue("confirmPassword", "error.user", "패스워드가 일치하지 않습니다.");
			}
			
			if(bindingResult.hasErrors()) {
				log.error("[hch] : " + bindingResult.getFieldError().toString());
			}else {
				userService.setUser(member);
				model.addAttribute("user", new Member());
				model.addAttribute("successMessage", "성공적으로 회원가입되었습니다.");
			}
			}
		}catch (Exception e) {
			log.error(e.getMessage());
			model.addAttribute("successMessage", "FAIL: " + e.getMessage());
		}
		return "auth/register";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Member member = null;
		try {
			member = userService.getUserByUsername(auth.getName());
		}catch(Exception e) {
			log.error("[hch]" + e.getMessage());
		}
		model.addAttribute("username", "" + member.getUsername() + "(" + member.getEmail() + ")");
		model.addAttribute("adminMessage", "관리자만 이용할 수 있는 영역입니다.");
		
		return "/index";
	}
	
	@GetMapping("/home/admin")
	public String adminHome(Model model) {
		return "/home/admin";
	}
	
	@GetMapping("/home/user")
	public String userHome(Model model) {
		return "/home/user";
	}
	
	@GetMapping("/home/guest")
	public String guestHome(Model model) {
		return "/home/guest";
	}
}