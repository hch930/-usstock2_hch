package org.hch.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController{
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	private final String DEFAULT_ERROR_PATH = "/error";
	
	public String getErrorPath() {
		return DEFAULT_ERROR_PATH;
	}
	
	@GetMapping("/error")
	public String errorHandle(HttpServletRequest request, Model model) {
		return errorHandleImpl(request, model);
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model model) {
		model.addAttribute("errorCode", "403");
		model.addAttribute("errorMessage", "Forbidden");
		return getErrorPath() + "/error";
	}
	
	private String errorHandleImpl(HttpServletRequest request ,Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
		
		model.addAttribute("errorCode", status.toString());
		model.addAttribute("errorMessage", httpStatus.getReasonPhrase());
		return getErrorPath() + "/error";
	}
}
