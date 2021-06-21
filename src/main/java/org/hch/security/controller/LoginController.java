package org.hch.security.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.hch.security.auth.model.User;
import org.hch.security.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource(name = "userServiceImpl")
	private UserService userService;

	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model) {
		return "/auth/login";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "/auth/registration";
	}

	@PostMapping("/registration")
	public String createNewUser(Model model, @Valid User user, BindingResult bindingResult) {
		try {
			User userExists = userService.findUserByUsername(user.getUsername());
			if (userExists != null) {
				bindingResult.rejectValue("email", "error.user",
						"There is already a user registered with the email provided");
			}
			if (!user.getPassword().equals(user.getConfirmpassword())) {
				bindingResult.rejectValue("confirmpassword", "error.user", "패스워드가 일치하지 않습니다.");
			}
			if (bindingResult.hasErrors()) {
				log.error("[hch] : " + bindingResult.getFieldError().toString());
			} else {
				userService.saveUser(user);
				model.addAttribute("user", new User());

				model.addAttribute("successMessage", "회원가입이 되었습니다.");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			model.addAttribute("successMessage", "FAIL : " + e.getMessage());
		}
		return "/auth/registration";
	}

	@GetMapping("/home")
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = null;
		try {
			user = userService.findUserByUsername(auth.getName());
		} catch (Exception e) {
			log.error("[hch]" + e.getMessage());
		}

		model.addAttribute("username", "" + user.getUsername());
		model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");

		return "/index";
	}

	@GetMapping("/stock")
	public String stock() {
		return "/stock";
	}

	/**
	 * Administration Home
	 */
	@RequestMapping(value = "/home/admin", method = RequestMethod.GET)
	public String adminHome(Model model) {
		return "/home/admin";
	}

	/**
	 * User Home
	 */
	@RequestMapping(value = "/home/user", method = RequestMethod.GET)
	public String userHome(Model model) {
		return "/home/user";
	}

	/**
	 * Guest Home
	 */
	@RequestMapping(value = "/home/guest", method = RequestMethod.GET)
	public String guestHome(Model model) {
		return "/home/guest";
	}
}
